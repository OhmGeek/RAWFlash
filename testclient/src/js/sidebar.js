// this deals with the sidebar.

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

// this jquery element deals with the view loading
$(function(){
  // define the function when jQuery is present.
  window.display_sidebar_preset = function(preset) {
    let fileToLoad = '/ajax/' + preset + '.html';
    $('#mySidebar').load(fileToLoad, function() {
      $('#mySidebar').trigger("sidebar-updated");
    });
  }

  // once loaded, display the main sidebar preset.
  display_sidebar_preset('sidebar-main');
});
