package uk.co.ohmgeek.rawflash.processor;

import java.util.HashMap;

public interface AbstractProcessor {
    void process(HashMap<String, String> operations);
}
