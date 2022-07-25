package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private int size;
    private Node head;
    private Node tail;

    private static class Node {
        private Object data;
        private Node next;
        private Node previous;

        Node(Node previous, Object data, Node next) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }

    @Override
    public void clear() {
        if (head != null) {
            head = null;
            tail = null;
            this.size = 0;
        }
    }

    @Override
    public int size() {
        return QueueImpl.this.size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node n;
        private Node l;

        public IteratorImpl() {
            n = head;
        }

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            l = n;
            n = n.next;
            return l.data;
        }

        @Override
        public void remove() {
            if (l == null) {
                throw new IllegalStateException();
            } else {
                Node last = l.next;
                unlink(l);
                if (n.equals(l)) {
                    n = last;
                } else {
                    l = null;
                }
            }
        }

        private void unlink(Node node) {
            final Node nextElement = node.next;
            final Node previousElement = node.previous;
            if (previousElement == null) {
                head = nextElement;
            } else {
                previousElement.next = nextElement;
                node.previous = null;
            }
            if (nextElement == null) {
                tail = previousElement;
            } else {
                nextElement.previous = previousElement;
                node.next = null;
            }
            node.data = null;
            size--;
        }
    }

    @Override
    public void enqueue(Object element) {
        Node previousElement = tail;
        Node node = new Node(previousElement, element, null);
        tail = node;
        if (previousElement == null) {
            head = node;
        } else {
            previousElement.next = node;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (head == null) {
            return null;
        }
        Node node = head;
        Object element = node.data;
        Node nextElement = node.next;
        node.data = null;
        node.next = null;
        head = nextElement;
        if (nextElement == null) {
            tail = null;
        } else {
            nextElement.previous = null;
        }
        size--;
        return element;
    }

    @Override
    public Object top() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        Node node = head;
        StringBuilder result = new StringBuilder();
        result.append('[');
        for (int i = 0; i < size; i++) {
            result.append(node.data);
            if (i != (size - 1)) {
                result.append(", ");
            }
            node = node.next;
        }
        return result.append(']').toString();
    }

    public static void main(String[] args) {
        Queue queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue(null);
        queue.enqueue("C");
        System.out.println(queue);
        System.out.println(queue.top());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
        Iterator iter = queue.iterator();
        Object object = "C";
        while (iter.hasNext()) {
            if (object.equals(iter.next())) {
                iter.remove();
            }
        }
        System.out.println(queue);
        queue.clear();
        System.out.println(queue);
    }
}
