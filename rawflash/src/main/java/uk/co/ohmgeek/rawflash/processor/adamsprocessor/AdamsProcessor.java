package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import uk.co.ohmgeek.jdcraw.DCRawManager;
import uk.co.ohmgeek.jdcraw.operations.*;
import uk.co.ohmgeek.rawflash.FileManager;
import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.GaussianBlur;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.HistogramEQ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class AdamsProcessor implements AbstractProcessor {
    // Adams Processor, named after Ansel Adams, a photographer and photo editor.
    private BufferedImage image;
    private HashMap<String, Runnable> getListOfCommands(HashMap<String, String> operations) {
        HashMap<String, Runnable> commands = new HashMap<>();

        commands.put("histogram-equalization", () -> {
            HistogramEQ equalizationOperation = new HistogramEQ();
            image = equalizationOperation.process(image);
        });

        return commands;
    }
    @Override
    public void process(HashMap<String, String> operations) {
        // first, we create an instance of the image. We can then pass the image down
        // the image is then modified, and then processed again.

        //todo consider order - we might want to do things in a certain order in the future.
        File fileToProcess = new File(operations.get("dcraw_output_filename"));
        HashMap<String, Runnable> commands = getListOfCommands(operations);

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
        } finally {
            ImageIO.write(image, )
        }



    }
}
