package com.epam.rd.java.basic.practice6.part6;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Part62 {

    private Part62() {
    }

    public static void getLength(String[] input) {
        class CountWithPlace {
            private final int wordLength;
            private final int place;

            public CountWithPlace(int wordLength, int place) {
                this.wordLength = wordLength;
                this.place = place;
            }

            public int getWordLength() {
                return wordLength;
            }
        }
        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            int finalPlace = place;
            wordCounts.computeIfAbsent(w, a -> new CountWithPlace(w.length(), finalPlace));
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace countWithPlaceA = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int length = countWithPlaceB.wordLength - countWithPlaceA.wordLength;
            if (length == 0) {
                return countWithPlaceA.place - countWithPlaceB.place;
            } else {
                return length;
            }
        });
        sortedWords.putAll(wordCounts);

        int i = 0;
        for (Map.Entry<String, CountWithPlace> entry : sortedWords.entrySet()) {
            if (i == 3) {
                break;
            }
            i++;
            String s = entry.getKey();
            System.out.println(s + " ==> " + sortedWords.get(s).getWordLength());
        }
    }

}
