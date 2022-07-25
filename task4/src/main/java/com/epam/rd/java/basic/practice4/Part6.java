package com.epam.rd.java.basic.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Part6 {
    private static final String REGEX_CYR = "\\p{InCyrillic}+";
    private static final String REGEX_LAT = "[A-Za-z]+";
    private static final String FILE_NAME = "part6.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        while (b) {
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "latn":
                    System.out.println(input + ": " + wordsFinder(REGEX_LAT));
                    break;
                case "cyrl":
                    System.out.println(input + ": " + wordsFinder(REGEX_CYR));
                    break;
                case "stop":
                    b = false;
                    break;
                default:
                    System.out.println(input + ": Incorrect input");
            }
        }
        scanner.close();
    }

    private static String wordsFinder(String regex) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Part3.getMatcher(Part3.getInput(FILE_NAME), regex);
        while (matcher.find()) {
            stringBuilder.append(matcher.group()).append(" ");
        }
        return stringBuilder.toString();
    }
}
