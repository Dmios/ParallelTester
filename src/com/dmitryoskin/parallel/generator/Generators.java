package com.dmitryoskin.parallel.generator;

import com.dmitryoskin.parallel.core.GraphData;
import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.generator.osu.OsuGraphDataGenerator;
import com.dmitryoskin.parallel.generator.scalapack.ScalapackGraphDataGenerator;
import com.dmitryoskin.parallel.generator.skampi.SkampiGraphDataGenerator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.dmitryoskin.parallel.core.TestType.OSU;
import static com.dmitryoskin.parallel.core.TestType.SCALAPACK;
import static com.dmitryoskin.parallel.core.TestType.SKAMPI;
import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Oskin
 */
public class Generators {


    public static List<GraphData> generate(Map<String, String> params, Path file) {
        String path = file.toString().toLowerCase();
        List<GraphData> data = new ArrayList<>();

        if (path.contains(SCALAPACK.getName())) {
            data.add(new ScalapackGraphDataGenerator().generate(params, null));
        } else if (path.contains(OSU.getName())) {
            data.add(new OsuGraphDataGenerator().generate(params, null));
        } else if (path.contains(SKAMPI.getName())) {
            GraphDataGenerator generator = new SkampiGraphDataGenerator();

            List<String> names = Stream.of(params.get(Param.SKAMPI_TEST_NAMES).split(",")).collect(toList());
            for (String name : names) {
                data.add(generator.generate(params, name));
            }
        }

        return data;

    }
}
