package com.dmitryoskin.parallel.parser;

import com.dmitryoskin.parallel.core.Param;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

/**
 *
 * @author Дмитрий
 */
public interface ResultParser {

    /** Common Lines */
    int CONFIGURATION_LINE = 0;
    int JVM_TIME_LINE = 1;

    /** Common parse indexed */
    int PROCESS_COUNT_INDEX = 5;
    int JVM_TIME_INDEX = 5;

    Map<Param, String> parse(Path input) throws IOException;
    
}
