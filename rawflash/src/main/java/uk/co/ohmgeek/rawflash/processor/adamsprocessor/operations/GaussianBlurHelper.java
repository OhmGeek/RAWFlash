package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GaussianBlurHelper {
    private int kernelSize;
    private double sigma;

    public GaussianBlurHelper(int kernelSize, double sigma) {
        this.kernelSize = kernelSize;
        this.sigma = sigma;
    }

    private double[][] getKernel(int kernel, double sigma) {
        double sum = 0.0;
        double[][] kernelArray = new double[kernel][kernel];
        for(int i = 0; i < kernel; i++) {
            for(int j = 0; j < kernel; j++) {
                // gaussian equation
                // Source: Mark S. Nixon and Alberto S. Aguado. Feature Extraction and Image Processing. Academic Press, 2008, p. 88.
                // Accessed 20th Oct 2017 at 5pm
                double exponent = -(i*i + j*j)/(2 * (sigma*sigma));
                double coefficient = (1 / (2 * Math.PI * sigma * sigma));
                // calculate the entire equation.
                double value = (coefficient * Math.exp(exponent)) * 10000;
                sum += value;
                kernelArray[i][j] = value;
            }
        }

        // iterate through the kernelSize again, dividing by sum.
        for(int i = 0; i < kernel; i++) {
            for (int j = 0; j < kernel; j++) {
                kernelArray[i][j] = (kernelArray[i][j] / sum);
                System.out.println(kernelArray[i][j]);
            }
        }

        return kernelArray;
    }

    public double[][][] process(BufferedImage input) {
        double[][][] smoothedInput = new double[input.getWidth()][input.getHeight()][3];
        double[][] kernel = getKernel(kernelSize, sigma);

        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                int incX = kernelSize - 1;
                incX /= 2;

                int incY = kernelSize - 1;
                incY /= 2;

                double value = 0.0;
                for (int kernelX = -incX; kernelX < incX; kernelX++) {
                    for (int kernelY = -incY; kernelY < incY; kernelY++) {
                        int locationX = i + kernelX;
                        int locationY = j + kernelY;

                        if (locationX < 0) {
                            locationX *= -1; //find abs value
                            locationX -= 1; //then subtract one, so 1 becomes 0.
                        }

                        if (locationY < 0) {
                            locationY *= -1;
                            locationY -= 1; //then subtract one, so 1 becomes 0.
                        }

                        if (locationX >= input.getWidth()) {
                            //take the overflow, and get the rightmost pixels.
                            locationX = locationX - input.getWidth();
                            locationX = input.getWidth() - locationX - 1;
                        }

                        if (locationY >= input.getHeight()) {
                            //take the overflow, and get the rightmost pixels.
                            locationY = locationY - input.getHeight();
                            locationY = input.getHeight() - locationY - 1;
                        }
                        Color pixel = new Color(input.getRGB(locationX, locationY));
                        int red = pixel.getRed();
                        int green = pixel.getGreen();
                        int blue = pixel.getBlue();
                        float[] hsl = new HSLColor(red, green, blue).getHSL();
                        value += kernel[incX+kernelX][incY+kernelY] * hsl[2];
                    }
                }
                // Now we do HSV calculations again.
                Color pixel = new Color(input.getRGB(i, j));
                float[] hsl = new HSLColor(pixel.getRed(), pixel.getGreen(), pixel.getBlue()).getHSL();
                // update v according to Gaussian
                // set color of the pixel in destination image.
                smoothedInput[i][j] = new double[3];
                smoothedInput[i][j][0] = hsl[0];
                smoothedInput[i][j][1] = hsl[1];
                smoothedInput[i][j][2] = value;
            }
        }
        return smoothedInput;
    }
}