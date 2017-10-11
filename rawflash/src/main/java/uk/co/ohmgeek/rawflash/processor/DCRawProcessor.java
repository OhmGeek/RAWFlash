package uk.co.ohmgeek.rawflash.processor;
import uk.co.ohmgeek.jdcraw.DCRawManager;
import uk.co.ohmgeek.jdcraw.RAWOperation;
import uk.co.ohmgeek.jdcraw.operations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DCRawProcessor implements AbstractProcessor {

    private HashMap<String, Runnable> getListOfCommands(HashMap<String, String> operations, DCRawManager dcraw) {
        HashMap<String, Runnable> commands = new HashMap<>();


        commands.put("exposure", () -> {
            String exposureValueStr = operations.get("exposure");
            int exposureValue = Integer.valueOf(exposureValueStr);
            try {
                dcraw.addOperation(new SetBrightnessOperation(exposureValue));
            } catch (NegativeBrightnessException e) {
                e.printStackTrace();
            }
        });

        commands.put("white-balance-preset", () -> {
            String wbPreset = operations.get("white-balance-preset");
            try {
                if(wbPreset.equals("camera")) {
                    dcraw.addOperation(new SetWBPresetOperation(WBPreset.CAMERA));
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        return commands;
    }

    @Override
    public void process(HashMap<String, String> operations) {
        // now we have all the operations, let's actually run them.
        // get the file to use:
        File fileToProcess = new File(operations.get("filename"));
        DCRawManager dcraw = new DCRawManager(fileToProcess);
        HashMap<String, Runnable> commands = getListOfCommands(operations, dcraw);

        try {
            for(String key : operations.keySet()) {
                // for each key, go through the hashmap executing the function.

                if(commands.containsKey(key)) {
                    Runnable functionToRun = commands.get(key);
                    functionToRun.run();
                }
            }
        } catch (Exception ex) {
            // generic error
            ex.printStackTrace();
        }

        // force it to always use Tiff, so we can read it.
        dcraw.addOperation(new SetFileOutputTypeOperation(FileOutputTypeEnum.TIFF));


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
