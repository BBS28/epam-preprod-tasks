package com.epam.rd.java.basic.practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Flowers {
    private List<Flower> flowerList;

    public Flowers() {
        this.flowerList = new ArrayList<>();
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    @Override
    public String toString() {
        if(flowerList == null || flowerList.isEmpty()) {
            return "No flowers in the list";
        }
        StringBuilder result = new StringBuilder();
       for (Flower f:flowerList) {
           result.append(f).append(System.lineSeparator());
       }
       return result.toString();
    }
}
