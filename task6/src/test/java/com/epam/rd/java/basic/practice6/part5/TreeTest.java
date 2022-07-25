package com.epam.rd.java.basic.practice6.part5;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TreeTest {
    @Test
    public void shouldReturnTrueWhenAddedFirstElement() {
        Tree<Integer> tree = new Tree<>();
        final boolean expectedBoolean = true;
        final boolean actualBoolean = tree.add(4);
        Assert.assertEquals(expectedBoolean, actualBoolean);
    }

    @Test
    public void shouldReturnTrueWhenAddedThirdElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(4);
        tree.add(7);
        final boolean expectedBoolean = true;
        final boolean actualBoolean = tree.add(1);
        Assert.assertEquals(expectedBoolean, actualBoolean);
    }

    @Test
    public void shouldReturnFalseWhenAddedElementExists() {
        Tree<Integer> tree = new Tree<>();
        tree.add(4);
        tree.add(7);
        final boolean expectedBoolean = false;
        final boolean actualBoolean = tree.add(7);
        Assert.assertEquals(expectedBoolean, actualBoolean);
    }

    @Test
    public void shouldPrintCorrect() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        Tree<Integer> tree = new Tree<>();
        tree.add(4);
        tree.add(7);
        tree.add(2);
        tree.print();

        final String expectedString = "  2" +
                System.lineSeparator() +
                "4" +
                System.lineSeparator() +
                "  7" +
                System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        System.setIn(System.in);
        System.setOut(System.out);
        Assert.assertEquals(expectedString, actualString);

    }
}
