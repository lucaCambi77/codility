/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.cambi.codility.util.AbstractTest;

/**
 * @author luca
 *
 */
public class HackerRankArraysTest extends AbstractTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams()
    {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    int energy;

    @Test
    public void acmTeam()
    {
        assertTrue(Arrays.equals(new int[] { 5, 2 }, acmTeam(new String[] { "10101", "11100", "11010", "00101" })));
    }

    private int[] acmTeam(String[] topic)
    {
        int maxTopic = 0;

        int[] topics = new int[6];

        for (int i = 0; i < topic.length; i++)
        {
            String attendee = topic[i];

            for (int j = i + 1; j < topic.length; j++)
            {
                String otherAttendee = topic[j];

                int currTopics = 0;
                for (int k = 0; k < otherAttendee.length(); k++)
                {
                    int c = otherAttendee.charAt(k) | attendee.charAt(k);
                    if (c == 49)
                        currTopics++;
                }

                topics[currTopics] = ++topics[currTopics];

                maxTopic = Math.max(maxTopic, currTopics);

            }
        }

        int[] result = new int[2];

        for (int i = topics.length - 1; i > 0; i--)
        {
            if (topics[i] != 0)
            {
                result[0] = i;
                result[1] = topics[i];
                break;
            }
        }

        return result;
    }

    @Test
    public void stepPerms()
    {

    }

    private int stepPerms(int n)
    {

        int memo[] = new int[n + 1];
        return stepPermsRec(0, n, memo);
    }

    public int stepPermsRec(int i, int n, int memo[])
    {
        if (i > n)
        {
            return 0;
        }
        if (i == n)
        {
            return 1;
        }
        if (memo[i] > 0)
        {
            return memo[i];
        }

        memo[i] = stepPermsRec(i + 1, n, memo) + stepPermsRec(i + 2, n, memo) + stepPermsRec(i + 3, n, memo);
        return memo[i];
    }

    @Test
    public void subArraySum()
    {
        assertEquals(9, subArraySum(new int[] { 1, -2, 4, -5, 1 }));
        assertEquals(953,
                subArraySum(new int[] { 463, 589, -321, 164, -613, 246, -869, -889, -712, -251, 969, -603, 49, 185, 439, 479, 255, -660, 848, 157,
                        644, 498, -722, 82, -275, -645, -268, -255, 573, 910, 303, 267, -162, 487, 103, -823, 400, 612, -61, -260, 732, 286, 505, -22,
                        37, 443, 27, 603, 341, -904, -87, -895, -753, 314, 257, 856, 832, -695, -387, 416, 354, 117, 273, -275, 811, -114, -962, -90,
                        868, 883, -330, 467, 233, 852, 232, -44, 831, -672, -883, -774, -830, 297, -897, -860, 143, 594, 186, -988, 928, 391, -812,
                        99, 302, -803, -422, 583, 817, 748, -619, 183 }));
    }

    private int subArraySum(int[] array)
    {
        if (null == array || (null != array && array.length == 0))
            return 0;

        if (array.length == 1)
            return array[0] < 0 ? 1 : 0;

        int length = array.length;
        int solution = 0;

        for (int i = 0; i < length; i++)
        {
            int value = array[i];

            if (value < 0)
                ++solution;

            int partSum = value;

            for (int j = i + 1; j < length; j++)
            {
                partSum += array[j];

                if (partSum < 0)
                    ++solution;
            }
        }

        return solution;
    }

    @Test
    public void twoDArray()
    {
        assertEquals(19,
                twoDArray(new int[][] { { 1, 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0 }, { 0, 0, 2, 4, 4, 0 }, { 0, 0, 0, 2, 0, 0 },
                        { 0, 0, 1, 2, 4, 0 } }));

        assertEquals(13,
                twoDArray(new int[][] { { 1, 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0 }, { 0, 9, 2, -4, -4, 0 },
                        { 0, 0, 0, -2, 0, 0 }, { 0, 0, -1, -2, -4, 0 } }));

        assertEquals(-6,
                twoDArray(new int[][] { { -1, -1, 0, -9, -2, -2
                }, { -2, -1, -6, -8, -2, -5 }, { -1, -1, -1, -2, -3, -4 }, { -1, -9, -2, -4, -4, -5 },
                        { -7, -3, -3, -2, -9, -9 }, { -1, -3, -1, -2, -4, -5 } }));
    }

    @Test
    public int twoDArray(int[][] array)
    {
        int k = 2;
        int length = array.length;
        int maxSum = Integer.MIN_VALUE;

        int i = 0;

        while (i + k < length)
        {
            int column = 0;

            int[] row = array[i];
            int[] row2 = array[i + k];

            while (column + k < length)
            {
                int col = column;
                int rowSum = IntStream.range(0, length).filter(v -> v >= col && v <= col + k).map(v -> new Integer(row[v])).sum();
                int rowSum2 = IntStream.range(0, length).filter(v -> v >= col && v <= col + k).map(v -> new Integer(row2[v])).sum();
                int middle = array[i + 1][col + 1];

                maxSum = Math.max(maxSum, rowSum2 + rowSum + middle);
                column++;
            }
            i++;
        }

        return maxSum;
    }

    @Test
    public void fibonacci()
    {

        assertEquals(0, fibonacci(0));
        assertEquals(1, fibonacci(1));
        assertEquals(2, fibonacci(3));
        assertEquals(8, fibonacci(6));

    }

    public static int fibonacci(int n)
    {

        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        int prev = 0;
        int next = 1;

        int i = 2;

        int sum = 0;
        while (i <= n)
        {
            sum = prev + next;

            prev = next;

            next = sum;

            ++i;
        }

        return sum;
    }

    @Test
    public void jumpingOnClouds()
    {
        assertEquals(92, jumpingOnClouds(new int[] { 0, 0, 1, 0, 0, 1, 1, 0 }, 2));
        assertEquals(80, jumpingOnClouds(new int[] { 1, 1, 1, 0, 1, 1, 0, 0, 0, 0 }, 3));
        assertEquals(97, jumpingOnClouds(new int[] { 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1 }, 19));

    }

    private int jumpingOnClouds(int[] c, int k)
    {
        int energy = 100;
        int length = c.length;

        int i = k;

        int cloud = c[0];
        while (i != length)
        {

            cloud = c[i];

            --energy;

            if (cloud == 1)
                energy -= 2;

            i += k;

            if (i > length)
                i = i - length;
            else if (i == length)
                cloud = c[0];
        }

        return cloud == 0 ? --energy : (energy -= 3);

    }

    @Test
    public void permutationEquation()
    {
        assertTrue(Arrays.equals(new int[] { 2, 3, 1 }, permutationEquation(new int[] { 2, 3, 1 })));
        assertTrue(Arrays.equals(new int[] { 1, 3, 5, 4, 2 }, permutationEquation(new int[] { 4, 3, 5, 1, 2 })));

    }

    private int[] permutationEquation(int[] p)
    {
        int[] sol = new int[p.length];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < p.length; i++)
        {
            map.put(p[i], i + 1);
        }

        for (int i = 0; i < sol.length; i++)
        {
            int value = map.get(i + 1);
            sol[i] = map.get(value);
        }

        return sol;
    }

    @Test
    public void circularArrayRotation()
    {
        assertTrue(Arrays.equals(new int[] { 5, 3 }, circularArrayRotation(new int[] { 3, 4, 5 }, 2, new int[] { 1, 2 })));
    }

    private int[] circularArrayRotation(int[] a, int k, int[] queries)
    {
        Stack<Integer> stack = new Stack<Integer>();

        for (int i : a)
        {
            stack.push(i);
        }

        for (int i = 0; i < k; i++)
        {
            int peek = stack.pop();

            stack.add(0, peek);
        }

        int numQueries = queries.length;

        int result[] = new int[numQueries];

        for (int i = 0; i < numQueries; i++)
        {
            result[i] = stack.get(queries[i]);
        }

        return result;
    }

    @Test
    public void largestRectangle()
    {

        assertEquals(9, largestRectangle(new int[] { 1, 2, 3, 4, 5 }));
        assertEquals(18, largestRectangle(new int[] { 1, 3, 5, 9, 11 }));
        assertEquals(12, largestRectangle(new int[] { 1, 2, 3, 3, 4, 5 }));
        assertEquals(1, largestRectangle(new int[] { 1 }));
        assertEquals(2,
                largestRectangle(new int[] { 1, 2 }));
        assertEquals(50, largestRectangle(new int[] { 11, 11, 10, 10, 10 }));
        assertEquals(26152,
                largestRectangle(new int[] { 8979, 4570, 6436, 5083, 7780, 3269, 5400, 7579, 2324, 2116 }));

    }

    private long largestRectangle(int[] h)
    {
        long maxAreaSoFar = 0L;

        for (int i = 0; i < h.length; i++)
        {
            int value = h[i];
            int count = search(h, i, i, value, 0, 0) + 1;
            maxAreaSoFar = Math.max(maxAreaSoFar, (long) count * value);
        }

        return maxAreaSoFar;
    }

    private int search(int h[], int l, int r, int key, int countLeft, int countRight)
    {

        while (--l >= 0)
        {
            if (h[l] >= key)
                search(h, l, h.length, h[l], ++countLeft, 0);
            else
                break;
        }

        while (++r < h.length)
        {
            if (h[r] >= key)
                search(h, -1, r, h[r], 0, ++countRight);
            else
                break;
        }

        return countLeft + countRight;
    }

    @Test
    public void maximumElement() throws IOException
    {
        InputStream is = new FileInputStream("src/test/resources/maximumElement/maximumElementInput.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        maximumElement(buf);

        buf.close();

        InputStream isOutput = new FileInputStream("src/test/resources/maximumElement/maximumElementOutput.txt");
        BufferedReader bufOut = new BufferedReader(new InputStreamReader(isOutput));

        String line = bufOut.readLine();
        String solution = "";

        String carriageReturn = getCarriageReturn();

        while (line != null)
        {
            solution += line + carriageReturn;
            line = bufOut.readLine();

        }

        bufOut.close();

        assertEquals(solution, outContent.toString());
    }

    private void maximumElement(BufferedReader buf) throws IOException
    {

        buf.readLine();
        String line = buf.readLine();

        // A stack to keep track of values
        Stack<Integer> stack = new Stack<Integer>();
        // A priority queue in descending order to keep of all values and get maximum
        PriorityQueue<Integer> tree = new PriorityQueue<Integer>(Collections.reverseOrder());

        while (line != null)
        {

            String[] split = line.split(" ");

            if (split.length > 1)
            {
                Integer add = Integer.valueOf(split[1]);
                stack.push(Integer.valueOf(split[1]));
                tree.add(add);
            }
            else
            {
                switch (split[0])
                {
                    case "2":

                        if (!stack.isEmpty())
                        {

                            Integer remove = stack.pop();
                            tree.remove(remove);
                        }

                        break;

                    default:

                        if (!stack.isEmpty())
                            System.out.println(tree.peek());

                        break;
                }
            }

            line = buf.readLine();
        }

    }

    @Test
    public void minimumAbsoluteDifference()
    {
        assertEquals(2, minimumAbsoluteDifference(new int[] { -2, 2, 4 }));
        assertEquals(3, minimumAbsoluteDifference(new int[] { 3, -7, 0 }));
        assertEquals(1, minimumAbsoluteDifference(new int[] { -59, -36, -13, 1, -53, -92, -2, -96, -54, 75 }));
        assertEquals(3, minimumAbsoluteDifference(new int[] { 1, -3, 71, 68, 17 }));

    }

    private int minimumAbsoluteDifference(int[] arr)
    {
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;
        int value = arr[0];

        for (int i = 1; i < arr.length; i++)
        {
            minDiff = Math.min(minDiff, Math.abs(value - arr[i]));
            value = arr[i];
        }

        return minDiff;
    }

    @Test
    public void maxSumArray() throws IOException
    {
        int[] array = new int[] { -2, 1, 3, -4, 5 };

        assertEquals(8, maxSumArray(array));

        array = new int[] { 3, 7, 4, 6, 5 };

        assertEquals(13, maxSumArray(array));

        array = new int[] { 2, 1, 5, 8, 4 };

        assertEquals(11, maxSumArray(array));

        array = new int[] { 3, 5, -7, 8, 10 };

        assertEquals(15, maxSumArray(array));

        InputStream is = new FileInputStream("src/test/resources/maxSubsetSum/maxSubsetSum1.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        array = new int[new Integer(buf.readLine())];

        String line = buf.readLine();

        String[] split = line.split(" ");

        int i = 0;
        for (String string : split)
        {
            array[i] = new Integer(string);
            i++;
        }

        buf.close();

        assertEquals(151598486, maxSumArray(array));

    }

    private int maxSumArray(int[] arr)
    {

        int inc = arr[0];
        int exc = 0;
        int exc_new = 0;

        for (int i = 1; i < arr.length; i++)
        {
            exc_new = Math.max(exc, inc);
            inc = exc + arr[i];
            exc = exc_new;
        }

        return Math.max(exc, inc);
    }

    @Test
    public void twoStacks() throws IOException
    {
        boolean isFile = true;

        if (isFile)
        {
            InputStream is = new FileInputStream("src/test/resources/gameOfTwoStacks/gameOfTwoStacksResult1.txt");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            LinkedList<Integer> list = new LinkedList<Integer>();

            String line = buf.readLine();

            while (line != null)
            {
                list.add(Integer.valueOf(line));
                line = buf.readLine();
            }

            buf.close();

            InputStream is1 = new FileInputStream("src/test/resources/gameOfTwoStacks/gameOfTwoStacks1.txt");
            BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));

            String line1 = buf1.readLine();
            line1 = buf1.readLine();

            int count = 0;
            int index = 0;
            while (line1 != null)
            {
                System.out.println(line1 + " - " + (index + 1));
                int x = Integer.valueOf(line1.split("\\s")[2]);

                LinkedList<int[]> listInput = new LinkedList<int[]>();

                while (count < 2)
                {
                    line1 = buf1.readLine();

                    listInput.add(Arrays.stream(line1.split("\\s")).map(s -> Integer.valueOf(s))
                            .collect(Collectors.toCollection(LinkedList::new)).stream().mapToInt(i -> i).toArray());

                    count++;
                }

                assertEquals(list.get(index), twoStacks(x, listInput.get(0), listInput.get(1)));

                count = 0;
                index++;
                line1 = buf1.readLine();

            }

            buf1.close();
        }

    }

    private int twoStacks(int x, int[] a, int[] b)
    {

        int ai = 0;
        int bi = 0;
        int count = 0;
        int sum = 0;
        // move bi to the position where if only take elements from B, last element it
        // can take
        while (bi < b.length && sum + b[bi] <= x)
        {
            sum += b[bi];
            bi++;
        }
        bi--; // loop exits only when bi reaches end or sum > x; in both case bi should
              // decrease
        count = bi + 1;
        while (ai < a.length && bi < b.length)
        {
            sum += a[ai];
            if (sum > x)
            {
                while (bi >= 0)
                {
                    sum -= b[bi];
                    bi--;
                    if (sum <= x)
                        break;
                }
                // if even no elements taken from B, but still sum greater than x, then a[ai]
                // should not be chosen
                // and loop terminates
                if (sum > x && bi < 0)
                {
                    ai--;
                    break;
                }
            }
            count = Math.max(ai + bi + 2, count);
            ai++;
        }

        return count;
    }

    @Test
    public void equalStacks()
    {
        assertEquals(5, equalStacks(new int[] { 3, 2, 1, 1, 1 }, new int[] { 4, 3, 2 }, new int[] { 1, 1, 4, 1 }));
        assertEquals(0, equalStacks(new int[] { 1, 1, 1, 1, 2 }, new int[] { 3, 7 }, new int[] { 1, 3, 1 }));

    }

    public int equalStacks(int[] h1, int[] h2, int[] h3)
    {
        int[] comul1 = new int[h1.length];
        int[] comul2 = new int[h2.length];
        int[] comul3 = new int[h3.length];

        int start = h1.length - 1;
        comul1[0] = h1[start];

        setArray(h1, comul1, start);

        start = h2.length - 1;
        comul2[0] = h2[start];

        setArray(h2, comul2, start);

        start = h3.length - 1;
        comul3[0] = h3[start];

        setArray(h3, comul3, start);

        for (int i = comul3.length - 1; i >= 0; i--)
        {
            int int3 = comul3[i];
            for (int j = comul2.length - 1; j >= 0; j--)
            {
                if (int3 == comul2[j])
                    for (int k = comul1.length - 1; k >= 0; k--)
                    {
                        if (int3 == comul1[k])
                            return int3;
                    }

            }
        }

        return 0;
    }

    /**
     * @param h1
     * @param comul1
     * @param start
     */
    private void setArray(int[] h1, int[] comul1, int start)
    {
        for (int i = start; i > 0; i--)
        {
            comul1[start - i + 1] = comul1[start - i] + h1[i - 1];
        }
    }

    @SuppressWarnings("serial")
    @Test
    public void freqQuery() throws IOException
    {

        assertEquals(Arrays.asList(new Integer[] { 0, 1, 1 }), freqQuery(new ArrayList<int[]>()
        {
            {
                add(new int[] { 1, 3 });
                add(new int[] { 2, 3 });
                add(new int[] { 3, 2 });
                add(new int[] { 1, 4 });
                add(new int[] { 1, 5 });
                add(new int[] { 1, 5 });
                add(new int[] { 1, 4 });
                add(new int[] { 3, 2 });
                add(new int[] { 2, 4 });
                add(new int[] { 3, 2 });

            }
        }));

        assertEquals(Arrays.asList(new Integer[] { 0, 1 }), freqQuery(new ArrayList<int[]>()
        {
            {
                add(new int[] { 3, 4 });
                add(new int[] { 2, 1003 });
                add(new int[] { 1, 16 });
                add(new int[] { 3, 1 });

            }
        }));

        assertEquals(new ArrayList<Integer>()
        {
            {
                InputStream is = new FileInputStream("src/test/resources/frequencies/frequenciesOutput.txt");
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));

                String line = buf.readLine();

                while (line != null)
                {

                    add(Integer.valueOf(line));
                    line = buf.readLine();
                }

                buf.close();
            }
        }, freqQuery(new ArrayList<int[]>()
        {
            {

                InputStream is = new FileInputStream("src/test/resources/frequencies/frequencies.txt");
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));

                String line = buf.readLine();

                while (line != null)
                {

                    String[] split = line.split(" ");

                    int[] list = new int[] { Integer.valueOf(split[0]), Integer.valueOf(split[1]) };
                    add(list);
                    line = buf.readLine();
                }

                buf.close();
            }
        }));
    }

    public List<Integer> freqQuery(List<int[]> queries)
    {

        LinkedList<Integer> out = new LinkedList<Integer>();

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int[] list : queries)
        {
            int value = list[1];
            int query = list[0];
            if (query == 1)
            {
                int freq = map.getOrDefault(value, 0);

                map.put(value, freq + 1);

                freqMap.put(freq + 1, freqMap.getOrDefault(freq + 1, 0) + 1);
                freqMap.put(freq, freqMap.getOrDefault(freq, 0) - 1);

            }
            else if (query == 2)
            {

                Integer mapGet = map.get(value);

                if (mapGet != null)
                {

                    freqMap.put(mapGet, freqMap.getOrDefault(mapGet, 0) - 1);

                    int freq = Math.max(0, mapGet - 1);

                    freqMap.put(freq, freqMap.getOrDefault(freq, 0) + 1);

                    map.put(value, freq);
                }
            }
            else
            {

                if (null == freqMap.get(value) || freqMap.get(value) == 0)
                    out.add(0);
                else
                    out.add(1);
            }
        }

        return out;
    }

    @Test
    public void divisibleSumPairs()
    {
        assertEquals(3, divisibleSumPairs(6, 5, new int[] { 1, 2, 3, 4, 5, 6 }));
        assertEquals(5, divisibleSumPairs(6, 3, new int[] { 1, 3, 2, 6, 1, 2 }));

    }

    private int divisibleSumPairs(int n, int k, int[] ar)
    {

        int count = 0;

        for (int i = 0; i < n; i++)
        {

            for (int j = i + 1; j < n; j++)
            {

                if ((ar[i] + ar[j]) % k == 0)
                    ++count;
            }
        }

        return count;
    }

    @Test
    public void lonelyinteger()
    {
        assertEquals(4, lonelyinteger(new int[] { 1, 2, 3, 4, 3, 2, 1 }));
        assertEquals(1, lonelyinteger(new int[] { 1 }));
        assertEquals(2, lonelyinteger(new int[] { 1, 1, 2 }));
        assertEquals(2, lonelyinteger(new int[] { 0, 0, 1, 2, 1 }));

    }

    private int lonelyinteger(int[] a)
    {

        Map<Integer, Long> map = IntStream.of(a).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return map.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().orElse(null).getKey();

    }

    @Test
    public void alternate()
    {
        assertEquals(5, alternate("beabeefeab"));
        assertEquals(4, alternate("abaacdabd"));
        assertEquals(8, alternate("asdcbsdcagfsdbgdfanfghbsfdab"));
    }

    private int alternate(String s)
    {

        int result = 0;

        List<Character> distinctCharacters = s.chars().distinct().mapToObj(c -> (char) c).collect(Collectors.toList());

        for (int i = 0; i < distinctCharacters.size(); i++)
        {

            for (int j = i + 1; j < distinctCharacters.size(); j++)
            {

                String replace = s;
                char first = distinctCharacters.get(i);
                char second = distinctCharacters.get(j);

                String filteredListToString = distinctCharacters.stream().filter(l -> l != first)
                        .filter(l -> l != second).map(String::valueOf).collect(Collectors.joining());

                replace = s.replaceAll("[" + filteredListToString + "]", "");

                if (checkConsecutive(replace))
                    result = Math.max(result, replace.length());
            }
        }

        return result;

    }

    private boolean checkConsecutive(String s)
    {

        int length = s.length();

        char charToCompare = s.charAt(0);

        for (int j = 0; j < length; j += 2)
        {

            if (charToCompare != s.charAt(j))
                return false;

        }

        charToCompare = s.charAt(1);

        for (int j = 1; j < length; j += 2)
        {

            if (charToCompare != s.charAt(j))
                return false;

        }

        return true;
    }

    @Test
    public void bonAppetit()
    {

        assertEquals(5, bonAppetit(Arrays.asList(new Integer[] { 3, 10, 2, 9 }), 1, 12));
        assertEquals(0, bonAppetit(Arrays.asList(new Integer[] { 3, 10, 2, 9 }), 1, 7));

    }

    private int bonAppetit(List<Integer> bill, int k, int b)
    {

        int sum = bill.stream().mapToInt(Integer::intValue).sum();

        int actual = sum - bill.get(k);

        if (actual / 2 == b)
            System.out.println("Bon Appetit");

        return b - (actual / 2);
    }

    @Test
    public void migratoryBirds()
    {
        assertEquals(4, migratoryBirds(Arrays.asList(new Integer[] { 1, 4, 4, 4, 5, 3 })));
        assertEquals(3, migratoryBirds(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 4, 3, 2, 1, 3, 4 })));

    }

    private int migratoryBirds(List<Integer> arr)
    {

        int maxFreq = 0;
        int birdOfMaxFreq = 0;

        Map<Integer, Integer> birdToFreqMap = new HashMap<Integer, Integer>();

        for (Integer integer : arr)
        {
            birdToFreqMap.put(integer, birdToFreqMap.getOrDefault(integer, 0) + 1);

            int freq = birdToFreqMap.get(integer);
            if (freq > maxFreq)
            {
                maxFreq = freq;
                birdOfMaxFreq = integer;
            }
            else if (freq == maxFreq)
            {
                birdOfMaxFreq = Math.min(birdOfMaxFreq, integer);
            }
        }

        return birdOfMaxFreq;
    }

    @Test
    public void sherlockAndAnagrams()
    {
        assertEquals(4, sherlockAndAnagrams("abba"));
        assertEquals(3, sherlockAndAnagrams("ifailuhkqq"));
        assertEquals(10, sherlockAndAnagrams("kkkk"));
    }

    private int sherlockAndAnagrams(String s)
    {
        // go through a string and add every value to a hashmap
        HashMap<String, Integer> map = new HashMap<>();

        // total of anagrams
        int total = 0;

        // for each key, add one to value
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = i + 1; j <= s.length(); j++)
            {
                // get substring and sort it!
                String sub = s.substring(i, j);

                // sorting the string
                char tempArray[] = sub.toCharArray();
                Arrays.sort(tempArray);
                sub = new String(tempArray);

                if (map.containsKey(sub))
                {
                    // adds one to last value
                    int oldValue = map.get(sub);
                    // total++ WRONG
                    // backtracking of previous equals values or combinations of elements
                    total += oldValue; // RIGHT
                    map.put(sub, ++oldValue);
                }
                else
                {
                    // add to map if not seen
                    map.put(sub, 1);
                }
            }
        }
        return total;
    }

    @SuppressWarnings("serial")
    @Test
    public void diagonalDifference()
    {
        List<List<Integer>> arr = new ArrayList<List<Integer>>()
        {
            {
                add(new ArrayList<Integer>()
                {
                    {
                        add(11);
                        add(2);
                        add(4);

                    }
                });

                add(new ArrayList<Integer>()
                {
                    {
                        add(4);
                        add(5);
                        add(6);

                    }
                });

                add(new ArrayList<Integer>()
                {
                    {
                        add(10);
                        add(8);
                        add(-12);

                    }
                });
            }
        };

        assertEquals(15, diagonalDifference(arr));
    }

    /**
     * @param arr
     */
    private int diagonalDifference(List<List<Integer>> arr)
    {
        double leftDiag = 0;
        double righttDiag = 0;

        int j = arr.size() - 1;

        for (int i = 0; i < arr.size(); i++)
        {

            List<Integer> innList = arr.get(i);

            righttDiag += innList.get(j);
            leftDiag += innList.get(arr.size() - 1 - j);

            j--;
        }

        return (int) Math.abs(leftDiag - righttDiag);
    }

    @Test
    public void aVeryBigSum()
    {

        long[] ar = new long[] { 1000000001, 1000000002, 1000000003, 1000000004, 1000000005 };

        assertEquals(5000000015L, LongStream.of(ar).sum());
    }

    @SuppressWarnings("serial")
    @Test
    public void compareTriplets()
    {

        List<Integer> list = compareTriplets(new ArrayList<Integer>()
        {
            {
                add(17);
                add(28);
                add(30);

            }
        }, new ArrayList<Integer>()
        {
            {
                add(99);
                add(16);
                add(8);
            }
        });

        Arrays.equals(new int[] { 2, 1 }, list.stream().mapToInt(Integer::intValue).toArray());

        List<Integer> list1 = compareTriplets(new ArrayList<Integer>()
        {
            {
                add(5);
                add(6);
                add(7);

            }
        }, new ArrayList<Integer>()
        {
            {
                add(3);
                add(6);
                add(10);
            }
        });

        Arrays.equals(new int[] { 1, 1 }, list1.stream().mapToInt(Integer::intValue).toArray());
    }

    private List<Integer> compareTriplets(List<Integer> a, List<Integer> b)
    {

        int countAlice = 0;
        int countBob = 0;

        for (int i = 0; i < 3; i++)
        {

            if (a.get(i) > b.get(i))
                countAlice++;
            else if (a.get(i) < b.get(i))
                countBob++;

        }

        List<Integer> toReturn = new LinkedList<Integer>();
        toReturn.add(countAlice);
        toReturn.add(countBob);

        return toReturn;
    }

    @Test
    public void arrayManipulation() throws IOException
    {

        /*
         * int n = 30000;
         * 
         * int[][] queries = new int[n][3];
         * 
         * InputStream is = new FileInputStream("src/test/resources/arrayManip.txt"); BufferedReader buf = new BufferedReader(new
         * InputStreamReader(is)); String line = buf.readLine();
         * 
         * int x = 0; while (line != null) { String[] scoresString = line.split(" "); for (int i = 0; i < scoresString.length; i++) { queries[x][0] =
         * Integer.parseInt(scoresString[0]); queries[x][1] = Integer.parseInt(scoresString[1]); queries[x][2] = Integer.parseInt(scoresString[2]);
         * 
         * } x++; line = buf.readLine(); }
         * 
         * buf.close();
         */
        int n = 5;

        int[][] queries = new int[][] { { 1, 2, 100 }, { 2, 5, 100 }, { 3, 4, 100 } };

        long[] arr = new long[n + 1];

        for (int i = 0; i < queries.length; i++)
        {
            long summond = (long) queries[i][2];

            arr[queries[i][0]] = arr[queries[i][0]] + summond;

            if ((queries[i][1] + 1) <= n)
                arr[queries[i][1] + 1] -= summond;
        }

        long k = 0, max = 0;

        for (int i = 1; i <= n; i++)
        {
            k = k + arr[i];
            if (max < k)
                max = k;

        }

        System.out.println(max);
    }

    @Test
    public void equalizeTheArray()
    {

        int[] arr = new int[] { 1, 2, 3, 1, 2, 3, 3, 3 };

        int arrLength = arr.length;
        Arrays.sort(arr);
        int count = 1;
        int maxFreq = 0;
        int itemToDelete = 0;

        for (int i = 1; i < arrLength; i++)
        {

            if (arr[i] == arr[i - 1])
            {
                count++;
                maxFreq = Math.max(count, maxFreq);
                continue;
            }
            maxFreq = Math.max(count, maxFreq);
            count = 1;
        }

        itemToDelete = arrLength - maxFreq;

        /*
         * int[] freq = new int[arr.length];
         * 
         * for (int i = 0; i < arrLength; i++) { freq[arr[i]]++; maxFreq = Math.max(maxFreq, freq[arr[i]]); }
         * 
         * itemToDelete = arrLength - maxFreq;
         */

        System.out.println("Max freq is " + maxFreq);
        System.out.println("Item to be deleted " + itemToDelete);
    }

    @Test
    public void minimunTwoSwaps()
    {

        int[] arr = new int[] { 1, 3, 5, 2, 4, 6, 7 };
        /*
         * int[] arr = new int[] { 8, 45, 35, 84, 79, 12, 74, 92, 81, 82, 61, 32, 36, 1, 65, 44, 89, 40, 28, 20, 97, 90, 22, 87, 48, 26, 56, 18, 49,
         * 71, 23, 34, 59, 54, 14, 16, 19, 76, 83, 95, 31, 30, 69, 7, 9, 60, 66, 25, 52, 5, 37, 27, 63, 80, 24, 42, 3, 50, 6, 11, 64, 10, 96, 47, 38,
         * 57, 2, 88, 100, 4, 78, 85, 21, 29, 75, 94, 43, 77, 33, 86, 98, 68, 73, 72, 13, 91, 70, 41, 17, 15, 67, 93, 62, 39, 53, 51, 55, 58, 99, 46
         * };
         */
        /*
         * int count = 0;
         * 
         * for (int i = 0; i < arr.length; i++) {
         * 
         * if (arr[i] != i + 1) count++; }
         */

        int swap = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (i + 1 != arr[i])
            {
                int t = i;
                while (arr[t] != i + 1)
                {
                    t++;
                }
                int temp = arr[t];
                arr[t] = arr[i];
                arr[i] = temp;
                swap++;
            }
        }

        System.out.println("Min two swap required" + swap);
    }
}
