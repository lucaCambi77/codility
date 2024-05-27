package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
