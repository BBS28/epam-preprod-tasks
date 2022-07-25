package com.epam.rd.java.basic.practice1;

public class Part6 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] array = new int[n];
        int count = 0;
        int digit = 1;
        while (count < n) {
            int dividerCounter = 0;
            int divider = 1;
            do {
                if (digit % divider == 0) {
                    dividerCounter++;
                }
            }while (divider++ != digit );
            if (dividerCounter == 2) {
                array[count++] = digit;
            }
            digit++;
        }
        for (int i = 0; i<array.length; i++) {
            System.out.print(array[i]);
            if ((i < array.length-1)){
                System.out.print(" ");
            }
        }
    }

}
