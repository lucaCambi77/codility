package it.cambi.codility.codebyte;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeByteString {

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
}
