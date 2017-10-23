package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

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

    private float[] getKernel(int kernel, float sigma) {
        float[] kernelArray = new float[kernel*kernel];
        for(int i=0; i < kernel; i++) {
            for(int j=0; j<kernel; j++) {
                // gaussian equation
                // Source: Mark S. Nixon and Alberto S. Aguado. Feature Extraction and Image Processing. Academic Press, 2008, p. 88.
                // Accessed 20th Oct 2017 at 5pm
                float exponent = -(i*i + j*j)/(2 * (sigma*sigma));
                float coefficient = (float) (1 / (2 * Math.PI * sigma * sigma));
                // calculate the entire equation.
                kernelArray[i+j] = (float) (coefficient * Math.exp(exponent));
                System.out.println("Value");
                System.out.println(kernelArray[i+j]);
            }
        }


        return kernelArray;
    }
}
