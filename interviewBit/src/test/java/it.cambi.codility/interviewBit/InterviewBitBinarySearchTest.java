package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitBinarySearchTest {

  @Test
  public void smallerOrEqualElement() {

    assertEquals(4, smallerOrEqualElement(new int[] {1, 3, 4, 4, 6}, 4));
    assertEquals(5, smallerOrEqualElement(new int[] {1, 3, 4, 4, 4, 6}, 4));
    assertEquals(2, smallerOrEqualElement(new int[] {1, 2, 5, 5}, 3));
    assertEquals(1, smallerOrEqualElement(new int[] {1, 4, 5, 5}, 3));
    assertEquals(4, smallerOrEqualElement(new int[] {1, 4, 5, 5, 7}, 6));
    assertEquals(5, smallerOrEqualElement(new int[] {1, 4, 4, 5, 5, 7}, 6));
    assertEquals(1, smallerOrEqualElement(new int[] {1}, 6));
  }

  private int smallerOrEqualElement(int[] A, int B) {

    int pos = binarySearchSoE(A, 0, A.length, B) + 1;

    while (pos < A.length) {
      if (A[pos] - B != 0) break;
      pos++;
    }

    return pos;
  }

  public static int binarySearchSoE(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1; // (low + high) / 2;
      int midVal = a[mid];

      if (midVal < key) {

        if (mid == a.length - 1 || a[mid + 1] > key) return mid;

        low = mid + 1;
      } else if (midVal > key) {

        if (mid == 0 || a[mid - 1] < key) return mid - 1;

        high = mid - 1;
      } else return mid; // key found
    }

    return 0; // key not found.
  }

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
