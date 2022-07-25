package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String REGEX_STRING = "\\p{L}{2,}";
    private static final String REGEX_CHAR = "\\b\\p{L}{1}\\b";
    private static final String REGEX_INT = "(?<![\\.\\d])\\d+(?![\\.\\d])";
    private static final String REGEX_DOUBLE = "(\\d+\\.\\d*)|(\\.\\d+)";
    private static final String STOP_WORD = "stop";
    private static final String FILE_NAME = "part3.txt";
    private static final String ENCODING = "CP1251";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        while (b) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (STOP_WORD.equals(input)){
                    b = false;
                } else {
                    System.out.println(typeSwitcher(input));
                }
            } else b = false;
        }
    }

    private static String typeSwitcher(String type) {
        switch (type) {
            case "String":
                return typeFounder(REGEX_STRING);
            case "int":
                return typeFounder(REGEX_INT);
            case "double":
                return typeFounder(REGEX_DOUBLE);
            case "char":
                return typeFounder(REGEX_CHAR);
            default:
                return "Incorrect input";
        }
    }

    private static String typeFounder(String regEx) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = getMatcher(getInput(FILE_NAME), regEx);
        while (matcher.find()) {
            stringBuilder.append(matcher.group()).append(" ");
        }
        return stringBuilder.toString();
    }

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            ex.getLocalizedMessage();
        }
        return sb.toString();
    }

    public static Matcher getMatcher(String input, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CHARACTER_CLASS);
        return pattern.matcher(input);
    }
}
