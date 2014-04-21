package com.dmitryoskin.parallel.parser.osu;

import static com.dmitryoskin.parallel.core.Util.joiner;

import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.parser.BaseResultParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public class OsuResultParser extends BaseResultParser {

    private static final int SIZE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int DATA_IN_ROW = 2;

    @Override
    public Map<String, String> parse(Path input) throws IOException {
        Map<String, String> result = super.parse(input);


        boolean testNameFound = false;
        StringJoiner sizes = joiner();
        StringJoiner values = joiner();

        for (String line : text) {
            if (line.startsWith("#") && !testNameFound) {
                result.put(Param.TEST_NAME, line.substring(2));
                testNameFound = true;
            } else {
                String[] keyValue = line.split(SPACES);

                if (keyValue.length == DATA_IN_ROW && !isMetaLine(line)) {
                    sizes.add(keyValue[SIZE_INDEX]);
                    values.add(keyValue[VALUE_INDEX]);
                }
            }

        }

        result.put(Param.OSU_PACKET_SIZE, sizes.toString());
        result.put(Param.OSU_LATENCY, values.toString());

        return result;
    }
}
