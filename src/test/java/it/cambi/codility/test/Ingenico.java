/**
 * 
 */
package it.cambi.codility.test;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Ingenico {

	@Test
	public void diverseDeputation() {
		int m = 2;
		int w = 3;

		System.out.println(ways(m, w, 3, 1));
	}

	private int fact(int n) {
		int fact = 1;
		for (int i = 2; i <= n; i++)
			fact *= i;
		return fact;
	}

	// Function to calculate ncr
	private int ncr(int n, int r) {
		int ncr = fact(n) / (fact(r) * fact(n - r));
		return ncr;
	}

	// Function to calculate
	// the total possible ways
	private int ways(int m, int w, int n, int k) {

		int ans = 0;
		while (m >= k) {
			if (n - k != 0) {
				ans += ncr(m, k) * ncr(w, n - k);
			}
			k += 1;
		}

		return ans;
	}

	@Test
	public void deposit() {
		NavigableMap<Double, Integer> moneyMap = new TreeMap<Double, Integer>(Collections.reverseOrder());

		String s = "0.20:99 0.50:99 1:100 10:100";

		String[] split = s.split("\\s");

		for (int i = 0; i < split.length; i++) {
			String[] split1 = split[i].split(":");

			moneyMap.put(new Double(split1[0]), Integer.parseInt(split1[1]));
		}

		double withDraw = 0.45;

		for (Entry<Double, Integer> entry : moneyMap.entrySet()) {

			if (withDraw < entry.getKey())
				continue;

			boolean hasLeft = true;

			while (hasLeft) {

				withDraw -= entry.getKey();
				moneyMap.put(entry.getKey(), moneyMap.get(entry.getKey()) - 1);

				if (withDraw < entry.getKey())
					hasLeft = false;
			}

			if (withDraw == 0)
				break;
		}

	}

}
