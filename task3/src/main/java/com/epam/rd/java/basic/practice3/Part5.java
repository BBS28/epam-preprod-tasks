package com.epam.rd.java.basic.practice3;

public class Part5 {
    private static final int[] DECIMAL_VALUE = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_VALUE = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final String ARROW = "-->";

    public static void main(String[] args) {
        int decimal;
        String roman;
        for (int i = 1; i <= 100; i++) {
            roman =  decimal2Roman(i);
            decimal = roman2Decimal(roman);
            System.out.println(i + ARROW + roman + ARROW + decimal );
        }
    }

    public static String decimal2Roman(int dec) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < DECIMAL_VALUE.length; i++) {
            while (dec >= DECIMAL_VALUE[i]) {
                dec -= DECIMAL_VALUE[i];
                result.append(ROMAN_VALUE[i]);
            }
        }
        return result.toString();
    }

    public static int roman2Decimal(String roman) {
        int result = 0;
        String temp = roman;
            while (temp.length() > 0) {
                for (int i = 0; i < ROMAN_VALUE.length; i++) {
                    String cursor = ROMAN_VALUE[i];
                    if (temp.startsWith(cursor)) {
                        result += DECIMAL_VALUE[i];
                        temp = temp.substring(cursor.length());
                    }
                }
            }
        return result;
    }
}
