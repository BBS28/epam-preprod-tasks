package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.entity.Flower;
import com.epam.rd.java.basic.practice7.entity.Flowers;

import java.util.Collections;
import java.util.Comparator;

public class Sorter {

    private Sorter() {
    }

    public static final Comparator<Flower> SORT_BY_NAME = Comparator.comparing(Flower::getName);

    public static final Comparator<Flower> SORT_BY_SOIL = Comparator.comparing(Flower::getSoil);

    public static final Comparator<Flower> SORT_BY_MULTIPLYING = Comparator.comparing(Flower::getMultiplying);

    public static void sortFlowersByName (Flowers flowers) {
        Collections.sort(flowers.getFlowerList(), SORT_BY_NAME);
    }

    public static void sortFlowersBySoil (Flowers flowers) {
        Collections.sort(flowers.getFlowerList(), SORT_BY_SOIL);
    }

    public static void sortFlowersByMultiplying (Flowers flowers) {
        Collections.sort(flowers.getFlowerList(), SORT_BY_MULTIPLYING);
    }

}
