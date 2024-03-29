/** */
package it.cambi.codility.gfg;

import it.cambi.codility.model.BinaryTree;
import it.cambi.codility.model.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author luca To get input from standard input
 *     <p>Scanner sc = new Scanner(System.in);
 *     <p>sc.nextLine();
 *     <p>while(sc.hasNext()){ System.out.println(sc.next()); }
 *     <p>// Or From solution BufferedReader br = new BufferedReader(new
 *     InputStreamReader(System.in)); int t = Integer.parseInt(br.readLine().trim()); //Inputting
 *     the testcases while(t > 0) { }
 */
class Geeks4GeeksTreeTest {

  @Test
  public void isBST() {

    Node node = new Node(1);
    node.setLeft(new Node(3));
    node.setRight(new Node(2));

    assertFalse(isBST(node, new LinkedHashSet<>(), null, null));

    assertTrue(isBST(BinaryTree.getBSTExample(), new LinkedHashSet<>(), null, null));

    Node node1 = new Node(20);
    Node nodeLeft = new Node(10);
    Node nodeLeftA = new Node(5);
    Node nodeLeftB = new Node(1);
    nodeLeftB.setRight(new Node(50));
    nodeLeft.setLeft(nodeLeftA);
    nodeLeftA.setLeft(nodeLeftB);
    node1.setLeft(nodeLeft);

    assertFalse(isBST(node1, new LinkedHashSet<>(), null, null));
  }

  private boolean isBST(
      Node root, LinkedHashSet<Integer> nodeList, Node previousLeft, Node previousright) {

    if (null == root) return true;

    if (!nodeList.add(root.getData())) return false;

    if (checkDataLeft(root, previousLeft)
        && checkDataRight(root, previousright)
        && isBST(root.getLeft(), nodeList, root, null)
        && isBST(root.getRight(), nodeList, null, root)) return true;

    return false;
  }

  private boolean checkDataLeft(Node root, Node previous) {
    if (null == root) return true;

    boolean checkLeft = false;
    boolean checkRight = false;

    if (null == root.getLeft()) checkLeft = true;
    else if (root.getLeft().getData() < root.getData()) checkLeft = true;

    if (null == root.getRight()) checkRight = true;
    else {
      if (root.getRight().getData() > root.getData()) checkRight = true;

      if (null != previous && !(root.getRight().getData() < previous.getData())) checkRight = false;
    }

    return checkRight && checkLeft;
  }

  private boolean checkDataRight(Node root, Node previous) {
    if (null == root) return true;

    boolean checkLeft = false;
    boolean checkRight = false;

    if (null == root.getLeft()) checkLeft = true;
    else {
      if (root.getLeft().getData() < root.getData()) checkLeft = true;

      if (null != previous && !(root.getLeft().getData() > previous.getData())) checkLeft = false;
    }

    if (null == root.getRight()) checkRight = true;
    else if (root.getRight().getData() > root.getData()) checkRight = true;

    return checkRight && checkLeft;
  }

  class Res {

    private Node pre;
    private Node succ;
  }

  @Test
  public void predecessorSuccessor() {

    Res p = new Res();
    Res s = new Res();
    int key = 65;
    predecessorSuccessor(BinaryTree.getBSTExample(), p, s, key);

    assertEquals("60 70", p.pre.getData() + " " + s.succ.getData());

    Node root = new Node(78);

    Node left = new Node(34);
    left.setLeft(new Node(12));
    left.setRight(new Node(45));
    root.setLeft(left);

    Node right = new Node(97);
    root.setRight(right);

    p = new Res();
    s = new Res();

    key = 34;
    predecessorSuccessor(root, p, s, key);
    assertEquals("12 45", p.pre.getData() + " " + s.succ.getData());

    Node root1 = new Node(78);

    Node left1 = new Node(24);
    left1.setLeft(new Node(18));
    left1.setRight(new Node(55));
    root1.setLeft(left1);

    Node right1 = new Node(80);
    right1.setRight(new Node(85));

    root1.setRight(right1);

    p = new Res();
    s = new Res();

    key = 40;

    predecessorSuccessor(root1, p, s, key);
    assertEquals("24 55", p.pre.getData() + " " + s.succ.getData());

    Node root2 = new Node(50);

    Node left1a = new Node(30);
    left1a.setLeft(new Node(20));
    left1a.setRight(new Node(40));
    root2.setLeft(left1a);

    Node right1a = new Node(70);
    right1a.setLeft(new Node(60));
    right1a.setRight(new Node(80));

    root2.setRight(right1a);

    p = new Res();
    s = new Res();

    key = 80;

    predecessorSuccessor(root2, p, s, key);
    assertEquals("70 -1", p.pre.getData() + " " + (s.succ == null ? "-1" : s.succ.getData()));

    p = new Res();
    s = new Res();

    key = 70;

    predecessorSuccessor(root2, p, s, key);
    assertEquals("60 80", p.pre.getData() + " " + (s.succ == null ? "-1" : s.succ.getData()));

    p = new Res();
    s = new Res();

    key = 100;

    predecessorSuccessor(root2, p, s, key);
    assertEquals("80 -1", p.pre.getData() + " " + (s.succ == null ? "-1" : s.succ.getData()));

    Node root3 = new Node(75);

    Node left2a = new Node(7);
    Node rightLeft2a = new Node(13);
    Node rightLeft2aa = new Node(18);

    Node rightLeft2aaa = new Node(44);
    rightLeft2aaa.setRight(new Node(45));
    rightLeft2aa.setRight(rightLeft2aaa);

    rightLeft2a.setRight(rightLeft2aa);

    rightLeft2a.setRight(rightLeft2aa);

    left2a.setRight(rightLeft2a);
    root3.setLeft(left2a);

    Node right2a = new Node(79);
    right2a.setRight(new Node(91));

    root3.setRight(right2a);

    p = new Res();
    s = new Res();

    key = 74;

    predecessorSuccessor(root3, p, s, key);

    assertEquals("45 75", p.pre.getData() + " " + (s.succ == null ? "-1" : s.succ.getData()));

    p = new Res();
    s = new Res();

    key = 7;

    predecessorSuccessor(root3, p, s, key);

    assertEquals(
        "-1 13",
        (p.pre == null ? "-1" : p.pre.getData())
            + " "
            + (s.succ == null ? "-1" : s.succ.getData()));

    Node root4 = new Node(75);

    Node left3a = new Node(7);
    Node rightLeft3a = new Node(13);
    Node rightLeft3aa = new Node(18);

    Node rightLeft3aaa = new Node(44);
    rightLeft3aaa.setRight(new Node(45));
    rightLeft3aa.setRight(rightLeft3aaa);

    rightLeft3a.setRight(rightLeft3aa);

    rightLeft3a.setRight(rightLeft3aa);

    left3a.setRight(rightLeft3a);
    root4.setLeft(left3a);

    Node right3a = new Node(79);
    right3a.setRight(new Node(91));

    root4.setRight(right3a);

    p = new Res();
    s = new Res();

    key = 44;

    predecessorSuccessor(root4, p, s, key);

    assertEquals(
        "18 45",
        (p.pre == null ? "-1" : p.pre.getData())
            + " "
            + (s.succ == null ? "-1" : s.succ.getData()));
  }

  private void predecessorSuccessor(Node root, Res p, Res s, int key) {

    if (root == null) return;

    if (root.getData() < key) {
      if (p.pre == null) p.pre = root;

      p.pre = p.pre.getData() > root.getData() ? p.pre : root;
    }

    if (root.getData() > key) {
      if (s.succ == null) s.succ = root;

      s.succ = s.succ.getData() > root.getData() ? root : s.succ;
    }

    predecessorSuccessor(root.getLeft(), p, s, key);
    predecessorSuccessor(root.getRight(), p, s, key);
  }

  @Test
  public void minValue() {

    assertEquals(20, minValue(BinaryTree.getBSTExample()));
  }

  private int minValue(Node node) {
    while (node.getLeft() != null) node = node.getLeft();

    return node.getData();
  }

  @Test
  public void isBalanced() {

    Node root = new Node(4);

    Node left = new Node(1);

    Node leftSec = new Node(5);

    Node rightSec = new Node(3);

    left.setLeft(leftSec);
    left.setRight(rightSec);

    root.setLeft(left);

    Node right = new Node(2);
    root.setRight(right);

    // Bnode root = Bnode.getExampleNode();

    System.out.println(isBalanced(root));
  }

  private boolean isBalanced(Node root) {

    if (root == null) return true;

    int ls = sumSubTrees(root.getLeft());
    int rs = sumSubTrees(root.getRight());

    return rs - ls <= 1 && isBalanced(root.getLeft()) && isBalanced(root.getRight());
  }

  /**
   * Height is the number of nodes along the longest path from the root node down to the farthest
   * leaf node.
   *
   * @param root
   * @return
   */
  private int sumSubTrees(Node root) {

    return root == null
        ? 0
        : 1 + Math.max(sumSubTrees(root.getLeft()), sumSubTrees(root.getRight()));
  }

  @Test
  public void countLeaves() {
    Node root = Node.getExampleNode();
    Queue<Node> queue = new LinkedList<Node>();
    queue.add(root);

    System.out.println(countLeaves(queue, 0));
  }

  private int countLeaves(Queue<Node> queue, int max) {
    int size = queue.size();
    if (size == 0) return max;

    List<Node> list = new ArrayList<>();

    for (Node root : queue) {
      if (null != root.getRight()) list.add(root.getRight());
      if (null != root.getLeft()) list.add(root.getLeft());
    }

    queue.addAll(list);

    for (int i = 0; i < size; i++) {
      queue.poll();
    }

    return countLeaves(queue, Math.max(size, queue.size()));
  }

  @Test
  public void reverseLevelOrderTrasv() {

    Node root = Node.getExampleNode();
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    Stack<Node> stack = new Stack<>();

    reverseLevelOrderTrasv(queue, stack);
    int size = stack.size();

    for (int i = 0; i < size; i++) {
      System.out.print(stack.pop().getData() + " ");
    }
  }

  private void reverseLevelOrderTrasv(Queue<Node> queue, Stack<Node> stack) {

    int size = queue.size();
    if (size == 0) return;

    List<Node> list = new ArrayList<>();

    for (Node root : queue) {
      if (null != root.getRight()) list.add(root.getRight());
      if (null != root.getLeft()) list.add(root.getLeft());
    }
    queue.addAll(list);
    for (int i = 0; i < size; i++) {
      stack.add(queue.poll());
    }
    reverseLevelOrderTrasv(queue, stack);
  }

  @Test
  public void levelOrderTrasversalLineByLine() {
    Node root = Node.getExampleNode();

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    levelOrderTrasversal(queue, "$ ");
  }

  /** Level trasversal of Tree, print all nodes on the same level */
  @Test
  public void levelOrderTrasversal() {

    Node root = Node.getExampleNode();

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    levelOrderTrasversal(queue, "");
  }

  private void levelOrderTrasversal(Queue<Node> queue, String message) {

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (Node root : queue) {
        System.out.print(root.getData() + " ");
      }
      System.out.print(message);
      List<Node> list = new ArrayList<>();

      for (Node root : queue) {
        if (null != root.getLeft()) list.add(root.getLeft());
        if (null != root.getRight()) list.add(root.getRight());
      }
      queue.addAll(list);
      for (int i = 0; i < size; i++) {
        queue.poll();
      }
      levelOrderTrasversal(queue, message);
    }
  }

  @Test
  public void postOrderTrasveral() {
    Node root = Node.getExampleNode();
    postOrderTrasvPrint(root);
  }

  private void postOrderTrasvPrint(Node root) {

    if (root != null) {
      postOrderTrasvPrint(root.getLeft());
      postOrderTrasvPrint(root.getRight());
      System.out.print(root.getData() + " ");
    }
  }

  @Test
  public void preOrderTrasveral() {
    Node root = Node.getExampleNode();
    preOrderTrasvPrint(root);
  }

  private void preOrderTrasvPrint(Node root) {

    if (root != null) {
      System.out.print(root.getData() + " ");
      preOrderTrasvPrint(root.getLeft());
      preOrderTrasvPrint(root.getRight());
    }
  }

  @Test
  public void inOrderTrasveral() {
    Node root = Node.getExampleNode();
    inOrderTrasvPrint(root);
  }

  private void inOrderTrasvPrint(Node root) {

    if (root != null) {
      inOrderTrasvPrint(root.getLeft());
      System.out.print(root.getData() + " ");
      inOrderTrasvPrint(root.getRight());
    }
  }

  @Test
  public void sizeOfTree() {
    Node root = Node.getExampleNode();
    /**
     * Atomic integer is not working in G4G cause you can't import it, but it is interesting to use
     * it
     */
    AtomicInteger count = new AtomicInteger();
    setSizeofTree(root, count);

    List<Node> list = new ArrayList<Node>();

    setSizeofTree1(root, list);

    assertEquals(count.intValue(), list.size());
  }

  private AtomicInteger setSizeofTree(Node root, AtomicInteger count) {

    if (null != root) {
      count.incrementAndGet();
      setSizeofTree(root.getLeft(), count);

      setSizeofTree(root.getRight(), count);
    }

    return count;
  }

  private void setSizeofTree1(Node root, List<Node> list) {

    if (root != null) {
      setSizeofTree1(root.getLeft(), list);
      list.add(root);
      setSizeofTree1(root.getRight(), list);
    }
  }

  /**
   * Asserts two trees are identical
   *
   * <p>In order trasversal is used but could be another one
   */
  @Test
  public void twoIdenticalTrees() {

    Node root = Node.getExampleNode();
    LinkedList<Integer> list = new LinkedList<>();
    inOrderTrasv(root, list);

    Node root1 = Node.getExampleNode();
    LinkedList<Integer> list1 = new LinkedList<>();
    inOrderTrasv(root1, list1);

    assertEquals(list, list1);
  }

  /** Find max height of tree */
  @Test
  public void heightOfBinaryTree() {
    Node root = Node.getExampleNode();
    assertEquals(4, getMaxHeight(root, 0));
  }

  private int getMaxHeight(Node root, int count) {

    if (root == null) return count;

    int tmp = count + 1;
    int left = getMaxHeight(root.getLeft(), tmp);
    int tmp1 = count + 1;
    int right = getMaxHeight(root.getRight(), tmp1);
    return Math.max(left, right);
  }

  /** Print root to leaf of all paths for tree */
  @Test
  public void rootToLeafPaths() {
    Node root = Node.getExampleNode();

    String rootKey = Integer.toString(root.getData());

    printLeafPaths(root, rootKey);
  }

  private void printLeafPaths(Node root, String map) {
    if (root != null) {
      if (root.getLeft() != null) {
        printLeafPaths(root.getLeft(), map + " " + root.getLeft().getData());
      } else {

        System.out.print(map + " #");
        return;
      }

      if (root.getRight() != null) {
        printLeafPaths(root.getRight(), map + " " + root.getRight().getData());
      }
    }
  }

  /** Find maximun number of nodes in a level */
  @Test
  public void maximumWidthOfTree() {
    Node root = Node.getExampleNode();

    Map<Integer, Integer> map = new HashMap<>();

    findMaxWidth(root, 0, map);

    int max = 0;
    for (Integer el : map.values()) {
      max = Math.max(max, el);
    }

    assertEquals(8, max);
  }

  private void findMaxWidth(Node node, int count, Map<Integer, Integer> map) {

    if (node == null) return;

    int tmp = count + 1;

    findMaxWidth(node.getLeft(), tmp, map);

    findMaxWidth(node.getRight(), tmp, map);

    map.merge(tmp, 1, Integer::sum);
  }

  /** Find all nodes at a k distance from root */
  @Test
  public void kDistanceFromRoot() {
    Node root = Node.getExampleNode();

    printKDistance(root, 2, 0);
  }

  private void printKDistance(Node node, int data, int count) {

    if (node == null) return;

    if (data == count) System.out.print(node.getData() + " ");

    int tmp = count + 1;

    printKDistance(node.getLeft(), data, tmp);

    printKDistance(node.getRight(), data, tmp);
  }

  /** Find the level of a node in a tree */
  @Test
  public void levelOfANodeInBinaryTree() {
    Node root = Node.getExampleNode();

    assertEquals(3, getLevelFunct(root, 5, 1));
  }

  private int getLevelFunct(Node node, int data, int count) {

    if (node == null) return 0;

    if (isData(node, data)) return count;

    int tmp = count + 1;

    return getLevelFunct(node.getLeft(), data, tmp) + getLevelFunct(node.getRight(), data, tmp);
  }

  /** Print ancestors of a node */
  @Test
  public void ancestorsInBinaryTree() {

    Node root = Node.getExampleNode();

    printAncestors(root, 3);
  }

  private boolean printAncestors(Node node, int target) {
    /* base cases */
    if (node == null) return false;

    if (node.getData() == target) return true;

    /*
     * If target is present in either left or right subtree of this node, then print this node
     */
    if (printAncestors(node.getLeft(), target) || printAncestors(node.getRight(), target)) {
      System.out.print(node.getData() + " ");
      return true;
    }

    /* Else return false */
    return false;
  }

  private boolean isData(Node node, int data) {

    return node.getData() == data;
  }

  private String printAncestors(Node node, String finder, int data) {

    if (node == null) return "";

    if (isData(node, data)) return finder;

    String retLeft = printAncestors(node.getLeft(), node.getData() + " ", data);
    // rigth find
    String retRight = printAncestors(node.getRight(), node.getData() + " ", data);

    return retLeft + retRight;
  }

  /** Sum of sub nodes must be equal to root node value */
  @Test
  public void sumOfBinaryTreeNodes() {

    Node root = new Node(3);

    root.setLeft(new Node(2));
    root.setRight(new Node(1));

    int ret = isSumTree(root);

    assertEquals(1, ret);
  }

  private int sum(Node node) {
    return node == null ? 0 : sum(node.getLeft()) + node.getData() + sum(node.getRight());
  }

  private int isSumTree(Node node) {
    int ls, rs;

    /*
     * If node is NULL or it's a leaf node then return true
     */
    if ((node == null) || (node.getLeft() == null && node.getRight() == null)) return 1;

    /* Get sum of nodes in left and right subtrees */
    ls = sum(node.getLeft());
    rs = sum(node.getRight());

    /*
     * if the node and both of its children satisfy the property return 1 else 0
     */
    if ((node.getData() == ls + rs)
        && (isSumTree(node.getLeft()) != 0)
        && (isSumTree(node.getRight())) != 0) return 1;

    return 0;
  }

  /** Find sum of nodes */
  @Test
  public void sumOfBinaryTree() {

    Node root = new Node(1);

    Node left = new Node(2);
    Node right = new Node(3);

    root.setLeft(left);
    root.setRight(right);

    List<Integer> list = new ArrayList<Integer>();

    inOrderTrasv(root, list);

    assertEquals(6, list.stream().mapToInt(i -> i).sum());
  }

  private void inOrderTrasv(Node root, List<Integer> list) {

    if (root != null) {
      inOrderTrasv(root.getLeft(), list);
      list.add(root.getData());
      inOrderTrasv(root.getRight(), list);
    }
  }
}
