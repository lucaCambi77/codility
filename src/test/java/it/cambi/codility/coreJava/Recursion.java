/**
 * 
 */
package it.cambi.codility.coreJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Recursion
{

    @Test
    public void factorial()
    {

        assertEquals(120, factorial(new BigInteger("5")).intValue());
        assertEquals("6227020800", factorial(new BigInteger("13")).toString());
        assertEquals("265252859812191058636308480000000", factorial(new BigInteger("30")).toString());
        assertEquals("3628800", factorial(new BigInteger("10")).toString());

    }

    public BigInteger factorial(BigInteger n)
    {

        if (n.compareTo(new BigInteger("1")) == 0)
            return new BigInteger("1");

        String number = factorial(n.subtract(new BigInteger("1"))).toString();

        return n.multiply(new BigInteger(number));
    }

    @Test
    public void power()
    {

        double n = 2;
        int p = 5;

        assertEquals(32.0, power(n, p));

    }

    public double power(double n, int p)
    {

        if (p == 0)
            return 1.0;

        return n * power(n, p - 1);
    }

}
