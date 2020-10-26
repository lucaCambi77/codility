package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitGreedyTest {

  @Test
  public void majorityElement() {
    assertEquals(2, majorityElement(new int[] {2, 1, 2}));
    assertEquals(1, majorityElement(new int[] {2, 1, 2, 2, 1, 1, 1}));
  }

  public int majorityElement(final int[] A) {
    return findCandidate(A, A.length);
  }

  // Moore's Voting Algorithm
  int findCandidate(int a[], int size) {
    int maj_index = 0, count = 1;
    int i;
    for (i = 1; i < size; i++) {
      if (a[maj_index] == a[i]) count++;
      else count--;
      if (count == 0) {
        maj_index = i;
        count = 1;
      }
    }
    return a[maj_index];
  }

  @Test
  public void mice() {
    assertEquals(4, mice(new int[] {4, -4, 2}, new int[] {4, 0, 5}));
    assertEquals(
        28,
        mice(
            new int[] {-49, 58, 72, -78, 9, 65, -42, -3},
            new int[] {30, -13, -70, 58, -34, 79, -36, 27}));
  }

  public int mice(int[] A, int[] B) {

    Arrays.sort(A);
    Arrays.sort(B);

    int sol = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; i++) {
      sol = Math.max(sol, Math.abs(A[i] - B[i]));
    }

    return sol;
  }

  @Test
  public void bulbs() {
    assertEquals(3, bulbs(new int[] {1, 0, 1, 0}));
    assertEquals(4, bulbs(new int[] {0, 1, 0, 1}));
  }

  public int bulbs(int[] A) {
    int count = 0;
    boolean flag = true;

    for (int bulb : A) {
      if (bulb == 0 && flag) {
        count += 1;
        flag = false;
      } else if (bulb == 1 && !flag) {
        count += 1;
        flag = true;
      }
    }
    return count;
  }
}
