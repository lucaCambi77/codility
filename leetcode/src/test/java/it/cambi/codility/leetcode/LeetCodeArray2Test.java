package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeArray2Test {

  @Test
  public void checkIfPangram() {
    assertTrue(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    assertFalse(checkIfPangram("leetcode"));
  }

  private boolean checkIfPangram(String sentence) {

    boolean[] bool = new boolean[26];

    for (int i = 0; i < sentence.length(); i++) {
      bool[sentence.charAt(i) - 'a'] = true;
    }

    for (boolean b : bool) {
      if (!b) return false;
    }

    return true;
  }

  @Test
  public void removeDuplicates() {
    assertEquals("ca", removeDuplicates("abbaca"));
    assertEquals("ay", removeDuplicates("azxxzy"));
    assertEquals("ay", removeDuplicates("azxxxxzy"));
    assertEquals("azxzy", removeDuplicates("azxxxzy"));
    assertEquals("", removeDuplicates("aaaaaaaa"));
  }

  private String removeDuplicates(String s) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for (char character : s.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1)) sb.deleteCharAt(sbLength-- - 1);
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }

  @Test
  public void minStartValue() {
    assertEquals(5, minStartValue(new int[] {-3, 2, -3, 4, 2}));
    assertEquals(1, minStartValue(new int[] {1, 2}));
    assertEquals(5, minStartValue(new int[] {1, -2, -3}));
    assertEquals(4, minStartValue(new int[] {-3, 6, 2, 5, 8, 6}));
  }

  private int minStartValue(int[] nums) {

    int[] prefSum = new int[nums.length];
    int min = nums[0];

    prefSum[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      prefSum[i] = prefSum[i - 1] + nums[i];
      min = Math.min(prefSum[i], min);
    }

    return min < 0 ? (min * -1 + 1) : 1;
  }

  @Test
  public void findMissingRanges() {
    assertEquals(Collections.emptyList(), findMissingRanges(new int[] {0, 1, 2, 3}, 0, 3));

    assertEquals(
        Arrays.asList("2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99));

    assertEquals(
        Arrays.asList("0->2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] {3, 50, 75}, 0, 99));

    assertEquals(Collections.singletonList("1"), findMissingRanges(new int[] {}, 1, 1));

    assertEquals(Collections.singletonList("-3->-1"), findMissingRanges(new int[] {}, -3, -1));

    assertEquals(Collections.singletonList("-1"), findMissingRanges(new int[] {}, -1, -1));

    assertEquals(Collections.singletonList("-2"), findMissingRanges(new int[] {-1}, -2, -1));

    assertEquals(Collections.singletonList("0"), findMissingRanges(new int[] {-1}, -1, 0));

    assertEquals(Arrays.asList("0", "2->9"), findMissingRanges(new int[] {1}, 0, 9));

    assertEquals(Arrays.asList("-1->0", "2->9"), findMissingRanges(new int[] {1}, -1, 9));
  }

  private List<String> findMissingRanges(int[] nums, int lower, int upper) {

    List<String> list = new ArrayList<>();

    int prev = lower - 1;
    for (int i = 0; i <= nums.length; i++) {
      int curr = (i < nums.length) ? nums[i] : upper + 1;
      if (prev + 1 <= curr - 1) {
        list.add(formatRange(prev + 1, curr - 1));
      }
      prev = curr;
    }

    return list;
  }

  private String formatRange(int lower, int upper) {
    if (lower == upper) {
      return String.valueOf(lower);
    }
    return lower + "->" + upper;
  }

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

  @Test
  public void transformArray() {
    assertEquals(Arrays.asList(6, 3, 3, 4), transformArray(new int[] {6, 2, 3, 4}));
    assertEquals(Arrays.asList(1, 4, 4, 4, 4, 5), transformArray(new int[] {1, 6, 3, 4, 3, 5}));
    assertEquals(
        Arrays.asList(2, 2, 1, 1, 1, 2, 2, 1), transformArray(new int[] {2, 1, 2, 1, 1, 2, 2, 1}));
  }

  private List<Integer> transformArray(int[] arr) {

    if (arr.length < 3) return Arrays.stream(arr).boxed().collect(Collectors.toList());

    int[] sol = new int[arr.length];
    sol[0] = arr[0];
    sol[arr.length - 1] = arr[arr.length - 1];

    boolean hasChanged = true;

    while (hasChanged) {
      updateArray(arr, sol);

      if (Arrays.equals(arr, sol)) hasChanged = false;
      else arr = sol.clone();
    }

    return Arrays.stream(sol).boxed().collect(Collectors.toList());
  }

  public void updateArray(int[] arr, int[] sol) {
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
        sol[i] = arr[i] + 1;
      } else if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
        sol[i] = arr[i] - 1;
      } else {
        sol[i] = arr[i];
      }
    }
  }

  @Test
  public void dietPlanPerformance() {
    assertEquals(0, dietPlanPerformance(new int[] {1, 2, 3, 4, 5}, 1, 3, 3));
    assertEquals(1, dietPlanPerformance(new int[] {3, 2}, 2, 0, 1));
    assertEquals(0, dietPlanPerformance(new int[] {6, 5, 0, 0}, 2, 1, 5));
    assertEquals(3, dietPlanPerformance(new int[] {6, 13, 8, 7, 10, 1, 12, 11}, 6, 5, 37));
  }

  private int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    int points = 0;
    int tot = 0;

    for (int i = 0; i < calories.length; i++) {
      tot += calories[i];

      if (i + 1 < k) continue;
      if (i + 1 > k) tot -= calories[i - k];

      if (tot < lower) points--;
      else if (tot > upper) points++;
    }

    return points;
  }
}
