package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramEQ implements IOperation{
    @Override
    public BufferedImage process(BufferedImage image) {
        int[] redHistogram = new int[256];
        int[] greenHistogram = new int[256];
        int[] blueHistogram = new int[256];

        // set histograms to zero.
        for(int i = 0; i < 256; i++) {
            redHistogram[i] = 0;
            greenHistogram[i] = 0;
            blueHistogram[i] = 0;
        }

        // calculate histogram
        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));
                redHistogram[pixel.getRed()] += 1;
                greenHistogram[pixel.getGreen()] += 1;
                blueHistogram[pixel.getBlue()] += 1;
            }
        }

        // now, go through and equalize.

        double scalingFactor = 255.0 / (image.getWidth() * image.getHeight());

        for(int i = 0; i < image.getWidth(); i++) {
            for(int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));

                int red = (int) (redHistogram[pixel.getRed()] * scalingFactor);
                int green = (int) (greenHistogram[pixel.getGreen()] * scalingFactor);
                int blue = (int) (blueHistogram[pixel.getBlue()] * scalingFactor);

                image.setRGB(red,green, blue);
            }
        }

        return image;
    }

}
