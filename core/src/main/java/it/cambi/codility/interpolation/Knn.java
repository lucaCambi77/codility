/** */
package it.cambi.codility.interpolation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/** @author luca */
public class Knn {

  @AllArgsConstructor
  @Getter
  class Point {
    int x;
    int y;
  }

  // find a spiral around zero points
  public void impute(int[][] data) {
    int nosRows = data[0].length;
    int nosCols = data.length;

    Set<Point> rowsWithZero = new HashSet<>();

    for (int i = 0; i < data.length; i++) {
      int tmp = i;

      IntStream.range(0, data[i].length)
          .filter(v -> data[tmp][v] == 0)
          .mapToObj(v -> rowsWithZero.add(new Point(tmp, v)))
          .count();
    }

    for (Point point : rowsWithZero) {

      int x = point.getX();
      int y = point.getY();
      int sum = 0;

      int k = 1;
      int count = 0;
      if (y + k < nosCols) {
        sum += data[x][y + k];
        count++;
      }
      if (y - k >= 0) {
        sum += data[x][y - k];
        count++;
      }
      if (x + k < nosRows) {
        sum += data[x + k][y];
        count++;
      }
      if (x - k >= 0) {
        sum += data[x - k][y];
        count++;
      }
      if (y - k >= 0 && x - k >= 0) {
        sum += data[x - k][y - k];
        count++;
      }
      if (x - k >= 0 && y + k < nosCols) {
        sum += data[x - k][y + k];
        count++;
      }
      if (y + k < nosCols && x + k < nosRows) {
        sum += data[x + k][y + k];
        count++;
      }
      if (x + k < nosRows && y - k >= 0) {
        sum += data[x + k][y - k];
        count++;
      }

      data[x][y] = sum / count;
    }
  }
}
