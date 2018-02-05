package uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations;

import uk.co.ohmgeek.rawflash.processor.adamsprocessor.GammaLookup;

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
        LookupTable table = new GammaLookup(image.getWidth(), image.getHeight(), gamma);
        LookupOp gammaCorrectionOp = new LookupOp(table, null);
        return gammaCorrectionOp.filter(image, null);
    }
}
