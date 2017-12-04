var APP_SETTINGS = {
  server_host: "localhost",
  server_port: 8000,
  image_settings: {
    "filename": "/home/ryan/Pictures/RAW_NIKON_D7100.NEF",
    "exposure": 1000,
  },
  magnification: {
    "multiplication_factor": 1
  },
  current_image: null,
};


var SIDEBAR_PRESETS = {
  "main": `<button class="w3-bar-item w3-button w3-large" onclick="w3_close()">Close &times;</button>
           <button class="w3-bar-item w3-button" onclick="display_sidebar_preset('exposure')">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
               <path d="M15 17v2h2v-2h2v-2h-2v-2h-2v2h-2v2h2zm5-15H4c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zM5 5h6v2H5V5zm15 15H4L20 4v16z"/>
            </svg>
            Exposure
          </button>
          <button class="w3-bar-item w3-button" onclick="display_sidebar_preset('colour')">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M12 3c-4.97 0-9 4.03-9 9s4.03 9 9 9c.83 0 1.5-.67 1.5-1.5 0-.39-.15-.74-.39-1.01-.23-.26-.38-.61-.38-.99 0-.83.67-1.5 1.5-1.5H16c2.76 0 5-2.24 5-5 0-4.42-4.03-8-9-8zm-5.5 9c-.83 0-1.5-.67-1.5-1.5S5.67 9 6.5 9 8 9.67 8 10.5 7.33 12 6.5 12zm3-4C8.67 8 8 7.33 8 6.5S8.67 5 9.5 5s1.5.67 1.5 1.5S10.33 8 9.5 8zm5 0c-.83 0-1.5-.67-1.5-1.5S13.67 5 14.55s1.5.67 1.5 1.5S15.33 8 14.5 8zm3 4c-.83 0-1.5-.67-1.5-1.5S16.67 9 17.5 9s1.5.67 1.5 1.5-.67 1.5-1.5 1.5z"/>
            </svg>
            Colour Adjustment
          </button>
          <button class="w3-bar-item w3-button" onclick="display_sidebar_preset('automatic')">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M9 3L7.17 5H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V7c0-1.1-.9-2-2-2h-3.17L15 3H9zm3 15c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-55zm0-1l1.25-2.75L16 13l-2.75-1.25L12 9l-1.25 2.75L8 13l2.75 1.25z"/>
            </svg>
              Auto Enhance
          </button>
          <button class="w3-bar-item w3-button" onclick="display_sidebar_preset('export')">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
              <path d="M17 3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V7l-4-4zm-5 16c-1.66 0-3-1.34-3-3s1.34-3 3-3 3 1.34 3 3-1.34 3-3 3zm3-10H5V5h10v4z"/>
            </svg>
            Save/Export
          </button>`,
  "exposure": `<button class="w3-bar-item w3-button w3-large w3-dark-gray" onclick="w3_close()">
                Close &times;
              </button>
              <button class="w3-bar-item w3-button w3-large w3-gray" onclick="display_sidebar_preset('main')">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                  <path d="M15 8.25H5.87l4.19-4.19L9 3 3 9l6 6 1.06-1.06-4.19-4.19H15v-1.5z"/>
                </svg>
                Back
                <span style="padding-left: 120px; font-weight: bold;">
                Exposure
                </span>
              </button>`,
  "colour": `<button class="w3-bar-item w3-button w3-large w3-dark-gray" onclick="w3_close()">
                Close &times;
              </button>
              <button class="w3-bar-item w3-button w3-large w3-gray" onclick="display_sidebar_preset('main')">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                  <path d="M15 8.25H5.87l4.19-4.19L9 3 3 9l6 6 1.06-1.06-4.19-4.19H15v-1.5z"/>
                </svg>
                Back
                <span style="padding-left: 120px; font-weight: bold;">
                  Colour Adjustment
                </span>
            </button>`,
  "automatic": `<button class="w3-bar-item w3-button w3-large w3-dark-gray" onclick="w3_close()">
                Close &times;
              </button>
              <button class="w3-bar-item w3-button w3-large w3-gray" onclick="display_sidebar_preset('main')">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                  <path d="M15 8.25H5.87l4.19-4.19L9 3 3 9l6 6 1.06-1.06-4.19-4.19H15v-1.5z"/>
                </svg>
                Back
                <span style="padding-left: 120px; font-weight: bold;">
                  Auto Enhance
                </span>
            </button>`,
  "export": `<button class="w3-bar-item w3-button w3-large w3-dark-gray" onclick="w3_close()">
                Close &times;
              </button>
              <button class="w3-bar-item w3-button w3-large w3-gray" onclick="display_sidebar_preset('main')">
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                  <path d="M15 8.25H5.87l4.19-4.19L9 3 3 9l6 6 1.06-1.06-4.19-4.19H15v-1.5z"/>
                </svg>
                Back
                <span style="padding-left: 120px; font-weight: bold;">
                  Save/Export
                </span>
            </button>`,
}

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

function display_sidebar_preset(preset) {
  document.getElementById('mySidebar').innerHTML = SIDEBAR_PRESETS[preset];
}

document.addEventListener("DOMContentLoaded", function(event) {
  document.getElementById('mySidebar').innerHTML = SIDEBAR_PRESETS['main'];
  updateImageSettings();
});
