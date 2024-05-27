package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitStackTest {

  @Test
  public void braces() {
    assertEquals(0, braces("(a + (a + b))"));
    assertEquals(1, braces("((a + b))"));
  }

  public int braces(String A) {
    Stack<Character> stack = new Stack<>();
    Set<Character> ops = new HashSet<>(Arrays.asList('+', '*', '/', '-'));

    for (char i : A.toCharArray()) {
      if (i == '(') stack.push(i);
      if (ops.contains(i) && !stack.isEmpty()) stack.pop();
    }

    return stack.isEmpty() ? 0 : 1;
  }

  @Test
  public void reverseString() {
    assertEquals("cba", reverseString("abc"));
  }

  private String reverseString(String A) {
    Stack<Character> stack = new Stack<>();

    for (Character c : A.toCharArray()) {
      stack.push(c);
    }

    StringBuilder sol = new StringBuilder();

    while (!stack.isEmpty()) {
      sol.append(stack.pop());
    }

    return sol.toString();
  }

  @Test
  public void simplifyPath() {
    assertEquals("/home", simplifyPath("/home/"));
    assertEquals("/c", simplifyPath("/a/./b/../../c/"));
  }

  private String simplifyPath(String A) {

    if (A == null || A.length() == 0) return null;

    String[] arr = ((String) A.subSequence(1, A.length())).split("[/]");

    Deque<String> stack = new LinkedList<>();

    for (String s : arr) {
      if (s.equals("")) {
        if (!stack.isEmpty()) stack.pop();
      } else if (s.matches("[a-zA-Z]+")) {
        stack.push("/" + s);
      }
    }

    StringBuilder sb = new StringBuilder();
    if (stack.isEmpty()) return "/";

    while (!stack.isEmpty()) sb.append(stack.removeLast());

    return sb.toString();
  }

  @Test
  public void prevSmaller() {
    assertArrayEquals(new int[] {-1, 4, -1, 2, 2}, prevSmaller(new int[] {4, 5, 2, 10, 8}));
    assertArrayEquals(
        new int[] {-1, 34, -1, 27, -1, 5, 28, 5, 20},
        prevSmaller(new int[] {34, 35, 27, 42, 5, 28, 39, 20, 28}));
    assertArrayEquals(
        new int[] {-1, -1, -1, -1, 4, 24, 24, -1},
        prevSmaller(new int[] {39, 27, 11, 4, 24, 32, 32, 1}));
  }

  private int[] prevSmaller(int[] A) {
    int[] sol = new int[A.length];

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < A.length; i++) {

      while (!stack.isEmpty() && stack.peek() >= A[i]) stack.pop();

      sol[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(A[i]);
    }

    return sol;
  }

  @Test
  public void evalRPN() {
    assertEquals(9, evalRPN(Arrays.asList("2", "1", "+", "3", "*")));
  }

  public int evalRPN(List<String> list) {
    Stack<String> stack = new Stack<>();
    for (String s : list)
      if (s.matches("\\*|/|\\+|-|") && !stack.empty()) {
        int ele2 = Integer.parseInt(stack.pop());
        int ele1 = Integer.parseInt(stack.pop());
        int ans;

        switch (s) {
          case "+":
            ans = ele1 + ele2;
            break;
          case "-":
            ans = ele1 - ele2;
            break;
          case "*":
            ans = ele1 * ele2;
            break;
          default:
            ans = ele1 / ele2;
            break;
        }

        stack.push(Integer.toString(ans));
      } else {
        stack.add(s);
      }

    return Integer.parseInt(stack.peek());
  }

  @Test
  public void maxSpecialProduct() {
    assertEquals(0, maxSpecialProduct(new int[] {10, 7}));
    assertEquals(0, maxSpecialProduct(new int[] {10, 7, 100}));
    assertEquals(3, maxSpecialProduct(new int[] {1, 4, 3, 4}));
    assertEquals(8, maxSpecialProduct(new int[] {1, 4, 4, 3, 4}));
    assertEquals(3, maxSpecialProduct(new int[] {5, 4, 3, 6}));
    assertEquals(0, maxSpecialProduct(new int[] {1, 2, 3, 4}));
  }

  public int maxSpecialProduct(int[] A) {

    int n = A.length;

    // Nearest Greater To Left
    int[] nll = new int[n];

    // Nearest Greater To Right
    int[] nlr = new int[n];

    Stack<Integer> st = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && A[st.peek()] < A[i]) nll[st.pop()] = i;

      st.push(i);
    }

    while (st.isEmpty()) nll[st.pop()] = -1;

    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && A[st.peek()] < A[i]) nlr[st.pop()] = i;

      st.push(i);
    }

    while (st.isEmpty()) nlr[st.pop()] = n;

    long ans = -1;
    int mod = (int) (1e9 + 7);

    for (int i = 0; i < n; i++) ans = Math.max(ans, (long) nll[i] * nlr[i]);

    return (int) (ans % mod);
  }

  @Test
  public void balancedParenthesis() {
    assertEquals(1, balancedParenthesis("(()())"));
    assertEquals(0, balancedParenthesis("(()"));
  }

  private int balancedParenthesis(String A) {

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < A.length(); i++) {

      if (!stack.isEmpty() && stack.peek() == '(' && A.charAt(i) == ')') {
        stack.pop();
        continue;
      }

      stack.push(A.charAt(i));
    }

    return stack.isEmpty() ? 1 : 0;
  }

  @Test
  public void firstNonRepeatingChar() {
    assertEquals("aabbdd", firstNonRepeatingChar("abadbc"));
    assertEquals("a#", firstNonRepeatingChar("aa"));
    assertEquals("a#b", firstNonRepeatingChar("aab"));
    assertEquals("aaabc#", firstNonRepeatingChar("abcabc"));
    assertEquals("ppt", firstNonRepeatingChar("ptp"));
    assertEquals(
        "x#iiiiiiiiiiiiiiiiwwwwwwwwwwwwwwwwwwwwww",
        firstNonRepeatingChar("xxikrwmjvsvckfrqxnibkcasompsuyuogauacjrr"));
    assertEquals(
        "jjjjjjjjjjjjjjjjjjjjjyyyyyyyyyyyyyyyyyyyyyyyyyyyqqqqq",
        firstNonRepeatingChar("jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasluuwooupcyxwgl"));
  }

  private String firstNonRepeatingChar(String s) {

    Queue<Character> queue = new LinkedList<>();

    int[] chars = new int[26];

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      ++chars[s.charAt(i) - 'a'];
      queue.add(s.charAt(i));

      while (!queue.isEmpty() && chars[queue.peek() - 'a'] > 1) queue.poll();

      builder.append(queue.isEmpty() ? '#' : queue.peek());
    }

    return builder.toString();
  }

  @Test
  public void nearestHotel() {
    assertArrayEquals(
        new int[] {1, 0, 2},
        nearestHotel(new int[][] {{0, 0}, {1, 0}}, new int[][] {{1, 1}, {2, 1}, {1, 2}}));
    assertArrayEquals(
        new int[] {1, 1}, nearestHotel(new int[][] {{1, 0, 0, 1}}, new int[][] {{1, 2}, {1, 3}}));
    assertArrayEquals(
        new int[] {1, 1}, nearestHotel(new int[][] {{1, 0},{0, 0},{0, 1}}, new int[][] {{1, 2}, {1, 3}}));
  }

  public class Pair {

    private final int key;
    private final int value;

    public int getKey() {
      return key;
    }

    public Pair(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public int[] nearestHotel(int[][] a, int[][] b) {
    int length = a[0].length;
    int height = a.length;
    int[][] adj = new int[height + 1][length + 1];
    boolean[][] vis = new boolean[height + 1][length + 1];
    Queue<Pair> q = new LinkedList<>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < length; j++) {
        if (a[i][j] == 1) {
          vis[i][j] = true;
          q.add(new Pair(i, j));
        }
      }
    }

    // Check the adjacent positions of the ones we already marked as visited and with an hotel.
    // If we find a place we have not visited and inside the grid, we add one step to the adjacent
    // position so we sum up the distance
    while (!q.isEmpty()) {
      Pair p = q.poll();

      int x = p.getKey();
      int j = p.getValue();

      // right and left
      if (x + 1 < height) {
        if (!vis[x + 1][j]) {
          adj[x + 1][j] = 1 + adj[x][j];
          q.add(new Pair(x + 1, j));
          vis[x + 1][j] = true;
        }
      }
      if (x - 1 >= 0) {
        if (!vis[x - 1][j]) {
          adj[x - 1][j] = 1 + adj[x][j];
          q.add(new Pair(x - 1, j));
          vis[x - 1][j] = true;
        }
      }
      // down and up
      if (j + 1 < length) {
        if (!vis[x][j + 1]) {
          adj[x][j + 1] = 1 + adj[x][j];
          q.add(new Pair(x, j + 1));
          vis[x][j + 1] = true;
        }
      }
      if (j - 1 >= 0) {
        if (!vis[x][j - 1]) {
          adj[x][j - 1] = 1 + adj[x][j];
          q.add(new Pair(x, j - 1));
          vis[x][j - 1] = true;
        }
      }
    }

    // We should now have a map of the distances from the nearest hotel given the 1's we marked in
    // the first place plus the iteration over the surrounding distances
    int[] v = new int[b.length];
    int row = b.length;
    for (int l = 0; l < row; l++) {
      int i = b[l][0] - 1;
      int j = b[l][1] - 1;
      v[l] = (adj[i][j]);
    }

    return v;
  }
}
