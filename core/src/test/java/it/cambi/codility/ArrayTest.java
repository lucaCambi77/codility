/** */
package it.cambi.codility;

import it.cambi.codility.model.Array;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
public class ArrayTest {

  @Test
  public void sortArrayTest() {

    int[] array = new int[] {1, 10, 5, 6, 2, 8, 4};

    quickSort(array, 0, array.length - 1);

    assertArrayEquals(new int[] {1, 2, 4, 5, 6, 8, 10}, array);
  }

  /**
   * Quick sort using single pivot strategy
   *
   * <p>Note that Arrays.sort() use dual pivot strategy
   *
   * <p>We iterate through the array using a pivot (last element) using the leftmost element as
   * indexing
   *
   * <p>We then quick sort recursively from the left to the pivot and from the pivot to the end
   *
   * <p>We compare every element with the pivot, if it is less than we increase the index and swap
   * the element at that index.
   *
   * <p>We return the index as next pivot.
   *
   * <p>We basically divide every section in smaller part until there is only one element both ways.
   *
   * <p>We exit the recursion when we get the last element from the rightmost recursion
   *
   * @param arr
   * @param begin
   * @param end
   */
  public void quickSort(int arr[], int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }

  private int partition(int arr[], int begin, int end) {
    int pivot = arr[end];
    int i = (begin - 1);

    for (int j = begin; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;

        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
      }
    }

    int swapTemp = arr[i + 1];
    arr[i + 1] = arr[end];
    arr[end] = swapTemp;

    return i + 1;
  }

  @Test
  public void binarySearchTest() {

    int[] array = new int[] {1, 10, 5, 6, 2, 8, 4};

    quickSort(array, 0, array.length - 1);

    assertEquals(1, Array.binarySearch(array, 0, array.length - 1, 2));
  }

  @Test
  public void bubblesortAscend() {

    int[] a = new int[] {6, 4, 1};
    int numSwaps = bubblesortAscend(a);
    assertArrayEquals(new int[] {1, 4, 6}, a);
    assertEquals(3, numSwaps);
  }

  public int bubblesortAscend(int[] a) {

    int n = a.length;
    int numSwaps = 0;

    for (int i = 0; i < n; i++) {

      for (int j = 0; j < n - 1; j++) {
        // Swap adjacent elements if they are in decreasing order
        if (a[j] > a[j + 1]) {
          swap(a, j, j + 1);
          numSwaps++;
        }
      }
    }

    return numSwaps;
  }

  /**
   * @param i
   * @param j
   */
  private void swap(int[] a, int i, int j) {

    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  @Test
  public void mergeSortTest() {
    Integer[] arr = new Integer[] {10, 5, 2, 7, 3, 9, 6, 2, 1, 4, 8};

    mergeSort(arr);

    assertArrayEquals(new Integer[] {1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
  }

  @SuppressWarnings("rawtypes")
  public static Comparable[] mergeSort(Comparable[] list) {
    // If list is empty; no need to do anything
    if (list.length <= 1) {
      return list;
    }

    // Split the array in half in two parts
    Comparable[] first = new Comparable[list.length / 2];
    Comparable[] second = new Comparable[list.length - first.length];
    System.arraycopy(list, 0, first, 0, first.length);
    System.arraycopy(list, first.length, second, 0, second.length);

    // Sort each half recursively
    mergeSort(first);
    mergeSort(second);

    // Merge both halves together, overwriting to original array
    merge(first, second, list);
    return list;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
    // Index Position in first array - starting with first element
    int iFirst = 0;

    // Index Position in second array - starting with first element
    int iSecond = 0;

    // Index Position in merged array - starting with first position
    int iMerged = 0;

    // Compare elements at iFirst and iSecond,
    // and move smaller element at iMerged
    while (iFirst < first.length && iSecond < second.length) {
      if (first[iFirst].compareTo(second[iSecond]) < 0) {
        result[iMerged] = first[iFirst];
        iFirst++;
      } else {
        result[iMerged] = second[iSecond];
        iSecond++;
      }
      iMerged++;
    }
    // copy remaining elements from both halves - each half will have already sorted
    // elements
    System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
    System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
  }
}
