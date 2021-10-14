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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeetCodeArray2Test {

  private static final char X = 'X';
  private static final char O = 'O';

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
