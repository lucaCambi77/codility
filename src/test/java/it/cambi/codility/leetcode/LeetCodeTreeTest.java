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

import it.cambi.codility.model.BinaryTree;
import it.cambi.codility.model.Node;

/**
 * @author luca
 *
 */
public class LeetCodeTreeTest
{

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
        Node root = BinaryTree.getBST();

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
