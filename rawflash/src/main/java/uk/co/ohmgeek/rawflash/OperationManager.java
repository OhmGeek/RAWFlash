package uk.co.ohmgeek.rawflash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        // process the image, using the built in processors.

        // first, deal with the RAW processing with DCRaw.
    }
}
