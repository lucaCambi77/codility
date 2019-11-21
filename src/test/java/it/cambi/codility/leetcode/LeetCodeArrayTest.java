/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.util.Pair;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeArrayTest
{

    private ObjectMapper mapper = new ObjectMapper();

    long k;

    private int[] nums;

    @Test
    public void numArray()
    {
        nums = new int[] { -2, 0, 3, -5, 2, -1 };

        assertEquals(1, sumRange(0, 2));
        assertEquals(-1, sumRange(2, 5));
        assertEquals(-3, sumRange(0, 5));

    }

    public int sumRange(int i, int j)
    {
        return IntStream.range(0, nums.length).filter(v -> v >= i && v <= j).map(v -> new Integer(nums[v])).sum();
    }

    @Test
    public void containsNearbyAlmostDuplicate()
    {
        assertEquals(true, containsNearbyAlmostDuplicate(new int[] { 1, 2, 3, 1 }, 3, 0));
        assertEquals(true, containsNearbyAlmostDuplicate(new int[] { 1, 0, 1, 1 }, 1, 2));
        assertEquals(false, containsNearbyAlmostDuplicate(new int[] { 1, 5, 9, 1, 5, 9 }, 2, 3));
        assertEquals(false, containsNearbyAlmostDuplicate(new int[] { -1, 2147483647 }, 1, 2147483647));

    }

    private boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t)
    {

        for (int i = 0; i < nums.length; i++)
        {
            int value = nums[i];

            for (int j = 0; j < nums.length && i != j; j++)
            {
                int valueIn = nums[j];

                long diff = (long) value - (long) valueIn;

                if (Math.abs(diff) <= t && Math.abs(i - j) <= k)
                    return true;
            }
        }

        return false;
    }

    @Test
    public void containsNearbyDuplicate()
    {
        assertEquals(true, containsNearbyDuplicate(new int[] { 1, 2, 4, 1 }, 3));
        assertEquals(true, containsNearbyDuplicate(new int[] { 1, 0, 1, 1 }, 1));
        assertEquals(false, containsNearbyDuplicate(new int[] { 1, 2, 3, 1, 2, 3 }, 2));
        assertEquals(false, containsNearbyDuplicate(new int[] {}, 0));

    }

    private boolean containsNearbyDuplicate(int[] nums, int k)
    {
        if (nums.length == 0)
            return false;

        int value = nums[0];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(value, 0);

        for (int i = 1; i < nums.length; i++)
        {
            value = nums[i];

            int tmp = map.getOrDefault(value, -1);

            if (-1 != tmp && i - tmp <= k)
                return true;

            map.put(value, i);

        }

        return false;
    }

    @Test
    public void containsDuplicate()
    {
        assertEquals(true, containsDuplicate(new int[] { 1, 2, 3, 1 }));
        assertEquals(false, containsDuplicate(new int[] { 1, 2, 3, 4 }));
        assertEquals(true, containsDuplicate(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));

    }

    private boolean containsDuplicate(int[] nums)
    {
        int size = (int) IntStream.of(nums).distinct().count();

        return nums.length != size;
    }

    @Test
    public void rob()
    {
        assertEquals(4, rob(new int[] { 1, 2, 3, 1 }));
        assertEquals(12, rob(new int[] { 2, 7, 9, 3, 1 }));
        assertEquals(4, rob(new int[] { 2, 1, 1, 2 }));
        assertEquals(1, rob(new int[] { 1 }));
        assertEquals(14, rob(new int[] { 4, 1, 2, 7, 5, 3, 1 }));
        assertEquals(39, rob(new int[] { 6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3 }));
    }

    private int rob(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++)
        {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    private int rob2(int[] nums)
    {

        if (nums == null || nums.length == 0)
            return 0;

        int even = 0;
        int odd = 0;

        for (int i = 0; i < nums.length; i++)
        {
            if (i % 2 == 0)
            {
                even += nums[i];
                even = even > odd ? even : odd;
            }
            else
            {
                odd += nums[i];
                odd = even > odd ? even : odd;
            }
        }

        return even > odd ? even : odd;
    }

    private int rob3(int[] nums)
    {
        if (nums.length == 0)
        {
            return 0;
        }

        int[] mem = new int[nums.length + 1];
        Arrays.fill(mem, -1);

        mem[0] = 0;

        return helper(nums.length, mem, nums);
    }

    private int helper(int size, int[] mem, int[] nums)
    {
        if (size < 1)
        {
            return 0;
        }

        if (mem[size] != -1)
        {
            return mem[size];
        }

        // two cases
        int firstSelected = helper(size - 2, mem, nums) + nums[nums.length - size];
        int firstUnselected = helper(size - 1, mem, nums);

        return mem[size] = Math.max(firstSelected, firstUnselected);
    }

    @Test
    public void rotateArray()
    {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        rotate(array, 3);
        assertEquals(true, Arrays.equals(new int[] { 5, 6, 7, 1, 2, 3, 4 }, array));

        array = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        rotate(array, 10);
        assertEquals(true, Arrays.equals(new int[] { 5, 6, 7, 1, 2, 3, 4 }, array));

        array = new int[] { -1, -100, 3, 99 };
        rotate(array, 2);
        assertEquals(true, Arrays.equals(new int[] { 3, 99, -1, -100 }, array));

        array = new int[] { 1, 2, 3, 4, 5, 6 };
        rotate(array, 3);
        assertEquals(true, Arrays.equals(new int[] { 4, 5, 6, 1, 2, 3 }, array));
    }

    private void rotate1(int[] nums, int k)
    {
        int length = nums.length;

        while (length < k)
            k = k - length;

        int[] newArray = new int[length];
        for (int i = 0; i < length; i++)
        {
            int value = nums[i];
            int newPosition = i + k < length ? i + k : i + k - length;
            newArray[newPosition] = value;
        }

        for (int i = 0; i < newArray.length; i++)
        {
            nums[i] = newArray[i];
        }
    }

    private void rotate(int[] nums, int k)
    {
        int length = nums.length;

        while (length < k)
            k = k - length;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < length; i++)
        {
            if (null != map.get(i))
            {

                int value = map.get(i);
                int newPosition = i + k < length ? i + k : i + k - length;

                if (map.get(newPosition) == null)
                    map.put(newPosition, nums[newPosition]);

                nums[newPosition] = value;
                continue;
            }

            int value = nums[i];
            int newPosition = i + k < length ? i + k : i + k - length;

            map.put(newPosition, nums[newPosition]);

            nums[newPosition] = value;

        }
    }

    @Test
    public void intersection()
    {
        assertEquals(true, Arrays.equals(new int[] { 2 }, intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
        assertEquals(true, Arrays.equals(new int[] { 4, 9 }, intersection(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 })));

    }

    private int[] intersection(int[] nums1, int[] nums2)
    {
        Set<Integer> set = IntStream.of(nums1).boxed().distinct().collect(Collectors.toSet());
        Set<Integer> set2 = IntStream.of(nums2).boxed().distinct().collect(Collectors.toSet());

        set.retainAll(set2);

        return set.stream().mapToInt(x -> x).toArray();
    }

    @Test
    public void majorityElement()
    {
        assertEquals(3, majorityElement(new int[] { 3, 2, 3 }));
        assertEquals(2, majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
        assertEquals(3, majorityElement(new int[] { 3, 3, 4 }));

    }

    private int majorityElement(int[] nums)
    {

        Map<Integer, Integer> mapToFreq = new HashMap<Integer, Integer>();
        int max = 0;
        int maxFreq = 0;

        for (int i = 0; i < nums.length; i++)
        {
            int value = nums[i];
            int freq = mapToFreq.getOrDefault(value, 0) + 1;

            if (freq > maxFreq)
            {

                max = value;
                maxFreq = freq;
            }

            mapToFreq.put(value, freq);
        }

        return max;

    }

    private int majorityElement1(int[] nums)
    {

        Arrays.sort(nums);
        return nums[nums.length / 2];

    }

    private int countInRange(int[] nums, int num, int lo, int hi)
    {
        int count = 0;
        for (int i = lo; i <= hi; i++)
        {
            if (nums[i] == num)
            {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi)
    {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi)
        {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right)
        {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    private int majorityElementDC(int[] nums)
    {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    @Test
    public void minStack()
    {
        MinStack stack = new MinStack();

        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        assertEquals(-3, stack.getMin());

        stack.pop();

        assertEquals(0, stack.top());
        assertEquals(-2, stack.getMin());

        stack = new MinStack();

        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.top());

        assertEquals(1, stack.getMin());

        stack.pop();

        assertEquals(1, stack.getMin());
        assertEquals(1, stack.top());

    }

    class MinStack
    {

        private Stack<Integer> stack;
        private int min = Integer.MAX_VALUE;

        public MinStack()
        {
            stack = new Stack<Integer>();
        }

        public void push(int x)
        {
            min = Math.min(min, x);
            stack.push(x);
        }

        public void pop()
        {
            stack.pop();
            min = Integer.MAX_VALUE;

            for (Integer x : stack)
                min = Math.min(min, x);

        }

        public int top()
        {
            return stack.peek();
        }

        public int getMin()
        {
            return min;
        }
    }

    @Test
    public void convertToTitle()
    {
        assertEquals("A", convertToTitle(1));
        assertEquals("AB", convertToTitle(28));
        assertEquals("BB", convertToTitle(54));

        assertEquals("ZY", convertToTitle(701));
        // assertEquals("ZZ", convertToTitle(702));
        assertEquals("AAA", convertToTitle(703));
        assertEquals("AAAA", convertToTitle(1406));

    }

    public String convertToTitle(int n)
    {

        char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        String out = "";

        while (n > 0)
        {

            int div = n / 26;

            int position = div > 0 ? div - 1 : n - 1;

            out += Character.toString((char) alphaBet[position]).toUpperCase();

            if (div == 0)
                break;

            n = n - (div * 26);
        }

        return out;
    }

    @Test
    public void missingNumber()
    {
        assertEquals(2, missingNumber(new int[] { 3, 0, 1 }));
        assertEquals(8, missingNumber(new int[] { 9, 6, 4, 2, 3, 5, 7, 0, 1 }));
        assertEquals(2, missingNumber(new int[] { 0, 1 }));
        assertEquals(0, missingNumber(new int[] { 1 }));

    }

    public int missingNumber(int[] nums)
    {
        Arrays.sort(nums);

        int length = nums.length;

        if (nums[0] != 0)
            return 0;

        if (nums[length - 1] != length)
            return length;

        int start = 0;
        int first = nums[start];

        while (start + 1 < length)
        {
            int next = nums[++start];

            if (next - first != 1)
                return next - 1;

            first = next;
        }

        return length - 2;
    }

    @Test
    public void singleNumber()
    {
        assertEquals(1,
                singleNumber(new int[] { 2, 2, 1 }));

        assertEquals(4,
                singleNumber(new int[] { 4, 1, 2, 1, 2 }));
    }

    public int singleNumber(int[] nums)
    {

        Set<Integer> set = new HashSet<Integer>();

        int length = nums.length;

        for (int i = 0; i < length; i++)
        {
            int value = nums[i];

            if (!set.contains(value))
                set.add(value);
            else
                set.remove(value);

        }

        return set.iterator().next();
    }

    public int singleNumber1(int[] nums)
    {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        int length = nums.length;

        for (int i = 0; i < length; i++)
        {
            int value = nums[i];

            if (null == map.get(value))
                map.put(value, 1);
            else
                map.remove(value);

        }

        return map.entrySet().iterator().next().getKey();
    }

    public int singleNumber2(int[] nums)
    {

        Set<Integer> set = Arrays.stream(nums).distinct().boxed().collect(Collectors.toSet());
        int sum = Arrays.stream(nums).sum();

        return 2 * (set.stream().reduce(0, (a, b) -> a + b) - sum);
    }

    /**
     * 
     * 
     * If we take XOR of zero and some bit, it will return that bit a⊕0=a
     * 
     * If we take XOR of two same bits, it will return 0 a⊕a=0
     * 
     * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     * 
     * @param nums
     * @return
     */
    public int singleNumber3(int[] nums)
    {

        int length = nums.length;

        int value = 0;

        for (int i = 0; i < length; i++)
        {

            value ^= nums[i];
        }

        return value;
    }

    @SuppressWarnings("serial")
    @Test
    public void addToArrayForm()
    {
        assertEquals(new ArrayList<Integer>()
        {
            {
                add(1);
                add(2);
                add(3);
                add(4);
            }
        }, addToArrayForm(new int[] { 1, 2, 0, 0 }, 34));
    }

    public List<Integer> addToArrayForm(int[] digits, int K)
    {

        String value = Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining(""));

        BigInteger retValue = new BigInteger(value).add(new BigInteger(new Integer(K).toString()));

        return retValue.toString().chars().mapToObj(c -> new Integer(String.valueOf((char) c))).collect(Collectors.toList());
    }

    @Test
    public void plusOne()
    {
        assertTrue(Arrays.equals(new int[] { 1, 2, 4 }, plusOne(new int[] { 1, 2, 3 })));
        assertTrue(Arrays.equals(new int[] { 4, 3, 2, 2 }, plusOne(new int[] { 4, 3, 2, 1 })));
        assertTrue(Arrays.equals(new int[] { 1, 0, 0, 0 }, plusOne(new int[] { 9, 9, 9 })));
        assertTrue(Arrays.equals(new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 1 }, plusOne(new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 })));

    }

    private int[] plusOne(int[] digits)
    {
        String value = Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining(""));

        BigInteger retValue = new BigInteger(value).add(new BigInteger("1"));

        return retValue.toString().chars().mapToObj(c -> String.valueOf((char) c)).mapToInt(x -> new Integer(x)).toArray();
    }

    @Test
    public void updateMatrix()
    {
        assertTrue(Arrays.deepEquals(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } },
                updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } })));
        assertTrue(Arrays.deepEquals(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 2, 1 } },
                updateMatrix(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } })));

        assertTrue(Arrays.deepEquals(new int[][] {},
                updateMatrix(new int[][] { { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1 }, { 1, 0, 0, 0, 1 } })));
    }

    private int[][] updateMatrix(int[][] matrix)
    {

        int rows = matrix.length;

        if (rows == 0)
            return matrix;

        int cols = matrix[0].length;

        int[][] dist = new int[][] {};

        Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (matrix[i][j] == 0)
                {
                    dist[i][j] = 0;
                    q.add(new Pair<Integer, Integer>(i, j)); // Put all 0s in the queue.
                }

        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        while (!q.isEmpty())
        {
            Pair<Integer, Integer> curr = q.peek();
            q.poll();
            for (int i = 0; i < 4; i++)
            {
                int new_r = curr.getKey() + dir[i][0], new_c = curr.getValue() + dir[i][1];
                if (new_r >= 0 && new_c >= 0 && new_r < rows && new_c < cols)
                {
                    if (dist[new_r][new_c] > dist[curr.getKey()][curr.getValue()] + 1)
                    {
                        dist[new_r][new_c] = dist[curr.getKey()][curr.getValue()] + 1;
                        q.add(new Pair<Integer, Integer>(new_r, new_c));
                    }
                }
            }
        }
        return dist;

    }

    private int[][] updateMatrix1(int[][] matrix)
    {
        int matrixLength = matrix.length;

        for (int i = 0; i < matrixLength; i++)
        {
            int[] row = matrix[i];

            int rowLength = row.length;

            for (int j = 0; j < rowLength; j++)
            {

                if (matrix[i][j] == 0)
                    continue;

                boolean hasFoundDistance = false;

                while (!hasFoundDistance)
                {

                    int distance = 1;
                    if (j - distance >= 0 && (matrix[i][j - distance] == 0)
                            || (i - distance >= 0 && matrix[i - distance][j] == 0)
                            || (j + distance < rowLength && matrix[i][j + distance] == 0)
                            || (i + distance < matrixLength && matrix[i + distance][j] == 0))
                    {
                        matrix[i][j] = distance;
                        hasFoundDistance = true;
                    }
                    else if (i - distance >= 0 && (j - distance >= 0 && matrix[i - distance][j - distance] == 0
                            || j + distance < rowLength && matrix[i - distance][j + distance] == 0))
                    {
                        matrix[i][j] = distance + 1;
                        hasFoundDistance = true;
                    }
                    else if (i + distance < matrixLength && (j - distance >= 0 && matrix[i + distance][j - distance] == 0
                            || j + distance < rowLength && matrix[i + distance][j + distance] == 0))
                    {
                        matrix[i][j] = distance + 1;
                        hasFoundDistance = true;
                    }
                    else
                        distance++;
                }

            }
        }

        return matrix;
    }

    @Test
    public void maxProfitII()
    {
        assertEquals(7, maxProfitII(new int[] { 7, 1, 5, 3, 6, 4 }));
        assertEquals(22, maxProfitII(new int[] { 7, 1, 8, 5, 18, 20 }));
        assertEquals(7, maxProfitII(new int[] { 7, 3, 5, 1, 6, 4 }));
        assertEquals(4, maxProfitII(new int[] { 1, 2, 3, 4, 5 }));
        assertEquals(0, maxProfitII(new int[] { 7, 6, 4, 3, 1 }));
        assertEquals(4, maxProfitII(new int[] { 1, 2, 3, 4, 5, 4, 3, 2 }));
        assertEquals(11, maxProfitII(new int[] { 7, 6, 4, 3, 1, 5, 3, 10 }));

    }

    private int maxProfitII(int[] prices)
    {

        // to hold profit

        int profit = 0;
        // to hold current minimum
        int currentMin = Integer.MAX_VALUE;

        // iterate thru prices
        for (int price : prices)
        {
            // check if this price is smaller than current min
            if (price < currentMin)
            {
                // set new current min
                currentMin = price;
            }
            else
            {
                // check if we can profit by selling stock on this day
                int currentProfit = price - currentMin;
                // check if we can profit
                if (currentProfit > 0)
                {
                    // add to profit
                    profit += currentProfit;
                    // set current min to current price
                    currentMin = price;
                }
            }
        }

        // result
        return profit;
    }

    @Test
    public void maxProfit()
    {
        assertEquals(5, maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        assertEquals(5, maxProfitLinear(new int[] { 7, 3, 5, 1, 6, 4 }));

    }

    private int maxProfitLinear(int[] prices)
    {

        if (prices.length == 0)
            return 0;

        int maxProf = 0;
        int minBuy = prices[0];

        for (int i = 1; i < prices.length; i++)
        {
            int sell = prices[i];

            if (minBuy >= sell)
            {

                minBuy = sell;
                continue;
            }

            maxProf = Math.max(sell - minBuy, maxProf);
        }

        return maxProf;
    }

    private int maxProfit(int[] prices)
    {

        int maxProf = 0;

        for (int i = 0; i < prices.length; i++)
        {
            int buy = prices[i];

            for (int j = i; j < prices.length; j++)
            {
                int sell = prices[j];

                if (buy < sell)
                    maxProf = Math.max(maxProf, sell - buy);
            }
        }

        return maxProf;
    }

    @Test
    public void maxSubArray()
    {
        assertEquals(9, maxSubArrayGreedy(new int[] { -1, 2, -3, 4, 5 }));
        assertEquals(9, maxSubArrayPrefSum(new int[] { -1, 2, -3, 4, 5 }));
        assertEquals(9, maxSubArrayDivideConquer(new int[] { -1, 2, -3, 4, 5 }));

    }

    public int maxSubArrayGreedy(int[] nums)
    {

        int start = 0;
        int end = start;
        int sum = Integer.MIN_VALUE;
        int currentSum = 0;
        while (start < nums.length && end < nums.length)
        {
            currentSum += nums[end];
            sum = Math.max(sum, currentSum);
            if (currentSum < 0)
            {
                start = end + 1;
                end = start;
                currentSum = 0;
            }
            else
            {
                end++;
            }
        }
        return sum;
    }

    public int maxSubArrayPrefSum(int[] nums)
    {
        int currentSum = 0;
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++)
        {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            sum = Math.max(currentSum, sum);
        }

        return sum;
    }

    public int maxSubArrayDivideConquer(int[] nums)
    {
        return maxSubArrayWithBoundries(nums, 0, nums.length - 1);
    }

    private int maxSubArrayWithBoundries(int[] nums, int start, int end)
    {
        if (start == end)
            return nums[start];

        int mid = (start + end) / 2;
        int leftMax = maxSubArrayWithBoundries(nums, start, mid);
        int rightMax = maxSubArrayWithBoundries(nums, mid + 1, end);
        int crossingMidMax = crossingMidMax(nums, start, end);
        return Math.max(Math.max(leftMax, rightMax), crossingMidMax);
    }

    private int crossingMidMax(int[] nums, int start, int end)
    {
        int mid = (start + end) / 2;
        int leftMidMax = Integer.MIN_VALUE;
        int rightMidMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= start; i--)
        {
            sum += nums[i];
            leftMidMax = Math.max(leftMidMax, sum);
        }
        sum = 0;
        for (int i = mid + 1; i <= end; i++)
        {
            sum += nums[i];
            rightMidMax = Math.max(rightMidMax, sum);
        }
        return leftMidMax + rightMidMax;
    }

    @Test
    public void firstBadVersion()
    {
        int n = 5;
        k = 4;
        assertEquals(k, firstBadVersion(n - 1));

        n = 10;
        k = 4;
        assertEquals(k, firstBadVersion(n - 1));

        n = 10;
        k = 2;
        assertEquals(k, firstBadVersion(n - 1));

        n = 1000000;
        k = 1;
        assertEquals(k, firstBadVersion(n - 1));

        n = 1000000;
        k = 1000000;
        assertEquals(k, firstBadVersion(n));

        n = 0;
        k = 0;
        assertEquals(k, firstBadVersion(n - 1));

        n = 2126753390;
        k = 1702766719;
        assertEquals(k, firstBadVersion(n - 1));

    }

    public int firstBadVersion(int n)
    {

        if (n <= 0)
            return 0;

        long left = 0;
        long right = n;

        while (left <= right)
        {

            Long middle = (left + right) / 2;

            boolean isBadVersion = isBadVersion(middle.intValue());

            if (isBadVersion)
            {

                if (isBadVersion(middle.intValue() - 1))
                    right = middle - 1;
                else
                    return middle.intValue();
            }
            else
                left = middle + 1;
        }

        return 0;
    }

    private boolean isBadVersion(int n)
    {
        if (n >= k)
            return true;

        return false;
    }

    @Test
    public void searchInsert()
    {
        assertEquals(0, searchInsert(new int[] { 1, 3, 5, 6 }, 0));
        assertEquals(2, searchInsert(new int[] { 1, 3, 5, 6 }, 5));
        assertEquals(1, searchInsert(new int[] { 1, 3, 5, 6 }, 2));
        assertEquals(4, searchInsert(new int[] { 1, 3, 5, 6 }, 7));
        assertEquals(1, searchInsert(new int[] { 1 }, 2));
        assertEquals(0, searchInsert(new int[] {}, 1));
        assertEquals(0, searchInsert(new int[] { 1, 3 }, 0));
        assertEquals(1, searchInsert(new int[] { 1, 3 }, 2));
        assertEquals(0, searchInsert(new int[] { 1, 3 }, 1));
        assertEquals(2, searchInsert(new int[] { 1, 4, 6, 7, 8, 9 }, 6));

    }

    private int searchInsert(int[] nums, int target)
    {
        return search(nums, 0, nums.length - 1, target);
    }

    int search(int nums[], int left, int right, int target)
    {
        if (nums.length == 0)
            return 0;

        // Binary search
        int middle = 0;
        while (left <= right)
        {

            middle = (left + right) / 2;

            int middleEl = nums[middle];

            if (middleEl == target)
                return middle;
            else if (middleEl < target)
                left = middle + 1;
            else if (middleEl > target)
                right = middle - 1;

        }

        int length = nums.length;

        // If index not exists it is first, last or iterate to find the match between lower and higher
        if (target >= nums[length - 1])
            return length;
        else if (target <= nums[0])
            return 0;
        else
        {

            int tmp = 0;

            while (tmp < length)
            {
                if (target > nums[tmp] && target < nums[tmp + 1])
                    return tmp + 1;

                tmp++;
            }
        }

        return -1;
    }

    @Test
    public void moveZeroes()
    {
        int[] nums = new int[] { 0, 1, 0, 3, 12 };
        moveZeroes(nums);

        assertEquals(true, Arrays.equals(nums, new int[] { 1, 3, 12, 0, 0 }));
        nums = new int[] { 0, 1, 0 };
        moveZeroes(nums);
        assertEquals(true, Arrays.equals(nums, new int[] { 1, 0, 0 }));

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
        int[] array = new int[] { 3, 2, 2, 3 };
        int size = removeElement(array, 3);

        assertEquals(true, Arrays.equals(new int[] { 2, 2 }, Arrays.copyOfRange(array, 0, size)));

        array = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
        size = removeElement(array, 2);

        assertEquals(true, Arrays.equals(new int[] { 0, 1, 3, 0, 4 }, Arrays.copyOfRange(array, 0, size)));

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
