package com.epam.rd.java.basic.practice7.entity;

public enum Multiplying {
    LEAFS("листья"),
    STALKS("черенки"),
    SEEDS("seeds");

    private final String value;

    Multiplying(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Multiplying fromValue(String v) {
        for (Multiplying m : Multiplying.values()) {
            if (m.value.equals(v)) {
                return m;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
