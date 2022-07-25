package com.epam.rd.java.basic.practice6.part5;

public class Tree<E extends Comparable<E>> {

    Node<E> root;

    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        if (root == null) {
            root = new Node<>(element);
            return true;
        }
        Node<E> currentNode = root;
        Node<E> previousNode = currentNode;
        while (currentNode != null) {
            int compareResult = element.compareTo(currentNode.element);
            if (compareResult > 0) {
                previousNode = currentNode;
                currentNode = currentNode.rightNode;
            } else if (compareResult < 0) {
                previousNode = currentNode;
                currentNode = currentNode.leftNode;
            } else {
                return false;
            }
        }
        Node<E> node = new Node<>(element);
        node.parentNode = previousNode;
        if (element.compareTo(previousNode.element) > 0) {
            previousNode.rightNode = node;
        } else {
            previousNode.leftNode = node;
        }
        return true;
    }

    public void add(E[] elements) {
        if (elements.length == 0) {
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    public boolean remove(E element) {
        if (element == null) {
            return false;
        }
        Node<E> currentNode = findNode(element);
        if (currentNode == null) { //element is missing
            return false;
        }
        if (currentNode.leftNode == null && currentNode.rightNode == null) { //node does not have any child
            removeIfHasNotChildren(currentNode);
            return true;
        }
        if (currentNode.leftNode == null || currentNode.rightNode == null) {  //node has 1 child
            removeIfHasOneChild(currentNode);
            return true;
        }
        removeIfHasTwoChildren(currentNode); //node has two children
        return true;
    }

    private void removeIfHasNotChildren(Node<E> node) {
        if (node == root) { //root does not have any child
            root = null;
        } else {
            Node<E> previousNode = node.parentNode;
            if (node.equals(previousNode.rightNode)) {
                previousNode.rightNode = null;
            } else {
                previousNode.leftNode = null;
            }
        }
    }

    private void removeIfHasOneChild(Node<E> currentNode) {
        if (currentNode.equals(root)) {
            root = currentNode.rightNode;
        } else {
            Node<E> previousNode = currentNode.parentNode;
            if (previousNode.rightNode.equals(currentNode)) {
                if (currentNode.rightNode != null) {
                    currentNode = currentNode.rightNode;
                } else {
                    currentNode = currentNode.leftNode;
                }
                currentNode.parentNode = previousNode;
                previousNode.rightNode = currentNode;
            } else {
                if (currentNode.rightNode != null) {
                    currentNode = currentNode.rightNode;
                } else {
                    currentNode = currentNode.leftNode;
                }
                currentNode.parentNode = previousNode;
                previousNode.leftNode = currentNode;
            }
        }
    }

    private void removeIfHasTwoChildren(Node<E> currentNode) {
        Node<E> tempNode = currentNode;
        currentNode = currentNode.rightNode;

        if (currentNode.leftNode == null) {
            tempNode.element = currentNode.element;
            if (currentNode.rightNode == null) {
                tempNode.rightNode = null;
                return;
            }
            tempNode.rightNode = currentNode.rightNode;
            tempNode.rightNode.parentNode = tempNode;
            return;
        }
        while (currentNode.leftNode != null) {
            currentNode = currentNode.leftNode;
        }
        tempNode.element = currentNode.element;
        Node<E> previousNode;
        if (currentNode.rightNode != null) {
            previousNode = currentNode.parentNode;
            previousNode.leftNode = currentNode.rightNode;
            currentNode = currentNode.rightNode;
            currentNode.parentNode = previousNode;
            return;
        }
        previousNode = currentNode.parentNode;
        previousNode.leftNode = null;
    }

    public void print() {
        print(root, "");
    }


    public void print(Node<E> node, String indentation) {
        if (node != null) {
            print(node.leftNode, indentation + "  ");
            System.out.println(indentation + node.element);
            print(node.rightNode, indentation + "  ");

        }
    }

    private Node<E> findNode(E element) {
        Node<E> currentNode = root;
        while (currentNode != null) {
            if (element.equals(currentNode.element)) {
                return currentNode;
            } else if (element.compareTo(currentNode.element) > 0) {
                currentNode = currentNode.rightNode;
            } else {
                currentNode = currentNode.leftNode;
            }
        }
        return null;
    }

    private static final class Node<E> {

        private E element;
        Node<E> parentNode = null;
        Node<E> leftNode = null;
        Node<E> rightNode = null;

        public Node(E element) {
            this.element = element;
        }
    }

}
