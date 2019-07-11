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
	private int key;
	private Bnode left, right;

	/**
	 * 
	 */
	public Bnode(int key) {

		this.setKey(key);
		this.setLeft(setRight(null));
	}

	/**
	 * @return the left
	 */
	public Bnode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(Bnode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Bnode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public Bnode setRight(Bnode right) {
		this.right = right;
		return right;
	}

	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}
}
