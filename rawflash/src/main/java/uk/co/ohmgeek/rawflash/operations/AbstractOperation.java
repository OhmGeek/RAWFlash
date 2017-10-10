package uk.co.ohmgeek.rawflash.operations;

import java.util.HashMap;

public interface AbstractOperation {
    void setParams(HashMap<String, Object> params);
    void process();
}
