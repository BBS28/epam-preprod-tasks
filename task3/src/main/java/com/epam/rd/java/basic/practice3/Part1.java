package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;

public class Part1 {
    private static final String REGEX_1 = "(.+);(\\w+) (\\w+);((.+)@(.+))";
    private static final String REGEX_2 = "(.+);(.+);(.+)";
    private static final int DOMAIN_GROUP = 6;

    public static void main(String[] args) {
        String fileName = "part1.txt";
        System.out.println(Util.getInput(fileName) + "\n");
        System.out.println(convert1(Util.getInput(fileName)));
        System.out.println(convert2(Util.getInput(fileName)));
        System.out.println(convert3(Util.getInput(fileName)));
        System.out.println(convert4(Util.getInput(fileName)));
    }

    public static String convert1(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher m = Util.getMatcher(input, REGEX_1);
        while (m.find()) {
            stringBuilder.append(m.group(1)).
                    append(": ").
                    append(m.group(4)).
                    append("\n");
        }
        return stringBuilder.toString();
    }

    public static String convert2(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Util.getMatcher(input, REGEX_1);
        while (matcher.find()) {
            stringBuilder.append(matcher.group(3)).
                    append(" ").
                    append(matcher.group(2)).
                    append(" (email: ").
                    append(matcher.group(4)).
                    append(")\n");
        }
        return stringBuilder.toString();
    }

    public static String convert3(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] domains = domainFounder(input);
        for (int i = 0; i < domains.length; i++) {
            stringBuilder.append(domains[i]).append(" ==> ");
            Matcher matcher = Util.getMatcher(input, REGEX_1);
            while (matcher.find()) {
                if (domains[i].compareTo(matcher.group(DOMAIN_GROUP)) == 0) {
                    stringBuilder.append(matcher.group(1)).
                            append(", ");
                }
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).
                    append("\n");
        }
        return stringBuilder.toString();
    }

    private static String[] domainFounder(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Util.getMatcher(input, REGEX_1);
        while (matcher.find()) {
            if (!stringBuilder.toString().contains(matcher.group(DOMAIN_GROUP))) {
                stringBuilder.append(matcher.group(DOMAIN_GROUP)).append(" ");
            }
        }
        return stringBuilder.toString().split(" ");
    }

    public static String convert4(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = Util.getMatcher(input, REGEX_2);
        while (matcher.find()) {
            stringBuilder.append(matcher.group(0)).append(";");
            if (matcher.group(3).contains("@")) {
                stringBuilder.append(passwordGenerator()).
                        append("\n");
            } else {
                stringBuilder.append("Password").
                        append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static long passwordGenerator() {
        SecureRandom random = new SecureRandom();
        double temp = random.nextDouble();
        return Math.round(((temp > 0.1) ? temp : (temp + 0.1)) * Math.pow(10, 4));
    }
}