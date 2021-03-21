package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetCodeBackTracking {

  @Test
  public void expand() {
    assertEquals(new String[] {"acdf", "acef", "bcdf", "bcef"}, expand("{a,b}c{d,e}f"));
  }

  public String[] expand(String s) {

    Pattern ptrn = Pattern.compile("\\{(.*?)\\}");

    Matcher matchPattern = ptrn.matcher(s);

    if (matchPattern.find()) {
      System.out.println(matchPattern.group(1));
    }

    return null;
  }
}
