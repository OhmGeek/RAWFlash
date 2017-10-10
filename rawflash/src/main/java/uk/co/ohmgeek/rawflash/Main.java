package uk.co.ohmgeek.rawflash;

import uk.co.ohmgeek.jdcraw.*;
import uk.co.ohmgeek.jdcraw.operations.FileOutputTypeEnum;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;
import uk.co.ohmgeek.jdcraw.operations.SetBrightnessOperation;
import uk.co.ohmgeek.jdcraw.operations.SetFileOutputTypeOperation;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws NegativeBrightnessException, IOException {
        String stringToProcess = "{'filename': '/home/ryan/Pictures/RAW_NIKON_D7100.NEF'}";

        OperationManager opManager = new OperationManager();
        opManager.loadInstructions(stringToProcess);
        opManager.process();
    }
}
