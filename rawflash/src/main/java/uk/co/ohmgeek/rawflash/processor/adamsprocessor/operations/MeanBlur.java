package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.AccurateKernel;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.ConvolutionOp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Created by ryan on 18/10/17.
 */
public class MeanBlur implements IOperation {
    private int kernel;

    public MeanBlur(int kernel) {
        this.kernel = kernel;
    }

    public BufferedImage process(BufferedImage input) {

        System.out.println(input);
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        AccurateKernel k = getMeanKernel();

        ConvolutionOp meanOperation = new ConvolutionOp(
                k,
                ConvolveOp.EDGE_ZERO_FILL,
                hints
        );

        // return the resulting image.
        // null here will generate a destination image
        BufferedImage dest =  meanOperation.filter(input, null);
        return dest;
    }
    private AccurateKernel getMeanKernel() {
        // mean kernel is just 1/k.

        double[] kernelValues = new double[kernel * kernel];
        int numberOfCells = kernel * kernel;
        for(int i = 0; i < numberOfCells; i++) {

            kernelValues[i] = 1.0 / numberOfCells;
            System.out.println(kernelValues[i]);
        }
        return new AccurateKernel(kernel, kernel, kernelValues);
    }
}
