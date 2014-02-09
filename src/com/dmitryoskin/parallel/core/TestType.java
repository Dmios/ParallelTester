package com.dmitryoskin.parallel.core;

/**
 *
 * @author Dmitry Oskin
 */
public enum TestType {
    
    SCALAPACK,
    
    OSU,
    
    SKAMPI;

    public String getName() {
        return name().toLowerCase();
    }
    
    
}
