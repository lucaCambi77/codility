/** */
package it.cambi.codility;

import it.cambi.codility.model.Array;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
@ExtendWith(MockitoExtension.class)
class ArrayTest {

  @InjectMocks private Array array;

  @Test
  public void sortArrayTest() {

    int[] array = new int[] {1, 10, 5, 6, 2, 8, 4};

    this.array.quickSort(array, 0, array.length - 1);

    assertArrayEquals(new int[] {1, 2, 4, 5, 6, 8, 10}, array);
  }

  @Test
  public void binarySearchTest() {

    int[] array = new int[] {1, 10, 5, 6, 2, 8, 4};

    this.array.quickSort(array, 0, array.length - 1);

    assertEquals(1, this.array.binarySearch(array, 0, array.length - 1, 2));
    assertEquals(1, this.array.binarySearchWithRecursion(array, 2, 0, array.length - 1));
  }

  @Test
  public void bubbleSortAscend() {

    int[] a = new int[] {6, 4, 1};
    int numSwaps = array.bubbleSortAscend(a);
    assertArrayEquals(new int[] {1, 4, 6}, a);
    assertEquals(3, numSwaps);
  }

  @Test
  public void mergeSortTest() {
    Integer[] arr = new Integer[] {10, 5, 2, 7, 3, 9, 6, 2, 1, 4, 8};

    array.mergeSort(arr);

    assertArrayEquals(new Integer[] {1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
  }
}
