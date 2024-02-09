package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LeetCodeArray3Test {

    @Test
    void findAnagrams() {
        assertEquals(List.of(0, 6), findAnagrams("cbaebabacd", "abc"));
        assertEquals(List.of(0, 1, 2), findAnagrams("abab", "ab"));
    }

    List<Integer> findAnagrams(String s, String p) {

        int ns = s.length(), np = p.length();
        if (ns < np)
            return new ArrayList<>();

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        // build reference array using string p
        for (char ch : p.toCharArray()) {
            pCount[(int) (ch - 'a')]++;
        }

        List<Integer> output = new ArrayList<>();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter
            // on the right side of the window
            sCount[(int) (s.charAt(i) - 'a')]++;
            // remove one letter
            // from the left side of the window
            if (i >= np) {
                sCount[(int) (s.charAt(i - np) - 'a')]--;
            }
            // compare array in the sliding window
            // with the reference array
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - np + 1);
            }
        }
        return output;
    }
}
