package com.epam.rd.java.basic.practice7.entity;

public enum Soil {
    PODZOLIC("подзолистая"),
    GROUND("грунтовая"),
    SOD_PODZOLIC("дерново-подзолистая");

    private final String value;

    Soil(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Soil fromValue(String v) {
        for (Soil s: Soil.values()) {
            if (s.value.equals(v)) {
                return s;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
