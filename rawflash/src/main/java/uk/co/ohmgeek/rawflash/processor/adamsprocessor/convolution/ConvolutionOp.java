package uk.co.ohmgeek.rawflash.processor.adamsprocessor.convolution;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

public class ConvolutionOp implements BufferedImageOp {
    private AccurateKernel kernel;
    public ConvolutionOp(AccurateKernel kernel, int i, RenderingHints renderingHints) {
        this.kernel = kernel;
    }

    @Override
    public BufferedImage filter(BufferedImage bufferedImage, BufferedImage dest) {
        if(dest == null) {
            dest = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        }

        double[] kernelData = kernel.getKernelData(null);
        // implement a standard for loop approach
        // for each pixel, convolve. Then use rgba.

        for(int i = 0; i < bufferedImage.getWidth(); i++) {
            for(int j = 0; j < bufferedImage.getHeight(); j++) {
                int incX = this.kernel.getWidth() - 1;
                incX /= 2;

                int incY = this.kernel.getHeight() - 1;
                incY /= 2;

                int red = 0;
                int green = 0;
                int blue = 0;
                int alpha = 0;

                int kernelArrayIndex = 0;
                for(int kernelX = -incX; kernelX < incX; kernelX++) {
                    for(int kernelY = -incY; kernelY < incY; kernelY++) {
                        int locationX = i + kernelX;
                        int locationY = j + kernelY;

                        if(locationX < 0) {
                            locationX *= -1; //find abs value
                            locationX -= 1; //then subtract one, so 1 becomes 0.
                        }

                        if(locationY < 0) {
                            locationY *= -1;
                            locationY -= 1; //then subtract one, so 1 becomes 0.
                        }

                        if(locationX > bufferedImage.getWidth()) {
                            //take the overflow, and get the rightmost pixels.
                            locationX = locationX - bufferedImage.getWidth();
                            locationX = bufferedImage.getWidth() - locationX + 1;
                        }

                        if(locationY > bufferedImage.getHeight()) {
                            //take the overflow, and get the rightmost pixels.
                            locationY = locationY - bufferedImage.getHeight();
                            locationY = bufferedImage.getHeight() - locationY + 1;
                        }

                        Color pixelColour = new Color(bufferedImage.getRGB(locationX, locationY));

                        // then use the kernel, and calculate the red, green, blue and alpha values
                        red += pixelColour.getRed() * kernelData[kernelArrayIndex];
                        green += pixelColour.getGreen() * kernelData[kernelArrayIndex];
                        blue  += pixelColour.getBlue() * kernelData[kernelArrayIndex];
                        alpha += pixelColour.getAlpha() * kernelData[kernelArrayIndex];

                        kernelArrayIndex++;
                    }
                }
                // set color of the pixel in destination image.
                Color newColor = new Color(red, green, blue, alpha);
                dest.setRGB(i, j, newColor.getRGB());
            }
        }


        return dest;
    }

    @Override
    public Rectangle2D getBounds2D(BufferedImage bufferedImage) {
        return null;
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage bufferedImage, ColorModel colorModel) {
        return null;
    }

    @Override
    public Point2D getPoint2D(Point2D point2D, Point2D point2D1) {
        return null;
    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
