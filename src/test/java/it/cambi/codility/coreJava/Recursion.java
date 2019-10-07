/**
 * 
 */
package it.cambi.codility.coreJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Recursion {

	@Test
	public void factorial() {

		int n = 5;

		assertEquals(120, factorial(n));
	}

	public int factorial(int n) {

		if (n < 0)
			return 0;

		if (n == 0)
			return 1;

		return n * factorial(n - 1);
	}

	@Test
	public void power() {

		double n = 2;
		int p = 5;

		assertEquals(32.0, power(n, p));

	}

	public double power(double n, int p) {

		if (p == 0)
			return 1.0;

		return n * power(n, p - 1);
	}

}
