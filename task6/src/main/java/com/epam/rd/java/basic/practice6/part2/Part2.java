package com.epam.rd.java.basic.practice6.part2;

import java.util.*;

public class Part2 {
    public static final int NUM_OF_MEN = 10_000;
    public static final int K = 4;
    public static final String INDEX = "Index";
    public static final String ITERATOR = "Iterator";
    private int n;
    List<Integer> arrayList = new ArrayList<>();
    List<Integer> linkedList = new LinkedList<>();

    public Part2(int n) {
        this.n = n;
        this.fillLists(this.n);
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2(NUM_OF_MEN);
        long time = removeByIndex(part2.arrayList, K);
        System.out.println(showResult(part2.arrayList, time, INDEX));
        time = removeByIndex(part2.linkedList, K);
        System.out.println(showResult(part2.linkedList, time, INDEX));
        part2 = new Part2(NUM_OF_MEN);
        time = removeByIterator(part2.arrayList, K);
        System.out.println(showResult(part2.arrayList, time, ITERATOR));
        time = removeByIterator(part2.linkedList, K);
        System.out.println(showResult(part2.linkedList, time, ITERATOR));
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long timeStart = System.currentTimeMillis();
        int index = 0;
        while (list.size() > 1) {
            index += k - 1;
            while (index >= list.size()) {
                index -= list.size();
            }
            list.remove(index);
        }
        long timeFinish = System.currentTimeMillis();
        return timeFinish - timeStart;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long timeStart = System.currentTimeMillis();
        ListIterator<Integer> iterator = list.listIterator();
        int counter = 0;
        while (list.size() > 1) {
            while (iterator.hasNext()) {
                iterator.next();
                counter++;
                if (counter == k) {
                    iterator.remove();
                    counter = 0;
                }
            }
            iterator = list.listIterator();
        }
        long timeFinish = System.currentTimeMillis();
        return timeFinish - timeStart;
    }

    public static String showResult(List<Integer> list, long time, String methodName) {
        String className = list.getClass().getSimpleName();
        return className + "#" + methodName + time + " ms";
    }

    private void fillLists(int n) {
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }
}
