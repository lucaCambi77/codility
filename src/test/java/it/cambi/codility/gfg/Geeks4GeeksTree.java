/**
 * 
 */
package it.cambi.codility.gfg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import it.cambi.codility.test.model.Bnode;

/**
 * @author luca To get input from standard input
 * 
 *         Scanner sc = new Scanner(System.in);
 * 
 *         sc.nextLine();
 * 
 *         while(sc.hasNext()){ System.out.println(sc.next()); }
 * 
 */
public class Geeks4GeeksTree {

	@Test
	public void isBalanced() {

		Bnode root = new Bnode(4);

		Bnode left = new Bnode(1);

		Bnode leftSec = new Bnode(5);

		Bnode rightSec = new Bnode(3);

		left.setLeft(leftSec);
		left.setRight(rightSec);

		root.setLeft(left);

		Bnode right = new Bnode(2);
		root.setRight(right);

		// Bnode root = getExampleNode();

		System.out.println(isBalanced(root));
	}

	public boolean isBalanced(Bnode root) {

		if (root == null)
			return true;

		int ls = sumSubTrees(root.getLeft());
		int rs = sumSubTrees(root.getRight());

		if (rs - ls <= 1 && isBalanced(root.getLeft()) && isBalanced(root.getRight()))
			return true;

		return false;

	}

	/**
	 * Height is the number of nodes along the longest path from the root node down
	 * to the farthest leaf node.
	 * 
	 * @param root
	 * @return
	 */
	public int sumSubTrees(Bnode root) {

		int ret = root == null ? 0 : 1 + Math.max(sumSubTrees(root.getLeft()), sumSubTrees(root.getRight()));

		return ret;
	}

	@Test
	public void countLeaves() {
		Bnode root = getExampleNode();
		Queue<Bnode> queue = new LinkedList<Bnode>();
		queue.add(root);

		System.out.println(countLeaves(queue, 0));
	}

	public int countLeaves(Queue<Bnode> queue, int max) {
		int size = queue.size();
		if (size == 0)
			return max;

		List<Bnode> list = new ArrayList<Bnode>();

		for (Bnode root : queue) {
			if (null != root.getRight())
				list.add(root.getRight());
			if (null != root.getLeft())
				list.add(root.getLeft());
		}

		queue.addAll(list);

		for (int i = 0; i < size; i++) {
			queue.poll();
		}

		return countLeaves(queue, Math.max(size, queue.size()));

	}

	@Test
	public void reverseLevelOrderTrasv() {

		Bnode root = getExampleNode();
		Queue<Bnode> queue = new LinkedList<Bnode>();
		queue.add(root);
		Stack<Bnode> stack = new Stack<Bnode>();

		reverseLevelOrderTrasv(queue, stack);
		int size = stack.size();

		for (int i = 0; i < size; i++) {
			System.out.print(stack.pop().getKey() + " ");
		}
	}

	void reverseLevelOrderTrasv(Queue<Bnode> queue, Stack<Bnode> stack) {

		int size = queue.size();
		if (size == 0)
			return;

		List<Bnode> list = new ArrayList<Bnode>();

		for (Bnode root : queue) {
			if (null != root.getRight())
				list.add(root.getRight());
			if (null != root.getLeft())
				list.add(root.getLeft());
		}
		queue.addAll(list);
		for (int i = 0; i < size; i++) {
			stack.add(queue.poll());
		}
		reverseLevelOrderTrasv(queue, stack);
	}

	@Test
	public void levelOrderTrasversalLineByLin() {
		Bnode root = getExampleNode();

		Queue<Bnode> queue = new LinkedList<Bnode>();
		queue.add(root);

		levelOderTrasversal(queue, "$ ");

	}

	/**
	 * Level trasversal of Tree, print all nodes on the same level
	 */
	@Test
	public void levelOderTrasversal() {

		Bnode root = getExampleNode();

		Queue<Bnode> queue = new LinkedList<Bnode>();
		queue.add(root);

		levelOderTrasversal(queue, "");

	}

	void levelOderTrasversal(Queue<Bnode> queue, String message) {

		int size = queue.size();
		if (size == 0)
			return;

		for (Bnode root : queue) {
			System.out.print(root.getKey() + " ");
		}
		System.out.print(message);
		List<Bnode> list = new ArrayList<Bnode>();

		for (Bnode root : queue) {
			if (null != root.getLeft())
				list.add(root.getLeft());
			if (null != root.getRight())
				list.add(root.getRight());
		}
		queue.addAll(list);
		for (int i = 0; i < size; i++) {
			queue.poll();
		}
		levelOderTrasversal(queue, message);

	}

	@Test
	public void postOrderTrasveral() {
		Bnode root = getExampleNode();
		postOrderTrasvPrint(root);
	}

	private void postOrderTrasvPrint(Bnode root) {

		if (root != null) {
			postOrderTrasvPrint(root.getLeft());
			postOrderTrasvPrint(root.getRight());
			System.out.print(root.getKey() + " ");
		}
	}

	@Test
	public void preOrderTrasveral() {
		Bnode root = getExampleNode();
		preOrderTrasvPrint(root);
	}

	private void preOrderTrasvPrint(Bnode root) {

		if (root != null) {
			System.out.print(root.getKey() + " ");
			preOrderTrasvPrint(root.getLeft());
			preOrderTrasvPrint(root.getRight());
		}
	}

	@Test
	public void inOrderTrasveral() {
		Bnode root = getExampleNode();
		inOrderTrasvPrint(root);
	}

	private void inOrderTrasvPrint(Bnode root) {

		if (root != null) {
			inOrderTrasvPrint(root.getLeft());
			System.out.print(root.getKey() + " ");
			inOrderTrasvPrint(root.getRight());
		}
	}

	@Test
	public void sizeOfTree() {
		Bnode root = getExampleNode();
		/**
		 * Atomic integer is not working in G4G cause you can't import it, but it is
		 * interesting to use it
		 */
		AtomicInteger count = new AtomicInteger();
		setSizeofTree(root, count);

		List<Bnode> list = new ArrayList<Bnode>();

		setSizeofTree1(root, list);

		assertEquals(count.intValue(), list.size());

	}

	private AtomicInteger setSizeofTree(Bnode root, AtomicInteger count) {

		if (null != root) {
			count.incrementAndGet();
			setSizeofTree(root.getLeft(), count);

			setSizeofTree(root.getRight(), count);

		}

		return count;
	}

	private void setSizeofTree1(Bnode root, List<Bnode> list) {

		if (root != null) {
			setSizeofTree1(root.getLeft(), list);
			list.add(root);
			setSizeofTree1(root.getRight(), list);
		}
	}

	/**
	 * Assert is two trees are identical
	 * 
	 * In order trasversal is used but could be another one
	 */
	@Test
	public void twoIdenticalTrees() {

		Bnode root = getExampleNode();
		LinkedList<Integer> list = new LinkedList<Integer>();
		inOrderTrasv(root, list);

		Bnode root1 = getExampleNode();
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		inOrderTrasv(root1, list1);

		assertEquals(list, list1);
	}

	/**
	 * Find max height of tree
	 */
	@Test
	public void heightOfBinaryTree() {
		Bnode root = getExampleNode();
		assertEquals(4, getMaxHeight(root, 0));
	}

	private int getMaxHeight(Bnode root, int count) {

		if (root == null)
			return count;

		int tmp = count + 1;
		int left = getMaxHeight(root.getLeft(), tmp);
		int tmp1 = count + 1;
		int right = getMaxHeight(root.getRight(), tmp1);
		return Math.max(left, right);

	}

	/**
	 * Print root to leaf of all paths for tree
	 */
	@Test
	public void rootToLeafPaths() {
		Bnode root = getExampleNode();

		String rootKey = Integer.toString(root.getKey());

		printLeafPaths(root, rootKey);

	}

	/**
	 * @param root
	 * @param key
	 * @param left
	 */
	private void printLeafPaths(Bnode root, String map) {
		if (root != null) {
			if (root.getLeft() != null) {
				printLeafPaths(root.getLeft(), map + " " + Integer.toString(root.getLeft().getKey()));
			} else {

				System.out.print(map + " #");
				return;
			}

			if (root.getRight() != null) {
				printLeafPaths(root.getRight(), map + " " + Integer.toString(root.getRight().getKey()));
			}
		}

	}

	/**
	 * Find maximun number of nodes in a level
	 */
	@Test
	public void maximumWidthOfTree() {
		Bnode root = getExampleNode();

		Map<Integer, Integer> map = new HashMap<>();

		findMaxWidth(root, 0, map);

		int max = 0;
		for (Integer el : map.values()) {
			max = Math.max(max, el);
		}

		assertEquals(8, max);
	}

	void findMaxWidth(Bnode node, int count, Map<Integer, Integer> map) {

		if (node == null)
			return;

		int tmp = count + 1;

		findMaxWidth(node.getLeft(), tmp, map);

		findMaxWidth(node.getRight(), tmp, map);

		if (map.get(tmp) == null) {
			map.put(tmp, 1);
		} else {
			map.put(tmp, map.get(tmp) + 1);
		}

	}

	/**
	 * Find all nodes at a k distance from root
	 */
	@Test
	public void kDistanceFromRoot() {
		Bnode root = getExampleNode();

		printKDistance(root, 2, 0);

	}

	void printKDistance(Bnode node, int data, int count) {

		if (node == null)
			return;

		if (data == count)
			System.out.print(node.getKey() + " ");

		int tmp = count + 1;

		printKDistance(node.getLeft(), data, tmp);

		printKDistance(node.getRight(), data, tmp);
	}

	/**
	 * Find the level of a node in a tree
	 */
	@Test
	public void levelOfANodeInBinaryTree() {
		Bnode root = getExampleNode();

		assertEquals(3, getLevelFunct(root, 5, 1));
	}

	int getLevelFunct(Bnode node, int data, int count) {

		if (node == null)
			return 0;

		if (isData(node, data))
			return count;

		int tmp = count + 1;

		return getLevelFunct(node.getLeft(), data, tmp) + getLevelFunct(node.getRight(), data, tmp);
	}

	/**
	 * Print ancestors of a node
	 */
	@Test
	public void ancestorsInBinaryTree() {

		Bnode root = getExampleNode();

		printAncestors(root, 3);
	}

	/**
	 * @return
	 */
	private Bnode getExampleNode() {
		Bnode root = new Bnode(0);

		Bnode left = new Bnode(1);

		Bnode leftSec = new Bnode(3);
		leftSec.setLeft(new Bnode(7));
		leftSec.setRight(new Bnode(8));

		Bnode rightSec = new Bnode(4);
		rightSec.setLeft(new Bnode(9));
		rightSec.setRight(new Bnode(10));

		left.setLeft(leftSec);
		left.setRight(rightSec);

		root.setLeft(left);

		Bnode right = new Bnode(2);

		Bnode leftSec1 = new Bnode(5);
		leftSec1.setLeft(new Bnode(11));
		leftSec1.setRight(new Bnode(12));

		Bnode rightSec1 = new Bnode(6);
		rightSec1.setLeft(new Bnode(13));
		rightSec1.setRight(new Bnode(14));

		right.setLeft(leftSec1);
		right.setRight(rightSec1);

		root.setRight(right);
		return root;
	}

	boolean printAncestors(Bnode node, int target) {
		/* base cases */
		if (node == null)
			return false;

		if (node.getKey() == target)
			return true;

		/*
		 * If target is present in either left or right subtree of this node, then print
		 * this node
		 */
		if (printAncestors(node.getLeft(), target) || printAncestors(node.getRight(), target)) {
			System.out.print(node.getKey() + " ");
			return true;
		}

		/* Else return false */
		return false;
	}

	private boolean isData(Bnode node, int data) {

		return node.getKey() == data ? true : false;
	}

	/**
	 * This solution is not working on G4G but it could be a valid one
	 * 
	 * @param node
	 * @param finder
	 * @param data
	 * @return
	 */
	private String printAncestors(Bnode node, String finder, int data) {

		if (node == null)
			return "";

		if (isData(node, data))
			return finder;

		String retLeft = printAncestors(node.getLeft(), node.getKey() + " ", data);
		// rigth find
		String retRight = printAncestors(node.getRight(), node.getKey() + " ", data);

		return retLeft + retRight;
	}

	/**
	 * Sum of subnodes must be equal to root node value
	 */
	@Test
	public void sumOfBinaryTreeNodes() {

		Bnode root = new Bnode(3);

		root.setLeft(new Bnode(2));
		root.setRight(new Bnode(1));

		int ret = isSumTree(root);

		assertEquals(1, ret);
	}

	int sum(Bnode node) {
		return node == null ? 0 : sum(node.getLeft()) + node.getKey() + sum(node.getRight());
	}

	int isSumTree(Bnode node) {
		int ls, rs;

		/*
		 * If node is NULL or it's a leaf node then return true
		 */
		if ((node == null) || (node.getLeft() == null && node.getRight() == null))
			return 1;

		/* Get sum of nodes in left and right subtrees */
		ls = sum(node.getLeft());
		rs = sum(node.getRight());

		/*
		 * if the node and both of its children satisfy the property return 1 else 0
		 */
		if ((node.getKey() == ls + rs) && (isSumTree(node.getLeft()) != 0) && (isSumTree(node.getRight())) != 0)
			return 1;

		return 0;
	}

	/**
	 * Find sum of nodes
	 */
	@Test
	public void sumOfBinaryTree() {

		Bnode root = new Bnode(1);

		Bnode left = new Bnode(2);
		Bnode right = new Bnode(3);

		root.setLeft(left);
		root.setRight(right);

		List<Integer> list = new ArrayList<Integer>();

		inOrderTrasv(root, list);

		assertEquals(6, list.stream().mapToInt(i -> i.intValue()).sum());
	}

	private void inOrderTrasv(Bnode root, List<Integer> list) {

		if (root != null) {
			inOrderTrasv(root.getLeft(), list);
			list.add(root.getKey());
			inOrderTrasv(root.getRight(), list);
		}
	}

}
