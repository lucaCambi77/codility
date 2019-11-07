/**
 * 
 */
package it.cambi.codility.coreJava;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class StringTest
{

    @Test
    public void isPalindrome()
    {
        assertTrue(isPalindrome("aaaa"));
        assertFalse(isPalindrome("aaaab"));
        assertTrue(isPalindrome("aa"));
        assertTrue(isPalindrome("a"));

    }

    /**
     * @return
     */
    public static boolean isPalindrome(String palindromeString)
    {
        boolean isPalindrome = true;

        int lengthOf = palindromeString.length();
        int middlePoint = (lengthOf & 1) == 1 ? lengthOf / 2 : (lengthOf + 1) / 2;

        for (int i = lengthOf - 1; i >= middlePoint; i--)
        {
            if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1))
            {
                isPalindrome = false;
                break;

            }

        }
        return isPalindrome;
    }
}
