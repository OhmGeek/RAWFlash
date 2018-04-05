package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.AccurateKernel;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.ConvolutionOp;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * Created by ryan on 18/10/17.
 */
public class AddImageOp implements IOperation {
   private BufferedImage imageToAdd;
   private double multiplier;
    public AddImageOp(BufferedImage imageToAdd, double multiplier) {
        this.imageToAdd = imageToAdd;
        this.multiplier = multiplier;
    }

    public BufferedImage process(BufferedImage input) {
        // TODO: check dimensions to ensure we can add
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int a = image.getRGB(i, j);
                int b = imageToAdd.getRGB(i,j);


                //TODO: if > 255, then clip rather than throwing error.
                int newRed = (int) a.getRed() + multiplier * b.getRed();
                int newGreen = (int) a.getGreen() + multiplier * b.getGreen();
                int newBlue = (int) a.getBlue() + multiplier * b.getBlue();
                image.setRGB(i, j, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }
        return image;
    }
}
