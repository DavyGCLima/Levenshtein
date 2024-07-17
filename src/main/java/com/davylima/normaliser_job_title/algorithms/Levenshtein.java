package com.davylima.normaliser_job_title.algorithms;

import java.util.Arrays;

/**
 * Damerau–Levenshtein implementation
 * Reference: https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#Distance_with_adjacent_transpositions
 * Implementation Reference: https://www.baeldung.com/java-levenshtein-distance
 */
public class Levenshtein {

      public static int calculateDistance(String leftinput, String rightInput) {
        final var left = leftinput.toLowerCase();
        final var right = rightInput.toLowerCase();

        int[] costs = new int[right.length() + 1];
        for (int i = 0; i <= left.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= right.length(); j++) {
                if (i == 0)  costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (left.charAt(i - 1) != right.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[right.length()] = lastValue;
        }
        return costs[right.length()];
    }
}
