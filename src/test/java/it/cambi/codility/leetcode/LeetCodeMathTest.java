/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class LeetCodeMathTest
{
    long k;

    @Test
    public void isPerfectSquare()
    {
        assertEquals(true, isPerfectSquare(1));
        assertEquals(false, isPerfectSquare(2));
        assertEquals(true, isPerfectSquare(16));
        assertEquals(false, isPerfectSquare(14));
        assertEquals(false, isPerfectSquare(1000));
        assertEquals(true, isPerfectSquare(100));
        assertEquals(false, isPerfectSquare(10000000));
        assertEquals(false, isPerfectSquare(2147483647));

    }

    private boolean isPerfectSquare(int num)
    {
        if (num == 1)
            return true;

        long left = 0;
        long right = (long) num;

        while (left < right)
        {

            long mid = (left + right) / 2;

            if (mid * mid == num)
                return true;
            else if (num > mid * mid && num < (mid + 1) * (mid + 1))
                return false;
            else if (num < mid * mid)
                right = mid;
            else if (num > mid * mid)
                left = mid;
        }

        return false;
    }

    @Test
    public void guessNumber()
    {
        int n = 10;
        k = 6;
        assertEquals(k, guessNumber(n));

        n = 2126753390;
        k = 1702766719;
        assertEquals(k, guessNumber(n));
    }

    public int guessNumber(int n)
    {
        long left = 0;
        long right = n;

        while (left <= right)
        {
            Long middle = (left + right) / 2;

            int guess = guess(middle.intValue());

            switch (guess)
            {
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

    private int guess(int n)
    {
        if ((long) n > k)
            return -1;
        else if ((long) n < k)
            return 1;

        return 0;
    }

    @Test
    public void myAtoi()
    {
        assertEquals(-42, myAtoi("   -42"));
        assertEquals(4193, myAtoi("4193 with words"));
        assertEquals(0, myAtoi("words and 987"));
        assertEquals(Integer.MIN_VALUE, myAtoi("-91283472332"));
        assertEquals(3, myAtoi("3.14159"));
        assertEquals(1, myAtoi("+1"));
        assertEquals(-5, myAtoi("-5-"));

    }

    public int myAtoi(String str)
    {
        str = str.trim();

        int index = str.indexOf(" ");

        if (str.length() > 0)
        {
            if (index > -1)
                str = str.substring(0, index);

            return getValue(str);

        }
        else
        {
            return 0;
        }

    }

    private int getValue(String str)
    {
        for (int i = 1; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                str = str.substring(0, i);
                break;
            }
        }

        Double value;
        try
        {

            value = Double.parseDouble(str);
        }
        catch (Exception e)
        {
            return 0;
        }

        if (value > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (value < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return value.intValue();
    }

    @Test
    public void reverse()
    {
        assertEquals(-1, reverse(-1));
        assertEquals(321, reverse(123));
        assertEquals(-321, reverse(-123));
        assertEquals(21, reverse(120));
        assertEquals(0, reverse(1534236469));

    }

    public int reverse(int x)
    {

        String s = Integer.toString(x);

        boolean isNegative = s.charAt(0) == '-' ? true : false;

        StringBuilder builder = new StringBuilder();
        if (isNegative)
            builder.append("-");

        for (int i = s.length() - 1; isNegative ? i >= 1 : i >= 0; i--)
            builder.append(s.charAt(i));

        int result = 0;
        try
        {
            result = Integer.valueOf(builder.toString());
        }
        catch (NumberFormatException e)
        {
            // TODO: handle exception
        }
        return result;
    }
}
