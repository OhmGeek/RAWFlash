package uk.co.ohmgeek.rawflash;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws NegativeBrightnessException, IOException {
        String stringToProcess = "{'filename': '/home/ryan/Pictures/RAW_NIKON_D7100.NEF', 'exposure': 1000,'set-color-space': 'srgb'}";

        OperationManager opManager = new OperationManager();
        opManager.loadInstructions(stringToProcess);
        opManager.process();
    }
}
