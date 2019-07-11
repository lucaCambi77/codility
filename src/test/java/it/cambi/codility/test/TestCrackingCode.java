package it.cambi.codility.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TestCrackingCode {

	/**
	 * 1.1
	 */
	@Test
	public void isUniqueCharTest() {

		String str = "hallo";

		assertFalse(isUniqueChar(str));

		String str1 = "antilope";

		assertTrue(isUniqueChar(str1));
	}

	private boolean isUniqueChar(String str) {
		boolean[] charSet = new boolean[128];

		for (int i = 0; i < str.length(); i++) {

			int val = str.charAt(i);

			if (charSet[val])
				return false;

			charSet[val] = true;
		}

		return true;
	}

	/**
	 * 1.3
	 */
	@Test
	public void URLify() {

		String A = "Mr John smith  ";

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < A.length(); i++) {
			char el = A.charAt(i);

			if (el == ' ') {

				builder.append("%20");
				continue;
			}

			builder.append(el);

		}

		assertEquals(builder.toString(), "Mr%20John%20smith%20%20");
	}

	/**
	 * 8.1
	 */
	@Test
	public void tripleTest() {
		int stepsToDo = 20;

		int[] memo = new int[stepsToDo + 1];
		Arrays.fill(memo, -1);

		int count = countWays(stepsToDo, memo);
		System.out.println(count);
	}

	private int countWays(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countWays(n - 3, memo) + countWays(n - 5, memo) + countWays(n - 10, memo);
			return memo[n];
		}
	}

}
