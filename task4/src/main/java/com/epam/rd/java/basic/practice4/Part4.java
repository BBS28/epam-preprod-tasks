package com.epam.rd.java.basic.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;


public class Part4 implements Iterable<String> {

    private static final String REGEX = "\\p{Lu}.*?[\\n\\r]*?.*?[\\n\\r]*?.*?\\.";
    private static final String FILE_NAME = "part4.txt";
    private Matcher matcher;

    public Part4(String fileName) {
        this.matcher = Part3.getMatcher(Part3.getInput(fileName), REGEX);
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4(FILE_NAME);
        for (String sentence : part4) {
            System.out.println(sentence);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new SentenceIterator(matcher);
    }

    private static class SentenceIterator implements Iterator<String> {
        private Matcher iteratorMatcher;

        SentenceIterator(Matcher matcher) {
            this.iteratorMatcher = matcher;
        }

        @Override
        public boolean hasNext() {
            return iteratorMatcher.find();
        }

        @Override
        public String next() {
            try {
                return iteratorMatcher.group().replaceAll("[\\n\\r]", " ");
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Operation \"remove\" is not supported");
        }
    }
}
