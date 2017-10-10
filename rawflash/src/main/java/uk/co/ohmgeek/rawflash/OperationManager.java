package uk.co.ohmgeek.rawflash;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import uk.co.ohmgeek.rawflash.operations.AbstractOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationManager {

    private List<AbstractOperation> ops;

    public OperationManager() {
        // construct
        ops = new ArrayList<AbstractOperation>();
    }

    public void loadInstructions(String instructions) {
        // first, convert the JSON instructions to a hashmap
        HashMap<String, String> map = new Gson().fromJson(
                instructions,
                new TypeToken<HashMap<String, String>>(){}.getType()
        );

        // iterate through each property in the JSON file
        // add the appropriate operation to our server list of instructions


    }
}
