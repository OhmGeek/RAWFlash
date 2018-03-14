/*
  This file contains the code that displays images in the canvas
  on screen.

  Featuring zoom in, zoom out, and other necessary features.
*/

$(function() {

  var viewer;

  window.imageDisplay = {};

  window.imageDisplay['magnification'] = 1.0;

  window.imageDisplay.init = function() {
    viewer = OpenSeadragon({
      element: "canvas-container",
      prefixUrl: "https://openseadragon.github.io/openseadragon/images/",
      tileSources: {
        type: "image",
        url: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg=="
      }
    });
  }

  window.imageDisplay.renderImage = function(image) {
    console.log(image)
    $('#canvas-container').empty();
    viewer = OpenSeadragon({
      element: "canvas-container",
      tileSources: {
        type: "image",
        url: image,
      }
    });
  }

});
   