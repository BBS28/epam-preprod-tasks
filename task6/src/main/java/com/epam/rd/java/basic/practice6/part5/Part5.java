package com.epam.rd.java.basic.practice6.part5;

public class Part5 {

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(new Integer[]{1, 4, 6, 2, 5, 0});
        tree.print();
        System.out.println("~~~~~~~");
        System.out.println(tree.remove(5));
        tree.print();
    }
}
