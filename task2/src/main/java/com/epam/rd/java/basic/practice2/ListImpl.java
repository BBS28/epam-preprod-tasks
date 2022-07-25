package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    private Node head;
    private int size = 0;

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        Node previousElement = head;
        Node currentElement = head;

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Object next() {
            if (currentElement == null) {
                throw new NoSuchElementException();
            }
            previousElement = currentElement;
            currentElement = currentElement.next;
            return previousElement.data;
        }

        @Override
        public void remove() {
            ListImpl.this.remove(previousElement.data);
        }
    }

    @Override
    public void addFirst(Object element) {
        if (size == 0) {
            head = new Node(element);
        } else {
            Node tempHead = head;
            head = new Node(element);
            head.next = tempHead;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (head == null) {
            head = new Node(element);
        } else if (head.next == null) {
            head.next = new Node(element);
        } else {
            Node previousElement = head;
            Node currentElement = head.next;
            while (currentElement != null) {
                previousElement = currentElement;
                currentElement = currentElement.next;
            }
            previousElement.next = new Node(element);
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            ListImpl.this.clear();
        } else {
            head = head.next;
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            ListImpl.this.clear();
        } else {
            Node currentElement = head;
            Node previousElement = head;
            while (currentElement.next != null) {
                previousElement = currentElement;
                currentElement = currentElement.next;
            }
            previousElement.next = null;
            size--;
        }
    }

    @Override
    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    @Override
    public Object getLast() {
        if (head == null) {
            return null;
        }
        Node currentElement = head;
        while (currentElement.next != null) {
            currentElement = currentElement.next;
        }
        return currentElement.data;
    }

    @Override
    public Object search(Object element) {
        Node currentElement = head;
        if (element != null) {
            while (currentElement.next != null) {
                if (currentElement.data.equals(element)) {
                    return element;
                }
                currentElement = currentElement.next;
            }
        } else {
            while (currentElement.next != null) {
                if (currentElement.data == element) {
                    return element;
                }
                currentElement = currentElement.next;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (element == null) {
            for (Node n = head; n != null; n = n.next) {
                if (n.data == null) {
                    unlink(n);
                    return true;
                }
            }
        } else {
            for (Node n = head; n != null; n = n.next) {
                if (element.equals(n.data)) {
                    unlink(n);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node node) {
        if (head.equals(node)) {
            head = head.next;
        } else {
            Node currentElement = head.next;
            Node previousElement = head;
            while (currentElement.next != null) {
                if (currentElement.equals(node)) {
                    previousElement.next = currentElement.next;
                }
                previousElement = currentElement;
                currentElement = currentElement.next;
            }
            if (currentElement.equals(node)) {
                previousElement.next = null;
            }
        }
        size--;
    }

    @Override
    public String toString() {
        Node currentElement = head;
        if (currentElement == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        while (currentElement != null) {
            result.append(currentElement.data);
            if (currentElement.next != null) {
                result.append(", ");
            }
            currentElement = currentElement.next;
        }
        return result.append("]").toString();
    }

    public static void main(String[] args) {
        List list = new ListImpl();
        list.addLast("X");
        list.addFirst("C");
        list.addLast("Y");
        list.addFirst("B");
        list.addLast("Z");
        list.addFirst("A");
        System.out.println(list);
        System.out.println(list.size());
        list.removeFirst();
        list.removeLast();
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.search("C"));
        System.out.println(list.remove("X"));
        System.out.println(list);
        Iterator iter = list.iterator();
        Object object = "B";
        while (iter.hasNext()) {
            if (iter.next().equals(object)) {
                iter.remove();
            }
        }
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }
}
