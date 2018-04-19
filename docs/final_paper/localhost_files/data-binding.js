/*
  This contains the code for binding data between image
  parameters, and the physical DOM components.
  Uses Bind.JS
*/
var undoManager = new UndoManager();
$(function() {
  $('#mySidebar').on('change','.input-bound-field', function() {

    var property = $(this).data("linkedproperty")
    var oldValue = APP_SETTINGS['image_settings'][property];
    var value = $(this).val();

    // first, update within the undo manager.
    undoManager.addEdit(property, oldValue, value);

    // then make changes.
    APP_SETTINGS['image_settings'][property] = value;

  });

  $('#mySidebar').on('change','.input-bound-checkbox', function() {

    var property = $(this).data("linkedproperty")
    var value = $(this).prop("checked");
    var oldValue = APP_SETTINGS['image_settings'][property];

    // first, update within the undo manager.
    undoManager.addEdit(property, oldValue, value);

    // then actually make changes.
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



  // Undo/Redo Functionality
  $('#undoButton').on('click', function() {
    undoManager.undo(APP_SETTINGS['image_settings']);
  });
  $('#redoButton').on('click', function() {
    undoManager.redo(APP_SETTINGS['image_settings']);
  });

});
