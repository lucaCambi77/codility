package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.*;

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

  private String firstNonRepeatingChar(String A) {

    Queue<Character> queue = new LinkedList<>();

    String alphaBet = "abcdefghijklmnopqrstuvwxyz";
    int[] chars = new int[26];

    StringBuilder s = new StringBuilder();

    for (int i = 0; i < A.length(); i++) {
      ++chars[alphaBet.indexOf(A.charAt(i))];
      queue.add(A.charAt(i));

      while (!queue.isEmpty() && chars[alphaBet.indexOf(queue.peek())] > 1) queue.poll();

      s.append(queue.isEmpty() ? '#' : queue.peek());
    }

    return s.toString();
  }
}
