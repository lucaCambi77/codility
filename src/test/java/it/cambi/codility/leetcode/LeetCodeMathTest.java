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

    @Test
    public void isNumber()
    {

    }

    public boolean isNumber(String s)
    {

        return false;
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
