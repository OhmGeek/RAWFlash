package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.*;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class UnsharpFilter implements IOperation {
    private int radius;
    private float amount;
    private boolean useGaussian;

    public UnsharpFilter(int radius, float amount, boolean useGaussian) {
        this.radius = radius;
        this.amount = amount;
        this.useGaussian = useGaussian;
    }
    @Override
    public BufferedImage process(BufferedImage image) {
        // First, find the smoothed image.
        double SIGMA = 1.0;
        IOperation smoothedImageOp;
        if(this.useGaussian) {
            smoothedImageOp = new GaussianBlur(radius, SIGMA);
        } else {
            smoothedImageOp = new MeanBlur(radius);
        }
        BufferedImage smoothedImage = smoothedImageOp.process(image);

        // BufferedImage edgeImage = new SubtractImageOp(smoothedImage).process(image);
        
        // Now, we need to deal with the edge enhancement.

        // We need to create an image, with (image - smoothedImage)*amount + image result
        
        float[][][] sharpenedImage = new float[image.getWidth()][image.getHeight()][3];
        
        // Minimum values to be used in the normalization
        float minRed = Float.MAX_VALUE;
        float minGreen = Float.MAX_VALUE;
        float minBlue = Float.MAX_VALUE;

        // Maximum Values to be used in the normalization
        // TODO explain normalization
        float maxRed = Float.MIN_VALUE;
        float maxGreen = Float.MIN_VALUE;
        float maxBlue = Float.MIN_VALUE;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));
                Color smoothedPixel = new Color(smoothedImage.getRGB(i, j));

                float newRed = ((pixel.getRed() - smoothedPixel.getRed())*amount + pixel.getRed());
                float newGreen = ((pixel.getGreen() - smoothedPixel.getGreen())*amount + pixel.getGreen());
                float newBlue =  ((pixel.getBlue() - smoothedPixel.getBlue())*amount + pixel.getBlue());

                minRed = newRed < minRed ? newRed : minRed; 
                minGreen = newGreen < minGreen ? newGreen : minGreen; 
                minBlue = newBlue < minBlue ? newBlue : minBlue; 

                maxRed = newRed > maxRed ? newRed : maxRed;
                maxGreen = newGreen > maxGreen ? newGreen : maxGreen;
                maxBlue = newBlue > maxBlue ? newBlue : maxBlue;
                // store in an array, for normalization.
                sharpenedImage[i][j][0] = newRed;
                sharpenedImage[i][j][1] = newGreen;
                sharpenedImage[i][j][2] = newBlue;

            }
        }

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                // Constrast STretch (Normalize)
                int red = Math.round(((sharpenedImage[i][j][0] - minRed)/(maxRed - minRed)) * 255);
                int green = Math.round(((sharpenedImage[i][j][1] - minGreen)/(maxGreen - minGreen)) * 255);
                int blue = Math.round(((sharpenedImage[i][j][2] - minBlue)/(maxBlue - minBlue)) * 255);

                image.setRGB(i, j, (new Color(red, green, blue).getRGB()));
            }
        }
        return image;
    }
}
