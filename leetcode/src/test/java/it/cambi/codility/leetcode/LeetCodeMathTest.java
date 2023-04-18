/**
 *
 */
package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author luca
 */
class LeetCodeMathTest {

    @Test
    public void isHappy() {
        assertTrue(isHappy(19));
        assertTrue(isHappy(1));
        assertFalse(isHappy(2));
    }

    public boolean isHappy(int n) {

        int sum;

        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);

            sum = 0;
            String number = String.valueOf(n);

            for (int i = 0; i < number.length(); i++) {
                sum = sum + (int) (Math.pow(Character.getNumericValue(number.charAt(i)), 2));
            }

            n = sum;
        }

        return n == 1;
    }

    @Test
    public void convertTemperature() {
        assertArrayEquals(new double[]{309.65000, 97.70000}, convertTemperature(36.50));
        assertArrayEquals(new double[]{395.26000, 251.79800}, convertTemperature(122.11));
    }

    public double[] convertTemperature(double celsius) {

        double[] sol = new double[2];

        sol[0] = celsius + 273.15;
        sol[1] = celsius * 1.80 + 32.00;
        return sol;
    }

    @Test
    public void plusMinus() {
        assertEquals(
                List.of("0.500000", "0.333333", "0.166667"), plusMinus(List.of(-4, 3, -9, 0, 4, 1)));
    }

    private LinkedList<String> plusMinus(List<Integer> arr) {
        int pos = 0, neg = 0, zero = 0;

        for (Integer integer : arr) {
            if (integer < 0) {
                neg++;
            } else if (integer == 0) {
                zero++;
            } else {
                pos++;
            }
        }

        LinkedList<String> sol = new LinkedList<>();

        sol.add(String.format("%.6f", (double) pos / arr.size()));
        sol.add(String.format("%.6f", (double) neg / arr.size()));
        sol.add(String.format("%.6f", (double) zero / arr.size()));

        return sol;
    }

    @Test
    public void miniMaxSum() {
        assertEquals(List.of(10L, 14L), miniMaxSum(Arrays.asList(1, 2, 3, 4, 5)));
    }

    private List<Long> miniMaxSum(List<Integer> arr) {

        Collections.sort(arr);
        long sum = 0;

        for (Integer integer : arr) {
            sum += integer;
        }

        return List.of(sum - arr.get(arr.size() - 1), sum - arr.get(0));
    }

    @Test
    public void isRectangleOverlap() {
        assertTrue(isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        assertFalse(isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        assertFalse(isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));
        assertTrue(isRectangleOverlap(new int[]{7, 8, 13, 15}, new int[]{10, 8, 12, 20}));
        assertTrue(isRectangleOverlap(new int[]{7, 8, 13, 15}, new int[]{7, 8, 13, 15}));
        assertTrue(isRectangleOverlap(new int[]{4, 4, 14, 7}, new int[]{4, 3, 8, 8}));
    }

    private boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        return !(rec1[2] <= rec2[0]
                || // left
                rec1[3] <= rec2[1]
                || // bottom
                rec1[0] >= rec2[2]
                || // right
                rec1[1] >= rec2[3]); // top
    }

    @Test
    public void maxValue() {
        assertEquals("999", maxValue("99", 9));
        assertEquals("998", maxValue("99", 8));
        assertEquals("87", maxValue("8", 7));
        assertEquals("787", maxValue("78", 7));
        assertEquals("98", maxValue("8", 9));
        assertEquals("-123", maxValue("-13", 2));
        assertEquals("-12", maxValue("-1", 2));
        assertEquals("828824579515", maxValue("28824579515", 8));
    }

    private String maxValue(String n, int x) {
        StringBuilder v;
        int i;

        if (n.charAt(0) != '-') {
            v = new StringBuilder(n);
            for (i = 0; i < v.length(); i++) {
                if (x > Character.getNumericValue(v.charAt(i))) {
                    v.insert(i, x);
                    return v.toString();
                }
            }
        } else {
            v = new StringBuilder(n);

            for (i = 1; i < v.length(); i++) {
                if (x < Character.getNumericValue(v.charAt(i))) {
                    v.insert(i, x);
                    return v.toString();
                }
            }
        }

        return v.append(x).toString();
    }

    // Jump 1 or 2 steps, min cost to climb stairs
    @Test
    public void minCostClimbingStairs() {
        assertEquals(15, minCostClimbingStairs(new int[]{10, 15, 20}));
        assertEquals(6, minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        assertEquals(6, minCostClimbingStairs1(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        assertEquals(6, minCostClimbingStairs2(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    private int minCostClimbingStairs(int[] cost) {
        int[] minimumCost = new int[cost.length + 1];

        // Start iteration from step 2, since the minimum cost of reaching
        // step 0 and step 1 is 0
        for (int i = 2; i < minimumCost.length; i++) {
            int takeOneStep = minimumCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minimumCost[i - 2] + cost[i - 2];
            minimumCost[i] = Math.min(takeOneStep, takeTwoSteps);
        }

        // The final element in minimumCost refers to the top floor
        return minimumCost[minimumCost.length - 1];
    }

    private HashMap<Integer, Integer> memo = new HashMap<>();

    private int minCostClimbingStairs1(int[] cost) {
        return minimumCost(cost.length, cost);
    }

    private int minimumCost(int i, int[] cost) {
        // Base case, we are allowed to start at either step 0 or step 1
        if (i <= 1) {
            return 0;
        }

        // Check if we have already calculated minimumCost(i)
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // If not, cache the result in our hash map and return it
        int downOne = cost[i - 1] + minimumCost(i - 1, cost);
        int downTwo = cost[i - 2] + minimumCost(i - 2, cost);
        memo.put(i, Math.min(downOne, downTwo));
        return memo.get(i);
    }

    private int minCostClimbingStairs2(int[] cost) {
        int downOne = 0;
        int downTwo = 0;
        for (int i = 2; i < cost.length + 1; i++) {
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }

        return downOne;
    }

    @Test
    public void numWaterBottles() {
        assertEquals(13, numWaterBottles(9, 3));
        assertEquals(19, numWaterBottles(15, 4));
        assertEquals(6, numWaterBottles(5, 5));
        assertEquals(2, numWaterBottles(2, 3));
        assertEquals(17, numWaterBottles(15, 7));
    }

    public int numWaterBottles(int numBottles, int numExchange) {

        if (numBottles < numExchange) return numBottles;

        int sol = numBottles;

        while (numBottles >= numExchange) {
            sol += numBottles / numExchange;
            numBottles =
                    numBottles - ((numBottles / numExchange) * numExchange) + numBottles / numExchange;
        }

        return sol;
    }

    @Test
    public void countBalls() {
        assertEquals(2, countBalls(1, 10));
        assertEquals(2, countBalls(5, 15));
        assertEquals(2, countBalls(19, 28));
    }

    private int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        while (lowLimit <= highLimit) {
            int num = lowLimit;
            int sum = 0;
            while (num > 0) {
                sum = sum + num % 10;
                num = num / 10;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            lowLimit++;
        }

        return map.values().stream().max(Comparator.naturalOrder()).orElse(0);
    }

    @Test
    public void numberOfMatches() {
        assertEquals(6, numberOfMatches(7));
        assertEquals(13, numberOfMatches(14));
    }

    private int numberOfMatches(int num) {

        int matches = 0;

        while (num > 1) {

            if ((num & 1) == 0) {
                num -= num / 2;
                matches += num;

            } else {
                matches += (num - 1) / 2;
                num -= (num - 1) / 2;
            }
        }

        return matches;
    }

    @Test
    public void numberOfSteps() {
        assertEquals(6, numberOfSteps(14));
        assertEquals(4, numberOfSteps(8));
        assertEquals(12, numberOfSteps(123));
    }

    private int numberOfSteps(int num) {

        int step = 0;

        while (num > 0) {
            if ((num & 1) == 0) num = num / 2;
            else num = num - 1;
            step++;
        }

        return step;
    }

    @Test
    public void arrangeCoins() {
        assertEquals(2, arrangeCoins(5));
        assertEquals(3, arrangeCoins(8));
        assertEquals(0, arrangeCoins(0));
        assertEquals(1, arrangeCoins(1));
        assertEquals(1, arrangeCoins(2));
        assertEquals(2, arrangeCoins(3));
        assertEquals(60070, arrangeCoins(1804289383));
    }

    private int arrangeCoins(int n) {

        return (int) binarySearch(n, 0);
    }

    private long binarySearch(int n, int fromIndex) {
        long low = fromIndex;
        long high = n;

        while (low <= high) {
            long mid = (low + high) >>> 1; // (low + high) / 2;

            long sum = mid * (1 + mid) / 2;

            if (sum == n) return mid;

            long sumPrev = (mid - 1) * (mid) / 2;

            if (sum < n) low = mid + 1;
            else if (sum > n && sumPrev < n) return mid - 1;
            else high = mid - 1;
        }

        return 0; // key not found.
    }

    @Test
    public void hammingWeight() {
        assertEquals(3, hammingWeight(00000000000000000000000000001011));
        assertEquals(1, hammingWeight(00000000000000000000000010000000));
    }

    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);

        return (int) str.chars().filter(c -> c == '1').count();
    }

    @Test
    public void isPowerOfTwo() {
        assertTrue(isPowerOfTwo(1));
        assertTrue(isPowerOfTwo(16));
        assertFalse(isPowerOfTwo(218));
    }

    private boolean isPowerOfTwo(int n) {

        double[] power = new double[65];
        power[0] = 1.0;

        for (int i = 1; i < 65; i++) power[i] = Math.pow(2.0, (double) i);

        int k = 0;

        while ((double) n >= power[k])
            if (power[k] == n) return true;
            else k++;

        return false;
    }

    @Test
    public void getHint() {
        assertEquals("1A3B", getHint("1807", "7810"));
        assertEquals("1A1B", getHint("1123", "0111"));
        assertEquals("1A0B", getHint("11", "10"));
        assertEquals("0A4B", getHint("1122", "2211"));
        assertEquals("0A1B", getHint("1234", "0111"));
        assertEquals("3A0B", getHint("1122", "1222"));
    }

    private String getHint(String secret, String guess) {

        int len = secret.length();
        int[] s = new int[10];
        int[] g = new int[10];
        int bulls = 0;
        for (int i = 0; i < len; i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;
        }
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += Math.min(s[i], g[i]);
        }

        return bulls + "A" + (total - bulls) + "B";
    }

    @Test
    public void trailingZeroes() {

        assertEquals(2, factorial(10));
        assertEquals(0, factorial(3));
        assertEquals(1, factorial(5));
        assertEquals(2, factorial(13));
        assertEquals(7, factorial(30));
        assertEquals(0, factorial(0));
        assertEquals(206, factorial(832));
        assertEquals(452137076, factorial(1808548329));
    }

    private int factorial(int n) {
        if (n < 5) {
            return 0;
        }

        int res = 0;
        while (n >= 5) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }

    /**
     * This runs in linear time but it is interesting to see. In creates an array of the factorial
     * number in reverse order where a single digit is shifted by the reminder of 10
     *
     * @param n
     * @return
     */
    private int factorial1(int n) {
        int[] res = new int[1000];

        // Initialize result
        res[0] = 1;
        int res_size = 1;

        // Apply simple factorial formula
        // n! = 1 * 2 * 3 * 4...*n
        for (int x = 2; x <= n; x++) res_size = multiply(x, res, res_size);

        StringBuilder builder = new StringBuilder();

        for (int i = res_size - 1; i >= 0; i--) builder.append(res[i]);

        return countZeros(builder.toString());
    }

    private int countZeros(String numberToString) {

        int count = 0;
        int index = numberToString.length() - 1;
        int end = numberToString.length();
        while (numberToString.substring(index, end).contains("0")) {
            index--;
            end--;
            count++;
        }
        return count;
    }

    static int multiply(int x, int[] res, int res_size) {
        int carry = 0; // Initialize carry

        // One by one multiply n with individual
        // digits of res[]
        for (int i = 0; i < res_size; i++) {
            int prod = res[i] * x + carry;
            res[i] = prod % 10; // Store last digit of
            // 'prod' in res[]
            carry = prod / 10; // Put rest in carry
        }

        // Put carry in res and increase result size
        while (carry != 0) {
            res[res_size] = carry % 10;
            carry = carry / 10;
            res_size++;
        }
        return res_size;
    }

    // Jump 1 or 2 steps, possible ways to climb stairs
    @Test
    public void climbStairs() {
        assertEquals(2, climbStairs(2));
        assertEquals(3, climbStairs(3));
        assertEquals(5, climbStairs(4));
        assertEquals(8, climbStairs(5));
        assertEquals(1134903170, climbStairs(44));
    }

    private int climbStairs(int n) {
        int[] memo = new int[n + 1];
        return climbStairsRec(0, n, memo);
    }

    public int climbStairsRec(int i, int n, int[] memo) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }

        memo[i] = climbStairsRec(i + 1, n, memo) + climbStairsRec(i + 2, n, memo);
        return memo[i];
    }

    @Test
    public void isPerfectSquare() {
        assertTrue(isPerfectSquare(1));
        assertFalse(isPerfectSquare(2));
        assertTrue(isPerfectSquare(16));
        assertFalse(isPerfectSquare(14));
        assertFalse(isPerfectSquare(1000));
        assertTrue(isPerfectSquare(100));
        assertFalse(isPerfectSquare(10000000));
        assertFalse(isPerfectSquare(2147483647));
    }

    private boolean isPerfectSquare(int num) {
        if (num == 1) return true;

        long left = 0;
        long right = (long) num;

        while (left < right) {

            long mid = (left + right) / 2;

            if (mid * mid == num) return true;
            else if (num > mid * mid && num < (mid + 1) * (mid + 1)) return false;
            else if (num < mid * mid) right = mid;
            else if (num > mid * mid) left = mid;
        }

        return false;
    }

    long k;

    @Test
    public void guessNumber() {
        int n = 10;
        k = 6;
        assertEquals(k, guessNumber(n));

        n = 2126753390;
        k = 1702766719;
        assertEquals(k, guessNumber(n));
    }

    public int guessNumber(int n) {
        long left = 0;
        long right = n;

        while (left <= right) {
            long middle = (left + right) / 2;

            int guess = guess((int) middle);

            switch (guess) {
                case 1:
                    left = middle + 1;
                    break;

                case -1:
                    right = middle - 1;
                    break;

                default:
                    return (int) middle;
            }
        }

        return 0;
    }

    private int guess(int n) {
        if ((long) n > k) return -1;
        else if ((long) n < k) return 1;

        return 0;
    }

    @Test
    public void myAtoi() {
        assertEquals(-42, myAtoi("   -42"));
        assertEquals(4193, myAtoi("4193 with words"));
        assertEquals(0, myAtoi("words and 987"));
        assertEquals(Integer.MIN_VALUE, myAtoi("-91283472332"));
        assertEquals(3, myAtoi("3.14159"));
        assertEquals(1, myAtoi("+1"));
        assertEquals(-5, myAtoi("-5-"));
    }

    public int myAtoi(String str) {
        str = str.trim();

        int index = str.indexOf(" ");

        if (str.length() > 0) {
            if (index > -1) str = str.substring(0, index);

            return getValue(str);

        } else {
            return 0;
        }
    }

    private int getValue(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                str = str.substring(0, i);
                break;
            }
        }

        double value;
        try {

            value = Double.parseDouble(str);
        } catch (Exception e) {
            return 0;
        }

        if (value > Integer.MAX_VALUE) return Integer.MAX_VALUE;

        if (value < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) value;
    }

    @Test
    public void reverse() {
        assertEquals(-1, reverse(-1));
        assertEquals(321, reverse(123));
        assertEquals(-321, reverse(-123));
        assertEquals(21, reverse(120));
        assertEquals(0, reverse(1534236469));
    }

    public int reverse(int x) {

        String s = Integer.toString(x);

        boolean isNegative = s.charAt(0) == '-';

        StringBuilder builder = new StringBuilder();
        if (isNegative) builder.append("-");

        for (int i = s.length() - 1; isNegative ? i >= 1 : i >= 0; i--) builder.append(s.charAt(i));

        int result = 0;
        try {
            result = Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {
            // TODO: handle exception
        }
        return result;
    }
}
