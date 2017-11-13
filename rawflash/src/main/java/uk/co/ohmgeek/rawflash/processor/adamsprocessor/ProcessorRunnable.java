package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by ryan on 31/10/17.
 */
public abstract class ProcessorRunnable implements Runnable {
    public BufferedImage image;
    public ProcessorRunnable() {
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public abstract void run();
}
