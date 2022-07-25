package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private int size;
    private Node headElement;

    private static class Node {
        private Object data;
        private Node previous;

        Node(Node previous, Object data) {
            this.data = data;
            this.previous = previous;
        }
    }

    @Override
    public void clear() {
        if (headElement != null) {
            headElement = null;
            this.size = 0;
        }
    }

    @Override
    public int size() {
        return StackImpl.this.size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node nextNode;
        private Node lastNode;
        private Node previousNode;

        public IteratorImpl() {
            nextNode = headElement;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            if (size < 3) {
                lastNode = nextNode;
                nextNode = nextNode.previous;
            } else {
                previousNode = lastNode;
                lastNode = nextNode;
                nextNode = nextNode.previous;
            }
            return lastNode.data;
        }

        @Override
        public void remove() {
            if (lastNode == null) {
                throw new IllegalStateException();
            } else if (previousNode != null){
                previousNode.previous = lastNode.previous;
            } else {
                headElement = nextNode;
            }
            size--;
        }
    }

    @Override
    public void push(Object element) {
        if (headElement == null) {
            headElement = new Node(null, element);
        } else {
            Node previousElement = headElement;
            headElement = new Node(previousElement, element);
        }
        size++;
    }

    @Override
    public Object pop() {
        if (headElement == null) {
            return null;
        }
        Node node = headElement;
        Object element = node.data;
        headElement = node.previous;
        size--;
        return element;
    }

    @Override
    public Object top() {
        if (headElement == null) {
            return null;
        }
        return headElement.data;
    }

    @Override
    public String toString() {
        if (headElement == null) {
            return "[]";
        }
        Node node = headElement;
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = node.data;
            node = node.previous;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = (size -1); i >= 0; i--) {
            if (i != size-1) {
                sb.append(", ");
            }
            sb.append(result[i]);
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        Stack stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push(null);
        stack.push("C");
        stack.push("D");
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack);
    }
}
