package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetCodeArray2Test {

  @Test
  public void minimumAbsDifference() {
    assertEquals(
        Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4)),
        minimumAbsDifference(new int[] {4, 2, 1, 3}));

    assertEquals(
        Collections.singletonList(Arrays.asList(1, 3)),
        minimumAbsDifference(new int[] {1, 3, 6, 10, 15}));

    assertEquals(
        Arrays.asList(Arrays.asList(-14, -10), Arrays.asList(19, 23), Arrays.asList(23, 27)),
        minimumAbsDifference(new int[] {3, 8, -10, 23, 19, -4, -14, 27}));

    assertEquals(
        Collections.singletonList(Arrays.asList(26, 27)),
        minimumAbsDifference(new int[] {40, 11, 26, 27, -20}));
  }

  private List<List<Integer>> minimumAbsDifference(int[] arr) {

    Arrays.sort(arr);

    int minDiff = Integer.MAX_VALUE;

    Map<Integer, List<List<Integer>>> map = new HashMap<>();

    for (int i = 1; i < arr.length; i++) {
      int diff = Math.abs(arr[i] - arr[i - 1]);

      minDiff = Math.min(diff, minDiff);

      if (diff <= minDiff) {
        List<List<Integer>> integers = map.getOrDefault(diff, new ArrayList<>());
        integers.add(Arrays.asList(arr[i - 1], arr[i]));
        map.put(diff, integers);
      }
    }

    return map.get(minDiff);
  }
}
