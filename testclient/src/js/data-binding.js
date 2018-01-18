/*
  This contains the code for binding data between image
  parameters, and the physical DOM components.
  Uses Bind.JS
*/
$(function() {
  $('#mySidebar').on('change','.input-bound-field', function() {

    var property = $(this).data("linkedproperty")
    var value = $(this).val();
    APP_SETTINGS['image_settings'][property] = value;
  });

  $('#mySidebar').on('sidebar-updated', function() {
    $('#mySidebar .input-bound-field').each(function(index, elem) {
      // for each value, we will update the UI
      var property = $(elem).data("linkedproperty");
      var value = APP_SETTINGS['image_settings'][property];

      // set the value
      $(elem).val(value);
    })
  });

});
