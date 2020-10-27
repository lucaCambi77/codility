/**
 *
 */
package it.cambi.codility.gfg;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luca
 *
 */
class Geek4GeeksHashTest
{

    @Test
    public void secondMostRepeatedString()
    {
        assertEquals("bbb", secondMostRepeatedString(new String[] { "bbb", "aaa", "ccc", "bbb", "aaa", "aaa" }));
        assertEquals("for", secondMostRepeatedString(new String[] { "geeks", "for", "geeks", "for", "geeks", "aaa" }));

    }

    private String secondMostRepeatedString(String[] array)
    {

        Map<String, Long> map = Arrays.stream(array)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> map1 = map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
                    throw new IllegalStateException();
                }, LinkedHashMap::new));

        return (String) map1.keySet().toArray()[1];
    }

    @Test
    public void winner()
    {
        String[] arr = new String[] { "john", "johnny", "jackie", "johnny", "john", "jackie", "jamie", "jamie", "john",
                "johnny", "jamie", "johnny", "john" };
        Map<String, Integer> namesMap = new HashMap<String, Integer>();
        String name = "zzzzzzzzzzzz";
        int count = 0;

        for (int i = 0; i < arr.length; i++)
        {
            int countTmp = namesMap.getOrDefault(arr[i], 1) + 1;

            namesMap.put(arr[i], countTmp);

            if (countTmp > count || (countTmp >= count && arr[i].compareTo(name) < 0))
            {

                name = arr[i];
                count = countTmp;
            }
        }

        System.out.print(name + " " + count);
    }

    @Test
    public void isIsogram()
    {

        String data = "machine";
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < data.toCharArray().length; i++)
        {
            if (set.add(data.charAt(i)))
                continue;

            return;
        }

    }

    @Test
    public void subArrayExists()
    {
        // int[] arr = { -21, -50, -4, 38, 47, -1, 40, -47, -17, 13, 47, 3, 42, 36, -25,
        // 2, 46, 25, 38 };
        int[] arr = { 4, 2, -3, 1, 6 };
        // int[] arr = { 4, 2, -3, -2, -1 };
        // int[] arr = { 36, 27, -35, 43, -15, 36, 42, -1, -29, 12, -23, 40, 9, 13, -24,
        // -10, -24, 22, -14, -39, 18, 17,
        // -21, 32, -20, 12, -27, 17, -15, -21, -48, -28, 8, 19 };
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < arr.length; i++)
        {
            list.add(arr[i]);
        }

        int length = arr.length;

        for (int i = 0; i < length; i++)
        {

            int step = length - i;
            int start = 0;

            while ((start + step) <= length)
            {
                int sum = list.subList(start, start + step).stream().reduce(0, (a, b) -> a + b);

                if (sum == 0)
                    System.out.println("Yes");

                start++;

            }
        }

        // Faster using prefix sum
        Set<Integer> prefixSumSet = new HashSet<Integer>();
        prefixSumSet.add(0);
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            sum += arr[i];

            if (prefixSumSet.contains(sum))
                System.out.println("Yes");

            prefixSumSet.add(sum);

        }

    }

}
