/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;

import it.cambi.codility.model.BinaryTree;
import it.cambi.codility.model.Node;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeTreeTest
{

    @Test
    public void hasPathSum()
    {
        assertEquals(true, hasPathSum(BinaryTree.getBSTExample(), 100));
        assertEquals(true, hasPathSum(BinaryTree.getBSTExample(), 200));
        assertEquals(false, hasPathSum(BinaryTree.getBSTExample(), 101));
        assertEquals(false, hasPathSum(BinaryTree.getBSTExample(), 183));
        assertEquals(true, hasPathSum(new Node(1), 1));

        Node node = new Node(1);
        node.setLeft(new Node(2));

        assertEquals(false, hasPathSum(node, 1));

    }

    private boolean hasPathSum(Node root, int sum)
    {

        return hasPathSum(root, 0, sum);

    }

    private boolean hasPathSum(Node root, int sumSoFar, int sum)
    {
        if (root == null)
            return false;

        sumSoFar = root.data + sumSoFar;

        if (root.right == null && root.left == null)
            return sumSoFar == sum;

        return hasPathSum(root.left, sumSoFar, sum) || hasPathSum(root.right, sumSoFar, sum);

    }

    @Test
    public void mergeTrees()
    {

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

    public Node mergeTrees(Node t1, Node t2)
    {

        if (null == t1 && null == t2)
            return null;

        if (null == t1 && null != t2)
            return t2;

        if (null != t1 && null == t2)
            return t1;

        if (null != t1 && null != t2)
        {
            t1.data = t2.data + t1.data;

            t1.left = mergeTrees(t1.left, t2.left);

            t1.right = mergeTrees(t1.right, t2.right);

        }

        return t1;

    }

    @Test
    public void isSameTree()
    {
        assertEquals(true, isSameTree(BinaryTree.getBSTExample(), BinaryTree.getBSTExample()));
    }

    private boolean isSameTree(Node p, Node q)
    {
        if (p == null && q == null)
            return true;

        if (null != p && null != q && p.data == q.data &&
                isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
            return true;

        return false;
    }

    @Test
    public void rangeSumBST()
    {
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

    public void rangeSumBST(Node root, int left, int right, AtomicInteger sum)
    {

        if (root == null)
            return;

        if (root.data >= left && root.data <= right)
            sum.addAndGet(root.data);

        rangeSumBST(root.left, left, right, sum);
        rangeSumBST(root.right, left, right, sum);
    }

    @Test
    public void findTarget()
    {
        Node root = BinaryTree.getBSTExample();

        List<Integer> listLeft = new ArrayList<Integer>();
        List<Integer> listRight = new ArrayList<Integer>();
        branchList(root.left, listLeft);
        branchList(root.right, listRight);

        listLeft.addAll(listRight);
        listLeft.add(root.data);

        assertEquals(true, twoSum(listLeft, 110));
    }

    private void branchList(Node root, List<Integer> listLeft)
    {

        if (null != root)
        {
            listLeft.add(root.data);
            branchList(root.left, listLeft);
            branchList(root.right, listLeft);

        }
    }

    private boolean twoSum(List<Integer> nums, int target)
    {
        Map<Integer, Integer> keyToIndex = new HashMap<Integer, Integer>();
        int size = nums.size();

        boolean hasSum = false;

        for (int i = 0; i < size; i++)
        {
            int diff = target - nums.get(i);

            if (null != keyToIndex.get(diff))
            {
                hasSum = true;
                break;
            }

            keyToIndex.put(nums.get(i), i);
        }

        return hasSum;
    }
}
