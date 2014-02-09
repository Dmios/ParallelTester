package com.dmitryoskin.parallel.parser;

import com.dmitryoskin.parallel.core.Param;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.dmitryoskin.parallel.core.Param.JVM_TIME;
import static com.dmitryoskin.parallel.core.Param.PROCESS_COUNT;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public class BaseResultParser implements ResultParser {

    protected List<String> text;

    @Override
    public Map<Param, String> parse(Path input) throws IOException {
        text = Files.readAllLines(input, Charset.forName("UTF-8"));

        String configuration = text.get(CONFIGURATION_LINE);

        String[] configParams = configuration.split(" ");
        String processCount = configParams[PROCESS_COUNT_INDEX];

        String jvmTime = text.get(JVM_TIME_LINE).split(" ")[JVM_TIME_INDEX];

        Map<Param, String> result = new TreeMap<>();
        result.put(PROCESS_COUNT, processCount);
        result.put(JVM_TIME, jvmTime);

        return result;
    }
}
