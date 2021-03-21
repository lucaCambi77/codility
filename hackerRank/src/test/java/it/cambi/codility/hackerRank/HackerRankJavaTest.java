/** */
package it.cambi.codility.hackerRank;

import it.cambi.codility.util.TestUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/** @author luca */
class HackerRankJavaTest extends TestUtil {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void javaRegExII() {
    assertEquals("Goodbye bye world", javaRexExII("Goodbye bye bye world world world"));
    assertEquals("Sam went to his business", javaRexExII("Sam went went to to to his business"));
    assertEquals(
        "Reya is the best player in eye game",
        javaRexExII("Reya is is the the best player in eye eye game"));
    assertEquals("Hello Ab", javaRexExII("Hello hello Ab aB"));
    assertEquals("in inthe", javaRexExII("in inthe"));
  }

  private String javaRexExII(String input) {
    String regex = "\\b(\\w+)(\\s+\\1\\b)+";
    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    Matcher m = p.matcher(input);

    while (m.find()) input = input.replaceAll(m.group(), m.group(1));

    return input;
  }

  @Test
  public void javaRegExIp() {
    assertTrue(javaRegExIp("000.12.12.034"));
    assertTrue(javaRegExIp("121.234.12.12"));
    assertTrue(javaRegExIp("23.45.12.56"));
    assertFalse(javaRegExIp("00.12.123.123123.123"));
    assertFalse(javaRegExIp("122.23"));
    assertFalse(javaRegExIp("Hello.IP"));
  }

  private boolean javaRegExIp(String ip) {

    String isIpAddress =
        "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    return ip.matches(isIpAddress);
  }

  @Test
  public void javaPatterSyntaxChecker() {
    assertTrue(javaPatterSyntaxChecker("([A-Z])(.+)"));
    assertFalse(javaPatterSyntaxChecker("[AZ[a-z](a-z)"));
    assertFalse(javaPatterSyntaxChecker("batcatpat(nat"));
  }

  public boolean javaPatterSyntaxChecker(String pattern) {

    try {
      Pattern.compile(pattern);
      return true;
    } catch (PatternSyntaxException e) {
      return false;
    }
  }

  @Test
  public void javaPriorityQueue() {
    Priorities priorities = new Priorities();

    @SuppressWarnings("serial")
    List<String> events =
        new ArrayList<String>() {
          {
            add("ENTER John 3.75 50");
            add("ENTER Mark 3.8 24");
            add("ENTER Shafaet 3.7 35");
            add("SERVED");
            add("SERVED");
            add("ENTER Samiha 3.85 36");
            add("SERVED");
            add("ENTER Ashley 3.9 42");
            add("ENTER Maria 3.6 46");
            add("ENTER Anik 3.95 49");
            add("ENTER Dan 3.95 50");
            add("SERVED");
          }
        };

    List<Student> list = priorities.getStudents(events);

    assertArrayEquals(
        list.toArray(new Student[0]),
        new Student[] {
          new Student(50, "Dan", 3.95),
          new Student(42, "Ashley", 3.9),
          new Student(35, "Shafaet", 3.7),
          new Student(46, "Maria", 3.6)
        });
  }

  class Priorities {
    PriorityQueue<Student> queue = new PriorityQueue<>();

    List<Student> getStudents(List<String> events) {
      for (String string : events) {
        StringTokenizer st = new StringTokenizer(string);

        String event = st.nextToken();

        if ("ENTER".equals(event)) {
          String name = st.nextToken();
          double cgpa = Double.parseDouble(st.nextToken());
          int id = Integer.parseInt(st.nextToken());

          Student student = new Student(id, name, cgpa);
          queue.add(student);
        } else {
          queue.poll();
        }
      }

      @SuppressWarnings("serial")
      List<Student> solution =
          new ArrayList<Student>() {
            {
              while (!queue.isEmpty()) add(queue.poll());
            }
          };

      return solution;
    }
  }

  @Getter
  @Setter
  @EqualsAndHashCode
  class Student implements Comparable<Student> {

    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
      super();
      this.id = id;
      this.name = name;
      this.cgpa = cgpa;
    }

    @Override
    public int compareTo(Student o) {
      String name = o.getName();
      double cgpa = o.getCgpa();
      int id = o.getId();

      if (cgpa - getCgpa() > 0) return 1;

      if (getCgpa() - cgpa == 0) {
        int compare = getName().compareTo(name);

        if (compare == 0) return id - getId() > 0 ? 1 : 0;

        return compare;
      }

      return -1;
    }
  }

  @Test
  public void java1DArrayII() throws IOException {

    LinkedList<String> list = new LinkedList<>();

    try (BufferedReader in =
        new BufferedReader(new FileReader("src/test/resources/1dArrayII/output1.txt"))) {
      String line = in.readLine();

      while (line != null) {
        list.add(line);
        line = in.readLine();
      }
    }

    int i = 0;

    try (BufferedReader in =
        new BufferedReader(new FileReader("src/test/resources/1dArrayII/input1.txt"))) {
      String line = in.readLine();

      while (line != null) {

        StringTokenizer st = new StringTokenizer(line);
        st.nextToken();
        int leap = Integer.parseInt(st.nextToken());

        line = in.readLine();

        int[] game = Arrays.stream(line.split("\\s")).mapToInt(Integer::valueOf).toArray();
        assertEquals(list.get(i), java1DArrayII(game, leap) ? "YES" : "NO");
        line = in.readLine();
        i++;
      }
    }
  }

  public boolean java1DArrayII(int[] game, int leap) {
    return java1DArrayII(game, leap, 0, new boolean[game.length]);
  }

  public boolean java1DArrayII(int[] game, int leap, int position, boolean[] visited) {

    if (position >= game.length) return true;

    if (position < 0) return false;

    if (game[position] == 1 || visited[position]) return false;

    visited[position] = true;

    return java1DArrayII(game, leap, position + leap, visited)
        || java1DArrayII(game, leap, position - 1, visited)
        || java1DArrayII(game, leap, position + 1, visited);
  }

  @Test
  public void javaBitSet() throws IOException {

    LinkedList<String> list = new LinkedList<String>();

    try (BufferedReader in =
        new BufferedReader(new FileReader("src/test/resources/javabitset/sol1.txt"))) {
      String line = in.readLine();

      while (line != null) {
        list.add(line);
        line = in.readLine();
      }
    }

    int i = 0;

    try (BufferedReader in =
        new BufferedReader(new FileReader("src/test/resources/javabitset/input1.txt"))) {
      String line = in.readLine();

      StringTokenizer st = new StringTokenizer(line);
      int n = Integer.parseInt(st.nextToken()) + 1;

      int[] b1 = new int[n];
      int[] b2 = new int[n];

      line = in.readLine();

      while (line != null) {
        javaBitSet(line, b1, b2);
        printOnes(b1, b2);

        String result = list.get(i);
        StringTokenizer out = new StringTokenizer(result);

        assertEquals(out.nextToken() + " " + out.nextToken(), outContent.toString());

        line = in.readLine();
        i++;
        outContent.reset();
      }
    }
  }

  /**
   * @param b1
   * @param b2
   */
  private void printOnes(int[] b1, int[] b2) {
    System.out.print(
        IntStream.of(b1).boxed().filter(value -> value == 1).count()
            + " "
            + IntStream.of(b2).boxed().filter(value -> value == 1).count());
  }

  private void javaBitSet(String str, int[] b1, int[] b2) {

    StringTokenizer st = new StringTokenizer(str);

    String type = st.nextToken();

    switch (type) {
      case "AND":
        int posAnd = Integer.parseInt(st.nextToken());

        if (posAnd == 2) for (int i = 0; i < b2.length; i++) b2[i] = b2[i] & b1[i];
        else for (int i = 0; i < b2.length; i++) b1[i] = b1[i] & b2[i];

        break;

      case "SET":
        int set = Integer.parseInt(st.nextToken());
        int posSet = Integer.parseInt(st.nextToken());

        if (set == 2) b2[posSet] = 1;
        else b1[posSet] = 1;

        break;

      case "FLIP":
        int setFlip = Integer.parseInt(st.nextToken());
        int posFlip = Integer.parseInt(st.nextToken());

        if (setFlip == 2) b2[posFlip] = b2[posFlip] == 0 ? 1 : 0;
        else b1[posFlip] = b1[posFlip] == 0 ? 1 : 0;

        break;

      case "OR":
        int posOr = Integer.parseInt(st.nextToken());

        if (posOr == 2) for (int i = 0; i < b2.length; i++) b2[i] = b2[i] | b1[i];
        else for (int i = 0; i < b2.length; i++) b1[i] = b1[i] | b2[i];

        break;

      case "XOR":
        int posXOr = Integer.parseInt(st.nextToken());

        if (posXOr == 2) for (int i = 0; i < b2.length; i++) b2[i] = b2[i] ^ b1[i];
        else for (int i = 0; i < b2.length; i++) b1[i] = b1[i] ^ b2[i];

        break;
    }
  }

  @Test
  public void javasha256() throws NoSuchAlgorithmException {
    assertEquals(
        "872e4e50ce9990d8b041330c47c9ddd11bec6b503ae9386a99da8584e9bb12c4",
        javasha256("HelloWorld"));
    assertEquals(
        "f1d5f8d75bb55c777207c251d07d9091dc10fe7d6682db869106aacb4b7df678",
        javasha256("Javarmi123"));
  }

  private String javasha256(String str) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    return bytesToHex(digest.digest(str.getBytes(StandardCharsets.UTF_8)));
  }

  private String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
      String hex = Integer.toHexString(0xff & hash[i]);
      if (hex.length() == 1) hexString.append('0');
      hexString.append(hex);
    }
    return hexString.toString();
  }

  @Test
  public void javaMd5() throws NoSuchAlgorithmException {
    assertEquals("68e109f0f40ca72a15e05cc22786f8e6", javaMd5("HelloWorld"));
    assertEquals("2da2d1e0ce7b4951a858ed2d547ef485", javaMd5("Javarmi123"));
  }

  private String javaMd5(String str) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(str.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();

    return myHash;
  }

  @Test
  public void javaLambaExpression() {
    assertEquals("EVEN", javaLambaExpression("1 4"));
    assertEquals("PRIME", javaLambaExpression("2 5"));
    assertEquals("PRIME", javaLambaExpression("2 1"));
    assertEquals("PALINDROME", javaLambaExpression("3 898"));
    assertEquals("ODD", javaLambaExpression("1 3"));
    assertEquals("COMPOSITE", javaLambaExpression("2 12"));
  }

  private String javaLambaExpression(String str) {
    StringTokenizer st = new StringTokenizer(str);

    int ch = Integer.parseInt(st.nextToken());
    int num = Integer.parseInt(st.nextToken());

    MyMath ob = new MyMath();

    PerformOperation op;
    boolean ret;
    String ans = null;

    if (ch == 1) {
      op = ob.isOdd();
      ret = ob.checker(op, num);
      ans = (ret) ? "ODD" : "EVEN";
    } else if (ch == 2) {
      op = ob.isPrime();
      ret = ob.checker(op, num);
      ans = (ret) ? "PRIME" : "COMPOSITE";
    } else if (ch == 3) {
      op = ob.isPalindrome();
      ret = ob.checker(op, num);
      ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
    }

    return ans;
  }

  interface PerformOperation {
    boolean check(int a);
  }

  class MyMath {
    public boolean checker(PerformOperation p, int num) {
      return p.check(num);
    }

    /** @return */
    public PerformOperation isPalindrome() {
      PerformOperation perform =
          (value) -> {
            String palindromeString = Integer.toString(value);

            boolean isPalindrome = true;

            int lengthOf = palindromeString.length();
            int middlePoint = (lengthOf & 1) == 1 ? lengthOf / 2 : (lengthOf + 1) / 2;

            for (int i = lengthOf - 1; i >= middlePoint; i--) {
              if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1)) {
                isPalindrome = false;
                break;
              }
            }

            return isPalindrome;
          };

      return perform;
    }

    /** @return */
    public PerformOperation isPrime() {
      PerformOperation perform =
          (value) -> {
            boolean flag = true;
            for (int i = 2; i <= value / 2; ++i) {

              if (value % i == 0) {
                flag = false;
                break;
              }
            }

            return flag;
          };

      return perform;
    }

    /** @return */
    public PerformOperation isOdd() {
      PerformOperation perform = (value) -> (value & 1) != 0;

      return perform;
    }
  }

  @Test
  public void javaBigDecimal() {
    String[] array =
        new String[] {"-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000"};
    javaBigDecimal(array);

    assertArrayEquals(
        new String[] {"90", "56.6", "50", "02.34", "0.12", ".12", "0", "000.000", "-100"}, array);
  }

  private void javaBigDecimal(String[] s) {
    Map<Integer, BigDecimal> map = new HashMap<>();
    for (int i = 0; i < s.length; i++) map.put(i, new BigDecimal(s[i]));

    List<Map.Entry<Integer, BigDecimal>> list = new LinkedList<>(map.entrySet());

    Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

    Map<Integer, BigDecimal> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<Integer, BigDecimal> entry : list)
      sortedMap.put(entry.getKey(), entry.getValue());

    int k = 0;

    String[] solution = new String[s.length];

    for (Map.Entry<Integer, BigDecimal> entry : sortedMap.entrySet()) {

      solution[k] = s[entry.getKey()];
      k++;
    }

    for (int i = 0; i < solution.length; i++) s[i] = solution[i];
  }

  @Test
  public void javaDeque() throws IOException {
    assertEquals(3, javaDeque("5 3 5 2 3 2", 3));

    assertEquals(2, javaDeque("1 1 1 1 1 1 1 2", 3));

    assertEquals(8, javaDeque("1 2 3 4 5 6 7 8", 8));

    InputStream is = new FileInputStream("src/test/resources/deque/javadeque.txt");
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
    buf.readLine();

    assertEquals(94983, javaDeque(buf.readLine(), 99877));

    buf.close();
  }

  private int javaDeque(String array, int m) {
    Deque<Integer> deque = new ArrayDeque<>();
    String[] split = array.split("\\s");
    int n = split.length;
    HashSet<Integer> set = new HashSet<>();
    int max = 0;

    for (String s : split) {
      deque.add(Integer.valueOf(s));
      set.add(Integer.valueOf(s));

      if (deque.size() == m) {
        if (set.size() > max) max = set.size();

        int first = deque.remove();
        if (!deque.contains(first)) set.remove(first);
      }
    }

    return max;
  }

  @Test
  public void javaComparator() {
    int n = 5;

    Player[] player = new Player[n];
    Checker checker = new Checker();

    player[0] = new Player("amy", 100);
    player[1] = new Player("david", 100);
    player[2] = new Player("heraldo", 50);
    player[3] = new Player("aakansha", 75);
    player[4] = new Player("aleksa", 150);

    javaComparator(player, checker);

    assertTrue(
        Arrays.equals(
            new Player[] {
              new Player("aleksa", 150),
              new Player("amy", 100),
              new Player("david", 100),
              new Player("aakansha", 75),
              new Player("heraldo", 50)
            },
            player));
  }

  public void javaComparator(Player[] player, Checker checker) {
    Arrays.sort(player, checker);
  }

  class Checker implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {

      int a = o1.score - o2.score;
      int b = o1.name.compareTo(o2.name);

      if (a == 0) return b < 0 ? -1 : 1;

      if (a > 0) return -1;

      return 1;
    }
  }

  class Player {
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + score;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;

      Player other = (Player) obj;
      if (name == null) {
        if (other.name != null) return false;
      } else if (!name.equals(other.name)) return false;
      if (score != other.score) return false;
      return true;
    }

    String name;
    int score;

    public Player(String name, int score) {
      this.name = name;
      this.score = score;
    }
  }

  @SuppressWarnings("serial")
  @Test
  public void javaArrayList() {
    javaArrayList(
        new ArrayList<List<Integer>>() {
          {
            add(Arrays.asList(5, 41, 77, 74, 22, 44));
            add(Arrays.asList(1, 12));
            add(Arrays.asList(4, 37, 34, 36, 52));
            add(Arrays.asList(0));
            add(Arrays.asList(3, 20, 22, 33));
          }
        },
        new ArrayList<String>() {
          {
            add("1 3");
            add("3 4");
            add("3 1");
            add("4 3");
            add("5 5");
          }
        });

    String out = outContent.toString();

    String carriageReturn = getCarriageReturn();

    assertEquals(
        out,
        "74"
            + carriageReturn
            + "52"
            + carriageReturn
            + "37"
            + carriageReturn
            + "ERROR!"
            + carriageReturn
            + "ERROR!"
            + carriageReturn);
  }

  private void javaArrayList(List<List<Integer>> list, List<String> queries) {
    for (String s : queries) {
      String[] query = s.split("\\s");
      int x = Integer.parseInt(query[0]);
      int size = list.size();

      if (x > size) System.out.println("ERROR!");
      else {

        List<Integer> l = list.get(x - 1);

        int position = Integer.parseInt(query[1]);
        size = l.size();

        if (null != l && position >= size) System.out.println("ERROR!");
        else System.out.println(l.get(position));
      }
    }
  }

  @Test
  public void javaVisitorPattern() throws IOException {

    Solution solution = new Solution();
    Tree root = solution.solve();

    SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
    ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
    FancyVisitor vis3 = new FancyVisitor();

    root.accept(vis1);
    root.accept(vis2);
    root.accept(vis3);

    assertEquals(24, vis1.getResult());
    assertEquals(40, vis2.getResult());
    assertEquals(15, vis3.getResult());
  }

  enum Color {
    RED,
    GREEN
  }

  abstract class Tree {

    private final int value;
    private final Color color;
    private final int depth;

    public Tree(int value, Color color, int depth) {
      this.value = value;
      this.color = color;
      this.depth = depth;
    }

    public int getValue() {
      return value;
    }

    public Color getColor() {
      return color;
    }

    public int getDepth() {
      return depth;
    }

    public abstract void accept(TreeVis visitor);
  }

  class TreeNode extends Tree {

    private final ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
      super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
      visitor.visitNode(this);

      for (Tree child : children) {
        child.accept(visitor);
      }
    }

    public void addChild(Tree child) {
      children.add(child);
    }
  }

  class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
      super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
      visitor.visitLeaf(this);
    }
  }

  abstract class TreeVis {

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);
  }

  class SumInLeavesVisitor extends TreeVis {

    private int result = 0;

    private int getResult() {
      return result;
    }

    public void visitNode(TreeNode node) {}

    public void visitLeaf(TreeLeaf leaf) {
      result += leaf.getValue();
    }
  }

  class ProductOfRedNodesVisitor extends TreeVis {

    private long result = 1;
    private final int M = 1000000007;

    private int getResult() {
      return (int) result;
    }

    public void visitNode(TreeNode node) {
      if (node.getColor().equals(Color.RED)) {
        result = (result * node.getValue()) % M;
      }
    }

    public void visitLeaf(TreeLeaf leaf) {
      if (leaf.getColor().equals(Color.RED)) {
        result = (result * leaf.getValue()) % M;
      }
    }
  }

  class FancyVisitor extends TreeVis {

    private int evenDepthNonLeavesSum = 0;
    private int greenLeavesSum = 0;

    private int getResult() {
      return Math.abs(evenDepthNonLeavesSum - greenLeavesSum);
    }

    public void visitNode(TreeNode node) {
      if (node.getDepth() % 2 == 0) evenDepthNonLeavesSum += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
      if (leaf.getColor().equals(Color.GREEN)) greenLeavesSum += leaf.getValue();
    }
  }

  class Solution {

    int N;
    HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    int[] values;
    int[] colors;
    boolean[] mark;

    public Tree dfs(int vertex) {
      if (map.get(vertex).isEmpty())
        return new TreeLeaf(values[vertex], Color.values()[colors[vertex]], 0);
      else {
        mark = new boolean[N];
        return runDfs(vertex, 0);
      }
    }

    public Tree runDfs(int vertex, int depth) {
      mark[vertex] = true;
      ArrayList<Tree> childs = new ArrayList<>();
      for (Integer e : map.get(vertex)) if (!mark[e]) childs.add(runDfs(e, depth + 1));
      if (childs.isEmpty())
        return new TreeLeaf(values[vertex], Color.values()[colors[vertex]], depth);
      else {
        TreeNode node = new TreeNode(values[vertex], Color.values()[colors[vertex]], depth);
        for (Tree child : childs) node.addChild(child);
        return node;
      }
    }

    public Tree solve() throws IOException {
      try (BufferedReader br =
          new BufferedReader(new FileReader("src/test/resources/visitor/tree.txt"))) {

        N = Integer.parseInt(br.readLine());
        values = new int[N];
        colors = new int[N];
        int parent, child;
        String[] chunks = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
          map.put(i, new HashSet<>());
          values[i] = Integer.parseInt(chunks[i]);
        }
        chunks = br.readLine().split(" ");
        for (int i = 0; i < N; i++) colors[i] = Integer.parseInt(chunks[i]);
        for (int i = 0, length = N - 1; i < length; i++) {
          chunks = br.readLine().split(" ");
          parent = Integer.parseInt(chunks[0]) - 1;
          child = Integer.parseInt(chunks[1]) - 1;

          map.get(parent).add(child);
          map.get(child).add(parent);
        }
        return dfs(0);
      }
    }
  }

  @Test
  public void calculatePower() {
    MyCalculator calculator = new MyCalculator();

    assertEquals("243", calculator.power(3, 5));
    assertEquals("16", calculator.power(2, 4));
    assertEquals("java.lang.Exception: n and p should not be zero.", calculator.power(0, 0));
    assertEquals("java.lang.Exception: n or p should not be negative.", calculator.power(-1, -2));
  }

  class MyCalculator {
    /*
     * Create the method long power(int, int) here.
     */
    public String power(int n, int p) {
      if (n == 0 && p == 0) return "java.lang.Exception: n and p should not be zero.";

      if (n < 0 || p < 0) return "java.lang.Exception: n or p should not be negative.";

      return calculatePower((long) n, (long) p).toString();
    }

    private Long calculatePower(long n, long p) {
      if (p == 0) return 1L;

      return n * calculatePower(n, p - 1L);
    }
  }

  @Test
  public void tagContetExtractor() {

    assertEquals(
        Arrays.asList(new String[] {"Nayeem loves counseling"}),
        tagContetExtractor("<h1>Nayeem loves counseling</h1>"));
    assertEquals(
        Arrays.asList(new String[] {"Sanjay has no watch"}),
        tagContetExtractor("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>"));
    assertEquals(
        Arrays.asList(new String[] {}),
        tagContetExtractor("<Amee>safat codes like a ninja</amee>"));
    assertEquals(
        Arrays.asList(new String[] {"Imtiaz has a secret crash"}),
        tagContetExtractor("<SA premium>Imtiaz has a secret crash</SA premium>"));
    assertEquals(
        Arrays.asList(new String[] {}), tagContetExtractor("<h1>had<h1>public</h1515></h1>"));
  }

  /** @param s */
  private List<String> tagContetExtractor(String s) {
    if (s.isEmpty()) return new ArrayList<>();

    List<String> list = new ArrayList<>();

    Pattern pattern = Pattern.compile("<(.+?)>", Pattern.DOTALL);
    Matcher m = pattern.matcher(s);

    int start = 0, end = 0;

    Stack<String> stack = new Stack<>();

    while (m.find()) {
      String match = m.group();

      if (!match.contains("/")) {
        stack.push(match);

        start = m.start();
        end = m.end();
        continue;
      }

      if (!stack.isEmpty() && match.contains("/")) {
        String peek = stack.pop();

        if (match.equals(peek.replace("<", "</"))) {
          String tag = s.substring(start, end).replace("<", "</");
          int index = s.indexOf(tag);

          String toPrint = s.substring(end, index);

          if (!toPrint.isEmpty()) list.add(toPrint);
        }

        stack.clear();
      }
    }

    return list;
  }

  @ParameterizedTest
  @CsvSource({
    "src/test/resources/regularExpression/username.txt,src/test/resources/regularExpression/usernameValidation.txt"
  })
  public void usernameValidator(String username, String validation) throws IOException {

    InputStream is = new FileInputStream(username);
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));

    InputStream is1 = new FileInputStream(validation);
    BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));

    String line = buf.readLine();
    String line1 = buf1.readLine();

    while (line != null && line1 != null) {

      assertEquals(!line1.equals("Invalid"), line.matches(UsernameValidator.regularExpression));

      line = buf.readLine();

      line1 = buf1.readLine();
    }

    buf.close();
    buf1.close();
  }

  class UsernameValidator {
    public static final String regularExpression = "^[a-zA-Z][a-zA-Z_0-9]{7,29}$";
  }

  @Test
  public void getSmallestAndLargest() {
    String carriageReturn = getCarriageReturn();

    assertEquals("ava" + carriageReturn + "wel", getSmallestAndLargest("welcometojava", 3));
  }

  private String getSmallestAndLargest(String s, int k) {
    String smallest = s;
    String largest = "";

    int length = s.length();
    int i = 0;

    while (i + k <= length) {
      String tmp = s.substring(i, i + k);
      smallest = smallest.compareTo(tmp) > 0 ? tmp : smallest;
      largest = largest.compareTo(tmp) < 0 ? tmp : largest;

      i++;
    }

    return smallest + getCarriageReturn() + largest;
  }

  @Test
  public void tokens() {
    assertTrue(
        Arrays.equals(
            new String[] {"He", "is", "a", "very", "very", "good", "boy", "isn", "t", "he"},
            tokens("He is a very very good boy, isn't he?")));

    assertTrue(
        Arrays.equals(
            new String[] {
              "YES", "leading", "spaces", "are", "valid", "problemsetters", "are", "evillllll"
            },
            tokens(
                "           YES      leading spaces        are valid,    problemsetters are         evillllll")));

    assertTrue(Arrays.equals(new String[] {}, tokens("                        ")));
  }

  private String[] tokens(String s) {
    String trimmed = s.trim();

    if (trimmed.length() == 0) return new String[] {};

    return trimmed.split("[\\s*\\!\\,\\?\\.\\_\\'\\@]+");
  }
}
