package uk.co.ohmgeek.rawflash;

/**
 * Created by ryan on 12/11/17.
 */
public class ProcessCommandObject  {
    private String jsonInput;

    public ProcessCommandObject(String jsonInput) {
        this.jsonInput = jsonInput;
    }

    public String getJSONInput() {
        return this.jsonInput;
    }
}
