/** */
package it.cambi.codility;

import it.cambi.codility.model.unionfind.QuickFind;
import it.cambi.codility.model.unionfind.QuickUnion;
import it.cambi.codility.model.unionfind.WeightedUnion;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author luca */
@TestMethodOrder(OrderAnnotation.class)
class UnionFindTest {

  @Test
  @Order(1)
  public void quickFindTest() {
    QuickFind unionFind = new QuickFind(10);

    unionFind.union(1, 2);
    unionFind.union(2, 3);

    assertTrue(Arrays.equals(new int[] {0, 3, 3, 3, 4, 5, 6, 7, 8, 9}, unionFind.getElements()));
    assertTrue(unionFind.areConnected(1, 2));

    unionFind.union(6, 7);
    unionFind.union(7, 2);
    assertTrue(Arrays.equals(new int[] {0, 3, 3, 3, 4, 5, 3, 3, 8, 9}, unionFind.getElements()));
  }

  @Test
  @Order(2)
  public void quickUnionTest() {

    QuickUnion unionFind = new QuickUnion(10);

    unionFind.union(1, 2);
    unionFind.union(2, 3);

    assertTrue(Arrays.equals(new int[] {0, 2, 3, 3, 4, 5, 6, 7, 8, 9}, unionFind.getElements()));
    assertTrue(unionFind.areConnected(1, 2));

    unionFind.union(6, 7);
    unionFind.union(7, 8);
    unionFind.union(9, 7);

    assertTrue(Arrays.equals(new int[] {0, 2, 3, 3, 4, 5, 7, 8, 8, 8}, unionFind.getElements()));

    // We assign the root of 2 to the root of 7
    unionFind.union(6, 2);

    assertTrue(Arrays.equals(new int[] {0, 2, 3, 3, 4, 5, 7, 8, 3, 8}, unionFind.getElements()));
  }

  @Test
  @Order(3)
  public void weightedUnionTest() {

    WeightedUnion unionFind = new WeightedUnion(10);

    unionFind.union(1, 2);
    unionFind.union(2, 3);

    assertTrue(Arrays.equals(new int[] {0, 1, 1, 1, 4, 5, 6, 7, 8, 9}, unionFind.getElements()));

    assertTrue(unionFind.areConnected(1, 2));

    unionFind.union(6, 7);
    unionFind.union(7, 8);
    unionFind.union(9, 7);

    assertTrue(Arrays.equals(new int[] {0, 1, 1, 1, 4, 5, 6, 6, 6, 6}, unionFind.getElements()));

    unionFind.union(7, 2);

    assertTrue(Arrays.equals(new int[] {0, 6, 1, 1, 4, 5, 6, 6, 6, 6}, unionFind.getElements()));

    assertTrue(unionFind.root(4) == 1);
  }
}
