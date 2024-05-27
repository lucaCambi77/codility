package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LeetCodeArray2Test {

  private static final char X = 'X';
  private static final char O = 'O';

  @Test
  void threeSum() {
    assertEquals(
        List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    assertEquals(List.of(List.of(0, 0, 0)), threeSum(new int[] {0, 0, 0}));
    assertEquals(
        List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)), threeSum(new int[] {-2, 0, 1, 1, 2}));
    assertEquals(List.of(), threeSum(new int[] {1, 2, -2, -1}));
  }

  List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
      if (i == 0 || nums[i - 1] != nums[i]) {
        twoSumII(nums, i, res);
      }
    return res;
  }

  void twoSumII(int[] nums, int i, List<List<Integer>> res) {
    int lo = i + 1, hi = nums.length - 1;
    while (lo < hi) {
      int sum = nums[i] + nums[lo] + nums[hi];
      if (sum < 0) {
        ++lo;
      } else if (sum > 0) {
        --hi;
      } else {
        res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
        while (lo < hi && nums[lo] == nums[lo - 1]) ++lo;
      }
    }
  }

  @Test
  public void findMin() {
    assertEquals(1, findMin(new int[] {3, 4, 5, 1, 2}));
    assertEquals(0, findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
  }

  public int findMin(int[] nums) {
    int res = nums[0];
    int left = 0, right = nums.length - 1;

    while (left <= right) {
      if (nums[left] < nums[right]) {
        res = Math.min(res, nums[left]);
        break;
      }
      int mid = (left + right) / 2;
      res = Math.min(res, nums[mid]);
      if (nums[mid] >= nums[left]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return res;
  }

  @Test
  public void searchMatrix() {
    assertTrue(searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
    assertFalse(searchMatrix(new int[][] {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
  }

  public boolean searchMatrix(int[][] matrix, int target) {

    for (int[] ints : matrix) {
      if (target >= ints[0] && target <= ints[ints.length - 1]) {
        return binarySearch(ints, 0, ints.length, target) >= 0;
      }
    }
    return false;
  }

  @Test
  public void search() {
    assertEquals(4, search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
    assertEquals(1, search(new int[] {3, 1}, 1));
    assertEquals(0, search(new int[] {1}, 1));
    assertEquals(0, search(new int[] {1, 3}, 1));
  }

  public int search(int[] nums, int target) {
    int i = 0;

    while (i < nums.length - 1 && nums[i] <= nums[i + 1]) {
      if (target == nums[i]) return i;
      i++;
    }

    if (target == nums[i]) return i;

    return binarySearch(nums, i + 1, nums.length, target);
  }

  public int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = a[mid];

      if (midVal < key) low = mid + 1;
      else if (midVal > key) high = mid - 1;
      else return mid; // key found
    }

    return -1; // key not found.
  }

  @Test
  void leftRigthDifference() {

    assertArrayEquals(new int[] {15, 1, 11, 22}, leftRigthDifference(new int[] {10, 4, 8, 3}));
  }

  int[] leftRigthDifference(int[] nums) {
    int leftSum = 0;
    int rightSum = 0;

    for (int num : nums) {
      rightSum = rightSum + num;
    }

    int[] sol = new int[nums.length];
    int i = 0;
    while (i < nums.length) {
      rightSum = rightSum - nums[i];
      sol[i] = Math.abs(leftSum - rightSum);
      leftSum = leftSum + nums[i];
      i++;
    }

    return sol;
  }

  @Test
  void minimumOperations() {
    assertEquals(3, minimumOperations(new int[] {1, 5, 0, 3, 5}));
    assertEquals(4, minimumOperations(new int[] {1, 5, 0, 3, 6}));
    assertEquals(1, minimumOperations(new int[] {5, 5, 0, 5, 5}));
    assertEquals(0, minimumOperations(new int[] {0}));
  }

  int minimumOperations(int[] nums) {
    Arrays.sort(nums);
    int numOp = 0;
    for (int i = 0; i < nums.length; i++) {

      if (nums[i] == 0) continue;

      numOp++;
      for (int j = i + 1; j < nums.length; j++) {

        nums[j] = nums[j] - nums[i];
      }
    }

    return numOp;
  }

  @Test
  void validWordSquare() {
    assertTrue(validWordSquare(List.of("abcd", "bnrt", "crmy", "dtye")));
    assertTrue(validWordSquare(List.of("abcd", "bnrt", "crm", "dt")));
    assertFalse(validWordSquare(List.of("ball", "area", "read", "lady")));
    assertFalse(validWordSquare(List.of("ball", "asee", "let", "lep")));
    assertFalse(validWordSquare(List.of("ball", "asee", "lett", "le")));
    assertFalse(validWordSquare(List.of("abc", "b")));
    assertFalse(validWordSquare(List.of("a", "abc")));
    assertFalse(validWordSquare(List.of("abc", "bde", "cefg", "g")));
    assertFalse(validWordSquare(List.of("abcd", "bef", "cfga", "d")));
  }

  private boolean validWordSquare(List<String> words) {

    int y = 0;
    int x;

    for (String word : words) {

      for (x = 0; x < word.length(); x++) {
        if (x == words.size() || y >= words.get(x).length()) return false; // x or y outbound

        if (word.charAt(x) != words.get(x).charAt(y)) return false;
      }

      if (x < words.size() && words.get(x).length() > y)
        return false; // y has greater length than x

      y++;
    }

    return true;
  }

  @Test
  void minProductSum() {
    assertEquals(40, minProductSum(new int[] {5, 3, 4, 2}, new int[] {4, 2, 2, 5}));
    assertEquals(65, minProductSum(new int[] {2, 1, 4, 5, 7}, new int[] {3, 2, 4, 8, 6}));
  }

  private int minProductSum(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    nums2 =
        IntStream.of(nums2).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

    int sum = 0;

    for (int i = 0; i < nums1.length; i++) {
      sum += nums1[i] * nums2[i];
    }

    return sum;
  }

  @Test
  void findKDistantIndices() {
    assertEquals(
        List.of(1, 2, 3, 4, 5, 6), findKDistantIndices(new int[] {3, 4, 9, 1, 3, 9, 5}, 9, 1));
    assertEquals(List.of(0, 1, 2, 3, 4), findKDistantIndices(new int[] {2, 2, 2, 2, 2}, 2, 2));
  }

  List<Integer> findKDistantIndices(int[] nums, int key, int k) {

    List<Integer> list = new ArrayList<>();
    LinkedHashSet<Integer> sol = new LinkedHashSet<>();

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] - key == 0) list.add(i);
    }

    for (int i = 0; i < nums.length; i++) {
      for (Integer integer : list) {
        if (Math.abs(i - integer) <= k) sol.add(i);
      }
    }

    return new ArrayList<>(sol);
  }

  @Test
  void timeRequiredToBuy() {

    assertEquals(6, timeRequiredToBuy(new int[] {2, 3, 2}, 2));
    assertEquals(154, timeRequiredToBuy(new int[] {84, 49, 5, 24, 70, 77, 87, 8}, 3));
    assertEquals(
        1457,
        timeRequiredToBuy(
            new int[] {
              15, 66, 3, 47, 71, 27, 54, 43, 97, 34, 94, 33, 54, 26, 15, 52, 20, 71, 88, 42, 50, 6,
              66, 88, 36, 99, 27, 82, 7, 72
            },
            18));
    assertEquals(
        8,
        timeRequiredToBuy(
            new int[] {
              5, 1, 1, 1,
            },
            0));
    assertEquals(
        6,
        timeRequiredToBuy(
            new int[] {
              5, 2, 3, 1,
            },
            1));
  }

  private int timeRequiredToBuy(int[] tickets, int k) {

    int steps = 0;

    int personKTickets = tickets[k];

    for (int i = 0; i < tickets.length; i++) {

      if (i == k) continue;

      steps +=
          i > k && tickets[i] >= personKTickets
              ? personKTickets - 1
              : Math.min(tickets[i], personKTickets);
    }

    return steps + personKTickets;
  }

  @Test
  void majorityElement() {
    assertTrue(majorityElement(new int[] {3, 2, 3}).contains(3));
    assertTrue(majorityElement(new int[] {1}).contains(1));
    assertTrue(majorityElement(new int[] {1, 2}).containsAll(List.of(1, 2)));

    assertTrue(majorityElementBoyerMoore(new int[] {3, 2, 3}).contains(3));
    assertTrue(majorityElementBoyerMoore(new int[] {1}).contains(1));
    assertTrue(majorityElementBoyerMoore(new int[] {1, 2}).containsAll(List.of(1, 2)));
  }

  private List<Integer> majorityElement(int[] nums) {

    List<Integer> sol = new ArrayList<>();

    Map<Integer, Integer> map = new HashMap<>();

    for (int value : nums) {
      map.put(value, map.getOrDefault(value, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> value : map.entrySet()) {
      if (value.getValue() > nums.length / 3) sol.add(value.getKey());
    }

    return sol;
  }

  List<Integer> majorityElementBoyerMoore(int[] nums) {

    // 1st pass
    int count1 = 0;
    int count2 = 0;

    Integer candidate1 = null;
    Integer candidate2 = null;

    for (int n : nums) {
      if (candidate1 != null && candidate1 == n) {
        count1++;
      } else if (candidate2 != null && candidate2 == n) {
        count2++;
      } else if (count1 == 0) {
        candidate1 = n;
        count1++;
      } else if (count2 == 0) {
        candidate2 = n;
        count2++;
      } else {
        count1--;
        count2--;
      }
    }

    // 2nd pass
    List<Integer> result = new ArrayList<>();

    count1 = 0;
    count2 = 0;

    for (int n : nums) {
      if (candidate1 != null && n == candidate1) count1++;
      if (candidate2 != null && n == candidate2) count2++;
    }

    int n = nums.length;
    if (count1 > n / 3) result.add(candidate1);
    if (count2 > n / 3) result.add(candidate2);

    return result;
  }

  @Test
  void isMajorityElement() {
    assertTrue(isMajorityElement(new int[] {2, 4, 5, 5, 5, 5, 5, 6, 6}, 5));
    assertFalse(isMajorityElement(new int[] {10, 100, 101, 101}, 101));
    assertFalse(isMajorityElement(new int[] {10, 100, 101, 101}, 101));
    assertFalse(isMajorityElement(new int[] {438885258}, 786460391));
  }

  private boolean isMajorityElement(int[] nums, int target) {

    int start = -10;
    int end = -11;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] - target == 0) {
        start = i;
        break;
      }
    }

    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] - target == 0) {
        end = i;
        break;
      }
    }

    return end - start + 1 > (nums.length / 2);
  }

  @Test
  void minMovesToSeat() {
    assertEquals(4, minMovesToSeat(new int[] {3, 1, 5}, new int[] {2, 7, 4}));
    assertEquals(7, minMovesToSeat(new int[] {4, 1, 5, 9}, new int[] {1, 3, 2, 6}));
    assertEquals(4, minMovesToSeat(new int[] {2, 2, 6, 6}, new int[] {1, 3, 2, 6}));

    assertEquals(0, minMovesToSeat(new int[] {1, 2, 3}, new int[] {1, 2, 3}));
    assertEquals(2, minMovesToSeat(new int[] {1, 3, 4}, new int[] {1, 2, 3}));
    assertEquals(2, minMovesToSeat(new int[] {1, 1, 4}, new int[] {1, 2, 3}));

    assertEquals(6, minMovesToSeat(new int[] {2, 4, 4}, new int[] {5, 5, 6}));
    assertEquals(9, minMovesToSeat(new int[] {2, 2, 4, 4}, new int[] {5, 5, 5, 6}));
    assertEquals(8, minMovesToSeat(new int[] {2}, new int[] {10}));
    assertEquals(19, minMovesToSeat(new int[] {12, 14, 19, 19, 12}, new int[] {19, 2, 17, 20, 7}));
  }

  private int minMovesToSeat(int[] seats, int[] students) {

    Arrays.sort(seats);
    Arrays.sort(students);

    int moves = 0;

    for (int i = 0; i < students.length; i++) {
      moves += Math.abs(seats[i] - students[i]);
    }

    return moves;
  }

  @Test
  void arraySign() {
    assertEquals(1, arraySign(new int[] {-1, -2, -3, -4, 3, 2, 1}));
    assertEquals(0, arraySign(new int[] {1, 5, 0, 2, -3}));
    assertEquals(-1, arraySign(new int[] {-1, 1, -1, 1, -1}));
  }

  private int arraySign(int[] nums) {

    boolean countNeg = true;

    for (int num : nums) {
      if (num == 0) return 0;

      if (num < 0) countNeg = !countNeg;
    }

    return countNeg ? 1 : -1;
  }

  @Test
  void searchRange() {
    assertArrayEquals(new int[] {3, 4}, searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8));
    assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6));
    assertArrayEquals(new int[] {-1, -1}, searchRange(new int[] {}, 0));
    assertArrayEquals(new int[] {0, 0}, searchRange(new int[] {1}, 1));
  }

  int[] searchRange(int[] nums, int target) {

    int index = binarySearch(nums, 0, nums.length, target);

    if (index == -1) return new int[] {-1, -1};

    int low = index, high = index;

    while (low - 1 >= 0 && target == nums[low - 1]) {
      low--;
    }

    while (high + 1 < nums.length && target == nums[high + 1]) {
      high++;
    }

    return new int[] {low, high};
  }

  @Test
  void targetIndices() {
    assertEquals(List.of(1, 2), targetIndices(new int[] {1, 2, 5, 2, 3}, 2));
    assertEquals(List.of(3), targetIndices(new int[] {1, 2, 5, 2, 3}, 3));
    assertEquals(List.of(4), targetIndices(new int[] {1, 2, 5, 2, 3}, 5));
  }

  private List<Integer> targetIndices(int[] nums, int target) {
    Arrays.sort(nums);

    LinkedList<Integer> integers = new LinkedList<>();

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) integers.add(i);
    }
    return integers;
  }

  @Test
  void maxDistance() {
    assertEquals(3, maxDistance(new int[] {1, 1, 1, 6, 1, 1, 1}));
    assertEquals(4, maxDistance(new int[] {1, 8, 3, 8, 3}));
    assertEquals(1, maxDistance(new int[] {0, 1}));
    assertEquals(8, maxDistance(new int[] {4, 4, 4, 11, 4, 4, 11, 4, 4, 4, 4, 4}));
  }

  int maxDistance(int[] colors) {

    if (colors.length < 2) return 0;

    int maxDist = 0;

    for (int i = 1; i < colors.length; i++) {
      if (colors[i] != colors[0]) maxDist = Math.max(i, maxDist);

      if (colors[colors.length - i] != colors[colors.length - 1])
        maxDist = Math.max(colors.length - (colors.length - i) - 1, maxDist);
    }

    return maxDist;
  }

  @Test
  void maximumDifference() {
    assertEquals(4, maximumDifference(new int[] {7, 1, 5, 4}));
    assertEquals(-1, maximumDifference(new int[] {9, 4, 3, 2}));
    assertEquals(9, maximumDifference(new int[] {1, 5, 2, 10}));
    assertEquals(9, maximumDifference(new int[] {2, 5, 2, 1, 10}));
    assertEquals(-1, maximumDifference(new int[] {5, 2}));
    assertEquals(-1, maximumDifference(new int[] {5}));
  }

  private int maximumDifference(int[] nums) {

    if (nums.length < 2) return -1;

    int minDiff = -1;
    int min = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) minDiff = Math.max(nums[i] - min, minDiff);
      min = Math.min(nums[i], min);
    }

    return minDiff;
  }

  @Test
  void shortestDistance() {
    assertEquals(
        3,
        shortestDistance(
            new String[] {"practice", "makes", "perfect", "coding", "makes"},
            "coding",
            "practice"));

    assertEquals(
        1,
        shortestDistance(
            new String[] {"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
  }

  private int shortestDistance(String[] wordsDict, String word1, String word2) {

    int prev = -1, min = Integer.MAX_VALUE;

    for (int i = 0; i < wordsDict.length; i++) {
      if (wordsDict[i].equals(word1) || wordsDict[i].equals(word2)) {
        if (prev >= 0 && !wordsDict[prev].equals(wordsDict[i])) min = Math.min(min, i - prev);
        prev = i;
      }
    }
    return min;
  }

  @Test
  void invalidTransactions() {
    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "alice,50,100,beijing"})
            .containsAll(Arrays.asList("alice,20,800,mtv", "alice,50,100,beijing")));

    assertTrue(
        invalidTransactions(
                new String[] {"alice,20,800,mtv", "alice,50,100,mtv", "alice,51,100,frankfurt"})
            .containsAll(
                Arrays.asList("alice,20,800,mtv", "alice,50,100,mtv", "alice,51,100,frankfurt")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "alice,50,1200,mtv"})
            .containsAll(Arrays.asList("alice,50,1200,mtv")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "bob,50,1200,mtv"})
            .containsAll(Arrays.asList("bob,50,1200,mtv")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,1220,mtv", "alice,20,1220,mtv"})
            .containsAll(Arrays.asList("alice,20,1220,mtv", "alice,20,1220,mtv")));

    assertTrue(
        invalidTransactions(
                new String[] {
                  "bob,55,173,barcelona",
                  "lee,113,952,zurich",
                  "maybe,115,1973,madrid",
                  "chalicefy,229,283,istanbul",
                  "bob,24,874,shanghai",
                  "alex,568,412,tokyo",
                  "alex,242,1710,milan",
                  "iris,722,879,shenzhen",
                  "chalicefy,281,1586,warsaw",
                  "maybe,246,778,bangkok",
                  "xnova,605,166,newdelhi",
                  "iris,631,991,hongkong",
                  "chalicefy,500,620,tokyo",
                  "chalicefy,380,428,istanbul",
                  "iris,905,180,barcelona",
                  "alex,810,732,shenzhen",
                  "iris,689,389,paris",
                  "xnova,475,298,singapore",
                  "lee,58,709,amsterdam",
                  "xnova,717,546,guangzhou",
                  "maybe,78,435,shenzhen",
                  "maybe,333,145,hongkong",
                  "lee,405,1230,hongkong",
                  "lee,456,1440,tokyo",
                  "chalicefy,286,1071,amsterdam",
                  "alex,55,271,shanghai",
                  "bob,91,273,warsaw",
                  "iris,195,1825,tokyo",
                  "maybe,639,417,madrid",
                  "maybe,305,882,chicago",
                  "lee,443,47,chicago",
                  "chalicefy,958,840,budapest",
                  "lee,162,1239,budapest",
                  "bob,701,505,montreal",
                  "alex,52,1575,munich",
                  "bob,533,1407,amsterdam",
                  "lee,621,491,tokyo",
                  "chalicefy,866,622,rome",
                  "alex,925,455,hongkong",
                  "lee,968,164,moscow",
                  "chalicefy,31,1119,newdelhi",
                  "iris,527,700,warsaw",
                  "bob,286,1694,dubai",
                  "maybe,903,29,barcelona",
                  "maybe,474,1606,prague",
                  "maybe,851,648,beijing",
                  "lee,48,655,chicago",
                  "maybe,378,25,toronto",
                  "lee,922,691,munich",
                  "maybe,411,903,taipei",
                  "lee,651,112,guangzhou",
                  "lee,664,506,dubai",
                  "chalicefy,704,924,milan",
                  "maybe,333,1264,budapest",
                  "chalicefy,587,1112,singapore",
                  "maybe,428,437,moscow",
                  "lee,721,366,newdelhi",
                  "iris,824,1962,beijing",
                  "chalicefy,834,489,istanbul",
                  "alex,639,1473,zurich",
                  "xnova,898,738,tokyo",
                  "chalicefy,585,1313,frankfurt",
                  "xnova,730,759,beijing",
                  "alex,69,892,montreal",
                  "lee,77,91,barcelona",
                  "lee,722,611,taipei",
                  "chalicefy,706,1982,jakarta",
                  "chalicefy,743,584,luxembourg",
                  "xnova,683,322,istanbul",
                  "chalicefy,60,861,prague",
                  "alex,366,871,shenzhen",
                  "chalicefy,77,870,shenzhen",
                  "iris,913,1501,warsaw",
                  "iris,846,1176,warsaw",
                  "bob,873,69,zurich",
                  "alex,601,181,chicago",
                  "chalicefy,118,145,hongkong",
                  "bob,879,982,montreal",
                  "lee,994,950,chicago",
                  "maybe,885,1900,shanghai",
                  "lee,717,1447,shanghai",
                  "chalicefy,71,434,istanbul",
                  "bob,870,968,toronto",
                  "maybe,718,51,beijing",
                  "alex,669,896,istanbul",
                  "chalicefy,639,506,rome",
                  "alex,594,934,frankfurt",
                  "maybe,3,89,jakarta",
                  "xnova,328,1710,rome",
                  "alex,611,571,chicago",
                  "chalicefy,31,458,montreal",
                  "iris,973,696,toronto",
                  "iris,863,148,rome",
                  "chalicefy,926,511,warsaw",
                  "alex,218,1411,zurich",
                  "chalicefy,544,1296,shenzhen",
                  "iris,27,23,montreal",
                  "chalicefy,295,263,prague",
                  "maybe,575,31,munich",
                  "alex,215,174,prague"
                })
            .containsAll(
                Arrays.asList(
                    "bob,55,173,barcelona",
                    "lee,113,952,zurich",
                    "maybe,115,1973,madrid",
                    "chalicefy,229,283,istanbul",
                    "bob,24,874,shanghai",
                    "alex,568,412,tokyo",
                    "alex,242,1710,milan",
                    "iris,722,879,shenzhen",
                    "chalicefy,281,1586,warsaw",
                    "maybe,246,778,bangkok",
                    "iris,631,991,hongkong",
                    "chalicefy,500,620,tokyo",
                    "iris,905,180,barcelona",
                    "iris,689,389,paris",
                    "lee,58,709,amsterdam",
                    "xnova,717,546,guangzhou",
                    "maybe,78,435,shenzhen",
                    "maybe,333,145,hongkong",
                    "lee,405,1230,hongkong",
                    "lee,456,1440,tokyo",
                    "chalicefy,286,1071,amsterdam",
                    "alex,55,271,shanghai",
                    "bob,91,273,warsaw",
                    "iris,195,1825,tokyo",
                    "maybe,305,882,chicago",
                    "lee,443,47,chicago",
                    "chalicefy,958,840,budapest",
                    "lee,162,1239,budapest",
                    "alex,52,1575,munich",
                    "bob,533,1407,amsterdam",
                    "lee,621,491,tokyo",
                    "chalicefy,866,622,rome",
                    "lee,968,164,moscow",
                    "chalicefy,31,1119,newdelhi",
                    "bob,286,1694,dubai",
                    "maybe,903,29,barcelona",
                    "maybe,474,1606,prague",
                    "maybe,851,648,beijing",
                    "lee,48,655,chicago",
                    "maybe,378,25,toronto",
                    "lee,922,691,munich",
                    "maybe,411,903,taipei",
                    "lee,651,112,guangzhou",
                    "lee,664,506,dubai",
                    "chalicefy,704,924,milan",
                    "maybe,333,1264,budapest",
                    "chalicefy,587,1112,singapore",
                    "maybe,428,437,moscow",
                    "lee,721,366,newdelhi",
                    "iris,824,1962,beijing",
                    "chalicefy,834,489,istanbul",
                    "alex,639,1473,zurich",
                    "chalicefy,585,1313,frankfurt",
                    "xnova,730,759,beijing",
                    "alex,69,892,montreal",
                    "lee,77,91,barcelona",
                    "lee,722,611,taipei",
                    "chalicefy,706,1982,jakarta",
                    "chalicefy,743,584,luxembourg",
                    "xnova,683,322,istanbul",
                    "chalicefy,60,861,prague",
                    "chalicefy,77,870,shenzhen",
                    "iris,913,1501,warsaw",
                    "iris,846,1176,warsaw",
                    "bob,873,69,zurich",
                    "alex,601,181,chicago",
                    "chalicefy,118,145,hongkong",
                    "bob,879,982,montreal",
                    "lee,994,950,chicago",
                    "maybe,885,1900,shanghai",
                    "lee,717,1447,shanghai",
                    "chalicefy,71,434,istanbul",
                    "bob,870,968,toronto",
                    "alex,669,896,istanbul",
                    "chalicefy,639,506,rome",
                    "alex,594,934,frankfurt",
                    "xnova,328,1710,rome",
                    "alex,611,571,chicago",
                    "chalicefy,31,458,montreal",
                    "iris,973,696,toronto",
                    "iris,863,148,rome",
                    "chalicefy,926,511,warsaw",
                    "alex,218,1411,zurich",
                    "chalicefy,544,1296,shenzhen",
                    "chalicefy,295,263,prague",
                    "alex,215,174,prague")));

    assertTrue(
        invalidTransactions(
                new String[] {
                  "alice,20,800,mtv",
                  "bob,50,1200,mtv",
                  "alice,20,800,mtv",
                  "alice,50,1200,mtv",
                  "alice,20,800,mtv",
                  "alice,50,100,beijing"
                })
            .containsAll(
                Arrays.asList(
                    "alice,20,800,mtv",
                    "bob,50,1200,mtv",
                    "alice,20,800,mtv",
                    "alice,50,1200,mtv",
                    "alice,20,800,mtv",
                    "alice,50,100,beijing")));
  }

  class Transaction {

    String getTransaction() {
      return transaction;
    }

    Transaction(String transaction) {
      this.transaction = transaction;
    }

    private final String transaction;
    private boolean isValid = true;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Transaction that = (Transaction) o;
      return Objects.equals(transaction, that.transaction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(transaction);
    }

    boolean isValid() {
      return isValid;
    }

    void setValid(boolean valid) {
      isValid = valid;
    }
  }

  private List<String> invalidTransactions(String[] transactions) {

    List<String> sol = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();

    for (String transaction : transactions) {
      transactionList.add(new Transaction(transaction));
    }

    for (int i = 0; i < transactionList.size(); i++) {
      Transaction transaction = transactionList.get(i);
      String[] s = transaction.getTransaction().split(",");

      if (Integer.parseInt(s[2]) - 1000 > 0) transaction.setValid(false);

      for (int j = i + 1; j < transactionList.size(); j++) {
        Transaction transaction1 = transactionList.get(j);
        String[] s1 = transaction1.getTransaction().split(",");

        if (s[0].equals(s1[0])
            && !s[3].equals(s1[3])
            && Math.abs(Integer.parseInt(s[1]) - Integer.parseInt(s1[1])) <= 60) {
          transaction.setValid(false);
          transaction1.setValid(false);
        }

        if (Integer.parseInt(s1[2]) - 1000 > 0) transaction1.setValid(false);
      }
    }

    for (Transaction t : transactionList) {
      if (!t.isValid()) sol.add(t.getTransaction());
    }
    return new ArrayList<>(sol);
  }

  @Test
  void makeEqual() {
    assertTrue(makeEqual(new String[] {"abc", "aabc", "bc"}));
    assertTrue(
        makeEqual(
            new String[] {
              "caaaaa",
              "aaaaaaaaa",
              "a",
              "bbb",
              "bbbbbbbbb",
              "bbb",
              "cc",
              "cccccccccccc",
              "ccccccc",
              "ccccccc",
              "cc",
              "cccc",
              "c",
              "cccccccc",
              "c"
            }));
    assertFalse(makeEqual(new String[] {"ab", "a"}));
    assertFalse(makeEqual(new String[] {"a", "b"}));
  }

  private boolean makeEqual(String[] words) {
    int[] map = new int[26];

    for (String s : words) {
      for (int i = 0; i < s.length(); i++) {
        ++map[s.charAt(i) - 'a'];
      }
    }

    for (int freq : map) {
      if (freq != 0 && freq % words.length != 0) return false;
    }

    return true;
  }

  @Test
  void areOccurrencesEqual() {
    assertTrue(areOccurrencesEqual("abacbc"));
    assertFalse(areOccurrencesEqual("aaabb"));
  }

  boolean areOccurrencesEqual(String s) {

    int[] dict = new int[256];

    for (int i = 0; i < s.length(); i++) {
      dict[s.charAt(i)]++;
    }

    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      if (dict[s.charAt(i)] > 0) {
        if (count == 0) count = dict[s.charAt(i)];
        else if (count != dict[s.charAt(i)]) return false;
      }
    }

    return true;
  }

  @Test
  void summaryRanges() {
    assertEquals(Arrays.asList("0->2", "4->5", "7"), summaryRanges(new int[] {0, 1, 2, 4, 5, 7}));
    assertEquals(
        Arrays.asList("0", "2->4", "6", "8->9"), summaryRanges(new int[] {0, 2, 3, 4, 6, 8, 9}));
  }

  private List<String> summaryRanges(int[] nums) {

    if (nums.length == 0) return new ArrayList<>();

    List<String> result = new ArrayList<>();

    int start = nums[0];
    int temp = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == temp + 1) {
        temp = nums[i];
        continue;
      }
      result.add(start == temp ? String.valueOf(start) : start + "->" + temp);
      start = nums[i];
      temp = nums[i];
    }

    result.add(start == temp ? String.valueOf(start) : start + "->" + temp);

    return result;
  }

  @Test
  void canAttendMeetings() {
    assertFalse(canAttendMeetings(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
    assertTrue(canAttendMeetings(new int[][] {{7, 10}, {2, 4}}));
    assertFalse(canAttendMeetings(new int[][] {{6, 15}, {13, 20}, {6, 17}}));
    assertFalse(canAttendMeetings(new int[][] {{19, 20}, {1, 10}, {5, 14}}));
  }

  private boolean canAttendMeetings(int[][] intervals) {

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) return false;
    }

    return true;
  }

  @Test
  void countKDifference() {
    assertEquals(4, countKDifference(new int[] {1, 2, 2, 1}, 1));
    assertEquals(0, countKDifference(new int[] {1, 3}, 3));
    assertEquals(3, countKDifference(new int[] {3, 2, 1, 5, 4}, 2));
  }

  private int countKDifference(int[] nums, int k) {

    int count = 0;

    Map<Integer, Integer> map = new HashMap<>();

    for (int num : nums) {
      count += map.getOrDefault(num + k, 0) + map.getOrDefault(num - k, 0);
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    return count;
  }

  @Test
  void maximumUnits() {
    assertEquals(8, maximumUnits(new int[][] {{1, 3}, {2, 2}, {3, 1}}, 4));
    assertEquals(91, maximumUnits(new int[][] {{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
  }

  private int maximumUnits(int[][] boxTypes, int truckSize) {

    Arrays.sort(boxTypes, (t1, t2) -> Integer.compare(t2[1], t1[1]));

    int i = 0;
    int sol = 0;
    int maxBoxes = 0;

    while (i < boxTypes.length && maxBoxes < truckSize) {

      int[] box = boxTypes[i];

      int boxesToAdd = box[0] + maxBoxes <= truckSize ? box[0] : truckSize - maxBoxes;

      maxBoxes = maxBoxes + boxesToAdd;

      sol = sol + box[1] * boxesToAdd;

      i++;
    }

    return sol;
  }

  @Test
  void findCenter() {
    assertEquals(2, findCenter(new int[][] {{1, 2}, {2, 3}, {4, 2}}));
    assertEquals(1, findCenter(new int[][] {{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
  }

  private int findCenter(int[][] edges) {
    int[] edge1 = edges[0];
    int[] edge2 = edges[1];

    if (edge1[0] == edge2[0] || edge1[0] == edge2[1]) return edge1[0];
    else return edge1[1];
  }

  @Test
  void getMaximumGenerated() {

    assertEquals(0, getMaximumGenerated(0));
    assertEquals(1, getMaximumGenerated(1));
    assertEquals(1, getMaximumGenerated(2));
    assertEquals(2, getMaximumGenerated(3));
    assertEquals(2, getMaximumGenerated(4));
    assertEquals(3, getMaximumGenerated(5));
    assertEquals(4, getMaximumGenerated(10));
    assertEquals(7, getMaximumGenerated(20));
    assertEquals(8, getMaximumGenerated(21));
    assertEquals(8, getMaximumGenerated(30));
    assertEquals(5, getMaximumGenerated(15));
  }

  int getMaximumGenerated(int n) {

    if (n == 0) return 0;
    if (n == 1 || n == 2) return 1;
    if (n == 3) return 2;

    int[] array = new int[n + 1];

    array[0] = 0;
    array[1] = 1;

    int i = 1;

    while (i <= n / 2) {
      array[i * 2] = array[i];

      if (i == n / 2) break;

      array[(i * 2) + 1] = array[i] + array[i + 1];
      i++;
    }

    if (((n & 1) == 1)) array[(i * 2) + 1] = array[i] + array[i + 1];

    return Arrays.stream(array).max().getAsInt();
  }

  @Test
  void finalValueAfterOperations() {
    assertEquals(1, finalValueAfterOperations(new String[] {"--X", "X++", "X++"}));
    assertEquals(3, finalValueAfterOperations(new String[] {"++X", "++X", "X++"}));
    assertEquals(0, finalValueAfterOperations(new String[] {"X++", "++X", "--X", "X--"}));
  }

  private int finalValueAfterOperations(String[] operations) {

    int sol = 0;

    for (String s : operations) {
      if (s.contains("+")) sol++;
      else sol--;
    }

    return sol;
  }

  @Test
  void tictactoe() {
    assertEquals(
        "A",
        tictactoe(
            new int[][] {
              {0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2},
            }));
    assertEquals("B", tictactoe(new int[][] {{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}}));
    assertEquals(
        "Draw",
        tictactoe(
            new int[][] {{0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}}));
    assertEquals("Pending", tictactoe(new int[][] {{0, 0}, {0, 1}}));
    assertEquals(
        "B",
        tictactoe(new int[][] {{2, 0}, {1, 1}, {0, 2}, {2, 1}, {1, 2}, {1, 0}, {0, 0}, {0, 1}}));
  }

  String tictactoe(int[][] moves) {
    char[][] board = new char[3][3];

    char player = X;

    for (int[] move : moves) {

      board[move[0]][move[1]] = player;

      if (checkWinner(board, moves, player)) return player == X ? "A" : "B";

      player = player == X ? O : X;
    }

    return moves.length == 9 ? "Draw" : "Pending";
  }

  private boolean checkWinner(char[][] board, int[][] moves, char c) {

    if (board[0][0] == c && board[0][1] == c && board[0][2] == c) return true;
    if (board[1][0] == c && board[1][1] == c && board[1][2] == c) return true;
    if (board[2][0] == c && board[2][1] == c && board[2][2] == c) return true;

    if (board[0][0] == c && board[1][0] == c && board[2][0] == c) return true;
    if (board[0][1] == c && board[1][1] == c && board[2][1] == c) return true;
    if (board[0][2] == c && board[1][2] == c && board[2][2] == c) return true;

    if (board[0][0] == c && board[1][1] == c && board[2][2] == c) return true;

    return board[0][2] == c && board[1][1] == c && board[2][0] == c;
  }

  @Test
  void getConcatenation() {
    assertArrayEquals(new int[] {1, 2, 1, 1, 2, 1}, getConcatenation(new int[] {1, 2, 1}));
    assertArrayEquals(new int[] {1, 3, 2, 1, 1, 3, 2, 1}, getConcatenation(new int[] {1, 3, 2, 1}));
  }

  private int[] getConcatenation(int[] nums) {
    int[] sol = new int[nums.length * 2];

    for (int i = 0; i < nums.length; i++) {
      sol[i] = nums[i];
      sol[i + nums.length] = nums[i];
    }
    return sol;
  }

  @Test
  void longestPalindrome() {
    assertEquals(7, longestPalindrome("abccccdd"));
    assertEquals(1, longestPalindrome("a"));
    assertEquals(2, longestPalindrome("bb"));
    assertEquals(6, longestPalindrome("AAAAAA"));
    assertEquals(
        983,
        longestPalindrome(
            "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
  }

  private int longestPalindrome(String s) {
    int[] chars = new int[256];

    for (char c : s.toCharArray()) {
      chars[c]++;
    }

    int ans = 0;
    for (int v : chars) {
      if (v != 0) {
        ans += v / 2 * 2;
        if (ans % 2 == 0 && v % 2 == 1) ans++;
      }
    }
    return ans;
  }

  @Test
  void canPermutePalindrome() {
    assertFalse(canPermutePalindrome("code"));
    assertTrue(canPermutePalindrome("aab"));
    assertTrue(canPermutePalindrome("carerac"));
  }

  private boolean canPermutePalindrome(String s) {
    int[] chars = new int[26];

    for (char c : s.toCharArray()) {
      chars[c - 'a']++;
    }

    int onlyOneEven = 0;

    for (int aChar : chars) {
      if ((aChar & 1) == 1) onlyOneEven++;

      if (onlyOneEven > 1) return false;
    }

    return true;
  }

  @Test
  void stringMatching() {
    assertTrue(
        stringMatching(new String[] {"mass", "as", "hero", "superhero"})
            .containsAll(Arrays.asList("as", "hero")));

    assertTrue(
        stringMatching(new String[] {"leetcode", "et", "code"})
            .containsAll(Arrays.asList("et", "code")));

    assertTrue(stringMatching(new String[] {"blue", "green", "bu"}).isEmpty());

    assertTrue(
        stringMatching(new String[] {"leetcoder", "leetcode", "od", "hamlet", "am"})
            .containsAll(Arrays.asList("leetcode", "od", "am")));
  }

  private List<String> stringMatching(String[] words) {

    Arrays.sort(words, Comparator.comparingInt(String::length));

    HashSet<String> strings = new HashSet<>();

    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (words[j].contains(words[i])) strings.add(words[i]);
      }
    }

    return new ArrayList<>(strings);
  }

  @Test
  void buildArray() {
    assertArrayEquals(new int[] {0, 1, 2, 4, 5, 3}, buildArray(new int[] {0, 2, 1, 5, 3, 4}));
    assertArrayEquals(new int[] {4, 5, 0, 1, 2, 3}, buildArray(new int[] {5, 0, 1, 2, 3, 4}));
  }

  int[] buildArray(int[] nums) {
    int[] sol = new int[nums.length];

    for (int i = 0; i < nums.length; i++) {
      sol[i] = nums[nums[i]];
    }

    return sol;
  }

  @Test
  void sortSentence() {
    assertEquals("This is a sentence", sortSentence("is2 sentence4 This1 a3"));
    assertEquals("Me Myself and I", sortSentence("Myself2 Me1 I4 and3"));
  }

  private String sortSentence(String s) {

    return Arrays.stream(s.split("\\s"))
        .sorted(Comparator.comparing(s12 -> s12.substring(s12.length() - 1)))
        .collect(Collectors.joining(" "))
        .replaceAll("\\d", "");
  }

  @Test
  void checkIfPangram() {
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
  void removeDuplicates() {
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
  void minStartValue() {
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
  void findMissingRanges() {
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
  void minimumAbsDifference() {
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
  void transformArray() {
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

  void updateArray(int[] arr, int[] sol) {
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
  void dietPlanPerformance() {
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
