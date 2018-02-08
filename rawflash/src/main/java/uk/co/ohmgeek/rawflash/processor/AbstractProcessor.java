package uk.co.ohmgeek.rawflash.processor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public interface AbstractProcessor {
    BufferedImage process(HashMap<String, String> operations, BufferedImage inputImage) throws IOException, InterruptedException;
}
