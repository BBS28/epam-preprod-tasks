package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;

public class Part2 {
    private static final String REGEX_1 = "\\w+";

    public static void main(String[] args) {
        String fileName = "part2.txt";
        System.out.println(convert(Util.getInput(fileName)));
    }

    public static String convert(String input) {
        return "Min: " +
                getWordsByLength(input, getMinLength(input)) +
                "\n" +
                "Max: " +
                getWordsByLength(input, getMaxLength(input));
    }

    private static String getWordsByLength(String input, int wordsLength) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Util.getMatcher(input,REGEX_1);
        while (matcher.find()) {
            if (matcher.group().length() == wordsLength &&
                    !stringBuilder.toString().contains(matcher.group())) {
                stringBuilder.append(matcher.group()).append(", ");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static int getMinLength(String input) {
        int min = Integer.MAX_VALUE;
        Matcher matcher = Util.getMatcher(input, REGEX_1);
        while (matcher.find()) {
            if (matcher.group().length() < min) {
                min = matcher.group().length();
            }
        }
        return  min;
    }

    private static int getMaxLength(String input) {
        int max = 0;
        Matcher matcher = Util.getMatcher(input, REGEX_1);
        while (matcher.find()) {
            if (matcher.group().length() > max) {
                max = matcher.group().length();
            }
        }
        return max;
    }
}
