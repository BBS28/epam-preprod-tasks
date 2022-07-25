package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String ENCODING = "CP1251";
    private static final String REGEX = "\\p{L}+|\\s|\\n|\\,|\\.|\\'";
    private static final String REGEX_2 = "\\p{L}+";
    private static final String FILE_NAME = "part1.txt";

    public static void main(String[] args) {

        System.out.println(convert(readFile(FILE_NAME)));
    }

    private static String convert(String input) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = getMatcher(input, REGEX);
        while (matcher.find()) {
            if (matcher.group().matches(REGEX_2)) {
                String currentWord = matcher.group();
                if (currentWord.length() > 3) {
                    result.append(currentWord.substring(2));
                } else {
                    result.append(currentWord);
                }
            } else {
                result.append(matcher.group());
            }
        }
        return result.toString();
    }

    private static String readFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        Reader inputstream = null;
        try {
            inputstream = new InputStreamReader(new FileInputStream(file), ENCODING);
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.getLocalizedMessage();
        }
        if (inputstream != null) {
            try (BufferedReader bufferedReader = new BufferedReader(inputstream)) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
            } catch (IOException e) {
                e.getLocalizedMessage();
            }
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static Matcher getMatcher(String input, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.UNICODE_CHARACTER_CLASS);
        return pattern.matcher(input);
    }
}
