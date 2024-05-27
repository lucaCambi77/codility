package it.cambi.codility.interviewBit;

import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitArrayTest {

  class Interval {
    int start;
    int end;

    Interval(int s, int e) {
      start = s;
      end = e;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Interval interval = (Interval) o;
      return start == interval.start && end == interval.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }

  @Test
  public void twoSum() {
    assertArrayEquals(new int[] {1, 2}, twoSum(new int[] {2, 7, 11, 15}, 9));
    assertArrayEquals(
        new int[] {4, 8},
        twoSum(
            new int[] {
              4, 7, -4, 2, 2, 2, 3, -5, -3, 9, -4, 9, -7, 7, -1, 9, 9, 4, 1, -4, -2, 3, -3, -5, 4,
              -7, 7, 9, -4, 4, -8
            },
            -3));
    assertArrayEquals(new int[] {1, 2}, twoSum(new int[] {1, 1, 1}, 2));
    assertArrayEquals(
        new int[] {3, 4},
        twoSum(
            new int[] {
              10, -3, 5, -7, -4, 5, 6, -7, 8, -5, 8, 0, 8, -5, -10, -1, 1, -6, 4, -1, -2, -2, 10,
              -2, -4, -7, 5, 1, 7, -10, 0, 5, 8, 6, -8, 8, -8, -8, 3, -9, -10, -5, -5, -10, 10, -4,
              8, 0, -6, -2, 3, 7, -5, 5, 1, -7, 0, -5, 1, -3, 10, -4, -3, 3, 3, 5, 1, -2, -6, 3, -4,
              10, -10, -3, -8, 2, -2, -3, 0, 10, -6, -8, -10, 6, 7, 0, 3, 9, -10, -7, 8, -7, -7
            },
            -2));
    assertArrayEquals(
        new int[] {4, 5},
        twoSum(
            new int[] {
              9, -8, -10, -7, 7, -8, 2, -7, 4, 7, 0, -3, -4, -5, -1, -4, 5, 8, 1, 9, -2, 5, 10, -5,
              -7, -1, -6, 4, 1, -5, 3, 8, -4, -10, -9, -3, 10, 0, 7, 9, -8, 10, -9, 7, 8, 0, 6, -6,
              -7, 6, -4, 2, 0, 10, 1, -2, 5, -2
            },
            0));
    assertArrayEquals(
        new int[] {10, 15},
        twoSum(
            new int[] {
              -7, 7, -10, 6, -3, -10, 9, 1, 10, 5, 6, 7, -3, 9, 0, -5, 5, 8, -6, -10, 10, -4, -7, 7,
              2, -5, 5, -7, -7, 8, 5, -3, 5, 10, 10, -8, -2, -3, -2, -2, -7, 8, -7, 1, -2, -8, -10,
              -5, -5
            },
            5));
  }

  int[] twoSum(final int[] A, int B) {
    Map<Integer, Integer> v = new HashMap<>();
    for (int i = 0; i < A.length; i++) {
      if (v.get(B - A[i]) != null) return new int[] {v.get(B - A[i]), i + 1};
      v.putIfAbsent(A[i], i + 1);
    }
    return new int[] {};
  }

  @Test
  public void wave() {
    assertArrayEquals(new int[] {2, 1, 4, 3}, wave(new int[] {1, 2, 3, 4}));
  }

  public int[] wave(int[] A) {

    Arrays.sort(A);

    for (int i = 0; i < A.length - 1; i += 2) {
      int tmp = A[i];
      A[i] = A[i + 1];
      A[i + 1] = tmp;
    }

    return A;
  }

  @Test
  public void pascaleTriangle() {

    assertArrayEquals(new int[][] {}, pascalTriangle(0));
    assertArrayEquals(new int[][] {{1}}, pascalTriangle(1));
    assertArrayEquals(new int[][] {{1}, {1, 1}}, pascalTriangle(2));
    assertArrayEquals(new int[][] {{1}, {1, 1}, {1, 2, 1}}, pascalTriangle(3));
    assertArrayEquals(new int[][] {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}}, pascalTriangle(4));
    assertArrayEquals(
        new int[][] {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}}, pascalTriangle(5));
    assertArrayEquals(
        new int[][] {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}, {1, 5, 10, 10, 5, 1}},
        pascalTriangle(6));
  }

  public int[][] pascalTriangle(int A) {

    if (A == 0) return new int[][] {};

    int[][] triangle = new int[A][];
    triangle[0] = new int[] {1};

    int i = 1;
    while (i < A) {
      int[] row = triangle[i - 1];
      int[] sol = new int[i + 1];
      sol[0] = 1;
      sol[sol.length - 1] = 1;

      for (int j = 0; j < sol.length - 2; j++) sol[j + 1] = row[j] + row[j + 1];

      triangle[i++] = sol;
    }

    return triangle;
  }

  @Test
  public void maxset() {
    assertArrayEquals(new int[] {1, 2, 5}, maxset(new int[] {1, 2, 5, -7, 2, 3}));
    assertArrayEquals(new int[] {2, 3, 9}, maxset(new int[] {1, 2, 5, -7, 2, 3, 9}));
    assertArrayEquals(new int[] {1, 2, 5}, maxset(new int[] {1, 2, 5, -7, 2, 3, 3}));
    assertArrayEquals(new int[] {2, 3, 2, 1}, maxset(new int[] {1, 2, 5, -7, 2, 3, 2, 1}));
    assertArrayEquals(
        new int[] {1967513926, 1540383426},
        maxset(new int[] {1967513926, 1540383426, -1303455736, -521595368}));
  }

  public int[] maxset(int[] A) {

    LinkedList<Integer> result = new LinkedList<>();
    LinkedList<Integer> resultTmp = new LinkedList<>();
    long sum = 0;
    long sumTmp = 0;
    for (int i = 0; i < A.length; i++) {

      if (A[i] >= 0) {
        resultTmp.add(A[i]);
        sumTmp += A[i];
      } else if (resultTmp.size() > 0) {

        if (sum < sumTmp || (sum == sumTmp && resultTmp.size() > result.size())) {
          sum = sumTmp;
          result = new LinkedList<>(resultTmp);
        }

        resultTmp.clear();
        sumTmp = 0;
      }
    }

    if (sum < sumTmp || (sum == sumTmp && resultTmp.size() > result.size()))
      return resultTmp.stream().mapToInt(i -> i).toArray();

    return result.stream().mapToInt(i -> i).toArray();
  }

  /*
   * Move to BairesDev company
   */
  @Test
  public void mostFrequent() {

    assertEquals(34, mostFrequent(new int[] {34, 31, 34, 77, 82}, 5));
    assertEquals(66, mostFrequent(new int[] {66}, 1));
    assertEquals(101, mostFrequent(new int[] {22, 101, 102, 101, 102, 525, 88}, 7));
    assertEquals(102, mostFrequent(new int[] {22, 102, 102, 101, 101, 102, 102}, 7));
  }

  private int mostFrequent(int[] array, int value) {

    Arrays.sort(array);

    int countTmp = 1;
    int compare = array[0];
    int count = 0;
    for (int i = 1; i < value; i++) {
      if (array[i] == array[i - 1]) countTmp++;
      else {
        if (countTmp > count) {
          count = countTmp;
          countTmp = 1;
          compare = array[i - 1];
        }
      }
    }

    return countTmp > count ? array[value - 1] : compare;
  }

  @Test
  public void firstMissingPositive() {
    assertEquals(3, firstMissingPositive(new int[] {1, 2, 0}));
    assertEquals(2, firstMissingPositive(new int[] {3, 4, -1, 1}));
    assertEquals(1, firstMissingPositive(new int[] {-8, -7, -6}));
  }

  public int firstMissingPositive(int[] A) {
    Arrays.parallelSort(A);

    int start = 1;

    int i = 0;

    while (i < A.length && A[i] < start) i++;

    for (; i < A.length; i++) {
      if (A[i] == start) start++;
      else return start;
    }

    return A[A.length - 1] < 0 ? 1 : start;
  }

  @Test
  public void prettyPrint() {
    assertEquals(
        Arrays.asList(Arrays.asList(2, 2, 2), Arrays.asList(2, 1, 2), Arrays.asList(2, 2, 2)),
        prettyPrint(2));
  }

  private ArrayList<ArrayList<Integer>> prettyPrint(int n) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    for (int i = 1; i < (2 * n); i++) {
      ArrayList<Integer> a = new ArrayList<>();
      for (int j = 1; j < (2 * n); j++) {
        a.add(Math.max(Math.abs(i - n), Math.abs(j - n)) + 1);
      }
      ans.add(a);
    }
    return ans;
  }

  @Test
  public void largestNumber() {
    assertEquals("9534330", largestNumber(Arrays.asList(3, 30, 34, 5, 9)));

    assertEquals("0", largestNumber(Arrays.asList(0, 0, 0, 0, 0)));

    assertEquals("99999999999998", largestNumber(Arrays.asList(9, 99, 999, 9999, 9998)));

    assertEquals(
        "9648527226766636354854724412368319",
        largestNumber(Arrays.asList(472, 663, 964, 722, 485, 852, 635, 4, 368, 676, 319, 412)));
  }

  public String largestNumber(final List<Integer> A) {
    A.sort(
        (o1, o2) -> {
          String first = o1 + Integer.toString(o2);
          String second = o2 + Integer.toString(o1);

          return new BigInteger(first).compareTo(new BigInteger(second)) < 0 ? 1 : -1;
        });

    String sol = A.stream().map(String::valueOf).collect(Collectors.joining());
    return sol.replaceFirst("^0+(?!$)", "");
  }

  @Test
  public void mergeIntervals() {
    assertEquals(
        Arrays.asList(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18)),
        mergeIntervals(
            new ArrayList<>(
                Arrays.asList(
                    new Interval(1, 3),
                    new Interval(2, 6),
                    new Interval(8, 10),
                    new Interval(15, 18)))));

    assertEquals(
        Arrays.asList(new Interval(1, 10)),
        mergeIntervals(
            new ArrayList<>(
                Arrays.asList(
                    new Interval(1, 10),
                    new Interval(2, 9),
                    new Interval(3, 8),
                    new Interval(4, 7),
                    new Interval(5, 6),
                    new Interval(6, 6)))));
    assertEquals(
        Arrays.asList(new Interval(1, 10)),
        mergeIntervals(
            new ArrayList<>(
                Arrays.asList(
                    new Interval(1, 10),
                    new Interval(2, 10),
                    new Interval(3, 8),
                    new Interval(4, 7),
                    new Interval(5, 6),
                    new Interval(6, 6)))));
  }

  public ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals) {

    intervals.sort((o1, o2) -> o1.start > o2.start ? 1 : -1);

    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).end > intervals.get(i - 1).end
          && intervals.get(i).start < intervals.get(i - 1).end) {
        intervals.get(i - 1).end = intervals.get(i).end;
        intervals.remove(i);
        i--;
      } else if (intervals.get(i).end <= intervals.get(i - 1).end) {
        intervals.remove(i);
        i--;
      }
    }

    return intervals;
  }

  @Test
  public void longestCommonPrefix() {
    assertEquals("a", longestCommonPrefix(new String[] {"abcdefgh", "aefghijk", "abcefgh"}));
    assertEquals(
        "aaaaa",
        longestCommonPrefix(
            new String[] {
              "aaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
              "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
            }));
  }

  public String longestCommonPrefix(String[] A) {
    Arrays.sort(A, Comparator.comparingInt(String::length));

    StringBuilder builder = new StringBuilder();
    String common = A[0];
    for (int i = 1; i < A.length; i++) {

      for (int j = 0; j < common.length(); j++) {

        if (A[i].charAt(j) == common.charAt(j)) builder.append(A[i].charAt(j));
      }
      common = builder.toString();
      builder = new StringBuilder();
    }

    return common;
  }

  @Test
  public void amazingSubArrays() {}

  public int amazingSubArrays(String A) {

    int co = 0;

    for (int i = 0; i < A.length(); i++) {
      char c = Character.toLowerCase(A.charAt(i));
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') co += A.length() - i;
    }
    return co % 10003;
  }

  @Test
  public void lengthOfLastWord() {
    assertEquals(5, lengthOfLastWord("Hello World "));
    assertEquals(5, lengthOfLastWord("Hello"));
    assertEquals(0, lengthOfLastWord(" "));
    assertEquals(5, lengthOfLastWord("Wordl   "));
  }

  public int lengthOfLastWord(final String A) {
    if (A.trim().isEmpty()) return 0;

    String[] split = A.split(" ");

    if (split.length == 1) return A.trim().length();

    return split[split.length - 1].length();
  }

  public int[] pascalTriangle(int[] A) {
    // 2 pointers
    // LHS pointer on last negatuve number
    // RHS pointer on fotst positive number

    int p1 = -1;
    int p2 = -1;
    int n = A.length;

    for (int i = 0; i <= A.length - 1; i++) {
      int num = A[i];
      if (num >= 0) {
        if (p2 == -1) {
          p2 = i;
          p1 = i - 1;
          break;
        }
      }
    }

    ArrayList<Integer> ans = new ArrayList<>();
    if (p2 == -1) {
      p1 = n - 1;
      p2 = n;
    }
    // if(p1 == -1 && p2 == -1)
    // {
    // return ans;
    // }

    while (p1 != -1 && p2 != n) {
      int a = A[p1] * A[p1];
      int b = A[p2] * A[p2];

      if (a < b) {
        ans.add(a);
        p1--;
      } else {
        ans.add(b);
        p2++;
      }
    }
    while (p1 != -1) {
      int a = A[p1] * A[p1];
      ans.add(a);
      p1--;
    }
    while (p2 != n) {
      int b = A[p2] * A[p2];
      ans.add(b);
      p2++;
    }

    return ans.stream().mapToInt(i -> i).toArray();
  }

  @Test
  public void perfectPeakArray() {

    assertEquals(1, perfectPeakArray(new int[] {5, 1, 4, 3, 6, 8, 10, 7, 9}));
    assertEquals(0, perfectPeakArray(new int[] {5, 1, 4, 4}));
    assertEquals(0, perfectPeakArray(new int[] {1}));
    assertEquals(0, perfectPeakArray(new int[] {10549, 15882, 24856, 301, 16642, 14414, 19856}));
    assertEquals(
        1,
        perfectPeakArray(
            new int[] {
              9895, 30334, 17674, 23812, 20038, 25668, 6869, 1870, 4665, 27645, 7712, 17036, 31323,
              8724, 28254, 28704, 26300, 25548, 15142, 12860, 19913, 32663, 32758
            }));
    assertEquals(
        0,
        perfectPeakArray(
            new int[] {
              5706, 26963, 24465, 29359, 16828, 26501, 28146, 18468, 9962, 2996, 492, 11479, 23282,
              19170, 15725, 6335
            }));
  }

  public int perfectPeakArray(int[] A) {

    int[] left = new int[A.length];
    int[] right = new int[A.length];
    left[0] = A[0];
    right[A.length - 1] = A[(A.length - 1)];
    for (int i = 1; i < A.length; i++) left[i] = Math.max(left[i - 1], A[i]);
    for (int i = A.length - 2; i >= 0; i--) right[i] = Math.min(right[i + 1], A[i]);
    for (int i = 1; i < A.length - 1; i++) {
      if (A[i] - left[i - 1] > 0 && A[i] - right[i + 1] < 0) return 1;
    }
    return 0;
  }

  @Test
  public void leaderInArray() {

    assertArrayEquals(new int[] {2, 5, 17}, leaderInArray(new int[] {16, 17, 4, 3, 5, 2}));
    assertArrayEquals(new int[] {2}, leaderInArray(new int[] {1, 2}));
  }

  public int[] leaderInArray(int[] A) {

    int max = 0;
    List<Integer> list = new ArrayList<>();

    for (int i = A.length - 1; i >= 0; i--) {
      if (A[i] > max) {
        list.add(A[i]);
        max = A[i];
      }
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  @Test
  public void duplicateNumber() {
    assertEquals(
        73,
        duplicateNumber(
            new int[] {
              247, 240, 303, 9, 304, 105, 44, 204, 291, 26, 242, 2, 358, 264, 176, 289, 196, 329,
              189, 102, 45, 111, 115, 339, 74, 200, 34, 201, 215, 173, 107, 141, 71, 125, 6, 241,
              275, 88, 91, 58, 171, 346, 219, 238, 246, 10, 118, 163, 287, 179, 123, 348, 283, 313,
              226, 324, 203, 323, 28, 251, 69, 311, 330, 316, 320, 312, 50, 157, 342, 12, 253, 180,
              112, 90, 16, 288, 213, 273, 57, 243, 42, 168, 55, 144, 131, 38, 317, 194, 355, 254,
              202, 351, 62, 80, 134, 321, 31, 127, 232, 67, 22, 124, 271, 231, 162, 172, 52, 228,
              87, 174, 307, 36, 148, 302, 198, 24, 338, 276, 327, 150, 110, 188, 309, 354, 190, 265,
              3, 108, 218, 164, 145, 285, 99, 60, 286, 103, 119, 29, 75, 212, 290, 301, 151, 17,
              147, 94, 138, 272, 279, 222, 315, 116, 262, 1, 334, 41, 54, 208, 139, 332, 89, 18,
              233, 268, 7, 214, 20, 46, 326, 298, 101, 47, 236, 216, 359, 161, 350, 5, 49, 122, 345,
              269, 73, 76, 221, 280, 322, 149, 318, 135, 234, 82, 120, 335, 98, 274, 182, 129, 106,
              248, 64, 121, 258, 113, 349, 167, 192, 356, 51, 166, 77, 297, 39, 305, 260, 14, 63,
              165, 85, 224, 19, 27, 177, 344, 33, 259, 292, 100, 43, 314, 170, 97, 4, 78, 310, 61,
              328, 199, 255, 159, 185, 261, 229, 11, 295, 353, 186, 325, 79, 142, 223, 211, 152,
              266, 48, 347, 21, 169, 65, 140, 83, 156, 340, 56, 220, 130, 117, 143, 277, 235, 59,
              205, 153, 352, 300, 114, 84, 183, 333, 230, 197, 336, 244, 195, 37, 23, 206, 86, 15,
              187, 181, 308, 109, 293, 128, 66, 270, 209, 158, 32, 25, 227, 191, 35, 40, 13, 175,
              146, 299, 207, 217, 281, 30, 357, 184, 133, 245, 284, 343, 53, 210, 306, 136, 132,
              239, 155, 73, 193, 278, 257, 126, 331, 294, 250, 252, 263, 92, 267, 282, 72, 95, 337,
              154, 319, 341, 70, 81, 68, 160, 8, 249, 96, 104, 137, 256, 93, 178, 296, 225, 237
            }));
  }

  private int duplicateNumber(final int[] A) {
    Set<Integer> seen = new HashSet<>();

    for (int value : A) {
      if (!seen.add(value)) return value;
    }

    return -1;
  }

  @Test
  public void pickFromBothSides() {
    assertEquals(8, pickFromBothSides(new int[] {5, -2, 3, 1, 2}, 3));
  }

  private int pickFromBothSides(int[] A, int B) {
    int sum = 0;

    for (int i = 0; i < B; i++) sum += A[i];

    int sum_last = 0, sum_first = 0;
    int actual_sum = sum;

    for (int i = A.length - 1; i >= 0 && B >= 1; i--) {
      sum_last += A[i];
      sum_first += A[B - 1];
      sum = Math.max(sum, actual_sum + sum_last - sum_first);
      B--;
    }

    return sum;
  }

  @Test
  public void repeatedNumber() {
    assertArrayEquals(new int[] {3, 4}, repeatedNumber(new int[] {3, 1, 2, 5, 3}));
  }

  private int[] repeatedNumber(final int[] A) {
    Arrays.parallelSort(A);

    int i = 1;
    while (i++ < A.length) if (i != A[i - 1]) return new int[] {A[i - 1], A[i - 1] + 1};

    return new int[] {};
  }

  @Test
  public void plusOne() {
    assertArrayEquals(new int[] {1, 2, 4}, plusOne(new int[] {1, 2, 3}));
    assertArrayEquals(
        new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        plusOne(new int[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}));
  }

  @Test
  public void segregate() {
    assertArrayEquals(
        new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        segregate(new int[] {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1}));
  }

  public int[] segregate(int[] A) {

    for (int l = 0, r = 0; r < A.length; r++) {
      if (A[l] == 1 && A[r] == 0) {
        A[l] = 0; // swap
        A[r] = 1;
        l++;
      }
      if (A[l] == 0) {
        l++;
      }
    }
    return A;
  }

  @Test
  public void addArrays() {
    assertArrayEquals(new int[] {1, 0, 0, 0}, addArrays(new int[] {9, 9, 9}, new int[] {1}));
    assertArrayEquals(new int[] {3, 7, 8}, addArrays(new int[] {1, 2, 3}, new int[] {2, 5, 5}));
    assertArrayEquals(
        new int[] {4, 3, 6, 8, 0, 6, 7, 6, 7},
        addArrays(new int[] {4, 3, 6, 7, 9, 9, 1, 7, 8}, new int[] {7, 5, 8, 9}));
  }

  public int[] addArrays(int[] A, int[] B) {

    int max = Math.max(A.length, B.length);

    int lengthA = A.length - 1;
    int lengthB = B.length - 1;

    int[] sol = new int[max];

    int pos;
    int rem = 0;

    while (lengthA >= 0 && lengthB >= 0) {
      pos = A[lengthA] + B[lengthB] + rem;

      if (pos >= 10) {
        rem = 1;
        pos = pos % 10;
      } else {
        rem = 0;
      }

      sol[--max] = pos;

      lengthA--;
      lengthB--;
    }

    if (lengthA >= 0) {
      while (lengthA >= 0) {
        pos = A[lengthA] + rem;

        if (pos >= 10) {
          rem = 1;
          pos = pos % 10;
        } else {
          rem = 0;
        }

        sol[--max] = pos;
        lengthA--;
      }
    } else if (lengthB >= 0) {
      while (lengthB >= 0) {
        pos = B[lengthB] + rem;

        if (pos >= 10) {
          rem = 1;
          pos = pos % 10;
        } else {
          rem = 0;
        }

        sol[--max] = pos;
        lengthB--;
      }
    }

    if (rem == 1) {
      int[] temp = new int[sol.length + 1];
      temp[0] = 1;
      System.arraycopy(sol, 0, temp, 1, sol.length);
      sol = temp;
    }

    return sol;
  }

  private int[] plusOne(int[] A) {

    StringBuilder builder = new StringBuilder();

    for (int value : A) builder.append(value);

    BigInteger value = new BigInteger(builder.toString());
    value = value.add(new BigInteger("1"));

    String result = value.toString();

    int[] output = new int[result.length()];

    for (int i = 0; i < result.length(); i++)
      output[i] = Integer.parseInt(String.valueOf(result.charAt(i)));

    return output;
  }
}
