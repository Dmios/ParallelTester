package com.dmitryoskin.parallel.generator.scalapack;

import com.dmitryoskin.parallel.core.GraphData;
import com.dmitryoskin.parallel.core.Param;
import com.dmitryoskin.parallel.core.TestType;
import com.dmitryoskin.parallel.generator.GraphDataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Oskin
 */
public class ScalapackGraphDataGenerator implements GraphDataGenerator {

    @Override
    public GraphData generate(Map<String, String> params, String testName) {

        String name = params.get(Param.TEST_NAME);
        String lineName = null;
        String xLabel = params.get("[" + name + "]x");
        String yLabel = params.get("[" + name + "]y");

        if ("base".equals(name)) {
            name = lineName = "Math PI evaluation";
        } else {
            lineName = name;
            name = "Scalapack ".concat(name);
        }

        List<Double> x = new ArrayList<>();
        x.add(Double.parseDouble(params.get(Param.PROCESS_COUNT)));

        List<Double> y = new ArrayList<>();
        y.add(Double.parseDouble(params.get(Param.JVM_TIME)));

        return new GraphData(name, xLabel, yLabel, x, y, TestType.SCALAPACK, lineName);
    }
}
