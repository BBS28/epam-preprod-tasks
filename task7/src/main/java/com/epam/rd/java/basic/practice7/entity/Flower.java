package com.epam.rd.java.basic.practice7.entity;

public class Flower {

    private String name;
    private Soil soil;
    private String origin;
    private Multiplying multiplying;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", soil=" + soil.value() +
                ", origin='" + origin + '\'' +
                ", multiplying=" + multiplying.value() +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                '}';
    }
}
