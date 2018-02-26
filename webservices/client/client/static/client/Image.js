window.ImagePicker.Images = {}
$(function() {
    // Define a render function.
    function getListItem(image) {
        var listItemDOM = $('<li>');
        listItemDOM.addClass('w3-bar')

        // Add SPAN


        var child = $('<img>');
        child.addClass("w3-bar-item w3-circle");
        child.attr('src', 'https://cataas.com/cat?width=60')
        listItemDOM.append(child);

        child = $('<div>');
        child.addClass('w3-bar-item');

        var subChild = $('<span>');
        subChild.addClass("w3-large");
        subChild.text(listItem.id);
        child.append(subChild);

        subChild = $('<br>');
        child.append(subChild);

        subChild = $('<span>');
        subChild.text(listItem.url);
        child.append(subChild);
        return
    }
    window.ImagePicker.Images.displayImages = function(album) {
        let id = album;
        $.getJSON('/ajax/get_images_for_album/', { id: id })
            .then(function(data) {
                data.forEach(image => {
                    let element = getListItem(image)
                    $('#image_list').append(element);
                });
            })
    }
    window.ImagePicker.Images.render = function(album) {
        let buttons = []
        buttons.push({
            text: "Upload",
            event: function() {
                console.log("Show model");
            }
        });

        let listPage = $('<ul>');
        listPage.addClass('w3-ul w3-hoverable');
        listPage.attr('id', 'image_list');


        let title = window.ImagePicker.UI.createTitleDOM(album.name, album.description, buttons)
        window.ImagePicker.UI.displayPage(title, listPage)
    }
    window.ImagePicker.Album.render()

})