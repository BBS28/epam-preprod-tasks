package com.epam.rd.java.basic.practice1;

public class Part7 {
    private static final int LETTER_LENGTH = 26;
    private static final int LETTER_A_POSITION = 65;

    public static void main(String[] args) {
        String[] arguments = {"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
        for (int i = 0; i < arguments.length; i++) {
            System.out.println(arguments[i] + " ==> " + str2int(arguments[i]) + " ==> " + int2str(str2int(arguments[i])));
        }
    }

    public static int str2int(String number) {
        int sum = 0;
        int rank = 0;
        char letter = 'A';
        char[] charArray = new char[LETTER_LENGTH];
        for (int i = 0; i < LETTER_LENGTH; i++) {
            charArray[i] = letter;
            letter++;
        }
        for (int i = (number.length() - 1); i >= 0; i--) {
            char ch = number.charAt(i);
            for (int j = 0; j < LETTER_LENGTH; j++) {
                if (charArray[j] == ch) {
                    sum += (j + 1) * Math.pow(LETTER_LENGTH, rank);
                    rank++;
                    break;
                }
            }
        }
        return sum;
    }

    public static String int2str(int number) {
        StringBuilder tempResult = new StringBuilder();
        int tempNumber;
        while (number > 0) {
            tempNumber = (number - 1) % LETTER_LENGTH;
            tempResult.append((char) (LETTER_A_POSITION + tempNumber));
            number = (number - tempNumber) / LETTER_LENGTH;
        }
        StringBuilder result = new StringBuilder();
        for (int i = tempResult.length() - 1; i >= 0; i--) {
            result.append(tempResult.charAt(i));
        }
        return result.toString();
    }

    public static String rightColumn(String number) {
        int tempNumber = str2int(number);
        return int2str(tempNumber + 1);
    }
}
