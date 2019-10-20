/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.cambi.codility.coreJava.StringTest;

/**
 * @author luca
 *
 */
public class LeetCodeStringTest
{

    @Test
    public void repeatedStringMatch()
    {
        assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
    }

    public int repeatedStringMatch(String A, String B)
    {
        String aCopy = new String(A);
        StringBuilder builder = new StringBuilder(A);
        int count = 1;

        while (builder.length() < 10000)
        {
            if (builder.indexOf(B) >= 0)
                return count;

            builder.append(aCopy);
            count++;
        }
        return -1;
    }

    @Test
    public void repeatedSubstringPattern()
    {
        assertEquals(true, repeatedSubstringPattern("abcabcabcabc"));
        assertEquals(true, repeatedSubstringPattern("ababab"));
        assertEquals(true, repeatedSubstringPattern("abab"));
        assertEquals(false, repeatedSubstringPattern("abc"));
        assertEquals(false, repeatedSubstringPattern("abcabcabcabcd"));

    }

    public boolean repeatedSubstringPattern(String s)
    {
        int length = s.length();

        int div = 2;
        int middle = (length / div) + 1;

        while (--middle > 0)
        {

            if (length % middle == 0)
            {
                boolean checkAllChuncks = true;

                int section = middle;
                int start = 0;

                String subString = s.substring(start, section);

                while (section <= length)
                {

                    if (!subString.equals(s.substring(start, section)))
                    {
                        checkAllChuncks = false;
                        break;
                    }

                    section += middle;
                    start += middle;
                }

                if (checkAllChuncks)
                    return true;
            }
        }

        return false;
    }

    /**
     * Can arrange palindrome removing one character
     */
    @Test
    public void canArrangePalindrome()
    {
        assertEquals(true, canArrangePalindrome("samanaplanacanalpanama"));
        assertEquals(false, canArrangePalindrome("abcc"));
        assertEquals(true, canArrangePalindrome("abcca"));
        assertEquals(true, canArrangePalindrome("abca"));
        assertEquals(true, canArrangePalindrome("acbcca"));
        assertEquals(true, canArrangePalindrome("aba"));
        assertEquals(false, canArrangePalindrome("abc"));

    }

    private boolean canArrangePalindrome(String palindromeString)
    {
        return isPalindrome(palindromeString, 0);
    }

    public static boolean isPalindrome(String palindromeString, int count)
    {
        boolean isPalindrome = true;

        int lengthOf = palindromeString.length();
        int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

        for (int i = lengthOf - 1; i >= middlePoint; i--)
        {
            if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1))
            {

                if (count == 1)
                    return false;

                String s1 = palindromeString.substring(0, i) + palindromeString.substring(i + 1);
                String s2 = palindromeString.substring(0, lengthOf - i - 1) + palindromeString.substring(lengthOf - i - 1 + 1);

                boolean isS1 = isPalindrome(s1, 1);
                boolean isS2 = isPalindrome(s2, 1);

                return isS1 || isS2;
            }

        }
        return isPalindrome;
    }

    @Test
    public void isPalindromeOnlyAlphaNumeric()
    {
        assertEquals(true, isPalindromeOnlyAlphaNumeric("A man, a plan, a canal: Panama"));
        assertEquals(false, isPalindromeOnlyAlphaNumeric("race a car"));
        assertEquals(false, isPalindromeOnlyAlphaNumeric("0P"));

    }

    public boolean isPalindromeOnlyAlphaNumeric(String s)
    {
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        return StringTest.isPalindrome(s.toLowerCase());
    }
}
