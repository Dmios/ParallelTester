package com.dmitryoskin.parallel.core;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public interface Param {

    /** Common */
    String PROCESS_COUNT = "process-count";
    String JVM_TIME ="jvm-time";
    String TEST_NAME = "test-name";
    String SPEC_PARAM = "Spec param";
    String LINE_NAME = "line-name";

    /** Scalapack base test */
    String SP_WALL_CLOCK = "wall-clock";

    /** OSU */
    String OSU_PACKET_SIZE = "size";
    String OSU_LATENCY = "latency";


    String SKAMPI_TEST_NAMES = "test-names";

}
