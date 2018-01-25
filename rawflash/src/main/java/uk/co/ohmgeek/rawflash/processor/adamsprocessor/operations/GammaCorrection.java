package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;

public class GammaCorrection implements IOperation {
    private double gamma;
    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }
    @Override
    public BufferedImage process(BufferedImage image) {
        // goes through lookup table,
        // looks up all values based on gamma
        // then adds that to the image.
        LookupTable table = new LookupTable(image.getWidth(), image.getHeight()) {


            @Override
            public int[] lookupPixel(int[] ints, int[] ints1) {
                return new int[0];
            }
        };

        BufferedImageOp op = new LookupOp(table, null);

        return null;
    }
}
