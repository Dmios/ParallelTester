package com.dmitryoskin.parallel.generator.skampi;

import static java.util.stream.Collectors.*;

import com.dmitryoskin.parallel.core.GraphData;
import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.generator.GraphDataGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Dmitry Oskin
 */
public class SkampiGraphDataGenerator implements GraphDataGenerator {

    @Override
    public GraphData generate(Map<String, String> params, String testName) {
        String key = String.format("[%s]", testName);

        List<Double> x =
                Stream.of(params.get(key + "count").split(","))
                        .map(Double::parseDouble)
                        .collect(toList());

        List<Double> y =
                Stream.of(params.get(key + "latency").split(","))
                        .map(Double::parseDouble)
                        .collect(toList());

        return new GraphData(testName, x, y);
    }
}
