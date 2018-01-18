package uk.co.ohmgeek.rawflash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.UUID;

public class FileManager {

    // files initially downloaded from the server.
    // First, check the cache (downloaded onto the system, for the file)
    // then, if not, then download from the main hosting server.
    // Then, add a local_filename tag.


    private final String CACHE_DIRECTORY = "/rawflash_cache/";

    public FileManager() {

    }

    private String getFilenameFromUUID(URL url) {

        System.out.println(url);
        // first, get the UUID, by converting URI to bytes.
        UUID uuid = UUID.nameUUIDFromBytes(url.toString().getBytes());// same url should have same uri.
        // convert to string
        String filename = uuid.toString();
        // to lower case, and remove dashes
        filename = filename.toLowerCase().replace("-","");


        // then, get the file extension (as a string). This should really be done
        // differently to make it safer, but it's a good assumption for testing.
        // Also assumes there is an extension. If there isn't the system will crash
        String[] separates = url.toString().split("\\.");

        if(separates.length == 0) {
            //todo throw exception
            System.out.println("Not split");
        }

        // get the extension
        String ext = "." + separates[separates.length - 1];
        return filename + ext;

    }

    public File downloadFile(String uri) throws IOException {
        //todo check cache for whether a file has already been downloaded
        // todo find a way to prevent filename collisions by randomising the filename

        // create a URL representation
        URL webResource = new URL(uri);


        // open the web channel to start downloading the file
        ReadableByteChannel byteChannel = Channels.newChannel(webResource.openStream());

        // check if the CACHE directory exists, if not, create it and all parent directories if they don't exist
        File cachedDirectory = new File(CACHE_DIRECTORY);

        if(!cachedDirectory.exists()) {
            cachedDirectory.mkdirs();
        }
        String filename = getFilenameFromUUID(webResource);
        System.out.println(filename);
        // now create the cached file
        File cachedFile = new File(CACHE_DIRECTORY + filename);

        // if cached file exists, it's already downloaded
        // if it isn't cached, download a copy.
        if(!cachedFile.exists()) {
            // open this cached file, as a stream, to be written to.
            FileOutputStream fileStream = new FileOutputStream(cachedFile);

            // write to the file.
            fileStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);

            // close the file
            fileStream.close();
        }

        // return a File object, for reference.
        return cachedFile;
    }


}
