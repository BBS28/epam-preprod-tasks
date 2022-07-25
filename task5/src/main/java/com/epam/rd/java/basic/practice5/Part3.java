package com.epam.rd.java.basic.practice5;

public class Part3 {

    private int counter;
    private int counter2;
    private Thread[] threads;
    private int numberOfThreads;
    private int numberOfIterations;
    private static final int DELAY = 100;
    private static final String EXCEPTION_WARNING = "InterruptedException";
    private final Object mutex = new Object();

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
    }

    public static void main(final String[] args) {
        Part3 part3 = new Part3(3, 5);
        part3.compare();
        part3.compareSync();
    }

    private void resetCounters() {
        counter = counter2 = 0;
    }

    public void compare() {
        resetCounters();
        threads = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    process();
                }
            };
        }
        for (Thread thread : threads) {
            thread.start();
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

    public void compareSync() {
        resetCounters();
        threads = new Thread[numberOfThreads];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    synchronized (mutex) {
                        process();
                    }
                }
            };
        }
        for (Thread thread : threads) {
            thread.start();
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

    private void process() {
        for (int i = 0; i < numberOfIterations; i++) {
            System.out.println(counter + " == " + counter2);
            counter++;
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.err.println(EXCEPTION_WARNING);
                Thread.currentThread().interrupt();
            }
            counter2++;
        }
    }
}
