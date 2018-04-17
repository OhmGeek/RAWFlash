package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.*;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class UnsharpFilter implements IOperation {
    private float radius;
    private float amount;
    private boolean useGaussian;
    private int threshold;
    public UnsharpFilter(float radius, float amount, int threshold) {
        this.radius = radius;
        this.amount = amount;
        this.threshold = threshold;
    }
    @Override
    public BufferedImage process(BufferedImage image) {
        GaussianBlurHelper gaussianBlurHelper = new GaussianBlurHelper(5, radius);
        double[][][] smoothedImage = gaussianBlurHelper.process(image);
        // Now go through, do calculations, and return final image (without contrast stretching)
        double minValue = Double.MAX_VALUE;
        double maxValue = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color pixel = new Color(image.getRGB(i, j));
                float[] originalHSL = new HSLColor(pixel.getRed(), pixel.getGreen(), pixel.getBlue()).getHSL();

//                    smoothedImage[i][j][0] = 1;
//                    smoothedImage[i][j][1] = 1;


                smoothedImage[i][j][0] = originalHSL[0];
                smoothedImage[i][j][1] = originalHSL[1];
                if(Math.abs(-smoothedImage[i][j][2] + originalHSL[2]) >= threshold) {
                    smoothedImage[i][j][2] = (originalHSL[2] - smoothedImage[i][j][2] * amount);
                }
                else {
                    smoothedImage[i][j][2] = originalHSL[2];
                }
                minValue = smoothedImage[i][j][2] < minValue ? smoothedImage[i][j][2] : minValue;
                maxValue = smoothedImage[i][j][2] > maxValue ? smoothedImage[i][j][2] : maxValue;
            }
        }
        System.out.println("Min " + String.valueOf(minValue));
        System.out.println("Max " + String.valueOf(maxValue));

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                // Constrast Stretch (Normalize)
                float h = (float) smoothedImage[i][j][0];
                float s = (float) smoothedImage[i][j][1];
//                float v = (float) smoothedImage[i][j][2];
//                float v = 0;
                float v = (float) (((smoothedImage[i][j][2] - minValue)/(maxValue - minValue)) * 100);
//                System.out.println(v);
                Color newRGBColor = new HSLColor(h, s, v).getRGB();
                image.setRGB(i, j, newRGBColor.getRGB());
            }
        }
        return image;
    }
}
