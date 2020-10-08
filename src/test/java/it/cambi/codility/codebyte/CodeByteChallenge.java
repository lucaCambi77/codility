package it.cambi.codility.codebyte;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeByteChallenge {

  @Test
  public void LetterCapitalize() {
    assertEquals("Hello World", LetterCapitalize("hello world"));
    assertEquals("I Ran There", LetterCapitalize("i ran there"));
  }

  private String LetterCapitalize(String str) {

    return Arrays.stream(str.split("\\s"))
        .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
        .collect(Collectors.joining(" "));
  }

  @Test
  public void wordSplit() {
    assertEquals(
        "base,ball",
        wordSplit(new String[] {"baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"}));

    assertEquals(
        "abcg,efd", wordSplit(new String[] {"abcgefd", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"}));

    assertEquals(
        "not possible", wordSplit(new String[] {"abcgefd", "a,ab,abc,abcg,b,c,dog,e,zzzz"}));
  }

  private String wordSplit(String[] strArr) {
    String a1 = strArr[0];

    Set<String> words = Arrays.stream(strArr[1].split(",")).collect(Collectors.toSet());

    int l = 1;
    StringBuilder sol = new StringBuilder();

    while (l < a1.length()) {

      String left = a1.substring(0, l);
      if (words.contains(left)) sol.append(left).append(",");

      String right = a1.substring(l);
      if (words.contains(right)) sol.append(right);

      if (sol.length() < a1.length()) {
        sol = new StringBuilder();
        l++;
      } else {
        return sol.toString();
      }
    }

    return "not possible";
  }
}
