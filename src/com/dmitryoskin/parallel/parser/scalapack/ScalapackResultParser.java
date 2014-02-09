package com.dmitryoskin.parallel.parser.scalapack;

import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.parser.BaseResultParser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.dmitryoskin.parallel.core.Param.WALL_CLOCK;

/**
 *
 * @author Дмитрий
 */
public class ScalapackResultParser extends BaseResultParser {

    @Override
    public Map<Param, String> parse(Path input) throws IOException {
        Map<Param, String> result = super.parse(input);

        for (String line : text) {
            if (line.contains("wall clock time")) {
                String wallClock = line.substring(line.lastIndexOf(' ') + 1);
                result.put(WALL_CLOCK, wallClock);
                break;
            }
        }

        return result;
    }
}
