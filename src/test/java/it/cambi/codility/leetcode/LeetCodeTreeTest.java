/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
