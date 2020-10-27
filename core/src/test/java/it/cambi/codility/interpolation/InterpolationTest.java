package it.cambi.codility.interpolation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpolationTest {

  private static Knn knn = new Knn();

  @Test
  public void testKnn() {
    int[][] data = new int[4][4];
    data[0][0] = 1;
    data[0][1] = 2;
    data[0][2] = 3;
    data[0][3] = 4;

    data[1][0] = 6;
    data[1][1] = 7;
    data[1][2] = 8;
    data[1][3] = 9;

    data[2][0] = 10;
    data[2][1] = 11;
    data[2][2] = 0;
    data[2][3] = 12;

    data[3][0] = 15;
    data[3][1] = 14;
    data[3][2] = 13;
    data[3][3] = 23;

    knn.impute(data);

    assertEquals(9, data[2][2]);
  }
}
