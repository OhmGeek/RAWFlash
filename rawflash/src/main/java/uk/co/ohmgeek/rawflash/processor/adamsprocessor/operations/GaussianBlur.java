package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.*;
import java.awt.image.*;

/**
 * Created by ryan on 18/10/17.
 */
public class GaussianBlur {
    private int kernel;
    private float sigma;

    public GaussianBlur(int kernel, float sigma) {
        this.kernel = kernel;
        this.sigma = sigma;
    }

    public BufferedImage process(BufferedImage input) {

        System.out.println(input);
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        Kernel k = new Kernel(this.kernel, this.kernel, getKernel(this.kernel,1.0f));
        ConvolveOp gaussianOperation = new ConvolveOp(
                k,
                ConvolveOp.EDGE_ZERO_FILL,
                hints
        );
        // return the resulting image.
        // null here will generate a destination image
        BufferedImage dest =  gaussianOperation.filter(input, null);
        return dest;
    }

    private float[] getKernel(int kernel, double sigma) {
        double sum = 0.0;
        double[] kernelArray = new double[kernel*kernel];
        float[] floatKernelArray = new float[kernel * kernel];
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

        // iterate through the kernel again, dividing by sum.
        for(int i = 0; i < kernel * kernel; i++) {
            floatKernelArray[i] = (float) (kernelArray[i] / sum);
            System.out.println(floatKernelArray[i]);
        }


        return floatKernelArray;
    }
}
