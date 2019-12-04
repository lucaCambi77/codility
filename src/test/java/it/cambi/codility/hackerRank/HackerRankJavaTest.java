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
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author luca
 *
 */
public class HackerRankJavaTest
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

    @Test
    public void javaBigDecimal()
    {
        String[] array = new String[] { "-100",
                "50",
                "0",
                "56.6",
                "90",
                "0.12",
                ".12",
                "02.34",
                "000.000" };
        javaBigDecimal(array);

        assertTrue(Arrays.equals(new String[] { "90",
                "56.6",
                "50",
                "02.34",
                "0.12",
                ".12",
                "0",
                "000.000",
                "-100" }, array));
    }

    private void javaBigDecimal(String[] s)
    {
        Map<Integer, BigDecimal> map = new HashMap<>();
        for (int i = 0; i < s.length; i++)
            map.put(i, new BigDecimal(s[i]));

        List<Map.Entry<Integer, BigDecimal>> list = new LinkedList<Map.Entry<Integer, BigDecimal>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, BigDecimal>>()
        {
            public int compare(Map.Entry<Integer, BigDecimal> o1,
                    Map.Entry<Integer, BigDecimal> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Integer, BigDecimal> sortedMap = new LinkedHashMap<Integer, BigDecimal>();
        for (Map.Entry<Integer, BigDecimal> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        int k = 0;

        String[] solution = new String[s.length];

        for (Map.Entry<Integer, BigDecimal> entry : sortedMap.entrySet())
        {

            solution[k] = s[entry.getKey()];
            k++;
        }

        s = solution;
    }

    @Test
    public void javaDeque() throws IOException
    {
        assertEquals(3,
                javaDeque("5 3 5 2 3 2", 3));

        assertEquals(2,
                javaDeque("1 1 1 1 1 1 1 2", 3));

        assertEquals(8,
                javaDeque("1 2 3 4 5 6 7 8", 8));

        InputStream is = new FileInputStream("src/test/resources/deque/javadeque.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        buf.readLine();

        assertEquals(94983,
                javaDeque(buf.readLine(), 99877));

        buf.close();
    }

    private int javaDeque(String array, int m)
    {
        Deque<Integer> deque = new ArrayDeque<>();
        String[] split = array.split("\\s");
        int n = split.length;
        HashSet<Integer> set = new HashSet<>();
        int max = 0;

        for (int i = 0; i < n; i++)
        {
            deque.add(new Integer(split[i]));
            set.add(new Integer(split[i]));

            if (deque.size() == m)
            {
                if (set.size() > max)
                    max = set.size();

                int first = deque.remove();
                if (!deque.contains(first))
                    set.remove(first);
            }
        }

        return max;
    }

    @Test
    public void javaComparator()
    {
        int n = 5;

        Player[] player = new Player[n];
        Checker checker = new Checker();

        player[0] = new Player("amy", 100);
        player[1] = new Player("david", 100);
        player[2] = new Player("heraldo", 50);
        player[3] = new Player("aakansha", 75);
        player[4] = new Player("aleksa", 150);

        javaComparator(player, checker);

        assertEquals(true, Arrays.equals(
                new Player[] { new Player("aleksa", 150), new Player("amy", 100), new Player("david", 100), new Player("aakansha", 75),
                        new Player("heraldo", 50) },
                player));
    }

    public void javaComparator(Player[] player, Checker checker)
    {
        Arrays.sort(player, checker);
    }

    class Checker implements Comparator<Player>
    {

        @Override
        public int compare(Player o1, Player o2)
        {

            int a = o1.score - o2.score;
            int b = o1.name.compareTo(o2.name);

            if (a == 0)
                return b < 0 ? -1 : 1;

            if (a > 0)
                return -1;

            return 1;
        }

    }

    class Player
    {
        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + score;
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;

            Player other = (Player) obj;
            if (name == null)
            {
                if (other.name != null)
                    return false;
            }
            else if (!name.equals(other.name))
                return false;
            if (score != other.score)
                return false;
            return true;
        }

        String name;
        int score;

        public Player(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

    }

    @SuppressWarnings("serial")
    @Test
    public void javaArrayList()
    {
        javaArrayList(new ArrayList<List<Integer>>()
        {
            {
                add(Arrays.asList(new Integer[] { 5, 41, 77, 74, 22, 44 }));
                add(Arrays.asList(new Integer[] { 1, 12 }));
                add(Arrays.asList(new Integer[] { 4, 37, 34, 36, 52 }));
                add(Arrays.asList(new Integer[] { 0 }));
                add(Arrays.asList(new Integer[] { 3, 20, 22, 33 }));

            }
        }, new ArrayList<String>()
        {
            {
                add("1 3");
                add("3 4");
                add("3 1");
                add("4 3");
                add("5 5");

            }
        });

        String out = outContent.toString();

        assertEquals(out, "74\n" +
                "52\n" +
                "37\n" +
                "ERROR!\n" +
                "ERROR!\n");

    }

    private void javaArrayList(List<List<Integer>> list, List<String> queries)
    {
        for (String s : queries)
        {
            String[] query = s.split("\\s");
            int x = Integer.valueOf(query[0]);
            int size = list.size();

            if (x > size)
                System.out.println("ERROR!");
            else
            {

                List<Integer> l = list.get(x - 1);

                int position = Integer.valueOf(query[1]);
                size = l.size();

                if (null == l || (null != l && position >= size))
                    System.out.println("ERROR!");
                else
                    System.out.println(l.get(position));

            }
        }

    }

    @Test
    public void javaVisitorPattern()
    {

        Solution sol = new Solution();
        sol.solve();

    }

    enum Color
    {
        RED, GREEN
    }

    abstract class Tree
    {

        private int value;
        private Color color;
        private int depth;

        public Tree(int value, Color color, int depth)
        {
            this.value = value;
            this.color = color;
            this.depth = depth;
        }

        public int getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }

        public int getDepth()
        {
            return depth;
        }

        public abstract void accept(TreeVis visitor);
    }

    class TreeNode extends Tree
    {

        private ArrayList<Tree> children = new ArrayList<>();

        public TreeNode(int value, Color color, int depth)
        {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor)
        {
            visitor.visitNode(this);

            for (Tree child : children)
            {
                child.accept(visitor);
            }
        }

        public void addChild(Tree child)
        {
            children.add(child);
        }
    }

    class TreeLeaf extends Tree
    {

        public TreeLeaf(int value, Color color, int depth)
        {
            super(value, color, depth);
        }

        public void accept(TreeVis visitor)
        {
            visitor.visitLeaf(this);
        }
    }

    abstract class TreeVis
    {
        public abstract int getResult();

        public abstract void visitNode(TreeNode node);

        public abstract void visitLeaf(TreeLeaf leaf);

    }

    class SumInLeavesVisitor extends TreeVis
    {
        int result = 0;

        public int getResult()
        {
            // implement this
            return result;
        }

        public void visitNode(TreeNode node)
        {
            // implement this
            return;
        }

        public void visitLeaf(TreeLeaf leaf)
        {
            // implement this
            result += leaf.getValue();
        }
    }

    class ProductOfRedNodesVisitor extends TreeVis
    {
        int result = 0;

        public int getResult()
        {
            // implement this
            return result;
        }

        public void visitNode(TreeNode node)
        {
            // implement this
            if (node.getColor() == Color.RED)
                result *= node.getValue();
        }

        public void visitLeaf(TreeLeaf leaf)
        {
            // implement this
            if (leaf.getColor() == Color.RED)
                result *= leaf.getValue();
        }
    }

    class FancyVisitor extends TreeVis
    {
        int result = 0;

        public int getResult()
        {
            // implement this
            return result;
        }

        public void visitNode(TreeNode node)
        {
            // implement this
            if (node.getDepth() % 2 == 0 || node.getColor() == Color.GREEN)
                result += node.getValue();
        }

        public void visitLeaf(TreeLeaf leaf)
        {
            // implement this
            if (leaf.getDepth() % 2 == 0 || leaf.getColor() == Color.GREEN)
                result += leaf.getValue();
        }
    }

    class Solution
    {

        public Tree solve()
        {

            String values = new String("4 7 2 5 12");

            String colors = new String("0 1 0 0 1");

            String[] nodeValues = values.split("\\s");
            String[] colorValues = colors.split("\\s");

            String[] parToChild = new String[] { "1 2", "1 3", "3 4", "3 5" };

            TreeNode tree = new TreeNode(Integer.valueOf(nodeValues[0]), colorValues[0] == "0" ? Color.RED : Color.GREEN, 0);

            SumInLeavesVisitor sumInLeavesV = new SumInLeavesVisitor();

            ProductOfRedNodesVisitor productOfRedNodesV = new ProductOfRedNodesVisitor();

            FancyVisitor fancyV = new FancyVisitor();

            tree.accept(sumInLeavesV);
            tree.accept(productOfRedNodesV);
            tree.accept(fancyV);

            return tree;
        }

    }

    @Test
    public void calculatePower()
    {
        MyCalculator calculator = new MyCalculator();

        assertEquals("243", calculator.power(3, 5));
        assertEquals("16", calculator.power(2, 4));
        assertEquals("java.lang.Exception: n and p should not be zero.", calculator.power(0, 0));
        assertEquals("java.lang.Exception: n or p should not be negative.", calculator.power(-1, -2));

    }

    class MyCalculator
    {
        /*
         * Create the method long power(int, int) here.
         */
        public String power(int n, int p)
        {
            if (n == 0 && p == 0)
                return "java.lang.Exception: n and p should not be zero.";

            if (n < 0 || p < 0)
                return "java.lang.Exception: n or p should not be negative.";

            return calculatePower((long) n, (long) p).toString();
        }

        private Long calculatePower(long n, long p)
        {
            if (p == 0)
                return 1L;

            return n * calculatePower(n, p - 1L);
        }
    }

    @Test
    public void tagContetExtractor()
    {

        assertEquals(Arrays.asList(new String[] { "Nayeem loves counseling" }), tagContetExtractor("<h1>Nayeem loves counseling</h1>"));
        assertEquals(Arrays.asList(new String[] { "Sanjay has no watch" }),
                tagContetExtractor("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>"));
        assertEquals(Arrays.asList(new String[] {}),
                tagContetExtractor("<Amee>safat codes like a ninja</amee>"));
        assertEquals(Arrays.asList(new String[] { "Imtiaz has a secret crash" }),
                tagContetExtractor("<SA premium>Imtiaz has a secret crash</SA premium>"));
        assertEquals(Arrays.asList(new String[] {}),
                tagContetExtractor("<h1>had<h1>public</h1515></h1>"));

    }

    /**
     * @param s
     */
    private List<String> tagContetExtractor(String s)
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

    @ParameterizedTest
    @CsvSource({ "src/test/resources/regularExpression/username.txt,src/test/resources/regularExpression/usernameValidation.txt" })
    public void usernameValidator(String username, String validation) throws IOException
    {

        InputStream is = new FileInputStream(username);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        InputStream is1 = new FileInputStream(validation);
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
