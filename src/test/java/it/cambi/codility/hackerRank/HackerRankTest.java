package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;;

public class HackerRankTest
{

    public static String[] ones = { "o' clock", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen",
            "nineteen" };

    public static String[] tens = { "", // 0
            "", // 10 min
            "twenty", // 20 min
            "half", // 30 min
            "forty", // 40 min
    };

    private PrintStream out;

    @BeforeEach
    public void setUpStreams()
    {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @Test
    public void simpleTextEditor() throws IOException
    {
        simpleTextEditor(new String[] { "1 abc", "3 3", "2 3", "1 xy", "3 2", "4", "4", "3 1" });

        InOrder orderVerifier = Mockito.inOrder(out);
        Character[] sol = new Character[] { 'c', 'y', 'a' };

        for (char string : sol)
            orderVerifier.verify(out, atLeastOnce()).println(string);
        out.flush();

        simpleTextEditor(new String[] { "1 lchbfcjtfpsmjrqsdgci", "3 19", "1 cpcvixlm", "1 apdjgjydvpbpvyiy", "2 29", "4", "4", "3 9", "4", "4" });

        Character[] sol1 = new Character[] { 'c', 'f' };

        for (char string : sol1)
            orderVerifier.verify(out, atLeastOnce()).println(string);
        out.flush();

        InputStream is = new FileInputStream("src/test/resources/textEditor/simpleTextEditor1.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        List<String> list = new ArrayList<String>();
        buf.readLine();
        String line = buf.readLine();

        while (line != null)
        {
            list.add(line);
            line = buf.readLine();
        }

        buf.close();
        simpleTextEditor(list.stream().toArray(String[]::new));


        /**
         * this is not working, sequence of chars seems that is not respected
         */
        /*
         * LinkedList<Character> solution = getSolution();
         * 
         * for (char string : solution) orderVerifier.verify(out, atLeastOnce()).println(string);
         */
    }

    public void simpleTextEditor(String[] array)
    {
        Stack<String> stack = new Stack<String>();
        StringBuilder currentWord = new StringBuilder();

        for (String string : array)
        {
            String[] split = string.split(" ");

            if (split.length > 1)
            {
                switch (split[0])
                {
                    case "1":

                        currentWord.append(split[1]);
                        stack.push(currentWord.toString());

                        break;

                    case "2":
                        currentWord = new StringBuilder(currentWord.substring(0, currentWord.length() - new Integer(split[1])));
                        stack.push(currentWord.toString());

                        break;

                    default:
                        int position = new Integer(split[1]) - 1;
                        System.out.println(currentWord.charAt(position));

                        break;
                }
            }
            else
            {

                stack.pop();

                if (stack.size() > 0)
                    currentWord = new StringBuilder(stack.peek());
                else
                    currentWord = new StringBuilder();
            }

        }
    }

    @Test
    public void counterGame()
    {

        assertEquals("Louise", counterGame(132));
        assertEquals("Richard", counterGame(1));
        assertEquals("Richard", counterGame(6));

    }

    public String counterGame(long n)
    {
        if (n == 1)
            return "Richard";

        double[] power = new double[65];

        for (int i = 1; i < 65; i++)
        {
            power[i] = Math.pow(2.0, (double) i);
        }

        long counter = n;

        boolean isPowerOfTwo = true;
        int count = 0;

        while (counter > 1)
        {
            if (((counter & (counter - 1)) == 0))
            {
                if (isPowerOfTwo)
                    counter = counter / 2;
                else
                    counter = n - counter;

                n = counter;
                isPowerOfTwo = true;
                count++;
            }
            else
            {
                isPowerOfTwo = false;

                int i = 0;

                while (Double.compare((double) counter, power[i]) > 0)
                    i++;

                counter = (long) power[i - 1];
            }

        }

        return (count & 1) != 0 ? "Louise" : "Richard";

    }

    @Test
    public void minimumNumber()
    {

        assertEquals(3, minimumNumber("Ab1"));
        assertEquals(1, minimumNumber("#HackerRank"));
        assertEquals(2, minimumNumber("LY!Z"));
        assertEquals(1, minimumNumber("AUzs-nV"));
    }

    private int minimumNumber(String password)
    {

        int length = password.length();

        int requiredDigits = 6;

        int miss = matchRegEx(password, "[0-9]+") + matchRegEx(password, "[!@#$%^&*\\(\\)\\-\\+]+")
                + matchRegEx(password, "[A-Z]+") + matchRegEx(password, "[a-z]+");

        int lengthDiff = requiredDigits - length;

        return lengthDiff > 0 ? Math.max(miss, lengthDiff) : miss;
    }

    private int matchRegEx(String input, String regEx)
    {

        if (Pattern.compile(regEx).matcher(input).find())
            return 0;

        return 1;
    }

    @Test
    public void camelcase()
    {
        assertEquals(3, camelcase("oneTwoThree"));
        assertEquals(5, camelcase("saveChangesInTheEditor"));

    }

    private int camelcase(String s)
    {

        String regEx = "(\\p{Lu})";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(s);
        int count = 1;

        while (matcher.find())
            count++;

        return count;
    }

    @Test
    public void pageCount()
    {

        assertEquals(1, pageCount(6, 2));
        assertEquals(0, pageCount(5, 4));
        assertEquals(1, pageCount(6, 5));
        assertEquals(0, pageCount(5, 5));
        assertEquals(0, pageCount(5, 1));

    }

    private int pageCount(int n, int p)
    {

        // If pages is even number, add 1 to avoid edge cases
        int n1 = (n & 1) == 0 ? n + 1 : n;

        int fromStart = p / 2;
        int fromEnd = (n1 - p) / 2;

        return Math.min(fromStart, fromEnd);

    }

    @Test
    public void birthday()
    {

        assertEquals(2, birthday(Arrays.asList(new Integer[] { 1, 2, 1, 3, 2 }), 3, 2));

        assertEquals(0, birthday(Arrays.asList(new Integer[] { 1, 1, 1, 1, 1, 1 }), 3, 2));

        assertEquals(1, birthday(Arrays.asList(new Integer[] { 4 }), 4, 1));
    }

    private int birthday(List<Integer> s, int d, int m)
    {

        int count = 0;

        for (int i = 0; i < s.size(); i++)
        {

            if (i + m > s.size())
                break;

            int sum = s.subList(i, i + m).stream().mapToInt(Integer::intValue).sum();

            if (sum == d)
                count++;
        }

        return count;
    }

    @Test
    public void breakingRecords()
    {

        assertEquals(true,
                Arrays.equals(new int[] { 2, 4 }, breakingRecords(new int[] { 10, 5, 20, 20, 4, 5, 2, 25, 1 })));
    }

    private int[] breakingRecords(int[] scores)
    {

        int min = scores[0];
        int max = scores[0];
        int countMin = 0;
        int countMax = 0;

        for (int i = 1; i < scores.length; i++)
        {
            if (scores[i] < min)
            {
                countMin++;
                min = scores[i];
            }
            else if (scores[i] > max)
            {
                countMax++;
                max = scores[i];
            }
        }

        return new int[] { countMax, countMin };
    }

    @SuppressWarnings("serial")
    @Test
    public void getTotalX()
    {

        assertEquals(3, getTotalX(new ArrayList<Integer>()
        {
            {
                add(2);
                add(4);

            }
        }, new ArrayList<Integer>()
        {
            {
                add(16);
                add(32);
                add(96);
            }
        }));

        assertEquals(2, getTotalX(new ArrayList<Integer>()
        {
            {
                add(3);
                add(4);

            }
        }, new ArrayList<Integer>()
        {
            {
                add(24);
                add(48);
            }
        }));

        assertEquals(1, getTotalX(new ArrayList<Integer>()
        {
            {
                add(2);

            }
        }, new ArrayList<Integer>()
        {
            {
                add(20);
                add(30);
                add(12);

            }
        }));
    }

    public int getTotalX(List<Integer> a, List<Integer> b)
    {

        // We assume arrays are sorted ascending
        int last = b.get(0);
        int first = a.get(a.size() - 1);

        int count = 0;

        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> toRemove = new HashSet<Integer>();

        for (int i = first; i <= last; i++)
        {

            for (Integer integer : a)
            {
                if (i % integer == 0)
                    set.add(i);
            }
        }

        for (int i = 0; i < a.size(); i++)
        {

            for (Integer integer : set)
            {
                if (integer % a.get(i) != 0)
                    toRemove.add(integer);
            }
        }
        set.removeAll(toRemove);

        int countNotDiv = 0;

        for (Integer integer2 : set)
        {

            countNotDiv = 0;
            for (int i = b.size() - 1; i >= 0; i--)
            {

                if (b.get(i) % integer2 != 0)
                    countNotDiv++;
            }

            if (countNotDiv == 0)
                count++;
        }

        return count;
    }

    @SuppressWarnings("serial")
    @Test
    public void gradingStudent()
    {

        assertEquals(new ArrayList<Integer>()
        {
            {
                add(75);
                add(67);
                add(40);
                add(33);

            }
        }, gradingStudents(new ArrayList<Integer>()
        {
            {
                add(73);
                add(67);
                add(38);
                add(33);

            }
        }));
    }

    /**
     * @param grades
     * @return
     */
    private LinkedList<Integer> gradingStudents(List<Integer> grades)
    {
        LinkedList<Integer> list = new LinkedList<Integer>();

        for (Integer integer : grades)
        {

            if (integer < 38)
            {
                list.add(integer);
                continue;
            }

            Integer round = 5 * (integer / 5);

            if ((round + 5) - integer < 3)
                list.add(round + 5);
            else
                list.add(integer);

        }
        return list;
    }

    public void kangaroo()
    {

        assertEquals("YES", calculateIntersectionPoint(0, 3, 4, 2));
        assertEquals("NO", calculateIntersectionPoint(0, 2, 5, 3));
        assertEquals("NO", calculateIntersectionPoint(21, 6, 47, 3));
        assertEquals("NO", calculateIntersectionPoint(35, 1, 45, 3));

    }

    private String calculateIntersectionPoint(int b1, int m1, int b2, int m2)
    {

        double x = (double) (b2 - b1) / (m1 - m2);

        if (x < 0 || !(x % 1 == 0))
            return "NO";

        double y = m1 * x + b1;

        return y < 0 ? "NO" : "YES";
    }

    @Test
    public void countApplesAndOranges()
    {

        countApplesAndOranges(7, 11, 5, 15, new int[] { -2, 2, 1 }, new int[] { 5, -6 });
        countApplesAndOranges(2, 3, 1, 1, new int[] { -2 }, new int[] { -1 });

    }

    private void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges)
    {

        int countApple = 0;
        int countOrange = 0;

        for (int j = 0; j < apples.length; j++)
        {
            int dist = a + apples[j];

            if (dist >= s && dist <= t)
                countApple++;
        }

        for (int i = 0; i < oranges.length; i++)
        {
            int dist = b + oranges[i];

            if (dist >= s && dist <= t)
                countOrange++;
        }

        System.out.println(countApple);
        System.out.println(countOrange);

    }

    @Test
    public void timeConversion()
    {

        assertEquals("19:05:45", timeConversion("07:05:45PM"));
        assertEquals("07:05:45", timeConversion("07:05:45AM"));
        assertEquals("00:05:45", timeConversion("12:05:45AM"));

    }

    /**
     * @param s
     * @return
     */
    private String timeConversion(String s)
    {
        String time = s.substring(0, 8);
        String format = s.substring(8, 10);

        Integer hour = Integer.parseInt(time.substring(0, 2));

        switch (format)
        {

            case "AM":

                if (hour == 12)
                {
                    time = "00" + time.substring(2, 8);

                }
                else
                {
                    if (hour < 10)
                        time = "0" + Integer.toString(hour) + time.substring(2, 8);

                }

                break;

            default:

                if (hour != 12)
                    time = Integer.toString(hour + 12) + time.substring(2, 8);

                break;
        }
        return time;
    }

    @Test
    public void staircase()
    {
        int n = 10;

        stairCase(n);
    }

    /**
     * @param n
     */
    private void stairCase(int n)
    {
        int spaces = n - 1;

        for (int i = 0; i < n; i++)
        {

            for (int j = 0; j < spaces; j++)
            {
                System.out.print(" ");
            }

            for (int h = 0; h < n - spaces; h++)
            {
                System.out.print("#");

            }
            System.out.println("");

            --spaces;
        }
    }

    @Test
    public void plusMinus()
    {

        int[] arr = new int[] { -4, 3, -9, 0, 4, 1 };
        plusMinus(arr);
    }

    /**
     * @param arr
     */
    private void plusMinus(int[] arr)
    {
        double size = arr.length;

        double[] count = new double[3];

        for (int i = 0; i < arr.length; i++)
        {

            if (arr[i] > 0)
                ++count[0];
            else if (arr[i] < 0)
                ++count[1];
            else
                ++count[2];

        }

        for (int i = 0; i < count.length; i++)
        {
            System.out.println(String.format("%.6f", (count[i] / size)));
        }
    }

    @Test
    public void isValid()
    {
        assertEquals("YES", isValidString("abc"));
        assertEquals("NO", isValidString("aaaabbcc"));
        assertEquals("YES", isValidString("abcdefghhgfedecba"));
        assertEquals("YES", isValidString("aabbc"));
        assertEquals("YES", isValidString(
                "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"));

        assertEquals("NO", isValidString("aabbcd"));
        assertEquals("NO", isValidString("xxxaabbccrry"));
    }

    /**
     * @param s
     * @return
     */
    private String isValidString(String s)
    {

        List<Entry<Character, Integer>> sortedEntries = s.chars().boxed().collect(Collectors.toMap(
                // key = char
                k -> Character.valueOf((char) k.intValue()), v -> 1, // 1 occurence
                Integer::sum)).entrySet().stream().sorted(new Comparator<Entry<Character, Integer>>()
                {
                    @Override
                    public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2)
                    {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                }).collect(Collectors.toCollection(LinkedList::new));

        int lastIndex = sortedEntries.size() - 1;

        int diff = sortedEntries.get(0).getValue() - sortedEntries.get(lastIndex).getValue();

        /**
         * First and last have same frequency
         */
        if (diff == 0
                /**
                 * Last has frequency 1 and all others elements have same frequency
                 */
                || sortedEntries.get(lastIndex).getValue() == 1
                        && sortedEntries.get(0).getValue() - sortedEntries.get(lastIndex - 1).getValue() == 0
                /**
                 * First and second element are 1 step away and all others elements have same frequency
                 */
                || sortedEntries.get(1).getValue() == sortedEntries.get(lastIndex).getValue()
                        && sortedEntries.get(0).getValue() - sortedEntries.get(1).getValue() == 1)
            return "YES";

        return "NO";
    }

    @Test
    public void stringConstruction()
    {
        assertEquals(3, stringConstruction("abcabc"));
        assertEquals(4, stringConstruction("abcd"));
        assertEquals(2, stringConstruction("bccb"));
        assertEquals(7, stringConstruction(
                "gbcebabbfffcdgfeeaadecaeecabbabbgcafeabgecfeffcbafgdegdacefcadabbfdcgcebegbfgeeebfegfacdagbbgeagaaceefcaedceacceebdgebeecedcbdbeebecgcfcgdaaaegfbcbfffccffabbceafaagdedadbfcaedaffbaggebfedegfabefafefgdbafedbggabccaedabfgfgggbcfgeggdcdfeebaedaaccefgegbffaaggdcbbbfdbgaaffbbgcfafccdgcaabccbfbgbabegddagcgfbcdfdaccegbabfedbbdaddebddgegedgaabebfeeggddagaeececcafdgddceddcbdagaecceacgfabgccecgecgcefaafcaedfccdeeceffefadeffefggaeggbbfgcacgfaeefbfbccggcbcgeagcaacdcbegcdaacdgbebdaabddeagafbfagfebfefffcbcgefbcfeggafccabfagegccefe"));
        assertEquals(3, stringConstruction("abcaaabcaa"));

    }

    /**
     * @param s
     * @return
     */
    private int stringConstruction(String s)
    {
        int length = s.toCharArray().length;

        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < length; i++)
        {

            set.add(s.charAt(i));

        }
        return set.size();
    }

    @Test
    public void makingAnagrams()
    {
        String s1 = "cde";
        String s2 = "abc";
        Map<Character, Integer> secPartMap = new HashMap<Character, Integer>();

        for (int i = 0; i < s2.toCharArray().length; i++)
        {
            secPartMap.put(s2.charAt(i), secPartMap.getOrDefault(s2.charAt(i), 0) + 1);
        }

        int count = compareStringForAnagram(s1, s2, secPartMap);

        assertEquals(4, count + secPartMap.values().stream().mapToInt(Integer::intValue).sum());
    }

    @Test
    public void anagram()
    {

        assertEquals(3, anagram("aaabbb"));

        assertEquals(-1, anagram("abc"));

        assertEquals(1, anagram("xaxbbbxx"));

        assertEquals(5, anagram("fdhlvosfpafhalll"));

        assertEquals(0, anagram("xyyx"));

    }

    /**
     * @param s
     * @return
     */
    private int anagram(String s)
    {
        if (s.length() % 2 != 0)
            return -1;

        int middle = s.length() / 2;

        String firstPart = s.substring(0, middle);
        String secPart = s.substring(middle, s.length());
        Map<Character, Integer> secPartMap = new HashMap<Character, Integer>();

        for (int i = 0; i < secPart.toCharArray().length; i++)
        {
            secPartMap.put(secPart.charAt(i), secPartMap.getOrDefault(secPart.charAt(i), 0) + 1);
        }

        return compareStringForAnagram(firstPart, secPart, secPartMap);
    }

    /**
     * @param s
     * @param firstPart
     * @param secPart
     * @return
     */
    private int compareStringForAnagram(String firstPart, String secPart, Map<Character, Integer> secPartMap)
    {

        int count = 0;

        for (int i = 0; i < firstPart.toCharArray().length; i++)
        {
            Character c = firstPart.charAt(i);

            if (secPartMap.get(c) != null && secPartMap.get(c) > 0)
            {

                secPartMap.put(c, secPartMap.get(c) - 1);

                continue;
            }
            count++;
        }
        return count;
    }

    @Test
    public void hourglassSum()
    {
        int arr[][] = new int[][] { { 1, 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0 },
                { 0, 0, 2, 4, 4, 0 }, { 0, 0, 0, 2, 0, 0 }, { 0, 0, 1, 2, 4, 0 } };

        SortedSet<Long> set = new TreeSet<Long>();

        for (int i = 0; i < arr.length - 2; i++)
        {

            for (int j = 0; j < arr.length - 2; j++)
            {
                List<Integer> list1 = Arrays.stream(arr[i]).boxed().collect(Collectors.toCollection(LinkedList::new))
                        .subList(j, j + 3);
                Integer middle = Arrays.stream(arr[i + 1]).boxed().collect(Collectors.toCollection(LinkedList::new))
                        .get(j + 2 / 2);
                List<Integer> list3 = Arrays.stream(arr[i + 2]).boxed()
                        .collect(Collectors.toCollection(LinkedList::new)).subList(j, j + 3);

                set.add(list1.stream().reduce((x, y) -> x + y).get() + list3.stream().reduce((x, y) -> x + y).get()
                        + middle.longValue());
            }
        }
        assertEquals(19, set.last().intValue());

    }

    @Test
    public void luckBalance()
    {
        int k = 2;
        int[][] contests = { { 5, 1 }, { 1, 1 }, { 4, 0 } };
        int total = 0;
        List<Integer> importantContests = new ArrayList<>();

        for (int i = 0; i < contests.length; i++)
        {
            int l = contests[i][0];
            int t = contests[i][1];
            total += l;
            if (t == 1)
            {
                importantContests.add(l);
            }
        }

        Collections.sort(importantContests);
        int luckToFlip = 0;
        // all important contests minus contest we can loose
        int mustWinImprCount = importantContests.size() - k;
        for (int i = 0; i < mustWinImprCount; i++)
        {
            luckToFlip += importantContests.get(i);
        }
        // multiplied by 2 because we have already added all luck
        int result = total - 2 * luckToFlip;

        System.out.println(result);
    }

    @Test
    public void countTriplets()
    {
        @SuppressWarnings("serial")
        List<Long> arr = new ArrayList<Long>()
        {
            {
                add(1L);
                add(3L);
                add(9L);
                add(9L);
                add(27L);
                add(81L);
            }
        };
        long r = 3;

        Map<Long, Long> t2 = new HashMap<>();
        Map<Long, Long> t3 = new HashMap<>();
        long result = 0L;

        for (Long a : arr)
        {
            result += t3.getOrDefault(a, 0L);
            if (t2.containsKey(a))
            {
                t3.put(a * r, t3.getOrDefault(a * r, 0L) + t2.get(a));
            }
            t2.put(a * r, t2.getOrDefault(a * r, 0L) + 1);
        }

        System.out.println(result);
    }

    @Test
    public void countSwaps()
    {

        int[] a = { 4, 2, 3, 1 };

        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++)
        {

            for (int j = 0; j < n - 1; j++)
            {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1])
                {
                    int swap = a[j];

                    a[j] = a[j + 1];
                    a[j + 1] = swap;

                    count++;
                }
            }

        }
        System.out.println("Array is sorted in " + count + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[n - 1]);

    }

    @Test
    public void chocolateFeast()
    {
        int n = 10;
        int c = 2;
        int m = 5;

        int chocolate = n / c;

        int wrappers = chocolate;
        int div = 0;
        while (wrappers >= m)
        {
            div = wrappers / m;
            chocolate += div;
            wrappers = div + (wrappers % m);
        }

        System.out.println(chocolate);
    }

    @Test
    public void theLoveLetterMystery()
    {
        String s = "cde";

        int lengthOf = s.length();
        int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

        char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphaBet.length; i++)
        {
            map.put(alphaBet[i], i);
        }

        int count = 0;
        for (int i = lengthOf - 1; i >= middlePoint; i--)
        {
            int posLeft = map.get(s.charAt(i));
            int posRight = map.get(s.charAt(lengthOf - 1 - i));

            count += Math.abs(posRight - posLeft);
        }

        assertEquals(2, count);
    }

    @Test
    public void beautifulBinaryString()
    {
        String B = "0100101010";
        System.out.println((B.length() - B.replaceAll("010", "").length()) / 3);

        /**
         * 
         */
        int step = 0;
        for (int i = 0; i <= B.length() - 3;)
        {
            if (B.substring(i, i + 3).equals("010"))
            {
                step++;
                i = i + 3;
            }
            else
            {
                i++;
            }
        }
        System.out.println(step);
    }

    @Test
    public void alternatingCharacters() throws IOException
    {
        String s = new String(Files.readAllBytes(Paths.get("src/test/resources/veryLongWord.txt")));
        int stringLength = s.toCharArray().length;
        char c = s.charAt(0);
        // Important!! use string builder and not concat of String
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toString(c));

        for (int i = 1; i < stringLength; i++)
        {
            char ch = s.charAt(i);

            if (c == ch)
                continue;

            sb.append(Character.toString(ch));
            c = ch;
        }

        System.out.println(stringLength - sb.length());
    }

    @Test
    public void gemstones()
    {

        String[] arr = new String[] { "abcdde", "baccd", "eeabg" };

        char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphaBet.length; i++)
        {
            map.put(alphaBet[i], i);
        }

        int countGemStone = 0;
        int[] alphabet = new int[26];

        for (int i = 0; i < arr.length; i++)
        {
            int stringLength = arr[i].toCharArray().length;
            Set<Character> set = new HashSet<Character>();

            for (int j = 0; j < stringLength; j++)
            {

                char c = arr[i].charAt(j);
                if (set.contains(c))
                    continue;

                set.add(c);
                int pos = map.get(c);

                if (++alphabet[pos] == arr.length)
                    countGemStone++;
            }
        }

        System.out.println(countGemStone);
    }

    @Test
    public void funnyString()
    {
        String s = "acxz";
        int[] diffAsc = new int[s.length() - 1];
        int[] diffDesc = new int[s.length() - 1];

        for (int i = 0; i < s.length() - 1; i++)
        {
            diffAsc[i] = Math.abs((int) s.charAt(i + 1) - (int) s.charAt(i));
        }
        int count = 0;
        for (int i = s.length() - 1; i > 0; i--)
        {
            diffDesc[count] = Math.abs((int) s.charAt(i) - (int) s.charAt(i - 1));
            ++count;
        }

        System.out.println(Arrays.equals(diffAsc, diffDesc) ? "Funny" : "Not Funny");
    }

    @Test
    public void separateNumbers()
    {
        String s = "101103";
        boolean valid = false;
        long firstx = -1;
        // Try each possible starting number
        for (int i = 1; i <= s.length() / 2; ++i)
        {
            long x = Long.parseLong(s.substring(0, i));
            firstx = x;
            // Build up sequence starting with this number
            String test = Long.toString(x);
            while (test.length() < s.length())
            {
                test += Long.toString(++x);
            }
            // Compare to original
            if (test.equals(s))
            {
                valid = true;
                break;
            }
        }
        System.out.println(valid ? "YES " + firstx : "NO");
    }

    @Test
    public void weightedUniformStrings()
    {
        char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphaBet.length; i++)
        {
            map.put(alphaBet[i], i + 1);
        }

        String s = "aaabbbbcccddd";
        int[] queries = new int[] { 9, 7, 8, 12, 5 };

        Set<Integer> set = new HashSet<Integer>();
        char c = s.charAt(0);
        int weight = map.get(c);
        int count = weight;
        set.add(count);

        for (int i = 1; i < s.toCharArray().length; i++)
        {
            char ch = s.charAt(i);
            if (ch == c)
            {
                count += weight;
                set.add(count);
                continue;

            }
            weight = map.get(ch);
            count = weight;
            set.add(count);
            c = ch;
        }

        String[] toReturn = new String[queries.length];

        for (int i = 0; i < queries.length; i++)
        {
            toReturn[i] = "No";

            if (set.contains(queries[i]))
                toReturn[i] = "Yes";

        }

        System.out.println(Arrays.toString(toReturn));
    }

    @Test
    public void pangram()
    {
        String s = "We promptly judged antique ivory buckles for the prize".toLowerCase().replaceAll("\\s", "");
        Set<Character> set = new HashSet<Character>();

        for (int i = 0; i < s.toCharArray().length; i++)
        {
            set.add(s.charAt(i));
        }

        String assertion = set.size() < 26 ? "not pangram" : "pangram";
        assertEquals("not pangram", assertion);
    }

    @Test
    public void hackerrankInString()
    {

        String hack = "hackerrank";
        Queue<Character> queue = new LinkedList<Character>();

        for (int i = 0; i < hack.toCharArray().length; i++)
        {
            queue.add(hack.charAt(i));
        }
        String s = "hacakaeararanaka";

        for (int i = 0; i < s.toCharArray().length; i++)
        {
            if (queue.peek() == s.charAt(i))
                queue.poll();

            if (queue.size() == 0)
                break;
        }

        assertEquals(0, queue.size());

    }

    @Test
    public void marsExploration()
    {
        String s = "SOSSOT";
        int cycleOfThree = 0;
        boolean isOdd = true;
        boolean tmp = false;
        int count = 0;

        for (int i = 0; i < s.length(); i++)
        {
            isOdd = (i & 1) == 1;

            if (tmp)
                isOdd = !isOdd;

            if (!isOdd)
            {
                if (s.charAt(i) != 'S')
                    count++;
            }
            else
            {
                if (s.charAt(i) != 'O')
                    count++;
            }

            cycleOfThree++;
            if (cycleOfThree == 3)
            {
                tmp = !tmp;
                cycleOfThree = 0;
                continue;
            }

        }
        assertEquals(1, count);
    }

    @Test
    public void caesarCipher()
    {

        char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int length = alphaBet.length;
        int k = 87;
        int shift = k > length ? k % length : k;

        Map<Character, Integer> posToChar = new HashMap<>();

        for (int i = 0; i < length; i++)
        {
            posToChar.put(alphaBet[i], i);
        }

        String s = "www.abc.xy";
        int sLength = s.toCharArray().length;
        Integer pos = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sLength; i++)
        {
            char c = s.charAt(i);
            boolean isUpperCase = Character.isUpperCase(c);
            pos = posToChar.get(Character.toLowerCase(c));

            char newChar = null == pos ? c : alphaBet[pos + shift < length ? pos + shift : shift - (length - pos)];

            sb.append(isUpperCase ? Character.toUpperCase(newChar) : newChar);
        }

        assertEquals("fff.jkl.gh", sb.toString());

    }

    @Test
    public void cavityMap()
    {
        String[] grid = new String[] { "1112", "1912", "1892", "1234" };

        int length = grid.length;

        for (int i = 1; i < length - 1; i++)
        {
            for (int j = 1; j < grid.length - 1; j++)
            {
                int cavity = Character.getNumericValue(grid[i].charAt(j));
                int right = Character.getNumericValue(grid[i].charAt(j + 1));
                int left = Character.getNumericValue(grid[i].charAt(j - 1));

                int top = Character.getNumericValue(grid[i - 1].charAt(j));
                int bottom = Character.getNumericValue(grid[i + 1].charAt(j));

                if (cavity > right && cavity > left && cavity > top && cavity > bottom)
                {
                    StringBuilder tmp = new StringBuilder(grid[i]);
                    tmp.setCharAt(j, 'X');
                    grid[i] = tmp.toString();
                }
            }

        }

        System.out.println("Array with cavities : " + Arrays.toString(grid));
    }

    @Test
    public void theTimeInWords()
    {

        int h = 7;
        int m = 29;

        String time = "";

        if (m == 0)
        {
            time += ones[h] + " " + ones[m];
            return;
        }

        if (m <= 30)
        {

            time += (m == 1 ? " minute" : (m == 15 || m == 30) ? "" : " minutes") + " past " + ones[h];

            if (m < 20)
            {

                time = ones[m] + time;
                return;
            }

            time = tens[(int) m / 10] + ((int) m % 10 == 0 ? "" : " " + ones[(int) m % 10]) + time;

        }
        else
        {
            int to = 60 - m;

            time += (to == 1 ? " minute" : (to == 15) ? "" : " minutes") + " to " + ones[h + 1];

            if (to < 20)
            {

                time = ones[to] + time;
                return;
            }

            time = tens[(int) to / 10] + ((int) to % 10 == 0 ? "" : " " + ones[(int) to % 10]) + time;

        }
        System.out.println(time);
    }

    @Test
    public void halloweenSale()
    {
        int p = 100;
        int d = 19;
        int m = 1;
        int s = 180;

        if (s < p)
            return;

        int buy = 1;

        int sum = p;
        int x = p;
        int toMin = (p - m) / d;

        for (int i = 0; i < toMin; i++)
        {

            x = x - d;

            if (sum + x > s)
                return;

            sum += x;
            buy++;
        }

        buy += (s - sum) / m;

        System.out.println(buy);
    }

    @Test
    public void minimumDistances()
    {

        int[] a = new int[] { 7, 1, 3, 4, 1, 7 };

        Map<Integer, Integer> map = new HashMap<>();
        int maxDist = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++)
        {

            if (map.get(a[i]) == null)
            {

                map.put(a[i], i);
            }
            else
            {

                maxDist = Math.min(i - map.get(a[i]), maxDist);
            }

        }

        assertEquals(3, maxDist);
    }

    @Test
    public void beautifulTriplets()
    {

        // int[] arr = new int[] { 2, 2, 3, 6, 5 };
        int[] arr = new int[] { 1, 2, 4, 5, 7, 8, 10 };

        int d = 3;

        int countTriplets = 0;
        int pivot = 0;
        int beautifTripl = 0;

        int[] currentTripl = new int[3];
        currentTripl[0] = 0;

        int i = 1;
        while (pivot < arr.length - 1)
        {
            int diff = arr[i] - arr[pivot];

            if (diff == d)
            {
                if (countTriplets == 0)
                    currentTripl[0] = pivot;

                countTriplets++;
                pivot = i;

                if (countTriplets == 2)
                {
                    pivot = currentTripl[0] + 1;
                    i = pivot + 1;
                    countTriplets = 0;
                    beautifTripl++;
                }

                continue;
            }

            if (i == arr.length - 1)
            {
                pivot = currentTripl[0] + 1;
                i = pivot + 1;
                currentTripl[0] = pivot;
                countTriplets = 0;
                continue;
            }

            i++;
        }

        assertEquals(3, beautifTripl);
    }

    @Test
    public void kaprekarNumbers()
    {

        int p = 1;
        int q = 100;

        boolean foundKaprekar = false;
        for (int num = p; num <= q; num++)
        {
            if (isKaprekar(num))
            {
                foundKaprekar = true;
            }
        }
        if (!foundKaprekar)
        {
            System.out.println("INVALID RANGE");
        }
    }

    private boolean isKaprekar(int num)
    {
        long squared = (long) num * num;
        String str = String.valueOf(squared);
        String left = str.substring(0, str.length() / 2);
        String right = str.substring(str.length() / 2);
        int numL = (left.isEmpty()) ? 0 : Integer.parseInt(left);
        int numR = (right.isEmpty()) ? 0 : Integer.parseInt(right);
        if (numL + numR == num)
        {
            System.out.print(num + " ");
            return true;
        }
        else
        {
            return false;
        }
    }

    @Test
    public void taumAndBday()
    {

        int b = 27984;
        int w = 1402;
        int bc = 619246;
        int wc = 615589;
        int z = 247954;

        Long cost = 0L;

        if (bc + z < wc)
        {
            cost += (long) w * (bc + z);
            cost += (long) b * bc;

        }
        else if (wc + z < bc)
        {

            cost += (long) b * (wc + z);
            cost += (long) w * wc;
        }
        else
        {
            cost += (long) b * bc;
            cost += (long) w * wc;
        }

        assertEquals(18192035842L, cost);
    }

    @Test
    public void twoStrings()
    {
        String s1 = "hi";
        String s2 = "World";
        int[] chars = new int[128];

        for (int i : s1.toCharArray())
        {
            chars[i]++;

        }

        int count = 0;
        for (int i : s2.toCharArray())
        {
            if (chars[i] > 0)
                count++;

        }
        System.out.println(count);
    }

    @Test
    public void hashTablesRansomNote()
    {

        String[] note = new String[] { "two", "times", "two", "is", "four" };
        String[] magazine = new String[] { "two", "times", "three", "is", "not", "four" };

        Map<String, Integer> magazineMap = new HashMap<String, Integer>();

        for (String s : magazine)
        {
            if (magazineMap.get(s) != null)
            {
                magazineMap.put(s, magazineMap.get(s) + 1);
            }
            else
            {
                magazineMap.put(s, 1);
            }
        }

        for (String s : note)
        {
            if (!magazineMap.containsKey(s))
            {
                System.out.println("No");
                return;
            }

            int counter = magazineMap.get(s) - 1;

            if (counter == 0)
            {
                magazineMap.remove(s);
            }
            else
            {
                magazineMap.put(s, counter);
            }
        }

        System.out.println("Yes");

    }

    @Test
    public void sockMerchant()
    {

        int[] ar = { 44, 55, 11, 15, 4, 72, 26, 91, 80, 14, 43, 78, 70, 75, 36, 83, 78, 91, 17, 17, 54, 65, 60, 21, 58,
                98, 87, 45, 75, 97, 81, 18, 51, 43, 84, 54, 66, 10, 44, 45, 23, 38, 22, 44, 65, 9, 78, 42, 100, 94, 58,
                5, 11, 69, 26, 20, 19, 64, 64, 93, 60, 96, 10, 10, 39, 94, 15, 4, 3, 10, 1, 77, 48, 74, 20, 12, 83, 97,
                5, 82, 43, 15, 86, 5, 35, 63, 24, 53, 27, 87, 45, 38, 34, 7, 48, 24, 100, 14, 80, 54 };

        int[] arrayPair = new int[101];

        for (int i = 0; i < ar.length; i++)
        {

            arrayPair[ar[i]] = ++arrayPair[ar[i]];
        }

        System.out.println("Sock array --> " + Arrays.toString(arrayPair));

        int returnPair = 0;

        for (int i = 0; i < arrayPair.length; i++)
        {

            if (arrayPair[i] > 1)
                returnPair += arrayPair[i] / 2;

        }

        System.out.println("Number of Pairs --> " + returnPair);

    }

    @Test
    public void countingValleys()
    {

        String s = "DDUUDDUDUUUD";

        boolean isValley = false;
        int seaLevel = 0;
        int numValleys = 0;

        for (char c : s.toCharArray())
        {

            if (c == 'U')
                seaLevel++;

            if (c == 'D')
                seaLevel--;

            if (seaLevel == 0 && isValley)
            {
                isValley = false;
                numValleys++;
            }

            if (seaLevel < 0)
                isValley = true;

            System.out.println("Sea level " + seaLevel);
        }
        System.out.println("Num Valleys " + numValleys);

    }

    @Test
    public void jumpingOnTheClouds()
    {

        // int[] c = { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0 };
        // int[] c = { 0, 0, 0, 1, 0, 0 };
        int[] c = { 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0,
                1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0,
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 };

        int countZero = 0;
        int countStep = 0;

        for (int i = 0; i < c.length; i++)
        {

            if (c[i] == 0)
                countZero++;

            if (c[i] == 1)
            {

                countStep++;

                countStep += countZero / 2;

                countZero = 0;
            }

            if (i == c.length - 1)
            {
                if (countZero > 1)
                    countStep += countZero / 2;

            }
        }

        System.out.println("Total steps " + countStep);
    }

    @Test
    public void repeatedString()
    {

        String s = "aba";
        long n = 10;

        long finalCount;
        long count = countOccourences(s);

        long occ = n / s.length();

        Long remain = n % s.length();

        String subString = s.substring(0, remain.intValue());

        finalCount = occ * count;
        count = countOccourences(subString);

        finalCount += count;

        System.out.println("Letter a count " + finalCount);
    }

    private static long countOccourences(String s)
    {
        Pattern pattern = Pattern.compile("[^a]*a");
        Matcher matcher = pattern.matcher(s);
        long count = 0;

        while (matcher.find())
        {
            count++;
        }
        return count;
    }

    @Test
    public void electronicsShop()
    {

    }

    @Test
    public void catsAndAMouse()
    {

        int x = 0;
        int y = 0;
        int z = 0;

        int distCatA = Math.abs(x - z);
        int distCatB = Math.abs(y - z);

        if (distCatA == distCatB)
        {

            System.out.println("Mouse C");
        }
        else if (distCatA < distCatB)
        {
            System.out.println("Cat A");

        }
        else
        {
            System.out.println("Cat B");

        }

    }

    @Test
    public void arraysLeftRotation()
    {

        int d = 4;
        int[] a = { 1, 2, 3, 4, 5 };
        int arrayLength = a.length;

        int[] finalArray = new int[arrayLength];

        // Slow solution O(n*n)
        for (int i = 0; i < arrayLength; i++)
        {

            int posTmp = i;

            for (int j = 0; j < d; j++)
            {

                if (posTmp == 0)
                    posTmp = arrayLength;

                posTmp--;

            }

            finalArray[posTmp] = a[i];

        }

        // Faster O(n)
        for (int i = 0; i < arrayLength; i++)
        {
            int locSwitch = (i + (arrayLength - d));

            int newLocation = locSwitch % arrayLength;
            finalArray[newLocation] = a[i];
        }

        System.out.println("Array ruotato a sinistra di " + d + " times :" + Arrays.toString(finalArray));
    }

    @Test
    public void newYearChaos()
    {
        // int[] q = { 2, 1, 5, 3, 4 };
        // int[] q = { 5, 1, 2, 3, 7, 8, 6, 4 };
        int[] q = { 1, 2, 5, 3, 7, 8, 6, 4 };

        int countBribes = 0;

        for (int i = q.length - 1; i >= 0; i--)
        {
            if (q[i] - (i + 1) > 2)
            {
                System.out.println("Too chaotic");
                return;
            }

            for (int j = Math.max(0, q[i] - 2); j < i; j++)
                if (q[j] > q[i])
                    countBribes++;
        }

        System.out.println(countBribes);
    }

    @Test
    public void pickingNumbers()
    {

        // int[] arr = { 1, 3, 5, 2, 4, 6, 7 };
        /*
         * Integer[] arr = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
         * 4, 4, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 99 };
         */

        /*
         * Integer[] arr = { 4, 97, 5, 97, 97, 4, 97, 4, 97, 97, 97, 97, 4, 4, 5, 5, 97, 5, 97, 99, 4, 97, 5, 97, 97, 97, 5, 5, 97, 4, 5, 97, 97, 5,
         * 97, 4, 97, 5, 4, 4, 97, 5, 5, 5, 4, 97, 97, 4, 97, 5, 4, 4, 97, 97, 97, 5, 5, 97, 4, 97, 97, 5, 4, 97, 97, 4, 97, 97, 97, 5, 4, 4, 97, 4,
         * 4, 97, 5, 97, 97, 97, 97, 4, 97, 5, 97, 5, 4, 97, 4, 5, 97, 97, 5, 97, 5, 97, 5, 97, 97, 97 };
         */

        Integer[] arr = { 9, 6, 13, 16, 5, 18, 4, 10, 3, 19, 4, 5, 8, 1, 13, 10, 20, 17, 15, 10, 6, 10, 13, 20, 18, 17,
                7, 10, 6, 5, 16, 18, 13, 20, 19, 7, 16, 13, 20, 17, 4, 17, 8, 19, 12, 7, 17, 1, 18, 3, 16, 4, 5, 3, 15,
                17, 6, 17, 14, 11, 11, 7, 11, 6, 15, 15, 12, 6, 17, 19, 8, 6, 13, 9, 10, 19, 14, 18, 7, 9, 11, 16, 11,
                20, 4, 20, 10, 7, 8, 4, 2, 12, 11, 8, 12, 13, 19, 8, 8, 5 };

        List<Integer> a = Arrays.asList(arr);

        int z = 0;
        int[] freq = new int[100];

        for (int i = 0; i < a.size(); i++)
            freq[a.get(i)]++;

        for (int i = 2; i < 100; i++)
            z = Math.max(z, freq[i] + freq[i - 1]);
    }

    @Test
    public void climbingTheLeaderboard() throws IOException
    {

        int[] alice = new int[] { 5, 25, 50, 120 };

        int[] scores = new int[] { 100, 100, 50, 40, 40, 20, 10 };

        /*
         * int[] alice = new int[200000];
         * 
         * int[] scores = new int[200000];
         */

        /**
         * Global scores
         */

        /*
         * InputStream is = new FileInputStream("src/test/resources/scores.txt"); BufferedReader buf = new BufferedReader(new InputStreamReader(is));
         * 
         * String line = buf.readLine(); StringBuilder sb = new StringBuilder();
         * 
         * while (line != null) { sb.append(line).append("\n"); line = buf.readLine(); }
         * 
         * String[] scoresString = sb.toString().split(" ");
         * 
         * for (int i = 0; i < scoresString.length; i++) { scores[i] = new Integer(scoresString[i].replace("\n", "")); }
         */

        /**
         * Alice scores
         */

        /*
         * is = new FileInputStream("src/test/resources/alice.txt"); buf = new BufferedReader(new InputStreamReader(is));
         * 
         * line = buf.readLine(); sb = new StringBuilder();
         * 
         * while (line != null) { sb.append(line).append("\n"); line = buf.readLine(); }
         * 
         * String[] aliceString = sb.toString().split(" ");
         * 
         * for (int i = 0; i < aliceString.length; i++) { alice[i] = new Integer(aliceString[i].replace("\n", "")); }
         */

        /**
         * Start
         */
        int[] array = IntStream.of(scores).distinct().toArray();
        int i = array.length - 1;
        int[] res = new int[alice.length];
        int c = 0;
        for (int score : alice)
        {
            while (i >= 0)
            {
                if (score >= array[i])
                    i--;
                else
                {
                    res[c] = i + 2;
                    c++;
                    break;
                }
            }
            if (i < 0)
            {
                res[c] = 1;
                c++;
            }
        }

    }

    @Test
    public void theHurdleRace()
    {

        int k = 2;
        int[] height = new int[] { 1, 6, 3, 5, 2 };

        List<Integer> integersList = IntStream.of(height) // returns IntStream
                .boxed().collect(Collectors.toList());
        Collections.sort(integersList, Collections.reverseOrder());

        System.out.println("Max hurdle --> " + (integersList.get(0) - k));

    }

    @Test
    public void designerPDFViewer()
    {

        int[] h = new int[] { 1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7 };
        String word = new String("zaba");

        Map<Character, Integer> lettersMap = new HashMap<Character, Integer>();

        char ch;
        int count = 0;

        for (ch = 'a'; ch <= 'z'; ch++)
        {
            lettersMap.put(ch, count);
            count++;
        }

        int maxHeight = 0;
        for (char c : word.toCharArray())
        {

            int position = lettersMap.get(c);
            maxHeight = Math.max(maxHeight, h[position]);
        }

        System.out.println("PDFViewer Area -->" + maxHeight * word.length());
    }

    @Test
    public void utopianTree()
    {
        int n = 10;

        int i = 1, height = 1;
        while (i <= n)
        {
            if (i % 2 == 0)
            {
                height += 1;
            }
            else
            {
                height *= 2;
            }
            i++;
        }

        System.out.println("Utopian Tree height -->" + height);
    }

    @Test
    public void beautifilDays()
    {

        int i = 20, j = 23, k = 6;
        int countBeautiful = 0;
        int num = 0;
        int reversed = 0;

        for (int n = i; n <= j; n++)
        {
            reversed = 0;
            num = n;

            while (num != 0)
            {
                int digit = num % 10;
                reversed = reversed * 10 + digit;
                num /= 10;
            }

            double result = (double) (n - reversed) / k;

            if (result % 1 == 0)
                countBeautiful++;

        }

        System.out.println("Count beautiful days --> " + countBeautiful);
    }

    @Test
    public void extraLongFactorial()
    {
        Integer n = 25;
        System.out.println("Fattoriale di " + n + "--> " + factorial(BigInteger.valueOf(n)));

    }

    static BigInteger factorial(BigInteger n)
    {

        if (n == BigInteger.ZERO)
            return new BigInteger("1");

        return n.multiply(factorial(n.subtract(new BigInteger("1"))));
    }

    @Test
    public void sherlockAndSquares()
    {

        int a = 24;
        int b = 49;

        int count = (int) (Math.floor(Math.sqrt(b)) - Math.ceil(Math.sqrt(a))) + 1;

        System.out.println("Square root tra " + a + " e " + b + " sono " + count);

    }

    @Test
    public void libraryFine()
    {
        int d1 = 28;
        int m1 = 2;
        int y1 = 2015;
        int d2 = 15;
        int m2 = 4;
        int y2 = 2015;

        int fine = 0;

        int monthDiff = m1 - m2;
        int dayDiff = d1 - d2;
        if (y2 - y1 > 0)
        {

        }
        else if (y2 - y1 != 0)
        {

            if (monthDiff > 0)
            {

                fine = 500 * monthDiff;
            }
            else if (monthDiff != 0)
            {

                if (dayDiff > 0)
                    fine = 15 * dayDiff;
            }
        }

        System.out.println("Multa di " + fine);
    }

    @Test
    public void cutTheSticks()
    {
        int[] arr = new int[] { 5, 4, 4, 2, 2, 8 };

        int[] arr1 = new int[1000];

        int count = arr.length;

        for (int i = 0; i < arr.length; i++)
        {
            arr1[arr[i]]++;
        }

        List<Integer> response = new LinkedList<Integer>();
        response.add(count);

        for (int i = 0; i < arr1.length; i++)
        {

            if (arr1[i] == 0)
                continue;

            count -= arr1[i];
            if (count == 0)
                break;

            response.add(count);
        }

        int[] responseInt = response.stream().mapToInt(i -> i).toArray();

        System.out.println("CutTheSticks array -->" + Arrays.toString(responseInt));
    }

    @Test
    public void nonDivisibleSum()
    {
        // int[] S = new int[] { 278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282,
        // 718, 771, 575, 436 };
        int[] S = new int[] { 1, 7, 2, 4 };
        int N = S.length;

        int k = 3;
        // Array for storing frequency of modulo
        // values
        int f[] = new int[k];
        Arrays.fill(f, 0);

        // Fill frequency array with values modulo K
        for (int i = 0; i < N; i++)
            f[S[i] % k]++;

        // if K is even, then update f[K/2]
        if (k % 2 == 0)
            f[k / 2] = Math.min(f[k / 2], 1);

        // Initialize result by minimum of 1 or
        // count of numbers giving remainder 0
        int res = Math.min(f[0], 1);

        // Choose maximum of count of numbers
        // giving remainder i or K-i
        for (int i = 1; i <= k / 2; i++)
            res += Math.max(f[i], f[k - i]);

        System.out.println("nonDivisibleSum max array length" + res);

    }

    /**
     * @return
     */
    private LinkedList<Character> getSolution()
    {
        @SuppressWarnings("serial")
        LinkedList<Character> solution = new LinkedList<Character>()
        {
            {
                add('n');
                add('z');
                add('y');
                add('d');
                add('a');
                add('y');
                add('a');
                add('l');
                add('v');
                add('s');
                add('g');
                add('n');
                add('d');
                add('k');
                add('w');
                add('d');
                add('l');
                add('z');
                add('p');
                add('z');
                add('b');
                add('b');
                add('t');
                add('o');
                add('t');
                add('q');
                add('s');
                add('a');
                add('v');
                add('f');
                add('m');
                add('i');
                add('v');
                add('h');
                add('s');
                add('e');
                add('a');
                add('n');
                add('x');
                add('n');
                add('y');
                add('v');
                add('a');
                add('x');
                add('s');
                add('x');
                add('y');
                add('x');
                add('o');
                add('i');
                add('b');
                add('y');
                add('y');
                add('r');
                add('s');
                add('t');
                add('i');
                add('r');
                add('s');
                add('j');
                add('e');
                add('o');
                add('k');
                add('r');
                add('k');
                add('u');
                add('n');
                add('n');
                add('h');
                add('l');
                add('j');
                add('f');
                add('t');
                add('l');
                add('t');
                add('l');
                add('k');
                add('l');
                add('q');
                add('k');
                add('e');
                add('g');
                add('o');
                add('p');
                add('l');
                add('h');
                add('b');
                add('l');
                add('p');
                add('v');
                add('x');
                add('h');
                add('n');
                add('g');
                add('k');
                add('g');
                add('l');
                add('m');
                add('d');
                add('o');
                add('m');
                add('i');
                add('y');
                add('s');
                add('r');
                add('i');
                add('v');
                add('z');
                add('b');
                add('i');
                add('u');
                add('q');
                add('c');
                add('q');
                add('z');
                add('q');
                add('g');
                add('t');
                add('n');
                add('x');
                add('j');
                add('n');
                add('b');
                add('t');
                add('g');
                add('s');
                add('d');
                add('m');
                add('b');
                add('b');
                add('w');
                add('q');
                add('i');
                add('v');
                add('g');
                add('p');
                add('v');
                add('v');
                add('h');
                add('g');
                add('e');
                add('b');
                add('a');
                add('y');
                add('i');
                add('g');
                add('q');
                add('y');
                add('w');
                add('y');
                add('g');
                add('y');
                add('y');
                add('b');
                add('b');
                add('i');
                add('b');
                add('w');
                add('i');
                add('b');
                add('b');
                add('y');
                add('u');
                add('b');
                add('b');
                add('o');
                add('s');
                add('b');
                add('r');
                add('d');
                add('p');
                add('r');
                add('r');
                add('l');
                add('q');
                add('n');
                add('r');
                add('n');
                add('m');
                add('j');
                add('f');
                add('u');
                add('n');
                add('m');
                add('u');
                add('o');
                add('q');
                add('o');
                add('b');
                add('v');
                add('p');
                add('q');
                add('o');
                add('q');
                add('p');
                add('b');
                add('p');
                add('q');
                add('p');
                add('p');
                add('p');
                add('v');
                add('p');
                add('v');
                add('a');
                add('y');
                add('n');
                add('m');
                add('q');
                add('x');
                add('f');
                add('h');
                add('w');
                add('i');
                add('z');
                add('u');
                add('x');
                add('f');
                add('z');
                add('f');
                add('w');
                add('d');
                add('p');
                add('p');
                add('p');
                add('v');
                add('u');
                add('e');
                add('z');
                add('m');
                add('x');
                add('p');
                add('q');
                add('i');
                add('p');
                add('j');
                add('s');
                add('b');
                add('b');
                add('b');
                add('v');
                add('p');
                add('v');
                add('r');
                add('o');
                add('w');
                add('j');
                add('r');
                add('h');
                add('b');
                add('w');
                add('v');
                add('r');
                add('h');
                add('e');
                add('o');
                add('v');
                add('p');
                add('d');
                add('x');
                add('s');
                add('o');
                add('x');
                add('c');
            }
        };
        return solution;
    }

}
