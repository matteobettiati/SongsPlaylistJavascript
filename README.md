# Description
A web application that allows the management of a playlist of music tracks. Playlists and tracks are personal to each user and not shared. Each music track is stored in the database with a title, image, album title from which the track is taken, the name of the performer (solo artist or group) of the album, the year of album publication, the music genre (assuming predefined genres), and the music file. The user, after logging in, can create tracks by uploading the relevant data and group them into playlists. A playlist is a collection of tracks chosen from those uploaded by the same user, sorted in descending order by the album's publication year. The same track can be included in multiple playlists. A playlist has a title and a creation date and is associated with its creator. Upon login, the user accesses the HOME PAGE, which displays a list of their playlists, sorted by descending creation date. It also includes a form for uploading a track with all the relevant data and a form for creating a new playlist. Creating a new playlist requires selecting one or more tracks to include. When the user clicks on a playlist in the HOME PAGE, the PLAYLIST PAGE appears, initially containing a table with one row and five columns. Each cell contains the title of a track and the album image it is from. The tracks are arranged from left to right in descending order of the album's publication date. If the playlist contains more than five tracks, commands are available to view the previous and next group of tracks. If the PLAYLIST PAGE displays the first group and there are subsequent groups in the ordering, a NEXT button appears to the right of the row, allowing the user to view the next group. If the PLAYLIST PAGE displays the last group and there are previous groups in the ordering, a PREVIOUS button appears to the left of the row, allowing the user to view the previous five tracks. If the PLAYLIST PAGE displays a block and both previous and next groups exist, a NEXT button appears to the right of the row, and a PREVIOUS button appears to the left. The PLAYLIST PAGE also includes a form that allows selecting and adding a track to the current playlist if it is not already present. After adding a track to the current playlist, the application displays the page again starting from the first block of the playlist. When the user selects the title of a track, the PLAYER PAGE shows all the data of the chosen track and an audio player for playing the track.

# Javascript version
The client-server web application will be modified based on the following specifications:

After the user logs in, the entire application will be implemented on a single page.
Each user interaction will be handled without fully reloading the page, but instead, it will trigger asynchronous server requests and update the content accordingly.
The event of displaying the previous/next block will be handled on the client-side without generating a request to the server.
The application should allow the user to reorder the playlists using a criterion different from the default one (descending date). From the HOME page, each playlist will have an associated link to a REORDER page. The REORDER page will display the complete list of tracks in the playlist and allow the user to drag and drop the title of a track within the list to reposition it and achieve the desired order without invoking the server. Once the user has achieved the desired ordering, they can use a "Save Order" button to store the sequence on the server. In subsequent accesses, the customized ordering will be used instead of the default one.
