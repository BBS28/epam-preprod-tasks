package com.epam.rd.java.basic.practice6.part1;

import java.util.*;

public class WordContainer extends ArrayList<Word> {

    @Override
    public boolean add(Word word) {
        if (!this.contains(word)) {
            return super.add(word);
        }
        for (Word w : this) {
            if (w.equals(word)) {
                w.increaseFrequency();
            }
        }
        Collections.sort(this);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Word w : this) {
            stringBuilder.append(w.toString()).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        List<String> wordsFromLine;
        List<Word> words = new WordContainer();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (scanner.hasNext()) {
            String temp = scanner.next();
            wordsFromLine = new ArrayList<>(Arrays.asList(temp.split("\\s")));
            for (String w : wordsFromLine) {
                if (w.equalsIgnoreCase("stop")) {
                    flag = false;
                    break;
                }
                words.add(new Word(w));
            }
            if (!flag) {
                break;
            }
        }
        System.out.print(words);
    }

}
