/** */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.cambi.codility.model.BinaryTree;
import it.cambi.codility.model.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author luca
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class LeetCodeTreeTest {

  class NodeNAry {
    public int val;
    public List<NodeNAry> children;

    public NodeNAry() {}

    public NodeNAry(int _val) {
      val = _val;
    }

    public NodeNAry(int _val, List<NodeNAry> _children) {
      val = _val;
      children = _children;
    }
  }

  @Getter
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  @Test
  void findTilt() {
    TreeNode treeNode = new TreeNode(1);
    TreeNode treeNodeLeft = new TreeNode(2);
    TreeNode treeNodeRight = new TreeNode(3);

    treeNode.left = treeNodeLeft;
    treeNode.right = treeNodeRight;

    assertEquals(1, findTilt(treeNode));

    treeNode = new TreeNode(4);
    treeNodeLeft = new TreeNode(2);
    treeNodeRight = new TreeNode(9);

    TreeNode treeNodeLeft1 = new TreeNode(3);
    TreeNode treeNodeRight1 = new TreeNode(5);
    treeNodeRight.right = new TreeNode(7);

    treeNodeLeft.left = treeNodeLeft1;
    treeNodeLeft.right = treeNodeRight1;

    treeNode.left = treeNodeLeft;
    treeNode.right = treeNodeRight;

    assertEquals(15, findTilt(treeNode));

    treeNode = new TreeNode(1);
    treeNodeLeft = new TreeNode(2);
    treeNodeLeft1 = new TreeNode(3);
    TreeNode treeNodeLeft2 = new TreeNode(4);

    treeNodeLeft2.left = new TreeNode(5);

    treeNodeLeft1.left = treeNodeLeft2;

    treeNodeLeft.left = treeNodeLeft1;

    treeNode.left = treeNodeLeft;

    assertEquals(40, findTilt(treeNode));

    assertEquals(0, findTilt(null));
  }

  private int findTilt(TreeNode root) {
    tilt(root);

    return traverse(root);
  }

  int tilt(TreeNode root) {
    if (root == null) return 0;

    TreeNode l = root.left;
    TreeNode r = root.right;

    int tiltL = tilt(l);
    int tiltR = tilt(r);

    int s = tiltL + tiltR + root.val;

    root.val = Math.abs(tiltL - tiltR);

    return s;
  }

  private int traverse(TreeNode root) {

    if (root == null) return 0;

    return root.val + traverse(root.left) + traverse(root.right);
  }

  @Test
  public void averageOfLevels() {

    Integer[] list = new Integer[] {3, 9, 20, null, null, 15, 7};
    TreeNode t = createFromArray(list);
    List<Double> result = new ArrayList<>();
    List<Double> sumList = new ArrayList<>();
    List<Integer> countList = new ArrayList<>();

    averageOfLevels(t, 0, sumList, countList);

    for (int i = 0; i < sumList.size(); i++) {
      result.add(sumList.get(i) / countList.get(i)); // Compute average
    }

    assertEquals(List.of(3.00000, 14.50000, 11.00000), result);
  }

  private void averageOfLevels(
      TreeNode node, int level, List<Double> sumList, List<Integer> countList) {
    if (node == null) return;

    if (level == sumList.size()) {
      sumList.add(0.0);
      countList.add(0);
    }

    sumList.set(level, sumList.get(level) + node.val);
    countList.set(level, countList.get(level) + 1);

    averageOfLevels(node.left, level + 1, sumList, countList);
    averageOfLevels(node.right, level + 1, sumList, countList);
  }

  @Test
  public void createFromArray() {
    Integer[] list = new Integer[] {0, 1, 2, 3, null, null, null, 4, null, 5};

    TreeNode res = createFromArray(list);

    TreeNode treeNode = new TreeNode(list[0]);

    TreeNode treeNodeLeft = new TreeNode(1);

    TreeNode treeNodeRight = new TreeNode(2);

    TreeNode treeNodeL1 = new TreeNode(3);

    TreeNode treeNodeL2 = new TreeNode(4);

    treeNodeL2.left = new TreeNode(5);

    treeNodeL1.left = treeNodeL2;

    treeNodeLeft.left = treeNodeL1;

    treeNode.left = treeNodeLeft;

    treeNode.right = treeNodeRight;

    assertTrue(isSameTreeNode(treeNode, res));

    list = new Integer[] {0, 1, null, 2, null, 3, null, 4, null, 5};

    res = createFromArray(list);

    treeNode = new TreeNode(list[0]);

    treeNodeLeft = new TreeNode(1);

    TreeNode treeNodeL = new TreeNode(2);

    treeNodeL1 = new TreeNode(3);

    treeNodeL2 = new TreeNode(4);

    treeNodeL2.left = new TreeNode(5);

    treeNodeL1.left = treeNodeL2;

    treeNodeL.left = treeNodeL1;

    treeNodeLeft.left = treeNodeL;

    treeNode.left = treeNodeLeft;

    assertTrue(isSameTreeNode(treeNode, res));
  }

  private TreeNode createFromArray(Integer[] arr) {
    if (arr == null || arr.length == 0 || arr[0] == null) return null;

    TreeNode root = new TreeNode(arr[0]);
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int i = 1;
    while (i < arr.length && !queue.isEmpty()) {
      TreeNode curr = queue.poll(); // Current node to assign children

      // Assign left child
      if (arr[i] != null) {
        curr.left = new TreeNode(arr[i]);
        queue.add(curr.left);
      }
      i++;

      // Assign right child
      if (i < arr.length && arr[i] != null) {
        curr.right = new TreeNode(arr[i]);
        queue.add(curr.right);
      }
      i++;
    }

    return root;
  }

  @Test
  public void tree2str() {}

  public String tree2str(TreeNode root) {
    return null;
  }

  @Test
  public void searchBST() {
    TreeNode treeNode = new TreeNode(4);
    TreeNode treeNodeLeft = new TreeNode(2);
    TreeNode treeNodeRight = new TreeNode(7);

    TreeNode treeNodeLeft1 = new TreeNode(1);
    TreeNode treeNodeRight1 = new TreeNode(3);

    treeNodeLeft.left = treeNodeLeft1;
    treeNodeLeft.right = treeNodeRight1;

    treeNode.left = treeNodeLeft;
    treeNode.right = treeNodeRight;

    TreeNode result = searchBST(treeNode, 1);
    TreeNode result1 = searchBST(treeNode, 5);

    isSameTreeNode(new TreeNode(1), result);
    isSameTreeNode(null, result1);
  }

  public TreeNode searchBST(TreeNode root, int val) {

    if (root == null) return null;

    if (root.val == val) return root;

    TreeNode right = searchBST(root.getRight(), val);

    return right == null ? searchBST(root.getLeft(), val) : right;
  }

  @Test
  public void minDepth() {
    Node head = new Node(3);
    Node left2 = new Node(9);
    Node right2 = new Node(20);

    right2.setLeft(new Node(15));
    right2.setRight(new Node(17));

    head.setLeft(left2);
    head.setRight(right2);
    assertEquals(2, minDepth(head, 1));
    assertEquals(3, minDepth(BinaryTree.getBSTExample(), 1));

    head = new Node(3);
    head.setLeft(new Node(3));

    assertEquals(2, minDepth(head, 1));
  }

  public int minDepth(Node root, int level) {

    if (root == null) return level;

    if (root.getLeft() == null && root.getRight() == null) return level;

    level++;

    return Math.min(minDepth(root.getLeft(), level), minDepth(root.getRight(), level));
  }

  @Test
  public void maxDepth() {
    assertEquals(3, maxDepth(BinaryTree.getBSTExample(), 0));
  }

  public int maxDepth(Node root, int level) {

    if (root == null) return level;

    level++;
    return Math.max(maxDepth(root.getLeft(), level), maxDepth(root.getRight(), level));
  }

  @Test
  public void isSymmetric() {

    assertTrue(isSymmetric(isSimmetricNode()));
    assertFalse(isSymmetric(isSymmetricNode1()));
    assertTrue(isSymmetric(isSimmetricNode2()));
  }

  public boolean isSymmetric(Node root) {
    if (root == null) return true;

    Map<Integer, List<Integer>> map = new HashMap<>();
    createTreeLevel(map, root, 0);

    Iterator<Map.Entry<Integer, List<Integer>>> entrySet = map.entrySet().iterator();
    entrySet.next();

    while (entrySet.hasNext()) {

      List<Integer> list = entrySet.next().getValue();

      for (int i = list.size() - 1; i >= list.size() / 2; i--) {
        if (!Objects.equals(list.get(i), list.get(list.size() - 1 - i))) return false;
      }
    }

    return true;
  }

  private void createTreeLevel(Map<Integer, List<Integer>> map, Node root, int level) {
    List<Integer> list = map.getOrDefault(level, new ArrayList<>());

    if (root == null) {
      list.add(0);
      map.put(level, list);
      return;
    }

    list.add(root.getData());
    map.put(level, list);

    createTreeLevel(map, root.getLeft(), level + 1);
    createTreeLevel(map, root.getRight(), level + 1);
  }

  private Node isSimmetricNode() {
    Node head = new Node(1);
    Node left2 = new Node(2);
    Node right2 = new Node(2);

    left2.setLeft(new Node(3));
    left2.setRight(new Node(4));

    right2.setLeft(new Node(4));
    right2.setRight(new Node(3));

    head.setLeft(left2);
    head.setRight(right2);
    return head;
  }

  private Node isSymmetricNode1() {
    Node head = new Node(1);
    Node left2 = new Node(2);
    Node right2 = new Node(2);

    left2.setRight(new Node(3));

    right2.setRight(new Node(3));

    head.setLeft(left2);
    head.setRight(right2);
    return head;
  }

  private Node isSimmetricNode2() {
    Node head = new Node(1);
    Node left2 = new Node(2);
    Node right2 = new Node(2);

    left2.setRight(new Node(3));

    right2.setLeft(new Node(3));

    head.setLeft(left2);
    head.setRight(right2);
    return head;
  }

  @Test
  public void longestUnivaluePath() {

    Node head = new Node(1);
    Node left2 = new Node(2);
    Node right2 = new Node(2);

    right2.setRight(new Node(2));
    right2.setLeft(new Node(2));

    Node leftLeft = new Node(2);
    leftLeft.setLeft(new Node(2));

    left2.setLeft(leftLeft);

    left2.setRight(new Node(2));

    head.setLeft(left2);
    head.setRight(right2);

    assertEquals(3, longestUnivaluePath(head, 0, 0));
  }

  public int longestUnivaluePath(Node root, int count, int max) {

    if (root == null) return max;

    int checkLeft = checkNodes(root.getLeft(), root.getData());
    int checkRight = checkNodes(root.getRight(), root.getData());

    int maxLeft = checkLeft == 0 ? 0 : count + checkLeft;
    int maxRight = checkRight == 0 ? 0 : count + checkRight;

    int left = longestUnivaluePath(root.getLeft(), maxLeft, Math.max(maxLeft + maxRight, max));
    int right = longestUnivaluePath(root.getRight(), maxRight, Math.max(maxLeft + maxRight, max));

    return Math.max(left, right);
  }

  private int checkNodes(Node root, int data) {

    if (null == root) return 0;

    return root.getData() - data == 0 ? 1 : 0;
  }

  /*
     TODO Iterative solution
  */
  @Test
  public void preorderNAry() {

    NodeNAry root = getNodeNAryTest();
    List<Integer> sol = new ArrayList<>();
    preorderNAry(root, sol);

    assertEquals(Arrays.asList(1, 3, 5, 6, 2, 4), List.of(sol));
  }

  public void preorderNAry(NodeNAry root, List<Integer> list) {

    if (root != null) {

      list.add(root.val);
      List<NodeNAry> nodes = root.children;
      if (null != nodes)
        for (NodeNAry node : nodes) {
          preorderNAry(node, list);
        }
    }
  }

  @Test
  public void postOrderNAry() {

    NodeNAry root = getNodeNAryTest();
    List<Integer> sol = new ArrayList<>();
    postOrderNAry(root, sol);

    assertEquals(Arrays.asList(5, 6, 3, 2, 4, 1), sol);
  }

  public void postOrderNAry(NodeNAry root, List<Integer> list) {

    if (root != null) {

      List<NodeNAry> nodes = root.children;
      if (null != nodes)
        for (NodeNAry node : nodes) {
          postOrderNAry(node, list);
        }

      list.add(root.val);
    }
  }

  private NodeNAry getNodeNAryTest() {
    NodeNAry root = new NodeNAry(1);

    LinkedList<NodeNAry> list1 = new LinkedList<>();
    NodeNAry node1 = new NodeNAry(3);
    node1.children =
        new LinkedList<NodeNAry>() {
          {
            add(new NodeNAry(5));
            add(new NodeNAry(6));
          }
        };

    list1.add(node1);
    list1.add(new NodeNAry(2));
    list1.add(new NodeNAry(4));

    root.children = list1;
    return root;
  }

  @Test
  public void hasPathSum() {
    assertTrue(hasPathSum(BinaryTree.getBSTExample(), 100));
    assertTrue(hasPathSum(BinaryTree.getBSTExample(), 200));
    assertFalse(hasPathSum(BinaryTree.getBSTExample(), 101));
    assertFalse(hasPathSum(BinaryTree.getBSTExample(), 183));
    assertTrue(hasPathSum(new Node(1), 1));

    Node node = new Node(1);
    node.setLeft(new Node(2));

    assertFalse(hasPathSum(node, 1));
  }

  private boolean hasPathSum(Node root, int sum) {

    return hasPathSum(root, 0, sum);
  }

  private boolean hasPathSum(Node root, int sumSoFar, int sum) {
    if (root == null) return false;

    sumSoFar = root.getData() + sumSoFar;

    if (root.getRight() == null && root.getLeft() == null) return sumSoFar == sum;

    return hasPathSum(root.getLeft(), sumSoFar, sum) || hasPathSum(root.getRight(), sumSoFar, sum);
  }

  @Test
  public void mergeTrees() {

    Node t1 = new Node(1);

    Node left = new Node(3);
    left.setLeft(new Node(5));

    t1.setLeft(left);

    Node right = new Node(2);

    t1.setRight(right);

    Node t2 = new Node(2);

    Node left1 = new Node(1);
    left1.setRight(new Node(4));

    t2.setLeft(left1);

    Node right1 = new Node(3);
    right1.setRight(new Node(7));

    t2.setRight(right1);

    mergeTrees(t1, t2);

    Node t3 = new Node(3);

    Node left2 = new Node(4);
    left2.setLeft(new Node(5));
    left2.setRight(new Node(4));

    t3.setLeft(left2);

    Node right3 = new Node(5);
    right3.setRight(new Node(7));

    t3.setRight(right3);

    assertTrue(isSameTree(t1, t3));

    Node node = new Node(1);

    mergeTrees(node, null);

    assertTrue(isSameTree(new Node(1), node));

    Node nodeX = new Node(1);

    Node node1 = new Node(2);
    node1.setLeft(new Node(3));

    nodeX.setLeft(node1);

    Node node2 = new Node(1);

    Node node3 = new Node(2);
    node3.setRight(new Node(3));

    node2.setRight(node3);

    mergeTrees(nodeX, node2);

    Node node4 = new Node(2);

    Node node5 = new Node(2);
    node5.setLeft(new Node(3));

    node4.setLeft(node5);

    Node node6 = new Node(2);
    node6.setRight(new Node(3));

    node4.setRight(node6);

    assertTrue(isSameTree(nodeX, node4));
  }

  public Node mergeTrees(Node t1, Node t2) {

    if (null == t1 && null == t2) return null;

    if (null == t1 && null != t2) return t2;

    if (null != t1 && null == t2) return t1;

    if (null != t1 && null != t2) {
      t1.setData(t2.getData() + t1.getData());

      t1.setLeft(mergeTrees(t1.getLeft(), t2.getLeft()));

      t1.setRight(mergeTrees(t1.getRight(), t2.getRight()));
    }

    return t1;
  }

  @Test
  public void isSameTree() {
    assertTrue(isSameTree(BinaryTree.getBSTExample(), BinaryTree.getBSTExample()));
  }

  private boolean isSameTree(Node p, Node q) {
    if (p == null && q == null) return true;

    return null != p
        && null != q
        && p.getData() == q.getData()
        && isSameTree(p.getLeft(), q.getLeft())
        && isSameTree(p.getRight(), q.getRight());
  }

  @Test
  public void rangeSumBST() {
    Node root = new Node(10);

    Node left = new Node(5);
    left.setLeft(new Node(3));
    left.setRight(new Node(7));

    root.setLeft(left);

    Node right = new Node(15);
    right.setRight(new Node(18));

    root.setRight(right);

    AtomicInteger sum = new AtomicInteger(0);
    rangeSumBST(root, 7, 15, sum);

    assertEquals(32, sum.get());
  }

  public void rangeSumBST(Node root, int left, int right, AtomicInteger sum) {

    if (root == null) return;

    if (root.getData() >= left && root.getData() <= right) sum.addAndGet(root.getData());

    rangeSumBST(root.getLeft(), left, right, sum);
    rangeSumBST(root.getRight(), left, right, sum);
  }

  @Test
  public void findTarget() {
    Node root = BinaryTree.getBSTExample();

    List<Integer> listLeft = new ArrayList<Integer>();
    List<Integer> listRight = new ArrayList<Integer>();
    branchList(root.getLeft(), listLeft);
    branchList(root.getRight(), listRight);

    listLeft.addAll(listRight);
    listLeft.add(root.getData());

    assertTrue(twoSum(listLeft, 110));
  }

  private void branchList(Node root, List<Integer> listLeft) {

    if (null != root) {
      listLeft.add(root.getData());
      branchList(root.getLeft(), listLeft);
      branchList(root.getRight(), listLeft);
    }
  }

  private boolean twoSum(List<Integer> nums, int target) {
    Map<Integer, Integer> keyToIndex = new HashMap<Integer, Integer>();
    int size = nums.size();

    boolean hasSum = false;

    for (int i = 0; i < size; i++) {
      int diff = target - nums.get(i);

      if (null != keyToIndex.get(diff)) {
        hasSum = true;
        break;
      }

      keyToIndex.put(nums.get(i), i);
    }

    return hasSum;
  }

  private boolean isSameTreeNode(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;

    return null != p
        && null != q
        && p.val == q.val
        && isSameTreeNode(p.left, q.left)
        && isSameTreeNode(p.right, q.right);
  }
}
