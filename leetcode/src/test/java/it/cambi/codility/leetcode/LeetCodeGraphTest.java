/** */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author luca
 */
class LeetCodeGraphTest {

  @Test
  public void findJudge() {
    assertEquals(2, findJudge(2, new int[][] {{1, 2}}));
    assertEquals(3, findJudge(3, new int[][] {{1, 3}, {2, 3}}));
    assertEquals(-1, findJudge(3, new int[][] {{1, 3}, {2, 3}, {3, 1}}));
    assertEquals(-1, findJudge(3, new int[][] {{1, 2}, {2, 3}}));
    assertEquals(3, findJudge(4, new int[][] {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
    assertEquals(
        -1,
        findJudge(
            11,
            new int[][] {
              {1, 8}, {1, 3}, {2, 8}, {2, 3}, {4, 8}, {4, 3}, {5, 8}, {5, 3}, {6, 8}, {6, 3},
              {7, 8}, {7, 3}, {9, 8}, {9, 3}, {11, 8}, {11, 3}
            }));
    assertEquals(1, findJudge(1, new int[][] {}));
    assertEquals(3, findJudge(4, new int[][] {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));
  }

  public int findJudge(int n, int[][] trust) {

    if (trust.length == 0) return n;

    int[][] dp = new int[n][2];

    for (int[] row : trust) {
      int per = row[0] - 1, trt = row[1] - 1;
      dp[per][0]++;
      dp[trt][1]++;
    }

    for (int i = 0; i < n; i++) if ((dp[i][0] == 0) && dp[i][1] == n - 1) return i + 1;

    return -1;
  }

  static Stream<Arguments> matrixProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4}, {4, 2}, {7, 4}, {4, 0}, {0, 9}, {5, 4}
            },
            10,
            5,
            9,
            true),
        Arguments.of(
            new int[][] {
              {0, 7}, {0, 8}, {6, 1}, {2, 0}, {0, 4}, {5, 8}, {4, 7}, {1, 3}, {3, 5}, {6, 5}
            },
            10,
            7,
            5,
            true),
        Arguments.of(new int[][] {{0, 1}, {1, 2}, {2, 0}}, 3, 0, 2, true));
  }

  @ParameterizedTest
  @MethodSource("matrixProvider")
  void validPath(int[][] matrix, int n, int source, int dest, boolean expected) {
    assertEquals(expected, validPath(n, matrix, source, dest));
  }

  boolean validPath(int n, int[][] edges, int source, int destination) {
    int[] parent = new int[n];
    int[] rank = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i;
      rank[i] = 0;
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      union(u, v, parent, rank);
    }

    return areConnected(source, destination, parent);
  }

  int root(int[] parent, int i) {
    if (i != parent[i]) {
      parent[i] = root(parent, parent[i]); // Path compression
    }
    return parent[i];
  }

  boolean areConnected(int a, int b, int[] parent) {
    return root(parent, a) == root(parent, b);
  }

  void union(int a, int b, int[] parent, int[] rank) {
    int rootA = root(parent, a);
    int rootB = root(parent, b);

    if (rootA != rootB) {
      // Union by rank
      if (rank[rootA] > rank[rootB]) {
        parent[rootB] = rootA;
      } else if (rank[rootA] < rank[rootB]) {
        parent[rootA] = rootB;
      } else {
        parent[rootB] = rootA;
        rank[rootA]++;
      }
    }
  }

}
