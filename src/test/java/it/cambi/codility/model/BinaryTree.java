/**
 * 
 */
package it.cambi.codility.model;

/**
 * @author luca
 *
 */
public class BinaryTree {

	private Node root;

	public BinaryTree(int key) {
		root = new Node(key);
	};

	/*
	 * Given a binary tree. Print its nodes in level order using array for
	 * implementing queue
	 */
	int size() {
		return size(root);
	}

	/* computes number of nodes in tree */
	int size(Node node) {
		if (node == null)
			return 0;
		else
			return (size(node.left) + 1 + size(node.right));
	}

	// This method mainly calls insertRec()
	void insert(int key) {
		root = insertRec(root, key);
	}

	/* A recursive function to insert a new key in BST */
	Node insertRec(Node root, int key) {

		/* If the tree is empty, return a new node */
		if (root == null) {
			root = new Node(key);
			return root;
		}

		/* Otherwise, recur down the tree */
		if (key < root.data)
			root.setLeft(insertRec(root.left, key));
		else if (key > root.data)
			root.setRight(insertRec(root.right, key));

		/* return the (unchanged) node pointer */
		return root;
	}

	// A utility function to search a given key in BST
	public Node search(Node root, int key) {
		// Base Cases: root is null or key is present at root
		if (root == null || root.data == key)
			return root;

		// val is greater than root's key
		if (root.data > key)
			return search(root.left, key);

		// val is less than root's key
		return search(root.right, key);
	}

	/**
	 * 
	 */
	public void inOrderTrasv() {

		if (root != null) {
			inOrderTrasv(root.left);
			System.out.println(root.data);
			inOrderTrasv(root.right);
		}
	}

	private void inOrderTrasv(Node root) {

		if (root != null) {
			inOrderTrasv(root.left);
			System.out.println(root.data);
			inOrderTrasv(root.right);
		}
	}

	public void preOrderTrasv() {

		if (root != null) {
			System.out.println(root.data);
			preOrderTrasv(root.left);
			preOrderTrasv(root.right);
		}
	}

	private void preOrderTrasv(Node root) {

		if (root != null) {
			System.out.println(root.data);
			preOrderTrasv(root.left);
			preOrderTrasv(root.right);
		}
	}

	public void postOrderTrasv() {

		if (root != null) {
			postOrderTrasv(root.left);
			postOrderTrasv(root.right);
			System.out.println(root.data);
		}
	}

	private void postOrderTrasv(Node root) {

		if (root != null) {
			preOrderTrasv(root.left);
			preOrderTrasv(root.right);
			System.out.println(root.data);
		}
	}

	public static Node getBST() {
		Node root = new Node(50);

		Node left = new Node(30);
		left.setLeft(new Node(20));
		left.setRight(new Node(40));

		root.setLeft(left);

		Node right = new Node(70);
		right.setRight(new Node(80));
		right.setLeft(new Node(60));

		root.setRight(right);
		return root;
	}
}
