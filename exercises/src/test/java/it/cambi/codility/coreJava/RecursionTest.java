/** */
package it.cambi.codility.coreJava;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
public class RecursionTest {

  @Test
  public void factorial() {

    assertEquals(120, factorial(new BigInteger("5")).intValue());
    assertEquals("6227020800", factorial(new BigInteger("13")).toString());
    assertEquals("265252859812191058636308480000000", factorial(new BigInteger("30")).toString());
    assertEquals("3628800", factorial(new BigInteger("10")).toString());
  }

  public BigInteger factorial(BigInteger n) {

    if (n.compareTo(new BigInteger("1")) == 0) return new BigInteger("1");

    String number = factorial(n.subtract(new BigInteger("1"))).toString();

    return n.multiply(new BigInteger(number));
  }

  @Test
  public void power() {
    assertEquals(32, power(2, 5));
    assertEquals(1, power(2, 0));
    assertEquals(64, power(2, 6));
  }

  public long power(int a, int b) {

    if (b == 0) return 1;
    if (b == 1) return a;

    long res = power(a, b / 2);
    if (b % 2 == 0) return res * res;
    return res * res * a;
  }
}
