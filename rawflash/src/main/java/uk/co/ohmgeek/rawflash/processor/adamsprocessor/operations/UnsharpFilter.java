package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.GammaLookup;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;

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
            smoothedImageOp = GaussianBlur(radius, SIGMA);
        } else {
            smoothedImageOp = MeanBlur(radius);
        }
        BufferedImage smoothedImage = smoothedImageOp.process(image);

        BufferedImage edgeImage = SubtractImageOp(smoothedImage).process(image);

        return AddImageOp(edgeImage, amount).process(image);
    }
}
