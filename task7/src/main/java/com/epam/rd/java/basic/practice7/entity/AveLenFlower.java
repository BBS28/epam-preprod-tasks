package com.epam.rd.java.basic.practice7.entity;

public class AveLenFlower {
    private int amount;
    private String measure;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int quantity) {
        this.amount = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "AveLenFlower = " + amount + ' ' + ' ';
    }
}

