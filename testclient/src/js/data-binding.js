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

  $('#mySidebar').on('change','.input-bound-checkbox', function() {

    var property = $(this).data("linkedproperty")

    var value = $(this).prop("checked");
    APP_SETTINGS['image_settings'][property] = value;
  });



  $('#mySidebar').on('sidebar-updated', function() {
    $('#mySidebar .input-bound-checkbox').each(function(index, elem) {
      var property = $(elem).data("linkedproperty");

      var value = APP_SETTINGS['image_settings'][property];

      // set the value
      $(elem).prop('checked', value);
    });


    $('#mySidebar .input-bound-field').each(function(index, elem) {
      // for each value, we will update the UI
      var property = $(elem).data("linkedproperty");
      var value = APP_SETTINGS['image_settings'][property];

      // set the value
      $(elem).val(value);
    })
  });

  // Set all checkboxes to have the value true when checked, and false when not.
  // This allows the binding to work properly
  $('#mySidebar').on('change', ':checkbox', function() {
    console.log("Checkbox changed");
    this.value = this.checked; // sets to true, or false, depending on whether checked.
    console.log(this.value);
  });

});