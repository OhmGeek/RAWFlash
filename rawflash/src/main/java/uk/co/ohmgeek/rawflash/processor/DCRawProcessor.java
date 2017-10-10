package uk.co.ohmgeek.rawflash.processor;
import uk.co.ohmgeek.jdcraw.RAWOperation;
import uk.co.ohmgeek.jdcraw.operations.NegativeBrightnessException;
import uk.co.ohmgeek.jdcraw.operations.SetBrightnessOperation;
import uk.co.ohmgeek.rawflash.operations.ExposureOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DCRawProcessor implements AbstractProcessor {
    private List<RAWOperation> dcrawOperations;

    public DCRawProcessor() {
        dcrawOperations = new ArrayList<>();
    }

    @Override
    public void process(HashMap<String, String> operations) {
        try {
            for(String key : operations.keySet()) {
                // follow the tree, and add the appropriate operations.

                switch (key) {
                    case "exposure":
                        // get the exposure value as a double
                        String exposureValueStr = operations.get("exposure");
                        int exposureValue = Integer.valueOf(exposureValueStr);
                        dcrawOperations.add(new SetBrightnessOperation(exposureValue));
                }
            }
        } catch (Exception ex) {
            // generic error
            ex.printStackTrace();
        }

    }
}
