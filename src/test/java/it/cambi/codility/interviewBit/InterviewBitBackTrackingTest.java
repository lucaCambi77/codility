package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitBackTrackingTest {

  private String sol = "";

  @Test
  // 1) Create a global variable which will store the maximum string or number.
  // 2) Define a recursive function that takes the string as number and value of k
  // 3) Run a nested loop, the outer loop from 0 to length of string -1 and inner loop from i+1 to
  // end of string.
  // 4) Swap the ith and jth character and check if the string is now maximum and update
  // the maximum string.
  // 5) Call the function recursively with parameters: string and k-1.
  // 6) Now again swap back the ith and jth character.
  public void maximalString() {

    maximalString("254", 1, 0);
    assertEquals("524", sol);
    sol = "";
    maximalString("254", 2, 0);
    assertEquals("542", sol);
    sol = "";
    maximalString("1714254313", 5, 0);
    assertEquals("7544331211", sol);
    sol = "";
    maximalString("271425431", 3, 0);
    assertEquals("754422131", sol);
  }

  void maximalString(String A, int B, int index) {
    if (B == 0) return;

    for (int i = index; i < A.length(); i++) {
      for (int j = i + 1; j < A.length(); j++) {
        String swap = swap(A, i, j);
        if (swap.compareTo(sol) > 0) sol = swap;
        maximalString(swap, B - 1, index + 1);
        A = swap(swap, i, j);
      }
    }
  }

  private String swap(String A, int i, int j) {
    char ch[] = A.toCharArray();
    char temp = ch[i];
    ch[i] = ch[j];
    ch[j] = temp;
    return new String(ch);
  }
}
