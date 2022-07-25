package com.epam.rd.java.basic.practice6.part3;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParkingTest {

    @Test
    public void shouldChange1to0WhenDepart() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        Parking parking = new Parking(4);
        parking.parkingPlaces = new int[]{1, 1, 1, 1};
        parking.depart(2);
        parking.print();
        final String expectedOutput = "1101" + System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        Assert.assertEquals(expectedOutput,actualString);
    }

    @Test
    public void shouldReturnTrueIfCarArrived() {
        Parking parking = new Parking(4);
        parking.parkingPlaces = new int[]{0,0,0,0};
        final boolean expectedBoolean = true;
        final Boolean actualBoolean = parking.arrive(2);
        Assert.assertEquals(expectedBoolean,actualBoolean);
    }

    @Test
    public void departShouldReturnFalseIfParkingPlaceIsEmpty() {
        Parking parking = new Parking(4);
        parking.parkingPlaces = new int[]{0,0,0,0};
        final boolean expectedBoolean = false;
        final Boolean actualBoolean = parking.depart(2);
        Assert.assertEquals(expectedBoolean,actualBoolean);
    }
}