/**
 *
 */
package it.cambi.codility.interpolation;

import java.util.*;

/**
 * @author luca
 *
 */
public class Knn {

	//@Test
	public void testKnn() {
		int[][] data = new int[4][4];
		data[0][0] = 1;
		data[0][1] = 2;
		data[0][2] = 3;
		data[0][3] = 4;

		data[1][0] = 6;
		data[1][1] = 7;
		data[1][2] = 8;
		data[1][3] = 9;

		data[2][0] = 10;
		data[2][1] = 11;
		data[2][2] = 0;
		data[2][3] = 12;

		data[3][0] = 15;
		data[3][1] = 14;
		data[3][2] = 13;
		data[3][3] = 23;

		impute(data);

		System.out.println(Arrays.toString(data));
	}

	public void impute(int[][] data) {
		int nosRows = data[0].length;
		int nosCols = data.length;
		double items = (double) nosRows * nosCols;
		/**
		 * Sample of neighbors
		 */
		double k = Math.sqrt(items);

		int[] countRows = new int[nosRows];
		Set<Integer> rowsWithZero = new HashSet<Integer>();

		for (int i = 0; i < data.length; i++) {
			int n = 0;
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 0) {
					rowsWithZero.add(i);
					n++;
					countRows[j]++;
				}
			}

			if (n == data[i].length) {
				throw new RuntimeException("The whole row " + i + " is missing");
			}

			if (countRows[i] == data.length) {
				throw new RuntimeException("The whole column " + i + " is missing");
			}
		}

		for (Integer withZero : rowsWithZero) {
			Map<Integer, Integer> distanceMap = new HashMap<Integer, Integer>();

			int[] x = data[withZero];

			/**
			 * Create a distance map
			 */
			int[] dist = new int[data.length];
			for (int j = 0; j < data.length; j++) {
				int[] y = data[j];
				dist[j] = 0;
				int n = 0;
				for (int m = 0; m < x.length; m++) {
					if (x[m] != 0 && y[m] != 0) {
						int d = x[m] - y[m];
						distanceMap.put(j, distanceMap.getOrDefault(j, 0) + d * d);
						dist[j] += d * d;
						n++;
					}
				}
				dist[j] = x.length * dist[j] / n;
			}

			Map<Integer, Integer> sortedMap = sortByValue(distanceMap);
			LinkedHashSet<Integer> neighbors = new LinkedHashSet<Integer>();

			double ktmp = k;
			/**
			 * Create index of neighbors
			 */
			while (ktmp > 0) {

				for (Map.Entry<Integer, Integer> aa : sortedMap.entrySet()) {
					neighbors.add(aa.getKey());
					ktmp--;
				}
			}

			for (int j = 0; j < data[withZero].length; j++) {
				if (x[j] == 0) {
					int n = 0;
					int m = 0;
					int replace = 0;
					while (m < nosCols) {
						for (Integer neighbor : neighbors) {
							if (data[neighbor][m] != 0) {
								replace = replace + data[neighbor][m];
								n++;
							}
						}
						m++;
					}

					x[j] = replace / n;
				}
			}
		}
	}

	public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public void imputes(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			int n = 0;
			int sum = 0;

			for (int x : data[i]) {
				if (x != 0) {
					n++;
					sum += x;
				}
			}

			if (n == 0) {
				throw new RuntimeException("The whole row " + i + " is missing");
			}

			if (n < data[i].length) {
				int avg = sum / n;
				for (int j = 0; j < data[i].length; j++) {
					if (data[i][j] == 0) {
						data[i][j] = avg;
					}
				}
			}
		}
	}
}
