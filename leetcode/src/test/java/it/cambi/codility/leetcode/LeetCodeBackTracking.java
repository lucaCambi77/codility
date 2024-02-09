package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetCodeBackTracking {

  @Test
  void expand() {
    assertEquals(new String[] {"acdf", "acef", "bcdf", "bcef"}, expand("{a,b}c{d,e}f"));
  }

  String[] expand(String s) {

    Pattern ptrn = Pattern.compile("\\{(.*?)}");

    Matcher matchPattern = ptrn.matcher(s);

    if (matchPattern.find()) {
      System.out.println(matchPattern.group(1));
    }

    return null;
  }

  @Test
  void subsets() {
    System.out.println(subsets(new int[] {1, 2, 3}));
  }

  List<List<Integer>> subsets(int[] nums) {

    List<List<Integer>> sol = new ArrayList<>();

    if (nums.length == 0) return sol;

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

  // Jump 1 or 2 steps, min cost to climb stairs
  @Test
  public void minCostClimbingStairs() {
    assertEquals(15, minCostClimbingStairs(new int[] {10, 15, 20}));
    assertEquals(6, minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    assertEquals(1, minCostClimbingStairs(new int[] {1, 2}));
    assertEquals(6, minCostClimbingStairsRecursive(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
  }

  private int minCostClimbingStairs(int[] cost) {
    int[] minimumCost = new int[cost.length + 1];

    // Start iteration from step 2, since the minimum cost of reaching
    // step 0 and step 1 is 0
    for (int i = 2; i < minimumCost.length; i++) {
      minimumCost[i] = Math.min(minimumCost[i - 1] + cost[i - 1], minimumCost[i - 2] + cost[i - 2]);
    }

    // The final element in minimumCost refers to the top floor
    return minimumCost[minimumCost.length - 1];
  }

  private HashMap<Integer, Integer> memo = new HashMap<>();

  private int minCostClimbingStairsRecursive(int[] cost) {
    return minimumCost(cost.length, cost);
  }

  private int minimumCost(int i, int[] cost) {
    // Base case, we are allowed to start at either step 0 or step 1
    if (i <= 1) {
      return 0;
    }

    // Check if we have already calculated minimumCost(i)
    if (memo.containsKey(i)) {
      return memo.get(i);
    }

    // If not, cache the result in our hash map and return it
    int downOne = cost[i - 1] + minimumCost(i - 1, cost);
    int downTwo = cost[i - 2] + minimumCost(i - 2, cost);
    memo.put(i, Math.min(downOne, downTwo));
    return memo.get(i);
  }

  @Test
  void tribonacci() {
    assertEquals(4, tribonacci(4));
    assertEquals(1389537, tribonacci(25));
  }

  int tribonacci(int n) {

    long[] dp = new long[38];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 1;

    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
    }

    return (int) dp[n];
  }

  @Test
  public void rob() {
    assertEquals(4, rob(new int[] {1, 2, 3, 1}));
    assertEquals(12, rob(new int[] {2, 7, 9, 3, 1}));
    assertEquals(4, rob(new int[] {2, 1, 1, 2}));
    assertEquals(1, rob(new int[] {1}));
    assertEquals(14, rob2(new int[] {4, 1, 2, 7, 5, 3, 1}));
    assertEquals(39, rob3(new int[] {6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3}));
  }

  private int rob(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    if (nums.length == 1) return nums[0];

    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }

    return dp[nums.length - 1];
  }

  private int rob2(int[] nums) {

    if (nums == null || nums.length == 0) return 0;

    int even = 0;
    int odd = 0;

    for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
        even += nums[i];
        even = Math.max(even, odd);
      } else {
        odd += nums[i];
        odd = Math.max(even, odd);
      }
    }

    return Math.max(even, odd);
  }

  private int rob3(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int[] mem = new int[nums.length + 1];
    Arrays.fill(mem, -1);

    mem[0] = 0;

    return helper(nums.length, mem, nums);
  }

  private int helper(int size, int[] mem, int[] nums) {
    if (size < 1) {
      return 0;
    }

    if (mem[size] != -1) {
      return mem[size];
    }

    // two cases
    int firstSelected = helper(size - 2, mem, nums) + nums[nums.length - size];
    int firstUnselected = helper(size - 1, mem, nums);

    return mem[size] = Math.max(firstSelected, firstUnselected);
  }

  // Jump 1 or 2 steps, possible ways to climb stairs
  @Test
  public void climbStairs() {
    assertEquals(2, climbStairs(2));
    assertEquals(3, climbStairs(3));
    assertEquals(5, climbStairs(4));
    assertEquals(8, climbStairs(5));
    assertEquals(1134903170, climbStairs(44));
  }

  private int climbStairs(int n) {
    int[] memo = new int[n + 1];
    return climbStairsRec(0, n, memo);
  }

  public int climbStairsRec(int i, int n, int[] memo) {
    if (i > n) {
      return 0;
    }
    if (i == n) {
      return 1;
    }
    if (memo[i] > 0) {
      return memo[i];
    }

    memo[i] = climbStairsRec(i + 1, n, memo) + climbStairsRec(i + 2, n, memo);
    return memo[i];
  }

  private HashMap<Integer, Integer> points;
  private HashMap<Integer, Integer> cache;

  @Test
  void deleteAndEarn() {
    assertEquals(6, deleteAndEarn(new int[] {3, 4, 2}));
    assertEquals(9, deleteAndEarn(new int[] {2, 2, 3, 3, 3, 4}));
    assertEquals(20, deleteAndEarn(new int[] {2, 3, 4, 5, 5, 6, 7}));
  }

  public int deleteAndEarn(int[] nums) {
    int maxNumber = 0;

    points = new HashMap<>();
    cache = new HashMap<>();
    // Precompute how many points we gain from taking an element
    for (int num : nums) {
      points.put(num, points.getOrDefault(num, 0) + num);
      maxNumber = Math.max(maxNumber, num);
    }

    return maxPoints(maxNumber);
  }

  private int maxPoints(int num) {
    // Check for base cases
    if (num == 0) {
      return 0;
    }

    if (num == 1) {
      return points.getOrDefault(1, 0);
    }

    if (cache.containsKey(num)) {
      return cache.get(num);
    }

    // Apply recurrence relation
    int gain = points.getOrDefault(num, 0);
    cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain));
    return cache.get(num);
  }
}
