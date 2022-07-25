package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class Part2 {
    private static final String FILE_NAME = "part2.txt";
    private static final String FILE_2_NAME = "part2_sorted.txt";
    private static final int RANDOM_BOUND = 50;
    private static final String ENCODING = "CP1251";
    private static final int NUMBERS_QUANTITY = 10;

    public static void main(String[] args) {
        process(FILE_NAME, FILE_2_NAME, NUMBERS_QUANTITY);
    }

    public static void process(String fileName1, String fileName2, int numbersQuantity) {
        int[] numbers = generateNumbers(numbersQuantity);
        if (createFile(fileName1)) {
            fillFileWithNumbers(fileName1, numbers);
            System.out.println(printNumbers("input", numbers));
        }
        if (createFile(fileName2)) {
            int[] newNumbers = readNumbers(fileName1, numbersQuantity);
            sortNumbers(newNumbers);
            fillFileWithNumbers(fileName2, newNumbers);
            System.out.println(printNumbers("output", newNumbers));
        }
    }

    private static int[] generateNumbers(int quantity) {
        int[] numbers = new int[quantity];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(RANDOM_BOUND);
        }
        return numbers;
    }

    private static boolean createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return true;
    }

    private static void fillFileWithNumbers(String fileName, int[] numbers){
        try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (int number : numbers) {
                outputWriter.write(number + " ");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static String printNumbers(String heading, int[] numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(heading).append(" ==> ");
        for (int i = 0; i < numbers.length; i++) {
            stringBuilder.append(numbers[i]);
            if (i<numbers.length-1){
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private static int[] readNumbers(String fileName, int size) {
        int[] numbers = new int[size];
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(fileName), ENCODING))) {
            int i = 0;
            while (scanner.hasNextInt()) {
                numbers[i] = scanner.nextInt();
                i++;
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return numbers;
    }

    private static void sortNumbers(int[] numbers) {
        boolean isSorted = false;
        int buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    isSorted = false;
                    buf = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = buf;
                }
            }
        }
    }
}
