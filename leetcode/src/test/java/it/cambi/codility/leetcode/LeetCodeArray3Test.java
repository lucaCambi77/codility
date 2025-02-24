package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class LeetCodeArray3Test {

  @Test
  void findAnagrams() {
    assertEquals(List.of(0, 6), findAnagrams("cbaebabacd", "abc"));
    assertEquals(List.of(0, 1, 2), findAnagrams("abab", "ab"));
  }

  List<Integer> findAnagrams(String s, String p) {

    int ns = s.length(), np = p.length();
    if (ns < np) return new ArrayList<>();

    int[] pCount = new int[26];
    int[] sCount = new int[26];
    // build reference array using string p
    for (char ch : p.toCharArray()) {
      pCount[ch - 'a']++;
    }

    List<Integer> output = new ArrayList<>();
    // sliding window on the string s
    for (int i = 0; i < ns; ++i) {
      // add one more letter
      // on the right side of the window
      sCount[s.charAt(i) - 'a']++;
      // remove one letter
      // from the left side of the window
      if (i >= np) {
        sCount[s.charAt(i - np) - 'a']--;
      }
      // compare array in the sliding window
      // with the reference array
      if (Arrays.equals(pCount, sCount)) {
        output.add(i - np + 1);
      }
    }
    return output;
  }

  @Test
  public void checkInclusion() {
    assertTrue(checkInclusion("ab", "eidbaooo"));
    assertFalse(checkInclusion1("ab", "eidboaoo"));
    assertTrue(checkInclusion("adc", "dcda"));
  }

  public boolean checkInclusion(String s1, String s2) {
    int ns = s2.length(), np = s1.length();
    if (ns < np) return false;

    int[] pCount = new int[26];
    int[] sCount = new int[26];

    for (char ch : s1.toCharArray()) {
      pCount[ch - 'a']++;
    }

    // sliding window on the string s
    for (int i = 0; i < ns; ++i) {
      // add one more letter
      // on the right side of the window
      sCount[s2.charAt(i) - 'a']++;
      // remove one letter
      // from the left side of the window
      if (i >= np) {
        sCount[s2.charAt(i - np) - 'a']--;
      }
      // compare array in the sliding window
      // with the reference array
      if (Arrays.equals(pCount, sCount)) {
        return true;
      }
    }

    return false;
  }

  public boolean checkInclusion1(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    HashMap<Character, Integer> s1map = new HashMap<>();

    for (int i = 0; i < s1.length(); i++)
      s1map.put(s1.charAt(i), s1map.getOrDefault(s1.charAt(i), 0) + 1);

    for (int i = 0; i <= s2.length() - s1.length(); i++) {
      HashMap<Character, Integer> s2map = new HashMap<>();
      for (int j = 0; j < s1.length(); j++) {
        s2map.put(s2.charAt(i + j), s2map.getOrDefault(s2.charAt(i + j), 0) + 1);
      }
      if (matches(s1map, s2map)) return true;
    }
    return false;
  }

  public boolean matches(HashMap<Character, Integer> s1map, HashMap<Character, Integer> s2map) {
    for (char key : s1map.keySet()) {
      if (s1map.get(key) - s2map.getOrDefault(key, -1) != 0) return false;
    }
    return true;
  }

  @Test
  public void topKFrequent() {
    assertArrayEquals(new int[] {1, 2}, topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2));
    assertArrayEquals(new int[] {1, 2}, topKFrequent(new int[] {1, 2}, 2));
  }

  public int[] topKFrequent(int[] nums, int k) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    List<Integer>[] freq = new ArrayList[nums.length + 1];

    for (int i = 0; i < freq.length; i++) freq[i] = new ArrayList<>();

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int key = entry.getKey();
      int frequency = entry.getValue();
      freq[frequency].add(key);
    }

    int[] ans = new int[k];
    int count = 0;
    int index = 0;

    for (int i = nums.length; i >= 0; i--) {
      if (freq[i] != null) {
        for (int j = 0; j < freq[i].size(); j++) {
          if (count == k) break;
          ans[index++] = freq[i].get(j);
          count++;
        }
      }
    }

    return ans;
  }

  @Test
  public void shortestToChar() {
    assertArrayEquals(
        new int[] {3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0}, shortestToChar("loveleetcode", 'e'));
    assertArrayEquals(new int[] {3, 2, 1, 0}, shortestToChar("aaab", 'b'));
    assertArrayEquals(new int[] {2, 1, 0, 1}, shortestToChar("aaba", 'b'));
    assertArrayEquals(new int[] {0, 1, 1, 0}, shortestToChar("baab", 'b'));
  }

  public int[] shortestToChar(String s, char c) {

    int[] sol = new int[s.length()];

    List<Integer> list = new ArrayList<>();
    int pos = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        sol[i] = 0;
        list.add(pos, i);
        pos++;
      }
    }

    pos = 0;

    int curr;
    int prev;
    for (int i = 0; i < s.length(); i++) {
      if (list.get(pos) != i) {

        curr = Math.abs(i - list.get(pos));

        if (pos - 1 >= 0) {
          prev = Math.abs(i - list.get(pos - 1));
        } else {
          prev = Math.abs(i - list.get(pos));
        }

        sol[i] = Math.min(curr, prev);
      } else {
        pos = Math.min(++pos, list.size() - 1);
      }
    }

    return sol;
  }

  @Test
  public void isPathCrossing() {
    assertTrue(isPathCrossing("NESWW"));
    assertFalse(isPathCrossing("NES"));
    assertFalse(isPathCrossing("ENNNNNNNNNNNEEEEEEEEEESSSSSSSSSS"));
    assertTrue(isPathCrossing("NESWW"));
  }

  private boolean isPathCrossing(String path) {
    Set<String> set = new HashSet<>();
    set.add("0-0");

    int x = 0, y = 0;

    for (int i = 0; i < path.length(); i++) {
      char c = path.charAt(i);

      if (c == 'N') {
        y++;
      } else if (c == 'E') {
        x++;
      } else if (c == 'S') {
        y--;
      } else {
        x--;
      }

      if (!set.add(x + "-" + y)) {
        return true;
      }
    }

    return false;
  }

  @Test
  public void findLHS() {
    assertEquals(5, findLHS(new int[] {1, 3, 2, 2, 5, 2, 3, 7}));
    assertEquals(4, findLHS(new int[] {-1, -2, -2, 5, -2, 3, 7}));
    assertEquals(2, findLHS(new int[] {1, 2, 3, 4}));
    assertEquals(0, findLHS(new int[] {1, 1, 1, 1}));
  }

  private int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();

    int max = 0;

    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (map.containsKey(entry.getKey() - 1)) {
        if (map.get(entry.getKey() - 1) + entry.getValue() > max) {
          max = map.get(entry.getKey() - 1) + entry.getValue();
        }
      }
    }

    return max;
  }

  @Test
  public void maxCount() {
    assertEquals(
        3,
        maxCount(
            92,
            2,
            new int[][] {
              {70, 1}, {37, 1}, {3, 2}, {67, 1}, {37, 2}, {87, 2}, {26, 1}, {43, 1}, {19, 1},
              {63, 1}, {67, 1}, {19, 1}, {14, 2}, {5, 1}, {27, 2}, {44, 2}, {13, 1}
            }));
    assertEquals(4, maxCount(3, 3, new int[][] {{2, 2}, {3, 3}}));
    assertEquals(
        4,
        maxCount(
            3,
            3,
            new int[][] {
              {2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3}, {3, 3}, {3, 3}, {2, 2}, {3, 3},
              {3, 3}, {3, 3}
            }));
  }

  private int maxCount(int m, int n, int[][] ops) {

    for (int[] op : ops) {
      m = Math.min(op[0], m);
      n = Math.min(op[1], n);
    }

    return n * m;
  }

  @Test
  void numberOfLines() {
    assertArrayEquals(
        new int[] {2, 4},
        numberOfLines(
            new int[] {
              4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10
            },
            "bbbcccdddaaa"));

    assertArrayEquals(
        new int[] {3, 60},
        numberOfLines(
            new int[] {
              4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10
            },
            "abcdefghijklmnopqrstuvwxyz"));
  }

  public int[] numberOfLines(int[] widths, String s) {

    int counter = 0;
    int buckets = 1;
    int width;
    for (char c : s.toCharArray()) {
      width = widths[c - 'a'];
      if (counter + width - 100 > 0) {
        buckets++;
        counter = width;
      } else {
        counter += width;
      }
    }
    return new int[] {buckets, counter};
  }
}
