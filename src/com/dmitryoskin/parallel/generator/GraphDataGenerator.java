package com.dmitryoskin.parallel.generator;

import com.dmitryoskin.parallel.core.GraphData;

import java.util.Map;

/**
 * @author Dmitry Oskin
 *
 */
public interface GraphDataGenerator {

   GraphData generate(Map<String, String> params, String testName);

}
