package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import com.google.gson.Gson;
import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.HistogramEQ;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.MeanBlur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

        commands.put("mean-blur", () -> {
            System.out.printf(operations.get("mean-blur"));
            int kernelSize = 3;
            MeanBlur blur = new MeanBlur(kernelSize);
            image = blur.process(image);
        });

        return commands;
    }
    @Override
    public void process(HashMap<String, String> operations) {
        // first, we create an instance of the image. We can then pass the image down
        // the image is then modified, and then processed again.

        //todo consider order - we might want to do things in a certain order in the future.
        File fileToProcess = new File(operations.get("processed_file_path"));
        try {
            this.image = ImageIO.read(fileToProcess);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            try {
                String newFilename = operations.get("processed_file_path").replaceFirst(".tiff", "_adams.tiff");
                File fileToSave = new File(newFilename);
                ImageIO.write(image, "TIFF", fileToSave);
                operations.put("adams_processed_path", newFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
