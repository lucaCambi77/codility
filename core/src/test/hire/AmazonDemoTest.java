package it.cambi.codility.hire;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmazonDemoTest {

  @Test
  public void test1() {

    assertEquals(2, findGCD(new int[] {2, 4, 6, 8, 16}, 5));
    assertEquals(1, findGCD(new int[] {2, 3, 4, 5, 6}, 5));
    assertEquals(2, findGCD(new int[] {2, 4, 6, 8, 10}, 5));
    assertEquals(2, findGCD(new int[] {2}, 1));
    assertEquals(0, findGCD(new int[] {0}, 1));
    assertEquals(0, findGCD(new int[] {}, 0));
  }

  @Test
  public void test2() {
    assertArrayEquals(
        new int[] {0, 1, 0, 0, 1, 0, 1, 0},
        getCellsState(new int[] {1, 0, 0, 0, 0, 1, 0, 0}, 1).stream().mapToInt(i -> i).toArray());

    assertArrayEquals(
        new int[] {0, 0, 0, 0, 0, 1, 1, 0},
        getCellsState(new int[] {1, 1, 1, 0, 1, 1, 1, 1}, 2).stream().mapToInt(i -> i).toArray());
  }

  private List<Integer> getCellsState(int[] cells, int days) {

    for (int i = 1; i <= days; i++) {
      int[] dayResults = new int[cells.length];

      for (int index = 0; index < cells.length; index++) {
        int leftValue = (index == 0) ? 0 : cells[index - 1];
        int rightValue = (index == cells.length - 1) ? 0 : cells[index + 1];
        dayResults[index] = (leftValue == rightValue) ? 0 : 1;
      }

      cells = dayResults;
    }

    List<Integer> result = new LinkedList<>();
    for (Integer t : cells) {
      result.add(t);
    }
    return result;
  }

  int findGCD(int arr[], int n) {
    if (null == arr || arr.length == 0) return 0;

    int result = arr[0];
    for (int i = 1; i < n; i++) {
      result = gcd(arr[i], result);

      if (result == 1) {
        return 1;
      }
    }

    return result;
  }

  int gcd(int a, int b) {
    if (a == 0) return b;
    return gcd(b % a, a);
  }
}
