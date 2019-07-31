/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.cambi.codility.test.model.Node;

/**
 * @author luca
 *
 */
public class HackerRankTreesTest {

	/**
	 * least common ancestor
	 */
	@Test
	public void lca() {

		Node root = new Node(4);

		Node left = new Node(2);

		left.left = new Node(1);
		left.right = new Node(3);

		root.left = left;

		Node right = new Node(7);
		right.left = new Node(6);
		root.right = right;

		List<Integer> listV1 = new ArrayList<Integer>();

		lca(root, 1, listV1);

		List<Integer> listV2 = new ArrayList<Integer>();

		lca(root, 7, listV2);

		int i = listV1.size() - 1;
		int j = listV2.size() - 1;
		int integer1 = listV1.get(i);
		int integer2 = listV2.get(j);

		int result = integer1;

		i--;
		j--;

		while (i >= 0 && j >= 0) {

			integer1 = listV1.get(i);
			integer2 = listV2.get(j);

			if (integer1 != integer2)
				break;

			i--;
			j--;
			result = integer1;
		}

		assertEquals(4, result);
	}

	public boolean lca(Node root, int v, List<Integer> list) {

		if (null == root)
			return false;

		if (root.data == v) {
			list.add(root.data);
			return true;
		}

		if (lca(root.left, v, list) || lca(root.right, v, list)) {
			list.add(root.data);
			return true;
		}

		return false;
	}

	@Test
	public void heightOfBinaryTree() {
		Node root = Node.getExampleNode();
		assertEquals(3, getMaxHeight(root, 0) - 1);
	}

	private int getMaxHeight(Node root, int count) {

		if (root == null)
			return count;

		int tmp = count + 1;
		int left = getMaxHeight(root.left, tmp);
		int tmp1 = count + 1;
		int right = getMaxHeight(root.right, tmp1);
		return Math.max(left, right);

	}

}
