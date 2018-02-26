window.ImagePicker.UI = {}

$(function() {


    window.ImagePicker.UI.createTitleDOM = function(titleText, descriptionText, buttons) {

        let titleSection = $('<div>');
        titleSection.addClass('w3-container w3-green');

        buttons.forEach(function(button) {
            let btnElement = $('<button>');
            btnElement.addClass('w3-button w3-teal w3-xlarge w3-right');
            btnElement.on('click', button.event);
            btnElement.text(button.text)
            titleSection.append(btnElement);
        });

        // Now add the title
        let title = $('<h2>');
        title.text(titleText);
        titleSection.append(title);

        let description = $('<p>');
        description.text(descriptionText);
        titleSection.append(description);

        return titleSection;
    }


    window.ImagePicker.UI.displayPage = function(titleElement, bodyElement) {
        $('#page-container').empty();
        $('#page-container').append(titleElement);
        $('#page-container').append(bodyElement);
    }
})