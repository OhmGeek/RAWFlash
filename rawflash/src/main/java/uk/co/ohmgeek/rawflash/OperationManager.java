package uk.co.ohmgeek.rawflash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileDeleteStrategy;
import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.DCRawProcessor;
import uk.co.ohmgeek.rawflash.processor.adamsprocessor.AdamsProcessor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class OperationManager {

    public OperationManager() {

    }

    private HashMap<String, String> operationMap;

    public void setInstructions(HashMap<String, String> instructions) {
        // first, convert the JSON instructions to a hashmap
        operationMap = instructions;
    }

    public BufferedImage process() {

        // eventually, load these processors at runtime, so we can automatically add modules, which in turn will
        // add themselves to the manifest.

        // for now though, we can add it fairly easily in code.


        // process the image, using the built in processors.

//        if(operationMap != null) {
//            // todo alert that the user needs to load instructions to proceed
//        }

        // first, deal with the RAW processing with DCRaw.
        AbstractProcessor rawProcessor = new DCRawProcessor();
        BufferedImage img = null;
        try {
            img = rawProcessor.process(this.operationMap, img);
        } catch (Exception e) {
            System.out.println("DCRAW PROCESSOR ERROR!");
            e.printStackTrace();
        }

        AbstractProcessor adamsProcessor = new AdamsProcessor();
        try {
            adamsProcessor.process(this.operationMap, img);
        } catch (Exception e) {
            System.out.println("ADAMS PROCESSOR ERROR");
            e.printStackTrace();
        }
        return img;

    }
}
