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
        File fileToProcess = new File("/home/ryan/Pictures/RAW_NIKON_D7100.NEF");

        DCRawManager jdcraw = new DCRawManager(fileToProcess);
        jdcraw.addOperation(new SetBrightnessOperation(
                10000
        ));
        jdcraw.addOperation(new SetFileOutputTypeOperation(FileOutputTypeEnum.TIFF));
        String outputFilename = jdcraw.process();
        System.out.println(outputFilename);

    }
}
