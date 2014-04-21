package com.dmitryoskin.parallel.generator.osu;

import static java.util.stream.Collectors.*;

import com.dmitryoskin.parallel.core.GraphData;
import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.generator.GraphDataGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Dmitry Oskin
 */
public class OsuGraphDataGenerator implements GraphDataGenerator {


    @Override
    public GraphData generate(Map<String, String> params, String testName) {
        String name = params.get(Param.TEST_NAME);

        List<Double> x =
            Arrays.asList(params.get(Param.OSU_PACKET_SIZE).split(","))
                .stream()
                .map(Double::parseDouble)
                .collect(toList());
        List<Double> y =
            Arrays.asList(params.get(Param.OSU_LATENCY).split(","))
                .stream()
                .map(Double::parseDouble)
                .collect(toList());

        return new GraphData(name, x, y);
    }
}
