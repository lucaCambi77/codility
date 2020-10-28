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

  public void replaceZeroWithKnnData(int[][] data, int k) {
    int nosRows = data.length;
    int nosCols = data[0].length;

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
      int xStart = Math.max(x - k, 0);
      int vStart = Math.max(y - k, 0);
      int rows = x - k + (2 * k) + 1;
      int columns = y - k + (2 * k) + 1;

      int sum = 0;
      int count = 0;
      for (int i = xStart; i < (Math.min(rows, nosRows)); i++) {
        for (int j = vStart; j < (Math.min(columns, nosCols)); j++) {
          sum += data[i][j];
          count++;
        }
      }

      if (count > 1) data[x][y] = sum / (count - 1);
    }
  }
}
