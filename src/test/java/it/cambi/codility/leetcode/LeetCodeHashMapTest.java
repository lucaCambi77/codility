package it.cambi.codility.leetcode;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LeetCodeHashMapTest {

    @Test
    public void findLucky() {
        assertEquals(2, findLucky(new int[]{2, 2, 3, 4}));
        assertEquals(3, findLucky(new int[]{1, 2, 2, 3, 3, 3}));
        assertEquals(-1, findLucky(new int[]{2, 2, 2, 3, 3}));
        assertEquals(-1, findLucky(new int[]{5}));
    }

    private int findLucky(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            set.add(i);
        }

        int sol = -1;

        for (int i : set) {
            if (i == map.get(i))
                sol = Math.max(sol, i);
        }

        return sol;
    }
    
    @Test
    public void uniqueOccurrences() {
        assertEquals(true, uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        assertEquals(false, uniqueOccurrences(new int[]{1, 2}));
        assertEquals(true, uniqueOccurrences(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
    }

    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> freq = new HashMap<>();

        for (int i : arr)
            freq.put(i, freq.getOrDefault(i, 0) + 1);

        HashSet<Integer> seen = new HashSet<>();

        for (Integer integer : freq.values()) {
            if (!seen.add(integer))
                return false;
        }

        return true;
    }

    @Test
    public void subdomainVisits() {

    }

    private List<String> subdomainVisits(String[] cpdomains) {


        return null;
    }
}
