package com.epam.rd.java.basic.practice6.part2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2Test {

    @Test
    public void removeByIndex_ArrayListShouldBeFasterAtLeastFourTimesThanLinkedList() {
        final int N = 10_000;
        final int K = 4;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        long timeArray = Part2.removeByIndex(arrayList, K);
        long timeLinked = Part2.removeByIndex(linkedList, K);
        double koefficient = (double) timeLinked / (double) timeArray;

        Assert.assertTrue(koefficient > 4);
    }

    @Test
    public void shouldFillArrayListWithGivenSize() {
        final int N = 10_000;
        Part2 part2 = new Part2(N);
        final int actualSize = part2.arrayList.size();
        Assert.assertEquals(N, actualSize);
    }

    @Test
    public void shouldFillLinkedListWithGivenSize() {
        final int N = 10_000;
        Part2 part2 = new Part2(N);
        final int actualSize = part2.linkedList.size();
        Assert.assertEquals(N, actualSize);
    }

    @Test
    public void removeByIteratorLinkedListShouldBeFasterThanIndex() {
        final int N = 10_000;
        final int K = 4;
        Part2 part2 = new Part2(N);
        final long timeIndex = Part2.removeByIndex(part2.linkedList, K);
        final long timeIterator = Part2.removeByIterator(part2.linkedList, K);
        Assert.assertTrue(timeIndex > timeIterator);
    }
}