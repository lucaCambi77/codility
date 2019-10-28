/**
 * 
 */
package it.cambi.codility.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class CapitalOne
{

    /**
     * FizzBuzz If a number can be divided by
     * 
     * - 3 print "Fizz"
     * 
     * - 5 print "Buzz"
     * 
     * - 5 and 3 print "FizzBuzz"
     */
    @Test
    public void fizzBuzz()
    {

        for (int i = 1; i <= 15; i++)
        {

            String output = "";

            if (i % 3 == 0)
                output += "Fizz";

            if (i % 5 == 0)
                output += "Buzz";

            if (output.isEmpty())
                System.out.println(i);
            else
                System.out.println(output);
        }
    }

    /**
     * Given a string that is a number, reverse the string.
     * 
     * For every number sum all odds, sum all even but if the even number multiplied by 2 is a 2 digit number then sum two digits.
     * 
     * Sum odds and even and if the reminder of 10 is zero print yes else no
     */
    @Test
    public void checkSum()
    {

        String input = new String("9795526789839145");
        int inputLength = input.length();

        int sumOdd = 0;
        int sumEven = 0;

        for (int i = inputLength - 1; i >= 0; i--)
        {

            char c = input.charAt(i);

            int num = new Integer(Character.toString(c));

            if ((num & 1) == 1)
            {

                sumOdd += num;
                continue;
            }

            int numTmp = num * 2;

            if (((numTmp > 9 && numTmp < 100)))
            {

                int sum = 0;
                while (numTmp > 0)
                {
                    sum = sum + numTmp % 10;
                    numTmp = numTmp / 10;
                }

                num = sum;
            }

            sumEven += num;
        }

        System.out.println((sumOdd + sumEven) % 10 == 0 ? "Yes" : "No");

    }

    /**
     * Remove from a string the script tag
     */
    @Test
    public void removeScript()
    {

        String luca = "Ciao <script>String I want to extract</script> luca";

        final Pattern pattern = Pattern.compile("<script>(.+?)</script>", Pattern.DOTALL);
        final Matcher m = pattern.matcher(luca);

        while (m.find())
        {

            System.out.println(luca.substring(0, m.start()) + luca.substring(m.end(), luca.length()));
        }
    }

}
