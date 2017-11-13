var APP_SETTINGS = {
  server_host: "localhost",
  server_port: 3000,
  image_settings: {
    "filename": "/home/ryan/Pictures/RAW_NIKON_D7100.NEF",
    "exposure": 1000,
  }
};


var socket = io("127.0.0.1:3000");
var ctx = document.getElementById('myCanvas').getContext("2d");
document.onload = function() {
  updateImageSettings();

  var can = document.getElementById('myCanvas');
    h = parseInt(document.getElementById("myCanvas").getAttribute("height"));
    w=parseInt(document.getElementById("myCanvas").getAttribute("width"));

    // get it's context
    hdc = can.getContext('2d');

    hdc.strokeStyle = 'red';
    hdc.lineWidth = 2;

    // Fill the path
    hdc.fillStyle = "#9ea7b8";
    hdc.fillRect(0,0,w,h);
    can.style.opacity = '0.2';
}

function updateImageSettings() {
  socket.emit("process-image", JSON.stringify(APP_SETTINGS.image_settings));
}

socket.on('image-processed', function(data) {
  let image = new Image();
  image.src = data;
  image.onload = () => {
    console.log("drawing image");
    ctx.drawImage(image, 0, 0);
  };

});
