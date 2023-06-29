package it.cambi.codility.model;

public class Array {

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

    return -1; // key not found.
  }

  public int binarySearchWithRecursion(int[] sortedArray, int key, int low, int high) {
    if (high < low) {
      return -1;
    }

    int middle = (low + high) / 2;

    if (key == sortedArray[middle]) {
      return middle;
    } else if (key < sortedArray[middle]) {
      return binarySearchWithRecursion(sortedArray, key, low, middle - 1);
    } else {
      return binarySearchWithRecursion(sortedArray, key, middle + 1, high);
    }
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
  public void quickSort(int[] arr, int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }

  private int partition(int[] arr, int begin, int end) {
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

  public int bubbleSortAscend(int[] a) {

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

  private void swap(int[] a, int i, int j) {

    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  @SuppressWarnings("rawtypes")
  public Comparable[] mergeSort(Comparable[] list) {
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
  private void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
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

  public void selectionSort(int[] list, int lo, int hi) {
    for (int h = lo; h < hi; h++) {
      int s = getSmallest(list, h, hi);
      swapSelection(list, h, s);
    }
  }

  public int getSmallest(int list[], int lo, int hi) {
    int small = lo;
    for (int h = lo + 1; h <= hi; h++) if (list[h] < list[small]) small = h;
    return small;
  }

  public void swapSelection(int list[], int i, int j) { // swap elements list[i] and list[j]
    int hold = list[i];
    list[i] = list[j];
    list[j] = hold;
  }

  public void insertionSortLow(int list[], int lo, int hi) {
    for (int h = lo + 1; h <= hi; h++) {
      swapElements(list, lo, h);
    }
  }

  private void swapElements(int[] list, int lo, int h) {
    int key = list[h];
    int k = h - 1;

    while (k >= lo && key < list[k]) {
      list[k + 1] = list[k];
      --k;
    }

    list[k + 1] = key;
  }

  public void insertionSortTop(int list[], int lo, int hi) {
    for (int h = hi; h >= 0; h--) {
      swapElements(list, lo, h);
    }
  }
}
