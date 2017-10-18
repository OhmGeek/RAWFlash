package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Created by ryan on 18/10/17.
 */
public class GaussianBlur {
    private int kernel;
    public GaussianBlur(int kernel) {
        this.kernel = kernel;
    }
    public BufferedImage process(BufferedImage input) {

        Kernel k = new Kernel(this.kernel, this.kernel, getKernel(this.kernel));
        ConvolveOp gaussianOperation = new ConvolveOp(
                k,
                ConvolveOp.EDGE_ZERO_FILL,
                null
        );
    }

    private float[] getKernel(int kernel) {
        float[] kernelArray = new float[kernel*kernel];

        return kernelArray;
    }
}
