package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitGreedyTest {

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
