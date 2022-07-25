package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Part5 {
    private static final Object object = new Object();
    private static final int LINES = 10;
    private static final int LENGTH = 20;
    private static final int DELAY = 20;
    private static final String FILE_NAME = "part5.txt";
    private static RandomAccessFile raf;
    private static int position;
    private static int index;

    public static void main(final String[] args) {
        File file = createFile(FILE_NAME);
        try {
            raf = new RandomAccessFile(file, "rw");
            position = 0;
            for (int i = 0; i < LINES; i++) {
                Thread thread = new Thread(new MyThread());
                thread.start();
                thread.join();
            }
            StringBuilder stringBuilder = new StringBuilder();
            raf.seek(0);
            for (int i = 0; i < LINES; i++) {
                stringBuilder.append(raf.readLine());
                if (i != (LINES - 1)) {
                    stringBuilder.append(System.lineSeparator());
                }
            }
            System.out.println(stringBuilder);
        } catch (InterruptedException | IOException e) {
            System.err.println(e.toString());
            Thread.currentThread().interrupt();
        }
    }

    private static File createFile(String fileName) {
        try {
            Files.deleteIfExists(new File(fileName).toPath());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        return new File(fileName);
    }

    public static void writeData(String data, long delay){
        try {
            raf.seek(position);
            raf.writeBytes(data);
            position++;
            Thread.sleep(delay);
        } catch (InterruptedException | IOException e) {
            System.err.println(e.toString());
            Thread.currentThread().interrupt();
        }
    }

    public static class MyThread implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                String data = String.valueOf(index);
                try {
                    for (int i = 0; i < LENGTH; i++) {
                        writeData(data, DELAY);
                    }
                    raf.seek(position);
                    raf.writeBytes(System.lineSeparator());
                    position++;
                } catch (IOException e) {
                    System.err.println(e.toString());
                }
                index++;
            }
        }
    }

}

