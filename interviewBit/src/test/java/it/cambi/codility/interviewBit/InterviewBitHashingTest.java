package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Integer.max;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitHashingTest {

  @Test
  public void checkPalindrome() {
    assertEquals(0, checkPalindrome("abcde"));
    assertEquals(0, checkPalindrome("aaabbbcde"));
    assertEquals(0, checkPalindrome("rl"));
    assertEquals(1, checkPalindrome("r"));
    assertEquals(1, checkPalindrome("abbaee"));
    assertEquals(1, checkPalindrome("abbbaee"));
  }

  private int checkPalindrome(String A) {
    int[] chars = new int[26];

    for (int i = 0; i < A.length(); i++) {
      int pos = A.charAt(i) - 'a';
      chars[pos] = ++chars[pos];
    }

    int countOdd = 0;

    for (int aChar : chars) {
      if ((aChar & 1) == 1) {
        if (countOdd >= 1) return 0;
        else countOdd++;
      }
    }

    return countOdd <= 1 ? 1 : 0;
  }

  @Test
  public void longestSubarrayLength() {
    assertEquals(5, longestSubarrayLength(new int[] {0, 1, 1, 0, 0, 1}));
    assertEquals(1, longestSubarrayLength(new int[] {1, 0, 0, 1, 0}));
    assertEquals(11, longestSubarrayLength(new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}));
  }

  private int longestSubarrayLength(int[] arr) {
    int n = arr.length;
    Map<Integer, Integer> um = new HashMap<>();
    int sum = 0, maxLen = 0;

    for (int i = 0; i < n; i++) {

      // consider '0' as '-1'
      sum += arr[i] == 0 ? -1 : 1;

      // when subarray starts from index '0'
      if (sum == 1) maxLen = i + 1;
      else um.putIfAbsent(sum, i);

      // check if 'sum-1' is present in 'um' or not
      if (um.get(sum - 1) != null) {
        // update maxLength
        if (maxLen < (i - um.get(sum - 1))) maxLen = i - um.get(sum - 1);
      }
    }

    return maxLen;
  }

  @Test
  public void colorful() {
    assertEquals(1, colorful(2345));
    assertEquals(1, colorful1(2345));
    assertEquals(1, colorful1(0));
    assertEquals(0, colorful1(22));
  }

  public int colorful(int A) {
    Map<Long, Long> mp = new HashMap<>();
    String s = Integer.toString(A);
    int n = s.length();
    for (int x = 0; x < n; x++) {
      long p = 1;

      for (int y = x; y < n; y++) {
        long a = s.charAt(y) - '0';
        p = p * a;

        if (mp.get(p) != null) return 0;
        else mp.put(p, mp.getOrDefault(p, 0L) + 1L);
      }
    }

    return 1;
  }

  public int colorful1(int A) {
    Map<Long, Integer> mp = new HashMap<>();
    Long prod;
    int num;
    while (A > 0) {
      prod = 1L;
      num = A;
      while (num > 0) {
        prod = prod * (num % 10);
        num = num / 10;
        if (mp.getOrDefault(prod, 0) == 1) return 0;
        mp.put(prod, 1);
      }
      A = A / 10;
    }
    return 1;
  }

  @Test
  public void subArrayWithEqualOccurrence() {
    assertEquals(2, subArrayWithEqualOccurrence(new int[] {1, 2, 1}, 1, 2));
    assertEquals(6, subArrayWithEqualOccurrence(new int[] {1, 2, 1}, 4, 6));
    assertEquals(10, subArrayWithEqualOccurrence(new int[] {1, 2, 1, 1, 3, 4, 2, 5}, 1, 2));
  }

  private int subArrayWithEqualOccurrence(int[] a, int b, int c) {
    int sum = 0, ans = 0;
    Map<Integer, Integer> m = new HashMap<>();

    m.put(0, 1);

    for (int i : a) {
      if (i == b) sum += 1;
      else if (i == c) sum += -1;
      ans += m.getOrDefault(sum, 0);
      m.put(sum, m.getOrDefault(sum, 0) + 1);
    }
    return ans;
  }

  @Test
  public void incrementProblem() {
    assertArrayEquals(new int[] {2, 1}, incrementProblem(new int[] {1, 1}));
    assertArrayEquals(new int[] {1, 2}, incrementProblem(new int[] {1, 2}));
    assertArrayEquals(
        new int[] {4, 5, 3, 2, 3, 2, 4, 2, 1, 3},
        incrementProblem(new int[] {1, 2, 3, 2, 3, 1, 4, 2, 1, 3}));
  }

  public int[] incrementProblem(int[] a) {
    int n = a.length;
    Map<Integer, Integer> m = new HashMap<>();

    for (int i = 0; i < n; i++) {
      if (m.get(a[i]) != null) {
        int index = m.get(a[i]);
        a[index] = a[index] + 1;
        m.put(a[index], index);
      }
      m.put(a[i], i);
    }

    return a;
  }

  @Test
  public void pairGivenXor() {
    assertEquals(
        5, pairGivenXor(new int[] {17, 18, 8, 13, 15, 7, 11, 5, 4, 9, 12, 6, 10, 14, 16, 3}, 9));
  }

  public int pairGivenXor(int[] A, int B) {

    Set<Integer> seen = new HashSet<>();
    int count = 0;
    for (int i : A) {

      if (!seen.add(B ^ i)) count++;

      seen.add(i);
    }

    return count;
  }

  @Test
  public void subArrayBOddNumber() {
    assertEquals(4, subArrayBOddNumber(new int[] {4, 3, 2, 3, 4}, 2));
  }

  private int subArrayBOddNumber(int[] A, int B) {
    Map<Integer, Integer> ump = new HashMap<>();

    int n = A.length;
    int ans = 0;
    for (int i = 0; i < n; i++) {
      if ((A[i] & 1) == 1) A[i] = 1;
      else A[i] = 0;
    }

    ump.put(0, 1);
    int sum = 0;
    for (int j : A) {

      sum += j;

      if (ump.get(sum - B) != null) {
        ans += ump.get(sum - B);
      }

      ump.put(sum, ump.getOrDefault(sum, 0) + 1);
    }

    return ans;
  }

  @Test
  public void findRepeatingElement() {
    assertEquals(5, findRepeatingElement(new int[] {10, 5, 3, 4, 3, 5, 6}));
    assertEquals(-1, findRepeatingElement(new int[] {6, 10, 5, 4, 9, 120}));
  }

  private int findRepeatingElement(int[] A) {

    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> duplicates = new HashMap<>();

    for (int i = 0; i < A.length; i++) {
      if (map.containsKey(A[i])) {
        duplicates.put(map.get(A[i]), A[i]);
        continue;
      }

      map.put(A[i], i);
    }

    if (duplicates.isEmpty()) return -1;

    int result = Integer.MAX_VALUE;

    for (Map.Entry<Integer, Integer> integerIntegerEntry : duplicates.entrySet()) {
      result = Math.min(result, integerIntegerEntry.getKey());
    }

    return duplicates.get(result);
  }

  @Test
  public void twoOutOfThree() {
    assertArrayEquals(
        new int[] {2, 3}, twoOutOfThree(new int[] {1, 1, 2}, new int[] {2, 3}, new int[] {3}));
    assertArrayEquals(
        new int[] {1, 2, 3}, twoOutOfThree(new int[] {1, 2}, new int[] {1, 3}, new int[] {2, 3}));
  }

  public int[] twoOutOfThree(int[] A, int[] B, int[] C) {
    HashMap<Integer, Integer> a, b, c;
    a = new HashMap<>();
    b = new HashMap<>();
    c = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    for (int i : A) {
      if (a.containsKey(i)) {
        a.put(i, a.get(i) + 1);
      } else a.put(i, 1);
    }
    for (int i : B) {
      if (b.containsKey(i)) {
        b.put(i, b.get(i) + 1);
      } else b.put(i, 1);
    }
    for (int i : C) {
      if (c.containsKey(i)) {
        c.put(i, c.get(i) + 1);
      } else c.put(i, 1);
    }
    for (int i : a.keySet()) if (b.containsKey(i) || c.containsKey(i)) set.add(i);
    for (int i : b.keySet()) if (a.containsKey(i) || c.containsKey(i)) set.add(i);
    for (int i : c.keySet()) if (b.containsKey(i) || a.containsKey(i)) set.add(i);
    ArrayList<Integer> ans = new ArrayList<>(set);
    Collections.sort(ans);
    return ans.stream().mapToInt(i -> i).toArray();
  }

  @Test
  public void longestConsecutive() {
    assertEquals(4, longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    assertEquals(5, longestConsecutive(new int[] {-6, -4, -5, -2, -3}));
    assertEquals(
        4,
        longestConsecutive(
            new int[] {
              113, 107, 10, 56, 111, 97, 29, 65, 70, 112, 99, 55, 73, 75, 24, 90, 117, 1, 78, 16,
              108, 2, -5, 50, 48, 41, 69, 86, 26, 77, 89, 19, 25, 57, 106, 49, 44, 11, 52, 47, 109,
              21, 17, 53
            }));
  }

  public int longestConsecutive(final int[] A) {

    Set<Integer> numsElem = new HashSet<>();
    int maxSeries = 0;
    for (int num : A) {
      numsElem.add(num);
    }

    for (int num : A) {
      if (!numsElem.contains(num - 1)) {
        int count = 1;
        int currentElem = num;
        while (numsElem.contains(currentElem + 1)) {
          currentElem += 1;
          count += 1;
        }
        maxSeries = Math.max(maxSeries, count);
      }
    }
    return maxSeries;
  }
}
