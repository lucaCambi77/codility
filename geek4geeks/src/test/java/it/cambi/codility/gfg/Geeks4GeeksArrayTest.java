/** */
package it.cambi.codility.gfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/**
 * @author luca
 */
class Geeks4GeeksArrayTest {

  @Test
  void reverseArrayInGroup() {
    reverseArrayInGroup("1 2 3 4 5", 3, 5);
  }

  void reverseArrayInGroup(String array, int k, int n) {
    int[] arr = new int[n];

    String[] inputLine = array.split("\\s");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(inputLine[i]);
    }

    reverse(arr, n, k);

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < n; i++) {
      sb.append(arr[i]).append(" ");
    }
    System.out.println(sb);
  }

  static void reverse(int[] arr, int n, int k) {
    for (int i = 0; i < n; i += k) {
      int left = i;

      // to handle case when k is not multiple
      // of n
      int right = Math.min(i + k - 1, n - 1);
      int temp;

      // reverse the sub-array [left, right]
      while (left < right) {
        temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        left += 1;
        right -= 1;
      }
    }
  }

  @Test
  void leaderInArray() {
    assertEquals("17 5 2 ", leaderInArray("16 17 4 3 5 2", 6));
    assertEquals("4 0 ", leaderInArray("1 2 3 4 0", 5));
    assertEquals("7 7 3 ", leaderInArray("7 4 5 7 3", 5));
    assertEquals("7 ", leaderInArray("5 4 5 3 7", 5));
  }

  private String leaderInArray(String array, int n) {
    int[] arr = new int[n];
    String[] inputLine = array.split("\\s");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(inputLine[i]);
    }
    int maxEle = arr[n - 1];
    StringBuilder str = new StringBuilder();
    ArrayList<Integer> res = new ArrayList<>();
    for (int i = n - 1; i >= 0; i--) {
      if (arr[i] >= maxEle) {
        maxEle = arr[i];
        res.add(maxEle);
      }
    }
    for (int i = res.size() - 1; i >= 0; i--) {
      str.append(res.get(i)).append(" ");
    }

    return str.toString();
  }

  @SuppressWarnings("serial")
  @Test
  void find3Numbers() {
    assertEquals(Arrays.asList(1, 2, 3), find3Numbers(new int[] {1, 2, 1, 1, 3}, 5));
    assertEquals(new ArrayList<Integer>(), find3Numbers(new int[] {1, 1, 3}, 3));
    assertEquals(new ArrayList<Integer>(), find3Numbers(new int[] {5, 6, 1}, 3));
  }

  private ArrayList<Integer> find3Numbers(int[] a, int n) {
    ArrayList<Integer> list = new ArrayList<>();

    int index = 0, first = 0, count = 0;
    int search = a[0];
    list.add(0, search);

    while (count < 2 && index < n - 1) {

      int indexOpt = getIndex(a, index, search);

      if (indexOpt > -1) {
        index = indexOpt;
        search = a[indexOpt];
        list.add(++count, search);
      } else {
        list = new ArrayList<>();
        index = ++first;
        first = index;
        search = a[index];
        count = 0;
        list.add(count, search);
      }
    }

    if (count == 2) return list;
    else return new ArrayList<>();
  }

  private Integer getIndex(int[] a, int index, int search) {
    for (int j = index + 1; j < a.length; j++) {
      if (a[j] > search) return j;
    }

    return -1;
  }

  @Test
  void segregateEvenOdd() {
    assertEquals(
        "8 12 34 90 3 9 45",
        segregateEvenOdd(7, new String[] {"12", "34", "45", "9", "8", "90", "3"}));
  }

  private String segregateEvenOdd(int n, String[] input) {
    List<Integer> setOdd = new ArrayList<>();
    List<Integer> setEven = new ArrayList<>();

    while (--n > -1) {
      int value = Integer.parseInt(input[n]);

      if ((value & 1) == 0) setEven.add(value);
      else setOdd.add(value);
    }

    return setEven.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "))
        + " "
        + setOdd.stream().sorted().map(String::valueOf).collect(Collectors.joining(" "));
  }

  @Test
  void sortBinaryArray() {
    assertEquals("0 0 1 1 1", sortBinaryArray(5, new String[] {"1", "0", "1", "1", "0"}));
    assertEquals(
        "0 0 0 0 1 1 1 1 1 1",
        sortBinaryArray(10, new String[] {"1", "0", "1", "1", "1", "1", "1", "0", "0", "0"}));
  }

  private String sortBinaryArray(int n, String[] values) {
    int countZero = 0;
    int countOne = 0;

    while (--n > -1) {
      switch (values[n]) {
        case "0":
          countZero++;
          break;

        case "1":
          countOne++;
          break;

        default:
          break;
      }
    }

    StringBuilder builder = new StringBuilder();

    if (countZero > 0)
      builder.append(String.join(" ", Collections.nCopies(countZero, "0"))).append(" ");

    builder.append(String.join(" ", Collections.nCopies(countOne, "1")));

    return builder.toString();
  }

  @Test
  void checkTwoArraysEquals() {
    assertTrue(checkTwoArraysEquals(Arrays.asList(1, 2, 4, 5, 0), Arrays.asList(0, 5, 4, 2, 1)));
    assertFalse(checkTwoArraysEquals(Arrays.asList(1, 4, 5, 0), Arrays.asList(0, 5, 4, 2, 1)));
  }

  private boolean checkTwoArraysEquals(List<Integer> list, List<Integer> list1) {

    Collections.sort(list);
    Collections.sort(list1);

    return Arrays.equals(list.toArray(), list1.toArray());
  }

  @Test
  void repeatChar() {
    assertEquals("b", repeatChar("bbccdefbbaa"));
    assertEquals("g", repeatChar("geeksforgeeks"));
    assertEquals("-1", repeatChar("card"));
  }

  private String repeatChar(String str) {
    char[] count = new char[256];

    int i;

    for (i = 0; i < str.length(); i++) count[Character.toLowerCase(str.charAt(i))]++;

    for (i = 0; i < str.length(); i++) if (count[Character.toLowerCase(str.charAt(i))] > 1) break;

    return i == str.length() ? "-1" : String.valueOf(str.charAt(i));
  }

  @Test
  void sort() {
    assertEquals("aabbbbccdef", sort("bbccdefbbaa"));
    assertEquals("eeeefggkkorss", sort("geeksforgeeks"));
  }

  private String sort(String str) {

    char[] count = new char[256];

    int i;

    for (i = 0; i < str.length(); i++) count[Character.toLowerCase(str.charAt(i))]++;

    StringBuilder builder = new StringBuilder();

    for (i = 0; i < count.length; i++) {
      int freq = count[i];

      if (freq > 0)
        builder.append(String.join("", Collections.nCopies(freq, Character.toString(i))));
    }

    return builder.toString();
  }

  @Test
  void multiplyLeftRightSum() {

    assertEquals(
        21,
        multiplyLeftRightSum(
            Arrays.stream("1 2 3 4".split(" ")).mapToInt(Integer::valueOf).toArray()));
    assertEquals(44, multiplyLeftRightSum(new int[] {4, 5, 6}));
  }

  private int multiplyLeftRightSum(int[] array) {

    int lengthOf = array.length;
    int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

    int sumLeft = 0;
    int sumRight = 0;

    for (int i = lengthOf - 1, j = 0; i >= middlePoint; i--, j++) {

      if (j < middlePoint) sumLeft += array[j];

      sumRight += array[i];
    }

    return sumLeft * sumRight;
  }

  @Test
  void num() {
    assertEquals(6, num(new int[] {11, 12, 13, 14, 15}, 5, 1));
    assertEquals(4, num(new int[] {0, 10, 20, 30}, 4, 0));
  }

  private long num(int a[], int n, int k) {
    long count = 0L;
    char match = Integer.toString(k).charAt(0);

    for (int j = 0; j < n; j++) {

      String s = Integer.toString(a[j]);

      count = count + s.chars().filter(ch -> ch == match).count();
    }

    return (int) count;
  }

  @Test
  void sortEmployee() {
    assertEquals("geek 50 xbnnskd 100", sortEmployee("xbnnskd 100 geek 50"));
    assertEquals("ram 50 shyam 50", sortEmployee("shyam 50 ram 50"));
    assertEquals("ram 50 shyam 50 xbnnskd 100", sortEmployee("xbnnskd 100 shyam 50 ram 50"));
  }

  private String sortEmployee(String s) {

    String[] split = s.split("(?<!\\G\\S+)\\s");

    Arrays.sort(
        split,
        (lhs, rhs) -> {
          String[] splitLeft = lhs.split(" ");
          String[] splitRight = rhs.split(" ");

          int a = splitLeft[1].compareTo(splitRight[1]);
          int b = splitLeft[0].compareTo(splitRight[0]);

          if (a == 0) return b < 0 ? -1 : 1;

          if (a > 0) return -1;

          return 1;
        });

    return String.join(" ", split);
  }

  @Test
  void checkIfFreqCanBeEqual() {

    assertFalse(checkIfFreqCanBeEqual("xxxxyyzz"));
    assertTrue(checkIfFreqCanBeEqual("xyyz"));
    assertTrue(checkIfFreqCanBeEqual("ehuuroaidj"));
    assertTrue(checkIfFreqCanBeEqual("cceea"));
    assertFalse(checkIfFreqCanBeEqual("evjxpnqgmvfjl"));
  }

  private boolean checkIfFreqCanBeEqual(String s1) {

    LinkedList<Long> list =
        s1
            .chars()
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values()
            .stream()
            .sorted()
            .collect(Collectors.toCollection(LinkedList::new));

    long first = list.get(0);
    long last = list.get(list.size() - 1);

    /** Diff greater than one */
    if (last - first > 1) return false;

    /** All values has same frequency */
    if (last - first == 0) return true;

    int firstFrequency = Collections.frequency(list, first);
    int lastFrequency = Collections.frequency(list, last);

    if (last - first == 1)
      if ((firstFrequency == 1 && lastFrequency == list.size() - 1)
          || (lastFrequency == 1 && firstFrequency == list.size() - 1)) return true;

    return false;
  }

  @Test
  void permutationsOfAGivenString() {

    String s = "ABC";

    assertEquals(
        "ABC ACB BAC BCA CAB CBA",
        permuteString(s, 0, s.length() - 1, new ArrayList<>()).stream()
            .sorted()
            .collect(Collectors.joining(" ")));
    assertEquals(
        "ABC ACB BAC BCA CAB CBA",
        permutations(s).stream().sorted().collect(Collectors.joining(" ")));
    assertEquals(
        "aabb abab abba baab baba bbaa",
        permutations("aabb").stream().sorted().collect(Collectors.joining(" ")));
  }

  private List<String> permuteString(String str, int l, int r, List<String> set) {

    if (l == r) {
      set.add(str);
    } else {
      for (int i = l; i <= r; i++) {
        str = swap(str, l, i);
        permuteString(str, l + 1, r, set);
        str = swap(str, l, i);
      }
    }

    return set;
  }

  private String swap(String a, int i, int j) {
    char temp;
    char[] charArray = a.toCharArray();
    temp = charArray[i];
    charArray[i] = charArray[j];
    charArray[j] = temp;
    return String.valueOf(charArray);
  }

  private Set<String> permutations(String s) {
    Set<String> permutations = new HashSet<>();

    if (s.length() == 1) {
      permutations.add(s);
      return permutations;
    }

    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);

      String remaining = s.substring(0, i) + s.substring(i + 1);
      for (String perm : permutations(remaining)) {
        permutations.add(currentChar + perm);
      }
    }
    return permutations;
  }

  @Test
  void reverseWordsInAGivenString() {

    assertEquals(
        "much.very.program.this.like.i",
        reverseWordsInAGivenString("i.like.this.program.very.much"));
  }

  String reverseWordsInAGivenString(String s) {

    String[] split = s.split("\\.");

    return IntStream.range(0, split.length)
        .mapToObj(i -> split[split.length - 1 - i])
        .collect(Collectors.joining("."));
  }

  @Test
  void reverseStringUsingStack() {
    assertEquals("ziuQskeeG", reverseStringUsingStack("GeeksQuiz"));
  }

  String reverseStringUsingStack(String str) {

    Stack<Character> stack = new Stack<>();

    str.chars().forEach(c -> stack.push((char) c));

    StringBuilder strBuild = new StringBuilder();

    while (!stack.isEmpty()) strBuild.append(stack.pop());

    return strBuild.toString();
  }

  @Test
  void reverseFirstKElementsOfQueue() {

    assertEquals(
        Arrays.asList(2, 1, 3, 4, 5), reverseFirstKElementsOfQueue(new int[] {3, 1, 2, 4, 5}, 3));
  }

  Deque<Integer> reverseFirstKElementsOfQueue(int[] array, int k) {

    Deque<Integer> q = new LinkedList<>();
    for (int i : array) q.add(i);

    int size = q.size();
    int[] toReverseArray = new int[size];

    Deque<Integer> queueOut = new LinkedList<>();

    int i = 0;
    while (!q.isEmpty()) {

      if (i > k - 1) {
        toReverseArray[i] = q.poll();
      } else {
        toReverseArray[k - i - 1] = q.poll();
      }
      i++;
    }

    for (i = 0; i < size; i++) {
      queueOut.add(toReverseArray[i]);
    }

    return queueOut;
  }

  @Test
  void stackOperations() {

    Stack<Integer> stack = new Stack<>();

    stack.push(2);
    stack.push(4);
    stack.push(3);
    stack.push(5);

    assertEquals(5, stack.peek());

    assertFalse(stack.contains(8));
  }

  @Test
  void operationsOnPriorityQueue() {

    PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    queue.add(5);
    queue.add(2);
    queue.add(3);
    queue.add(1);

    assertTrue(queue.contains(5));

    assertEquals(1, queue.poll());
  }

  @Test
  void queueOperations() {

    Queue<Integer> queue = new LinkedList<>();

    queue.add(1);
    queue.add(2);
    queue.add(3);
    queue.add(4);
    queue.add(5);
    queue.add(2);
    queue.add(3);
    queue.add(1);

    assertEquals(2, Collections.frequency(queue, 1));
  }

  @Test
  void arrayListOperation() {

    List<Character> cList = new ArrayList<>();

    cList.add('g');
    cList.add('e');
    cList.add('e');
    cList.add('k');
    cList.add('s');
    cList.add('c');
    cList.add('p');
    cList.add('p');

    assertEquals(2, Collections.frequency(cList, 'p'));
    assertEquals(0, Collections.frequency(cList, 'f'));
  }

  @Test
  void operationsOnArrayList() {

    List<Integer> list = new LinkedList<>();

    list.add(1);
    list.add(2);
    list.add(2);
    list.add(2);
    list.add(3);
    list.add(4);

    Collections.sort(list);

    int index =
        findFirstOccurrence(list.stream().mapToInt(i -> i).toArray(), 0, list.size() - 1, 2);

    assertEquals(1, index);
  }

  /**
   * We search for middle value and move to the left or right until we find the value. If there are
   * multiple occurrence for the key we move until middle value is the end of our search
   *
   * @param a
   * @param start
   * @param end
   * @param key
   * @return
   */
  static int findFirstOccurrence(int[] a, int start, int end, int key) {

    while (start < end) {
      int mid = start + (end - start) / 2;

      if (a[mid] >= key) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return (a[start] == key) ? start : -1;
  }

  @Test
  void kthMissingElement() {
    assertEquals(9, kthMissingElement(new int[] {2, 4, 10, 7}, 5));
  }

  private int kthMissingElement(int[] arr, int k) {

    Arrays.sort(arr);

    Set<Integer> set = new LinkedHashSet<>();

    for (int i : arr) {
      set.add(i);
    }

    Iterator<Integer> iterator = set.iterator();
    Integer start = iterator.next();
    int count = 0;
    int diff = 0;
    int thElement = 0;

    while (iterator.hasNext()) {
      int next = iterator.next();

      diff = next - start;

      if (diff == 1) {
        start = next;
        continue;
      }

      count += diff - 1;

      if (count >= k) {
        thElement = start + diff - 1;
        break;
      }

      start = next;
    }

    return thElement;
  }

  @Test
  void checkGivenStringIsRotationOfAnotherString() {
    assertFalse(doubledOriginContainsRotation("aaaa", "bbbb"));
    assertTrue(doubledOriginContainsRotation("abcd", "cdab"));
    assertTrue(doubledOriginContainsRotation("abcd", "abcd"));

    assertFalse(fromCommonStartWithOrigin("aaaa", "bbbb"));
    assertTrue(fromCommonStartWithOrigin("abcd", "cdab"));
    assertTrue(fromCommonStartWithOrigin("abcd", "abcd"));

    assertFalse(withQueue("aaaa", "bbbb"));
    assertTrue(withQueue("abcd", "cdab"));
    assertTrue(withQueue("abcd", "abcd"));

    assertFalse(withSuffixAndPrefix("aaaa", "bbbb"));
    assertTrue(withSuffixAndPrefix("abcd", "cdab"));
    assertTrue(withSuffixAndPrefix("abcd", "abcd"));
  }

  boolean doubledOriginContainsRotation(String origin, String rotation) {
    if (origin.length() == rotation.length()) {
      return origin.concat(origin).contains(rotation);
    }

    return false;
  }

  boolean fromCommonStartWithOrigin(String origin, String rotation) {

    if (origin.length() == rotation.length()) {

      // Collect indexes where the rotation could start with the first letter of the original string
      List<Integer> indexes =
          IntStream.range(0, origin.length())
              .filter(i -> rotation.charAt(i) == origin.charAt(0))
              .boxed()
              .toList();

      for (int foundAt : indexes) {
        if (isRotation(origin, rotation, foundAt)) {
          return true;
        }
      }
    }

    return false;
  }

  boolean isRotation(String origin, String rotation, int foundAt) {

    for (int i = 0; i < origin.length(); i++) {
      if (origin.charAt(i) != rotation.charAt((foundAt + i) % origin.length())) {
        return false;
      }
    }

    return true;
  }

  boolean withQueue(String origin, String rotation) {

    if (origin.length() == rotation.length()) {
      return isRotation(origin, rotation);
    }

    return false;
  }

  boolean isRotation(String origin, String rotation) {

    if (origin.length() == rotation.length()) {

      Queue<Character> originQueue = getCharactersQueue(origin);

      Queue<Character> rotationQueue = getCharactersQueue(rotation);

      int k = rotation.length();
      while (k > 0 && null != rotationQueue.peek()) {
        k--;
        char ch = rotationQueue.peek();
        rotationQueue.remove();
        rotationQueue.add(ch);
        if (rotationQueue.equals(originQueue)) return true;
      }
    }

    return false;
  }

  private Queue<Character> getCharactersQueue(String origin) {
    return origin.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(LinkedList::new));
  }

  boolean withSuffixAndPrefix(String origin, String rotation) {

    if (origin.length() == rotation.length()) {
      return areRotations(origin, rotation);
    }

    return false;
  }

  boolean areRotations(String origin, String rotation) {
    if (origin.length() == rotation.length()) {

      for (int i = 0; i < origin.length(); i++) {
        if (origin.charAt(i) == rotation.charAt(0)) {
          // checking prefix of s2 with suffix of
          // s1
          if (origin.substring(i).equals(rotation.substring(0, origin.length() - i))) {
            // checking prefix of s1 with suffix
            // of s2
            if (origin.substring(0, i).equals(rotation.substring(origin.length() - i))) return true;
          }
        }
      }
    }

    return false;
  }
}
