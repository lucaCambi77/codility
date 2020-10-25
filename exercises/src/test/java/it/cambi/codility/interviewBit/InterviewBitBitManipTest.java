package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitBitManipTest {

  @Test
  public void countTotalSetBits() {
    assertEquals(4, countTotalSetBits(3));
    assertEquals(1, countTotalSetBits(1));
  }

  public int countTotalSetBits(int A) {
    A++;
    int count = 0;

    for (int i = 0; i < 32; i++) {
      int sets = (int) Math.pow(2, i + 1);
      count += ((A / sets) * (sets / 2));
      int remainder = A % sets;
      if (remainder - (sets / 2) > 0) {
        count += remainder - (sets / 2);
      }
      count = count % 1000000007;
    }
    return count;
  }

  @Test
  public void numSetBits() {
    assertEquals(1, numSetBits(1));
    assertEquals(1, numSetBits(2));
    assertEquals(2, numSetBits(3));
    assertEquals(2, numSetBits(5));
  }

  public int numSetBits(long n) {

    int count = 0;
    while (n > 0) {
      count += n & 1;
      n >>= 1;
    }
    return count;
  }
}
