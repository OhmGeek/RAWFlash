/*
  This contains the code for binding data between image
  parameters, and the physical DOM components.
  Uses Bind.JS
*/
$(function() {
  $('.input-bound-field').on('change', function() {
    console.log("Change")
    var property = $(this).data("linkedProperty")
    var value = $(this).val();
    APP_SETTINGS['image_settings'][property] = value;

    // todo remove this
    window.updateImageSettings();
  });

  $('.input-bound-field').on('load', function() {
    var property = $(this).data("linkedProperty");
    var value = APP_SETTINGS['image_settings'][property];
    $(this).val(value);
  });
});
