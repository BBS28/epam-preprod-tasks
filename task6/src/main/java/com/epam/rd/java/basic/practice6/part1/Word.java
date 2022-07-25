package com.epam.rd.java.basic.practice6.part1;

public class Word implements Comparable<Word> {

    private String content;
    private int frequency;

    public Word(String content) {
        this.content = content;
        this.frequency = 1;
    }

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        frequency++;
    }

    @Override
    public int compareTo(Word o) {
        if (this.getFrequency() == o.getFrequency()) {
            return this.getContent().compareTo(o.getContent());
        }
        return o.getFrequency() - this.getFrequency();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        return this.getContent().equals(((Word) obj).getContent());
    }

    @Override
    public int hashCode() {
        return 31 * content.hashCode() + this.getFrequency();
    }

    @Override
    public String toString() {
        return this.getContent() + " : " +
                this.getFrequency();
    }
}
