package com.epam.rd.java.basic.practice6.part1;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Part1 {

    private static final String INPUT = "asd 43 asdf asd 43\n" +
            "434 asdf\n" +
            "kasdf asdf stop asdf\n" +
            "stop";

    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream(INPUT.getBytes(StandardCharsets.UTF_8)));
        WordContainer.main(null);
    }

}
