var amqp = require('amqplib/callback_api');
var io = require('socket.io')(4000);
var args = process.argv.slice(2);
amqp.connect('amqp://rabbitmq', function(err, conn) {

  if(conn) {
    conn.createChannel(function(err, ch) {
      ch.assertQueue('', {exclusive: true}, function(err, q) {
        var corr = generateUuid();
        // on a connection.
        io.on("connection", function(client) {
          console.log("Connection made");
          client.on("process-image", function(data) {
            console.log("Request Render");
            // first create a request, by adding json info
            var newJSON = JSON.parse(data);
            newJSON['client'] = client.id;
            console.log(newJSON);
            ch.sendToQueue('rawflash',
              new Buffer(JSON.stringify(newJSON)),
              { correlationId: corr, replyTo: q.queue });
          });

          ch.consume(q.queue, function(msg) {
            // if (msg.properties.correlationId == corr) {
              console.log("REturn");

              var jsonStr = JSON.parse(msg.content);
              var clientid = jsonStr['client']; // todo get client id from JSON
              var client = io.to(clientid);
              // we need to convert to JSON.
              client.emit("image-processed", jsonStr);
            // }
          }, {noAck: false});
        });
      });
    });
  } else if (err) {
    console.log("*** ERROR Connecting to RabbitMQ ***");
    console.log(err);
  }
});

function generateUuid() {
  return Math.random().toString() +
         Math.random().toString() +
         Math.random().toString();
}
