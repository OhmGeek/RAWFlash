package uk.co.ohmgeek.rawflash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

public class FileManager {

    // files initially downloaded from the server.
    // First, check the cache (downloaded onto the system, for the file)
    // then, if not, then download from the main hosting server.
    // Then, add a local_filename tag.


    private final String CACHE_DIRECTORY = "/home/ryan/rawflash_cache/";

    public FileManager() {

    }

    public void downloadFile(String uri) throws IOException {
        // create a URL representation
        URL webResource = new URL(uri);

        // extract the filename, for caching
        // todo find a way to avoid filename collisions
        String filename = webResource.getFile();

        filename = filename.substring(filename.lastIndexOf("/")+1); // get the file name itself and extension

        System.out.println(filename);
        // open the web channel to start downloading the file
        ReadableByteChannel byteChannel = Channels.newChannel(webResource.openStream());

        File cachedFile = new File(CACHE_DIRECTORY + filename);


        // open a local file, in the cache directory, with the filename as before.
        FileOutputStream fileStream = new FileOutputStream(cachedFile);

        // write to the file.
        fileStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

        // close the file
        fileStream.close();
    }


}
