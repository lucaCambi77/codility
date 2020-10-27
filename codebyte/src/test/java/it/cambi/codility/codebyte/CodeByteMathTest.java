package it.cambi.codility.codebyte;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeByteMathTest {

  @Test
  public void firstFactorial() {
    assertEquals(2, firstFactorial(2));
    assertEquals(5040, firstFactorial(7));
    assertEquals(362880, firstFactorial(9));
  }

  public int firstFactorial(int num) {
    if (num == 1) return 1;
    return num * firstFactorial(num - 1);
  }
}
