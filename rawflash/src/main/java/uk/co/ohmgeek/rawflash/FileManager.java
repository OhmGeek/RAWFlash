package uk.co.ohmgeek.rawflash;

import java.io.File;

public class FileManager {

    // files initially downloaded from the server.
    // First, check the cache (downloaded onto the system, for the file)
    // then, if not, then download from the main hosting server.
    // Then, add a local_filename tag.


    public FileManager() {

    }


    public void addFilenameToInstructions(String jsonStr) {
        // this adds the appropriate filename to instructions
        String filename = getRAWFile();

    }

    private String getRAWFile() {
        String f = "/path/to/file";
        // check cache.

        // if not in cache
        // download file from url, then store it in cache for use in the future.

        //return the file
        return f;
    }


}
