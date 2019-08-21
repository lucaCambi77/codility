/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class HackerRankArraysTest {

	@Test
	public void bonAppetit() {

		assertEquals(5, bonAppetit(Arrays.asList(new Integer[] { 3, 10, 2, 9 }), 1, 12));
		assertEquals(0, bonAppetit(Arrays.asList(new Integer[] { 3, 10, 2, 9 }), 1, 7));


	}

	private int bonAppetit(List<Integer> bill, int k, int b) {

		int sum = bill.stream().mapToInt(Integer::intValue).sum();

		int actual = sum - bill.get(k);

		if (actual / 2 == b)
			System.out.println("Bon Appetit");

		return b - (actual / 2);
	}

	@Test
	public void migratoryBirds() {
		assertEquals(4, migratoryBirds(Arrays.asList(new Integer[] { 1, 4, 4, 4, 5, 3 })));
		assertEquals(3, migratoryBirds(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4 })));

	}

	private int migratoryBirds(List<Integer> arr) {

		int maxFreq = 0;
		int birdOfMaxFreq = 0;

		Map<Integer, Integer> birdToFreqMap = new HashMap<Integer, Integer>();

		for (Integer integer : arr) {
			birdToFreqMap.put(integer, birdToFreqMap.getOrDefault(integer, 0) + 1);

			int freq = birdToFreqMap.get(integer);
			if (freq > maxFreq) {
				maxFreq = freq;
				birdOfMaxFreq = integer;
			} else if (freq == maxFreq) {
				birdOfMaxFreq = Math.min(birdOfMaxFreq, integer);
			}
		}

		return birdOfMaxFreq;
	}

	@Test
	public void sherlockAndAnagrams() {
		assertEquals(4, sherlockAndAnagrams("abba"));
		assertEquals(3, sherlockAndAnagrams("ifailuhkqq"));
		assertEquals(10, sherlockAndAnagrams("kkkk"));
	}

	private int sherlockAndAnagrams(String s) {
		// go through a string and add every value to a hashmap
		HashMap<String, Integer> map = new HashMap<>();

		// total of anagrams
		int total = 0;

		// for each key, add one to value
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				// get substring and sort it!
				String sub = s.substring(i, j);

				// sorting the string
				char tempArray[] = sub.toCharArray();
				Arrays.sort(tempArray);
				sub = new String(tempArray);

				if (map.containsKey(sub)) {
					// adds one to last value
					int oldValue = map.get(sub);
					// total++ WRONG
					// backtracking of previous equals values or combinations of elements
					total += oldValue; // RIGHT
					map.put(sub, ++oldValue);
				} else {
					// add to map if not seen
					map.put(sub, 1);
				}
			}
		}
		return total;
	}

	@SuppressWarnings("serial")
	@Test
	public void diagonalDifference() {
		List<List<Integer>> arr = new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(11);
						add(2);
						add(4);

					}
				});

				add(new ArrayList<Integer>() {
					{
						add(4);
						add(5);
						add(6);

					}
				});

				add(new ArrayList<Integer>() {
					{
						add(10);
						add(8);
						add(-12);

					}
				});
			}
		};

		assertEquals(15, diagonalDifference(arr));
	}

	/**
	 * @param arr
	 */
	private int diagonalDifference(List<List<Integer>> arr) {
		double leftDiag = 0;
		double righttDiag = 0;

		int j = arr.size() - 1;

		for (int i = 0; i < arr.size(); i++) {

			List<Integer> innList = arr.get(i);

			righttDiag += innList.get(j);
			leftDiag += innList.get(arr.size() - 1 - j);

			j--;
		}

		return (int) Math.abs(leftDiag - righttDiag);
	}

	@Test
	public void aVeryBigSum() {

		long[] ar = new long[] { 1000000001, 1000000002, 1000000003, 1000000004, 1000000005 };

		assertEquals(5000000015L, LongStream.of(ar).sum());
	}

	@SuppressWarnings("serial")
	@Test
	public void compareTriplets() {

		List<Integer> list = compareTriplets(new ArrayList<Integer>() {
			{
				add(17);
				add(28);
				add(30);

			}
		}, new ArrayList<Integer>() {
			{
				add(99);
				add(16);
				add(8);
			}
		});

		Arrays.equals(new int[] { 2, 1 }, list.stream().mapToInt(Integer::intValue).toArray());

		List<Integer> list1 = compareTriplets(new ArrayList<Integer>() {
			{
				add(5);
				add(6);
				add(7);

			}
		}, new ArrayList<Integer>() {
			{
				add(3);
				add(6);
				add(10);
			}
		});

		Arrays.equals(new int[] { 1, 1 }, list1.stream().mapToInt(Integer::intValue).toArray());
	}

	private List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {

		int countAlice = 0;
		int countBob = 0;

		for (int i = 0; i < 3; i++) {

			if (a.get(i) > b.get(i))
				countAlice++;
			else if (a.get(i) < b.get(i))
				countBob++;

		}

		List<Integer> toReturn = new LinkedList<Integer>();
		toReturn.add(countAlice);
		toReturn.add(countBob);

		return toReturn;
	}

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
