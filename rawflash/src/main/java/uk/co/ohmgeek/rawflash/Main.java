package uk.co.ohmgeek.rawflash;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.GaussianBlur;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String args[]) throws NegativeBrightnessException, IOException {

//        // json string to process for testing purposes
//        String stringToProcess = "{" +
//                    "'filename': '/home/ryan/Pictures/RAW_NIKON_D7100.NEF', " +
//                    "'exposure': 1000," +
//                    "'set-color-space': 'srgb'" +
//                "}";
//
//        // load json instructions into processor, and run
//        OperationManager opManager = new OperationManager();
//        opManager.loadInstructions(stringToProcess);
//        opManager.process();


        BufferedImage img = ImageIO.read(new File("/home/ryan/Downloads/sample.tif"));
        GaussianBlur g = new GaussianBlur(5, 2);
        BufferedImage output = g.process(img);
        File outputfile = new File("/home/ryan/abcd.tiff");
        ImageIO.write(output, "tiff", outputfile);
    }
}
