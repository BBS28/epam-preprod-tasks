package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {

    private Thread[] threads;
    private String[] messages;
    private int[] delays;
    private static final String EXCEPTION_WARNING = "InterruptedException";

    public Spam(final String[] messages, final int[] delays) {
        if (messages.length == delays.length) {
            this.messages = messages;
            this.delays = delays;
            threads = new Thread[messages.length];
        } else {
            System.err.println("the number of messages does not match the number of delays");
        }
    }

    public static void main(final String[] args) {
        String[] messages = new String[]{"@@@", "bbbbbbb"};
        int[] times = new int[]{1000, 2 * 1000};
        Spam spam = new Spam(messages, times);
        spam.start();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            if ((bufferedReader.readLine()).equals("")) {
                spam.stop();
            }
        } catch (IOException e) {
            System.err.println("IOException");
        }
    }

    public void start() {
        for (int i = 0; i < messages.length; i++) {
            threads[i] = new Worker(messages[i], delays[i]);
            threads[i].start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(EXCEPTION_WARNING);
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class Worker extends Thread {

        private String message;
        private int time;

        public Worker(String message, int time) {
            this.message = message;
            this.time = time;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(message);
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
