package com.dmitryoskin.parallel.parser.scalapack;

import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.parser.BaseResultParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import static com.dmitryoskin.parallel.core.Param.SP_WALL_CLOCK;

/**
 *
 * @author Дмитрий
 */
public class ScalapackResultParser extends BaseResultParser {

    @Override
    public Map<String, String> parse(Path input) throws IOException {
        Map<String, String> result = super.parse(input);

        result.put(Param.TEST_NAME, "base");

        for (String line : text) {
            if (line.contains("wall clock time")) {
                String wallClock = line.substring(line.lastIndexOf(' ') + 1);
                result.put(SP_WALL_CLOCK, wallClock);
                break;
            }
        }

        return result;
    }
}
