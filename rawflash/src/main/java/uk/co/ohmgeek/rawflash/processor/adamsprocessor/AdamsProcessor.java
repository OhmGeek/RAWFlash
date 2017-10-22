package uk.co.ohmgeek.rawflash.processor.adamsprocessor;

import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.operations.GaussianBlur;

import java.util.HashMap;

public class AdamsProcessor implements AbstractProcessor {
    // Adams Processor, named after Ansel Adams, a photographer and photo editor.

    @Override
    public void process(HashMap<String, String> operations) {
        // first, we create an instance of the image. We can then pass the image down
        // the image is then modified, and then processed again.

        //todo consider order - we might want to do things in a certain order in the future.
    }
}
