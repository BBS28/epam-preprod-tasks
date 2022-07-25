package com.epam.rd.java.basic.practice5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Part4 {

    public static final String ENCODING = "Cp1251";
    public static final String FILE_PATH = "part4.txt";
    private static final String EXCEPTION_WARNING = "InterruptedException";

    public static void main(final String[] args) {
        int[][] digits = getArray(FILE_PATH);
        setArrayWithDigits(digits);
        long time1 = System.currentTimeMillis();
        System.out.println(getMaxInParallel(digits));
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
        time1 = System.currentTimeMillis();
        System.out.println(getMaxSingleThread(digits));
        time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }

    private static int[][] getArray(String filepath) {
        int row = 0;
        int column = 0;
        try (Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(filepath), ENCODING))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] lineArray = line.split("\\s+");
                column = lineArray.length;
                if (column > 0) {
                    row++;
                }
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            System.err.println(e);
        }
        return new int[row][column];
    }

    private static void setArrayWithDigits(int[][] digits) {
        int row = digits.length;
        int column = digits[0].length;
        try (Scanner sc = new Scanner(new InputStreamReader(new FileInputStream(FILE_PATH), ENCODING))) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (sc.hasNext()) {
                        digits[i][j] = sc.nextInt();
                    }
                }
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static int getMaxInParallel(int[][] digits) {
        Thread[] threads = new Thread[digits.length];
        int[] lineMax = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            int element = i;
            threads[i] = new Thread(() -> lineMax[element] = maxValueThread(digits[element]));
        }
        for (Thread t : threads) {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(EXCEPTION_WARNING);
            }
        }
        return maxValueThread(lineMax);
    }

    public static int getMaxSingleThread(int[][] digits) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits[0].length; j++) {
                if (digits[i][j] > max) {
                    max = digits[i][j];
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(EXCEPTION_WARNING);
                }
            }
        }
        return max;
    }

    public static int maxValueThread(int[] digits) {
        int max = Integer.MIN_VALUE;
        for (int value : digits) {
            if (value > max) {
                max = value;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(EXCEPTION_WARNING);
                }
            }
        }
        return max;
    }

}
