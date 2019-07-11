/**
 * 
 */
package it.cambi.codility.gfg;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geek4GeeksMatrix {

	@Test
	public void countZeros() {
		int length = 14;
		int[][] A = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		int count = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (A[i][j] == 0)
					++count;
			}
		}

		System.out.println(count);
	}

	@Test
	public void binarySearch() {
		int[] A = { 1, 2, 3, 4, 5 };
		int left = 0;
		int right = 4;
		int k = 4;

		int bs = binarySearch(A, left, right, k);

		System.out.println(bs);
	}

	public int binarySearch(int[] A, int left, int right, int k) {

		if (right < left)
			return -1;

		int mid = (left + right) / 2;

		if (A[mid] == k) {

			return mid;
		} else if (A[mid] > k) {
			return binarySearch(A, left, mid - 1, k);

		} else {

			return binarySearch(A, mid + 1, right, k);
		}

	}
}
