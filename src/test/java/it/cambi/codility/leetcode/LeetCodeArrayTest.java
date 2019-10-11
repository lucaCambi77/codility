/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class LeetCodeArrayTest
{

    @Test
    public void twoSumSortedArrays()
    {
        assertArrayEquals(new int[] { 1, 2 }, twoSumSortedArrays(new int[] { 2, 7, 11, 15 }, 9));
        assertArrayEquals(new int[] { 1, 2 }, twoSumSortedArrays(new int[] { 0, 0, 3, 4 }, 0));
        assertArrayEquals(new int[] { 1, 2 }, twoSumSortedArrays(new int[] { -1, 0 }, -1));
        assertArrayEquals(new int[] { 4, 5 }, twoSumSortedArrays(new int[] { 1, 2, 3, 4, 4, 9, 56, 90 }, 8));

    }

    public int[] twoSumSortedArrays(int[] nums, int target)
    {
        int length = nums.length - 1;

        for (int i = 0; i < length; i++)
        {
            int num = nums[i];

            int fit = target - num;

            int search = binarySearch(nums, i + 1, length, fit);

            if (search >= 0)
                return new int[] { i + 1, search + 1 };

        }

        return new int[2];
    }

    public int binarySearch(int[] a, int left, int right, int k)
    {

        if (right < left)
            return -1;

        int mid = (left + right) / 2;

        if (a[mid] == k)
        {

            return mid;
        }
        else if (a[mid] > k)
        {
            return binarySearch(a, left, mid - 1, k);

        }
        else
        {

            return binarySearch(a, mid + 1, right, k);
        }

    }

    @Test
    public void twoSum()
    {
        assertArrayEquals(new int[] { 0, 1 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
    }

    public int[] twoSum(int[] nums, int target)
    {
        Map<Integer, Integer> keyToIndex = new HashMap<Integer, Integer>();
        int size = nums.length;

        int[] solution = new int[2];
        for (int i = 0; i < size; i++)
        {
            int diff = target - nums[i];

            if (null != keyToIndex.get(diff))
            {
                solution[0] = keyToIndex.get(diff);
                solution[1] = i;
                break;
            }

            keyToIndex.put(nums[i], i);
        }

        return solution;
    }
}
