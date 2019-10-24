/**
 * 
 */
package it.cambi.codility.gfg;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geek4GeeksMatrix
{

    @Test
    public void countZeros()
    {
        int length = 14;
        int[][] A = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

        int count = 0;
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                if (A[i][j] == 0)
                    ++count;
            }
        }

        System.out.println(count);
    }

    @Test
    public void binarySearch()
    {
        int[] a = { 1, 2, 3, 4, 5 };

        int right = a.length;
        int k = 4;

        int bs = binarySearch(a, 0, right, k);

        System.out.println(bs);
    }

    private int binarySearch(int[] a, int left, int right, int k)
    {

        if (right < left)
            return -1;

        int mid = (left + right) / 2;

        if (a[mid] == k)
        {

            return mid;
        }
        else if (a[mid] > k)
        {
            return binarySearch(a, left, mid - 1, k);

        }
        else
        {

            return binarySearch(a, mid + 1, right, k);
        }

    }
}
