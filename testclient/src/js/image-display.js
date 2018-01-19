/*
  This file contains the code that displays images in the canvas
  on screen.

  Featuring zoom in, zoom out, and other necessary features.
*/

$(function() {
  var ctx;
  var canvas;
  window.imageDisplay = {};

  window.imageDisplay['magnification'] = 1.0;

  window.imageDisplay.init = function() {
    canvas = document.getElementById('myCanvas');
    ctx = canvas.getContext("2d");
  }

  window.imageDisplay.renderImage = function(image) {
    ctx.clearRect(0,0, image.width, image.height);
    let magnification = imageDisplay['magnification'];
    // set dimensions of canvas
    canvas.width = image.width * magnification;
    canvas.height = image.height * magnification;

    // draw, with magnification.
    ctx.drawImage(image, 0, 0, canvas.width, canvas.height);
  }

});
