package company;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ExpertLeadInterview {

  @Test
  void findDifferenceBetweenPoints() {
    assertArrayEquals(
        new int[][] {{3, 2}, {4, 4}, {1, 4}, {5, 1}},
        findDifferenceBetweenPoints(new int[][] {{1, 4}, {4, 4}, {3, 2}, {5, 1}}));
  }

  private int[][] findDifferenceBetweenPoints(int[][] coordinateSet) {

    double minDist = Double.MAX_VALUE;
    double maxDist = Double.MIN_VALUE;
    double tmpDist;

    int[][] sol = new int[4][4];

    for (int i = 0; i < coordinateSet.length; i++) {

      for (int j = 1; j < coordinateSet.length && j != i; j++) {

        tmpDist =
            Math.sqrt(
                Math.pow(coordinateSet[i][0] - coordinateSet[j][0], 2)
                    + Math.pow(coordinateSet[i][1] - coordinateSet[j][1], 2));

        if (tmpDist < minDist) {
          minDist = tmpDist;
          sol[0] = coordinateSet[i];
          sol[1] = coordinateSet[j];
        }

        if (tmpDist > maxDist) {
          maxDist = tmpDist;
          sol[2] = coordinateSet[i];
          sol[3] = coordinateSet[j];
        }
      }
    }

    return sol;
  }
}
