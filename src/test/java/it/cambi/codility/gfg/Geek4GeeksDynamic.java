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
public class Geek4GeeksDynamic {

    
	@Test
	public void reachAGivenNumber() {
		int n = 20;
		// table[i] will store count of solutions for
		// value i.
		int table[] = new int[n + 1], i;

		// Initialize all table values as 0
		Arrays.fill(table, 0);

		// Base case (If given value is 0)
		table[0] = 1;

		// One by one consider given 3
		// moves and update the table[]
		// values after the index greater
		// than or equal to the value of
		// the picked move
		for (i = 3; i <= n; i++)
			table[i] += table[i - 3];
		for (i = 5; i <= n; i++)
			table[i] += table[i - 5];
		for (i = 10; i <= n; i++)
			table[i] += table[i - 10];

		System.out.println(Arrays.toString(table));
	}

}
