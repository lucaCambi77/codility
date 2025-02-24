package it.cambi.codility.model;

/** @author luca */
public class BinaryTree {

  private Node root;

  public BinaryTree(int key) {
    root = new Node(key);
  }

  int size() {
    return size(root);
  }

  int size(Node node) {
    if (node == null) return 0;
    else return (size(node.getLeft()) + 1 + size(node.getRight()));
  }

  public void insert(int key) {
    root = insertRec(root, key);
  }

  Node insertRec(Node root, int key) {

    if (root == null) {
      root = new Node(key);
      return root;
    }

    if (key < root.getData()) root.setLeft(insertRec(root.getLeft(), key));
    else if (key > root.getData()) root.setRight(insertRec(root.getRight(), key));

    return root;
  }

  // A utility function to search a given key in BST
  public Node search(Node root, int key) {
    // Base Cases: root is null or key is present at root
    if (root == null || root.getData() == key) return root;

    // val is greater than root's key
    if (root.getData() > key) return search(root.getLeft(), key);

    // val is less than root's key
    return search(root.getRight(), key);
  }

  /** */
  public void inOrderTrasv() {

    if (root != null) {
      inOrderTrasv(root.getLeft());
      System.out.println(root.getData());
      inOrderTrasv(root.getRight());
    }
  }

  private void inOrderTrasv(Node root) {

    if (root != null) {
      inOrderTrasv(root.getLeft());
      System.out.println(root.getData());
      inOrderTrasv(root.getRight());
    }
  }

  public void preOrderTrasv() {

    if (root != null) {
      System.out.println(root.getData());
      preOrderTrasv(root.getLeft());
      preOrderTrasv(root.getRight());
    }
  }

  private void preOrderTrasv(Node root) {

    if (root != null) {
      System.out.println(root.getData());
      preOrderTrasv(root.getLeft());
      preOrderTrasv(root.getRight());
    }
  }

  public void postOrderTrasv() {

    if (root != null) {
      postOrderTrasv(root.getLeft());
      postOrderTrasv(root.getRight());
      System.out.println(root.getData());
    }
  }

  private void postOrderTrasv(Node root) {

    if (root != null) {
      preOrderTrasv(root.getLeft());
      preOrderTrasv(root.getRight());
      System.out.println(root.getData());
    }
  }

  public static Node getBSTExample() {
    Node root = new Node(50);

    Node left = new Node(30);
    left.setLeft(new Node(20));
    left.setRight(new Node(40));

    root.setLeft(left);

    Node right = new Node(70);
    right.setLeft(new Node(60));
    right.setRight(new Node(80));

    root.setRight(right);
    return root;
  }
}
