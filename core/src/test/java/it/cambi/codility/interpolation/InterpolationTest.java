package it.cambi.codility.interpolation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpolationTest {

  private static Knn knn = new Knn();

  @Test
  public void should_find_knn() {
    int[][] data = new int[][] {{1, 2, 3, 4}, {6, 7, 8, 9}, {10, 11, 0, 12}, {15, 14, 13, 23}};

    knn.impute(data, 1);

    assertEquals(12, data[2][2]);
  }

  @Test
  public void should_find_knn_1() {
    int[][] data = new int[][] {{0, 2, 3, 4}, {6, 7, 8, 9}, {10, 11, 16, 12}, {15, 14, 13, 23}};

    knn.impute(data, 1);

    assertEquals(5, data[0][0]);
  }
}
