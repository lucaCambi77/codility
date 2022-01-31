package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeetCodeArray2Test {

  private static final char X = 'X';
  private static final char O = 'O';

  @Test
  public void invalidTransactions() {
    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "alice,50,100,beijing"})
            .containsAll(Arrays.asList("alice,20,800,mtv", "alice,50,100,beijing")));

    assertTrue(
        invalidTransactions(
                new String[] {"alice,20,800,mtv", "alice,50,100,mtv", "alice,51,100,frankfurt"})
            .containsAll(
                Arrays.asList("alice,20,800,mtv", "alice,50,100,mtv", "alice,51,100,frankfurt")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "alice,50,1200,mtv"})
            .containsAll(Arrays.asList("alice,50,1200,mtv")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,800,mtv", "bob,50,1200,mtv"})
            .containsAll(Arrays.asList("bob,50,1200,mtv")));

    assertTrue(
        invalidTransactions(new String[] {"alice,20,1220,mtv", "alice,20,1220,mtv"})
            .containsAll(Arrays.asList("alice,20,1220,mtv", "alice,20,1220,mtv")));

    assertTrue(
        invalidTransactions(
                new String[] {
                  "bob,55,173,barcelona",
                  "lee,113,952,zurich",
                  "maybe,115,1973,madrid",
                  "chalicefy,229,283,istanbul",
                  "bob,24,874,shanghai",
                  "alex,568,412,tokyo",
                  "alex,242,1710,milan",
                  "iris,722,879,shenzhen",
                  "chalicefy,281,1586,warsaw",
                  "maybe,246,778,bangkok",
                  "xnova,605,166,newdelhi",
                  "iris,631,991,hongkong",
                  "chalicefy,500,620,tokyo",
                  "chalicefy,380,428,istanbul",
                  "iris,905,180,barcelona",
                  "alex,810,732,shenzhen",
                  "iris,689,389,paris",
                  "xnova,475,298,singapore",
                  "lee,58,709,amsterdam",
                  "xnova,717,546,guangzhou",
                  "maybe,78,435,shenzhen",
                  "maybe,333,145,hongkong",
                  "lee,405,1230,hongkong",
                  "lee,456,1440,tokyo",
                  "chalicefy,286,1071,amsterdam",
                  "alex,55,271,shanghai",
                  "bob,91,273,warsaw",
                  "iris,195,1825,tokyo",
                  "maybe,639,417,madrid",
                  "maybe,305,882,chicago",
                  "lee,443,47,chicago",
                  "chalicefy,958,840,budapest",
                  "lee,162,1239,budapest",
                  "bob,701,505,montreal",
                  "alex,52,1575,munich",
                  "bob,533,1407,amsterdam",
                  "lee,621,491,tokyo",
                  "chalicefy,866,622,rome",
                  "alex,925,455,hongkong",
                  "lee,968,164,moscow",
                  "chalicefy,31,1119,newdelhi",
                  "iris,527,700,warsaw",
                  "bob,286,1694,dubai",
                  "maybe,903,29,barcelona",
                  "maybe,474,1606,prague",
                  "maybe,851,648,beijing",
                  "lee,48,655,chicago",
                  "maybe,378,25,toronto",
                  "lee,922,691,munich",
                  "maybe,411,903,taipei",
                  "lee,651,112,guangzhou",
                  "lee,664,506,dubai",
                  "chalicefy,704,924,milan",
                  "maybe,333,1264,budapest",
                  "chalicefy,587,1112,singapore",
                  "maybe,428,437,moscow",
                  "lee,721,366,newdelhi",
                  "iris,824,1962,beijing",
                  "chalicefy,834,489,istanbul",
                  "alex,639,1473,zurich",
                  "xnova,898,738,tokyo",
                  "chalicefy,585,1313,frankfurt",
                  "xnova,730,759,beijing",
                  "alex,69,892,montreal",
                  "lee,77,91,barcelona",
                  "lee,722,611,taipei",
                  "chalicefy,706,1982,jakarta",
                  "chalicefy,743,584,luxembourg",
                  "xnova,683,322,istanbul",
                  "chalicefy,60,861,prague",
                  "alex,366,871,shenzhen",
                  "chalicefy,77,870,shenzhen",
                  "iris,913,1501,warsaw",
                  "iris,846,1176,warsaw",
                  "bob,873,69,zurich",
                  "alex,601,181,chicago",
                  "chalicefy,118,145,hongkong",
                  "bob,879,982,montreal",
                  "lee,994,950,chicago",
                  "maybe,885,1900,shanghai",
                  "lee,717,1447,shanghai",
                  "chalicefy,71,434,istanbul",
                  "bob,870,968,toronto",
                  "maybe,718,51,beijing",
                  "alex,669,896,istanbul",
                  "chalicefy,639,506,rome",
                  "alex,594,934,frankfurt",
                  "maybe,3,89,jakarta",
                  "xnova,328,1710,rome",
                  "alex,611,571,chicago",
                  "chalicefy,31,458,montreal",
                  "iris,973,696,toronto",
                  "iris,863,148,rome",
                  "chalicefy,926,511,warsaw",
                  "alex,218,1411,zurich",
                  "chalicefy,544,1296,shenzhen",
                  "iris,27,23,montreal",
                  "chalicefy,295,263,prague",
                  "maybe,575,31,munich",
                  "alex,215,174,prague"
                })
            .containsAll(
                Arrays.asList(
                    "bob,55,173,barcelona",
                    "lee,113,952,zurich",
                    "maybe,115,1973,madrid",
                    "chalicefy,229,283,istanbul",
                    "bob,24,874,shanghai",
                    "alex,568,412,tokyo",
                    "alex,242,1710,milan",
                    "iris,722,879,shenzhen",
                    "chalicefy,281,1586,warsaw",
                    "maybe,246,778,bangkok",
                    "iris,631,991,hongkong",
                    "chalicefy,500,620,tokyo",
                    "iris,905,180,barcelona",
                    "iris,689,389,paris",
                    "lee,58,709,amsterdam",
                    "xnova,717,546,guangzhou",
                    "maybe,78,435,shenzhen",
                    "maybe,333,145,hongkong",
                    "lee,405,1230,hongkong",
                    "lee,456,1440,tokyo",
                    "chalicefy,286,1071,amsterdam",
                    "alex,55,271,shanghai",
                    "bob,91,273,warsaw",
                    "iris,195,1825,tokyo",
                    "maybe,305,882,chicago",
                    "lee,443,47,chicago",
                    "chalicefy,958,840,budapest",
                    "lee,162,1239,budapest",
                    "alex,52,1575,munich",
                    "bob,533,1407,amsterdam",
                    "lee,621,491,tokyo",
                    "chalicefy,866,622,rome",
                    "lee,968,164,moscow",
                    "chalicefy,31,1119,newdelhi",
                    "bob,286,1694,dubai",
                    "maybe,903,29,barcelona",
                    "maybe,474,1606,prague",
                    "maybe,851,648,beijing",
                    "lee,48,655,chicago",
                    "maybe,378,25,toronto",
                    "lee,922,691,munich",
                    "maybe,411,903,taipei",
                    "lee,651,112,guangzhou",
                    "lee,664,506,dubai",
                    "chalicefy,704,924,milan",
                    "maybe,333,1264,budapest",
                    "chalicefy,587,1112,singapore",
                    "maybe,428,437,moscow",
                    "lee,721,366,newdelhi",
                    "iris,824,1962,beijing",
                    "chalicefy,834,489,istanbul",
                    "alex,639,1473,zurich",
                    "chalicefy,585,1313,frankfurt",
                    "xnova,730,759,beijing",
                    "alex,69,892,montreal",
                    "lee,77,91,barcelona",
                    "lee,722,611,taipei",
                    "chalicefy,706,1982,jakarta",
                    "chalicefy,743,584,luxembourg",
                    "xnova,683,322,istanbul",
                    "chalicefy,60,861,prague",
                    "chalicefy,77,870,shenzhen",
                    "iris,913,1501,warsaw",
                    "iris,846,1176,warsaw",
                    "bob,873,69,zurich",
                    "alex,601,181,chicago",
                    "chalicefy,118,145,hongkong",
                    "bob,879,982,montreal",
                    "lee,994,950,chicago",
                    "maybe,885,1900,shanghai",
                    "lee,717,1447,shanghai",
                    "chalicefy,71,434,istanbul",
                    "bob,870,968,toronto",
                    "alex,669,896,istanbul",
                    "chalicefy,639,506,rome",
                    "alex,594,934,frankfurt",
                    "xnova,328,1710,rome",
                    "alex,611,571,chicago",
                    "chalicefy,31,458,montreal",
                    "iris,973,696,toronto",
                    "iris,863,148,rome",
                    "chalicefy,926,511,warsaw",
                    "alex,218,1411,zurich",
                    "chalicefy,544,1296,shenzhen",
                    "chalicefy,295,263,prague",
                    "alex,215,174,prague")));

    assertTrue(
        invalidTransactions(
                new String[] {
                  "alice,20,800,mtv",
                  "bob,50,1200,mtv",
                  "alice,20,800,mtv",
                  "alice,50,1200,mtv",
                  "alice,20,800,mtv",
                  "alice,50,100,beijing"
                })
            .containsAll(
                Arrays.asList(
                    "alice,20,800,mtv",
                    "bob,50,1200,mtv",
                    "alice,20,800,mtv",
                    "alice,50,1200,mtv",
                    "alice,20,800,mtv",
                    "alice,50,100,beijing")));
  }

  class Transaction {

    public String getTransaction() {
      return transaction;
    }

    public Transaction(String transaction) {
      this.transaction = transaction;
    }

    private final String transaction;
    private boolean isValid = true;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Transaction that = (Transaction) o;
      return Objects.equals(transaction, that.transaction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(transaction);
    }

    public boolean isValid() {
      return isValid;
    }

    public void setValid(boolean valid) {
      isValid = valid;
    }
  }

  private List<String> invalidTransactions(String[] transactions) {

    List<String> sol = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();

    for (String transaction : transactions) {
      transactionList.add(new Transaction(transaction));
    }

    for (int i = 0; i < transactionList.size(); i++) {
      Transaction transaction = transactionList.get(i);
      String[] s = transaction.getTransaction().split(",");

      if (Integer.parseInt(s[2]) - 1000 > 0) transaction.setValid(false);

      for (int j = i + 1; j < transactionList.size(); j++) {
        Transaction transaction1 = transactionList.get(j);
        String[] s1 = transaction1.getTransaction().split(",");

        if (s[0].equals(s1[0])
            && !s[3].equals(s1[3])
            && Math.abs(Integer.parseInt(s[1]) - Integer.parseInt(s1[1])) <= 60) {
          transaction.setValid(false);
          transaction1.setValid(false);
        }

        if (Integer.parseInt(s1[2]) - 1000 > 0) transaction1.setValid(false);
      }
    }

    for (Transaction t : transactionList) {
      if (!t.isValid()) sol.add(t.getTransaction());
    }
    return new ArrayList<>(sol);
  }

  @Test
  public void makeEqual() {
    assertTrue(makeEqual(new String[] {"abc", "aabc", "bc"}));
    assertTrue(
        makeEqual(
            new String[] {
              "caaaaa",
              "aaaaaaaaa",
              "a",
              "bbb",
              "bbbbbbbbb",
              "bbb",
              "cc",
              "cccccccccccc",
              "ccccccc",
              "ccccccc",
              "cc",
              "cccc",
              "c",
              "cccccccc",
              "c"
            }));
    assertFalse(makeEqual(new String[] {"ab", "a"}));
    assertFalse(makeEqual(new String[] {"a", "b"}));
  }

  private boolean makeEqual(String[] words) {
    int[] map = new int[26];

    for (String s : words) {
      for (int i = 0; i < s.length(); i++) {
        ++map[s.charAt(i) - 'a'];
      }
    }

    for (int freq : map) {
      if (freq != 0 && freq % words.length != 0) return false;
    }

    return true;
  }

  @Test
  public void areOccurrencesEqual() {
    assertTrue(areOccurrencesEqual("abacbc"));
    assertFalse(areOccurrencesEqual("aaabb"));
  }

  public boolean areOccurrencesEqual(String s) {

    int[] dict = new int[256];

    for (int i = 0; i < s.length(); i++) {

      dict[s.charAt(i)]++;
    }

    int count = 0;

    for (int i = 0; i < s.length(); i++) {

      if (dict[s.charAt(i)] > 0) {
        if (count == 0) count = dict[s.charAt(i)];
        else if (count != dict[s.charAt(i)]) return false;
      }
    }

    return true;
  }

  @Test
  public void summaryRanges() {
    assertEquals(Arrays.asList("0->2", "4->5", "7"), summaryRanges(new int[] {0, 1, 2, 4, 5, 7}));
    assertEquals(
        Arrays.asList("0", "2->4", "6", "8->9"), summaryRanges(new int[] {0, 2, 3, 4, 6, 8, 9}));
  }

  private List<String> summaryRanges(int[] nums) {

    if (nums.length == 0) return new ArrayList<>();

    List<String> result = new ArrayList<>();

    int start = nums[0];
    int temp = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == temp + 1) {
        temp = nums[i];
        continue;
      }
      result.add(start == temp ? String.valueOf(start) : start + "->" + temp);
      start = nums[i];
      temp = nums[i];
    }

    result.add(start == temp ? String.valueOf(start) : start + "->" + temp);

    return result;
  }

  @Test
  public void canAttendMeetings() {
    assertFalse(canAttendMeetings(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
    assertTrue(canAttendMeetings(new int[][] {{7, 10}, {2, 4}}));
    assertFalse(canAttendMeetings(new int[][] {{6, 15}, {13, 20}, {6, 17}}));
    assertFalse(canAttendMeetings(new int[][] {{19, 20}, {1, 10}, {5, 14}}));
  }

  private boolean canAttendMeetings(int[][] intervals) {

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) return false;
    }

    return true;
  }

  @Test
  public void countKDifference() {
    assertEquals(4, countKDifference(new int[] {1, 2, 2, 1}, 1));
    assertEquals(0, countKDifference(new int[] {1, 3}, 3));
    assertEquals(3, countKDifference(new int[] {3, 2, 1, 5, 4}, 2));
  }

  private int countKDifference(int[] nums, int k) {

    int count = 0;

    Map<Integer, Integer> map = new HashMap<>();

    for (int num : nums) {
      count += map.getOrDefault(num + k, 0) + map.getOrDefault(num - k, 0);
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    return count;
  }

  @Test
  public void maximumUnits() {
    assertEquals(8, maximumUnits(new int[][] {{1, 3}, {2, 2}, {3, 1}}, 4));
    assertEquals(91, maximumUnits(new int[][] {{5, 10}, {2, 5}, {4, 7}, {3, 9}}, 10));
  }

  private int maximumUnits(int[][] boxTypes, int truckSize) {

    Arrays.sort(boxTypes, (t1, t2) -> Integer.compare(t2[1], t1[1]));

    int i = 0;
    int sol = 0;
    int maxBoxes = 0;

    while (i < boxTypes.length && maxBoxes < truckSize) {

      int[] box = boxTypes[i];

      int boxesToAdd = box[0] + maxBoxes <= truckSize ? box[0] : truckSize - maxBoxes;

      maxBoxes = maxBoxes + boxesToAdd;

      sol = sol + box[1] * boxesToAdd;

      i++;
    }

    return sol;
  }

  @Test
  public void findCenter() {
    assertEquals(2, findCenter(new int[][] {{1, 2}, {2, 3}, {4, 2}}));
    assertEquals(1, findCenter(new int[][] {{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
  }

  private int findCenter(int[][] edges) {
    int[] edge1 = edges[0];
    int[] edge2 = edges[1];

    if (edge1[0] == edge2[0] || edge1[0] == edge2[1]) return edge1[0];
    else return edge1[1];
  }

  @Test
  public void getMaximumGenerated() {

    assertEquals(0, getMaximumGenerated(0));
    assertEquals(1, getMaximumGenerated(1));
    assertEquals(1, getMaximumGenerated(2));
    assertEquals(2, getMaximumGenerated(3));
    assertEquals(2, getMaximumGenerated(4));
    assertEquals(3, getMaximumGenerated(5));
    assertEquals(4, getMaximumGenerated(10));
    assertEquals(7, getMaximumGenerated(20));
    assertEquals(8, getMaximumGenerated(21));
    assertEquals(8, getMaximumGenerated(30));
    assertEquals(5, getMaximumGenerated(15));
  }

  public int getMaximumGenerated(int n) {

    if (n == 0) return 0;
    if (n == 1 || n == 2) return 1;
    if (n == 3) return 2;

    int[] array = new int[n + 1];

    array[0] = 0;
    array[1] = 1;

    int i = 1;

    while (i <= n / 2) {
      array[i * 2] = array[i];

      if (i == n / 2) break;

      array[(i * 2) + 1] = array[i] + array[i + 1];
      i++;
    }

    if (((n & 1) == 1)) array[(i * 2) + 1] = array[i] + array[i + 1];

    return Arrays.stream(array).max().getAsInt();
  }

  @Test
  public void finalValueAfterOperations() {
    assertEquals(1, finalValueAfterOperations(new String[] {"--X", "X++", "X++"}));
    assertEquals(3, finalValueAfterOperations(new String[] {"++X", "++X", "X++"}));
    assertEquals(0, finalValueAfterOperations(new String[] {"X++", "++X", "--X", "X--"}));
  }

  private int finalValueAfterOperations(String[] operations) {

    int sol = 0;

    for (String s : operations) {
      if (s.contains("+")) sol++;
      else sol--;
    }

    return sol;
  }

  @Test
  public void tictactoe() {
    assertEquals(
        "A",
        tictactoe(
            new int[][] {
              {0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2},
            }));
    assertEquals("B", tictactoe(new int[][] {{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}}));
    assertEquals(
        "Draw",
        tictactoe(
            new int[][] {{0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}}));
    assertEquals("Pending", tictactoe(new int[][] {{0, 0}, {0, 1}}));
    assertEquals(
        "B",
        tictactoe(new int[][] {{2, 0}, {1, 1}, {0, 2}, {2, 1}, {1, 2}, {1, 0}, {0, 0}, {0, 1}}));
  }

  public String tictactoe(int[][] moves) {
    char[][] board = new char[3][3];

    char player = X;

    for (int[] move : moves) {

      board[move[0]][move[1]] = player;

      if (checkWinner(board, moves, player)) return player == X ? "A" : "B";

      player = player == X ? O : X;
    }

    return moves.length == 9 ? "Draw" : "Pending";
  }

  private boolean checkWinner(char[][] board, int[][] moves, char c) {

    if (board[0][0] == c && board[0][1] == c && board[0][2] == c) return true;
    if (board[1][0] == c && board[1][1] == c && board[1][2] == c) return true;
    if (board[2][0] == c && board[2][1] == c && board[2][2] == c) return true;

    if (board[0][0] == c && board[1][0] == c && board[2][0] == c) return true;
    if (board[0][1] == c && board[1][1] == c && board[2][1] == c) return true;
    if (board[0][2] == c && board[1][2] == c && board[2][2] == c) return true;

    if (board[0][0] == c && board[1][1] == c && board[2][2] == c) return true;

    return board[0][2] == c && board[1][1] == c && board[2][0] == c;
  }

  @Test
  public void getConcatenation() {
    assertArrayEquals(new int[] {1, 2, 1, 1, 2, 1}, getConcatenation(new int[] {1, 2, 1}));
    assertArrayEquals(new int[] {1, 3, 2, 1, 1, 3, 2, 1}, getConcatenation(new int[] {1, 3, 2, 1}));
  }

  private int[] getConcatenation(int[] nums) {
    int[] sol = new int[nums.length * 2];

    for (int i = 0; i < nums.length; i++) {
      sol[i] = nums[i];
      sol[i + nums.length] = nums[i];
    }
    return sol;
  }

  @Test
  public void longestPalindrome() {
    assertEquals(7, longestPalindrome("abccccdd"));
    assertEquals(1, longestPalindrome("a"));
    assertEquals(2, longestPalindrome("bb"));
    assertEquals(6, longestPalindrome("AAAAAA"));
    assertEquals(
        983,
        longestPalindrome(
            "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
  }

  private int longestPalindrome(String s) {
    int[] chars = new int[256];

    for (char c : s.toCharArray()) {
      chars[c]++;
    }

    int ans = 0;
    for (int v : chars) {
      if (v != 0) {
        ans += v / 2 * 2;
        if (ans % 2 == 0 && v % 2 == 1) ans++;
      }
    }
    return ans;
  }

  @Test
  public void canPermutePalindrome() {
    assertFalse(canPermutePalindrome("code"));
    assertTrue(canPermutePalindrome("aab"));
    assertTrue(canPermutePalindrome("carerac"));
  }

  private boolean canPermutePalindrome(String s) {
    int[] chars = new int[26];

    for (char c : s.toCharArray()) {
      chars[c - 'a']++;
    }

    int onlyOneEven = 0;

    for (int aChar : chars) {
      if ((aChar & 1) == 1) onlyOneEven++;

      if (onlyOneEven > 1) return false;
    }

    return true;
  }

  @Test
  public void stringMatching() {
    assertTrue(
        stringMatching(new String[] {"mass", "as", "hero", "superhero"})
            .containsAll(Arrays.asList("as", "hero")));

    assertTrue(
        stringMatching(new String[] {"leetcode", "et", "code"})
            .containsAll(Arrays.asList("et", "code")));

    assertTrue(stringMatching(new String[] {"blue", "green", "bu"}).isEmpty());

    assertTrue(
        stringMatching(new String[] {"leetcoder", "leetcode", "od", "hamlet", "am"})
            .containsAll(Arrays.asList("leetcode", "od", "am")));
  }

  private List<String> stringMatching(String[] words) {

    Arrays.sort(words, Comparator.comparingInt(String::length));

    HashSet<String> strings = new HashSet<>();

    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if (words[j].contains(words[i])) strings.add(words[i]);
      }
    }

    return new ArrayList<>(strings);
  }

  @Test
  public void buildArray() {
    assertArrayEquals(new int[] {0, 1, 2, 4, 5, 3}, buildArray(new int[] {0, 2, 1, 5, 3, 4}));
    assertArrayEquals(new int[] {4, 5, 0, 1, 2, 3}, buildArray(new int[] {5, 0, 1, 2, 3, 4}));
  }

  public int[] buildArray(int[] nums) {
    int[] sol = new int[nums.length];

    for (int i = 0; i < nums.length; i++) {
      sol[i] = nums[nums[i]];
    }

    return sol;
  }

  @Test
  public void sortSentence() {
    assertEquals("This is a sentence", sortSentence("is2 sentence4 This1 a3"));
    assertEquals("Me Myself and I", sortSentence("Myself2 Me1 I4 and3"));
  }

  private String sortSentence(String s) {

    return Arrays.stream(s.split("\\s"))
        .sorted(Comparator.comparing(s12 -> s12.substring(s12.length() - 1)))
        .collect(Collectors.joining(" "))
        .replaceAll("\\d", "");
  }

  @Test
  public void checkIfPangram() {
    assertTrue(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    assertFalse(checkIfPangram("leetcode"));
  }

  private boolean checkIfPangram(String sentence) {

    boolean[] bool = new boolean[26];

    for (int i = 0; i < sentence.length(); i++) {
      bool[sentence.charAt(i) - 'a'] = true;
    }

    for (boolean b : bool) {
      if (!b) return false;
    }

    return true;
  }

  @Test
  public void removeDuplicates() {
    assertEquals("ca", removeDuplicates("abbaca"));
    assertEquals("ay", removeDuplicates("azxxzy"));
    assertEquals("ay", removeDuplicates("azxxxxzy"));
    assertEquals("azxzy", removeDuplicates("azxxxzy"));
    assertEquals("", removeDuplicates("aaaaaaaa"));
  }

  private String removeDuplicates(String s) {
    StringBuilder sb = new StringBuilder();
    int sbLength = 0;
    for (char character : s.toCharArray()) {
      if (sbLength != 0 && character == sb.charAt(sbLength - 1)) sb.deleteCharAt(sbLength-- - 1);
      else {
        sb.append(character);
        sbLength++;
      }
    }
    return sb.toString();
  }

  @Test
  public void minStartValue() {
    assertEquals(5, minStartValue(new int[] {-3, 2, -3, 4, 2}));
    assertEquals(1, minStartValue(new int[] {1, 2}));
    assertEquals(5, minStartValue(new int[] {1, -2, -3}));
    assertEquals(4, minStartValue(new int[] {-3, 6, 2, 5, 8, 6}));
  }

  private int minStartValue(int[] nums) {

    int[] prefSum = new int[nums.length];
    int min = nums[0];

    prefSum[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      prefSum[i] = prefSum[i - 1] + nums[i];
      min = Math.min(prefSum[i], min);
    }

    return min < 0 ? (min * -1 + 1) : 1;
  }

  @Test
  public void findMissingRanges() {
    assertEquals(Collections.emptyList(), findMissingRanges(new int[] {0, 1, 2, 3}, 0, 3));

    assertEquals(
        Arrays.asList("2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] {0, 1, 3, 50, 75}, 0, 99));

    assertEquals(
        Arrays.asList("0->2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] {3, 50, 75}, 0, 99));

    assertEquals(Collections.singletonList("1"), findMissingRanges(new int[] {}, 1, 1));

    assertEquals(Collections.singletonList("-3->-1"), findMissingRanges(new int[] {}, -3, -1));

    assertEquals(Collections.singletonList("-1"), findMissingRanges(new int[] {}, -1, -1));

    assertEquals(Collections.singletonList("-2"), findMissingRanges(new int[] {-1}, -2, -1));

    assertEquals(Collections.singletonList("0"), findMissingRanges(new int[] {-1}, -1, 0));

    assertEquals(Arrays.asList("0", "2->9"), findMissingRanges(new int[] {1}, 0, 9));

    assertEquals(Arrays.asList("-1->0", "2->9"), findMissingRanges(new int[] {1}, -1, 9));
  }

  private List<String> findMissingRanges(int[] nums, int lower, int upper) {

    List<String> list = new ArrayList<>();

    int prev = lower - 1;
    for (int i = 0; i <= nums.length; i++) {
      int curr = (i < nums.length) ? nums[i] : upper + 1;
      if (prev + 1 <= curr - 1) {
        list.add(formatRange(prev + 1, curr - 1));
      }
      prev = curr;
    }

    return list;
  }

  private String formatRange(int lower, int upper) {
    if (lower == upper) {
      return String.valueOf(lower);
    }
    return lower + "->" + upper;
  }

  @Test
  public void minimumAbsDifference() {
    assertEquals(
        Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4)),
        minimumAbsDifference(new int[] {4, 2, 1, 3}));

    assertEquals(
        Collections.singletonList(Arrays.asList(1, 3)),
        minimumAbsDifference(new int[] {1, 3, 6, 10, 15}));

    assertEquals(
        Arrays.asList(Arrays.asList(-14, -10), Arrays.asList(19, 23), Arrays.asList(23, 27)),
        minimumAbsDifference(new int[] {3, 8, -10, 23, 19, -4, -14, 27}));

    assertEquals(
        Collections.singletonList(Arrays.asList(26, 27)),
        minimumAbsDifference(new int[] {40, 11, 26, 27, -20}));
  }

  private List<List<Integer>> minimumAbsDifference(int[] arr) {

    Arrays.sort(arr);

    int minDiff = Integer.MAX_VALUE;

    Map<Integer, List<List<Integer>>> map = new HashMap<>();

    for (int i = 1; i < arr.length; i++) {
      int diff = Math.abs(arr[i] - arr[i - 1]);

      minDiff = Math.min(diff, minDiff);

      if (diff <= minDiff) {
        List<List<Integer>> integers = map.getOrDefault(diff, new ArrayList<>());
        integers.add(Arrays.asList(arr[i - 1], arr[i]));
        map.put(diff, integers);
      }
    }

    return map.get(minDiff);
  }

  @Test
  public void transformArray() {
    assertEquals(Arrays.asList(6, 3, 3, 4), transformArray(new int[] {6, 2, 3, 4}));
    assertEquals(Arrays.asList(1, 4, 4, 4, 4, 5), transformArray(new int[] {1, 6, 3, 4, 3, 5}));
    assertEquals(
        Arrays.asList(2, 2, 1, 1, 1, 2, 2, 1), transformArray(new int[] {2, 1, 2, 1, 1, 2, 2, 1}));
  }

  private List<Integer> transformArray(int[] arr) {

    if (arr.length < 3) return Arrays.stream(arr).boxed().collect(Collectors.toList());

    int[] sol = new int[arr.length];
    sol[0] = arr[0];
    sol[arr.length - 1] = arr[arr.length - 1];

    boolean hasChanged = true;

    while (hasChanged) {
      updateArray(arr, sol);

      if (Arrays.equals(arr, sol)) hasChanged = false;
      else arr = sol.clone();
    }

    return Arrays.stream(sol).boxed().collect(Collectors.toList());
  }

  public void updateArray(int[] arr, int[] sol) {
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
        sol[i] = arr[i] + 1;
      } else if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
        sol[i] = arr[i] - 1;
      } else {
        sol[i] = arr[i];
      }
    }
  }

  @Test
  public void dietPlanPerformance() {
    assertEquals(0, dietPlanPerformance(new int[] {1, 2, 3, 4, 5}, 1, 3, 3));
    assertEquals(1, dietPlanPerformance(new int[] {3, 2}, 2, 0, 1));
    assertEquals(0, dietPlanPerformance(new int[] {6, 5, 0, 0}, 2, 1, 5));
    assertEquals(3, dietPlanPerformance(new int[] {6, 13, 8, 7, 10, 1, 12, 11}, 6, 5, 37));
  }

  private int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    int points = 0;
    int tot = 0;

    for (int i = 0; i < calories.length; i++) {
      tot += calories[i];

      if (i + 1 < k) continue;
      if (i + 1 > k) tot -= calories[i - k];

      if (tot < lower) points--;
      else if (tot > upper) points++;
    }

    return points;
  }
}
