var amqp = require('amqplib/callback_api');
var io = require('socket.io')(4000);



var args = process.argv.slice(2);

if (args.length == 0) {
  console.log("Usage: rpc_client.js num");
  process.exit(1);
}
amqp.connect('amqp://localhost', function(err, conn) {
  conn.createChannel(function(err, ch) {
    ch.assertQueue('', {exclusive: true}, function(err, q) {
      var corr = generateUuid();
      // on a connection.
      io.on("connection", function(client) {
        client.on("request-render", function(data) {
          // first create a request, by adding json info
          var newJSON = JSON.parse(data);
          newJSON['client'] = client.id;
          ch.sendToQueue('rpc_queue',
            new Buffer(JSON.stringify(newJSON)),
            { correlationId: corr, replyTo: q.queue });
        });
      })

      ch.consume(q.queue, function(msg) {
        if (msg.properties.correlationId == corr) {
          var response = msg.content.toString();
          var jsonStr = JSON.parse(response);
          var clientid = jsonStr['client']; // todo get client id from JSON
          var client = io.to(clientid);
          // we need to convert to JSON.
          client.emit("rendered", response);
          setTimeout(function() { conn.close(); process.exit(0) }, 500);
        }
      }, {noAck: false});


    });
  });
});

function generateUuid() {
  return Math.random().toString() +
         Math.random().toString() +
         Math.random().toString();
}
