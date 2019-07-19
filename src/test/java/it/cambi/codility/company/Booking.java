/**
 * 
 */
package it.cambi.codility.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Booking {

	@Test
	public void getLuckyFloorNumber() {
		int n = 12;

		for (int i = 0; i <= n; i++) {

			n = checkValue(new Integer(i), n);
		}

		assertEquals(15, n);
	}

	private Integer checkValue(Integer aInt, int n) {
		return aInt.toString().indexOf("4") >= 0 || aInt.toString().indexOf("13") >= 0 ? ++n : n;
	}

	@Test
	public void packNumbers() {

		@SuppressWarnings("serial")
		List<Integer> arr = new ArrayList<Integer>() {
			{
				add(5);
				add(5);
				add(5);
				add(7);
				add(7);
				add(3);
				add(4);
				add(7);

			}
		};

		List<String> list = new ArrayList<String>();

		Integer compare = arr.get(0);
		Integer count = 1;
		Integer toCompare = 0;
		for (int i = 1; i < arr.size(); i++) {
			toCompare = arr.get(i);

			if (toCompare - compare == 0) {
				count++;
				continue;
			}

			list.add(compare.toString() + (count == 1 ? "" : ":" + count.toString()));
			count = 1;
			compare = toCompare;

		}

		list.add(toCompare.toString() + (count == 1 ? "" : ":" + count.toString()));

		String s = list.stream().map(Object::toString).collect(Collectors.joining(" "));

		assertEquals("5:3 7:2 3 4 7", s);
	}

}
