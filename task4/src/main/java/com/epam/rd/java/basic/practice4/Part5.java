package com.epam.rd.java.basic.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    public static final String REGEX = "(\\S+) (\\S{2,})";
    public static final String STOP_WORD = "stop";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher;
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(STOP_WORD)) {
                break;
            } else {
                try {
                    matcher = pattern.matcher(input);
                    if (matcher.find()) {
                        System.out.println(getKeyWord(matcher.group(1), matcher.group(2)));
                    } else {
                        System.out.println("Input: key locale");
                    }
                } catch (MissingResourceException e) {
                    System.out.println("Wrong input!");
                }
            }
        }
        scanner.close();
    }

    public static String getKeyWord(String key, String locale) {
        ResourceBundle resBundle = ResourceBundle.getBundle("resources", new Locale(locale));
        return resBundle.getString(key);
    }
}
