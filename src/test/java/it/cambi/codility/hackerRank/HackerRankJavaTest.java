/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class HackerRankJavaTest
{

    @Test
    public void tagContetExtractor()
    {
        /*
         * assertEquals("Nayeem loves counseling", tagContetExtractor("<h1>Nayeem loves counseling</h1>")); assertEquals("Sanjay has no watch",
         * tagContetExtractor("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>")); assertEquals("So wait for a while",
         * tagContetExtractor("<Amee>safat codes like a ninja</amee>"));
         */

        tagContetExtractor("<h1>Nayeem loves counseling</h1>");
        tagContetExtractor("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>");
        tagContetExtractor("<Amee>safat codes like a ninja</amee>");
        tagContetExtractor("<SA premium>Imtiaz has a secret crash</SA premium>");
        tagContetExtractor("<h1>had<h1>public</h1515></h1>");

    }

    private void tagContetExtractor(String s)
    {
        List<String> list = findContent(s);

        if (list.size() == 0)
            System.out.println("None");
        else
            for (String string : list)
            {
                System.out.println(string);
            }
    }

    /**
     * @param s
     */
    private List<String> findContent(String s)
    {
        if (s.isEmpty())
            return new ArrayList<String>();

        List<String> list = new ArrayList<String>();

        Pattern pattern = Pattern.compile("<(.+?)>", Pattern.DOTALL);
        Matcher m = pattern.matcher(s);

        int start = 0, end = 0;

        Stack<String> stack = new Stack<String>();

        while (m.find())
        {
            String match = m.group();

            if (match.indexOf("/") < 0)
            {
                stack.push(match);

                start = m.start();
                end = m.end();
                continue;
            }

            if (!stack.isEmpty() && match.indexOf("/") >= 0)
            {
                String peek = stack.pop();

                if (match.equals(peek.replace("<", "</")))
                {
                    String tag = s.substring(start, end).replace("<", "</");
                    int index = s.indexOf(tag);

                    String toPrint = s.substring(end, index);

                    if (!toPrint.isEmpty())
                        list.add(toPrint);

                }

                stack.clear();
            }
        }

        return list;
    }

    @Test
    public void usernameValidator() throws IOException
    {

        InputStream is = new FileInputStream("src/test/resources/regularExpression/username.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        InputStream is1 = new FileInputStream("src/test/resources/regularExpression/usernameValidation.txt");
        BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));

        String line = buf.readLine();
        String line1 = buf1.readLine();

        while (line != null && line1 != null)
        {

            assertEquals(line1.equals("Invalid") ? false : true,
                    line.matches(UsernameValidator.regularExpression));

            line = buf.readLine();

            line1 = buf1.readLine();
        }

        buf.close();
        buf1.close();

    }

    class UsernameValidator
    {
        public static final String regularExpression = "^[a-zA-Z][a-zA-Z_0-9]{7,29}$";
    }

    @Test
    public void getSmallestAndLargest()
    {
        assertEquals("ava\nwel", getSmallestAndLargest("welcometojava", 3));
    }

    private static String getSmallestAndLargest(String s, int k)
    {
        String smallest = s;
        String largest = "";

        int length = s.length();
        int i = 0;

        while (i + k <= length)
        {
            String tmp = s.substring(i, i + k);
            smallest = smallest.compareTo(tmp) > 0 ? tmp : smallest;
            largest = largest.compareTo(tmp) < 0 ? tmp : largest;

            i++;
        }

        return smallest + "\n" + largest;
    }

    @Test
    public void tokens()
    {
        assertEquals(true, Arrays.equals(new String[] { "He", "is", "a", "very", "very", "good", "boy", "isn", "t", "he" },
                tokens("He is a very very good boy, isn't he?")));

        assertEquals(true,
                Arrays.equals(new String[] { "YES", "leading", "spaces", "are", "valid", "problemsetters", "are", "evillllll" },
                        tokens("           YES      leading spaces        are valid,    problemsetters are         evillllll")));

        assertEquals(true, Arrays.equals(new String[] {},
                tokens("                        ")));
    }

    private String[] tokens(String s)
    {
        String trimmed = s.trim();

        if (trimmed.length() == 0)
            return new String[] {};

        String[] tokens = trimmed.split("[\\s*\\!\\,\\?\\.\\_\\'\\@]+");

        return tokens;
    }
}
