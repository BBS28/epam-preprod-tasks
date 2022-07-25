package com.epam.rd.java.basic.practice7.entity;

public class Tempreture {
    private int value;
    private String measure;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Tempreture = " + value + ' ' + measure + ' ';
    }
}
