package com.dmitryoskin.parallel.core;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public enum Param {

    /** Common */
    PROCESS_COUNT("process-count"),
    JVM_TIME("jvm-time"),

    /** Scalapack base test */
    WALL_CLOCK("wall-clock")

    ;

    private String name;

    Param(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
