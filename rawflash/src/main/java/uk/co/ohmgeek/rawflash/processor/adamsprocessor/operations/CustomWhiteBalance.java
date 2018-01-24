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

                int alpha_mask = pixel & 0xff000000;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

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
