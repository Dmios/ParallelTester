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

    private String xAxisLabel;

    private String yAxisLabel;

    private List<Double> x;

    private List<Double> y;

    private TestType type;

    private String lineName;

    public GraphData() {}

    public GraphData(String name, List<Double> x, List<Double> y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public GraphData(String name, String xAxisLabel, String yAxisLabel,
                     List<Double> x, List<Double> y, TestType type, String lineName) {
        this.name = name;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.x = x;
        this.y = y;
        this.type = type;
        this.lineName = lineName;
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

    public String getXAxisLabel() {
        return xAxisLabel;
    }

    public void setXAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    public String getYAxisLabel() {
        return yAxisLabel;
    }

    public void setYAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
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

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
}
