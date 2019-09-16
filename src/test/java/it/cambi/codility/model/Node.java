/**
 * 
 */
package it.cambi.codility.model;

/**
 * @author luca
 *
 */
/**
 * @author luca
 *
 */
public class Node {
	public int data;
	public Node left, right;

	/**
	 * 
	 */
	public Node(int key) {

		this.setData(key);
		this.setLeft(setRight(null));
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	public Node setRight(Node right) {
		this.right = right;
		return right;
	}

	/**
	 * @param data the key to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	public static Node getExampleNode() {
		Node root = new Node(0);

		Node left = new Node(1);

		Node leftSec = new Node(3);
		leftSec.setLeft(new Node(7));
		leftSec.setRight(new Node(8));

		Node rightSec = new Node(4);
		rightSec.setLeft(new Node(9));
		rightSec.setRight(new Node(10));

		left.setLeft(leftSec);
		left.setRight(rightSec);

		root.setLeft(left);

		Node right = new Node(2);

		Node leftSec1 = new Node(5);
		leftSec1.setLeft(new Node(11));
		leftSec1.setRight(new Node(12));

		Node rightSec1 = new Node(6);
		rightSec1.setLeft(new Node(13));
		rightSec1.setRight(new Node(14));

		right.setLeft(leftSec1);
		right.setRight(rightSec1);

		root.setRight(right);
		return root;
	}
}
