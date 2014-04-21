package com.dmitryoskin.parallel.parser.skampi;

import static com.dmitryoskin.parallel.core.Util.joiner;

import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.parser.BaseResultParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public class SkampiResultParser extends BaseResultParser {

    private static final String START_TEST = "# begin result ";
    private static final String END_TEST = "# end result ";

    private static final int DATA_IN_ROW = 4;
    private static final int COUNT_INDEX = 1;
    private static final int LATENCY_INDEX = 3;


    @Override
    public Map<String, String> parse(Path input) throws IOException {
        Map<String, String> result = super.parse(input);

        List<String> tests = new ArrayList<>();
        StringJoiner sizes = joiner(), values = joiner();

        for (String line : text) {
            if (line.startsWith(START_TEST)) {
                tests.add(line.substring(START_TEST.length() + 1, line.length() - 1));
            } else if (line.startsWith(END_TEST)) {
                String key = String.format("[%s]", tests.get(tests.size() - 1));

                result.put(key + "count", sizes.toString());
                result.put(key + "latency", values.toString());

                sizes = joiner();
                values = joiner();
            } else if (!isMetaLine(line)) {
                String[] data = line.split(SPACES);
                if (data.length >= DATA_IN_ROW) {
                    sizes.add(data[COUNT_INDEX]);
                    values.add(data[LATENCY_INDEX]);
                }
            }
        }

        String testNames = tests.stream().collect(Collectors.joining(","));
        result.put(Param.SKAMPI_TEST_NAMES, testNames);
        return result;
    }


}
