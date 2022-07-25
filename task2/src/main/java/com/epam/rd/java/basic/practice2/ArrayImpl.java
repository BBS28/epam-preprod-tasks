package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private int size = 0;
    private int capacity;
    private Object[] elementData;
    private static final String ILLEGAL_INDEX = "Illegal Index";
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayImpl() {
        this.capacity = DEFAULT_CAPACITY;
        this.elementData = new Object[this.capacity];
    }

    public ArrayImpl(int initCapacity) {
        this.capacity = initCapacity;
        this.elementData = new Object[this.capacity];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Object next() {
            if (currentIndex >= size) {
                throw new NoSuchElementException(ILLEGAL_INDEX);
            }
            return get(currentIndex++);
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(currentIndex - 1);
        }
    }

    @Override
    public void add(Object element) {
        if (size == capacity) {
            capacity = capacity * 3 / 2 + 1;
            Object[] newElementData = new Object[capacity];
            for (int i = 0; i < size; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }
        elementData[size++] = element;
    }

    @Override
    public void set(int index, Object element) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException(ILLEGAL_INDEX);
        }
        elementData[index] = element;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException(ILLEGAL_INDEX);
        }
        return elementData[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException(ILLEGAL_INDEX);
        }
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(elementData[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        return result.append("]").toString();
    }

    public static void main(String[] args) {
        Array arr = new ArrayImpl();
        arr.add("A");
        arr.add("B");
        arr.add(null);
        arr.add("D");
        System.out.println(arr);
        System.out.println(arr.size());
        System.out.println(arr.indexOf("D"));
        arr.set(2, "T");
        System.out.println(arr.toString());
        arr.remove(2);
        System.out.println(arr.toString());
        Iterator iter = arr.iterator();
        while (iter.hasNext()) {
            Object nextObj = iter.next();
            if (nextObj.equals("T")) {
                iter.remove();
            }
        }
        System.out.println(arr.toString());
    }
}
