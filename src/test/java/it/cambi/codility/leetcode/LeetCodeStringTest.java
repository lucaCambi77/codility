/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import it.cambi.codility.coreJava.StringTest;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeStringTest
{

    @Test
    public void wordPattern()
    {
        assertEquals(true, wordPattern("abba", "dog cat cat dog"));
        assertEquals(false, wordPattern("abba", "dog cat cat fish"));
        assertEquals(false, wordPattern("aaaa", "dog cat cat dog"));
        assertEquals(false, wordPattern("abba", "dog dog dog dog"));
        assertEquals(true, wordPattern("abc", "b c a"));
        assertEquals(true, wordPattern("ab", "happy hacking"));
        assertEquals(false, wordPattern("aaa", "aa aa aa aa"));

    }

    private boolean wordPattern(String pattern, String str)
    {

        char[] patternArray = pattern.toCharArray();

        String[] strArray = str.split(" ");

        if (patternArray.length != strArray.length)
            return false;

        Map<Character, String> charToString = new HashMap<>();
        Map<String, Character> stringToChar = new HashMap<>();

        for (int i = 0; i < patternArray.length; i++)
        {
            String word = charToString.get(patternArray[i]);

            if (word == null)
                if (stringToChar.get(strArray[i]) != null)
                    return false;
                else
                {
                    stringToChar.put(strArray[i], patternArray[i]);
                    charToString.put(patternArray[i], strArray[i]);
                    continue;
                }

            if (!strArray[i].equals(word))
                return false;
        }

        return true;
    }

    @Test
    public void defangIPaddr()
    {
        assertEquals("255[.]100[.]50[.]0", defangIPaddr("255.100.50.0"));
        assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));

    }

    private String defangIPaddr(String address)
    {
        return address.replaceAll("\\.", "[.]");
    }

    @Test
    public void addBinary()
    {
        assertEquals("100", addBinary("11", "1"));
        assertEquals("10101", addBinary("1010", "1011"));

    }

    private String addBinary(String a, String b)
    {
        // Initialize result
        String result = "";

        // Initialize digit sum
        int s = 0;

        // Traverse both strings starting
        // from last characters
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || s == 1)
        {

            // Comput sum of last
            // digits and carry
            s += ((i >= 0) ? a.charAt(i) - '0' : 0);
            s += ((j >= 0) ? b.charAt(j) - '0' : 0);

            // If current digit sum is
            // 1 or 3, add 1 to result
            result = (char) (s % 2 + '0') + result;

            // Compute carry
            s /= 2;

            // Move to next digits
            i--;
            j--;
        }

        return result;
    }

    @Test
    public void reverseString()
    {
        char[] array = new char[] { 'h', 'e', 'l', 'l', 'o' };
        reverseString(array);
        assertEquals(true, Arrays.equals(new char[] { 'o', 'l', 'l', 'e', 'h' }, array));

        array = new char[] { 'H', 'a', 'n', 'n', 'a', 'h' };
        reverseString(array);
        assertEquals(true, Arrays.equals(new char[] { 'h', 'a', 'n', 'n', 'a', 'H' }, array));

        array = new char[] { 'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c', 'a', 'n', 'a', 'l', ':', ' ',
                'P', 'a', 'n', 'a', 'm', 'a' };
        reverseString(array);
        assertEquals(true, Arrays.equals(new char[] { 'a', 'm', 'a', 'n', 'a', 'P', ' ', ':', 'l', 'a', 'n', 'a', 'c', ' ', 'a', ' ', ',', 'n', 'a',
                'l', 'p', ' ', 'a', ' ', ',', 'n', 'a', 'm', ' ', 'A' }, array));

    }

    private void reverseString(char[] s)
    {
        int length = s.length;

        int middle = (length & 1) == 1 ? length >>> 1 : (length >>> 1) - 1;
        for (int i = length - 1; i > middle; i--)
        {
            char left = s[length - 1 - i];
            char right = s[i];

            char tmp = right;
            s[i] = left;
            s[length - 1 - i] = tmp;
        }
    }

    @Test
    public void reverseVowels()
    {
        assertEquals("holle", reverseVowels("hello"));
        assertEquals("leotcede", reverseVowels("leetcode"));

    }

    private String reverseVowels(String s)
    {

        StringBuilder builder = new StringBuilder(s);

        int length = s.length();

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < length; i++)
        {
            char ch = builder.charAt(i);

            if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' ||
                    ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O' ||
                    ch == 'u' || ch == 'U')
            {

                stack.push(ch);
                builder.replace(i, i + 1, "~");
            }

        }

        for (int i = 0; i < length; i++)
        {
            char ch = builder.charAt(i);

            if (ch == '~')
                builder.replace(i, i + 1, Character.toString(stack.pop()));
        }

        return builder.toString();
    }

    @Test
    public void lengthOfLastWord()
    {
        assertEquals(5, lengthOfLastWord("Hello world"));
    }

    private int lengthOfLastWord(String s)
    {

        if (s == null)
            return 0;

        if (s.trim().isEmpty())
            return 0;

        String[] split = s.split(" ");
        int lenght = split.length;
        return split[lenght - 1].length();
    }

    @Test
    public void longestCommonPrefix()
    {
        assertEquals("fl", longestCommonPrefix(new String[] { "flower", "flow", "flight" }));
        assertEquals("", longestCommonPrefix(new String[] { "dog", "racecar", "car" }));
        assertEquals("", longestCommonPrefix(new String[] { "", "" }));
        assertEquals("c", longestCommonPrefix(new String[] { "c", "c" }));
        assertEquals("", longestCommonPrefix(new String[] { "caa", "", "a", "acb" }));
        assertEquals("a", longestCommonPrefix(new String[] { "acc", "aaa", "aaba" }));
    }

    public String longestCommonPrefix(String[] strs)
    {
        if (strs.length == 0)
            return "";

        if (strs.length == 1)
            return strs[0];

        String word = strs[0];

        String match = word;

        for (int i = 1; i < strs.length; i++)
        {
            String s = strs[i];

            if (s.startsWith(match))
                continue;

            while (!s.startsWith(word) && word.length() > 0)
                word = word.substring(0, word.length() - 1);

            if (word.isEmpty())
                return "";

            match = word;
            word = s;
        }

        return match;
    }

    @Test
    public void romanToInt()
    {
        assertEquals(3, romanToInt("III"));
        assertEquals(4, romanToInt("IV"));
        assertEquals(9, romanToInt("IX"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
        assertEquals(621, romanToInt("DCXXI"));

    }

    private int romanToInt(String s)
    {
        @SuppressWarnings("serial")
        Map<Character, Integer> romanToIntMap = new HashMap<Character, Integer>()
        {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);

            }
        };

        int sum = 0;

        for (int i = 0; i < s.length(); i++)
        {
            if (i == s.length() - 1)
            {
                sum += romanToIntMap.get(s.charAt(i));
                break;
            }

            char c = s.charAt(i);
            char c1 = s.charAt(i + 1);

            switch (c)
            {
                case 'I':

                    if (c1 == 'V' || c1 == 'X')
                        sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
                    else
                        sum += romanToIntMap.get(c);

                    break;

                case 'X':

                    if (c1 == 'L' || c1 == 'C')
                        sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
                    else
                        sum += romanToIntMap.get(c);

                    break;

                case 'C':
                    if (c1 == 'D' || c1 == 'M')
                        sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
                    else
                        sum += romanToIntMap.get(c);

                    break;

                default:
                    sum += romanToIntMap.get(c);

                    break;
            }
        }

        return sum;
    }

    @ParameterizedTest
    @ValueSource(strings = "abc")
    public void validString(String input)
    {
        assertEquals(true, validString("aabcbc", input));
        assertEquals(true, validString("abcabcababcc", input));
        assertEquals(false, validString("abccba", input));
        assertEquals(false, validString("cababc", input));

    }

    private boolean validString(String s, String valid)
    {

        StringBuilder builder = new StringBuilder(s);

        int index = builder.indexOf(valid);
        while (index >= 0 && !builder.toString().equals(valid))
        {
            builder.replace(index, index + 3, "");
            index = builder.indexOf(valid);

        }

        return builder.toString().equals(valid);
    }

    @Test
    public void zigZagConversion()
    {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
        assertEquals("AB", convert("AB", 1));
        assertEquals("ABC", convert("ABC", 3));

    }

    private String convert(String s, int numRows)
    {
        int length = s.length();

        int[][] strings = new int[numRows][1000];

        int rows = 0;
        int columns = 0;
        int count = 0;

        while (count < length)
        {
            strings[rows][columns] = s.charAt(count);

            if (rows == numRows - 1)
            {
                count++;
                columns++;
                rows--;

                while (rows > 0 && count < length)
                {
                    strings[rows][columns] = s.charAt(count);
                    rows--;
                    columns++;
                    count++;
                }

                rows = 0;
                continue;
            }

            count++;
            rows++;
        }

        StringBuilder out = new StringBuilder();

        for (int[] characters : strings)
        {
            for (int i = 0; i <= columns; i++)
            {

                if (0 != characters[i])
                    out.append(Character.toString((char) characters[i]));
            }
        }

        return out.toString();

    }

    @Test
    public void repeatedStringMatch()
    {
        assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
    }

    private int repeatedStringMatch(String A, String B)
    {
        String aCopy = new String(A);
        StringBuilder builder = new StringBuilder(A);
        int count = 1;

        while (builder.length() <= 10000)
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

    private boolean repeatedSubstringPattern(String s)
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

    private static boolean isPalindrome(String palindromeString, int count)
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

    private boolean isPalindromeOnlyAlphaNumeric(String s)
    {
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        return StringTest.isPalindrome(s.toLowerCase());
    }
}
