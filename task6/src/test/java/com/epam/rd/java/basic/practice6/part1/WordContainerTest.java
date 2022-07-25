package com.epam.rd.java.basic.practice6.part1;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WordContainerTest {

    @Test
    public void shouldSortEqualDuplicateCountWordsLexicographically() {
        List<Word> words = new WordContainer();
        words.add(new Word("A"));
        words.add(new Word("A"));
        words.add(new Word("B"));
        words.add(new Word("B"));
        String expectedOutput =
                "A : 2" +
                        System.lineSeparator() +
                        "B : 2" +
                        System.lineSeparator();
        String actualOutput = words.toString();
        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldCorrectlyDoMainMethod() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        final String content =
                "asd 43 asdf asd 43\n" +
                        "434 asdf\n" +
                        "kasdf asdf stop asdf\n" +
                        "stop";
        System.setIn(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
        WordContainer.main(null);
        final String expectedString =
                "asdf : 3" +
                        System.lineSeparator() +
                        "43 : 2" +
                        System.lineSeparator() +
                        "asd : 2" +
                        System.lineSeparator() +
                        "434 : 1" +
                        System.lineSeparator() +
                        "kasdf : 1" +
                        System.lineSeparator();
        final String actualString = byteArrayOutputStream.toString();
        System.setIn(System.in);
        System.setOut(System.out);
        Assert.assertEquals(expectedString, actualString);

    }

    @Test
    public void shouldCreateNotExistWordInWordContainerAndReturnFrequencyOne() {
        List<Word> words = new WordContainer();
        words.add(new Word("A"));
        words.add(new Word("B"));
        words.add(new Word("C"));
        String expectedOutput =
                "A : 1" +
                        System.lineSeparator() +
                        "B : 1" +
                        System.lineSeparator() +
                        "C : 1" +
                        System.lineSeparator();
        String actualOutput = words.toString();
        Assert.assertEquals(expectedOutput, actualOutput);
    }
}
