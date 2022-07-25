package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;

public class Part6 {

    private static final String REGEX = "\\p{L}+|\\s|\\n";
    private static final String REGEX_2 = "\\p{L}+";

    public static void main(String[] args) {
        String fileName = "part6.txt";
        System.out.println(convert(Util.getInput(fileName)));
    }

    public static String convert(String input) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = Util.getMatcher(input, REGEX);
        while (matcher.find()) {
            if (matcher.group().matches(REGEX_2)) {
                String currentWord = matcher.group();
                String regExCurrentWord = ("\\b" + currentWord + "\\b");
                Matcher matcher2 = Util.getMatcher(input, regExCurrentWord);
                int counter = 0;
                while (matcher2.find()) {
                    if (matcher2.group().equals(currentWord)) {
                        counter++;
                    }
                }
                if (counter > 1) {
                    result.append("_");
                }
                result.append(currentWord);
            } else {
                result.append(matcher.group());
            }
        }
        return result.toString();
    }
}
