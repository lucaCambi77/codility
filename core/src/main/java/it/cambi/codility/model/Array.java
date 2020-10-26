package it.cambi.codility.model;

public class Array {

  /**
   * Binary search of element in array
   *
   * <p>First we sort the array, then we loop through the array looking for the middle element
   *
   * <p>If searched element is less than middle, we search in lower half
   *
   * <p>If searched element is greater than middle, we search in upper half
   *
   * <p>else we have found the element
   *
   * @param a
   * @param fromIndex
   * @param toIndex
   * @param key
   * @return
   */
  public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = a[mid];

      if (midVal < key) low = mid + 1;
      else if (midVal > key) high = mid - 1;
      else return mid; // key found
    }

    return -(low + 1); // key not found.
  }

  /**
   * Recursive implementation of binary search
   *
   * <p>Same as above but we use recursion
   *
   * @param sortedArray
   * @param key
   * @param low
   * @param high
   * @return
   */
  public int binarySearchRecursively(int[] sortedArray, int key, int low, int high) {
    int middle = (low + high) / 2;

    if (high < low) {
      return -1;
    }

    if (key == sortedArray[middle]) {
      return middle;
    } else if (key < sortedArray[middle]) {
      return binarySearchRecursively(sortedArray, key, low, middle - 1);
    } else {
      return binarySearchRecursively(sortedArray, key, middle + 1, high);
    }
  }
}
