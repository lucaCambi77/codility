/** */
package it.cambi.codility.interpolation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** @author luca */
public class Knn {

  public void impute(int[][] data) {
    int nosRows = data[0].length;
    int nosCols = data.length;
    double items = (double) nosRows * nosCols;
    /** Sample of neighbors */
    double k = Math.sqrt(items);

    int[] countRows = new int[nosRows];
    Set<Integer> rowsWithZero = new HashSet<>();
    Map<Integer, Integer> distanceMap = new HashMap<>();

    for (int i = 0; i < data.length; i++) {
      int tmp = i;
      long n =
          IntStream.range(0, data[i].length)
              .filter(v -> data[tmp][v] == 0)
              .map(v -> ++countRows[v])
              .count();

      if (n > 0) rowsWithZero.add(i);

      if (n == data[i].length) throw new RuntimeException("The whole row " + i + " is missing");

      if (countRows[i] == data.length)
        throw new RuntimeException("The whole column " + i + " is missing");
    }

    for (Integer withZero : rowsWithZero) {
      int[] x = data[withZero];

      for (int j = 0; j < data.length; j++) {
        int[] y = data[j];
        for (int m = 0; m < x.length; m++) {
          if (x[m] != 0 && y[m] != 0) {
            int d = x[m] - y[m];
            distanceMap.put(j, distanceMap.getOrDefault(j, 0) + d * d);
          }
        }
      }

      Map<Integer, Integer> sortedMap =
          distanceMap.entrySet().stream()
              .sorted(Map.Entry.comparingByValue())
              .collect(
                  Collectors.toMap(
                      Map.Entry::getKey,
                      Map.Entry::getValue,
                      (oldValue, newValue) -> oldValue,
                      LinkedHashMap::new));

      LinkedHashSet<Integer> neighbors = new LinkedHashSet<>();

      double ktmp = k;
      /** Create index of neighbors */
      while (ktmp > 0) {

        for (Map.Entry<Integer, Integer> aa : sortedMap.entrySet()) {
          neighbors.add(aa.getKey());
          ktmp--;
        }
      }

      for (int j = 0; j < data[withZero].length; j++) {
        if (x[j] == 0) {
          int n = 0;
          int m = 0;
          int replace = 0;
          while (m < nosCols) {
            for (Integer neighbor : neighbors) {
              if (data[neighbor][m] != 0) {
                replace = replace + data[neighbor][m];
                n++;
              }
            }
            m++;
          }

          if (n > 0) x[j] = replace / n;
        }
      }
    }
  }
}
