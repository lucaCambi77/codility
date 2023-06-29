package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitMathTest {

  @Test
  public void titleToNumber() {
    assertEquals(1, titleToNumber("A"));
    assertEquals(28, titleToNumber("AB"));
  }

  private int titleToNumber(String A) {

    int result = 0;
    for (char c : A.toCharArray()) {
      result = result * 26 + (c - 'A') + 1;
    }
    return result;
  }

  @Test
  public void fizzBuzz() {
    assertArrayEquals(new String[] {"1", "2", "Fizz", "4", "Buzz"}, fizzBuzz(5));
  }

  private String[] fizzBuzz(int A) {

    int i = 1;
    String[] sol = new String[A];
    while (i <= A) {

      if (i % 15 == 0) sol[i - 1] = "FizzBuzz";
      else if (i % 5 == 0) sol[i - 1] = "Buzz";
      else if (i % 3 == 0) sol[i - 1] = "Fizz";
      else sol[i - 1] = Integer.toString(i);

      i++;
    }
    return sol;
  }
}
