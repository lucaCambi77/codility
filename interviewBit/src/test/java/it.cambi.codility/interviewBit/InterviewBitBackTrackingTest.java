package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitBackTrackingTest {

  @Test
  public void permute() {
    int[] input = new int[] {1, 2, 3};
    List<int[]> list = permute(input, 0, input.length - 1, new ArrayList<>());

    int[][] result = new int[list.size()][];
    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }

    assertArrayEquals(
        new int[][] {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 2, 1}, {3, 1, 2}}, result);
  }

  private List<int[]> permute(int[] A, int l, int r, List<int[]> list) {

    if (l == r) {
      list.add(A);
    } else {
      for (int i = l; i <= r; i++) {
        swapArray(A, l, i);
        permute(A.clone(), l + 1, r, list);
        swapArray(A, l, i);
      }
    }

    return list;
  }

  private void swapArray(int[] array, int i, int j) {
    int temp;
    temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private Map<Character, String> digitToCharMap =
      new HashMap<>() {
        {
          put('0', "0");
          put('1', "1");
          put('2', "abc");
          put('3', "def");
          put('4', "ghi");
          put('5', "jkl");
          put('6', "mno");
          put('7', "pqrs");
          put('8', "tuv");
          put('9', "wxyz");
        }
      };

  @Test
  public void letterCombinations() {
    String A = "23";
    List<String> res = new ArrayList<>();
    findWords(A, digitToCharMap, res, "", A.length());
    Collections.sort(res);

    assertArrayEquals(
        new String[] {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"},
            res.toArray(String[]::new));
  }

  private void findWords(
      String str, Map<Character, String> map, List<String> res, String subset, int size) {
    if (subset.length() == size) {
      res.add(subset);
      return;
    }

    int i = 0;
    char c = str.charAt(0);
    StringBuilder subsetBuilder = new StringBuilder(subset);
    while (i < map.get(c).length()) {
      String subsetTmp = subsetBuilder.toString();
      subsetBuilder.append(map.get(c).charAt(i));

      findWords(str.substring(1), map, res, subsetBuilder.toString(), size);

      subsetBuilder = new StringBuilder(subsetTmp);

      i++;
    }
  }

  @Test
  public void Mod() {
    assertEquals(2, modularExpression(2, 3, 3));
    assertEquals(19, modularExpression(-1, 1, 20));
    assertEquals(1, modularExpression(2132, 0, 12));
    assertEquals(20805472, modularExpression(71045970, 41535484, 64735492));
  }

  private int modularExpression(int A, int B, int C) {

    if (A == 0) return 0;
    if (A < 0) {
      A = (A % C + C) % C;
    }
    return (int) power(A, B, C);
  }

  private long power(int a, int b, int c) {

    if (b == 0) return 1;
    if (b == 1) return a % c;

    long res = power(a, b / 2, c) % c;
    if (b % 2 == 0) return (res * res) % c;
    return ((res * res) % c * (a % c)) % c;
  }

  private String sol = "";

  @Test
  // 1) Create a global variable which will store the maximum string or number.
  // 2) Define a recursive function that takes the string as number and value of k
  // 3) Run a nested loop, the outer loop from 0 to length of string -1 and inner loop from i+1 to
  // end of string.
  // 4) Swap the ith and jth character and check if the string is now maximum and update
  // the maximum string.
  // 5) Call the function recursively with parameters: string and k-1.
  // 6) Now again swap back the ith and jth character.
  public void maximalString() {

    maximalString("254", 1, 0);
    assertEquals("524", sol);
    sol = "";
    maximalString("254", 2, 0);
    assertEquals("542", sol);
    sol = "";
    maximalString("1714254313", 5, 0);
    assertEquals("7544331211", sol);
    sol = "";
    maximalString("271425431", 3, 0);
    assertEquals("754422131", sol);
  }

  void maximalString(String A, int B, int index) {
    if (B == 0) return;

    for (int i = index; i < A.length(); i++) {
      for (int j = i + 1; j < A.length(); j++) {
        String swap = swap(A, i, j);
        if (swap.compareTo(sol) > 0) sol = swap;
        maximalString(swap, B - 1, index + 1);
        A = swap(swap, i, j);
      }
    }
  }

  private String swap(String A, int i, int j) {
    char[] ch = A.toCharArray();
    char temp = ch[i];
    ch[i] = ch[j];
    ch[j] = temp;
    return new String(ch);
  }
}
