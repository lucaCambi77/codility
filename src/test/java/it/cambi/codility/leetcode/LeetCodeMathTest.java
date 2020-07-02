/** */
package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
public class LeetCodeMathTest {
  long k;

  @Test
  public void numberOfSteps() {
    assertEquals(6, numberOfSteps(14));
    assertEquals(4, numberOfSteps(8));
    assertEquals(12, numberOfSteps(123));
  }

  public int numberOfSteps(int num) {

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

  public int arrangeCoins(int n) {

    return (int) binarySearch(n, 0);
  }

  public long binarySearch(int n, int fromIndex) {
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
    assertEquals(true, isPowerOfTwo(1));
    assertEquals(true, isPowerOfTwo(16));
    assertEquals(false, isPowerOfTwo(218));
  }

  public boolean isPowerOfTwo(int n) {

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

  public String getHint(String secret, String guess) {

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

    return String.valueOf(bulls) + "A" + String.valueOf(total - bulls) + "B";
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

  private int countZeros(String numberToString) {

    int count = 0;
    int index = numberToString.length() - 1;
    int end = numberToString.length();
    while (numberToString.substring(index, end).indexOf("0") >= 0) {
      index--;
      end--;
      count++;
    }
    return count;
  }

  /**
   * This runs in linear time but it is interesting to see. In creates an array of the factorial
   * number in reverse order where a single digit is shifted by the reminder of 10
   *
   * @param n
   * @return
   */
  private int factorial1(int n) {
    int res[] = new int[1000];

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

  static int multiply(int x, int res[], int res_size) {
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

  @Test
  public void climbStairs() {
    assertEquals(2, climbStairs(2));
    assertEquals(3, climbStairs(3));
    assertEquals(5, climbStairs(4));
    assertEquals(8, climbStairs(5));

    // assertEquals(1134903170, climbStairs(44));

  }

  private int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climbStairsRec(0, n, memo);
  }

  public int climbStairsRec(int i, int n, int memo[]) {
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
    assertEquals(true, isPerfectSquare(1));
    assertEquals(false, isPerfectSquare(2));
    assertEquals(true, isPerfectSquare(16));
    assertEquals(false, isPerfectSquare(14));
    assertEquals(false, isPerfectSquare(1000));
    assertEquals(true, isPerfectSquare(100));
    assertEquals(false, isPerfectSquare(10000000));
    assertEquals(false, isPerfectSquare(2147483647));
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
      Long middle = (left + right) / 2;

      int guess = guess(middle.intValue());

      switch (guess) {
        case 1:
          left = middle + 1;
          break;

        case -1:
          right = middle - 1;
          break;

        default:
          return middle.intValue();
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

    Double value;
    try {

      value = Double.parseDouble(str);
    } catch (Exception e) {
      return 0;
    }

    if (value > Integer.MAX_VALUE) return Integer.MAX_VALUE;

    if (value < Integer.MIN_VALUE) return Integer.MIN_VALUE;

    return value.intValue();
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

    boolean isNegative = s.charAt(0) == '-' ? true : false;

    StringBuilder builder = new StringBuilder();
    if (isNegative) builder.append("-");

    for (int i = s.length() - 1; isNegative ? i >= 1 : i >= 0; i--) builder.append(s.charAt(i));

    int result = 0;
    try {
      result = Integer.valueOf(builder.toString());
    } catch (NumberFormatException e) {
      // TODO: handle exception
    }
    return result;
  }
}
