package com.dmitryoskin.parallel.parser;

import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.parser.osu.OsuResultParser;
import com.dmitryoskin.parallel.parser.scalapack.ScalapackResultParser;
import com.dmitryoskin.parallel.parser.skampi.SkampiResultParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Map;

import static com.dmitryoskin.parallel.core.TestType.*;

/**
 * @author Dmitry Oskin
 * @version 1.0
 */
public class Parsers {

    public static Map<String, String> parse(Path input) throws IOException {
        if (!Files.exists(input, LinkOption.NOFOLLOW_LINKS)) throw new IllegalArgumentException("File not found!");

        String path = input.toString().toLowerCase();

        ResultParser parser = null;
        if (path.contains(SCALAPACK.getName())) {
            parser = new ScalapackResultParser();
        } else if (path.contains(OSU.getName())) {
            parser = new OsuResultParser();
        } else if (path.contains(SKAMPI.getName())) {
            parser = new SkampiResultParser();
        }

        if (parser == null) throw new IllegalArgumentException("Test Type not supported");

        return parser.parse(input);
    }
}
