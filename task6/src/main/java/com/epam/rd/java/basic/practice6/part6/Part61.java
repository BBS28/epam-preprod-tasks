package com.epam.rd.java.basic.practice6.part6;

import java.util.*;

public class Part61 {

    private Part61() {
    }

    public static void getFrequency(String[] input) {

        class CountWithPlace {
            private int count = 1;
            private final int place;

            public CountWithPlace(int place) {
                this.place = place;
            }

            public int getCount() {
                return count;
            }

            public CountWithPlace setCount(int count) {
                this.count = count;
                return this;
            }
        }

        final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
        for (int place = 0; place < input.length; place++) {
            String w = input[place];
            CountWithPlace countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, new CountWithPlace(place));
            } else {
                countWithPlace.setCount(countWithPlace.getCount() + 1);
            }
        }
        TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>((a, b) -> {
            CountWithPlace cwp = wordCounts.get(a);
            CountWithPlace countWithPlaceB = wordCounts.get(b);
            int count = countWithPlaceB.count - cwp.count;
            if (count == 0) {
                return cwp.place - countWithPlaceB.place;
            } else {
                return count;
            }
        });
        sortedWords.putAll(wordCounts);

        TreeSet<String> firstStrings = new TreeSet<>(Comparator.reverseOrder());
        int i = 0;
        for (Map.Entry<String, CountWithPlace> entry : sortedWords.entrySet()) {
            if (i == 3) {
                break;
            }
            i++;
            String s = entry.getKey();
            firstStrings.add(s);
        }
        for (String s : firstStrings) {
            System.out.println(s + " ==> " + sortedWords.get(s).getCount());
        }
    }
}
