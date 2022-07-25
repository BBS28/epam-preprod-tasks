package com.epam.rd.java.basic.practice6.part6;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Part6Test {
    @Test
    public void ShouldReturnMostCommonWordsAndSortInReverse() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        new Part6().console("--input", "part6.txt", "--task", "frequency" );
        final String expectedString = "whale ==> 3" +
                System.lineSeparator() +
                "cheetah ==> 4" +
                System.lineSeparator() +
                "bison ==> 3" +
                System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        System.setIn(System.in);
        System.setOut(System.out);
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void ShouldReturnLongestWordsAndSortInDescendingLettersOrder() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        new Part6().console("--input", "part6.txt", "--task", "length" );
        final String expectedString = "chimpanzee ==> 10" +
                System.lineSeparator() +
                "mongoose ==> 8" +
                System.lineSeparator() +
                "tortoise ==> 8" +
                System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        System.setIn(System.in);
        System.setOut(System.out);
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void ShouldReturnOccuredMostOftenSortAndPrintReverse() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        new Part6().console("--input", "part6.txt", "--task", "duplicates" );
        final String expectedString = "RAUGAJ" +
                System.lineSeparator() +
                "NOSIB" +
                System.lineSeparator() +
                "ELAHW" +
                System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        System.setIn(System.in);
        System.setOut(System.out);
        Assert.assertEquals(expectedString, actualString);
    }
}
