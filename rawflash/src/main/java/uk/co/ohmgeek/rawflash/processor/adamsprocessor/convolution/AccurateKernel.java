package uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution;

// Based on:
// https://docs.oracle.com/javase/7/docs/api/java/awt/image/Kernel.html
// only using double instead of float.

public class AccurateKernel implements Cloneable {
    private final int width;
    private final int height;

    private final double[] kernelData;

    private final int xOrigin;
    private final int yOrigin;

    public AccurateKernel(int width, int height, double[] data) {
        this.width = width;
        this.height = height;
        this.kernelData = data;

        xOrigin = (width - 1) / 2;
        yOrigin = (height - 2) / 2;
    }

    public final int getHeight() {
        return height;
    }

    public final int getWidth() {
        return width;
    }

    public final int getXOrigin() {
        return xOrigin;
    }

    public final int getYOrigin() {
        return yOrigin;
    }

    public double[] getKernelData(double[] data) {
        // if data is null here, allocate a new array
        // otherwise, use the current array
        if(data == null) {
            data = new double[this.kernelData.length];
        }

        // now copy to the new array using a System call (much faster).
        // source: https://stackoverflow.com/questions/18638743/is-it-better-to-use-system-arraycopy-than-a-for-loop-for-copying-arrays
        System.arraycopy(this.kernelData, 0, data, 0, data.length);

        // return the new array
        return data;
    }

    public Object clone() {
        // returns a clone of this object
        return new AccurateKernel(width, height, kernelData);
    }
}
