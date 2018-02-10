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

var socket = null;




$(function() {

  window.imageDisplay.init();

  socket = io(APP_SETTINGS['server_host'] + ":" + APP_SETTINGS['server_port']);

  socket.on('image-processed', function(data) {
    let image = new Image();
    image.src = data.img;
    APP_SETTINGS['current_image'] = image;
    image.onload = () => {
      window.imageDisplay.renderImage(APP_SETTINGS['current_image']);
    };

  });
});


function updateImageSettings() {
  console.log("Sent job");
  socket.emit("process-image", JSON.stringify(APP_SETTINGS.image_settings));
}

function setProperty(propertyName, value) {
  if(propertyName in APP_SETTINGS['image_settings']) {
    APP_SETTINGS['image_settings'][propertyName] = value;
  }

  $('#renderButton').on('click', updateImageSettings);

}
