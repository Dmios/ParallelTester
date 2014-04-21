package com.dmitryoskin.parallel.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Данные для графика
 *
 * @author Dmitry Oskin
 * @since 21.04.2014
 */
public class GraphData {

    private String name;

    private List<Double> x;

    private List<Double> y;

    public GraphData() {}

    public GraphData(String name, List<Double> x, List<Double> y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    public GraphData merge(GraphData data) {
        if (data != null) {
            if (x == null) {
                x = new ArrayList<>();
            }
            x.addAll(data.getX());

            if (y == null) {
                y = new ArrayList<>();
            }
            y.addAll(data.getY());
        }
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }

    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

}
