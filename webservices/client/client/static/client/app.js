$(function() {

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