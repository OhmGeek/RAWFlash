var ImagePicker = {}


$(function() {
    // Deal with CSRF for Django not to throw errors
    function csrfSafeMethod(method) {
        // these HTTP methods do not require CSRF protection
        return (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
    }
    $.ajaxSetup({
        beforeSend: function(xhr, settings) {
            if (!csrfSafeMethod(settings.type) && !this.crossDomain) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        }
    });

    window.ImagePicker.UI = {}
    window.ImagePicker.Album = {}
    window.ImagePicker.Images = {}
    window.ImagePicker.UI.createTitleDOM = function(titleText, descriptionText, buttons) {

        let titleSection = $('<div>');
        titleSection.addClass('w3-container w3-green');

        buttons.forEach(function(button) {
            let btnElement = $('<button>');
            console.log(button.text)
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
        $('#page-container').append(titleElement);
        $('#page-container').append(bodyElement);
    }

    window.ImagePicker.Album.addAlbum = function() {
        // First, ask user to input album name
        swal({
                title: 'New Album Name',
                html: '<input id="swal-input-1" class="swal2-input">' +
                    '<input id="swal-input-2" class="swal2-input">',
                preConfirm: function() {
                    return new Promise(function(resolve) {
                        resolve([$('#swal-input-1').val(), $('#swal-input-2').val()]);
                    });
                },
                button: {
                    text: "Add!",
                    closeModal: true,
                },
                onOpen: function() {
                    $('#swal-input-1').focus()
                },
            }).then(function(values) {
                let name = values.value[0]
                let desc = values.value[1]
                console.log(name);
                return $.post(window.location.origin + "/ajax/add_album_for_user", { 'name': name, 'description': desc });
            }).then(function(resp) {
                window.ImagePicker.Album.displayAlbums(); // update album window
            }).catch(function(err) {
                swal("Error Adding Album", ":(", "error");
            })
            // then, make AJAX request creating album.

        // then display the page by refreshing.
    }


    window.ImagePicker.Album.displayAlbums = function() {
        $.getJSON(window.location.origin + "/ajax/get_albums_for_user", function(data) {
            $('#album_list').empty();
            data.albums.forEach(function(listItem) {
                var listItemDOM = $('<li>');
                listItemDOM.addClass('w3-bar')

                // Add SPAN
                var child = $('<span>');
                child.addClass('w3-bar-item w3-button w3-xlarge w3-right');
                child.text("&#8250;")
                listItemDOM.append(child);

                child = $('<img>');
                child.addClass("w3-bar-item w3-circle");
                child.attr('src', 'https://cataas.com/cat?width=60')
                listItemDOM.append(child);

                child = $('<div>');
                child.addClass('w3-bar-item');

                var subChild = $('<span>');
                subChild.addClass("w3-large");
                subChild.text(listItem.album_name);
                child.append(subChild);

                subChild = $('<br>');
                child.append(subChild);

                subChild = $('<span>');
                subChild.text(listItem.album_description);
                child.append(subChild);

                listItemDOM.append(child);

                $('#album_list').append(listItemDOM);
            });
        })

    }


    window.ImagePicker.Album.render = function() {
        let buttons = []
        buttons.push({
            text: "Add",
            event: function() {
                window.ImagePicker.Album.addAlbum();
            }
        })

        let listPage = $('<ul>');
        listPage.addClass('w3-ul w3-hoverable');
        listPage.attr('id', 'album_list');


        let title = window.ImagePicker.UI.createTitleDOM("View Albums", "Here you can view the folders that images are sorted into", buttons)
        window.ImagePicker.UI.displayPage(title, listPage)
        window.ImagePicker.Album.displayAlbums();
    }



    window.ImagePicker.Images.render = function(album) {
        let buttons = []
        buttons.push({
            text: "Upload",
            event: function() {

            }
        });

        let listPage = $('<ul>');
        listPage.addClass('w3-ul w3-hoverable');
        listPage.attr('id', 'image_list');


        let title = window.ImagePicker.UI.createTitleDOM(album.name, album.description, buttons)
        window.ImagePicker.UI.displayPage(title, listPage)
    }

    // On load, display albums first.
    window.ImagePicker.Album.render()

});