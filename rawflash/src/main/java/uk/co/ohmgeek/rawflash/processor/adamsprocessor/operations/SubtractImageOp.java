package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.AccurateKernel;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.ConvolutionOp;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * Created by ryan on 18/10/17.
 */
public class SubtractImageOp implements IOperation {
   private BufferedImage imageToSubtract;
    public SubtractImageOp(BufferedImage imageToSubtract) {
        this.imageToSubtract = imageToSubtract;
    }

    public BufferedImage process(BufferedImage image) {
        // go through the image pixel by pixel
        // and subtract each channel.
        // TODO: ensure dimensions match up.
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                Color a = new Color(image.getRGB(i, j));
                Color b = new Color(imageToSubtract.getRGB(i,j));


                // TODO: ensure >= 0
                int newRed = a.getRed() - b.getRed();
                int newGreen = a.getGreen() - b.getGreen();
                int newBlue = a.getBlue() - b.getBlue();
                image.setRGB(i, j, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }
        return image;
    }
}
