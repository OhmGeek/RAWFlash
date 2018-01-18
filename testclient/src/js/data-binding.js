/*
  This contains the code for binding data between image
  parameters, and the physical DOM components.
  Uses Bind.JS
*/
$(function() {
  $('#mySidebar').on('change','.input-bound-field', function(e) {

    var property = $(this).data("linkedproperty")
    var value = $(this).val();
    console.log("Set " + property + " to " + value);
    APP_SETTINGS['image_settings'][property] = value;
  });


});
