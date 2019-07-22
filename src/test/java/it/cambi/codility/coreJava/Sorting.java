/**
 * 
 */
package it.cambi.codility.coreJava;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Sorting {

	@Test
	public void selectionsort() {
		int[] arr = { 3, 2, 1 };
		selectionSort(arr, 0, 2);
		int[] arr1 = { 1, 2, 3 };

		assertTrue(Arrays.equals(arr1, arr));
	}

	public void selectionSort(int[] list, int lo, int hi) { // sort list[lo] to list[hi] in ascending order
		for (int h = lo; h < hi; h++) {
			int s = getSmallest(list, h, hi);
			swap(list, h, s);
		}
	}

	public int getSmallest(int list[], int lo, int hi) { // return location of smallest from list[lo..hi]
		int small = lo;
		for (int h = lo + 1; h <= hi; h++)
			if (list[h] < list[small])
				small = h;
		return small;
	}

	public void swap(int list[], int i, int j) { // swap elements list[i] and list[j]
		int hold = list[i];
		list[i] = list[j];
		list[j] = hold;
	}

	@Test
	public void insertionSort() {

		int[] arr = { 3, 2, 6, 1, 5 };
		insertionSort1(arr, 0, 4);
		int[] arr1 = { 1, 2, 3, 5, 6 };

		assertTrue(Arrays.equals(arr1, arr));
	}

	public static void insertionSort1(int list[], int lo, int hi) { // sort list[lo] to list[hi] in ascending order
		for (int h = lo + 1; h <= hi; h++) {
			int key = list[h];
			int k = h - 1; // start comparing with previous item
			while (k >= lo && key < list[k]) {
				list[k + 1] = list[k];
				--k;
			}
			list[k + 1] = key;
		} // end for

	}
}
