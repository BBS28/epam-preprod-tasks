package com.epam.rd.java.basic.practice1;

public class Part4 {

    public static void main(String[] args) {
        int nod = 1;
        int minArg = 0;
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);
        if (arg1 > arg2) {
            minArg = arg2;
        } else minArg = arg1;
        for (int i = 2; i <= minArg; i++) {
            if (arg1 % i == 0 && arg2 % i == 0) {
                nod = i;
            }
        }
        System.out.print(nod);
    }

}
