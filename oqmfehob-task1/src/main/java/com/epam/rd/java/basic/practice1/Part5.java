package com.epam.rd.java.basic.practice1;

public class Part5 {

    public static void main(String[] args) {
        int sum = 0;
        int arg = Integer.parseInt(args[0]);
        while (arg > 0) {
            int x;
            x = arg % 10;
            sum += x;
            arg = arg / 10;
        }
        System.out.print(sum);
    }

}
