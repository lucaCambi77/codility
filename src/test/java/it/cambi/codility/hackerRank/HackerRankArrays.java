/**
 * 
 */
package it.cambi.codility.hackerRank;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class HackerRankArrays {

	@Test
	public void arrayManipulation() throws IOException {

		/*
		 * int n = 30000;
		 * 
		 * int[][] queries = new int[n][3];
		 * 
		 * InputStream is = new FileInputStream("src/test/resources/arrayManip.txt");
		 * BufferedReader buf = new BufferedReader(new InputStreamReader(is)); String
		 * line = buf.readLine();
		 * 
		 * int x = 0; while (line != null) { String[] scoresString = line.split(" ");
		 * for (int i = 0; i < scoresString.length; i++) { queries[x][0] =
		 * Integer.parseInt(scoresString[0]); queries[x][1] =
		 * Integer.parseInt(scoresString[1]); queries[x][2] =
		 * Integer.parseInt(scoresString[2]);
		 * 
		 * } x++; line = buf.readLine(); }
		 * 
		 * buf.close();
		 */
		int n = 5;

		int[][] queries = new int[][] { { 1, 2, 100 }, { 2, 5, 100 }, { 3, 4, 100 } };

		long[] arr = new long[n + 1];

		for (int i = 0; i < queries.length; i++) {
			long summond = (long) queries[i][2];

			arr[queries[i][0]] = arr[queries[i][0]] + summond;

			if ((queries[i][1] + 1) <= n)
				arr[queries[i][1] + 1] -= summond;
		}

		long k = 0, max = 0;

		for (int i = 1; i <= n; i++) {
			k = k + arr[i];
			if (max < k)
				max = k;

		}

		System.out.println(max);
	}

	@Test
	public void equalizeTheArray() {

		int[] arr = new int[] { 1, 2, 3, 1, 2, 3, 3, 3 };

		int arrLength = arr.length;
		Arrays.sort(arr);
		int count = 1;
		int maxFreq = 0;
		int itemToDelete = 0;

		for (int i = 1; i < arrLength; i++) {

			if (arr[i] == arr[i - 1]) {
				count++;
				maxFreq = Math.max(count, maxFreq);
				continue;
			}
			maxFreq = Math.max(count, maxFreq);
			count = 1;
		}

		itemToDelete = arrLength - maxFreq;

		/*
		 * int[] freq = new int[arr.length];
		 * 
		 * for (int i = 0; i < arrLength; i++) { freq[arr[i]]++; maxFreq =
		 * Math.max(maxFreq, freq[arr[i]]); }
		 * 
		 * itemToDelete = arrLength - maxFreq;
		 */

		System.out.println("Max freq is " + maxFreq);
		System.out.println("Item to be deleted " + itemToDelete);
	}

	@Test
	public void minimunTwoSwaps() {

		int[] arr = new int[] { 1, 3, 5, 2, 4, 6, 7 };
		/*
		 * int[] arr = new int[] { 8, 45, 35, 84, 79, 12, 74, 92, 81, 82, 61, 32, 36, 1,
		 * 65, 44, 89, 40, 28, 20, 97, 90, 22, 87, 48, 26, 56, 18, 49, 71, 23, 34, 59,
		 * 54, 14, 16, 19, 76, 83, 95, 31, 30, 69, 7, 9, 60, 66, 25, 52, 5, 37, 27, 63,
		 * 80, 24, 42, 3, 50, 6, 11, 64, 10, 96, 47, 38, 57, 2, 88, 100, 4, 78, 85, 21,
		 * 29, 75, 94, 43, 77, 33, 86, 98, 68, 73, 72, 13, 91, 70, 41, 17, 15, 67, 93,
		 * 62, 39, 53, 51, 55, 58, 99, 46 };
		 */
		/*
		 * int count = 0;
		 * 
		 * for (int i = 0; i < arr.length; i++) {
		 * 
		 * if (arr[i] != i + 1) count++; }
		 */

		int swap = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i + 1 != arr[i]) {
				int t = i;
				while (arr[t] != i + 1) {
					t++;
				}
				int temp = arr[t];
				arr[t] = arr[i];
				arr[i] = temp;
				swap++;
			}
		}

		System.out.println("Min two swap required" + swap);
	}
}
