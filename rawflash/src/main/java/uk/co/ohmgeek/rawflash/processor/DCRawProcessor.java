package uk.co.ohmgeek.rawflash.processor;
import uk.co.ohmgeek.jdcraw.DCRawManager;
import uk.co.ohmgeek.jdcraw.operations.SetBrightnessOperation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DCRawProcessor implements AbstractProcessor {


    @Override
    public void process(HashMap<String, String> operations) {
        // now we have all the operations, let's actually run them.
        // get the file to use:
        File fileToProcess = new File(operations.get("filename"));
        DCRawManager dcraw = new DCRawManager(fileToProcess);


        try {
            for(String key : operations.keySet()) {
                // follow the tree, and add the appropriate operations.

                switch (key) {
                    case "exposure":
                        // get the exposure value as a double
                        String exposureValueStr = operations.get("exposure");
                        int exposureValue = Integer.valueOf(exposureValueStr);
                        dcraw.addOperation(new SetBrightnessOperation(exposureValue));
                }
            }
        } catch (Exception ex) {
            // generic error
            ex.printStackTrace();
        }

        // now try to process the image itself.
        try {
            String outputFilename = dcraw.process();
            System.out.println("Output Filename" + outputFilename);
        } catch (IOException e) {
            System.out.println("Error in processing RAW image");
            e.printStackTrace();
        }
    }
}
