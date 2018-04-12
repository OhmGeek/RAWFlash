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
        

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));
                Color smoothedPixel = new Color(smoothedImage.getRGB(i, j));

                int newRed = Math.round((pixel.getRed() - smoothedPixel.getRed())*amount + pixel.getRed());
                int newGreen = Math.round((pixel.getGreen() - smoothedPixel.getGreen())*amount + pixel.getGreen());
                int newBlue =  Math.round((pixel.getBlue() - smoothedPixel.getBlue())*amount + pixel.getBlue());

                // CLip at 255 if larger than needed.
                newRed = newRed > 255 ? 255 : newRed;
                newGreen = newGreen > 255 ? 255 : newGreen;
                newBlue = newBlue > 255 ? 255 : newBlue;

                newRed = newRed < 0 ? 0 : newRed;
                newGreen = newGreen < 0 ? 0 : newGreen;
                newBlue = newBlue < 0 ? 0 : newBlue;

                image.setRGB(i, j, new Color(newRed, newGreen, newBlue).getRGB());
            }
        }
        // return new AddImageOp(edgeImage, amount).process(image);
        return image;
    }
}
