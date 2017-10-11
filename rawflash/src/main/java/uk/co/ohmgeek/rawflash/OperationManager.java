package uk.co.ohmgeek.rawflash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uk.co.ohmgeek.rawflash.processor.AbstractProcessor;
import uk.co.ohmgeek.rawflash.processor.DCRawProcessor;

import java.util.HashMap;

public class OperationManager {

    public OperationManager() {

    }

    private HashMap<String, String> operationMap;

    public void loadInstructions(String instructions) {
        // first, convert the JSON instructions to a hashmap
        operationMap = new Gson().fromJson(
                instructions,
                new TypeToken<HashMap<String, String>>(){}.getType()
        );
    }

    public void process() {

        // eventually, load these processors at runtime, so we can automatically add modules, which in turn will
        // add themselves to the manifest.

        // for now though, we can add it fairly easily in code.


        // process the image, using the built in processors.

//        if(operationMap != null) {
//            // todo alert that the user needs to load instructions to proceed
//        }

        // first, deal with the RAW processing with DCRaw.
        AbstractProcessor rawProcessor = new DCRawProcessor();
        rawProcessor.process(this.operationMap);


    }
}
