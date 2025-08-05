/** */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
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
            5,
            9,
            true),
        Arguments.of(
            new int[][] {
              {0, 7}, {0, 8}, {6, 1}, {2, 0}, {0, 4}, {5, 8}, {4, 7}, {1, 3}, {3, 5}, {6, 5}
            },
            7,
            5,
            true),
        Arguments.of(new int[][] {{0, 1}, {1, 2}, {2, 0}}, 0, 2, true));
  }

  @ParameterizedTest
  @MethodSource("matrixProvider")
  void validPath(int[][] matrix, int source, int dest, boolean expected) {
    assertEquals(expected, validPath(matrix, source, dest));
  }

  boolean validPath(int[][] edges, int source, int destination) {
    int n = edges.length;

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

  @Test
  void wallsAndGates() {
    int[][] rooms =
        new int[][] {
          {2147483647, -1, 0, 2147483647},
          {2147483647, 2147483647, 2147483647, -1},
          {2147483647, -1, 2147483647, -1},
          {0, -1, 2147483647, 2147483647}
        };

    wallsAndGates(rooms);

    assertArrayEquals(
        new int[][] {{3, -1, 0, 1}, {2, 2, 1, -1}, {1, -1, 2, -1}, {0, -1, 3, 4}}, rooms);
  }

  public void wallsAndGates(int[][] rooms) {
    if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

    int m = rooms.length;
    int n = rooms[0].length;
    Queue<int[]> queue = new LinkedList<>();

    // Step 1: Enqueue all gates (cells with value 0)
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rooms[i][j] == 0) {
          queue.offer(new int[] {i, j});
        }
      }
    }

    // Direction vectors for moving up, down, left, right
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // Step 2: BFS from all gates
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      for (int[] dir : dirs) {
        int r = row + dir[0];
        int c = col + dir[1];

        // Skip out-of-bounds or walls or already visited cells
        if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != Integer.MAX_VALUE) {
          continue;
        }

        // Update distance and add to queue
        rooms[r][c] = rooms[row][col] + 1;
        queue.offer(new int[] {r, c});
      }
    }
  }

  @Test
  void numIslands() {
    assertEquals(
        3,
        numIslands(
            new char[][] {
              {'1', '1', '0', '0', '0'},
              {'1', '1', '0', '0', '0'},
              {'0', '0', '1', '0', '0'},
              {'0', '0', '0', '1', '1'}
            }));
    assertEquals(
        1,
        numIslands(
            new char[][] {
              {'1', '1', '1', '1', '0'},
              {'1', '1', '0', '1', '0'},
              {'1', '1', '0', '0', '0'},
              {'0', '0', '0', '0', '0'}
            }));
  }

  int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  private int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int numIslands = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1' && grid[i][j] != 'x') {
          bfs(grid, i, j, m, n);
          numIslands++;
        }
      }
    }
    return numIslands;
  }

  void bfs(char[][] grid, int i, int j, int m, int n) {

    grid[i][j] = 'x';
    for (int[] dir : dirs) {

      int r = i + dir[0];
      int c = j + dir[1];

      if (r < 0 || c < 0 || r >= m || c >= n) continue;

      if (grid[r][c] == '1') bfs(grid, r, c, m, n);
    }
  }
}
