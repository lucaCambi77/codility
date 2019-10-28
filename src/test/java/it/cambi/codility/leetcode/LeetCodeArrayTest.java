/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author luca
 *
 */
public class LeetCodeArrayTest
{

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void moveZeroes()
    {
        moveZeroes(new int[] { 0, 1, 0, 3, 12 });
        moveZeroes(new int[] { 0, 0, 1 });

    }

    private void moveZeroes(int[] nums)
    {
        for (int i = nums.length - 1; i >= 0; i--)
        {
            if (nums[i] == 0)
            {
                int j = i;
                while (j + 1 < nums.length)
                {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                    j++;
                }
            }
        }

    }

    @Test
    public void removeElement()
    {
        assertEquals(2, removeElement(new int[] { 3, 2, 2, 3 }, 3));
        assertEquals(5, removeElement(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }, 2));

    }

    private int removeElement(int[] nums, int val)
    {
        LinkedList<Integer> b = IntStream.of(nums).filter(e -> e != val).boxed().collect(Collectors.toCollection(LinkedList::new));
        int size = b.size();

        for (int i = 0; i < size; i++)
        {
            nums[i] = b.poll();
        }

        return size;
    }

    @Test
    public void removeDuplicates()
    {
        assertEquals(2, removeDuplicates(new int[] { 1, 1, 2 }));
        assertEquals(5, removeDuplicates(new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }));
        assertEquals(1, removeDuplicates(new int[] { 1, 1 }));

    }

    private int removeDuplicates(int[] nums)
    {
        LinkedList<Integer> b = IntStream.of(nums).distinct().boxed().collect(Collectors.toCollection(LinkedList::new));
        int size = b.size();

        for (int i = 0; i < size; i++)
        {
            nums[i] = b.poll();
        }

        return size;

    }

    @Test
    public void combinationSum() throws JsonProcessingException
    {
        assertEquals("[[2,2,3],[7]]", mapper.writeValueAsString(combinationSum(new int[] { 2, 3, 6, 7 }, 7)));
        assertEquals("[[2,2,2,2],[2,3,3],[3,5]]", mapper.writeValueAsString(combinationSum(new int[] { 2, 3, 5 }, 8)));
        assertEquals("[[1,1]]", mapper.writeValueAsString(combinationSum(new int[] { 1 }, 2)));
        assertEquals("[[2,2,3],[2,5]]", mapper.writeValueAsString(combinationSum(new int[] { 2, 3, 5 }, 7)));
        assertEquals("[[7,7,2,2],[7,2,2,2,2,3],[7,2,3,3,3],[2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,3,3],[2,2,2,3,3,3,3],[3,3,3,3,3,3]]",
                mapper.writeValueAsString(combinationSum(new int[] { 7, 2, 3 }, 18)));

    }

    private List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> ans = new ArrayList<>();
        helper(0, target, 0, candidates, new ArrayList<>(), ans);
        return ans;
    }

    private void helper(int index, int target, int sum, int[] candidates,
            List<Integer> list, List<List<Integer>> ans)
    {
        if (index > candidates.length - 1 || sum > target)
            return;

        if (sum == target && list.size() != 0)
            ans.add(new ArrayList<>(list));

        for (int i = index; i < candidates.length; i++)
        {
            list.add(candidates[i]);
            helper(i, target, sum + candidates[i], candidates, list, ans);
            list.remove(list.size() - 1);
        }
    }

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

    private int binarySearch(int[] a, int left, int right, int k)
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

    private int[] twoSum(int[] nums, int target)
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
