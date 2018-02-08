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
                if (wbPreset.equals("camera")) {
                    dcraw.addOperation(new SetWBPresetOperation(WBPreset.CAMERA));
                }
        });

        commands.put("set-color-space", () -> {
            String wbPreset = operations.get("set-color-space");
            switch (wbPreset) {
                case "raw":
                    dcraw.addOperation(new SetColorSpaceOperation(ColourSpaceEnum.RAW));
                    break;
                case "adobergb":
                    dcraw.addOperation(new SetColorSpaceOperation(ColourSpaceEnum.ADOBE_RGB));
                    break;
                case "srgb":
                    dcraw.addOperation(new SetColorSpaceOperation(ColourSpaceEnum.SRGB));
                    break;
            }
        });

        return commands;
    }

    @Override
    public void process(HashMap<String, String> operations) throws IOException, InterruptedException {
        // now we have all the operations, let's actually run them.
        // get the file to use:
        File fileToProcess = new File(operations.get("filename"));
        DCRawManager dcraw = new DCRawManager(fileToProcess);
        HashMap<String, Runnable> commands = getListOfCommands(operations, dcraw);

        for (String key : operations.keySet()) {
            // for each key, go through the hashmap executing the function.

            if (commands.containsKey(key)) {
                Runnable functionToRun = commands.get(key);
                functionToRun.run();
            }
        }

        // force it to always use Tiff, so we can read it.
        dcraw.addOperation(new SetFileOutputTypeOperation(FileOutputTypeEnum.TIFF));


        // now try to process the image itself.
        String outputFilename = dcraw.process();
        Thread.sleep(500);
        System.out.println("Output Filename " + outputFilename);
        // now add the output filename back to the JSON.
        operations.put("processed_file_path", outputFilename);
    }
}
