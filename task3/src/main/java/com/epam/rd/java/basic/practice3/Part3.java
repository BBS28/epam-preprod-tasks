package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;

public class Part3 {
    private static final String REGEX = "\\w+|\\W+";

    public static void main(String[] args) {
        String fileName = "part3.txt";
        String input = Util.getInput(fileName);
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Util.getMatcher(input, REGEX);
        while (matcher.find()) {
            if (matcher.group().length() > 2) {
                stringBuilder.append(convertWord(matcher.group()));
            } else {
                stringBuilder.append(matcher.group());
            }
        }
        return stringBuilder.toString();
    }

    private static boolean isUppercase(String word) {
        return word.charAt(0) == word.substring(0,1).toUpperCase().charAt(0);
    }

    private static String convertWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!isUppercase(word)) {
            return stringBuilder.append(word.substring(0, 1).toUpperCase()).
                    append(word.substring(1)).toString();
        }
        return stringBuilder.append(word.substring(0, 1).toLowerCase()).
                append(word.substring(1)).toString();
    }
}
