var ImagePicker = {}


$(function() {
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

    window.ImagePicker.addAlbum = function() {
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
                return
            }).catch(function(err) {
                swal("Error Adding Album", ":(", "error");
            })
            // then, make AJAX request creating album.

        // then display the page by refreshing.
    }
    $.getJSON(window.location.origin + "/ajax/get_albums_for_user", function(data) {
        $('#album_list').empty();
        data.albums.forEach(function(listItem) {
            var templateString = `
              <li class="w3-bar">
                <span class="w3-bar-item w3-button w3-xlarge w3-right">&#8250;</span>
                <img src="img_avatar2.png" class="w3-bar-item w3-circle" style="width:85px">
                <div class="w3-bar-item">
                  <span class="w3-large"> {{album_name}} </span><br>
                  <span> {{album_description}} </span>
                </div>
              </li>
        `;

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
});