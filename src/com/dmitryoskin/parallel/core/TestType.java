package com.dmitryoskin.parallel.core;

import java.nio.file.Path;
import java.util.stream.Stream;

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

    public static TestType fromPath(Path file) {
        String path = file.toString().toLowerCase();
        return Stream.of(values())
            .filter(test -> path.contains(test.getName()))
            .findFirst()
            .orElse(null);
    }
    
}
