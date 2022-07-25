package com.epam.rd.java.basic.practice6.part6;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Part63 {

    private Part63() {
    }

    public static void getDuplicates(String[] input) {
        final Map<String, Integer> wordCounts = new LinkedHashMap<>();
        for (String w : input) {
            wordCounts.merge(w, 1, Integer::sum);
        }
        int i = 0;
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i == 3) {
                break;
            }
            if (wordCount.getValue() > 1) {
                i++;
                System.out.println(new StringBuilder(wordCount.getKey()).reverse().toString().toUpperCase());
            }
        }
    }
}
