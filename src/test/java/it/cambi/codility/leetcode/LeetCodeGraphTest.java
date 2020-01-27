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
public class LeetCodeGraphTest
{

    @Test
    public void findJudge()
    {
        assertEquals(2, findJudge(2, new int[][] { { 1, 2 } }));
        assertEquals(3, findJudge(3, new int[][] { { 1, 3 }, { 2, 3 } }));
        assertEquals(-1, findJudge(3, new int[][] { { 1, 3 }, { 2, 3 }, { 3, 1 } }));
        assertEquals(-1, findJudge(3, new int[][] { { 1, 2 }, { 2, 3 } }));
        assertEquals(3, findJudge(4, new int[][] { { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 4, 3 } }));
        assertEquals(-1, findJudge(11, new int[][] { { 1, 8 }, { 1, 3 }, { 2, 8 }, { 2, 3 }, { 4, 8 }, { 4, 3 }, { 5, 8 }, { 5, 3 }, { 6, 8 },
                { 6, 3 }, { 7, 8 }, { 7, 3 }, { 9, 8 }, { 9, 3 }, { 11, 8 }, { 11, 3 } }));
        assertEquals(1, findJudge(1, new int[][] {}));
        assertEquals(3, findJudge(4, new int[][] {
                { 1, 3 }, { 1, 4 }, { 2, 3 }, { 2, 4 }, { 4, 3 } }));

    }

    public int findJudge(int n, int[][] trust)
    {

        if (trust.length == 0)
            return n;

        int dp[][] = new int[n][2];

        for (int row[] : trust)
        {
            int per = row[0] - 1, trt = row[1] - 1;
            dp[per][0]++;
            dp[trt][1]++;
        }

        for (int i = 0; i < n; i++)
            if ((dp[i][0] == 0) && dp[i][1] == n - 1)
                return i + 1;

        return -1;
    }
}
