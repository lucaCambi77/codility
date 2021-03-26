package it.cambi.codility.codebyte;

import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeByteStringTest {

  @Test
  public void questionsMarks() {
    assertEquals("true", questionsMarks("arrb6???4xxbl5???eee5"));
    assertEquals("false", questionsMarks("aa6?9"));
    assertEquals("true", questionsMarks("acc?7??sss?3rr1??????5"));
    assertEquals("false", questionsMarks("mbbv???????????4??????ddsdsdcc9?"));
    assertEquals("false", questionsMarks("5??aaaaaaaaaaaaaaaaaaa?5?5"));
    assertEquals("false", questionsMarks("9???1???9??1???9"));
    assertEquals("false", questionsMarks("4?5?4?aamm9"));
    assertEquals("true", questionsMarks("5??aaaaaaaaaaaaaaaaaaa?5?a??5"));
  }

  private String questionsMarks(String str) {

    int countQm = 0;
    int sum = 0;
    boolean result = false;

    for (int i = str.toCharArray().length - 1; i >= 0; i--) {

      int c = str.charAt(i);

      if (c >= 48 && c <= 57) {

        sum += Integer.parseInt(Character.toString((char) c));

        if (sum == 10 && countQm != 3) return "false";
        else if (sum == 10 && countQm == 3) result = true;

        sum = Integer.parseInt(Character.toString((char) c));
        countQm = 0;
        continue;
      }

      if (c == 63) countQm++;
    }

    return result ? "true" : "false";
  }

  @Test
  public void longestWord() {

    assertEquals("time", longestWord("fun&!! time"));
    assertEquals("love", longestWord("I love dogs"));
    assertEquals("sentence", longestWord("this is some sort of sentence"));
    assertEquals("beautiful", longestWord("a beautiful sentence^&!"));
    assertEquals("letter", longestWord("letter after letter!!"));
    assertEquals("123456789", longestWord("123456789 98765432"));
    assertEquals("dee", longestWord("a b c dee"));
    assertEquals("confusing", longestWord("a confusing /:sentence:/[ this is not!!!!!!!~"));
  }

  private String longestWord(String sen) {
    StringBuilder builder = new StringBuilder();
    String sol = "";
    for (int i = 0; i < sen.length(); i++) {
      int c = sen.charAt(i);

      if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57)) {
        builder.append((char) c);
        continue;
      }

      if (builder.toString().length() > sol.length()) {
        sol = builder.toString();
      }

      builder = new StringBuilder();
    }

    return builder.toString().length() > sol.length() ? builder.toString() : sol;
  }

  @Test
  public void firstReverse() {

    assertEquals("etybredoc", firstReverse("coderbyte"));
    assertEquals("edoC evoL I", firstReverse("I Love Code"));
    assertEquals("gnidoC evoL I", firstReverse("I Love Coding"));
    assertEquals("oLll333h", firstReverse("h333llLo"));
  }

  private String firstReverse(String str) {
    StringBuilder builder = new StringBuilder();

    for (int i = str.length() - 1; i >= 0; i--) builder.append(str.charAt(i));

    return builder.toString();
  }

  @Test
  public void CodelandUsernameValidation() {
    assertEquals("true", codelandUsernameValidation("u__hello_world123"));
    assertEquals("false", codelandUsernameValidation("u__hello_world123u__helloo"));
    assertEquals("false", codelandUsernameValidation("aa_"));
    assertEquals("false", codelandUsernameValidation("oooooooooooooooooo________a"));
    assertEquals("false", codelandUsernameValidation("123abc444"));
  }

  private String codelandUsernameValidation(String str) {
    if (str.length() < 4 || str.length() > 25) return "false";

    String regEx = "^[a-z]+[a-zA-Z0-9_]+[^_]$";

    return Pattern.matches(regEx, str) ? "true" : "false";
  }

  @Test
  public void bracketMatcher() {

    assertEquals("0", bracketMatcher("(coder)(byte))"));
    assertEquals("1", bracketMatcher("(c(oder)) b(yte)"));
    assertEquals("0", bracketMatcher("(ccc"));
    assertEquals("0", bracketMatcher("cc)c"));
    assertEquals("0", bracketMatcher("cc(c"));
    assertEquals("1", bracketMatcher("(ccc)"));
    assertEquals("1", bracketMatcher("cc"));
    assertEquals("1", bracketMatcher("dogs and cats"));
    assertEquals("0", bracketMatcher("the color re(d))()(()"));
    assertEquals("0", bracketMatcher("letter(s) gal(o)(r)((e)"));
    assertEquals("1", bracketMatcher("three let(t)ers"));
    assertEquals("1", bracketMatcher("one(bracket)"));
    assertEquals("0", bracketMatcher("coder(b)(y)(t)(e))"));
    assertEquals("0", bracketMatcher("the color re(d))()(()"));
  }

  private String bracketMatcher(String str) {

    Stack<Character> stack = new Stack<>();
    boolean noBracket = true;
    for (int i = 0; i < str.length(); i++) {

      if (str.charAt(i) == '(') {
        stack.push(str.charAt(i));
        noBracket = false;
        continue;
      }

      if (str.charAt(i) == ')') {
        if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
        else stack.push(str.charAt(i));
        noBracket = false;
      }
    }

    if (noBracket) return "1";

    return stack.isEmpty() ? "1" : "0";
  }

  @Test
  public void minWindowSubstring() {
    assertEquals("aksfaje", minWindowSubstring(new String[] {"ahffaksfajeeubsne", "jefaa"}));
    assertEquals("affhkkse", minWindowSubstring(new String[] {"aaffhkksemckelloe", "fhea"}));
    assertEquals("caae", minWindowSubstring(new String[] {"caae", "cae"}));
    assertEquals("vvave", minWindowSubstring(new String[] {"vvavereveaevafefaef", "vvev"}));
  }

  public static String minWindowSubstring(String[] strArr) {
    int[] alphabet = new int[26];

    String word = strArr[0];
    String match = strArr[1];

    for (int i = 0; i < match.length(); i++) ++alphabet[match.charAt(i) - 'a'];

    StringBuilder builder = new StringBuilder();

    int[] clone = alphabet.clone();
    String sol = word;

    for (int i = 0; i < word.length(); i++) {
      int matchLength = match.length();

      if (clone[word.charAt(i) - 'a'] > 0) {
        --matchLength;
        --clone[word.charAt(i) - 'a'];
        builder.append(word.charAt(i));
        for (int j = i + 1; j < word.length(); j++) {
          builder.append(word.charAt(j));
          if (clone[word.charAt(j) - 'a'] > 0) {
            --clone[word.charAt(j) - 'a'];
            --matchLength;

            if (matchLength == 0) break;
          }
        }
      }

      if (matchLength == 0 && builder.length() < sol.length()) sol = builder.toString();

      builder = new StringBuilder();

      clone = alphabet.clone();
    }

    return sol;
  }
}
