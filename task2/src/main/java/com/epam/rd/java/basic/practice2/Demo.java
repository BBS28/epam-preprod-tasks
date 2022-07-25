package com.epam.rd.java.basic.practice2;

public class Demo {

    public static void main(String[] args) {
        Array array = new ArrayImpl();
        array.add("A");
        array.add("B");
        array.add("C");
        array.add("D");
        System.out.println(array);

        List list = new ListImpl();
        list.addLast("E");
        list.addLast("F");
        list.addLast("G");
        list.addLast("H");
        System.out.println(list);

        Queue queue = new QueueImpl();
        queue.enqueue("I");
        queue.enqueue("J");
        queue.enqueue("K");
        queue.enqueue("L");
        System.out.println(queue);

        Stack stack = new StackImpl();
        stack.push("M");
        stack.push("N");
        stack.push("O");
        stack.push("P");
        System.out.println(stack);
    }
}
