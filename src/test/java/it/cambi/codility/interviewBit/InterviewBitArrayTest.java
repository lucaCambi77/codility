package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitArrayTest {

  public class Interval {
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
  public void prettyPrint() {
    assertEquals(
        new ArrayList<ArrayList<Integer>>() {
          {
            add(
                new ArrayList<>() {
                  {
                    add(2);
                    add(2);
                    add(2);
                  }
                });
            add(
                new ArrayList<>() {
                  {
                    add(2);
                    add(1);
                    add(2);
                  }
                });
            add(
                new ArrayList<>() {
                  {
                    add(2);
                    add(2);
                    add(2);
                  }
                });
          }
        },
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
    assertEquals(
        "9534330",
        largestNumber(
            new ArrayList<>() {
              {
                add(3);
                add(30);
                add(34);
                add(5);
                add(9);
              }
            }));

    assertEquals(
        "0",
        largestNumber(
            new ArrayList<>() {
              {
                add(0);
                add(0);
                add(0);
                add(0);
                add(0);
              }
            }));

    assertEquals(
        "99999999999998",
        largestNumber(
            new ArrayList<>() {
              {
                add(9);
                add(99);
                add(999);
                add(9999);
                add(9998);
              }
            }));

    assertEquals(
        "9648527226766636354854724412368319",
        largestNumber(Arrays.asList(472, 663, 964, 722, 485, 852, 635, 4, 368, 676, 319, 412)));
  }

  public String largestNumber(final List<Integer> A) {
    Collections.sort(
        A,
        new Comparator<Integer>() {
          @Override
          public int compare(Integer o1, Integer o2) {
            String first = Integer.toString(o1) + Integer.toString(o2);
            String second = Integer.toString(o2) + Integer.toString(o1);

            return new BigInteger(first).compareTo(new BigInteger(second)) < 0 ? 1 : -1;
          }
        });

    String sol = A.stream().map(String::valueOf).collect(Collectors.joining());
    return sol.replaceFirst("^0+(?!$)", "");
  }

  @Test
  public void mergeIntervals() {
    assertEquals(
        new ArrayList<Interval>() {
          {
            add(new Interval(1, 6));
            add(new Interval(8, 10));
            add(new Interval(15, 18));
          }
        },
        mergeIntervals(
            new ArrayList<>() {
              {
                add(new Interval(1, 3));
                add(new Interval(2, 6));
                add(new Interval(8, 10));
                add(new Interval(15, 18));
              }
            }));

    assertEquals(
        new ArrayList<Interval>() {
          {
            add(new Interval(1, 10));
          }
        },
        mergeIntervals(
            new ArrayList<>() {
              {
                add(new Interval(1, 10));
                add(new Interval(2, 9));
                add(new Interval(3, 8));
                add(new Interval(4, 7));
                add(new Interval(5, 6));
                add(new Interval(6, 6));
              }
            }));

    assertEquals(
        new ArrayList<Interval>() {
          {
            add(new Interval(1, 10));
          }
        },
        mergeIntervals(
            new ArrayList<>() {
              {
                add(new Interval(1, 10));
                add(new Interval(2, 10));
                add(new Interval(3, 8));
                add(new Interval(4, 7));
                add(new Interval(5, 6));
                add(new Interval(6, 6));
              }
            }));
  }

  public ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals) {

    Collections.sort(
        intervals,
        new Comparator<Interval>() {
          @Override
          public int compare(Interval o1, Interval o2) {
            return o1.start > o2.start ? 1 : -1;
          }
        });

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
    Arrays.sort(A, (a, b) -> Integer.compare(a.length(), b.length()));

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
    if (A.trim().length() == 0) return 0;

    String[] split = A.split(" ");

    if (split.length == 1) return A.trim().length();

    return split[split.length - 1].length();
  }

  public int[] solve(int[] A) {
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

    int left[] = new int[A.length];
    int right[] = new int[A.length];
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
    Set<Integer> seen = new HashSet<Integer>();

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
    while (i++ < A.length) {
      if (i == A[i - 1]) continue;
      else return new int[] {A[i - 1], A[i - 1] + 1};
    }

    return new int[] {};
  }

  @Test
  public void plusOne() {
    assertArrayEquals(new int[] {1, 2, 4}, plusOne(new int[] {1, 2, 3}));
    assertArrayEquals(
        new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        plusOne(new int[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}));
  }

  private int[] plusOne(int[] A) {

    StringBuilder builder = new StringBuilder();

    for (int value : A) builder.append(value);

    BigInteger value = new BigInteger(builder.toString());
    value = value.add(new BigInteger("1"));

    String result = value.toString();

    int[] output = new int[result.length()];

    for (int i = 0; i < result.length(); i++)
      output[i] = Integer.valueOf(String.valueOf(result.charAt(i)));

    return output;
  }
}
