package uk.co.ohmgeek.rawflash;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws NegativeBrightnessException, IOException {

        // json string to process for testing purposes
        String stringToProcess = "{" +
                    "'filename': '/home/ryan/Pictures/RAW_NIKON_D7100.NEF', " +
                    "'exposure': 1000," +
                    "'set-color-space': 'srgb'" +
                "}";

        // load json instructions into processor, and run
        OperationManager opManager = new OperationManager();
        opManager.loadInstructions(stringToProcess);
        opManager.process();
    }
}
