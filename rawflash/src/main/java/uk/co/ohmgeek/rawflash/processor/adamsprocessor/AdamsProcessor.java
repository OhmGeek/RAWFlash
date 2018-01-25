package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import com.google.gson.Gson;
import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AdamsProcessor implements AbstractProcessor {
    // Adams Processor, named after Ansel Adams, a photographer and photo editor.
    private BufferedImage image;

    private boolean strToBoolean(String str) {
        String lowerStr = str.toLowerCase();
        return lowerStr.equals("true");
    }

    private HashMap<String, Runnable> getListOfCommands(HashMap<String, String> operations) {
        HashMap<String, Runnable> commands = new HashMap<>();

        commands.put("histogram-equalization", () -> {
            HistogramEQ equalizationOperation = new HistogramEQ();
            image = equalizationOperation.process(image);
        });

        commands.put("mean-blur", () -> {
            String kernelStr = operations.get("mean-blur");
            int kernelSize = Integer.parseInt(kernelStr);

            // Only carry out the process if kernel size is odd AND 3 or more.
            if(kernelSize > 2 && kernelSize % 2 != 0) {
                MeanBlur blur = new MeanBlur(kernelSize);
                image = blur.process(image);
            }
        });
        commands.put("gaussian-blur", () -> {
            String isGaussianOn = operations.get("gaussian-blur");

            if(strToBoolean(isGaussianOn)) {
                String kernelSizeStr = operations.get("gaussian-kernel");
                String sigmaStr = operations.get("gaussian-sigma");

                int kernelSize = Integer.parseInt(kernelSizeStr);
                double sigma = Double.parseDouble(sigmaStr);

                GaussianBlur blur = new GaussianBlur(kernelSize, sigma);
                image = blur.process(image);
            }
        });

        commands.put("gamma-correction", () -> {
            String isGammaCorrectionOn = operations.get("gamma-correction");
            if(strToBoolean(isGammaCorrectionOn)) {
                String gammaStr = operations.get("gamma-correction-val");
                double gamma = Double.parseDouble(gammaStr);

                GammaCorrection corrector = new GammaCorrection(gamma);
            }

        });

        commands.put("adams-wb", () -> {
            // get the rgb gain values from the system
            String isWbOn = operations.get("adams-wb");

            if(strToBoolean(isWbOn)) {
                double redGain = Double.parseDouble(operations.get("adams-wb-red"));
                double greenGain = Double.parseDouble(operations.get("adams-wb-green"));
                double blueGain = Double.parseDouble(operations.get("adams-wb-blue"));

                // now apply the computation
                CustomWhiteBalance wBalanceChange = new CustomWhiteBalance(redGain, greenGain, blueGain);
                image = wBalanceChange.process(image);
            }
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
