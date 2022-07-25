package com.epam.rd.java.basic.practice5;

import java.io.ByteArrayInputStream;

public class Part2 {

    public static void main(final String[] args) {
        System.setIn(new ByteArrayInputStream(
                System.lineSeparator().getBytes()));
        Thread thread = new Thread(() -> Spam.main(null));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("InterruptedException");
            Thread.currentThread().interrupt();
        }
        System.setIn(System.in);
    }
}