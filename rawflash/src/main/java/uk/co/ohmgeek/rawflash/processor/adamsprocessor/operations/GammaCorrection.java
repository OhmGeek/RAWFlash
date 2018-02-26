package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.GammaLookup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;

public class GammaCorrection implements IOperation {
    private int[] lookup;
    public GammaCorrection(double gamma) {
        // make gamma lookup array.
        lookup = new int[256];
        for(int j = 0; j < lookup.length; j++) {
            lookup[j] = (int) (255 * Math.pow((double) j / (double) 255, gamma));
        }
    }
    @Override
    public BufferedImage process(BufferedImage image) {
        // goes through lookup table,
        // looks up all values based on gamma
        // then adds that to the image.
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                int rgb = image.getRGB(i, j);
                Color colour = new Color(rgb);
                int newRed = lookup[colour.getRed()];
                int newGreen = lookup[colour.getGreen()];
                int newBlue = lookup[colour.getBlue()];

                image.setRGB(i, j, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }
        return image;
    }
}
