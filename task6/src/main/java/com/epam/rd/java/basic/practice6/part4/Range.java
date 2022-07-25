package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private int n;
    private int m;
    private boolean reversedOrder;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        n = firstBound;
        m = secBound;
        this.reversedOrder = reversedOrder;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl(reversedOrder);
    }

    private final class IteratorImpl implements Iterator<Integer> {
        int cursor;

        public IteratorImpl(boolean reversedOrder) {
            if (!reversedOrder) {
                this.cursor = n;
            } else {
                this.cursor = m;
            }
        }

        @Override
        public boolean hasNext() {
            if (!reversedOrder) {
                return cursor <= m;
            } else {
                return n <= cursor;
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!reversedOrder) {
                return cursor++;
            } else {
                return cursor--;
            }
        }
    }
}
