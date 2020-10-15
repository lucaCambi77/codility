package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitBinarySearchTest {

  @Test
  public void woodCutting() {
    assertEquals(15, woodCutting(new int[] {20, 15, 10, 17}, 7));
    assertEquals(36, woodCutting(new int[] {4, 42, 40, 26, 46}, 20));
  }

  private long woodCutting(int[] A, int B) {
    if (A.length == 0) return 0;
    if (A.length == 1) return A[0] - B;
    long ans = -1;
    long st = 0;

    long ed = Arrays.stream(A).max().getAsInt();

    while (st <= ed) {
      long md = (st + ed) / 2;
      long val = findval(A, md);
      if (val == B) {
        ans = Math.max(ans, md);
        break;
      } else if (val > B) {
        ans = Math.max(ans, md);
        st = md + 1;
      } else {
        ed = md - 1;
      }
    }
    return ans;
  }

  private long findval(int[] A, long ind) {
    long cv = 0;
    for (int i : A) if (i > ind) cv += i - ind;
    return cv;
  }

  @Test
  public void validBSTfromPreorder() {
    assertEquals(0, validBSTfromPreorder(new int[] {7, 7, 10, 10, 9, 5, 2, 8}));
  }

  public int validBSTfromPreorder(int[] A) {
    Stack<Integer> s = new Stack<>();
    int root = Integer.MIN_VALUE;

    for (Integer a : A) {

      if (a < root) return 0;

      while (!s.isEmpty() && s.peek() < a) {
        root = s.peek();
        s.pop();
      }

      s.push(a);
    }
    return 1;
  }

  @Test
  public void searchBitonicArray() {
    assertEquals(3, searchBitonicArray(new int[] {3, 9, 10, 20, 17, 5, 1}, 20));
    assertEquals(4, searchBitonicArray(new int[] {3, 9, 10, 20, 17, 5, 1}, 17));
    assertEquals(4, searchBitonicArray(new int[] {3, 9, 18, 20, 17, 5, 1}, 17));
    assertEquals(1, searchBitonicArray(new int[] {3, 9, 17, 20, 18, 5, 1}, 9));
    assertEquals(5, searchBitonicArray(new int[] {3, 9, 17, 20, 18, 5, 1}, 5));
    assertEquals(-1, searchBitonicArray(new int[] {1, 20, 50, 40, 10}, 5));
    assertEquals(0, searchBitonicArray(new int[] {5, 20, 50, 40, 10}, 5));
  }

  private int searchBitonicArray(int[] a, int k) {

    int bitonicInd = 0;

    if (a[0] == k) return 0;

    for (int i = 1; i < a.length; i++) {
      if (a[i] == k) return i;

      if (a[i - 1] < a[i] && a[i + 1] > a[i]) bitonicInd = i;
    }

    return binarySearch(a, bitonicInd, a.length, k);
  }

  public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1; // (low + high) / 2;
      int midVal = a[mid];

      if (midVal < key) low = mid + 1;
      else if (midVal > key) high = mid - 1;
      else return mid; // key found
    }

    return -1; // key not found.
  }
}
