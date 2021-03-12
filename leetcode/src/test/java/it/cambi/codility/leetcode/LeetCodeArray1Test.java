package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LeetCodeArray1Test {

  static class SubrectangleQueries {
    private int[][] matrix;

    public SubrectangleQueries(int[][] rectangle) {
      matrix = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {

      while (row1 <= row2) {
        int col = col1;

        while (col <= col2) {

          matrix[row1][col] = newValue;
          col++;
        }
        row1++;
      }
    }

    public int getValue(int row, int col) {
      return matrix[row][col];
    }
  }

  static class IntObj implements Comparable<IntObj> {
    private int value;
    private int freq;

    public IntObj(int value, int freq) {
      this.value = value;
      this.freq = freq;
    }

    @Override
    public int compareTo(IntObj o) {
      int freq = o.freq;

      return Integer.compare(freq - this.freq, 0);
    }
  }

  static class RLEIteratorObj {
    private int value;
    private long freq;

    public RLEIteratorObj(int value, long freq) {
      this.value = value;
      this.freq = freq;
    }
  }

  static class RLEIterator {

    private Queue<RLEIteratorObj> list = new LinkedList<>();
    private long pointer, lastPosition;
    private int currentValue;

    public RLEIterator(int[] A) {
      long sum = 0;

      for (int i = 0; i < A.length; i += 2) {
        if (A[i] == 0) continue;

        sum += A[i];
        list.add(new RLEIteratorObj(A[i + 1], sum));
      }
    }

    public int next(int n) {
      pointer += n;

      if (pointer <= lastPosition) return currentValue;

      while (!list.isEmpty() && pointer > list.peek().freq) list.poll();

      if (list.isEmpty()) return -1;

      RLEIteratorObj next = list.peek();
      currentValue = next.value;
      lastPosition = next.freq;
      return currentValue;
    }
  }

  @Test
  public void fixedPoint() {
    assertEquals(3, fixedPoint(new int[] {-10, -5, 0, 3, 7}, 0, 5));
    assertEquals(0, fixedPoint(new int[] {0, 2, 5, 8, 17}, 0, 5));
    assertEquals(-1, fixedPoint(new int[] {-10, -5, 3, 4, 7, 9}, 0, 6));
    assertEquals(4, fixedPoint(new int[] {-10, -5, -2, 0, 4, 5, 6, 7, 8, 9, 10}, 0, 11));
  }

  public int fixedPoint(int[] a, int fromIndex, int toIndex) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = a[mid];

      if (midVal < mid) low = mid + 1;
      else if (midVal > mid || mid > 0 && a[mid - 1] == mid - 1) high = mid - 1;
      else return mid;
    }

    return -1;
  }

  @Test
  public void countMatches() {
    assertEquals(
        1,
        countMatches(
            Arrays.asList(
                Arrays.asList("phone", "blue", "pixel"),
                Arrays.asList("computer", "silver", "lenovo"),
                Arrays.asList("phone", "gold", "iphone")),
            "color",
            "silver"));

    assertEquals(
        2,
        countMatches(
            Arrays.asList(
                Arrays.asList("phone", "blue", "pixel"),
                Arrays.asList("computer", "silver", "lenovo"),
                Arrays.asList("phone", "gold", "iphone")),
            "type",
            "phone"));
  }

  public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {

    HashMap<String, Integer> map = new HashMap<>();
    map.put("type", 0);
    map.put("color", 1);
    map.put("name", 2);

    return (int) items.stream().filter(i -> i.get(map.get(ruleKey)).equals(ruleValue)).count();
  }

  @Test
  public void sortString() {
    assertEquals("abccbaabccba", sortString("aaaabbbbcccc"));
  }

  public String sortString(String s) {
    String alphaBet = "abcdefghijklmnopqrstuvwxyz";

    int totFreq = 0;
    int[] freq = new int[256];

    for (int i = 0; i < s.length(); i++) {
      freq[s.charAt(i)] = ++freq[s.charAt(i)];
      totFreq++;
    }

    StringBuilder sb = new StringBuilder();

    while (totFreq > 0) {

      for (int i = 0; i < alphaBet.length(); i++) {
        if (freq[alphaBet.charAt(i)] > 0) {
          freq[alphaBet.charAt(i)] = --freq[alphaBet.charAt(i)];
          sb.append(alphaBet.charAt(i));
          totFreq--;
        }
      }

      for (int i = alphaBet.length() - 1; i >= 0; i--) {
        if (freq[alphaBet.charAt(i)] > 0) {
          freq[alphaBet.charAt(i)] = --freq[alphaBet.charAt(i)];
          sb.append(alphaBet.charAt(i));
          totFreq--;
        }
      }
    }

    return sb.toString();
  }

  @Test
  public void sumOddLengthSubarrays() {
    assertEquals(58, sumOddLengthSubarrays(new int[] {1, 4, 2, 5, 3}));
    assertEquals(3, sumOddLengthSubarrays(new int[] {1, 2}));
    assertEquals(66, sumOddLengthSubarrays(new int[] {10, 11, 12}));
  }

  private int sumOddLengthSubarrays(int[] arr) {

    int sum = 0;

    int iter;
    int partSum = 0;
    for (int i = 0; i < arr.length; i++) {
      iter = 1;
      sum += arr[i];
      partSum += arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        iter++;
        partSum += arr[j];

        if ((iter & 1) == 1) sum += partSum;
      }
      partSum = 0;
    }

    return sum;
  }

  @Test
  public void arraysIntersection() {
    assertEquals(
        Arrays.asList(1, 5),
        arraysIntersection(
            new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 5, 7, 9}, new int[] {1, 3, 4, 5, 8}));

    assertEquals(
        Collections.emptyList(),
        arraysIntersection(
            new int[] {197, 418, 523, 876, 1356},
            new int[] {501, 880, 1593, 1710, 1870},
            new int[] {521, 682, 1337, 1395, 1764}));
  }

  private List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {

    int length = Math.min(arr3.length, Math.min(arr1.length, arr2.length));

    int i = 0;
    int[] arr = new int[2001];

    while (i < length) {
      arr[arr1[i]] = ++arr[arr1[i]];
      arr[arr2[i]] = ++arr[arr2[i]];
      arr[arr3[i]] = ++arr[arr3[i]];
      i++;
    }

    return IntStream.range(0, arr.length)
        .filter(p -> arr[p] >= 3)
        .boxed()
        .collect(Collectors.toList());
  }

  @Test
  public void highFive() {
    assertArrayEquals(
        new int[][] {{1, 100}, {7, 100}},
        highFive(
            new int[][] {
              {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100}, {1, 100}, {7, 100},
              {1, 100}, {7, 100}
            }));
  }

  private int[][] highFive(int[][] items) {
    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int[] stud : items) {
      List<Integer> list = map.getOrDefault(stud[0], new ArrayList<>());
      list.add(stud[1]);
      map.put(stud[0], list);
    }

    int[][] result = new int[map.size()][];

    int i = 0;
    int sum = 0;
    int pos = 0;
    for (Map.Entry<Integer, List<Integer>> integerListEntry : map.entrySet()) {

      List<Integer> scores =
          integerListEntry.getValue().stream()
              .sorted(Comparator.reverseOrder())
              .collect(Collectors.toList());

      while (i < 5) {
        sum += scores.get(i++);
      }

      result[pos++] = new int[] {integerListEntry.getKey(), sum / 5};
      sum = 0;
      i = 0;
    }

    return result;
  }

  @Test
  public void maximumWealth() {
    assertEquals(6, maximumWealth(new int[][] {{1, 2, 3}, {3, 2, 1}}));
    assertEquals(10, maximumWealth(new int[][] {{1, 5}, {7, 3}, {3, 5}}));
    assertEquals(17, maximumWealth(new int[][] {{2, 8, 7}, {7, 1, 3}, {1, 9, 5}}));
  }

  private int maximumWealth(int[][] accounts) {
    int value = 0;
    for (int[] account : accounts) {
      value = Math.max(value, IntStream.of(account).sum());
    }

    return value;
  }

  @Test
  public void isPossibleDivide() {
    assertTrue(isPossibleDivide(new int[] {1, 2, 3, 3, 4, 4, 5, 6}, 4));
    assertTrue(isPossibleDivide(new int[] {3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
    assertTrue(isPossibleDivide(new int[] {3, 3, 2, 2, 1, 1}, 3));
    assertFalse(isPossibleDivide(new int[] {1, 2, 3, 4}, 3));
    assertTrue(isPossibleDivide(new int[] {1}, 1));
    assertFalse(isPossibleDivide(new int[] {}, 1));
    assertFalse(isPossibleDivide(new int[] {16, 21, 26, 35}, 4));
    assertFalse(isPossibleDivide(new int[] {2, 5, 6, 8, 9, 10}, 3));
  }

  public boolean isPossibleDivide(int[] nums, int k) {

    if (nums == null || nums.length == 0) return false;

    Arrays.parallelSort(nums);
    int start = nums[0];
    int end = start + k - 1;

    int[] array = new int[nums.length - k <= 0 ? k : nums.length - k];
    int z = 0;
    for (int i = 0; i < nums.length; i++) {

      if (nums[i] == start && start <= end) {
        start++;
        continue;
      }

      if (z >= array.length) return false;

      array[z++] = nums[i];
      if (i == nums.length - 1) {
        if (array[0] == 0) return true;
        else if (array[array.length - 1] == 0 || array.length < k) return false;

        nums = array.clone();
        start = nums[0];
        end = start + k - 1;
        array = new int[nums.length <= k ? k : nums.length - k];
        i = -1;
        z = 0;
      }
    }

    return array.length == 0 || array[0] == 0;
  }

  @Test
  public void numTimesAllBlue() {
    assertEquals(6, numTimesAllBlue(new int[] {1, 2, 3, 4, 5, 6}));
    assertEquals(6, numTimesAllBlue(new int[] {1, 2, 3, 4, 7, 6, 5, 8}));
    assertEquals(5, numTimesAllBlue(new int[] {1, 2, 3, 4, 7, 8, 6, 5}));
    assertEquals(1, numTimesAllBlue(new int[] {2, 3, 4, 5, 6, 7, 8, 1}));
    assertEquals(2, numTimesAllBlue(new int[] {1, 8, 2, 3, 4, 5, 6, 7}));
    assertEquals(3, numTimesAllBlue(new int[] {2, 1, 3, 5, 4}));
    assertEquals(2, numTimesAllBlue(new int[] {3, 2, 4, 1, 5}));
    assertEquals(3, numTimesAllBlue(new int[] {2, 1, 4, 3, 6, 5}));
    assertEquals(1, numTimesAllBlue(new int[] {4, 1, 2, 3}));
    assertEquals(1, numTimesAllBlue(new int[] {1}));
  }

  public int numTimesAllBlue(int[] light) {

    int max = light[0];
    int ans = 0;
    for (int i = 0; i < light.length; i++) {
      if (light[i] > max) max = light[i];

      if (max == i + 1) ans++;
    }
    return ans;
  }

  @Test
  public void frequencySort() {
    assertArrayEquals(new int[] {3, 1, 1, 2, 2, 2}, frequencySort(new int[] {1, 1, 2, 2, 2, 3}));
    assertArrayEquals(new int[] {1, 3, 3, 2, 2}, frequencySort(new int[] {2, 3, 1, 3, 2}));
    assertArrayEquals(
        new int[] {5, -1, 4, 4, -6, -6, 1, 1, 1},
        frequencySort(new int[] {-1, 1, -6, 4, 5, -6, 1, 4, 1}));
  }

  public int[] frequencySort(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int integer : nums) map.put(integer, map.getOrDefault(integer, 0) + 1);

    return Arrays.stream(nums)
        .boxed()
        .sorted(
            (o1, o2) -> {
              int compare = map.get(o1).compareTo(map.get(o2));

              if (compare == 0) return o2.compareTo(o1);

              return compare;
            })
        .mapToInt(i -> i)
        .toArray();
  }

  @Test
  public void reformat() {
    assertEquals("0c1b2a", reformat("a0b1c2"));
    assertEquals("", reformat("leetcode"));
    assertEquals("", reformat("1229857369"));
    assertEquals("c0v1o2i9d", reformat("covid2019"));
    assertEquals("1b2a3", reformat("ab123"));
  }

  private String reformat(String s) {

    char[] chars = s.toCharArray();
    Arrays.parallelSort(chars);
    int countAlpha = 0, countDigit = 0;

    for (char c : chars) {
      if (c >= 48 && c <= 57) countDigit++;
      else countAlpha++;
    }

    if (countAlpha - countDigit > 1 || countAlpha - countDigit < -1) return "";

    StringBuilder builder = new StringBuilder();

    for (int i = chars.length - 1; i >= chars.length / 2; i--) {

      if ((chars.length & 1) == 1 && i == chars.length / 2 && countDigit > countAlpha) {
      } else if ((chars.length & 1) == 1 && i == chars.length / 2 && countDigit < countAlpha) {
        builder.insert(0, chars[chars.length - 1 - i]);
        break;
      } else builder.append(chars[chars.length - 1 - i]);

      builder.append(chars[i]);
    }

    return builder.toString();
  }

  @Test
  public void RLEIteratorTest() {

    RLEIterator rleIterator1 =
        new RLEIterator(
            new int[] {
              923381016, 843, 898173122, 924, 540599925, 391, 705283400, 275, 811628709, 850,
              895038968, 590, 949764874, 580, 450563107, 660, 996257840, 917, 793325084, 82
            });

    assertEquals(843, rleIterator1.next(612783106));
    assertEquals(924, rleIterator1.next(486444202));
    assertEquals(924, rleIterator1.next(630147341));
    assertEquals(275, rleIterator1.next(845077576));
    assertEquals(275, rleIterator1.next(243035623));
    assertEquals(850, rleIterator1.next(731489221));
    assertEquals(850, rleIterator1.next(117134294));
    assertEquals(590, rleIterator1.next(220460537));
    assertEquals(590, rleIterator1.next(794582972));
    assertEquals(580, rleIterator1.next(332536150));
    assertEquals(660, rleIterator1.next(815913097));
    assertEquals(660, rleIterator1.next(100607521));
    assertEquals(660, rleIterator1.next(146358489));
    assertEquals(917, rleIterator1.next(697670641));
    assertEquals(917, rleIterator1.next(45234068));
    assertEquals(82, rleIterator1.next(573866037));
    assertEquals(82, rleIterator1.next(519323635));
    assertEquals(82, rleIterator1.next(27431940));
    assertEquals(82, rleIterator1.next(16279485));
    assertEquals(82, rleIterator1.next(203970));

    RLEIterator rleIterator = new RLEIterator(new int[] {3, 8, 0, 9, 2, 5});

    assertEquals(8, rleIterator.next(2));
    assertEquals(8, rleIterator.next(1));
    assertEquals(5, rleIterator.next(1));
    assertEquals(-1, rleIterator.next(2));
  }

  @Test
  public void threeConsecutiveOdds() {
    assertFalse(threeConsecutiveOdds(new int[] {2, 6, 4, 1}));
    assertTrue(threeConsecutiveOdds(new int[] {1, 2, 34, 3, 4, 5, 7, 23, 12}));
    assertTrue(threeConsecutiveOdds(new int[] {1, 1, 1}));
    assertTrue(threeConsecutiveOdds(new int[] {0, 1, 1, 1}));
    assertFalse(threeConsecutiveOdds(new int[] {1}));
  }

  public boolean threeConsecutiveOdds(int[] arr) {

    if (arr.length < 3) return false;

    if ((arr[0] & 1) == 1 && (arr[1] & 1) == 1 && (arr[2] & 1) == 1) return true;

    int i = 3;

    while (i < arr.length) {
      if ((arr[i] & 1) == 1 && (arr[i - 1] & 1) == 1 && (arr[i - 2] & 1) == 1) return true;

      i++;
    }

    return false;
  }

  @Test
  public void countGoodTriplets() {
    assertEquals(4, countGoodTriplets(new int[] {3, 0, 1, 1, 9, 7}, 7, 2, 3));
  }

  public int countGoodTriplets(int[] arr, int a, int b, int c) {

    int count = 0;

    for (int i = 0; i < arr.length; i++) {

      for (int j = i + 1; j < arr.length; j++) {

        if (Math.abs(arr[i] - arr[j]) <= a) {
          for (int k = j + 1; k < arr.length; k++) {
            if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) count++;
          }
        }
      }
    }

    return count;
  }

  @Test
  public void numIdenticalPairs() {
    assertEquals(4, numIdenticalPairs(new int[] {1, 2, 3, 1, 1, 3}));
    assertEquals(6, numIdenticalPairs(new int[] {1, 1, 1, 1}));
    assertEquals(0, numIdenticalPairs(new int[] {1, 2, 3}));
  }

  private int numIdenticalPairs(int[] nums) {
    int[] count = new int[101];

    for (int num : nums) {
      count[num] = count[num] + 1;
    }

    int sol = 0;

    for (int value : count) {
      sol += value > 1 ? (value * (value - 1)) / 2 : 0;
    }

    return sol;
  }

  @Test
  public void characterReplacement() {
    assertEquals(0, characterReplacement(null, 2));
    assertEquals(1, characterReplacement("A", 2));
    assertEquals(0, characterReplacement("", 2));
    assertEquals(4, characterReplacement("ABAB", 2));
    assertEquals(5, characterReplacement("ABABB", 2));
    assertEquals(4, characterReplacement("AABABBA", 1));
    assertEquals(7, characterReplacement("AAAAABABBA", 1));
    assertEquals(8, characterReplacement("ABBBBBBABAA", 1));
    assertEquals(7, characterReplacement("BBBBBBB", 1));
    assertEquals(4, characterReplacement("AAAA", 2));
    assertEquals(9, characterReplacement("AAAABAAAA", 1));
    assertEquals(4, characterReplacement("ABBB", 2));
    assertEquals(5, characterReplacement("ABBCCC", 2));
    assertEquals(11, characterReplacement("ABABBABBBBBB", 2));
  }

  public int characterReplacement(String s, int k) {

    if (s == null) return 0;
    int n = s.length();

    int[] charsCounter = new int[26];
    int start = 0;
    int charCount = 0;
    int maxLength = 0;

    for (int end = 0; end < n; end++) {

      char ch = s.charAt(end);
      charsCounter[ch - 'A']++;

      charCount = Math.max(charCount, charsCounter[ch - 'A']);

      while (end - start - charCount + 1 > k) {
        charsCounter[s.charAt(start) - 'A']--;
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  @Test
  public void countPrimes() {
    assertEquals(4, countPrimes(10));
  }

  private int countPrimes(int n) {
    boolean[] isPrime = new boolean[n];
    for (int i = 2; i < n; i++) {
      isPrime[i] = true;
    }

    for (int i = 2; i * i < n; i++) {
      if (!isPrime[i]) continue;
      for (int j = i * i; j < n; j += i) {
        isPrime[j] = false;
      }
    }
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (isPrime[i]) count++;
    }
    return count;
  }

  @Test
  public void hIndex() {

    assertEquals(3, hIndex(new int[] {3, 0, 6, 1, 5}));
    assertEquals(1, hIndex(new int[] {0, 1}));
    assertEquals(1, hIndex(new int[] {0, 0, 3}));
    assertEquals(1, hIndex(new int[] {0, 2}));
  }

  private int hIndex(int[] citations) {

    int n = citations.length;

    Arrays.sort(citations);
    if (n == 0 || citations[0] == 0 & n == 1) return 0;
    if (n == 1) return 1;
    int i = n - 1;
    while (i >= 0) {
      if (citations[i] < (n - i)) break;
      i--;
    }
    return n - i - 1;
  }

  @Test
  public void restoreString() {
    assertEquals("leetcode", restoreString("codeleet", new int[] {4, 5, 6, 7, 0, 2, 1, 3}));
    assertEquals("abc", restoreString("abc", new int[] {0, 1, 2}));
    assertEquals("nihao", restoreString("aiohn", new int[] {3, 1, 4, 2, 0}));
    assertEquals("arigatou", restoreString("aaiougrt", new int[] {4, 0, 2, 6, 7, 3, 1, 5}));
    assertEquals("rat", restoreString("art", new int[] {1, 0, 2}));
  }

  public String restoreString(String s, int[] indices) {

    char[] chars = new char[s.length()];

    for (int i = 0; i < indices.length; i++) {
      chars[indices[i]] = s.charAt(i);
    }

    StringBuilder result = new StringBuilder();
    for (char c : chars) {
      result.append(c);
    }

    return result.toString();
  }

  @Test
  public void largestSumAfterKNegations() {
    assertEquals(5, largestSumAfterKNegations(new int[] {4, 2, 3}, 1));
    assertEquals(6, largestSumAfterKNegations(new int[] {3, -1, 0, 2}, 3));
    assertEquals(13, largestSumAfterKNegations(new int[] {2, -3, -1, 5, -4}, 2));
    assertEquals(32, largestSumAfterKNegations(new int[] {-2, 9, 9, 8, 4}, 5));
    assertEquals(11, largestSumAfterKNegations(new int[] {-5, 6}, 1));
    assertEquals(1, largestSumAfterKNegations(new int[] {-5, 6}, 2));
    assertEquals(11, largestSumAfterKNegations(new int[] {-5, 0, 6}, 2));
    assertEquals(11, largestSumAfterKNegations(new int[] {-5, 0, 6}, 1));
    assertEquals(26, largestSumAfterKNegations(new int[] {1, 3, 2, 6, 7, 9}, 3));
    assertEquals(53, largestSumAfterKNegations(new int[] {8, -7, -3, -9, 1, 9, -6, -9, 3}, 8));
  }

  public int largestSumAfterKNegations(int[] A, int K) {
    Arrays.sort(A);

    int i = 0;

    while (K > 0) {

      if (A[i] <= A[i + 1]) {
        A[i] = -A[i];
        K--;
        continue;
      }

      i++;
    }

    return Arrays.stream(A).sum();
  }

  @Test
  public void twoCitySchedCost() {
    assertEquals(
        1859,
        twoCitySchedCost(
            new int[][] {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}}));
  }

  public int twoCitySchedCost(int[][] costs) {
    if (costs.length == 0) return 0;

    Arrays.sort(costs, (a, b) -> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));

    int min = 0;
    int cityA = 0;
    int cityB = 0;
    int N = costs.length / 2;

    for (int[] cost : costs) {
      if (cost[0] < cost[1] && cityA < N || (cityB == N)) {
        min += cost[0];
        cityA++;
      } else {
        min += cost[1];
        cityB++;
      }
    }

    return min;
  }

  @Test
  public void minDeletionSize() {
    assertEquals(1, minDeletionSize(new String[] {"cba", "daf", "ghi"}));
    assertEquals(0, minDeletionSize(new String[] {"a", "b"}));
    assertEquals(3, minDeletionSize(new String[] {"zyx", "wvu", "tsr"}));
  }

  public int minDeletionSize(String[] A) {

    int lettersLength = A[0].length();
    int result = 0;
    for (int i = 0; i < lettersLength; i++) {

      for (int j = 1; j < A.length; j++) {
        if (A[j].charAt(i) < A[j - 1].charAt(i)) {
          result++;
          break;
        }
      }
    }

    return result;
  }

  @Test
  public void isOneBitCharacter() {

    assertTrue(isOneBitCharacter(new int[] {1, 0, 0}));
    assertFalse(isOneBitCharacter(new int[] {1, 1, 1, 0}));
    assertFalse(isOneBitCharacter(new int[] {1}));
    assertTrue(isOneBitCharacter(new int[] {0}));
  }

  public boolean isOneBitCharacter(int[] bits) {

    boolean twoBits = false;

    for (int i = 0; i < bits.length; i++) {

      if (i == bits.length - 1 && twoBits) return false;

      if (bits[i] == 1 || twoBits) twoBits = !twoBits;
    }

    return bits[bits.length - 1] == 0;
  }

  @Test
  public void findPeakElement() {
    assertEquals(0, findPeakElement(new int[] {1}));
    assertEquals(1, findPeakElement(new int[] {1, 2}));
    assertEquals(2, findPeakElement(new int[] {1, 2, 3}));
    assertEquals(2, findPeakElement(new int[] {1, 2, 3, 1}));
  }

  public int findPeakElement(int[] nums) {
    int i = 0;

    while (i + 1 < nums.length && nums[i] < nums[i + 1]) i++;

    return i;
  }

  @Test
  public void peakIndexInMountainArray() {

    assertEquals(1, peakIndexInMountainArray(new int[] {0, 1, 0}));
    assertEquals(1, peakIndexInMountainArray(new int[] {0, 2, 1, 0}));
  }

  public int peakIndexInMountainArray(int[] A) {
    int i;
    for (i = 1; i < A.length; i++) {

      if (i < A.length - 1 && A[i - 1] < A[i] && A[i + 1] < A[i]) return i;
    }

    return -1;
  }

  @Test
  public void numTeams() {
    assertEquals(3, numTeams(new int[] {2, 5, 3, 4, 1}));
    assertEquals(0, numTeams(new int[] {2, 1, 3}));
    assertEquals(4, numTeams(new int[] {1, 2, 3, 4}));
  }

  public int numTeams(int[] rating) {

    int countIncr = 0;
    int countDecr = 0;

    for (int i = 0; i < rating.length; i++) {

      for (int j = i; j < rating.length; j++) {}
    }

    return countIncr + countDecr;
  }

  @Test
  public void peopleIndexes() {

    assertEquals(
        Arrays.asList(0, 1, 4),
        peopleIndexes(
            Arrays.stream(
                    new String[][] {
                      {"leetcode", "google", "facebook"},
                      {"google", "microsoft"},
                      {"google", "facebook"},
                      {"google"},
                      {"amazon"},
                    })
                .map(Arrays::asList)
                .collect(Collectors.toList())));
  }

  public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {

    List<Integer> result = new ArrayList<>();
    boolean isContained;

    for (int i = 0; i < favoriteCompanies.size(); i++) {
      isContained = false;
      for (int j = 0; j < favoriteCompanies.size(); j++) {
        if (favoriteCompanies.get(j).size() > favoriteCompanies.get(i).size() && i != j) {

          if (favoriteCompanies.get(j).containsAll(favoriteCompanies.get(i))) {
            isContained = true;
            break;
          }
        }
      }
      if (!isContained) result.add(i);
    }

    return result;
  }

  @Test
  public void customSortString() {
    assertEquals("cbad", customSortString("cba", "abcd"));
    assertEquals("kqeep", customSortString("kqep", "pekeq"));
  }

  public String customSortString(String S, String T) {

    StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");

    int i = 0;

    while (i < S.length()) {
      alphabet.setCharAt(i, S.charAt(i));
      i++;
    }

    String alpha = alphabet.toString();

    Map<Character, Integer> map = new HashMap<>();
    Map<Character, Integer> countMap = new HashMap<>();

    i = 0;
    while (i < T.length()) {
      map.put(T.charAt(i), alpha.indexOf(T.charAt(i)));
      countMap.put(T.charAt(i), countMap.getOrDefault(T.charAt(i), 0) + 1);
      i++;
    }

    return map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .map(
            entry ->
                String.join(
                    "",
                    Collections.nCopies(
                        countMap.get(entry.getKey()), Character.toString(entry.getKey()))))
        .collect(Collectors.joining(""));
  }

  @Test
  public void average() {

    assertEquals(2500.00000, average(new int[] {4000, 3000, 1000, 2000}));
    assertEquals(2000.00000, average(new int[] {1000, 2000, 3000}));
    assertEquals(3500.00000, average(new int[] {6000, 5000, 4000, 3000, 2000, 1000}));
    assertEquals(4750.00000, average(new int[] {8000, 9000, 2000, 3000, 6000, 1000}));
    assertEquals(
        41111.11111,
        average(
            new int[] {
              48000, 59000, 99000, 13000, 78000, 45000, 31000, 17000, 39000, 37000, 93000, 77000,
              33000, 28000, 4000, 54000, 67000, 6000, 1000, 11000
            }));
    assertEquals(
        41700.00000,
        average(
            new int[] {
              25000, 48000, 57000, 86000, 33000, 10000, 42000, 3000, 54000, 29000, 79000, 40000
            }));
  }

  public double average(int[] salary) {

    if (salary.length == 2) return (salary[0] + salary[1]) / 2;

    int min = Integer.MAX_VALUE;
    int max = 0;

    int sum = 0;
    for (int item : salary) {
      sum += item;
      min = Math.min(item, min);
      max = Math.max(item, max);
    }

    sum = sum - min - max;
    DecimalFormat numberFormat = new DecimalFormat("#.#####");
    String value = numberFormat.format((double) sum / (salary.length - 2));
    return Double.parseDouble(value);
  }

  @Test
  public void finalPrices() {
    assertArrayEquals(new int[] {4, 2, 4, 2, 3}, finalPrices(new int[] {8, 4, 6, 2, 3}));
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, finalPrices(new int[] {1, 2, 3, 4, 5}));
    assertArrayEquals(new int[] {9, 0, 1, 6}, finalPrices(new int[] {10, 1, 1, 6}));
  }

  public int[] finalPrices(int[] prices) {

    for (int i = 0; i < prices.length - 1; i++) {
      int price = prices[i];
      int j = i + 1;

      while (j < prices.length && price < prices[j]) {
        j++;
      }

      if (j < prices.length) {
        int discount = price - prices[j];
        prices[i] = discount;
      }
    }

    return prices;
  }

  @Test
  public void canMakeArithmeticProgression() {

    assertTrue(canMakeArithmeticProgression(new int[] {3, 5, 1}));
    assertFalse(canMakeArithmeticProgression(new int[] {1, 2, 4}));
  }

  public boolean canMakeArithmeticProgression(int[] arr) {

    if (arr.length == 2) return true;

    Arrays.sort(arr);

    int value = arr[0];
    int diff = arr[1] - arr[0];

    for (int i = 1; i < arr.length; i++) {

      if ((arr[i] - value) - diff != 0) return false;

      value = arr[i];
    }

    return true;
  }

  @Test
  public void subRectangleQueries() {
    SubrectangleQueries subrectangleQueries =
        new SubrectangleQueries(new int[][] {{1, 2, 1}, {4, 3, 4}, {3, 2, 1}, {1, 1, 1}});

    assertEquals(1, subrectangleQueries.getValue(0, 2));
    subrectangleQueries.updateSubrectangle(0, 0, 3, 2, 5);
    assertEquals(5, subrectangleQueries.getValue(0, 2));
    assertEquals(5, subrectangleQueries.getValue(3, 1));
    subrectangleQueries.updateSubrectangle(3, 0, 3, 2, 10);
    assertEquals(10, subrectangleQueries.getValue(3, 1));
    assertEquals(5, subrectangleQueries.getValue(0, 2));
  }

  @Test
  public void maxProductSubArray() {
    assertEquals(6, maxProductSubArray(new int[] {2, 3, -2, 4}));
    assertEquals(0, maxProductSubArray(new int[] {-2, 0, -1}));
    assertEquals(-2, maxProductSubArray(new int[] {-2}));
    assertEquals(2, maxProductSubArray(new int[] {0, 2}));
    assertEquals(24, maxProductSubArray(new int[] {-2, 3, -4}));
    assertEquals(24, maxProductSubArray(new int[] {2, -5, -2, -4, 3}));
  }

  private int maxProductSubArray(int[] nums) {

    if (null == nums || nums.length == 0) return 0;

    int maxProduct = nums[0];

    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];

      for (int j = i + 1; j < nums.length; j++) {

        maxProduct = Math.max(maxProduct, Math.max(value * nums[j], nums[j]));

        value = value * nums[j];
      }
    }

    return maxProduct;
  }

  @Test
  public void maximumProduct() {
    assertEquals(6, maximumProduct(new int[] {1, 2, 3}));
    assertEquals(24, maximumProduct(new int[] {1, 2, 3, 4}));
    assertEquals(720, maximumProduct(new int[] {-4, -3, -2, -1, 60}));
  }

  public int maximumProduct(int[] nums) {
    if (nums.length == 3) return nums[0] * nums[1] * nums[2];

    Arrays.sort(nums);

    return Math.max(
        Math.max(
            (nums[0] * nums[1] * nums[2]),
            (nums[nums.length - 3] * nums[nums.length - 2] * nums[nums.length - 1])),
        (nums[0] * nums[1] * nums[nums.length - 1]));
  }

  @Test
  public void busyStudent() {
    assertEquals(1, busyStudent(new int[] {1, 2, 3}, new int[] {3, 2, 7}, 4));
    assertEquals(1, busyStudent(new int[] {4}, new int[] {4}, 4));
    assertEquals(0, busyStudent(new int[] {4}, new int[] {4}, 5));
    assertEquals(0, busyStudent(new int[] {1, 1, 1, 1}, new int[] {1, 3, 2, 4}, 0));
    assertEquals(
        5,
        busyStudent(
            new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1},
            new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10},
            5));
  }

  public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
    int count = 0;

    for (int i = 0; i < startTime.length; i++) {
      if (startTime[i] <= queryTime) if (queryTime <= endTime[i]) count++;
    }

    return count;
  }

  @Test
  public void maxProduct() {
    assertEquals(12, maxProduct(new int[] {3, 4, 5, 2}));
    assertEquals(16, maxProduct(new int[] {1, 5, 4, 5}));
    assertEquals(12, maxProduct(new int[] {3, 7}));
  }

  public int maxProduct(int[] nums) {

    Arrays.sort(nums);
    return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
  }

  @Test
  public void kidsWithCandies() {
    assertEquals(
        Arrays.asList(true, true, true, false, true),
        kidsWithCandies(new int[] {2, 3, 5, 1, 3}, 3));

    assertEquals(
        Arrays.asList(true, false, false, false, false),
        kidsWithCandies(new int[] {4, 2, 1, 1, 2}, 1));

    assertEquals(Arrays.asList(true, false, true), kidsWithCandies(new int[] {12, 1, 12}, 10));
  }

  public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

    List<Boolean> result = new LinkedList<>();

    int max = candies[0];

    for (int i = 1; i < candies.length; i++) {
      if (candies[i] > max) max = candies[i];
    }

    for (int candy : candies) {
      result.add(candy + extraCandies >= max);
    }

    return result;
  }

  @Test
  public void isToeplitzMatrix() {

    assertTrue(
        isToeplitzMatrix(
            new int[][] {
              {1, 2, 3, 4},
              {5, 1, 2, 3},
              {9, 5, 1, 2}
            }));

    assertFalse(
        isToeplitzMatrix(
            new int[][] {
              {1, 2},
              {1, 2}
            }));

    assertFalse(
        isToeplitzMatrix(
            new int[][] {
              {44, 35, 39}, {15, 44, 35}, {17, 15, 44}, {80, 17, 15}, {43, 80, 0}, {77, 43, 80}
            }));
  }

  private boolean isToeplitzMatrix(int[][] matrix) {

    int rows = 0;
    int cols = matrix[0].length - 1;

    while (rows < matrix.length && cols >= 0) {
      int rowsTmp = rows;
      int colsTmp = cols;

      int value = matrix[rowsTmp][colsTmp];
      while (--rowsTmp >= 0 && --colsTmp >= 0) {
        if (value - matrix[rowsTmp][colsTmp] != 0) return false;
      }

      if (rows == matrix.length - 1) {
        rows = matrix.length - 1;
        cols--;
      } else rows++;
    }

    return true;
  }

  @Test
  public void minSetSize() {
    assertEquals(2, minSetSize(new int[] {3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
    assertEquals(1, minSetSize(new int[] {7, 7, 7, 7, 7, 7}));
    assertEquals(1, minSetSize(new int[] {1, 9}));
    assertEquals(1, minSetSize(new int[] {1000, 1000, 3, 7}));
    assertEquals(5, minSetSize(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 107}));
    assertEquals(0, minSetSize(new int[] {}));
  }

  private int minSetSize(int[] arr) {

    if (arr == null || arr.length == 0) return 0;

    Arrays.sort(arr);

    PriorityQueue<IntObj> queue = new PriorityQueue<>();

    int value = arr[0];
    int freq = 1;

    for (int i = 1; i < arr.length; i++) {

      if (arr[i] - value == 0) {
        freq++;
        continue;
      }

      queue.add(new IntObj(value, freq));
      freq = 1;
      value = arr[i];
    }

    queue.add(new IntObj(value, freq));

    int arrCheck = arr.length;
    int setCount = 0;

    while (!queue.isEmpty()) {
      IntObj intObj = queue.poll();

      setCount++;
      arrCheck -= intObj.freq;

      if (arrCheck <= arr.length / 2) break;
    }

    return setCount;
  }

  @Test
  public void runningSum() {
    assertArrayEquals(new int[] {1, 3, 6, 10}, runningSum(new int[] {1, 2, 3, 4}));
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, runningSum(new int[] {1, 1, 1, 1, 1}));
    assertArrayEquals(new int[] {3, 4, 6, 16, 17}, runningSum(new int[] {3, 1, 2, 10, 1}));
    assertArrayEquals(new int[] {1}, runningSum(new int[] {1}));
  }

  private int[] runningSum(int[] nums) {

    for (int i = 1; i < nums.length; i++) {
      nums[i] = nums[i] + nums[i - 1];
    }

    return nums;
  }

  @Test
  public void reorderLogFiles() {
    assertArrayEquals(
        new String[] {
          "let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"
        },
        reorderLogFiles(
            new String[] {
              "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"
            }));

    assertArrayEquals(
        new String[] {
          "a2 act car", "g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"
        },
        reorderLogFiles(
            new String[] {
              "a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"
            }));

    assertArrayEquals(
        new String[] {"t kvr", "7 so", "r 3 1", "i 403", "t 54"},
        reorderLogFiles(new String[] {"t kvr", "r 3 1", "i 403", "7 so", "t 54"}));

    assertArrayEquals(
        new String[] {"g1 Act car", "a8 act zoo", "ab1 off KEY dog", "a1 9 2 3 1", "zo4 4 7"},
        reorderLogFiles(
            new String[] {"a1 9 2 3 1", "g1 Act car", "zo4 4 7", "ab1 off KEY dog", "a8 act zoo"}));
  }

  private String[] reorderLogFiles(String[] logs) {

    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    Arrays.sort(
        logs,
        (o1, o2) -> {
          String[] split = o1.split(" ");
          String[] split1 = o2.split(" ");

          boolean o1Matches = pattern.matcher(split[1]).matches();
          boolean o2Matches = pattern.matcher(split1[1]).matches();

          if (!o1Matches && !o2Matches) {
            String patho1 = o1.substring(split[0].length() + 1);
            String patho2 = o2.substring(split1[0].length() + 1);

            if (patho1.compareTo(patho2) == 0) return split[0].compareTo(split1[0]);

            return patho1.compareTo(patho2);
          }

          if (o1Matches && o2Matches) return 0;

          if (!o1Matches) return -1;

          return 1;
        });

    return logs;
  }

  @Test
  public void findDuplicates() {

    assertArrayEquals(
        new int[] {2, 3},
        findDuplicates(new int[] {4, 3, 2, 7, 8, 2, 3, 1}).stream().mapToInt(i -> i).toArray());
  }

  private List<Integer> findDuplicates(int[] nums) {

    Set<Integer> sol = new HashSet<>();
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (!sol.add(nums[i])) list.add(nums[i]);
    }

    return list;
  }

  @Test
  public void canBeEqual() {
    assertTrue(canBeEqual(new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}));
  }

  private boolean canBeEqual(int[] target, int[] arr) {
    Arrays.sort(target);
    Arrays.sort(arr);

    return Arrays.equals(target, arr);
  }

  @Test
  public void luckyNumbers() {

    assertEquals(
        Collections.singletonList(15),
        luckyNumbers(new int[][] {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}}));

    assertEquals(
        Collections.singletonList(12),
        luckyNumbers(new int[][] {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}}));
  }

  private List<Integer> luckyNumbers(int[][] matrix) {

    int length = matrix.length;
    int rowLentgh = matrix[0].length;

    int[] maxInColumns = new int[matrix[0].length];
    int[] minInRows = new int[matrix.length];

    for (int i = 0; i < length; i++) {

      int j = 0;
      int minInRow = Integer.MAX_VALUE;

      while (j < rowLentgh) {
        minInRow = Math.min(minInRow, matrix[i][j++]);
      }

      minInRows[i] = minInRow;
    }

    for (int i = 0; i < rowLentgh; i++) {

      int j = 0;
      int maxInCol = 0;

      while (j < length) {
        maxInCol = Math.max(maxInCol, matrix[j++][i]);
      }

      maxInColumns[i] = maxInCol;
    }

    LinkedList<Integer> sol = new LinkedList<>();

    int col = 0;

    while (col < rowLentgh) {

      int row = 0;

      while (row < length) {

        if (maxInColumns[col] - minInRows[row] == 0) sol.add(maxInColumns[col]);

        row++;
      }
      col++;
    }

    return sol;
  }

  @Test
  public void shuffle() {
    assertArrayEquals(new int[] {2, 3, 5, 4, 1, 7}, shuffle(new int[] {2, 5, 1, 3, 4, 7}, 3));
    assertArrayEquals(
        new int[] {1, 4, 2, 3, 3, 2, 4, 1}, shuffle(new int[] {1, 2, 3, 4, 4, 3, 2, 1}, 4));
    assertArrayEquals(new int[] {1, 2, 1, 2}, shuffle(new int[] {1, 1, 2, 2}, 2));
  }

  private int[] shuffle(int[] nums, int n) {

    if (nums.length == 0) return new int[] {};

    int[] clone = nums.clone();

    int start = 0;
    int middle = n;
    int pointer = 0;

    while (start < n) {

      nums[pointer++] = clone[start++];
      nums[pointer++] = clone[middle++];
    }

    return nums;
  }

  @Test
  public void numEquivDominoPairs() {
    assertEquals(1, numEquivDominoPairs(new int[][] {{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
    assertEquals(3, numEquivDominoPairs(new int[][] {{1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}}));
  }

  private int numEquivDominoPairs(int[][] dominoes) {

    Map<Integer, Map<Integer, Integer>> dominoesMap = new HashMap<Integer, Map<Integer, Integer>>();

    int count = 0;

    for (int[] dominoe : dominoes) {}

    return count;
  }

  @Test
  public void isAlienSorted() {
    assertTrue(isAlienSorted(new String[] {"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    assertFalse(isAlienSorted(new String[] {"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    assertFalse(isAlienSorted(new String[] {"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
  }

  private boolean isAlienSorted(String[] words, String order) {

    int i = 0;

    while (i + 1 < words.length) {
      String word1 = words[i];
      String word2 = words[i + 1];

      int length = Math.min(word1.length(), word2.length());

      int j = 0;

      while (j < length) {
        if (order.indexOf(word1.charAt(j)) > order.indexOf(word2.charAt(j))) return false;
        else if (order.indexOf(word1.charAt(j)) < order.indexOf(word2.charAt(j))) break;

        j++;
      }

      if (j == length && word1.length() > word2.length()) return false;

      i++;
    }

    return true;
  }

  @Test
  public void shortestCompletingWord() {
    assertEquals(
        "steps",
        shortestCompletingWord("1s3 PSt", new String[] {"step", "steps", "stripe", "stepple"}));
    assertEquals(
        "pest", shortestCompletingWord("1s3 456", new String[] {"looks", "pest", "stew", "show"}));
  }

  public String shortestCompletingWord(String licensePlate, String[] words) {

    String word = null;
    Map<Character, Integer> map = new HashMap<>();
    int licensPlateWordLength = 0;

    for (char s : licensePlate.toCharArray()) {
      if ((s >= 65 && s <= 90) || (s >= 97 && s <= 122)) {
        licensPlateWordLength++;
        char lower = Character.toLowerCase(s);
        map.put(lower, map.getOrDefault(lower, 0) + 1);
      }
    }

    Set<Character> seen = new HashSet<>();
    int countChars;

    for (String s : words) {
      countChars = 0;
      for (char c : s.toCharArray()) {
        if (seen.add(c)) {
          long count = s.chars().filter(ch -> ch == c).count();

          int mapCount = map.getOrDefault(c, 0);

          if (mapCount != 0 && mapCount <= count) countChars += mapCount;
        }
      }

      if (countChars >= licensPlateWordLength)
        if (word == null) {
          word = s;
        } else {
          if (s.length() < word.length()) word = s;
        }

      seen = new HashSet<>();
    }

    return word;
  }

  @Test
  public void maxNumberOfBalloons() {
    assertEquals(1, maxNumberOfBalloons("nlaebolko"));
    assertEquals(2, maxNumberOfBalloons("loonbalxballpoon"));
    assertEquals(
        98,
        maxNumberOfBalloons(
            "rrnlnfevwkvhqzathacmhyhonsvcwmmmehcgchzfjvfoxbnagtxwnwcjcbmbgexqhlefjyrijwjolebtodxtapgyrzmgqzexvugtermrktyjbaicmskxmdopmtoxayydqjjogxmmmpvfiajknjfsikgrwsejzgnbsblsjkkcbaunqthiaetvxlylylzjqkvgysxfoltcmxwrtwpkvhofoaxdugfnsuhepfxudwllyiyrszrpwkwlvpsspvgcimcrngfllsjjdppihhczogfwegibuzzacojtruvozbqwikgdjeqxyarjwaawzictwpdruufifgbqzatmkkdnjajnklnzfdxcsjpmebjqpuqqamoexnnqxccngljzhnhbroyoaphgmksefhizibzpgehvtujmjdhskvpqicxbziqifjqqkaowileochjqhrccajnthqeqgwazedgehdtkwchxjjzcyjimrkykcvkfbzqiusgkxdilnfislphmeliqspvhuichuusqxnutiyxvdtgfnnrrkjwjmkjqjradnocssmfefgfgtzpbnlzxardrrrttnnqttnsowwrqswyqxhsxtlnfxogulwqhkowniohtznlyfjqlzvowcvqxynjpiyiggnxgtmyhjcbehxxsgltojszqyfpnsiybksmozwsqfxpvgeyoyeccxqlcziikavapxyjcftxhfimabmquzkyxnnablhpikwbwwmpjdtmukmuvcfkraqcbopufsaigskwgpjdubbympilgaojuvzgapairttwzuexbsigzcgerblgdgfwkiiqvokbmbtdzkakkeahkgjuyepvtbwqgerztvgunzhgitwottmejilmwyzwczjoxckyjtcbfsryofffaugsketifypahgqgmeypsyjvftqynbzhrobiwouuykyczskcdedidptykluykypnsfuudqgpqzwextbbumiuymngczkfrowkzxxtggqlwlaohfkgfmbyyaboapwhhopewnchlmvifpdkeovpjyugautultdbbxkjxqbcamrpqhndfeybwoatrjwvroakmqapknzzumapytdsdhhlstojlnlvpqjxlpbwfkdupgxzajyjrutzeljnmyxkcdrcjsxfmbqhzblnlqgsqzqjtiaelfkuoyjcjdlpvajkkgyuabgiwlybwmaqdtireukwieifvpzptnebjtwcvmhltkotrvkxcbbsehkdkiegzzbtszfdslqnecluxilqqgqwotfqqoogzzsyfdaujymrjfeoiqpzdeispqsnvlocqdyjcthtxlerjgowzfwsqgourkdhyfaaexjwnfppvrwhdbtsxqqqlrzvkergkxiqzlwdfomyreafirkaycmvjmwnnaqexoewwymvgsrajurzfiohzsbrehhnfghanczevwavsjjslwmnljlmixepwiwalqatgwfzzqhqvrrrhxleuzffgjuihnlbeukyapalwlfjtpumiexkwnvdxiikfajsbxcaywwjjqmgtzrihaoebjduvsmyfvjcthmbjjcausloupnqwjcncosrinvnspqwzlsrivpbpmgsoevunzthofjxspcgordbpudedcfdbbqvymtwujzdtcvigwuofoowqusrsavwkoyabsvllwpqidbpelffmrzfwdhaypxzwofssgkcqobnuomcejbuolmashglsiixcogolmgcmtauauhupkcqdhwesdmpnelcpvnyipakzlsbrpjppayzsremllqnmizvqcphkyuvwrqphdmgiwousanjuazlocddvgduoqmvfovidicmjufaytymcvoonvndbuuljgdvalsnypemzbwovgclcdfwcjptsbelifrqocsuwiwsyzrcxwuweiehtiegogvawkjogwtftbwabpcufuepabxxiqngntudhhuxrvhmhunepamdtfavtuajmrfvgirquwginxtjcmxgmstmhkqncpkdicemhcofqcbceiukstmjzsavwuhgpwbsymupyldwtsbvdjdevvgtddvqqcibnbseqgiaputwgapmovminivrbzlerjwsiofgiihyooqyjchcctvjfebhpvwncqhbleluycybaqlxikorlkqxwzmtrbgpwiqqormjftiarumgxeasqeusnewjqedzhiibbfurgbuwmxfycaevtijtttcbzvgumgiimwzjzpwhuqqwotjrlayrqwcwltiivumafhucrnzjqddvvtrxjsupfxlrohxmytridseyiajntpjsdrbdhdrtnackpytfuzkeiwgukwhvtvehvrdldfugtlwksywnuygichqnjbsmsupsowajduoukduoulagskxvxskewsyhsgakjyqeconciayfwqwdwdnilmllyuwqmatjzxyuhvujrcwkdybdmszzoxwzxojimlfqldxaiqgvziifuobnhonlafoulxgcorcclsxkrwrxinsadpgfmunwjdwgsiepbclggbqpxfjdxqseqidtgrqkixxwmblwokkupjqavvniwjyfuobcbhqfymrayevkteoaturjyvpbjaawgesxnwdvshiiijylclbqwjavftdmrrdsyhqcjweptywnqpcnzucqnzyyptaosrhvfzdomojkoeyepbpbfupkrnyzftjwpdgfqmwhjnhatsedirwzcpeppusaekjsbevlrzyxqtfgcnpszwxhgaeincudnfmcqawcnfkawjrguhitkqtxcxcvojgudfsrezcpfcntnezvskggzhisksxsehgzqxrrqtdlhjhgrblgnbfulmfawtfwnsrjixrjbwwxkvppjdtydgrsnmcpwcudxoiunilylpivbicvxwijrcxhflkznmskrbnighehsyrrzxazwyyvxgffmheubmogkdqtbeyoffgpuathllbiublcvkhzhogbspoogzobbbkylptbwxrbnbhevugkufrbrsgijcvylqeoucsomarwklmmztirevahocqoewrxojvfrakuevheeioirhgttufsxwfymwgxebtazonolntbhfwwgpmoluhnnzcwfhjoxthbuihecsxlfnexaghbaiawxfcsrznockvfoaemmizfltiebkoygchtspwcmijtjvrzljaovsbprmcsgwkqpvjtucemnpxxzzhpxvydfxsoyypecppdwwusbrgysxdaccafafoslqfcohtlgqcuojqwilxbsxrogyfcsqbxdqvdvpnjdeqmrcriczwviehftrhuwrguzrhukemyzeeworelrjwosskahsiazkpwvgscbihgnqtcoicapcsbvngvqgkqgvfuyhcoabpgrdqthxbalglvvrxifdzocgnweutsgrhmiuhoitrmccscophlcmarepzlisqdzidhocliidjikgzsmbjasukprfvlpkgryoyjtmuknjcmphkzhipocykitztqihhsjmxbrwefwgbdkdillzkosondaevcfyeikdfhgnughasibklabfwxkpxjpfwbjlczyusecvbyzlnxbhodtqpacvdgzzffxegivtemuswsmgiieldivzbildwucddnizmyofqtymmikpkzwohivqnipnwrdjatiyvlnrtwkrcrifmxvofolrbbhvyigrlsldqgbivahebsttymezbixrheczduzsqidjxfqorcxqssmcpnphnprpwlkyofnamfmezxoivrtxoxqralelxegltcpsleogdotrdgwxaspyghklhklbxsvfwjkbdjikdtzyhoufeqnqcmetpvvilyakjxmsoescrvivbslvdpunwhuylsgswgvfxfptnrskmsyiezttvwjwilhqdnpvayggvwepizpmjwhsellrypgtujbmqmpnngkwkgwrasmnomqhukusjadlpgpbhpnefhjippzhcdasuhauthuqbvvmndggmlckpjuuopsyrwtwkdtkwplrgfrgunfogevjqxqsoryjwmahmqhlgbyfqfgcfanjkerbirdilapihcrquviryewnphzwhsprelcnlgsczxonivmzknffspyeodsyxtkjcnljwfldvqwqndjcpxzxhjnsjuoedkrbmcsyyfhhjbvekmnfpxwchganhcmxbtoaofbbfvfewpzqrpgindhcqutdkyrtufdnsxxhachxvvfokczlrcdvygxusslaxllcspnbrtblgjlzmkxfpgeuepetuzesccdskwfunxaehfxsqopqjplukuivautozxtlujwxoijihzueabboyxvbtaangqzokdzwhbxodlfmryhikccbdprouslrgktmdjimvwshpbzvdmbtyyggnsamppcasbmdxlbsoosftfmamcmpvaojyfbwajwkodaokljngsknpklkticblmghnihdmezdyvriohgvvonutlmcfavvqndjnsskhamflpipuvusyqyzwlfrlestfppascaszytzagpcrwgeswhzuquurnytvrzsoyuqvjpwbmyksxueizewjmxnncniuddtmkpqhrzonwahjgmjxwihyalelbnwplqqhdgyrhcrnpdslxbdjhlmbvbvkwhjivspoejwtviwujcnuqjkskfpdnefruoyziynqcivjvlvaojhpcncbspuatgsyizklwfdbhwzbrbmxcyfxiwuwsorckrtdazaorauxmecsoywytrhuwqrobxndyfvwqukccpidvmecwuhdnqfsahqoxtswfdzzaystjmikdumrznihaakatobdrmjmahgefjomhywqfuiquafulpybnrvcfptttjaohipdrgbtvdzgxsakfvvoumdlalajlpusskjwlweufcjzpzhdcssqlkkrhaonmzxqtulrpqhubbgbagwyzpizbjegvjtqbkaiqididqvnbsknxegvkcfxdlljqs"));
  }

  private int maxNumberOfBalloons(String text) {

    String alphaBet = "abcdefghijklmnopqrstuvwxyz";
    int[] chars = new int[26];

    for (char c : text.toCharArray()) {
      int index = alphaBet.indexOf(c);
      chars[index] += 1;
    }

    int b = alphaBet.indexOf('b');
    int a = alphaBet.indexOf('a');
    int l = alphaBet.indexOf('l');
    int o = alphaBet.indexOf('o');
    int n = alphaBet.indexOf('n');

    int count = 0;

    while (chars[b] >= 1 && chars[a] >= 1 && chars[l] >= 2 && chars[o] >= 2 && chars[n] >= 1) {
      count++;
      chars[b] = --chars[b];
      chars[a] = --chars[a];
      chars[l] = --chars[l];
      chars[l] = --chars[l];
      chars[o] = --chars[o];
      chars[o] = --chars[o];
      chars[n] = --chars[n];
    }

    return count;
  }

  @Test
  public void floodFill() {

    assertArrayEquals(
        new int[][] {{0, 0, 0}, {0, 1, 1}}, floodFill(new int[][] {{0, 0, 0}, {0, 1, 1}}, 1, 1, 1));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 2}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 2, 2, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 0, 0, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 1}, {1, 1, 2}, {1, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 2, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 1}}, floodFill(new int[][] {{0, 0, 0}, {0, 0, 1}}, 1, 0, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 1, 2}}, floodFill(new int[][] {{0, 0, 0}, {0, 1, 0}}, 0, 0, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 0}, {1, 1, 1}}, floodFill(new int[][] {{0, 1, 0}, {0, 0, 1}}, 1, 1, 1));

    assertArrayEquals(
        new int[][] {
          {8, 4, 5, 3, 2, 2, 2, 6, 9, 5},
          {3, 1, 3, 7, 4, 8, 9, 0, 1, 6},
          {0, 7, 1, 7, 5, 2, 2, 8, 6, 9},
          {9, 1, 0, 5, 8, 8, 0, 5, 5, 9},
          {3, 4, 9, 5, 4, 8, 0, 2, 7, 5},
          {5, 1, 1, 8, 1, 6, 5, 9, 7, 8},
          {9, 7, 9, 4, 5, 3, 4, 1, 8, 2},
          {5, 1, 6, 3, 5, 4, 8, 9, 0, 8},
          {9, 9, 6, 1, 0, 8, 0, 6, 9, 9},
          {4, 1, 8, 7, 3, 6, 9, 6, 6, 1}
        },
        floodFill(
            new int[][] {
              {8, 4, 5, 3, 2, 2, 2, 6, 9, 5},
              {3, 1, 3, 7, 4, 8, 9, 0, 1, 6},
              {0, 7, 1, 7, 5, 2, 2, 8, 6, 9},
              {9, 1, 0, 5, 8, 8, 0, 5, 5, 9},
              {3, 4, 9, 5, 4, 8, 0, 2, 7, 5},
              {5, 1, 1, 8, 1, 6, 5, 9, 7, 8},
              {9, 7, 9, 4, 5, 3, 4, 1, 8, 2},
              {5, 1, 6, 3, 5, 4, 8, 9, 0, 8},
              {8, 8, 6, 1, 0, 8, 0, 6, 9, 9},
              {4, 1, 8, 7, 3, 6, 9, 6, 6, 1}
            },
            8,
            0,
            9));
  }

  private int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

    if (image[sr][sc] == newColor) return image;

    int oldColor = image[sr][sc];
    image[sr][sc] = newColor;

    floodFill(image, sr, sc, newColor, oldColor);

    return image;
  }

  private void floodFill(int[][] image, int sr, int sc, int newColor, int oldColor) {

    if (sr >= 0 && sr < image.length && sc >= 0 && sc < image[sr].length) {

      // check directions
      if (sr + 1 < image.length && checkPoint(image, sc, oldColor, newColor, sr + 1))
        floodFill(image, sr + 1, sc, newColor, oldColor);

      if (sr - 1 >= 0 && checkPoint(image, sc, oldColor, newColor, sr - 1))
        floodFill(image, sr - 1, sc, newColor, oldColor);

      if (sc + 1 < image[sr].length && checkPoint(image, sc + 1, oldColor, newColor, sr))
        floodFill(image, sr, sc + 1, newColor, oldColor);

      if (sc - 1 >= 0 && checkPoint(image, sc - 1, oldColor, newColor, sr))
        floodFill(image, sr, sc - 1, newColor, oldColor);
    }
  }

  private boolean checkPoint(int[][] image, int sc, int oldColor, int newColor, int sr) {

    if (image[sr][sc] - oldColor != 0) return false;

    image[sr][sc] = newColor;

    return true;
  }

  @Test
  public void islandPerimeter() {
    assertEquals(
        16, islandPerimeter(new int[][] {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}));

    assertEquals(
        4, islandPerimeter(new int[][] {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));

    assertEquals(
        8, islandPerimeter(new int[][] {{1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
  }

  private int islandPerimeter(int[][] grid) {

    int sol = 0;

    for (int i = 0; i < grid.length; i++) {
      int[] arr = grid[i];

      for (int j = 0; j < arr.length; j++) {

        if (arr[j] == 1) {
          sol += 4;

          if (j - 1 >= 0) {
            if (arr[j - 1] == 1) sol -= 1;
          }

          if (j + 1 < arr.length) {
            if (arr[j + 1] == 1) sol -= 1;
          }

          if (i + 1 < grid.length) {
            if (grid[i + 1][j] == 1) sol -= 1;
          }

          if (i - 1 >= 0) {
            if (grid[i - 1][j] == 1) sol -= 1;
          }
        }
      }
    }

    return sol;
  }

  @Test
  public void longestWord() {
    assertEquals(
        "apple", longestWord(new String[] {"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    assertEquals("world", longestWord(new String[] {"w", "wo", "wor", "worl", "world"}));
    assertEquals(
        "breakfast",
        longestWord(
            new String[] {
              "b",
              "br",
              "bre",
              "brea",
              "break",
              "breakf",
              "breakfa",
              "breakfas",
              "breakfast",
              "l",
              "lu",
              "lun",
              "lunc",
              "lunch",
              "d",
              "di",
              "din",
              "dinn",
              "dinne",
              "dinner"
            }));
    assertEquals(
        "yodn",
        longestWord(
            new String[] {
              "yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod",
              "ewqz", "y"
            }));
    assertEquals(
        "eyj",
        longestWord(
            new String[] {
              "ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq",
              "ogznk", "og", "eyjuoi", "d"
            }));
    assertEquals(
        "otif",
        longestWord(
            new String[] {
              "rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy",
              "onpd"
            }));

    assertEquals(
        "otif",
        longestWord1(
            new String[] {
              "rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy",
              "onpd"
            }));
  }

  private String longestWord(String[] words) {

    Arrays.parallelSort(words);

    Set<String> seenSeed = new HashSet<>();

    String word = "";
    for (String s : words) {
      if (s.length() == 1) {
        seenSeed.add(s);
        word = checkWord(word, s);
        continue;
      }

      if (seenSeed.contains(s.substring(0, s.length() - 1))) {
        seenSeed.add(s);

        word = checkWord(word, s);
      }
    }

    return word;
  }

  private String checkWord(String word, String s) {
    if (s.length() - word.length() > 0) word = s;
    else if (s.length() - word.length() == 0) {
      if (s.compareTo(word) < 0) word = s;
    }
    return word;
  }

  private String longestWord1(String[] words) {
    Trie trie = new Trie();
    int index = 0;
    for (String word : words) {
      trie.insert(word, ++index); // indexed by 1
    }
    trie.words = words;
    return trie.dfs();
  }

  class Node {
    char c;
    Map<Character, Node> children = new HashMap<>();
    int end;

    public Node(char c) {
      this.c = c;
    }
  }

  class Trie {
    Node root;
    String[] words;

    public Trie() {
      root = new Node('0');
    }

    public void insert(String word, int index) {
      Node cur = root;
      for (char c : word.toCharArray()) {
        cur.children.putIfAbsent(c, new Node(c));
        cur = cur.children.get(c);
      }
      cur.end = index;
    }

    public String dfs() {
      String ans = "";
      Stack<Node> stack = new Stack<>();
      stack.push(root);
      while (!stack.empty()) {
        Node node = stack.pop();
        if (node.end > 0 || node == root) {
          if (node != root) {
            String word = words[node.end - 1];
            if (word.length() > ans.length()
                || word.length() == ans.length() && word.compareTo(ans) < 0) {
              ans = word;
            }
          }
          for (Node nei : node.children.values()) {
            stack.push(nei);
          }
        }
      }
      return ans;
    }
  }

  @Test
  public void repeatedNTimes() {

    assertEquals(3, repeatedNTimes(new int[] {1, 2, 3, 3}));
    assertEquals(2, repeatedNTimes(new int[] {2, 1, 2, 5, 3, 2}));
    assertEquals(5, repeatedNTimes(new int[] {5, 1, 5, 2, 5, 3, 5, 4}));
  }

  private int repeatedNTimes(int[] A) {

    int half = A.length / 2;

    int[] freq = new int[10001];

    for (int i : A) {
      int frequency = ++freq[i];
      if (frequency - half == 0) return i;
    }

    return -1;
  }

  @Test
  public void findOcurrences() {
    assertArrayEquals(
        new String[] {"girl", "student"},
        findOcurrences("alice is a good girl she is a good student", "a", "good"));
    assertArrayEquals(
        new String[] {"we", "rock"}, findOcurrences("we will we will rock you", "we", "will"));
    assertArrayEquals(
        new String[] {"kcyxdfnoa", "kcyxdfnoa", "kcyxdfnoa"},
        findOcurrences(
            "jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa",
            "kcyxdfnoa",
            "jkypmsxd"));
  }

  private String[] findOcurrences(String text, String first, String second) {

    String[] split = text.split(" ");

    LinkedList<String> list = new LinkedList<>();

    for (int position = 0; position < split.length; position++) {
      if (position - 1 >= 0
          && split[position].equals(second)
          && position + 1 < split.length
          && split[position - 1].equals(first)) list.add(split[position + 1]);
    }

    return list.stream().toArray(String[]::new);
  }

  @Test
  public void dailyTemperatures() {
    assertArrayEquals(
        new int[] {1, 1, 4, 2, 1, 1, 0, 0},
        dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
    assertArrayEquals(
        new int[] {8, 1, 5, 4, 3, 2, 1, 1, 0, 0},
        dailyTemperatures(new int[] {89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
  }

  private int[] dailyTemperatures(int[] T) {

    int[] positions = new int[101];

    int[] sol = new int[T.length];
    sol[T.length - 1] = 0;
    int i = T.length - 1;
    positions[T[i]] = i;

    while (--i >= 0) {

      int value = T[i];

      int position = value;

      int result = Integer.MAX_VALUE;

      while (++position < 101) {
        if (positions[position] > i) result = Math.min(result, positions[position] - i);
      }

      sol[i] = result == Integer.MAX_VALUE ? 0 : result;
      positions[value] = i;
    }

    return sol;
  }

  @Test
  public void oddCells() {
    assertEquals(6, oddCells(2, 3, new int[][] {{0, 1}, {1, 1}}));
    assertEquals(0, oddCells(2, 2, new int[][] {{1, 1}, {0, 0}}));
  }

  private int oddCells(int n, int m, int[][] indices) {

    int[][] matrix = new int[n][m];
    int sol = 0;
    for (int[] ints : indices) {

      int[] row = matrix[ints[0]];
      for (int i = 0; i < row.length; i++) {
        int prev = row[i];
        row[i] = prev + 1;

        if ((row[i] & 1) != 0) sol++;

        if (prev != 0 && (prev & 1) == 1) {
          sol--;
        }
      }

      int column = ints[1];

      for (int[] rows : matrix) {
        int prev = rows[column];
        rows[column] = prev + 1;

        if ((rows[column] & 1) != 0) sol++;
        if (prev != 0 && (prev & 1) == 1) {
          sol--;
        }
      }
    }

    return sol;
  }

  private final String[] daysOfWeek =
      new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

  @Test
  public void dayOfTheWeek() {
    assertEquals(daysOfWeek[6], dayOfTheWeek(31, 8, 2019));
    assertEquals(daysOfWeek[0], dayOfTheWeek(18, 7, 1999));
    assertEquals(daysOfWeek[0], dayOfTheWeek(15, 8, 1993));
  }

  private String dayOfTheWeek(int day, int month, int year) {

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day);

    return daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1];
  }

  @Test
  public void heightChecker() {

    assertEquals(3, heightChecker(new int[] {1, 1, 4, 2, 1, 3}));
    assertEquals(5, heightChecker(new int[] {5, 1, 2, 3, 4}));
  }

  private int heightChecker(int[] heights) {

    int[] clone = heights.clone();

    Arrays.parallelSort(heights);

    int count = 0;

    for (int i = 0; i < clone.length; i++) {
      if (clone[i] != heights[i]) count++;
    }

    return count;
  }

  @Test
  public void relativeSortArray() {
    assertArrayEquals(
        new int[] {2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19},
        relativeSortArray(
            new int[] {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[] {2, 1, 4, 3, 9, 6}));
    assertArrayEquals(
        new int[] {22, 28, 8, 6, 17, 44},
        relativeSortArray(new int[] {28, 6, 22, 8, 44, 17}, new int[] {22, 28, 8, 6}));
    assertArrayEquals(
        new int[] {21, 11, 26, 20, 1, 18, 34, 50},
        relativeSortArray(new int[] {26, 21, 11, 20, 50, 34, 1, 18}, new int[] {21, 11, 26, 20}));
  }

  private int[] relativeSortArray(int[] arr1, int[] arr2) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < arr2.length; i++) {
      map.put(arr2[i], i);
    }

    arr1 =
        Arrays.stream(arr1)
            .boxed()
            .sorted(
                (o1, o2) -> {
                  Integer left = map.get(o1);
                  Integer right = map.get(o2);

                  if (null != left && null != right) return left.compareTo(right);

                  if (null != left) return -1;

                  if (null != right) return 1;

                  return o1.compareTo(o2);
                })
            .mapToInt(i -> i)
            .toArray();

    return arr1;
  }

  @Test
  public void countCharacters() {

    assertEquals(6, countCharacters(new String[] {"cat", "bt", "hat", "tree"}, "atach"));
    assertEquals(
        10, countCharacters(new String[] {"hello", "world", "leetcode"}, "welldonehoneyr"));
  }

  private int countCharacters(String[] words, String chars) {

    char[] letters = chars.toCharArray();
    int[] frequency = new int[128];

    for (char letter : letters) {
      frequency[letter] = ++frequency[letter];
    }

    int sol = 0;

    for (String word : words) {
      int[] clone = frequency.clone();
      boolean isOk = true;

      for (char c : word.toCharArray()) {
        if (clone[c] > 0) clone[c] = --clone[c];
        else {
          isOk = false;
          break;
        }
      }

      if (isOk) sol += word.length();
    }

    return sol;
  }

  @Test
  public void numRookCaptures() {

    assertEquals(
        3,
        numRookCaptures(
            new char[][] {
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', 'p', '.', '.', '.', '.'},
              {'.', '.', '.', 'R', '.', '.', '.', 'p'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', 'p', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'}
            }));

    assertEquals(
        0,
        numRookCaptures(
            new char[][] {
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
              {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
              {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
              {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
              {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'}
            }));
  }

  private int numRookCaptures(char[][] board) {

    int i = 0;

    int rockAxis = 0;
    int rockOrd = 0;

    loop:
    while (i < board.length) {

      char[] line = board[i];
      int j = 0;

      while (j < line.length) {
        if (line[j] == 'R') {
          rockAxis = j;
          rockOrd = i;
          break loop;
        }

        j++;
      }

      i++;
    }

    int result = 0;

    char[] posToCheck;

    int up = rockOrd - 1;
    int down = rockOrd + 1;
    int east = rockAxis + 1;
    int west = rockAxis - 1;

    while (up >= 0 || down < board.length || east < board.length || west >= 0) {

      if (up >= 0) {

        posToCheck = board[up];
        if (posToCheck[rockAxis] == 'B') up = -1;
        else if (posToCheck[rockAxis] == 'p') {
          result++;
          up = -1;
        }
        up--;
      }

      if (down < board.length) {
        posToCheck = board[down];

        if (posToCheck[rockAxis] == 'B') down = board.length;
        else if (posToCheck[rockAxis] == 'p') {
          result++;
          down = board.length;
        }

        down++;
      }

      if (east < board.length) {
        posToCheck = board[rockOrd];

        if (posToCheck[east] == 'B') east = board.length;
        else if (posToCheck[east] == 'p') {
          result++;
          east = board.length;
        }
        east++;
      }

      if (west >= 0) {
        posToCheck = board[rockOrd];

        if (posToCheck[west] == 'B') west = -1;
        else if (posToCheck[west] == 'p') {
          result++;
          west = -1;
        }
        west--;
      }
    }

    return result;
  }
}
