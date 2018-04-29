var APP_SETTINGS = {
    server_host: "localhost",
    server_port: 4000,
    image_settings: {
        "filename": "http://www.mannyphoto.com/D700D3/_DSC4175.NEF",
        "exposure": 1,
        "adams-wb": false,
        "adams-wb-red": 1.0,
        "adams-wb-green": 1.0,
        "adams-wb-blue": 1.0,
        "histogram-equalization": false,
        "adams-gamma": false,
        "adams-gamma-gamma": 1.0,
        "adams-meanblur": false,
        "adams-meanblur-kernel": 3,
        "adams-gaussianblur": false,
        "adams-gaussianblur-kernel": 3
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
        APP_SETTINGS['current_image'] = data.img;
        window.imageDisplay.renderImage(data.img);


        // Now deal with the export
        exportImage(data);
    });

    $('#renderButton').on('click', updateImageSettings);
});

function exportImage(data) {
    let outputFilename = "output." + data['export-format'];
    let mimeType = "image/" + data['export-format'];
    download(data['export-base64'], outputFilename, mimeType);


    // // First, we populate the href with base64 data
    // $('#export-button').attr('href', data['export-base64']);
    // // Then set filename
    // let filename = "download" + "." + data['export-format'];
    // $('#export-button').attr('download', filename);
    // // Then open modal
    // document.getElementById('id01').style.display = 'block'
    // console.log("EXPORT")
    // window.open(data['export-base64']);
}

function updateImageSettings() {
    console.log("Sent job");
    socket.emit("process-image", JSON.stringify(APP_SETTINGS.image_settings));
}

function setProperty(propertyName, value) {
    if (propertyName in APP_SETTINGS['image_settings']) {
        APP_SETTINGS['image_settings'][propertyName] = value;
    }



}