package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetCodeBackTracking {

  @Test
  void expand() {
    assertEquals(new String[] { "acdf", "acef", "bcdf", "bcef" }, expand("{a,b}c{d,e}f"));
  }

  public String[] expand(String s) {

    Pattern ptrn = Pattern.compile("\\{(.*?)\\}");

    Matcher matchPattern = ptrn.matcher(s);

    if (matchPattern.find()) {
      System.out.println(matchPattern.group(1));
    }

    return null;
  }

  @Test
  void subsets() {
    System.out.println(subsets(new int[]{1,2,3}));
  }

  List<List<Integer>> subsets(int[] nums) {

    List<List<Integer>> sol = new ArrayList<>();

    if (nums.length == 0)
      return sol;

    List<Integer> list = new ArrayList<>();
    sol.add(list);

    for (int i = 0; i < nums.length; i++) {
      subsets(i, nums, new ArrayList<>(list), sol);
      list.add(nums[i]);
      sol.add(list);
    }

    return sol;
  }

  void subsets(int start, int[] nums, List<Integer> list, List<List<Integer>> sol) {

    for (int i = start; i < nums.length; i++) {
      list.add(nums[i]);
      sol.add(list);
      subsets(i + 1, nums, new ArrayList<>(list), sol);
    }
  }

}
