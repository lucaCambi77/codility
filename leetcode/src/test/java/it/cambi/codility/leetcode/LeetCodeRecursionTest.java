package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class LeetCodeRecursionTest {

  @Test
  void totalNumbers() {

    assertEquals(2, totalNumbers(new int[] {0, 2, 2}));
    assertEquals(12, totalNumbers(new int[] {1, 2, 3, 4}));
    assertEquals(1, totalNumbers(new int[] {6, 6, 6}));
    assertEquals(0, totalNumbers(new int[] {1, 3, 5}));
  }

  public int totalNumbers(int[] digits) {

    List<List<Integer>> combinations = new ArrayList<>();
    generateCombinations(digits, 0, new ArrayList<>(), combinations);

    Set<Integer> allPermutations = new LinkedHashSet<>();
    for (List<Integer> combo : combinations) {
      generatePermutations(combo, 0, allPermutations);
    }

    return allPermutations.size();
  }

  private static void generateCombinations(
      int[] digits, int start, List<Integer> current, List<List<Integer>> result) {

    if (current.size() == 3) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = start; i < digits.length; i++) {
      current.add(digits[i]);
      generateCombinations(digits, i + 1, current, result);
      current.remove(current.size() - 1); // backtrack
    }
  }

  // Step 2: Generate all permutations of a combination
  private static void generatePermutations(List<Integer> combo, int start, Set<Integer> result) {

    if (start == combo.size()) {
      if (combo.get(0) == 0) {
        return;
      }

      int number = 0;

      for (int digit : combo) {
        number = number * 10 + digit;
      }

      if (number % 2 == 0) result.add(number);

      return;
    }

    for (int i = start; i < combo.size(); i++) {
      Collections.swap(combo, i, start);
      generatePermutations(combo, start + 1, result);
      Collections.swap(combo, i, start); // backtrack
    }
  }
}
