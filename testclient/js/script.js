var APP_SETTINGS = {
  server_host: "localhost",
  server_port: 4000,
  image_settings: {
    "filename": "http://www.mannyphoto.com/D700D3/_DSC4175.NEF",
    "exposure": 1000,
  },
  magnification: {
    "multiplication_factor": 1
  },
  current_image: null,
};



var socket = io(APP_SETTINGS['server_host'] + ":" + APP_SETTINGS['server_port']);
var canvas = document.getElementById('myCanvas');
var ctx = canvas.getContext("2d");
document.onload = function() {


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
  APP_SETTINGS['current_image'] = image;
  image.onload = renderImage;
});

function renderImage() {
  console.log("drawing image")
  let image = APP_SETTINGS['current_image'];
  ctx.clearRect(0,0,image.width, image.height); //clear canvas
  canvas.width = image.width;
  canvas.height = image.height;
  ctx.drawImage(image, 0, 0);
  let scale_factor = APP_SETTINGS['magnification']['multiplication_factor'];
  ctx.scale(scale_factor, scale_factor);
}

function setSettingsAndUpdate() {
  APP_SETTINGS['image_settings']['exposure'] = document.getElementById('exposure').value;


  if(document.querySelector('#histogram-eq').checked) {
    APP_SETTINGS['image_settings']['histogram-equalization'] = true;
  } else {
    delete APP_SETTINGS['image_settings']['histogram-equalization'];
  }

  if(document.querySelector('#mean-blur-on').checked) {
    APP_SETTINGS['image_settings']['mean-blur'] = document.getElementById('mean-blur-kernel').value;
  } else {
    delete APP_SETTINGS['image_settings']['mean-blur'];
  }

  updateImageSettings();
}


// deal with the scroll wheel for zooming
document.getElementById('myCanvas').addEventListener('wheel', function() {
  APP_SETTINGS['magnification']['multiplication_factor'] *= 0.5;
  renderImage();
});


// sidebar opening/closing
function w3_open() {
  document.getElementById("main").style.marginLeft = "25%";
  document.getElementById("mySidebar").style.width = "25%";
  document.getElementById("mySidebar").style.display = "block";
  document.getElementById("openNav").style.display = 'none';
}
function w3_close() {
  document.getElementById("main").style.marginLeft = "0%";
  document.getElementById("mySidebar").style.display = "none";
  document.getElementById("openNav").style.display = "inline-block";
}

function setProperty(propertyName, value) {
  if(propertyName in APP_SETTINGS['image_settings']) {
    APP_SETTINGS['image_settings'][propertyName] = value;
  }
}
document.addEventListener("DOMContentLoaded", function(event) {
  updateImageSettings();
});
