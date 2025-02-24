/** */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.cambi.codility.model.Strings;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author luca
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class LeetCodeStringTest {

  /** Letter to primitive map */
  private static final Map<Character, Integer> alphabetMap =
      new HashMap<>() {
        {
          put('a', 2);
          put('b', 3);
          put('c', 5);
          put('d', 7);
          put('e', 11);
          put('f', 13);
          put('g', 17);
          put('h', 19);
          put('i', 23);
          put('j', 29);
          put('k', 31);
          put('l', 37);
          put('m', 41);
          put('n', 43);
          put('o', 47);
          put('p', 53);
          put('q', 59);
          put('r', 61);
          put('s', 67);
          put('t', 71);
          put('u', 73);
          put('v', 79);
          put('w', 83);
          put('x', 89);
          put('y', 97);
          put('z', 101);
        }
      };

  private final Map<String, String> monthMap =
      new HashMap<>() {
        {
          put("Jan", "01");
          put("Feb", "02");
          put("Mar", "03");
          put("Apr", "04");
          put("May", "05");
          put("Jun", "06");
          put("Jul", "07");
          put("Aug", "08");
          put("Sep", "09");
          put("Oct", "10");
          put("Nov", "11");
          put("Dec", "12");
        }
      };

  @Test
  public void addStrings() {
    assertEquals("134", addStrings("11", "123"));
    assertEquals("533", addStrings("456", "77"));
    assertEquals("0", addStrings("0", "0"));
    assertEquals("10", addStrings("9", "1"));
    assertEquals("108", addStrings("99", "9"));
  }

  public String addStrings(String num1, String num2) {

    int lentght1 = num1.length() - 1;
    int lentght2 = num2.length() - 1;

    int rem = 0;
    int sum;
    StringBuilder builder = new StringBuilder();

    while (lentght1 >= 0 && lentght2 >= 0) {
      sum = num1.charAt(lentght1--) - '0' + num2.charAt(lentght2--) - '0' + rem;
      if (sum >= 10) {
        builder.insert(0, sum - 10);
        rem = 1;
      } else {
        builder.insert(0, sum);
        rem = 0;
      }
    }

    if (lentght1 >= 0) {
      rem = remain(num1, rem, builder, lentght1);
    } else if (lentght2 >= 0) {
      rem = remain(num2, rem, builder, lentght2);
    }

    return rem == 1 ? builder.insert(0, rem).toString() : builder.toString();
  }

  private int remain(String s, int rem, StringBuilder builder, int length) {
    int sum;
    while (length >= 0) {
      sum = Integer.parseInt(Character.toString(s.charAt(length--))) + rem;
      if (sum >= 10) {
        builder.insert(0, sum - 10);
        rem = 1;
      } else {
        builder.insert(0, sum);
        rem = 0;
      }
    }
    return rem;
  }

  @Test
  public void equationsPossible() {
    assertFalse(equationsPossible(new String[] {"a==b", "b!=a"}));
    assertTrue(equationsPossible(new String[] {"a==b", "b==a"}));
    assertTrue(equationsPossible(new String[] {"c==c", "b==d", "x!=z"}));
    assertFalse(equationsPossible(new String[] {"b!=b"}));
    assertFalse(equationsPossible(new String[] {"a==b", "b!=c", "c==a"}));
    assertFalse(equationsPossible(new String[] {"a!=b", "b!=c", "c!=a"}));
    assertFalse(equationsPossible(new String[] {"a==b", "e==c", "b==c", "a!=e"}));
    assertFalse(equationsPossible(new String[] {"b==a", "e==c", "b==c", "a!=e"}));
    assertFalse(equationsPossible(new String[] {"a!=e", "b==a", "e==c", "b==c"}));

    // a -> ( b ) b -> ( a ) e -> ( c ) c -> ( e )
    // a -> ( b , c, e ) b -> ( a, c, e) c -> ( b, a, e) e -> ( c, b, a )

  }

  private boolean equationsPossible(String[] equations) {

    return true;
  }

  @Test
  public void countValidWords() {
    assertEquals(2, countValidWords("sx  1,  x"));
    assertEquals(3, countValidWords("cat and  dog"));
    assertEquals(5, countValidWords("alice and  bob are playing stone-game10"));
    assertEquals(0, countValidWords("!this  1-s b8d!"));
    assertEquals(1, countValidWords("!this  a-s b8d!"));
    assertEquals(6, countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
    assertEquals(0, countValidWords("-"));
    assertEquals(1, countValidWords("!"));
    assertEquals(1, countValidWords(" o6 t"));
  }

  private int countValidWords(String sentence) {
    String[] split = sentence.split("\\s+");

    int count = 0;

    for (String s : split) {

      if (s.isEmpty() || s.matches("\\d+")) continue;

      if (s.matches("^([a-z]+-[a-z]+)?([!,.])?$")) count++;
      if (s.matches("^[a-z]+([!,.])?$")) count++;
    }

    return count;
  }

  @Test
  public void findComplement() {
    assertEquals(2, findComplement(5));
  }

  private int findComplement(int num) {

    String s = Integer.toBinaryString(num);

    StringBuilder strinBuilder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      strinBuilder.append(s.charAt(i) == '0' ? '1' : '0');
    }

    return Integer.parseInt(strinBuilder.toString(), 2);
  }

  @Test
  public void reverseOnlyLetters() {
    assertEquals("dc-ba", reverseOnlyLetters("ab-cd"));
    assertEquals("j-Ih-gfE-dCba", reverseOnlyLetters("a-bC-dEf-ghIj"));
    assertEquals("Qedo1ct-eeLg=ntse-T!", reverseOnlyLetters("Test1ng-Leet=code-Q!"));
  }

  public String reverseOnlyLetters(String s) {

    char[] chars = s.toCharArray();
    char[] sol = new char[s.length()];
    int right = chars.length - 1;

    for (int i = 0; i < chars.length; i++) {
      if ((chars[i] < 91 && chars[i] > 64) || (chars[i] < 123 && chars[i] > 96)) {

        while (chars[right] < 65
            || chars[right] > 122
            || (chars[right] > 90 && chars[right] < 97)) {
          right--;
        }
        sol[right--] = chars[i];

      } else {
        sol[i] = chars[i];
      }
    }

    StringBuilder stringBuilder = new StringBuilder();
    for (char c : sol) {
      stringBuilder.append(c);
    }

    return stringBuilder.toString();
  }

  @Test
  public void reformatDate() {
    assertEquals("2052-10-20", reformatDate("20th Oct 2052"));
    assertEquals("1933-06-06", reformatDate("6th Jun 1933"));
    assertEquals("1960-05-26", reformatDate("26th May 1960"));
  }

  private String reformatDate(String date) {
    String[] s = date.split("\\s");

    return new StringBuilder()
        .append(s[2])
        .append("-")
        .append(monthMap.get(s[1]))
        .append("-")
        .append(String.format("%02d", Integer.parseInt(s[0].replaceAll("[^0-9]", ""))))
        .toString();
  }

  @Test
  public void stringShift() {
    assertEquals("cab", stringShift("abc", new int[][] {{0, 1}, {1, 2}}));
    assertEquals("bca", stringShift("abc", new int[][] {{1, 2}}));
    assertEquals("abc", stringShift("abc", new int[][] {{1, 3}}));
    assertEquals("bca", stringShift("abc", new int[][] {{0, 1}}));
    assertEquals("abc", stringShift("abc", new int[][] {{0, 3}}));
    assertEquals("bca", stringShift("abc", new int[][] {{0, 4}}));
    assertEquals("efgabcd", stringShift("abcdefg", new int[][] {{1, 1}, {1, 1}, {0, 2}, {1, 3}}));
  }

  private String stringShift(String s, int[][] shift) {

    int k = 0;

    for (int[] ints : shift) {
      if (ints[0] == 0) k -= ints[1];
      else k += ints[1];
    }

    if (k > 0) k = s.length() - (k % s.length());
    else k = Math.abs(k) % s.length();

    return s.substring(k) + s.substring(0, k);
  }

  @Test
  public void isSumEqual() {
    assertTrue(isSumEqual("acb", "cba", "cdb"));
    assertFalse(isSumEqual("aaa", "a", "cdb"));
    assertTrue(isSumEqual("aaa", "a", "aaaa"));
  }

  private boolean isSumEqual(String firstWord, String secondWord, String targetWord) {

    int first = 0, second = 0, sum = 0;
    for (int i = 0; i < firstWord.length(); i++) {
      first = first * 10 + firstWord.charAt(i) - 'a';
    }
    for (int i = 0; i < secondWord.length(); i++) {
      second = second * 10 + secondWord.charAt(i) - 'a';
    }
    for (int i = 0; i < targetWord.length(); i++) {
      sum = sum * 10 + targetWord.charAt(i) - 'a';
    }
    return first + second == sum;
  }

  @Test
  public void squareIsWhite() {
    assertFalse(squareIsWhite("a1"));
    assertTrue(squareIsWhite("h3"));
    assertFalse(squareIsWhite("c7"));
    assertFalse(squareIsWhite("h2"));
    assertTrue(squareIsWhite("c2"));
  }

  private boolean squareIsWhite(String coordinates) {

    boolean sol = (coordinates.charAt(0) - 'a') % 2 != 0;

    var numberDiff = (coordinates.charAt(1) - 1) % 2;

    return sol && numberDiff == 0 || !sol && numberDiff != 0;
  }

  @Test
  public void checkZeroOnes() {
    assertTrue(checkZeroOnes("1101"));
    assertFalse(checkZeroOnes("111000"));
    assertFalse(checkZeroOnes("110100010"));
  }

  private boolean checkZeroOnes(String s) {

    int maxSeqZero = 0;
    int maxSeqOne = 0;

    int tmpSeqZero = 0;
    int tmpSeqOne = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') {
        tmpSeqZero++;
        maxSeqZero = Math.max(maxSeqZero, tmpSeqZero);
        tmpSeqOne = 0;
      } else {
        tmpSeqOne++;
        maxSeqOne = Math.max(maxSeqOne, tmpSeqOne);
        tmpSeqZero = 0;
      }
    }

    return maxSeqOne > maxSeqZero;
  }

  @Test
  public void maxScore() {
    assertEquals(5, maxScore("011101"));
    assertEquals(5, maxScore("00111"));
    assertEquals(3, maxScore("1111"));
    assertEquals(1, maxScore("00"));

    assertEquals(5, maxScore1("011101"));
    assertEquals(5, maxScore1("00111"));
    assertEquals(3, maxScore1("1111"));
    assertEquals(1, maxScore1("00"));
  }

  private int maxScore(String s) {

    int sumLeft = 0;
    int maxScore = 0;
    int sumRight = 0;

    for (int i = 0; i < s.length(); i++) {
      sumRight += Character.getNumericValue(s.charAt(i));
    }

    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '0') {
        sumLeft++;
      } else {
        sumRight--;
      }
      maxScore = Math.max(maxScore, sumLeft + sumRight);
    }

    return maxScore;
  }

  private int maxScore1(String s) {
    int max = Integer.MIN_VALUE;
    int zeros = 0, ones = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') zeros++;
      else ones++;
      if (i != s.length() - 1) max = Math.max(zeros - ones, max);
    }
    return max + ones;
  }

  @Test
  public void secondHighest() {

    assertEquals(2, secondHighest("dfa12321afd"));
    assertEquals(-1, secondHighest("abc1111"));
  }

  public int secondHighest(String s) {
    int a = -1;
    int b = -1;
    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {

        int x = Character.getNumericValue(c);
        if (x > a) {
          b = a;
          a = x;
        } else if (x != a && x > b) {
          b = x;
        }
      }
    }
    return b;
  }

  @Test
  public void countPoints() {

    assertEquals(1, countPoints("B0B6G0R6R0R6G9"));
    assertEquals(1, countPoints("B0R0G0R9R0B0G0"));
    assertEquals(0, countPoints("G4"));
  }

  private int countPoints(String rings) {

    Map<Integer, Set<Character>> map = new HashMap<>();

    for (int i = 0; i < rings.length(); i++) {
      int value = Character.getNumericValue(rings.charAt(i + 1));

      Set<Character> set = map.getOrDefault(value, new HashSet<>());
      set.add(rings.charAt(i));
      map.put(value, set);
      i++;
    }

    return (int) map.values().stream().filter(v -> v.size() == 3).count();
  }

  @Test
  public void reversePrefix() {
    assertEquals("dcbaefd", reversePrefix("abcdefd", 'd'));
    assertEquals("zxyxxe", reversePrefix("xyxzxe", 'z'));
  }

  private String reversePrefix(String word, char ch) {

    StringBuilder stringBuilder = new StringBuilder();

    int i;
    for (i = 0; i < word.length(); i++) {
      stringBuilder.append(word.charAt(i));
      if (word.charAt(i) == ch) break;
    }

    return i == word.length() ? word : stringBuilder.reverse().toString() + word.substring(i + 1);
  }

  @Test
  public void replaceDigits() {
    assertEquals("abcdef", replaceDigits("a1c1e1"));
    assertEquals("abbdcfdhe", replaceDigits("a1b2c3d4e"));
  }

  private String replaceDigits(String s) {

    String[] split = s.split("[a-z]+");
    int j = 1;
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (Character.isLetter(s.charAt(i))) {
        stringBuilder.append(s.charAt(i));

        if (j < split.length) {
          int c = s.charAt(i) + Integer.parseInt(split[j]);
          stringBuilder.append((char) c);
          j++;
        }
      }
    }

    return stringBuilder.toString();
  }

  @Test
  public void truncateSentence() {
    assertEquals("Hello how are you", truncateSentence("Hello how are you Contestant", 4));
    assertEquals(
        "What is the solution", truncateSentence("What is the solution to this problem", 4));
    assertEquals("chopper is not a tanuki", truncateSentence("chopper is not a tanuki", 5));
  }

  private String truncateSentence(String s, int k) {
    String[] split = s.split("\\s");

    if (split.length == k) return s;

    return Arrays.stream(split, 0, k).collect(Collectors.joining(" "));
  }

  @Test
  public void numUniqueEmails() {
    assertEquals(
        2,
        numUniqueEmails(
            new String[] {
              "test.email+alex@leetcode.com",
              "test.e.mail+bob.cathy@leetcode.com",
              "testemail+david@lee.tcode.com"
            }));

    assertEquals(
        3, numUniqueEmails(new String[] {"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}));

    assertEquals(
        2,
        numUniqueEmails(
            new String[] {"test.email+alex@leetcode.com", "test.email.leet+alex@code.com"}));
  }

  public int numUniqueEmails(String[] emails) {

    Set<String> seen = new HashSet<>();

    for (String email : emails) {

      StringBuilder stringBuilder = new StringBuilder();

      String[] split = email.split("@");

      for (int i = 0; i < split[0].length(); i++) {

        if (email.charAt(i) == '.') continue;

        if (email.charAt(i) == '+') break;

        stringBuilder.append(email.charAt(i));
      }

      seen.add(stringBuilder.append("@").append(split[1]).toString());
    }

    return seen.size();
  }

  @Test
  public void maxDepth() {
    assertEquals(3, maxDepth("(1+(2*3)+((8)/4))+1"));
    assertEquals(3, maxDepth("(1)+((2))+(((3)))"));
    assertEquals(1, maxDepth("1+(2*3)/(2-1)"));
    assertEquals(0, maxDepth("1"));
    assertEquals(0, maxDepth(")("));
    assertEquals(0, maxDepth("()("));
  }

  public int maxDepth(String s) {

    Stack<Character> stack = new Stack<>();

    int count = 0;
    int sol = 0;
    for (int i = 0; i < s.length(); i++) {

      if (!stack.isEmpty() && stack.peek() == '(') {
        if (s.charAt(i) == ')') {
          count--;
          stack.pop();
          continue;
        }
      }

      if (s.charAt(i) == '(') {
        count++;
        sol = Math.max(sol, count);
      }

      if (s.charAt(i) == ')' || s.charAt(i) == '(') stack.push(s.charAt(i));
    }

    return stack.isEmpty() ? sol : 0;
  }

  @Test
  public void makeGood() {
    assertEquals("leetcode", makeGood("leEeetcode"));
    assertEquals("", makeGood("abBAcC"));
    assertEquals("s", makeGood("s"));
  }

  public String makeGood(String s) {
    StringBuilder stringBuilder = new StringBuilder(s);

    int i = 0;
    while (i + 1 < stringBuilder.length()) {
      if (stringBuilder.charAt(i) + 32 == stringBuilder.charAt(i + 1)
          || stringBuilder.charAt(i) - 32 == stringBuilder.charAt(i + 1)) {
        stringBuilder.deleteCharAt(i);
        stringBuilder.deleteCharAt(i);

        i = Math.max(i - 2, 0);
      } else i++;
    }

    return stringBuilder.toString();
  }

  @Test
  public void maxPower() {
    assertEquals(2, maxPower("leetcode"));
    assertEquals(5, maxPower("abbcccddddeeeeedcba"));
    assertEquals(1, maxPower("j"));
    assertEquals(2, maxPower("cc"));
  }

  private int maxPower(String s) {

    char c = s.charAt(0);
    int max = 0;
    int countTmp = 1;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        countTmp++;
      } else {
        max = Math.max(countTmp, max);
        countTmp = 1;
        c = s.charAt(i);
      }
    }

    return Math.max(countTmp, max);
  }

  @Test
  public void validWordAbbreviation() {
    assertTrue(validWordAbbreviation("internationalization", "i12iz4n"));
    assertTrue(validWordAbbreviation("internationalization", "i5a11o1"));
    assertFalse(validWordAbbreviation("apple", "a2e"));
    assertTrue(validWordAbbreviation("test", "t2t"));
    assertFalse(validWordAbbreviation("test", "t4t"));
    assertFalse(validWordAbbreviation("a", "01"));
    assertFalse(validWordAbbreviation("hi", "hi1"));
    assertFalse(validWordAbbreviation("hi", "2i"));
    assertFalse(validWordAbbreviation("word", "w02d"));
  }

  private boolean validWordAbbreviation(String word, String abbr) {

    if (abbr.length() > word.length()) return false;

    int j = 0, i;
    for (i = 0; i < word.length(); i++) {
      if (word.charAt(i) != abbr.charAt(j)) {

        if (Integer.parseInt(String.valueOf(abbr.charAt(j))) < 1) return false;

        StringBuilder stringBuilder = new StringBuilder().append(abbr.charAt(j));

        while (j < abbr.length() - 1 && Character.isDigit(abbr.charAt(++j)))
          stringBuilder.append(abbr.charAt(j));

        if (i + Integer.parseInt(stringBuilder.toString()) == word.length()) break;

        i = i + Integer.parseInt(stringBuilder.toString());

        if (i >= word.length() || word.charAt(i) != abbr.charAt(j)) return false;
      }
      j++;
    }

    return j <= i;
  }

  @Test
  public void arrayStringsAreEqual() {

    assertTrue(arrayStringsAreEqual(new String[] {"ab", "c"}, new String[] {"a", "bc"}));
    assertTrue(arrayStringsAreEqual(new String[] {"abc", "d", "defg"}, new String[] {"abcddefg"}));
    assertFalse(arrayStringsAreEqual(new String[] {"a", "cb"}, new String[] {"ab", "c"}));
  }

  private boolean arrayStringsAreEqual(String[] word1, String[] word2) {

    StringBuilder stringBuilder = new StringBuilder();

    Arrays.stream(word1).forEach(stringBuilder::append);

    StringBuilder stringBuilder1 = new StringBuilder();

    Arrays.stream(word2).forEach(stringBuilder1::append);

    return stringBuilder.toString().equals(stringBuilder1.toString());
  }

  @Test
  public void calculateTime() {
    assertEquals(4, calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
    assertEquals(73, calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode"));
  }

  private int calculateTime(String keyboard, String word) {

    int pos = 0;
    int sol = 0;
    for (int i = 0; i < word.length(); i++) {

      int i1 = keyboard.indexOf(word.charAt(i));
      sol += Math.abs(i1 - pos);
      pos = i1;
    }
    return sol;
  }

  @Test
  public void longestNiceSubstring() {
    assertEquals("aAa", longestNiceSubstring("YazaAay"));
    assertEquals("Bb", longestNiceSubstring("Bb"));
    assertEquals("", longestNiceSubstring("c"));
    assertEquals("dD", longestNiceSubstring("dDzeE"));
    assertEquals("cChH", longestNiceSubstring("cChH"));
  }

  private String longestNiceSubstring(String s) {

    return null;
  }

  @Test
  public void removeVowels() {
    assertEquals("ltcdscmmntyfrcdrs", removeVowels("leetcodeisacommunityforcoders"));
    assertEquals("", removeVowels("aeiou"));
  }

  private String removeVowels(String s) {

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'a'
          || s.charAt(i) == 'e'
          || s.charAt(i) == 'i'
          || s.charAt(i) == 'o'
          || s.charAt(i) == 'u') continue;
      builder.append(s.charAt(i));
    }

    return builder.toString();
  }

  @Test
  public void interpret() {
    assertEquals("Goal", interpret("G()(al)"));
    assertEquals("Gooooal", interpret("G()()()()(al)"));
    assertEquals("alGalooG", interpret("(al)G(al)()()G"));
  }

  private String interpret(String command) {
    String replace = command.replace("(al)", "al");
    return replace.replace("()", "o");
  }

  @Test
  public void minAddToMakeValid() {
    assertEquals(1, minAddToMakeValid("())"));
    assertEquals(3, minAddToMakeValid("((("));
    assertEquals(4, minAddToMakeValid("()))(("));
    assertEquals(0, minAddToMakeValid("()"));
  }

  private int minAddToMakeValid(String s) {

    Stack<Character> st = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(' || st.isEmpty()) st.push(c);
      else if (st.peek() == '(' && c == ')') {
        st.pop();
      } else {
        st.push(c);
      }
    }

    return st.size();
  }

  @Test
  public void frequencySort() {
    assertEquals("eert", frequencySort("tree"));
    assertEquals("aaaccc", frequencySort("cccaaa"));
    assertEquals("bbAa", frequencySort("Aabb"));
  }

  private String frequencySort(String s) {

    Map<Character, Integer> map = new HashMap<>();

    for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

    StringBuilder sb = new StringBuilder();
    map.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            w ->
                sb.append(
                    String.join(
                        "", Collections.nCopies(w.getValue(), Character.toString(w.getKey())))));

    return sb.toString();
  }

  @Test
  public void isLongPressedName() {
    assertTrue(isLongPressedName("alex", "aaleex"));
    assertFalse(isLongPressedName("saeed", "ssaaedd"));
    assertTrue(isLongPressedName("leelee", "lleeelee"));
    assertTrue(isLongPressedName("laiden", "laiden"));
    assertFalse(isLongPressedName("kikcxmvzi", "kiikcxxmmvvzz"));
    assertFalse(isLongPressedName("dfuyalc", "fuuyallc"));
  }

  private boolean isLongPressedName(String name, String typed) {

    int pointA = 0;
    int pointB = 0;

    while (pointA < name.length() && pointB < typed.length()) {
      if (name.charAt(pointA) == typed.charAt(pointB)) {
        pointA++;
        pointB++;
      } else if (name.charAt(Math.max(pointA - 1, 0)) == typed.charAt(pointB)) {
        pointB++;
      } else return false;
    }

    return name.charAt(name.length() - 1) == typed.charAt(typed.length() - 1);
  }

  @Test
  public void hammingDistance() {

    assertEquals(2, hammingDistance(1, 4));
  }

  private int hammingDistance(int x, int y) {

    String binX = String.format("%32s", Integer.toBinaryString(x)).replace(' ', '0');
    String binY = String.format("%32s", Integer.toBinaryString(y)).replace(' ', '0');

    int length = Math.min(binX.length(), binY.length());
    int count = 0;

    for (int i = 0; i < length; i++) {
      if (binX.charAt(i) != binY.charAt(i)) count++;
    }

    return count;
  }

  @Test
  public void generateTheString() {
    assertEquals("aaab", generateTheString(4));
    assertEquals("aaa", generateTheString(3));
  }

  private String generateTheString(int n) {

    int i = 0;
    StringBuilder builder = new StringBuilder();

    while (i++ < n) {
      builder.append('a');
    }

    if ((n & 1) == 0) builder.setCharAt(n - 1, 'b');

    return builder.toString();
  }

  @Test
  public void isPrefixOfWord() {
    assertEquals(4, isPrefixOfWord("i love eating burger", "burg"));
    assertEquals(2, isPrefixOfWord("this problem is an easy problem", "pro"));
    assertEquals(-1, isPrefixOfWord("i am tired", "you"));
    assertEquals(-1, isPrefixOfWord("hellohello hellohellohello", "ell"));
  }

  private int isPrefixOfWord(String sentence, String searchWord) {

    String[] split = sentence.split(" ");

    int pos = 1;
    for (String s : split) {
      int prefix = s.indexOf(searchWord);
      if (prefix == 0) return pos;
      pos++;
    }

    return -1;
  }

  @Test
  public void freqAlphabets() {

    assertEquals("jkab", freqAlphabets("10#11#12"));
    assertEquals("acz", freqAlphabets("1326#"));
    assertEquals("y", freqAlphabets("25#"));
  }

  private String freqAlphabets(String s) {

    char[] alphabet =
        new char[] {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
          's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
    StringBuilder sol = new StringBuilder();

    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '#') {
        int pos = Integer.parseInt(s.charAt(i - 2) + Character.toString(s.charAt(i - 1)));
        sol.append(alphabet[pos - 1]);
        --i;
        --i;
        continue;
      }

      sol.append(alphabet[Integer.parseInt(Character.toString(s.charAt(i))) - 1]);
    }

    return sol.reverse().toString();
  }

  @Test
  public void numJewelsInStones() {
    assertEquals(3, numJewelsInStones("aA", "aAAbbbb"));
    assertEquals(0, numJewelsInStones("z", "ZZ"));
  }

  private int numJewelsInStones(String J, String S) {

    Map<Character, Integer> map = new HashMap<>();

    for (Character aChar : S.toCharArray()) map.put(aChar, map.getOrDefault(aChar, 0) + 1);

    int solution = 0;

    for (Character character : J.toCharArray()) solution += map.getOrDefault(character, 0);

    return solution;
  }

  @Test
  public void mostCommonWord() {

    assertEquals(
        "ball",
        mostCommonWord(
            "Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}));
    assertEquals("bob", mostCommonWord("Bob!", new String[] {"hit"}));
    assertEquals("b", mostCommonWord("a, a, a, a, b,b,b,c, c", new String[] {"a"}));
  }

  private String mostCommonWord(String paragraph, String[] banned) {

    Set<String> words = new HashSet<>(Arrays.asList(banned));

    String[] cleanPar = paragraph.replaceAll("[;//.//://'//,//!//?]", " ").split("\\s+");

    Map<String, Integer> wordToFreq = new HashMap<String, Integer>();

    int max = 0;
    String result = null;
    int freq = 0;

    for (String s : cleanPar) {

      String lowerCase = s.toLowerCase();

      if (words.contains(lowerCase)) continue;

      freq = wordToFreq.getOrDefault(lowerCase, 0) + 1;

      if (freq > max) {
        max = freq;
        result = lowerCase;
      }

      wordToFreq.put(lowerCase, freq);
    }
    return result;
  }

  @Test
  public void rotatedDigits() {
    assertEquals(4, rotatedDigits(10));
    assertEquals(4, rotatedDigits(11));
    assertEquals(247, rotatedDigits(857));
  }

  private int rotatedDigits(int N) {

    Pattern match = Pattern.compile("[347]");

    Pattern match1 = Pattern.compile("[2569]");

    int res = 0;

    for (int i = 2; i <= N; i++) {

      String s = Integer.toString(i);

      if (match.matcher(s).find()) continue;

      if (match1.matcher(s).find()) res++;
    }

    return res;
  }

  @Test
  public void toLowerCase() {
    assertEquals("hello", toLowerCase("Hello"));
  }

  private String toLowerCase(String str) {

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      int c = str.charAt(i);

      if (c > 64 && c < 91) {

        c = c + 32;
      }
      builder.append((char) c);
    }

    return builder.toString();
  }

  @Test
  public void findLUSlength() {
    assertEquals(3, findLUSlength("aba", "cdc"));
    assertEquals(17, findLUSlength("aefawfawfawfaw", "aefawfeawfwafwaef"));
    assertEquals(-1, findLUSlength("aaa", "aaa"));
  }

  private int findLUSlength(String a, String b) {

    int aLength = a.length();
    int bLength = b.length();

    int i = 0;
    int maxUncommon = 0;
    int count = 0;

    while (i < aLength && i < bLength) {

      if (a.charAt(i) != b.charAt(i)) count++;
      else {
        maxUncommon = Math.max(maxUncommon, count);
        count = 0;
      }
      i++;
    }

    maxUncommon = Math.max(maxUncommon, count);

    int diff = aLength > bLength ? aLength - bLength : bLength - aLength;

    if (maxUncommon == 0 && diff == 0) return -1;

    int uncommon = Math.max(aLength, bLength);

    int solution = maxUncommon + diff;
    solution = Math.max(solution, uncommon);

    return solution;
  }

  @Test
  public void licenseKeyFormatting() {
    assertEquals("5F3Z-2E9W", licenseKeyFormatting("5F3Z-2e-9-w", 4));
    assertEquals("2-5G-3J", licenseKeyFormatting("2-5g-3-J", 2));
    assertEquals("2", licenseKeyFormatting("2", 2));
  }

  private String licenseKeyFormatting(String S, int K) {
    String replace = S.replaceAll("-", "").toUpperCase();

    if (replace.length() < K) return replace;

    int reminder = replace.length() % K;

    StringBuilder builder = new StringBuilder();

    int i = 0;

    if (reminder > 0) {

      while (reminder-- > 0) {
        builder.append(replace.charAt(i++));
      }

      builder.append("-");
    }

    int z = 0;
    for (int j = i; j < replace.length(); j++) {
      builder.append(replace.charAt(j));
      if (++z == K && j != replace.length() - 1) {

        builder.append("-");
        z = 0;
      }
    }

    return builder.toString();
  }

  @Test
  public void removeOuterParentheses() {
    assertEquals("()()()", removeOuterParentheses("(()())(())"));
    assertEquals("()()()()(())", removeOuterParentheses("(()())(())(()(()))"));
    assertEquals("", removeOuterParentheses("()()"));
  }

  private String removeOuterParentheses(String s) {
    Stack<Character> stack = new Stack<>();
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (stack.isEmpty()) {
        stack.push(s.charAt(i));
      } else {
        if (stack.peek() == '(' && s.charAt(i) == '(') {
          stringBuilder.append(s.charAt(i));
          stack.push(s.charAt(i));
        } else if (s.charAt(i) == ')' && stack.size() > 1) {
          stringBuilder.append(s.charAt(i));
          stack.pop();
        } else {
          stack.pop();
        }
      }
    }

    return stringBuilder.toString();
  }

  @Test
  public void compress() {
    assertEquals(6, compress(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
    assertEquals(
        4, compress(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    assertEquals(6, compress(new char[] {'a', 'a', 'a', 'b', 'b', 'a', 'a'}));
    assertEquals(1, compress(new char[] {'a'}));
    assertEquals(3, compress(new char[] {'a', 'a', 'a', 'a', 'a', 'b'}));
  }

  private int compress(char[] chars) {
    if (null == chars || chars.length == 0) return 0;

    int length = chars.length;

    int i = 1;
    int position = 1;
    char c = chars[0];
    int charCount = 1;

    while (i < length) {
      if (c == chars[i]) charCount++;
      else {
        length = swapByCount(chars, position, charCount, i, length);
        i = position + Integer.toString(charCount).length() - 1;
        charCount = 1;
        c = chars[i];
        position = i + 1;
      }

      i++;
    }

    length = swapByCount(chars, position, charCount, i, length);

    return length;
  }

  private int swapByCount(char[] chars, int position, int count, int i, int length) {
    if (count > 1) {
      String s = Integer.toString(count);

      for (int j = 0; j < s.length(); j++) {

        chars[position++] = s.charAt(j);
      }

      if (position < i) {
        length = length - (i - position);

        for (int j = position; j < length; j++) {
          chars[position++] = chars[i++];
        }
      }
    }
    return length;
  }

  @Test
  public void judgeCircle() {
    assertTrue(judgeCircle("UD"));
    assertFalse(judgeCircle("LL"));
  }

  private boolean judgeCircle(String moves) {

    int countLeft = 0;
    int countUp = 0;

    for (int i = 0; i < moves.length(); i++) {
      if (moves.charAt(i) == 'L') countLeft++;
      else if (moves.charAt(i) == 'R') countLeft--;
      else if (moves.charAt(i) == 'U') countUp++;
      else if (moves.charAt(i) == 'D') countUp--;
    }

    return countLeft == 0 && countUp == 0;
  }

  @Test
  public void backspaceCompare() {
    assertTrue(backspaceCompare("ab##", "c#d#"));
    assertTrue(backspaceCompare("a##c", "#a#c"));
    assertFalse(backspaceCompare("a#c", "b"));
    assertFalse(backspaceCompare("xywrrmp", "xywrrm#p"));
    assertFalse(backspaceCompare("xywrrmp", "xywrrm#p"));
  }

  boolean backspaceCompare(String s, String t) {
    StringBuilder toCompare = new StringBuilder();

    int count = 0;
    for (int i = t.length() - 1; i >= 0; i--) {
      if (t.charAt(i) == '#') {
        count++;
      } else {

        if (count == 0) {
          toCompare.insert(0, t.charAt(i));
        } else {
          count--;
        }
      }
    }

    count = 0;

    int j = toCompare.length() - 1;

    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == '#') {
        count++;
      } else {

        if (count == 0) {
          if (j < 0 || toCompare.charAt(j) != s.charAt(i)) return false;
          j--;
        } else {
          count--;
        }
      }
    }

    return j < 0;
  }

  /**
   * @param S
   */
  private Stack<Character> getStack(String S) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == '#') {
        if (!stack.isEmpty()) stack.pop();
      } else {
        stack.add(S.charAt(i));
      }
    }

    return stack;
  }

  @Test
  public void countSegments() {
    assertEquals(0, countSegments(" "));
    assertEquals(6, countSegments(", , , ,        a, eaefa"));
  }

  private int countSegments(String s) {
    String trim = s.trim();

    if ("".equals(trim)) return 0;

    return s.trim().split("\\s+").length;
  }

  @Test
  public void lengthOfLongestSubstring() {
    assertEquals(3, lengthOfLongestSubstring("abc", new LinkedList<>(), 0));
    assertEquals(3, lengthOfLongestSubstring("abcabcbb", new LinkedList<>(), 0));
    assertEquals(1, lengthOfLongestSubstring("bbbbb", new LinkedList<>(), 0));
    assertEquals(3, lengthOfLongestSubstring("pwwkew", new LinkedList<>(), 0));
    assertEquals(6, lengthOfLongestSubstring("rphqbgbse".toCharArray()));
    assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
  }

  // Too slow
  private int lengthOfLongestSubstring(String chars, LinkedList<Character> list, int position) {

    int maxLength = list.size();

    for (int i = position; i < chars.length(); i++) {

      char ch = chars.charAt(position);

      if (list.contains(ch)) return maxLength;

      list.add(ch);
      int length1 = lengthOfLongestSubstring(chars, list, position + 1);
      list.pop();
      int length2 = lengthOfLongestSubstring(chars, list, position + 1);

      maxLength = Math.max(maxLength, Math.max(length2, length1));
    }

    return maxLength;
  }

  // Sliding window
  private int lengthOfLongestSubstring(char[] s) {

    boolean[] seen = new boolean[26];

    int longest = 0;
    int l = 0;

    for (int r = 0; r < s.length; r++) {
      if (seen['a' - s[r]] == true) {
        return longest;
      }
      seen['a' - s[r]] = true;
      longest = Math.max(longest, r - l + 1);
    }

    return 0;
  }

  private int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    Map<Character, Integer> map = new HashMap<>(); // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      if (map.containsKey(s.charAt(j))) {
        i = Math.max(map.get(s.charAt(j)), i);
      }
      ans = Math.max(ans, j - i + 1);
      map.put(s.charAt(j), j + 1);
    }
    return ans;
  }

  @Test
  public void groupAnagrams() {
    groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
    groupAnagrams1(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
    groupAnagrams2(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
  }

  private List<List<String>> groupAnagrams2(String[] strs) {
    if (strs.length == 0) return new ArrayList<>();

    Map<String, List<String>> ans = new HashMap<>();
    int[] count = new int[26];

    for (String s : strs) {
      Arrays.fill(count, 0);
      for (char c : s.toCharArray()) count[c - 'a']++;

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 26; i++) {
        sb.append('#');
        sb.append(count[i]);
      }

      String key = sb.toString();
      if (!ans.containsKey(key)) ans.put(key, new ArrayList<String>());
      ans.get(key).add(s);
    }

    return new ArrayList<>(ans.values());
  }

  private List<List<String>> groupAnagrams1(String[] strs) {

    HashMap<OrderedChars, List<String>> map = new HashMap<>();

    for (String str : strs) {
      char[] chars = str.toCharArray();

      Arrays.parallelSort(chars);
      OrderedChars chars1 = new OrderedChars(chars);

      List<String> anagrams = map.getOrDefault(chars1, new ArrayList<String>());
      anagrams.add(str);

      map.put(chars1, anagrams);
    }

    return new ArrayList<>(map.values());
  }

  private List<List<String>> groupAnagrams(String[] strs) {
    if (strs.length == 0) return new ArrayList<>();

    Map<String, List<String>> ans = new HashMap<>();
    for (String s : strs) {
      char[] ca = s.toCharArray();
      Arrays.sort(ca);
      String key = String.valueOf(ca);

      if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());

      ans.get(key).add(s);
    }
    return new ArrayList<>(ans.values());
  }

  static class OrderedChars {
    private char[] chars;

    /**
     * @param chars
     */
    public OrderedChars(char[] chars) {
      super();
      this.chars = chars;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(chars);
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      OrderedChars other = (OrderedChars) obj;

      return Arrays.equals(chars, other.chars);
    }
  }

  @Test
  public void isAnagram() {
    assertTrue(isAnagram("hello", "llohe"));
    assertTrue(isAnagram("anagram", "nagaram"));
    assertTrue(isAnagram1("anagram", "nagaram"));
    assertFalse(isAnagram("rat", "car"));
  }

  public boolean isAnagram(String s, String t) {
    AtomicReference<BigInteger> valueHolder = new AtomicReference<>();
    valueHolder.set(new BigInteger("1"));

    s.chars()
        .forEach(
            c ->
                valueHolder.getAndAccumulate(
                    BigInteger.valueOf(alphabetMap.get((char) c)), BigInteger::multiply));

    AtomicReference<BigInteger> valueHolder1 = new AtomicReference<>();
    valueHolder1.set(new BigInteger("1"));

    t.chars()
        .forEach(
            c ->
                valueHolder1.getAndAccumulate(
                    BigInteger.valueOf(alphabetMap.get((char) c)), BigInteger::multiply));

    return valueHolder.get().compareTo(valueHolder1.get()) == 0;
  }

  public boolean isAnagram1(String s, String t) {
    char[] c = s.toCharArray();
    char[] c1 = t.toCharArray();

    Arrays.parallelSort(c);
    Arrays.parallelSort(c1);

    return Arrays.equals(c, c1);
  }

  @Test
  public void findWords() {
    assertTrue(
        Arrays.equals(
            new String[] {"Alaska", "Dad"},
            findWords(new String[] {"Hello", "Alaska", "Dad", "Peace"})));
  }

  private String[] findWords(String[] words) {
    String[] patterns = new String[] {"[asdfghjkl]", "[qwertyuiop]", "[zxcvbnm]"};
    ArrayList<String> out = new ArrayList<String>();

    for (String word : words) {

      for (String s : patterns) {

        Pattern pattern = Pattern.compile(s, Pattern.CASE_INSENSITIVE);

        int count = 0;

        Matcher m = pattern.matcher(word);

        while (m.find()) count++;

        if (count != 0) {
          if (count == word.length()) out.add(word);
          break;
        }
      }
    }

    return out.stream().sorted().toArray(String[]::new);
  }

  @Test
  public void fizzBuzz() {
    assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz"), fizzBuzz(5));
  }

  public List<String> fizzBuzz(int n) {
    List<String> ans = new LinkedList<>();

    HashMap<Integer, String> fizzBizzDict =
        new HashMap<>() {
          {
            put(3, "Fizz");
            put(5, "Buzz");
          }
        };

    for (int num = 1; num <= n; num++) {

      StringBuilder numAnsStr = new StringBuilder();

      for (Integer key : fizzBizzDict.keySet()) {

        if (num % key == 0) numAnsStr.append(fizzBizzDict.get(key));
      }

      if (numAnsStr.length() == 0) numAnsStr.append(num);

      ans.add(numAnsStr.toString());
    }

    return ans;
  }

  @Test
  public void isSubsequence() {
    assertTrue(isSubsequence("abc", "ahbgdc"));
    assertFalse(isSubsequence("axc", "ahbgdc"));
    assertTrue(isSubsequence("", "ahbgdc"));
  }

  public boolean isSubsequence(String s, String t) {
    int sLength = s.length();
    int tLength = t.length();

    if (sLength == 0) return true;

    int k = 0;
    char c = s.charAt(k);

    for (int i = 0; i < tLength; i++) {
      if (t.charAt(i) == c) {
        if (++k == sLength) return true;

        c = s.charAt(k);
      }
    }

    return false;
  }

  @Test
  public void checkRecord() {
    assertTrue(checkRecord("PPALLPL"));
    assertFalse(checkRecord("PPALLL"));
    assertTrue(checkRecord("A"));
    assertFalse(checkRecord("LLL"));
    assertFalse(checkRecord("ALLAPPL"));
  }

  private boolean checkRecord(String str) {
    Pattern pattern = Pattern.compile("(.*A.*){2,}|(L){3}+");
    Matcher m = pattern.matcher(str);

    return !m.find();
  }

  @Test
  public void findTheDifference() {
    assertEquals('e', findTheDifference("abcd", "abcde"));
  }

  private char findTheDifference(String s, String t) {

    int sum = s.chars().sum();
    int sum1 = t.chars().sum();

    return (char) (sum1 - sum);
  }

  @Test
  public void canConstruct() {
    assertFalse(canConstruct("a", "b"));
    assertFalse(canConstruct("aa", "ab"));
    assertTrue(canConstruct("aa", "aab"));
  }

  private boolean canConstruct(String ransomNote, String magazine) {
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < magazine.length(); i++) {
      map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
    }

    for (int i = 0; i < ransomNote.length(); i++) {
      int freq = map.getOrDefault(ransomNote.charAt(i), 0);

      if (freq == 0) return false;
      else map.put(ransomNote.charAt(i), --freq);
    }

    return true;
  }

  @Test
  public void detectCapitalUse() {
    assertTrue(detectCapitalUse("USA"));
    assertTrue(detectCapitalUse("leetcode"));
    assertTrue(detectCapitalUse("Google"));
    assertTrue(detectCapitalUse("Google"));
    assertFalse(detectCapitalUse("FlaG"));
  }

  private boolean detectCapitalUse(String word) {

    boolean areAllCapitals = true;
    boolean areAllLowerLetters = true;
    boolean isFirstCapital = Character.isUpperCase(word.charAt(0));

    for (int i = 1; i < word.length(); i++) {
      char c = word.charAt(i);

      if (Character.isUpperCase(c)) areAllLowerLetters = false;
      else areAllCapitals = false;

      if (!areAllCapitals && !areAllLowerLetters) break;
    }

    return areAllCapitals && isFirstCapital || areAllLowerLetters;
  }

  @Test
  public void uniqueMorseRepresentations() {
    assertEquals(2, uniqueMorseRepresentations(new String[] {"gin", "zen", "gig", "msg"}));

    assertEquals(
        1,
        uniqueMorseRepresentations(new String[] {"rwjje", "aittjje", "auyyn", "lqtktn", "lmjwn"}));
  }

  private int uniqueMorseRepresentations(String[] words) {

    String[] alphabetMorse =
        new String[] {
          ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
          "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
          "-.--", "--.."
        };

    Set<String> seen = new HashSet<>();

    for (String word : words) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < word.length(); i++) sb.append(alphabetMorse[word.charAt(i) - 'a']);

      String morsetransform = sb.toString();

      seen.add(morsetransform);
    }

    return seen.size();
  }

  @Test
  public void reverseWords() {
    assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
  }

  public String reverseWords(String s) {
    char[] array = s.toCharArray();

    int i = s.indexOf(" ");
    int tmp;
    int start = 0;
    while (i >= 0) {
      tmp = i - 1;

      while (tmp > start) {
        char l = array[start];
        char r = array[tmp];
        array[start] = r;
        array[tmp] = l;

        tmp--;
        start++;
      }

      start = i + 1;
      i = s.indexOf(" ", i + 1);
    }

    tmp = array.length - 1;

    while (tmp > start) {
      char l = array[start];
      char r = array[tmp];
      array[start] = r;
      array[tmp] = l;

      tmp--;
      start++;
    }

    return new String(array);
  }

  @Test
  public void reverseStr() {
    assertEquals("bacde", reverseStr("abcde", 2));
    assertEquals("bacdfeg", reverseStr("abcdefg", 2));
    assertEquals("bacdfegh", reverseStr("abcdefgh", 2));
    assertEquals("cbad", reverseStr("abcd", 3));
    assertEquals("a", reverseStr("a", 2));
    assertEquals("ba", reverseStr("ab", 2));
    assertEquals("abcdefg", reverseStr("abcdefg", 1));
  }

  private String reverseStr(String s, int k) {

    Queue<Character> queue =
        s.chars()
            .boxed()
            .map(i -> (char) i.intValue())
            .collect(Collectors.toCollection(LinkedList::new));
    Stack<Character> reverse = new Stack<Character>();

    StringBuilder sb = new StringBuilder();
    StringBuilder solution = new StringBuilder();

    int step = 2 * k;

    int i = 0;
    int queueSize = queue.size();

    while (!queue.isEmpty()) {

      if (i < k) {
        reverse.push(queue.poll());
        i++;
      } else if (i < step) {
        i++;
        sb.append(queue.poll());
      } else {
        i = 0;

        int stackSize = reverse.size();

        for (int ii = 0; ii < stackSize; ii++) solution.append(reverse.pop());

        solution.append(sb.toString());

        sb = new StringBuilder();
        reverse.clear();

        int remaining = queueSize - queue.size();

        if (remaining < k) {

          while (!queue.isEmpty()) reverse.push(queue.poll());

          stackSize = reverse.size();

          for (int ii = 0; ii < stackSize; ii++) solution.append(reverse.pop());

          break;

        } else if (remaining < 2 * k && remaining >= k) {

          int tmp = -1;

          while (++tmp < k) reverse.push(queue.poll());

          stackSize = reverse.size();

          for (int ii = 0; ii < stackSize; ii++) solution.append(reverse.pop());

          while (!queue.isEmpty()) sb.append(queue.poll());

          solution.append(sb.toString());

          sb = new StringBuilder();

          break;
        }
      }
    }

    if (!reverse.isEmpty()) while (!reverse.isEmpty()) solution.append(reverse.pop());

    if (sb.length() > 0) solution.append(sb.toString());

    return solution.toString();
  }

  @Test
  public void removeDuplicates() {
    assertEquals("ca", removeDuplicates("abbaca"));
  }

  private String removeDuplicates(String str) {

    Stack<Character> stack = new Stack<Character>();
    stack.push(str.charAt(0));

    for (int i = 1; i < str.length(); i++) {
      if (!stack.isEmpty() && str.charAt(i) == stack.peek()) stack.pop();
      else stack.push(str.charAt(i));
    }

    return stack.stream().map(i -> Character.toString(i)).collect(Collectors.joining(""));
  }

  @Test
  public void firstUniqChar() {
    assertEquals(0, firstUniqChar("leetcode"));
    assertEquals(2, firstUniqChar("loveleetcode"));
    assertEquals(-1, firstUniqChar("lllcc"));
    assertEquals(
        -1,
        firstUniqChar(
            "sdnvlbkrmtbollujsdjfjfppksravjkwwsimlmdtcmiilpjibjhcppluisqbqfwrjjlrapsmcwrsrnfrmtjrffpuuqwonqfjfqxellpvmcfmhxccljqlvboioelpfcawrxlwsajfaiehutvogduhobwgpogvatpbvoaognbepqnkhkjsvqmfaghavopppcjbjunuaeotpkbfsmeqikjflakgjexnqqgxnsdjolbjbvhreighxhkihwphexwqufasjakmrdrpwciefaiqsaifmcfdeidhmjekoorvcuxtejlrfscrjekfkcnsdhhsxenhkuanafdpnsjnepdmvvrhbxwuhmrkenrcwbadsiulpvcklhlburudrbbskokwnwqktwjxstsvebvpqcugxjebcivudojorntphtscxhoxlhteuqunhvrsndtabfpdqdcsuqmdbeiiexdkaqtgkncfatlawrudbausifpsicibgrwastcxkjasmrmtrchbwlkxnbsxeaurtfdwmjqkgikcctgccsisgwlkkwflvahasltfusxogtbejrderxbqshacgsrvtqpgdcwmuwuejcmqanirwthsaxihlowgwjeegdltsujtrfuhjnqjjefqjwubiktuglirmgcdqfolvmrilkrvtlrtntujffbrtfiwdtvtfrkfnwklfulecxtkvqpgcrqvgjxbebjlbiopbnhqbdhhxsmgpdojhupxnfkmqsjlmcserhsiwhvelcufuvqrvpwhckexvutuwgfbgnqsmpmfuqkrvwggskovlqtqnqxvpghqoomplnnkmuhkbwpaxuhrkphbgwopxikisuegmxhbjocwhumauhiegojccvibwfdrdnahnwduvnuxjcuncrcgwwrjhxmdemnuxcjspjisgduuoojpxluvehoqpctncxniatxqckhmggtwgjihfnxdhbjwjhuqwuoveibqppqrmxrmaddukgcxpubmvgidcvwenbisercjiwvogdsgaqgsmahrlhgguanubvdptiofskqehtugwjdtgmcfdbpcovmvkntwsmtlcvrpmjclmsasnjtbfolgggthxjxplmkoafjodoevblwxbhctberkobjdqbidfsktudhcnsrexmetkowvxmrxvixbnfgrpeeuwpmskmbjvfdvjnippnedtbupouibddxahrhwdofdlvsnmwxakbsbxfrxlagdqpexnakowwnlkvcmnxmiogtwldgiepsigitkbnklunqqivlrkbwhuofmgfccjkrxffdamqbuglhcovmmnewgfptkabfwlqgrfhnburpbtajjmnfwmamehrdjkaalalxvwwaglidkkthgtuxwjehjegvxcclkrrudbknoqvgqfobarlsetjpgdflhjxfrxdodudljkxadlhghxcfbjigrmfcoxwxsabfgqilathijexhuwjjevobvgpvgfrrvotilgdvskfoeqvlgnaofwgdxdtborgsqxrqkusaphbldgdsntgtumhsbcbjwfgsvfsmmuonpclpxjxwskficxigsdadxpjslhhvqehdddsrxgdfljlowxkilobxqroctficwgedsspktwvpdibnrevillfwrsxjmxvuhqxmvhkjtrgvhxxecpjnkdjisrcqgnwtkmwxscdhihvpjgueboghiuieeqqwbpkohrklmukmbjueqnkfgsdigxrdporegntvppdjqdghwfrmnuhncubwteturdvcuppsawqraabqaffjwtmhmcibhepauhthunhcnwlcdebbgxqqwkbreoqsdvvpxcutnhcqtpcfbnxpavcmcbvulobiorfqkmrphgssmjgblfsnxfslmrsxaukmqhhtgfxtsivihexpituqkrjntigltpvgsgcrcwbopgmlejngagtlriidpishuhavdqqcpfwrnbxtmghhlaqufcffjpwuueectmxntdnmepwxouwkcaddmxicipbrsnaukcalqrpvvsjasesgmwerrxtdewnhpuwebcktovuxxrxvretussdojcmhowaocqmkxepgtbuisubpcwrtdmxbgqnpuwdntclvvavejthrgspvfjeupmjlprekrgofcatvkofuafubevbidetgwkkuenjbkojnitulgghpowubdmxqlrxcbwxcouoejougudoxritmngxqmxaenpqhhblqmxkcvogxxbiaumbeumfpwgomcggxbbfmxmumpgqbxddhodqgekgnjrpkqgrpfpuxvnfjnwsdwdpexdrlexjlnredcxajnmjiaqvscwuhmxufawusutabdemknrffbguxaxmwdhokxjkvikrmeprwklbvlmgsxxvolirwcpxgbpqcprqjnjntcxrmdqdbroldtnfsghitjlavokiigcupuwnifngkgsiwhfjjxijqamavumcsqkvtpknjsugmxnciqiktgqkonsrdjjgorkswdjrojlsxdgkjvoljsjdfanswoiufdqjjjnjideducfxiwhtdtkemkfvxbrmwwwduqmmwonrlvcdntljijselbcavvmgcakxwaidruqmdkvucvcgrftetxenrhbbblgpqsaoietlnuweudmkghuummtjljqoswbkbfefexiqiqfjoornofnmnxxhscbvvkkpkruinugbvisuwerxjbgdhirgpcnoeglhswigcdfxelfmeqfhqkxlrhpvbksqmkdkkvabdnfcmjptbxdhdrikuvkojrbvixkhbfupkrnjfdoxvlfvrebbijtxoqltguiqihurmxawaimrxdrstnsqulqajfwtwjnlhrboupthvahjaprnimuormdlcsevjwrrngbaashntmbpxxtqbanvwdlpqhtaxeestdnnahuillicvebrobwlmihtdkfwhchwasgwfgsqnfgurgsjwhabkujamvjvriivnkslxakealslelwqkdcpjniwthpoxpqgbdlmvukwueuionfgbmlniwmfqbwrxwqsnwxridlmkaulwlhldcfkiefujkagrfwjjrqjptgxkxkdfwmosfhpjjdujkmugfmtupnntgbglcwskveiegaekewworsnnvahwvovltolgqawwixrjnsbuwoqqatxhfexjnlbiduecsiiapnjcimgovekjgflugprswurcnldaxgqbdgipbodjdgwgerjlhrombjuajcgihcwqdtvpxixmivcsihanqkgvvkekqdnjknjopssslkdascasmarrjsbvsxxcbfklrnctivnkduhmldgracfitjgvaqgnmieitubwvchurhtrlsvvchsknpxfqviojmsniqgqwlpdnjppifewefrwimsejhpdbcnnblbamohnotsfewfvowrmtaittermlafnnlspgnfcmgfuxmgkgiunglarhfhkaxbaucqfexslxqqtxxamgvajrnmhmlbvoxtmwxejweoqatldgwdmfmwxnxsmsmxmhjjrmsgdferqonwmldxrmngijxtjksrittuqwlwebxtebibnxvtesaqufhpwtmoiaxubccmuafdtbddobebehflkkngcfkxnqlrlsxhcuwcopvfirqhsemasptavjtuwdjdxfcixucamcahtxcgopnivmaeawbvmxqrmdthcqjvwdtrouaghsqalktudmlshoxmlvceebtrebwskptoofvttqwwodbsohqeeshxnwbtnujugimqshnqowacpmtxjdkboldpuajmceeftarseqgotubcjojxsjnljomwjnvnskqgbwlnlasodtbnckelorrbpvekfxttwntxxstjiqxqncqsdwssadgmvqjmqfnbfgoxovqnthpmdswslemogqumovfdjiroatbinobtdvickctnsmdfqpurxgwjbprbpgfqfniridebecitsnvaxretddoeikmdjpiivduimhcdbcttbwmptmnsakevnscuwemiwxnaeurgitfxpcwslsivgfquqotuxxhqxjdrlpltcehnvgnjillfwdjrkwhkuqdxbrgkxshjwvpwsmhdbnxkivjedkoaevpguqonqevsvxpspegjpsnutchuclqmaadfpvdqxgnepbgqmujtbkucqkmlaafhaqgecbnmehxqlltsiwijqddohvohukvjtwctpsurohmgcgagmrtdmxugouoiphicrjosmkagfwsgtimfhgkiefolaomqjjpbtbkarbcflncapmhxkensiobftdbgbiihjwxwhgimoletnhrundehcjntiutjptvekrdmnbemoajiglkthlxqposxgatcvkmixrxcmcevevpprhkvqfhusnihnxcbifxdixdamrkgxbaqxebscisvjphuxkhwleeftravmhfbiudtiahkkubbmtpgsarquiffljmkfmmmxxxpdfmflcvjucbfwbkrdrjmoxixgwbidjgnjlblsrmfrclauwrpbfikvjneqxlvvspnjbulkjqnkfgrtgwtwgcxpbcbdgstucchuhoortxjatdonjamblhsnxwgwegkbcpdjfiehotassiapxrmknxfgrdkbqxbujwjreivfctdrepotjictccwtbagfknbmipiesajdapcubosjwfvbniodwvcccqsrocjrchpagxnehpcjhrgxdjnxbsohjquutxmqbeavxtebqjpcdsndowsxbhjnwogfpwbtbjmrilwclekecjrnnfpsgunlrqkldixprfdnthnxrqiiuwmgixsqibaktiopolipkanjhhpsjnmwccmwafetiolslutjqicidsrmvlnqdlmnttkeefnbndocbobxkigfedjwtdgehkkwptgbmrvsiiqdomqutreufldqdauuokuahiiubadphgdurqpgdflwpticfafqhllekupokbrxetnnkjhotdvkbjitpucrlfgbqonghovdhrpicwqdscaqmwsudvlhibxasoqnqgilowrlibkgqufkokxvjribjferoljtwrkoledombvogamnbgavpkbqtdglxxaxqxknwbesowbukgfeniduhpfdirnwsjmshaspnhnpxafphwpfqcqljaftgrburjptseifkfoeopsicququtjklebxqrscdhptrdnomtxruuaxveswrcuqpnrqltplrefruvjlubaiqnxthcsxfbtgmcnorgjnjgjoeldubhjpstxnitmkkxulddeikciifotralvqtfbocgxujvhipmqicqaououffucdigvqsljwjhqbbrnaviovujxrbufmhjpufnedhrjogmctbaeldlijopkratcuflclooscrpbhbpvtxifjrfuukqqgsmmghedrdlhmrwrjlwrrftarpgdxhfdhvndjdcknvdkknmqdllnelpmdggfkfxjudxpothxqtffmhcxobfsuxwlanfcenedwmcaexkjjvtorhbgrferstptaeejowgrnpofeftrbeuwbthvnaaawgknkebepatlaemiohrxihivwfoucrapqrpjhmvbamnjhtcxrewdfghsvvvlpbgawdvxxnjbbbscefktvnrvrjrpjjtjepcjduggbfeerpexvjkemrcmdrgcxdspklklfechngfbrxkacxeblajjunwuccjnjnxwuehlmpgkapmnmphmvswetgjfapqlqobkcdgagbgvhskwtcllaakvbsaejjxqqfixmspwsdudnfmswaphujlegtbdxiwgqlfuvhcxjfkmlkclfhficnkqbbfqpeftjwgckmarcixiqamhbrmfkvqdssvedgeidqnbojhfoktwfpcxsneitattkixpdnlffrqrxmrteogpsifsqevxgonbbdvjhsdjphjkvxetvjamoctscmhlkiuewrojodsodmwtjskfjvktcgvvqpluivcokmbjisrcohrgdtsaxjcmfkmudnhkpfgvveahwxjwlbrbkedwavjrqawxlvmuirpsgxsmiiacqbonhepflcqicifghhtddihfvphxvhiwfhqrjuvgbxvkeipbxlkqhcfgtthkomqjtmhpblvqbejxwcgxnsdinirvqectsnpecolvwbpfecnphnjjxdegmqlrdiausuxxrxemjxllkaqcqcapnwpjfmdmvsjtvapsahsusdblkorhaqtbiudqxubenpnquchfrnnvqpjvefcxihcphdhmtxojurnvxrpqrjopedlrceklpavqugvvanifshpbnvxsdltrvwihblbiobuwmkmlhoihwjmlhoxefunmuaggukhrmuhpbrewqlhwiinumnxwhlviarhhbsefcjgmlsvvsdtlklqmvbqasarfkkbkicitiprmsghsoowwjshdjsqsomltccohkltufgbbmdcltfwpqorarbitswkrxjmghunicweruuwjshfveofmevjupwvrjxfogvwpwidvqoedsussasvathbgufgjsniaaopiktxvjlcdufueckisovcwsjuspdmneagffhwbwsbhirvushwsubkxrnputhlstcveqfkjhdsivqecsqxsgsdiiwxoetmsmucwlukegpdgecjnhwditjeihoursohuixxtfeoljforeamhlqkomfuipuooormpokkconfoqnsqojvowwusrwniiqnticleqvvnaibnmagnjwmdaarurvgqblcahmnttbxkqurauemoovkoppcwejphtmdpteamrnhsrsngsmlueafwocnglkihvmrgwevgsjhfbbnbtndpuhgvlrlowqjwarvhgdvdwnwkajbxbrmwgjwrpvnumetvenfidjorlsedhccrmpodjoldbfjbwvmuialrohrbmhrwovwomirmooxlsjbmttvkmqnuwuhlhffembgahdvsukabbsupuxorkpnauapwucjnkolvaqkfmiauhxrajqappncmaeaevrljxntxnhbvnlnoticqjpbvwiurjpulxxmimvadxbarqarngwghmkltvckparsefgfbagamvvqlfjlcqivfedmjukgomkshmnunmjfbplcbdvlfaghsorowujhfglmmxelmqvnrhummtvrnwquffnatvvvwgtasmuqodkkflpaeuqfeepupgwsbojqpdlgdtuvdfrdodeeqhwxapchquetnpcbrnkwffeacpbqrqecjlxhsscvqnqvcssotnaajkelurcrbjwjajefstvhpbqhhmqiplfuxbfvbtegeicrlruciknduspjlqeqdwcdolxwjdahesqsckamdaccrnsupuxmbpodipqoeefmhrjmpugmuvsnumtolqivpwbfdwsnbumfwfupjaqtuaxkgxbihnxewbekqiedsuelqoklhogxacrdogmctdvafdoipewindfmxkkisfostcwhgivfojraosxjhkrmtjprjmuokgktejanunlxritwpxiectehtittikqvwrjrcgpxrwobnhebbbarwpasxniibfxoqnmwwsjundxuhhdrmjphmvdtfdwkpeihwskbifquidnhspawqooqmgpehagpjxlrbjtsigthcxirfqlierllwreldmwjwarfjcqrggdnkwjepknpcbbijxqsbqjppnrswepwjeowbbxpjentqpfpwqvhduejfodxiqdjkrgcppcstxemhlragtduhpbxvuqwovrsretbmbdfvsicpuetxriqpspjsgpojmlbooeuapaawodvpdwjrrwdjexhcwihbvixbojrcielqsnojpqqoeeewoijhjtqohqkrvskevuwjxftdbrrertvfqsppbjtakeditqnufsbcvlooasvujcvqrvbdgrpwhdluxsutbfelthfsvjkhljenrspcamergnagkqniavhtjbcgiaskaqlfsacwfrlcwtufdtcvtdlebcmuawdiqiaarxafaiswaewtlcbabwwuicccfmppcaugbakjpbsauinocnestocebhwjijfrhbxghkrpkvlhjalihuclrunmkdpjrnnewrsswftktdwkhtposidlchbmdfvsirhracliukcbxljcxbjluixchpelnoaldmdxeemrjqndjwwhjcjonvoxhnqkpfawngjilndfgxerwtlrupxwmwqltimctcnbmpbcfhcldqwejccpbpdfstfodaaqwfpfiqdgrrqvxsnjqukdxlvrgmwigweplxgstqhmxeejtvaddqctpbvcrsjbuiawpxdpkqvmrhqiwjhatgmqbmustupqwjuenvmwqgvwusdsbxkciwxcmbiqtkbweoakdklfkeiblwnxeorhjjdaikcdjnvdnrmqrvontsmouswoohnaxgiexwlmomfpcragaohorolnpowihqemjgouwvjlkphscgneqgeaecdwfcfoadhviinjiiwivxnegrbrqepcnwoboxtvjapjumotswicntweobvgbqpnmqlnjmjajossekhkhxjlvdbtbgkukldrikbtwrfwjvknidjfntqbdiqrhrgquuvwsdkmhhqojnnvsonoedfvpjcwsjlvgtmerufnsfdjaovrxdjdvgqxcwhjhwakrqiloecrhtmhoraqcdpvqnrguhpubruonmjcqlrpxsniegilinvduhaemnkvwbbsrghkqbsojjemkteturfxsuvhlpafggwowhttwpvpwadkpkefeigxruaqqgsmsilctwlvruscgiblusauijhnsqjpeqokciqfrlixsjkhaaopiuaxbuchnxqtppfgfvfjpwkdivdvnijouxevpcgvrcggidqrdismgakpmiwgmbuixdupjrdjgpbwasndwixhgaomfceiorousianqmrroilmowgutnmcwwacmutedcquvpjptiapovcqkxbufpsewtpjrfvksmagpxvmlhijbmicgdatchpxaocalfabdghtjqfnqirqmhlrqucqkmerrxtiseothqvlkdslrlelkgvpabgugoclmorkitgwgkcvlpcewjhubhfcxtwpgohnguulccqdhvxslepibrdrbedtnrrjrdvxcilolxtklikldthjxljleuhkjdxjqntawtisburdcdvwtduvdipkharotsibkvibcudgmibftxodkevokphgmxjjveupasgladstvmlcglpujhcsxaahdrhbmllereednawbeoiqmxxqpkdjaujeugthtxoqotkueepqirgifmfoniqfnkugfxvtqptsxqifsgvqrsokqbdxkgjnpcivrtfnttdmtkonsxjibtqfwgilpwwqgfvrgcmendhudcevsnbbwtntgdujthphrjlkffabgafdwpcvkkxlasdkjrxtxkmcjjspvdlnvrxjvbbntorgijmloxrhdvnbfcsjgmdnlglgpqxvvxjcfxwgowkwgcdtogdqkbcuxqtbvvfmpxvgimtsrnkqbbubuufmhbobswoughelkvhmrjjjoekvlabuomqfferdtatfewsdvkwspncjodinnrlsmxvlfdktdgmfetrjrfkbdaftqtvhwuadqecfpnrrcjamavpqnglimcsxnlkmphsbxtjmskwnxqpbqegpkwbrlebfedneieicuvaagkbplebodbfvqlusancmweqpatcaujbrmwsgvewjcdtdwhemkofvdfjuikhjshcwmfrlgqwdvskrfsrpjwsbcwcfhtjneafroqogfacmtgjpeltdwvjouqcannxgklbirwjpdnalmaiuusdibdseajbgurjdfagmvpswxwbkjhhcuppvrrwfhexxcfxdfaefddkkklgxcmghflithwlpiwudxebxjklctthnmnvdgovepfhklkcuebabmdapcolvmosnmtigwfnrbfkklweckutdbekdxmuexueddfvqjjevlmdtcbijlavpnwqcbnoadoewiugqattkobbeodvtxtwgrjlvqqbbblbqepkatstgsudpivfgfxopmwrntffpitvxgoaoboksxolkpohewmnupmmasntbboxvhssdcakwlniweokljredbdkwxxbaufpqhcpgqhrcjescogimhobvdmnrpuqviiuivwxbqlbvxqjagmaoxdgsvgtxvkrgvvixkgcknfjqvdnodlsdcjbcobgkgddlvwsdvlalfurevegvwqgbixmnwwfavwfwvclwxejsaaqgulmvxnurafochlqjimbnarbwhkwiprqbmrqofrflktgtglppxegjemnmotnmcqiuvniarehxicooodcjlolsjkpxmbxiiuqwxivskxdvxjvdfvbomdcqimqbiefsrwiqbaiorjbucbtmtimpjskdbigakcvlhnrkdhxkntkvgbqqbuduttdklicntfrlprbmgnugxluiwbuwxspfbgfphenadfpkucjwcanuplrvibxhebwjmebimkgrbirperkxwkulesquvkrtasbbmunoduupgqwdxncjldicwbquultcntjgaxcqlkrekunxwokkirrcwhtwvntdroegadqqvkuwufirrfdkwowdumidsokaulejgkaebwugekwrpbutujrnqjpqaibhpapjhmifofludtxjjkemttjpafqaktgbbpxugxklmiixbjdpojfutkosxadkgvlwmxleqneonotrnssnhvgudrbxtsinmixvnblpqoaogpeiaqmjlwxuevaemmxcnmrioehvmansstmvblkldeiefimjmvajrxtnxahaevklpdrijksnxqrlxbnqqglchtuniglhrubbnweiavvwwbcdjqehtfruipmixudbdwjmnevhnuefjilkugpiceajlutghepxrksexgntaqvlptmrqcpjidjpbbbwgohbmieqslsndikarvfieuftlifpcwgjejtpdqprpdwntmrewtgwivintnkjndshtvmqxtlhfxxddvughxinnmprbkkfsjvaisanqabsvohhrcsxmqrttjevnnekcphmgbahnipkwinivttqewtbbvgsgaxbvnblcjnmobowrgjhiqwgxkepqpfavknskurmhcfmvfhiohcebsvuwlonihjhjmfvclialnkjxeaoulshkihjjbpsankwtfxcflhxgrljhudrukqhilmmpnjhisqhcfdaskerbwqwchwjnfeonsmwjflvrvtqxubbbkroemagdndnqtwcrnqxpmnclhlcslrttkvvgmnjauixevxmemoxqpxwngifcrthadapavorjgpvksvmfaxwuarlaetqixifpejubitfhshijcrkeitlmwdpmbcvluwerfsecumucbiisntvcmliqsvdqewsuqwgfpkxtjrprksmveewpffthcxxmfqikdbbeabelxlwvnfwjgkwssdvsjjlbmwhwwfovdpvshqbfsrmptptmtdfqemxhjcwaamnbvmabarpksgpgurnubxfkfwkvntxhjmfiqrgqkxjmempxasoddwtaligeaiqtbhqrdmgggaakruubxbeajiabgdrlalkgbeevowcedosmhmnvsfqeemmgxftlhtvnbdktkxacrphtiftdxqshkmxovqoduqbtpusugqshbfbhmqxkjrciopbbitjeisvonompspgtcggprlotvaipnvjjbvtwkbtgctxfxgepemqmrqtffftwjwiuwqvqkfrexkfomuwdqovgmofrqiukrlokstxtqwiacmerrqcgfenmimtwpbdgfctecwjgffqbgpfwhmsrlgcwxmofqbqaouxkqrscqaixqmxhvhetkmtfifofvglqdrbldumgbqcukderljbfdwxruxtpobtxvslxuhvtjguglvkhjsapnmpbkkflklcvcndirkgjxqhfdithqslqjgtocwtibkwbueclmnpxdqlkdovjhrqqonthelhxdqautvhtnrhfgmpmsniwphaqmhgfbjxxqthvvsuwluaehofmapooxmdadvfaiqlbrnfneshewxqmovtsosfihibxmabhsinvovatpfroglimuacvtapnxebgooasnkmbnscapccemwqfabkoxiceqgfvbjscdmpiaojmttkvxsipcpdmkkhwjxllohbpmipiwavolpbspwhrcsfifgnssaealkdqpmmcaqaxpwoserwtbkxsheormqifdrsgdswfngiwqaswxvwoohsrruiswwokdemrdamcvkxengfnqjfsscgvkwexamiwvtriuaaiqudixgpafvlgmrtpccsvtjukqwdlaraaxvvdvbqpibctmnihjicowulvnvrlolvkrtuehpprafqjswheijucrxnitakaxghnphcvvbvhwrwwxvgpwtxaejscshhrsrvbpcndafdaqvencawitaurikrekcixpxbptkgixnoniksmtrpfagtnukloujfrhvbxlrfmkprnooiedxjbuxetjjbppnvaiuganfsokvtjbkvebjdcpjxlfabalujeuuadtaexnotbjxtnvbgrkodpdndabxcmtvhaubnhossugdawxgsnmnkeqjriqxwaeicojptfcaisbqeaaqphvvsfovjxacfbhttfbepjdkvejersxxfwoadpehmocsrxmpioexjuqxqkgjofnlulffpnkqcfnwqbmfcrdiawkpdliwcbjudfnennjjllboeqgdanuxggacmiqcgflkdflnuparxjhrtesdelsejvcrurgpsktveljavehxfogrneqemkcpskqkdfqegxcfogooimwvotwvmqjosdoqrgtgruibjvxsbpsffwgckhhfxrniegnivjcvnhnofdlfenowtdhorlsbvhsucshnasviigxtacmmabrcukprdgwrxrkrwhvqibfvkqpadrmjvkdpwuilqbmovqmxpgosoirwlgmogctcljfgcjbepekgpljgexkbcurbtaptakxrkgoqqqtubokcmtxporspeqwacewiddkubawmqjelwaudfiaanfflujpcpnmwvvtfbhwrjfgjgjphlbucidtubkeplbwdppdttmnbeubpqstiuwsnbcvumjghgjojrsrwfxldjvdlwkaeiackgsskfgtraekwtgfgpqjxirwxocwnrhepaecoukwhadvemciddngvbpcrbcwbnsrwpqfiqneiavusnoqnxbcvdifdhrisgcqvtfuilwxvotjahdpeecfhiejtxxshkxroemgwtwgelelfqjnasqidwswtdwoibifibuukjnbghjttqxrrbfsrodcsbldnnncstgeefjbdjsabjwdqjwebiwppsjkgnpdocsrnhvfudwshrodtjwmelmmewwslqgndwxatghoxdfbnbncdskeabekaeclepjkjhflhmqkvsjwujohbuveoahqlwwnbcbbqmobcxhtpqiikrxstxnkxbmoncsbtjvlqoxksaebmvsiqbbetdcirueeojfoqdwwbvtprwjstaauhqteqxvwwaxdxlaubbunurhhgteqwlrgfawkcvwsfhusoakmpuonhkocqvfkemogdhbhalegfhecuiunakmilqwqrfvrfmxksvvjucqrqbwjcsvpothfrshkalmuqtlmbmrlvbbmnnlrwpolpjhuxrchnpjlxqleptgsdsqoedkksxhafkdwwfxiibmwjfoiwbeigtmhnsmcespohaukpwnujahohinknpkmwnxxamjdwqowvrcqbtvdifpshshjufbotqwpvpqfwqophfmbdgwfvaplclngldihgmlguejowdejugcdhvjtarvqishqjaaovbwfwsgqnbdextnjmbwecqjbcbehbwiejowcjlmkdsjbvtficsujbuuwogvtalbxduuagnofqoophpjsrpdigpgqvcbnpacrblxeoksiumdhnhkgjsvdkhwoqfbmgveknlqahaqdpwqpptxnkbpslnfqghebnrxafmhdtxjorkejfkbwefmttjkhwhtsfosflvlvirnccbpnivarpwjirkojcxojekwroafgindwkctbgflguuriebijbnmgewckolehcklcchgpracgjgcmwhjetkvhdcxkchaiwrhhqwqxhggahomtgsruhgsglwnaeuthmsnmoachcrhehsbnmkpqpqtdcrlftrtoeuvjcvlbbxulxtodqrerlsamsebhpxqenbqhovnfesourafdcvpwrsxtkaqbjhdkcpnfqqvjsifhddwqkabxkbdxktqahhuwqpnlsnvpvjavhsjhaqbmwhkjkvigspclqbqstwnguphnmxusmwigftmikabkcjwgsvvbdbexxjthcasigjneahsukksmalhrodnarptqwwedrvjjhtrgfjmnkemvcxglnkhqxxkmbhrkriswfcmvfkjekwhmcdkevtmhelqabettkpmgcpvirccisasrwpufqweairnwevjheuhbmcnhijxkscgfanlvmlgaesdnljefhnnantddjwpaltcpwmaelkkjgohfxjufltmcdjidwlculvxpcdldmcjshfmhwoipavmhaomxoojgmomollljwvtdhwpkswimhvwilafibaeemxctjerwtjhnpldwjrfdwrceuaompisafxioeoekkpoxnkwevjhexkhdtwfoqqesgqppmivotgqjlmileufqiavfqbllewraoixhrmmjgaeehrstqmaoltgtllpftukmpipflqquddcxlaenapqogtxumcstnqovaaassvjwcadcotfxenmrthlrdhdnscwmjirmwkahlhpgxiwmmqmgbiabastekmkqkpdvibnxhavsiiwrahjvawoetscxgvjrtxvfoqxhulowadoqajtwpbosvjxheuexuoitjjpgtprcxrgqehjcarauenkkoscdvvtxccnmvfxhfbfrebeicntbsxeschumgmhrxuicavpmcelebxlbowabnhnxsdjtqsujcwixwhccosrabqhucuwnhnexefsgjjrqscwlnliajtxoffijxpwegbcmcogxsppggokxhaiwuxuewhvqfppgekxnlxogeffraddclptufwrmceosmlkdqjktpwsjntelprceriqdfhgwlmuovvpecwdngdbkhjmltqwgilisvtsixrxewlfrsbdtqqmjjoecmpiwsikbdcfmqbwfbguaqcsvndsmcmuxkaxpwhpjbmabvosiugoluaxunoenmbnsnjegmxhbvjdawwtnbcirgelxixstwghdfdpnfbrrbifqdbanxmfsgwvhogtpqsjddwhlhfokmsprtbjhgslheookhnhispebnjnqtlfswhaernhtfvshauodmshwmdhfegifjspuphbpsucjgvtldhehthljohlajtjjketnocsmphjkirgbdriagcccilirkrheexspnxcdawnftdclsquvtenidxfqridprulrmqblnjflpbqbwnnwemltndlomxfrxlhebdqeohsluexrbhotxefupobsviekatwwfbhwubmfleinjavfosdkkspmtjatcghrqkvkvacbcbsnagcmqheouvpvvdddhkdfkndxlsmaksoxnkggnligpihmvgkcoajjelrnuxlrmqefaeinnwrmjkhbssibhakfenvfkjxuomdobukaaquhenaemgspscfjurkomohssettxkbioqmaxhcotetexvdocxwabqmcwiolnvauhxwwrjuwpkbqgdmdibcddfoeeskwageutffjmlgfjruuhkpanfnqjnejdqhubnaacsxspnkkhbwxcdevifagtkoqkdgwickjegmaswgnsjuchvettpswjdfrdtotfnswsiwdfikosdbdpchjvmuxdpjfnqidrvrxccktijhgqfseqafaodvbbhbsacehkuxvwksjlcmucisftbmhwowmelwtuarwritxmrouhdkikupmrlxgndhbslhfrmaqpsovgogukdbtxrjtbomvdlxxeofkhmiglkpakdjsnuimigjccemvawpxdfhjwrkhepphkwpfbuilxmppouwvfewpvrxshcflusncnqbwmmqallkmunsoqmnrdacoqjktkuskdbxlaurumttilqsgcatuokkmkmbwgdgfmbqotmtgsejenflxmgbjfrheomjmhpqoiqvghdvpvgfpbmajuhhhxdapsbtnxkashnvmddhgfqpjxexcmdugwcguidwsfkatrrrxpcslcdwshgiucjbhbmbthblscbwagmqhkjgomxsdbsuxqlfbirxfwnboufsroodvwhcileulgatjvxjmddtjpvbvmpfxbpcxsgxiprmtuqemjoeikjthrkcxkwobawmqguexrijjusipuipofsqamuvuwmoqbfsfckkthhehgfwlovjvehbkqdcnrhnsoctapqrgtxmflchhxmloxqhavgabbqcrngiqtgaalikkrjllhhfqgfrplwiaqpgmiiinhmhhbcgokkbxwegdsfqrstjtemaufsuiurifmexjfvrumuxxbrlfcvujxojqostqqhajdpgtjbmrrwpffsutemikihaisbwvmesuqmkfiqspvuxeugimohwpxfgvjhvajbunkadpjqkebmouinbhjqoodbdwdehiwvwebolxmknctxnoxrxmwvxswdotaqvvscbsvvqpbfljfneilibmtxkipcquasoaxgfngesgitdhfwcndmagsookoiqcttlausrpmecvjcvgfchsptdsbrmhdlhurtmfbgfqhfisudcneifrxvdjcjdihuudskqqgsdhhptirocvrbjjkeqkuputqtanwqxhcnaevsuualunowwtcxjnpbgspqtugekvibhhhvcabcbfkesghfpltacfmsmaihrpnmojwbodvsiwvgvkhhjlqtqcastqojdxkhfjtoxvcecoptqptvinouhtjiruwkbexsnxvmohcagiakdotptbvrqarmwusscgeuguddsidwnrqmfwhedavdshwwbaibpgcxhppsuipgplhrhpqoprgsgckwdjkwqlprrqadeqnhtvgjasmlamiwereluphsevnttavmjhmckpddjwveokuwckewtaxndfcvgnigenghlchrblriqsitdxjpqwcxxkaxksaimscopvlbbskbhnffnpfgmqqxvefiguvukhqxfanerspvkjeubdxdphbpqmdssvxawlqswuxsqlxgsamqokpfarvvgjrlxmwfpxswsqakwwmcorerneffajucxtxevjcdvsguauxgusvshfoovbjlomgwmtvmtnmcjmueabgesxcfndrqpcmxdetbmxdncxckdvfwuweviftvhlfpdwujxaucrsbfbifscghrrtjrufaqogrtifsirapsirpnbwodhglpkafxrojhbthuwqporvwbuwfnkttfcnsofwajnrhcrcwbgobjroppcadflxgismkpsuknruarnlglqxscotmufxbfeinmhsfkkddembvtwpalteailvuidmimxicqchjgfslggahslnkoquvvftocmwomjxmoluafcrsljiuufwfphxiphfmdrnvlfddubqjvsqjltbfkuibvxgqpplftssdsrwodnduheagfuddtxvppiihfvgrkgukqvppcwnalrakcvwfbpfnotfebtgqxwdjhsxfdvxxjqqgknlcstgvkioxtgjcmjipnimiejriicjafnceamcnlgjisirpxicwdobbodrdcosslfjcjrjhqoqphrfxbcrvrmdwbtrrfwtdtmcugtjklhlmdbnkcanehuuifiqasxndgdesosuvssjilocrbcxdedlxkdwradpbebiudnrcgpsrkttcahgssmaexlemrkrpfpnklfripngihgawnaljxnufnrbcttlurndehjlxmeowrqcgjefaegwuvmvrahwapkksxsxxhnjrbaiimumhjqoxevrdgwrljwdmlxnioaplphsoghaxntvqvemgnpnnndnlsrossebkdmbpowsokwkfhiwmpsqxmgifgxbglcgamaecoqrnosnajjqtlbfhfkfmvshcjkwedkljuhvncwhiubjfqjjoketoikoxldcnjlpdbihmglpfanjbxfgmrpkvwoiwlfanetxannoesqnpfqjpastkmrdhrxvfxbllmrlihosufgbocpipdlawrknwxeloqpiptnltrkuljnmsqngbhogkitirsomxfgbrpjqbjcllemvtaatnfluvdenaimqcqhvjkcbsiwhthevjaqitwdsoiutdspfohjvpstpogfqdrekuahetxwahbesrpxvsevxuisioshxlefofccqnddhpelsubdxqvtvwdhwiofdtoorcmmcsrdpwkcemguehcchwvdrnipndriseaceqanoesrhphrdxvcmneghobumlldbmmpwbjkwrwhegutmqeferrxsbjnqmewwarjwtukgfeurrvrpcbaxeepcaqjjugbjsoawqjuxqfrqajmnfcwoevvfcvbiupgdjvfgwaapcbwresdvjamxdlcvwknunrkeemauhixalqcclgpqxkvnxafsovevfsikfeisstrsubovhgaufkpvskpsjxwewtwplnoxoxnttokkwrfdeosjlkqxxinawtesohbennuwjnnpkrxfvhpisapaahobpeduorkqgmfaxpxwcmfqofotxlvoefeqamlutnjljobseebhvuiojiorcvrxltfuqutsfjvngbqnhddqfppxvqisluuwnhjvrmlouwopnwrbsomlrwljjmlgkbfqkcjlsuvbruvapfnmulfxjimoxfawpmfpltfhttgccccjsodqulfvfhbvwlprtndkwemqutbjasuaxruumgftnjdxggxamtabulwhvcepekuxpvxrdmnebprlpwqjlpungjvdwrhdudjwadnwjeuvkjerjkuorrwvkggeiatfcvumtdvlwhsiwxtrulahujhjssmkjoigexnijviiswamoipuarhsgxwknuvwknvveddfjjwwwgojdopjvehbwtehcnjputtkrxpasggmqsdidhmrteatleridojvvjjosmadwfrdbbaixugklrdrpdefhvfrpxqqjcoouwojcsejammolcxsbafhosukifpavrdxebwijgqufbhfvawojdnnaphischwwqnigwiqxvmanjovxlngxueinkfeuaadmruiknnpvppvkgkqdlgitkjhnnhngvjopsbiavnpcuselosohxjxngfsagetxstaauuoiphifdmtishnnjoifqxjgnngbofafxumkdtaafofrrbhmxdvdqqvaushltaghsccwrtjkkpxovxlmsdvaganmkcvnhdkgxvhfqestcqdhukknceicbgjtwgbxuxvlmgdfcbutvaiepbtxghpclccgbnccaegusuohfaoklignwfgkadjrknxttaielrflbqbusbiitqgsrnlggqgfppimewdghotpqjlcjfknwppexsldbapibhwilamqkfhhrielrufbegcbsdnrjrlrsgawrosdgdjnkvfwufiunmndabkqotshqmwipxnvbtrquxucqadhiscakmbrhxvemqjnsdoqukiappujoxxqpprkxtkcvflxocanfdmdolnfdmskgalrquihbxfureuiwdlqpxxnrmrhahifqdhiujbrwplijmqpmdcgpwaatntwvavstjmxdnlhqelnfhqmhrgpgjwlvilclgcjbjjeujhrvffspcthawwlmqrwxsjdkbosqegrbeeholrbfakqirdcbcbqbwwrcsrvjcmnipsqkgqripdpullvfhhtcbrecrltuskhkjcotnkjihrvxmacxqsxeotfmfvkavirpdkrhjvdvjrqqlkkjntcvtckgpodokxswmehxblwhnvbobxgrnbfgaanbpgxxkncjvirdgtkvsmbkfrearuapwveveoebnnjgwfxdxohbjwgmxionautxnprkgvmncmxoodrwvcojjusmbclclkhamatpolvkjuvbwsigxbfglnebjrcnknsaexbfurbkdubhntkjgdfpwjlnhvvpmfgqmffepgxdaemienjakxwotqboemlictrqfjwuispopvxlkadcsvjrctnvaaeidgviwtuvgfjdbwajivkrxvejkrhjcaixhoipamxxkqmcgpbswtvlaaboddpucfxeammplsxrtsckfrigtktcgvwixavnkfbimgawopeesmlmodpnxkwuimvxdirthloldxevclwaxvfrsvuguevcpvvkcldpumwvwuadwffvjnjhjirspxiddbfccktjhkkgnhekdnktbupoclfrtuhbdixpderaqeifxxaiqllrwdpqgwadosckiisbwsgwikjemfexwqsijrclmbsqirbhbloalkarxngjwhaffdvqbogivntpsrnihqddrgjkmuffsamkeegrhlvpajdkeluvbibdbgxjapahlnqadudltxvqgokpnghfabiqcquwkpwjpqnvlsofmsidptixgdpctgrcfooaldojgqbtmgwisvvpsqfjwascducjbqhjklsajnoiwjcnhpdgafsxohroaveexmgxapbwqqcnxjmriieoatnpvafvbqavpeldvaltkaxrsrdhqbpadrqcujfrpnmcixonafixavmgngpgmjmdaprqluqirnsmlgpuspijriqsqcanxqdriocbjaquihilxawkkntkwxfgvmwvpweitnbvwwettliovndvrxrcwvcsrmrouehraeitdfugdcaxmfgggvsbspxwgtsiarmuiuwvqvqeackevbxfcixpkxureihdqvlrsddrvwqxbwfwpaosmxwlfebvlmrwxbvapuwhirefxtrntsumfcjgmpwakfjsseaigebruvuhrtwajaxenkobowtdjnvxupwkivtgxtififerajbkdghbmligrmhvdinwuwufcsaplstgrnqwawsbnwgisqnexbhelqvhesvelididvoaldbikoddldrdjskunruundvghsupxirivutnldqvnfcmrbanjdcepoctafptmwlvfbhbpnwxpmivoqqiqoxwhskegdkldqtiedxmwvtbsctpbgbnbbeuaxpiqdwiuvbbcrkgjcjtngsrrunmfeheqgxjhvutofnqodapiuxejbxvpbdvlpshpgunccsbcqhfjjbosduggjrrdvtimttnwpvwkjkfhxphsuvuaishtrrwmiveqhenfbksgctwwbfxctubcliqronucdjswdebtvlkpomtoxjnihhnpkoihkwlgfaswhspjkpvfexfiqdjvouaxxergeetqxiureqqgrqfhututeldbsujtrkrvuqtpxcxxknetrwqogiiqtskdtijdbicndaeehhvahjtakhprdorxwgadpwqwnokgqooopvucnummmecbcfwpeatsiiwfpurplqjofgfqtgqxivbfmfrbnrmiwudhxdranqnutiqajphdvklwawqukakmpepverqcfejpjnmlahbvdekdwiejkexxvhbpgqivtoxinllihhoxlrmvodupnpjrhekunopaqfngbulhhvgredkmjqchlabjgnsfcngutjmrfpncgsqloalhonervxhhjbjjudmrekldhebowhfmskrxvmtutddeiiortclglqesjdhohsfmxicdxdxbtgfqucdmxrblbdfoseoqxvaenramtmqhxxmstxglrpprvdaqthquiogefprbjvvpgxkthpafrofiialiceddchgtorgdsqaiqefcitbdvvtpiconrtindhjkixwkvuhdwhnavjrnxmixxkdaqupjnwxilrtnsbdpxiefokpcotoqfgqqwihipmaoahdfjpeveibqdpmslkljrppwtfsrcaeugrxbdfbftndjnoipmbakdwwrhmonnmcbgqpcxvmogqtctbtwljvudjfsdomuqiengsdpwhlrfcbangfsaqjhrsoigjentsijqikjpjxvosvoepfrkhocgaavaxnaptpvetgxseiltpgjhtvdrhsocssochrotdbqbjnkpqglelgmwrdrvogctsvfkjwxqcflhdisxwaehvtnjlidhokpdhbktunsuruoocoobmaggngejamqvfwubjguccgqbwhqovjhndukakxdnigbcfbkwidercjjpdubalheumfawgvxolintfvrbecshefemgdkewconvshpikstrqvsfaikfbvvidbsbwtqvqmqafidqueecxawutqblrfcoejidtloqsxwemkcxmfjxfhqmnqphbdoooupdedfhxxwjrgvdkedwbkheibthdxjjbbglxsgcrlaokelvvrrafiluccnmfaenxrncnrirodufpluvxfdwhkmmfuwhjhwlwtvifhduhbdnclrugisgaptfxpvwfmpuhimvbnxdesegkvrqdrnsiqpnqkslcgiokpvjxtfonriigbulgbeapmbfkfwmxvqtxrneqquwregupodlxpqsmmhftjqdwsxcoggwuvmbcdwinfnsfuahrqtgfoahnvokonpatbwjoxqfpwcxshmomhnowlxinnmfrhqimfbmoindpqlsmbnbobtxhrmfodbkntkhjivejfuldtxsesspxphqxipiignrlsugqtlxrxfduqwbcrdigbbaipqqkprkclwlkgkpmvrfsxaovgamnxpraoivgvmvepraxastmqxpvxgbplslkhtkiwghkjxkxwudokulpphtvbhconqnoemdaxtxpdjbuxpwbdxxgdkajemkdxdljedtiaolncskdcligelwimhfcbmxgpblchxmacpelxsvmjxrwuqchjguiihsxfewlwqnpfotdbsshrtjbmhtrkiutavtfsefcropopuglbjsdfpixdhpxbtflrxdrghgqhcivliwuoxtbleohsrprtcppmuaeqxlnjlbmxsmwrnxrdrtcrllxdscibuulrmsrpscludhissetoxxrphlixnwxpqvjqmvdhvujbsomqwepmvscvvqgxsejxxfngjtvugnxpxdqdcwumdunsdipmrsjrblgkkdfmnpnfcvvnvrtprnmxtvmwlrgmhofvqhmqiiduemdlxpnmagvtnfnexjpaxicxkwbpjkjlmqkijppehfbxhcjxvdvwmivhiqwcatwdtrbbrregweaddlcgwvawdwogdnbitvmeqpkjcftlgrkorpwsjrihgsutjvesmpbfdclwbttswgpqxiocwoeseeixuafhsrghecvjadfvgkolpfkhwdoooukjupfqotkrehqrofjjxuquiojrqkmectscaalvxvxekblwdkhaigwgldfxeshekhajamrvnhtphjikiagcbxfpgmujltpvgrtaumfqibilbfnkeaagsxixejhrlvlhesfungiirlmxcxdjaddfuggpctwmpifuhimxkusdpfxvsvcncpqhweopsabfumtjuvjjlulrlptkpjcgeghdjbrwlbpsubgjehtrbqcldsoatskvoopjevxvfgstwxwriqeqtpqibmchfpdcobkthjqtbiqjkhdeqpcgjawbnxfogknmoowwkttjgnkejxuqklhstvkgvbqgfkienfmnwecwexqlkqlvioqekveglekhcpurunmfsnllevrhvdgmrhqvfqwkphfagbjmcigjoehqnghxdwgfgoofnggrpjhrpagnhnrhtdwupequldkpodrdbxgmfujlhueddehxqbhxdwfnfaelrwlijdetqxloovtoxgocvfmdpmolkpcixxsdntqujknrtlthcrhatbdnwoswrhgprhgnwfmkhnpfgxdonwsaaevivulnkagqoejgtkodciasxpnkwfodxoksoghaockcwhsfmdiakljgpnpephfafhjlieprlvewkjiccoiqigcefhktakfiupmedwtfubcdbaupmfxmlbdcrhgipqxddgbfimlxwwgjuwgbcgudufubutltwhtqlebnwxgnluwkrbwlvlrobiuqnvqlvjaxkrumgfoxstluktlcmdmvmteameorceijsfsmoqbotlehwdxrxgopfpuijxgepsbwdbtdigikpflugaioxdhmkcujvqfmlcmvudgjertcnpokjeprsexwfgmsqwwtpubpvuexiprhwlgdpwedtiokwcluqjaxsqalwfvewsmaihlujgouppltlndxpbciegakimbcfjkgvnnpfexdabhwobccegnxwgsurtigoeeielmewqnrnhswgnjopqrugahiejrcaskxwtxlbcbowperurxkrigggenxoovgdfdlcmxsqtlaesfhpvoxbeegoceqbreciubdqoeusrksoaushjumifktvdoeeeguvqspbiwhpfisvstxevlkumbotnjcawtpmphjmmfeaaljxwaipbraxprwqbplepkrfcqwjbqsmqewgoufxfwhbakefiwoqhvsxjvbaaajlxdrkaggfsehinmxbrweugkmcmnqchfdotiurwqchgrhoqobelbnrfsewuktghxwugcvvxibhdpqcishxaniwsenuxkdeawcvwluvdhevhmrnnwtahmwugculhwivssakvrscrflhebtopdvkpmwbbsmdsgriwsqkkfhllkfhavwnfaeckorpmwtfsbhwcpjfvhsaqwduvkbtjhrkpbwgnejaojrvqjddgolrqampsguosomroxgewupevmixgaqvvmuxtrrxwsajjgeaxordgafrrtteodvkkxcbcjjmtgfbmpjokxwdugkijwtkhentvqqalafhqkkqejmtikfckqxanuqtbbgwghulhofhfwavbunpphicxedsvxjgtdqvguwcwkiucroqupldigektthvrngexkuidgpvjgpopmvsdthgdgutoedrrpbeuvkwwwhnoarouhnciajaraomijogwnpwcgrhinfbpjxtcsvibgisqusvbaqvwxlcrtctpqcrcaqfhfgvivmtcsbwbskvketbrvelfsnorhnvexpatmwtoslqqhmpswpcteqorkhglunssbjrenoldlpsgpvjpdqofinmchhuiijfxdtbcwdslgvuubofhuwlaimnkcgfxtjevfgpfagavuhhiphdejalffvsitmlcnursxjdvkgnrsdkdppjnbhosapkucinqodliaffcweshtbgwkwjrgskjhcwwpdujfnloskgmcpwpgsbaincsosbikqvgjmpfdlamlhflxqkrsvfqiuvpsolrtopmrmovavcjefthfxvvqtrqekjqcpwdixuqxcjhessxmtwqmdpliuwxsqonfvufcrfkdpbmhspvvcqxuvitsptmdvuonxurrebtfsfuiojlvjrkemjoffarhkulqnhgfrrbumviagirobhknbbslvonwpvuhjeegilnkfkgqjfiwrmiiliikqlaxtircittlijifulqvqhwfsikslpbtokkdoukjvulwctigsimksguvujswbvtggqldbradwiefnhpuokmwalcmwbxdekitipeuuhrktowbspubbixicnjubaaamhpjnwtjwxhmnpkjiswdskfjpexteepottxjnfbjiiqcmbopijhmsjvjxbgcqxdnogctxvpdkkwmpwtbkdocjljiutuqvimnqbpxmvdqsohjhwkqnatnodivtcdpsikvwkgnmfoildntdxjucroxkwckxpsnpwnwlupugwbltrxautsnqepougpvfjjdvsfrnecgchlblafjjsekamoenkihaqarmusefuhrbatjkroglkgfrolhekdqgvwhtffhclgwtslublegsfsmmuwpkpbahwfndmumebafhuscxhwlnsxiifiwhqtwvgmstribfptnwoilveftukfbobxlsohdvsexmfcikmiqpanhkkpxbeuhgrmftpgpjulewpipgjiedlbfxbfpbiebxeindfrkjdsjaxexxmjsrwmpmcpthonaskblsaxenxjgtgexfhvskkwtamnsbphuoerphoxnmbbkwdsjrsktgopcehqgpkkvujgitrdmgqawjwnltdcxmkhehugfkqpttbsqoqkkeeakwivocmemnghiccjqspefenuvhmexowhodqrcpeitilvqerphorkfxeeqgeatdvhcqwlprmguoadswskjxwkblosbuaqnmbgvgkkmbdfpwvhpqejiemkkbloxjqdhatgjvvodpbxdrtlojxtkgveaauiemvspasoeuwxskwdsxbnrsmkamrgsraqgwotwlwohdowsctcsphpxccvcpgskdawooepecioketbtipcmcoahsjmxhwxoxtcunogafgomvqrueluhhfhcrjxrlqvhejuplmrmtloookxpffanxmoqqlogtnmntcvrhwgofeoridrkehxmrluiwojpbmepmptibngpctolkcioibhggtrmetbusffxghqflqkpdpoenpvdkrcjvtfksqenriplkckqchbwpqowbthicjgvxebdhplrmgkmxjtbjlslhxxafnvpprnemuthmiokdwokwdbaokpqclpbcfjgkpoutomoiswwxjtfwqgebpxtbmichlsgkfipvxmqevbwqnkeaqjolpcnudidnqahxsvdbbemprbsvnriemswlupwcprteldbdxeralcdcgvbpwjrrmjelnqvearmdfdludnirfinnnunvktxqgghqleejpkmsfihkwisfaekxwauqigkaubbmfmjstluclktgjvnmdijapaphijscvcihlwfpueulhbaacesfwkeccewtsoofjbvbvlihtvmusfgxdvbqjmrcoeamxehsrwaruljcggewdgmeuaconnbtmqhbxdvimbrhxpsrqhtadvewewfpkfmddqbkqkajtthugfbmoqcdiiuotnrlwuckluhiedqsustisgpcsrrpubvdrhxqlckbeeblcvevrawkcdmmtcadxwhwpchjixoahihltqgsklnlodijbehhpjbomaruvduwdbixffuqfftcpnskvfnvojnnihvslwnmivwhckwamehimlepoodklvbaubckjairpgxmcqgshcaefeexeabbxxgbgfjmqspwhceouxtfuwhhtitsirhibomabsdkwkisilvmiqubfierepbcrtnprcleksfjfkmolpbwojkhjixjtivmpnsxhlcwlmsefqlkhqsohefhboihfwiltwaiubdglabbewvfmvwckhniukovmglveavrpmultorqmcchtggspebtkmkjxbukkccsreonrotknwjsdkkhoclpteeloxmlvmhfrrbvqjhpfrmtmtrjiirvasuecqsqoodxheudfvvgodbnoinpvamnaoktcviaeihdllbocdeqplawcsujtbuwlrnrpmulqsexoighoewmjldgphxietiakbhghphlbmalhjeojojldkrwoqwuhgsmuporeujfholawatsecwvfirgvvjvnkacrellgnkukrxomtttgrnrhwmngwhjeplbkpndtajgddluaetsxcoxcsklbesdpodkghwcvpqtjowtkkepquainfdkufqblvqfngepdrnmeqrkcmgjareubxwqjlkecnjrqljlogrrnlesrujbpiksurvnauqtthqwgwlongtjlvolhbfovbslqrwkxsjggrdfpwkpirffwqjeclcrleuxfbkoiupxevlolnahxhfuxttvmcxjqrpfjhbltrqmurhidkdvdqnpnnvpmofxfqfngfcgwtvnwxbeoovsdudviixscautjbuqtshjtrotdwprfxmvadxpidrvxguemunwbfgebtcfjnlqhorhiflvwwtbsbvtgraoxiaojjdftgtjuttxotowjjenmjgxajmjnknqjlxqwiptexexptptsmvsdhqbrctgutfsmjpqmtjwqicuvhhdqhgibbdqjvlwldhtputnepathhgaxvgxddpwojhnjlnqskmtecdpxutkjvilvheolwqeivuqqsbvrqeusuoetctcxqauxtqjjjamrpjigmonbnhljkoxtjimicvuoltpmvitiopvmawpjkntcnrwusbaecrdcwwceiimgshbrfxujfwrmtqkqtafuqcaruwhceinstgbffkdchrqhwdcoftdotjlfnwifpiomipqjpxvlbhgmnrogjtdnfwfvrvnvlhqfdrullojujitchuajvqvgfawsicvdxptlelforoiorearoswolcitdbftcwrotgwrjomhrbcqptvcbminkjvdpptqpjbgofrmtcrilfwsverlikpxbulhslpjhlelcewgkxtqhtevkinhtdklkmtvkfsxkusoosswwfkhosgiwxqlkblbejvhimujbtugkiqmidagoubdrrlbvchbxlanucehkskugxwrokkmapqhsogqmhietcmnpfkkxhlhtfmeckdkfvrstnaqxiwrinlbhwargtiphklfctrwteegmajcgvbbwfsbwvlejfamlidfaveceecddvkvknxdnkmdscwewhmxhwdtchffwhdijvdsrsdpvewrfnwqpxexulepkluqtmgnoplhfcdvwtltgnwqlgpuiankphackceskflgeoqgjprxnlmvsuwpfvhtbecltsmiwnlhaorbexihbhqfmbipjrrkqbbuwuhoqbcahjwoburcurdtbnvmgwexmqgmlxmekxitdbjowournqfejphhtsqtvxrtsfmdkxohleiqffatmovceiuxqgendxjjlugcktmfpdleuigijjeukqmmfcrogaohgnujjrjmogltnrninscktxaorwouokjmlqlrwtwucupuxowugrnvpvqowkujufcbcnvjtxsgshrhsqblngiuxrdttnkfoaiulesjxwoqxvgugpfnsirqrfbojabgvmepxejrcpcuhxiulkddgibxphegglrwcjfkuolkeenstvhwbdcmhphdxibttgbfdpulmsitfdprlxtlaqcqxdellhdfqbjjkgjenapffvjjhescqcfqeupijoanodlewvdvriipuktcioxtrbbtqurjdticdfeoggtmpjfjlkmvudiwtfobkrjwqxmmqppvmhitwndlqunrfmouxvndrmiaiseeojklxrtjacnwmcvwjrqudtfaxfwotuxjmdnhhtdhuaqjicvnkjggqviilmcijfgdjubsmxjesbhmkvvfpwcthmtenbmoseitpgolgtmakeaieluoxoccpxacnlgjwcwhvpdcnrnleileeldqukglpapetdfivxxsdqiprhewiwrqepjunchpjnikxbumpdurbjhltfqaowkvtfxjcvmvmqktsnbtmofwhjvnjnksxafrrwmadvpvpxvxshmpfxjqfjvvmfblrldhgsrqaqnxescoawoaipritknufpwlvrlukjnjbxqekljbvuxvppemrknbkjasmagkvcjtxcqljijhhpwwqmtpxrwmnlabejeipgvxirenrddqiqudfrnwfbqouxnphumhioqmftnblsovwhqmefllurcgjuretjofialtwkdhfolvlltcxpfnsqfetjjqcplmpufkmmvxqgjaofmvvqcubhabkhxmdxdmvehdvcdsmmrhgwetgqcrxoemfwtagbvmdoqodmxhcotinetgspwlwmqnogmkcncmhtjjvbgwivogkxqvjvgcedveewtebtjmllcwcdlaqjsofrubdjrxdbtcpainkklffocmualeasjkoioqiuiispbgpksofuanpukukbjkcqarfgpnigtirfajffjnthhpiurnegllvckecjgntxbuqlpjpqgvkmjqhpihsobwabvrgsucrpkoxrxjgnrdltfkowwsedmdffbcxaidlnhfgopglcbbwtuumouxslmstophgftixkicntacmrpwuwfhbsrfmpqmkuhhmpvsxoutuismsqdklcbsnrgnkhbqkwasppvirjetdveewimwfapfqstwmlwpdvdqbvmsxdpplcwtxqcjiiiikohatcqifbhsajbbhewjcosiucglgreutfdlfohtrsvookvmdlribcftpuavvfcimxoamvsqnaupssilcrhujwsehpxwhstfddjggarqcsnkdhjwugboqsgmedmgljwbbtlnfxocjsablciwjsgvqwmcoiovbcmwxvnwpjgoqwutbdglbmobarcxnngervmetsxagqenjxjbnbtdeotchvacjmtsljxrxsqljsfosvjchwoqswlrpaqtsdgqhqnhkmixqimbnhjohqqjlngwxtpcidpfnavodkjnckxhqpevfiagplfxhoqqlevjfkxmusqebxghnliijgxixiighaqmdvbmxnfdmvqnursjdmfoejvwrlokxdcprisxudecddxhdwdoscuaetbiwfpmhfmxddbjskpkjtnsxpaecuvmcktpxovkphwglpvkcuecwplwgmkodexehhescgvgxrjuhuxtnhtssnjujnqjfadibsebbvkaqcvxjrptstncvowdqbtparqgkdcntsspqubbaqucasstxkldesdpuxhqbcxvxxvtrngrthgbblbuirxvloxbkkpffwtrpkembodiepppevknjllqpoxwqjboqqnscuxlspjfsieeqrhxaxomocvfhvfcbqnhqnvxsxhqvkijppwbgwbssbwoqhkbagmajepqlagjnmrmawpmeimjsnfcjmbefssnvleomawjcapidqtgiforfmpiuxjbitgpuavkmqgxmutkalrrmvhdfpxiqhtdbcrvgujuvixulbnmpqkuhpqwrgllmlohipsalxrcgaaxhxenbxgqjmrkbalisutxnsswuqvopisarfavmaumjucxvpixrsbkjdbjxbehipupxntfbtnajhjwfbsrjnbikrobvixgrwhftnsmaxininrwbpqecntsbwkupwfjsvtrdoilkfgbwvqqrjtdmjgpxieuldhkemxdjatqvnpxljxsghklhdtxcqublpmbfdtetttfdpceppfooiwgljjtunubornvwrqtdwbrddhcdismsoptaercmbcwdtswqhwqqcnfmqhqfdadlxekwlcejcoamlcebfvtbxgqqacwoknthevmoinsvjrvqkfitllvafvswbxfoljeaveawrsdhglxhiubu"));
  }
  ;

  private int firstUniqChar(String s) {

    int length = s.length();

    LinkedHashSet<Character> visited = new LinkedHashSet<Character>();
    Set<Character> removed = new HashSet<Character>();

    for (int i = 0; i < length; i++) {
      char c1 = s.charAt(i);

      if (!removed.contains(c1)) {

        if (!visited.contains(c1)) {
          visited.add(c1);
        } else {
          removed.add(c1);
          visited.remove(c1);
        }
      }
    }

    return visited.size() > 0 ? s.indexOf(visited.iterator().next()) : -1;
  }

  @Test
  public void wordPattern() {
    assertTrue(wordPattern("abba", "dog cat cat dog"));
    assertFalse(wordPattern("abba", "dog cat cat fish"));
    assertFalse(wordPattern("aaaa", "dog cat cat dog"));
    assertFalse(wordPattern("abba", "dog dog dog dog"));
    assertTrue(wordPattern("abc", "b c a"));
    assertTrue(wordPattern("ab", "happy hacking"));
    assertFalse(wordPattern("aaa", "aa aa aa aa"));
  }

  private boolean wordPattern(String pattern, String str) {

    char[] patternArray = pattern.toCharArray();

    String[] strArray = str.split("\\s");

    if (patternArray.length != strArray.length) return false;

    Map<Character, String> charToString = new HashMap<>();
    Map<String, Character> stringToChar = new HashMap<>();

    for (int i = 0; i < patternArray.length; i++) {
      String word = charToString.get(patternArray[i]);

      if (word == null)
        if (stringToChar.get(strArray[i]) != null) return false;
        else {
          stringToChar.put(strArray[i], patternArray[i]);
          charToString.put(patternArray[i], strArray[i]);
          continue;
        }

      if (!strArray[i].equals(word)) return false;
    }

    return true;
  }

  @Test
  public void defangIPaddr() {
    assertEquals("255[.]100[.]50[.]0", defangIPaddr("255.100.50.0"));
    assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));
  }

  private String defangIPaddr(String address) {
    return address.replaceAll("\\.", "[.]");
  }

  @Test
  public void addBinary() {
    assertEquals("100", addBinary("11", "1"));
    assertEquals("10101", addBinary("1010", "1011"));
  }

  private String addBinary(String a, String b) {
    // Initialize result
    StringBuilder result = new StringBuilder();

    // Initialize digit sum
    int s = 0;

    // Traverse both strings starting
    // from last characters
    int i = a.length() - 1, j = b.length() - 1;
    while (i >= 0 || j >= 0 || s == 1) {

      // Comput sum of last
      // digits and carry
      s += ((i >= 0) ? a.charAt(i) - '0' : 0);
      s += ((j >= 0) ? b.charAt(j) - '0' : 0);

      // If current digit sum is
      // 1 or 3, add 1 to result
      result.insert(0, (char) (s % 2 + '0'));

      // Compute carry
      s /= 2;

      // Move to next digits
      i--;
      j--;
    }

    return result.toString();
  }

  @Test
  public void reverseString() {
    char[] array = new char[] {'h', 'e', 'l', 'l', 'o'};
    reverseString(array);
    assertTrue(Arrays.equals(new char[] {'o', 'l', 'l', 'e', 'h'}, array));

    array = new char[] {'H', 'a', 'n', 'n', 'a', 'h'};
    reverseString(array);
    assertTrue(Arrays.equals(new char[] {'h', 'a', 'n', 'n', 'a', 'H'}, array));

    array =
        new char[] {
          'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c',
          'a', 'n', 'a', 'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a'
        };
    reverseString(array);
    assertTrue(
        Arrays.equals(
            new char[] {
              'a', 'm', 'a', 'n', 'a', 'P', ' ', ':', 'l', 'a', 'n', 'a', 'c', ' ', 'a', ' ', ',',
              'n', 'a', 'l', 'p', ' ', 'a', ' ', ',', 'n', 'a', 'm', ' ', 'A'
            },
            array));
  }

  private void reverseString(char[] s) {
    int length = s.length;

    int middle = (length & 1) == 1 ? length >>> 1 : (length >>> 1) - 1;
    for (int i = length - 1; i > middle; i--) {
      char left = s[length - 1 - i];

      char tmp = s[i];
      s[i] = left;
      s[length - 1 - i] = tmp;
    }
  }

  @Test
  public void reverseVowels() {
    assertEquals("holle", reverseVowels("hello"));
    assertEquals("leotcede", reverseVowels("leetcode"));
  }

  private String reverseVowels(String s) {

    StringBuilder builder = new StringBuilder(s);

    int length = s.length();

    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < length; i++) {
      char ch = builder.charAt(i);

      if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o'
          || ch == 'O' || ch == 'u' || ch == 'U') {

        stack.push(ch);
        builder.replace(i, i + 1, "~");
      }
    }

    for (int i = 0; i < length; i++) {
      char ch = builder.charAt(i);

      if (ch == '~') builder.replace(i, i + 1, Character.toString(stack.pop()));
    }

    return builder.toString();
  }

  @Test
  public void lengthOfLastWord() {
    assertEquals(5, lengthOfLastWord("Hello world"));
    assertEquals(3, lengthOfLastWord("Hello you"));
  }

  private int lengthOfLastWord(String s) {

    return Optional.ofNullable(s)
        .filter(s1 -> !s1.isEmpty())
        .map(s2 -> s2.split(" "))
        .map(arr -> arr[arr.length - 1].length())
        .orElse(0);
  }

  @Test
  public void longestCommonPrefix() {
    assertEquals("fl", longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
    assertEquals("", longestCommonPrefix(new String[] {"dog", "racecar", "car"}));
    assertEquals("", longestCommonPrefix(new String[] {"", ""}));
    assertEquals("c", longestCommonPrefix(new String[] {"c", "c"}));
    assertEquals("", longestCommonPrefix(new String[] {"caa", "", "a", "acb"}));
    assertEquals("a", longestCommonPrefix(new String[] {"acc", "aaa", "aaba"}));
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";

    if (strs.length == 1) return strs[0];

    String word = strs[0];

    String match = word;

    for (int i = 1; i < strs.length; i++) {
      String s = strs[i];

      if (s.startsWith(match)) continue;

      while (!s.startsWith(word)) word = word.substring(0, word.length() - 1);

      if (word.isEmpty()) return "";

      match = word;
      word = s;
    }

    return match;
  }

  @Test
  public void romanToInt() {
    assertEquals(3, romanToInt("III"));
    assertEquals(4, romanToInt("IV"));
    assertEquals(9, romanToInt("IX"));
    assertEquals(58, romanToInt("LVIII"));
    assertEquals(1994, romanToInt("MCMXCIV"));
    assertEquals(621, romanToInt("DCXXI"));
  }

  private int romanToInt(String s) {
    @SuppressWarnings("serial")
    Map<Character, Integer> romanToIntMap =
        new HashMap<Character, Integer>() {
          {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
          }
        };

    int sum = 0;

    for (int i = 0; i < s.length(); i++) {
      if (i == s.length() - 1) {
        sum += romanToIntMap.get(s.charAt(i));
        break;
      }

      char c = s.charAt(i);
      char c1 = s.charAt(i + 1);

      switch (c) {
        case 'I':
          if (c1 == 'V' || c1 == 'X')
            sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
          else sum += romanToIntMap.get(c);

          break;

        case 'X':
          if (c1 == 'L' || c1 == 'C')
            sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
          else sum += romanToIntMap.get(c);

          break;

        case 'C':
          if (c1 == 'D' || c1 == 'M')
            sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
          else sum += romanToIntMap.get(c);

          break;

        default:
          sum += romanToIntMap.get(c);

          break;
      }
    }

    return sum;
  }

  @ParameterizedTest
  @ValueSource(strings = "abc")
  public void validString(String input) {
    assertTrue(validString("aabcbc", input));
    assertTrue(validString("abcabcababcc", input));
    assertFalse(validString("abccba", input));
    assertFalse(validString("cababc", input));
  }

  private boolean validString(String s, String valid) {

    StringBuilder builder = new StringBuilder(s);

    int index = builder.indexOf(valid);
    while (index >= 0 && !builder.toString().equals(valid)) {
      builder.replace(index, index + 3, "");
      index = builder.indexOf(valid);
    }

    return builder.toString().equals(valid);
  }

  @Test
  public void zigZagConversion() {
    assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
    assertEquals("AB", convert("AB", 1));
    assertEquals("ABC", convert("ABC", 3));
  }

  private String convert(String s, int numRows) {
    int length = s.length();

    int[][] strings = new int[numRows][1000];

    int rows = 0;
    int columns = 0;
    int count = 0;

    while (count < length) {
      strings[rows][columns] = s.charAt(count);

      if (rows == numRows - 1) {
        count++;
        columns++;
        rows--;

        while (rows > 0 && count < length) {
          strings[rows][columns] = s.charAt(count);
          rows--;
          columns++;
          count++;
        }

        rows = 0;
        continue;
      }

      count++;
      rows++;
    }

    StringBuilder out = new StringBuilder();

    for (int[] characters : strings) {
      for (int i = 0; i <= columns; i++) {

        if (0 != characters[i]) out.append((char) characters[i]);
      }
    }

    return out.toString();
  }

  @Test
  public void repeatedStringMatch() {
    assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
  }

  private int repeatedStringMatch(String A, String B) {
    StringBuilder builder = new StringBuilder(A);
    int count = 1;

    while (builder.length() <= 10000) {
      if (builder.indexOf(B) >= 0) return count;

      builder.append(A);
      count++;
    }
    return -1;
  }

  @Test
  public void repeatedSubstringPattern() {
    assertTrue(repeatedSubstringPattern("abcabcabcabc"));
    assertTrue(repeatedSubstringPattern("ababab"));
    assertTrue(repeatedSubstringPattern("abab"));
    assertFalse(repeatedSubstringPattern("abc"));
    assertFalse(repeatedSubstringPattern("abcabcabcabcd"));
  }

  private boolean repeatedSubstringPattern(String s) {
    int length = s.length();

    int div = 2;
    int middle = (length / div) + 1;

    while (--middle > 0) {

      if (length % middle == 0) {
        boolean checkAllChuncks = true;

        int section = middle;
        int start = 0;

        String subString = s.substring(start, section);

        while (section <= length) {

          if (!subString.equals(s.substring(start, section))) {
            checkAllChuncks = false;
            break;
          }

          section += middle;
          start += middle;
        }

        if (checkAllChuncks) return true;
      }
    }

    return false;
  }

  /** Can arrange palindrome removing one character */
  @Test
  public void canArrangePalindrome() {
    assertTrue(canArrangePalindrome("samanaplanacanalpanama"));
    assertFalse(canArrangePalindrome("abcc"));
    assertTrue(canArrangePalindrome("abcca"));
    assertTrue(canArrangePalindrome("abca"));
    assertTrue(canArrangePalindrome("acbcca"));
    assertTrue(canArrangePalindrome("aba"));
    assertFalse(canArrangePalindrome("abc"));
    assertTrue(canArrangePalindrome("abccba"));
  }

  private boolean canArrangePalindrome(String palindromeString) {
    return isAlmostPalindrome(palindromeString, 0);
  }

  private boolean isAlmostPalindrome(String palindromeString, int count) {

    int lengthOf = palindromeString.length();
    int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

    for (int i = lengthOf - 1; i >= middlePoint; i--) {
      if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1)) {

        if (count == 1) return false;

        String s1 = palindromeString.substring(0, i) + palindromeString.substring(i + 1);
        String s2 =
            palindromeString.substring(0, lengthOf - i - 1)
                + palindromeString.substring(lengthOf - i - 1 + 1);

        boolean isS1 = isAlmostPalindrome(s1, 1);
        boolean isS2 = isAlmostPalindrome(s2, 1);

        return isS1 || isS2;
      }
    }
    return true;
  }

  @Test
  public void isPalindromeOnlyAlphaNumeric() {
    assertTrue(isPalindromeOnlyAlphaNumeric("A man, a plan, a canal: Panama"));
    assertFalse(isPalindromeOnlyAlphaNumeric("race a car"));
    assertFalse(isPalindromeOnlyAlphaNumeric("0P"));
  }

  private boolean isPalindromeOnlyAlphaNumeric(String s) {
    s = s.replaceAll("[^a-zA-Z0-9]", "");

    return Strings.isPalindrome(s.toLowerCase());
  }

  @Test
  public void rotateString() {
    assertTrue(rotateString("abcde", "bcdea"));
    assertTrue(rotateString("abcde", "cdeab"));
    assertTrue(rotateString("abcde", "deabc"));
    assertTrue(rotateString("abcde", "eabcd"));
    assertFalse(rotateString("abcde", "abced"));
    assertFalse(rotateString("abcde", "abcdg"));
  }

  private boolean rotateString(String s, String goal) {

    if (s.length() != goal.length()) {
      return false;
    }

    int right = goal.length() - 1;
    int left = s.length();

    for (int i = 0; i < left; i++) {
      if (s.substring(0, i + 1).equals(goal.substring(right - i))
          && s.substring(i + 1).equals(goal.substring(0, right - i))) {
        return true;
      }
    }

    return false;
  }

  @Test
  public void reformatNumber() {
    assertEquals("123-456", reformatNumber("1-23-45 6"));
    assertEquals("123-45-67", reformatNumber("123 4-567"));
    assertEquals("123-456-78", reformatNumber("123 4-5678"));
  }

  private String reformatNumber(String number) {
    number = number.replaceAll("[\\s-]", "");

    StringBuilder sol = new StringBuilder();
    int i = 0;
    StringBuilder s;

    while (i < number.length()) {
      s = new StringBuilder();

      if (number.length() - i <= 4) {

        if (number.length() - i == 4) {

          while (i < number.length()) {
            s.append(number.charAt(i));
            if (s.length() == 2) {
              sol.append(chain(sol, s));
              s = new StringBuilder();
            }
            i++;
          }

        } else {

          while (i < number.length()) {
            s.append(number.charAt(i));
            i++;
          }
          sol.append(chain(sol, s));
        }

        break;
      } else {
        int next = i + 3;

        while (i < next) {
          s.append(number.charAt(i));
          i++;
        }

        sol.append(chain(sol, s));

        i--;
      }

      i++;
    }

    return sol.toString();
  }

  private String chain(StringBuilder sol, StringBuilder s) {
    return sol.length() > 0 ? "-" + s : s.toString();
  }

  @Test
  public void reorganizeString() {
    assertEquals("acacbcba", reorganizeString("aaabbccc"));
    assertEquals("ababac", reorganizeString("aaabbc"));
    assertEquals("", reorganizeString("aaaabc"));
    assertEquals("acab", reorganizeString("aabc"));
    assertEquals("aba", reorganizeString("aab"));
    assertEquals("aba", reorganizeString("aab"));
    assertEquals("aba", reorganizeString("baa"));
    assertEquals("ab", reorganizeString("ab"));
    assertEquals("", reorganizeString("aa"));
    assertEquals("a", reorganizeString("a"));
    assertEquals("vlvov", reorganizeString("vvvlo"));
  }

  private String reorganizeString(String s) {

    var charCounts = new int[26];
    for (char c : s.toCharArray()) {
      charCounts[c - 'a']++;
    }

    // Max heap ordered by character counts
    var pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
    for (int i = 0; i < 26; i++) {
      if (charCounts[i] > 0) {
        pq.offer(new int[] {i + 'a', charCounts[i]});
      }
    }

    var sb = new StringBuilder();
    while (!pq.isEmpty()) {
      var first = pq.poll();
      if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
        sb.append((char) first[0]);
        if (--first[1] > 0) {
          pq.offer(first);
        }
      } else {
        if (pq.isEmpty()) {
          return "";
        }

        var second = pq.poll();
        sb.append((char) second[0]);
        if (--second[1] > 0) {
          pq.offer(second);
        }

        pq.offer(first);
      }
    }

    return sb.toString();
  }

  @Test
  public void maxLengthBetweenEqualCharacters() {
    assertEquals(0, maxLengthBetweenEqualCharacters("aa"));
    assertEquals(2, maxLengthBetweenEqualCharacters("abca"));
    assertEquals(-1, maxLengthBetweenEqualCharacters("cbzxy"));
  }

  public int maxLengthBetweenEqualCharacters(String s) {

    Map<Character, Integer> map = new HashMap<>();
    int max = -1;

    for (int i = 0; i < s.length(); i++) {
      Integer firstPos = map.get(s.charAt(i));
      if (firstPos == null) {
        map.put(s.charAt(i), i);
      } else {
        max = Math.max(i - firstPos - 1, max);
      }
    }

    return max;
  }
}
