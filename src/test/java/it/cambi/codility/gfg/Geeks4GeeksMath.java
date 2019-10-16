/**
 * 
 */
package it.cambi.codility.gfg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geeks4GeeksMath
{

    @Test
    public void ishaanInternship()
    {
        assertEquals(27, ishaanInternship(30, 3));
        assertEquals(9, ishaanInternship(18, 3));
        assertEquals(4, ishaanInternship(5, 2));

    }

    public int ishaanInternship(int n, int k)
    {
        LinkedList<Integer> range = IntStream.rangeClosed(1, n)
                .boxed().collect(Collectors.toCollection(LinkedList::new));

        while (range.size() >= k)
        {
            range = IntStream.range(0, range.size())
                    .filter(p -> (p + 1) % k == 0)
                    .mapToObj(range::get)
                    .collect(Collectors.toCollection(LinkedList::new));

        }

        return range.get(0);

    }

    @Test
    public void floorSqrt()
    {
        assertEquals(2, floorSqrt(5));
    }

    long floorSqrt(long x)
    {
        double sqrt = Math.sqrt((double) x);
        return (long) Math.floor(sqrt);
    }
}
