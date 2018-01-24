package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import java.awt.image.BufferedImage;
import java.awt.image.LookupTable;

public class GammaLookup extends LookupTable {

    private int[] lookup = new int[256];
    private BufferedImage img;
    protected GammaLookup(int i, int i1, double gamma, BufferedImage img) {
        super(i, i1);

        // make gamma lookup array.
        for(int i = 0; i < lookup.length; i++) {
           lookup[i] = (int) (255 * Math.pow((double) i / (double) 255, gamma));
        }
    }

    @Override
    public int[] lookupPixel(int[] ints, int[] ints1) {



        return new int[0];
    }
}
