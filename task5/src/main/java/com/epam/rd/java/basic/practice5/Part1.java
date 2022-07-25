package com.epam.rd.java.basic.practice5;

public class Part1 {
    private static final String EXCEPTION_WARNING = "InterruptedException";

    public static void main(String[] args) {
        try {
            MyThread myThread1 = new MyThread();
            myThread1.start();
            myThread1.join();
            Thread myThread2 = new Thread(new MyThread2());
            myThread2.start();
            myThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(EXCEPTION_WARNING);
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                    System.out.println(currentThread().getName());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(EXCEPTION_WARNING);
                }
            }
        }
    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                    System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(EXCEPTION_WARNING);
                }
            }
        }
    }
}
