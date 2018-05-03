package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.AccurateKernel;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution.ConvolutionOp;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * Created by ryan on 18/10/17.
 */
public class GaussianBlur implements IOperation {
    private int kernelSize;
    private double sigma;

    public GaussianBlur(int kernelSize, double sigma) {
        this.kernelSize = kernelSize;
        this.sigma = sigma;
    }

    public BufferedImage process(BufferedImage input) {
        if(kernelSize < 3 || kernelSize % 2 == 0) {
            return input; //Do Nothing
        }
        System.out.println(input);
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        AccurateKernel k = new AccurateKernel(this.kernelSize, this.kernelSize, getKernel(this.kernelSize,this.sigma));
        ConvolutionOp gaussianOperation = new ConvolutionOp(
                k,
                ConvolveOp.EDGE_ZERO_FILL,
                hints
        );
        // return the resulting image.
        // null here will generate a destination image
        BufferedImage dest =  gaussianOperation.filter(input, null);
        return dest;
    }

    private double[] getKernel(int kernel, double sigma) {
        double sum = 0.0;
        double[] kernelArray = new double[kernel*kernel];
        int counter = -1;
        for(int i = 0; i < kernel; i++) {
            for(int j = 0; j < kernel; j++) {
                counter++;
                // gaussian equation
                // Source: Mark S. Nixon and Alberto S. Aguado. Feature Extraction and Image Processing. Academic Press, 2008, p. 88.
                // Accessed 20th Oct 2017 at 5pm
                double exponent = -(i*i + j*j)/(2 * (sigma*sigma));
                double coefficient = (1 / (2 * Math.PI * sigma * sigma));
                // calculate the entire equation.
                double value = (coefficient * Math.exp(exponent)) * 10000;
                sum += value;
                kernelArray[counter] = value;
            }
        }

        // iterate through the kernelSize again, dividing by sum.
        for(int i = 0; i < kernel * kernel; i++) {
            kernelArray[i] = (kernelArray[i] / sum);
            System.out.println(kernelArray[i]);
        }


        return kernelArray;
    }
}
