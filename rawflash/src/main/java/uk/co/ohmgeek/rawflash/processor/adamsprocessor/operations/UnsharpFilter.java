package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.*;

import java.awt.image.BufferedImage;

public class UnsharpFilter implements IOperation {
    private int radius;
    private double amount;
    private boolean useGaussian;

    public UnsharpFilter(int radius, double amount, boolean useGaussian) {
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

        BufferedImage edgeImage = new SubtractImageOp(smoothedImage).process(image);


        return new AddImageOp(edgeImage, amount).process(image);
    }
}
