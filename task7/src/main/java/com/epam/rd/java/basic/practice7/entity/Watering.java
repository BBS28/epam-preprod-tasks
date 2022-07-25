package com.epam.rd.java.basic.practice7.entity;

public class Watering {
    private int quantity;
    private String measure;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Watering = " + quantity + ' ' + measure + ' ';
    }
}
