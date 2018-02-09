package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.AccurateKernel;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.ConvolutionOp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.ConvolveOp;

/**
 * Created by ryan on 18/10/17.
 */
public class CustomWhiteBalance implements IOperation {
    private double redGain;
    private double greenGain;
    private double blueGain;

    public CustomWhiteBalance(double redGain, double greenGain, double blueGain) {
        this.redGain = redGain;
        this.greenGain = greenGain;
        this.blueGain = blueGain;
    }

    public BufferedImage process(BufferedImage input) {
        // R_out = R_in * R_gain
        // G _ out = G_in * G_gain
        // B _ out = B_ in * B_gain
        for(int x = 0; x < input.getWidth(); x++) {
            for(int y = 0; y < input.getHeight(); y++) {
                int pixel = input.getRGB(x, y);
                Color c = new Color(pixel);
                
                int alpha_mask = c.getAlpha();
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

                // now apply the gains:
                red = (int) (red * redGain);
                green = (int) (green * greenGain);
                blue = (int) (blue * blueGain);

                input.setRGB(x, y, new Color(red, green, blue, alpha_mask).getRGB());
            }
        }

        return input;
    }



}
