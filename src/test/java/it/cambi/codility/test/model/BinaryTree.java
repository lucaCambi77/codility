/**
 * 
 */
package it.cambi.codility.test.model;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class BinaryTree {

	private Bnode root;

	public BinaryTree(int key) {
		root = new Bnode(key);
	};

	/*
	 * Given a binary tree. Print its nodes in level order using array for
	 * implementing queue
	 */
	int size() {
		return size(root);
	}

	/* computes number of nodes in tree */
	int size(Bnode node) {
		if (node == null)
			return 0;
		else
			return (size(node.getLeft()) + 1 + size(node.getRight()));
	}

	// This method mainly calls insertRec()
	void insert(int key) {
		root = insertRec(root, key);
	}

	/* A recursive function to insert a new key in BST */
	Bnode insertRec(Bnode root, int key) {

		/* If the tree is empty, return a new node */
		if (root == null) {
			root = new Bnode(key);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (key < root.getKey())
			root.setLeft(insertRec(root.getLeft(), key));
		else if (key > root.getKey())
			root.setRight(insertRec(root.getRight(), key));

		/* return the (unchanged) node pointer */
		return root;
	}

	// A utility function to search a given key in BST
	public Bnode search(Bnode root, int key) {
		// Base Cases: root is null or key is present at root
		if (root == null || root.getKey() == key)
			return root;

		// val is greater than root's key
		if (root.getKey() > key)
			return search(root.getLeft(), key);

		// val is less than root's key
		return search(root.getRight(), key);
	}

	/**
	 * 
	 */
	public void inOrderTrasv() {

		if (root != null) {
			inOrderTrasv(root.getLeft());
			System.out.println(root.getKey());
			inOrderTrasv(root.getRight());
		}
	}

	private void inOrderTrasv(Bnode root) {

		if (root != null) {
			inOrderTrasv(root.getLeft());
			System.out.println(root.getKey());
			inOrderTrasv(root.getRight());
		}
	}

	public void preOrderTrasv() {

		if (root != null) {
			System.out.println(root.getKey());
			preOrderTrasv(root.getLeft());
			preOrderTrasv(root.getRight());
		}
	}

	private void preOrderTrasv(Bnode root) {

		if (root != null) {
			System.out.println(root.getKey());
			preOrderTrasv(root.getLeft());
			preOrderTrasv(root.getRight());
		}
	}

	public void postOrderTrasv() {

		if (root != null) {
			postOrderTrasv(root.getLeft());
			postOrderTrasv(root.getRight());
			System.out.println(root.getKey());
		}
	}

	private void postOrderTrasv(Bnode root) {

		if (root != null) {
			preOrderTrasv(root.getLeft());
			preOrderTrasv(root.getRight());
			System.out.println(root.getKey());
		}
	}

	@Test
	public void test() {

		System.out.println("topa");
	}
}
