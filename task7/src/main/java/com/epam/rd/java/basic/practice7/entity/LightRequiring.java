package com.epam.rd.java.basic.practice7.entity;

public enum LightRequiring {
    YES("yes"),
    NO("no");

    private final String value;

    LightRequiring(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LightRequiring fromValue(String v) {
        for (LightRequiring l: LightRequiring.values()) {
            if (l.value.equals(v)) {
                return l;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
