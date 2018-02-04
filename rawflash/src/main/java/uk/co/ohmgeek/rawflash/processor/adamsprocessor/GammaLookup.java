package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import java.awt.image.BufferedImage;
import java.awt.image.LookupTable;

public class GammaLookup extends LookupTable {

    private int[] lookup = new int[256];
    public GammaLookup(int i, int i1, double gamma) {
        super(i, i1);

        // make gamma lookup array.
        for(int j = 0; j < lookup.length; j++) {
           lookup[j] = (int) (255 * Math.pow((double) j / (double) 255, gamma));
        }
    }

    @Override
    public int[] lookupPixel(int[] ints, int[] ints1) {

        for(int index = 0; index < ints.length; index++) {
            //Gamma Correction on pixel value
            ints1[index] = lookup[ints[index]];
        }

        return ints1;
    }
}

