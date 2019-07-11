/**
 * 
 */
package it.cambi.codility.gfg;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geek4GeeksGreedy {

	@Test
	public void shopInCandyStore() {
		int n = 4;
		int k = 2;
		int[] candies = new int[] { 1, 2, 4, 3 };

		Arrays.sort(candies);
		int minCost = candies[0];

		for (int i = n - k - 1; i > 0; i--) {
			minCost += candies[i];
		}

		System.out.println(minCost);
		int maxCost = candies[n -1];

		for (int i = k; i < n - 1; i++) {
			maxCost += candies[i];
		}

		System.out.println(maxCost);
	}

	@Test
	public void minimumOperations() {
		int value = 9;

		minimumOperations(value, 0);
	}

	public void minimumOperations(int value, int count) {
		if (value == 1) {
			System.out.println(++count);
			return;
		}

		if (value == 2) {
			++count;
			System.out.println(++count);
			return;
		}

		if (value == 3) {
			++count;
			++count;
			System.out.println(++count);
			return;
		}

		if (((value & 1) == 1)) {
			minimumOperations(--value, ++count);
		} else {
			minimumOperations(value / 2, ++count);
		}

	}
}
