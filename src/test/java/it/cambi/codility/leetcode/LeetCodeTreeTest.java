/**
 *
 */
package it.cambi.codility.leetcode;

import it.cambi.codility.model.BinaryTree;
import it.cambi.codility.model.Node;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeTreeTest {

    class NodeNAry {
        public int val;
        public List<NodeNAry> children;

        public NodeNAry() {
        }

        public NodeNAry(int _val) {
            val = _val;
        }

        public NodeNAry(int _val, List<NodeNAry> _children) {
            val = _val;
            children = _children;
        }
    }

    @Test
    public void longestUnivaluePath() {

        Node head = new Node(5);
        Node left = new Node(4);
        Node right = new Node(5);

        right.right = new Node(5);

        left.left = new Node(1);
        left.right = new Node(1);

        head.left = left;
        head.right = right;

        assertEquals(2, longestUnivaluePath(head, 0, 0));

        head = new Node(1);
        Node left1 = new Node(4);
        Node right1 = new Node(5);

        right1.right = new Node(5);

        left1.left = new Node(4);
        left1.right = new Node(4);

        head.left = left1;
        head.right = right1;

        assertEquals(2, longestUnivaluePath(head, 0, 0));
    }

    public int longestUnivaluePath(Node root, int count, int max) {

        if (root == null)
            return max;

        int checkLeft = checkNodes(root.left, root.data);
        int checkRight = checkNodes(root.right, root.data);

        int maxLeft = checkLeft == 0 ? 0 : count + checkLeft;
        int maxRight = checkRight == 0 ? 0 : count + checkRight;

        int left = longestUnivaluePath(root.left, maxLeft, Math.max(maxLeft + maxRight, max));
        int right = longestUnivaluePath(root.right, maxRight, Math.max(maxLeft + maxRight, max));

        return Math.max(left, right);
    }

    private int checkNodes(Node root, int data) {

        if (null == root)
            return 0;

        return root.data - data == 0 ? 1 : 0;
    }

    /*
        TODO Iterative solution
     */
    @Test
    public void preorderNAry() {

        NodeNAry root = getNodeNAryTest();
        List<Integer> sol = new ArrayList<>();
        preorderNAry(root, sol);

        assertEquals(Arrays.asList(new LinkedList<Integer>() {{
            add(1);
            add(3);
            add(5);
            add(6);
            add(2);
            add(4);
        }}), Arrays.asList(sol));
    }

    private NodeNAry getNodeNAryTest() {
        NodeNAry root = new NodeNAry(1);

        LinkedList<NodeNAry> list1 = new LinkedList<>();
        NodeNAry node1 = new NodeNAry(3);
        node1.children = new LinkedList<NodeNAry>() {{
            add(new NodeNAry(5));
            add(new NodeNAry(6));
        }};

        list1.add(node1);
        list1.add(new NodeNAry(2));
        list1.add(new NodeNAry(4));

        root.children = list1;
        return root;
    }

    public void preorderNAry(NodeNAry root, List<Integer> list) {

        if (root != null) {

            List<NodeNAry> nodes = root.children;
            list.add(root.val);
            if (null != nodes)
                for (NodeNAry node : nodes) {
                    preorderNAry(node, list);
                }

        }

    }

    @Test
    public void hasPathSum() {
        assertEquals(true, hasPathSum(BinaryTree.getBSTExample(), 100));
        assertEquals(true, hasPathSum(BinaryTree.getBSTExample(), 200));
        assertEquals(false, hasPathSum(BinaryTree.getBSTExample(), 101));
        assertEquals(false, hasPathSum(BinaryTree.getBSTExample(), 183));
        assertEquals(true, hasPathSum(new Node(1), 1));

        Node node = new Node(1);
        node.setLeft(new Node(2));

        assertEquals(false, hasPathSum(node, 1));

    }

    private boolean hasPathSum(Node root, int sum) {

        return hasPathSum(root, 0, sum);

    }

    private boolean hasPathSum(Node root, int sumSoFar, int sum) {
        if (root == null)
            return false;

        sumSoFar = root.data + sumSoFar;

        if (root.right == null && root.left == null)
            return sumSoFar == sum;

        return hasPathSum(root.left, sumSoFar, sum) || hasPathSum(root.right, sumSoFar, sum);

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

        assertEquals(true, isSameTree(t1, t3));

        Node node = new Node(1);

        mergeTrees(node, null);

        assertEquals(true, isSameTree(new Node(1), node));

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

        assertEquals(true, isSameTree(nodeX, node4));
    }

    public Node mergeTrees(Node t1, Node t2) {

        if (null == t1 && null == t2)
            return null;

        if (null == t1 && null != t2)
            return t2;

        if (null != t1 && null == t2)
            return t1;

        if (null != t1 && null != t2) {
            t1.data = t2.data + t1.data;

            t1.left = mergeTrees(t1.left, t2.left);

            t1.right = mergeTrees(t1.right, t2.right);

        }

        return t1;

    }

    @Test
    public void isSameTree() {
        assertEquals(true, isSameTree(BinaryTree.getBSTExample(), BinaryTree.getBSTExample()));
    }

    private boolean isSameTree(Node p, Node q) {
        if (p == null && q == null)
            return true;

        if (null != p && null != q && p.data == q.data &&
                isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
            return true;

        return false;
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

        if (root == null)
            return;

        if (root.data >= left && root.data <= right)
            sum.addAndGet(root.data);

        rangeSumBST(root.left, left, right, sum);
        rangeSumBST(root.right, left, right, sum);
    }

    @Test
    public void findTarget() {
        Node root = BinaryTree.getBSTExample();

        List<Integer> listLeft = new ArrayList<Integer>();
        List<Integer> listRight = new ArrayList<Integer>();
        branchList(root.left, listLeft);
        branchList(root.right, listRight);

        listLeft.addAll(listRight);
        listLeft.add(root.data);

        assertEquals(true, twoSum(listLeft, 110));
    }

    private void branchList(Node root, List<Integer> listLeft) {

        if (null != root) {
            listLeft.add(root.data);
            branchList(root.left, listLeft);
            branchList(root.right, listLeft);

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
}
