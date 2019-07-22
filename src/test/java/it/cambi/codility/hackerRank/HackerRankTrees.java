/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.cambi.codility.test.model.Bnode;

/**
 * @author luca
 *
 */
public class HackerRankTrees {

	@Test
	public void heightOfBinaryTree() {
		Bnode root = Bnode.getExampleNode();
		assertEquals(3, getMaxHeight(root, 0) - 1);
	}

	private int getMaxHeight(Bnode root, int count) {

		if (root == null)
			return count;

		int tmp = count + 1;
		int left = getMaxHeight(root.left, tmp);
		int tmp1 = count + 1;
		int right = getMaxHeight(root.right, tmp1);
		return Math.max(left, right);

	}

}
