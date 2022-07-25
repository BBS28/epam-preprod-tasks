package com.epam.rd.java.basic.practice7.entity;

public class Lighting {
    private LightRequiring lightRequiring;

    public Lighting(LightRequiring lightRequiring) {
        this.lightRequiring = lightRequiring;
    }

    public LightRequiring getLightRequiring() {
        return lightRequiring;
    }

    public void setLightRequiring(LightRequiring lightRequiring) {
        this.lightRequiring = lightRequiring;
    }

    @Override
    public String toString() {
        return "Lighting{" +
                "lightRequiring=" + lightRequiring.value() +
                '}';
    }
}
