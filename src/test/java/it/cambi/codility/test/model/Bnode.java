/**
 * 
 */
package it.cambi.codility.test.model;

/**
 * @author luca
 *
 */
/**
 * @author luca
 *
 */
public class Bnode {
	public int key;
	public Bnode left, right;

	/**
	 * 
	 */
	public Bnode(int key) {

		this.setKey(key);
		this.setLeft(setRight(null));
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Bnode left) {
		this.left = left;
	}

	/**
	 * @param right the right to set
	 */
	public Bnode setRight(Bnode right) {
		this.right = right;
		return right;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}

	public static Bnode getExampleNode() {
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
}
