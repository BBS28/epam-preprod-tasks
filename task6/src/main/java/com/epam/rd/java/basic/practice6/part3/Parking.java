package com.epam.rd.java.basic.practice6.part3;

public class Parking {

    private int capacity;
    int[] parkingPlaces;

    public Parking(int capacity) {
        this.capacity = capacity;
        parkingPlaces = new int[capacity];
    }

    public boolean arrive(int k) {
        if (k >= capacity) {
            throw new IllegalArgumentException();
        }
        if (parkingPlaces[k] == 0) {
            parkingPlaces[k] = 1;
            return true;
        }
        if (k != (capacity - 1)) {
            for (int i = k + 1; i < capacity; i++) {
                if (parkingPlaces[i] == 0) {
                    parkingPlaces[i] = 1;
                    return true;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            if (parkingPlaces[i] == 0) {
                parkingPlaces[i] = 1;
                return true;
            }
        }
        return false;
    }

    public boolean depart(int k) {
        if (k >= capacity) {
            throw new IllegalArgumentException();
        }
        if (parkingPlaces[k] == 1) {
            parkingPlaces[k] = 0;
            return true;
        }
        return false;
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : parkingPlaces) {
           stringBuilder.append(i);
        }
        System.out.println(stringBuilder);
    }
}
