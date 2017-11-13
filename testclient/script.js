var APP_SETTINGS = {
  server_host: "localhost",
  server_port: 8000,
  image_settings: {
    "filename": "/home/ryan/Pictures/RAW_NIKON_D7100.NEF",
    "exposure": 1000,
  }
};


var socket = io(APP_SETTINGS['server_host'] + ":" + APP_SETTINGS['server_port']);
var canvas = document.getElementById('myCanvas');
var ctx = canvas.getContext("2d");
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
    canvas.width = image.width;
    canvas.height = image.height;
    ctx.drawImage(image, 0, 0);
  };

});

function setSettingsAndUpdate() {
  APP_SETTINGS['image_settings']['exposure'] = document.getElementById('exposure').value;
  if(document.querySelector('#histogram-eq').checked) {
    APP_SETTINGS['image_settings']['histogram-equalization'] = true;
  } else {
    delete APP_SETTINGS['image_settings']['histogram-equalization'];
  }
  updateImageSettings();
}
