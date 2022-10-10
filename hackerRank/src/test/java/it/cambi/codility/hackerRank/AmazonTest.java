package it.cambi.codility.hackerRank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonTest {

  @Test
  void test1() {
    assertEquals(3, test1(Arrays.asList(3, 2, 1)));
  }

  int test1(List<Integer> plates) {
    if (plates.size() == 1) return 0;

    int[] array = plates.stream().mapToInt(i -> i).toArray();

    List<Integer> sorted = plates.stream().sorted().collect(Collectors.toList());

    int min = sorted.get(0);
    int max = sorted.get(sorted.size() - 1);

    boolean found = false;
    int index = 0;
    int moves = 0;

    for (int i = 0; i < array.length; i++) {

      if (!found) {
        if (array[i] - min == 0) {
          index = i;
          found = true;
          i = 0;
        }
      } else {
        while (index - 1 >= 0) {
          int temp = array[index - 1];
          array[index - 1] = array[index];
          array[index] = temp;
          index--;
          moves++;
        }
        break;
      }
    }

    found = false;
    index = 0;

    for (int i = array.length - 1; i > 0; i--) {

      if (!found) {
        if (array[i] - max == 0) {
          index = i;
          found = true;
          i = array.length - 1;
        }
      } else {
        while (index + 1 < array.length) {
          int temp = array[index + 1];
          array[index + 1] = array[index];
          array[index] = temp;
          index++;
          moves++;
        }
        break;
      }
    }

    return moves;
  }

  @Test
  void test2() {
    assertEquals(3, test2(Arrays.asList(3, 6, 1, 3, 4), Arrays.asList(2, 1, 3, 4, 5), 25));
  }

  int test2(List<Integer> processingPower, List<Integer> bootingPower, int powerMax) {

    int size = processingPower.size();
    int groupLength = size;
    int right;
    int left;

    int rightLoop;
    int max;
    int sum;

    while (groupLength > 1) {
      right = size;
      while (right - groupLength >= 0) {
        sum = 0;
        left = right - groupLength;
        rightLoop = right - 1;

        max = Integer.MIN_VALUE;

        while (rightLoop >= left) {
          max = Math.max(max, bootingPower.get(rightLoop));
          sum += processingPower.get(rightLoop);

          rightLoop--;
        }

        if (((sum * groupLength) + max) <= powerMax) return groupLength;

        right--;
      }
      groupLength--;
    }

    return 0;
  }
}
