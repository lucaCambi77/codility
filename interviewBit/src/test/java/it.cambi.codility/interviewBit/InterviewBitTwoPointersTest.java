package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitTwoPointersTest {

  @Test
  public void removeDuplicates() {
    assertEquals(2, removeDuplicates(Arrays.asList(1, 1, 2)));
    assertEquals(
        4,
        removeDuplicates(
            Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3)));
  }

  private int removeDuplicates(List<Integer> a) {
    int pointer = 1;

    for (int i = 1; i < a.size(); i++) {

      while (i < a.size() && a.get(i).equals(a.get(pointer - 1))) i++;

      if (i == a.size()) {
        a.set(pointer, a.get(pointer));
        break;
      }

      a.set(pointer++, a.get(i));
    }

    return pointer;
  }

  @Test
  public void countingSubArrays() {
    assertEquals(4, countingSubArrays(new int[] {2, 5, 6}, 10));
  }

  private int countingSubArrays(int[] A, int B) {

    int n = A.length;
    int ans = 0, sum = 0;
    int start = 0, end = 0;
    while (end < n) {
      if (sum + A[end] < B) {
        sum += A[end];
        ans += (end - start + 1);
        end++;

      } else {
        sum -= A[start];
        start++;
      }
    }
    return ans;
  }

  @Test
  public void maxOneAfterModification() {
    assertEquals(4, maxOneAfterModification(new int[] {1, 0, 0, 1, 1, 0, 1}, 1));
    assertEquals(5, maxOneAfterModification(new int[] {1, 0, 0, 1, 0, 1, 0, 1, 0, 1}, 2));
  }

  public int maxOneAfterModification(int[] A, int B) {
    int ws = 0; // window start
    int zc = 0; // zero count
    int w = 0; // width of the window

    int n = A.length;

    for (int we = 0; we < n; we++) {

      if (A[we] == 0) {
        zc++;
      }

      while (zc > B) {
        if (A[ws] == 0) {
          zc--;
        }
        ws++;
      }

      if (we - ws + 1 > w) w = we - ws + 1;
    }
    return w;
  }

  @Test
  public void pairWithGivenDifference() {
    assertEquals(1, pairWithGivenDifference(new int[] {5, 10, 3, 2, 50, 80}, 78));
    assertEquals(1, pairWithGivenDifference(new int[] {-10, 20}, 30));
    assertEquals(0, pairWithGivenDifference(new int[] {1}, 30));
    assertEquals(1, pairWithGivenDifference(new int[] {1, 2}, -1));
    assertEquals(0, pairWithGivenDifference(new int[] {20, 500, 1000, 200}, 0));
    assertEquals(
        0,
        pairWithGivenDifference(new int[] {-698, 371, -534, -322, -407, 851, 484, 18, -536}, -36));
  }

  public int pairWithGivenDifference(int[] arr, int B) {
    Arrays.parallelSort(arr);

    Set<Integer> seen = new HashSet<>();

    for (int i : arr) {
      if (B != 0) seen.add(i + B);
      else seen.add(-i);
    }

    for (int i : arr) if (seen.contains(i)) return 1;

    return 0;
  }

  @Test
  public void intersect() {
    assertArrayEquals(
        new int[] {3, 3, 5}, intersect(new int[] {1, 2, 3, 3, 4, 5, 6}, new int[] {3, 3, 5}));
    assertArrayEquals(
        new int[] {3, 5}, intersect(new int[] {1, 2, 3, 3, 4, 5, 6}, new int[] {3, 5}));
    assertArrayEquals(new int[] {1}, intersect(new int[] {1}, new int[] {1}));
    assertArrayEquals(
        new int[] {35, 38, 53, 67, 69, 94, 98},
        intersect(
            new int[] {
              1, 3, 8, 10, 13, 13, 16, 16, 16, 18, 21, 23, 24, 31, 31, 31, 33, 35, 35, 37, 37, 38,
              40, 41, 43, 47, 47, 48, 48, 52, 52, 53, 53, 55, 56, 60, 60, 61, 61, 63, 63, 64, 66,
              67, 67, 68, 69, 71, 80, 80, 80, 80, 80, 80, 81, 85, 87, 87, 88, 89, 90, 94, 95, 97,
              98, 98, 100, 101
            },
            new int[] {
              5, 7, 14, 14, 25, 28, 28, 34, 35, 38, 38, 39, 46, 53, 65, 67, 69, 70, 78, 82, 94, 94,
              98
            }));
  }

  public int[] intersect(final int[] A, final int[] B) {
    int i = 0;
    int j = 0;

    List<Integer> sol = new ArrayList<>();

    while (i < A.length && j < B.length) {

      if (A[i] < B[j]) i++;
      else if (A[i] > B[j]) j++;
      else {
        sol.add(A[i]);
        i++;
        j++;
      }
    }

    return sol.stream().mapToInt(v -> v).toArray();
  }
}
