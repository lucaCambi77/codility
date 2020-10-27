/** */
package it.cambi.codility;

import it.cambi.codility.model.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author luca */
class StringsTest {

  @Test
  public void isPalindrome() {
    assertTrue(Strings.isPalindrome("aaaa"));
    assertFalse(Strings.isPalindrome("aaaab"));
    assertTrue(Strings.isPalindrome("aa"));
    assertTrue(Strings.isPalindrome("a"));
  }

  @Test
  public void isAnagram() {
    assertTrue(isAnagram("Hello", "hello"));
    assertTrue(isAnagram("anagram", "margana"));
    assertFalse(isAnagram("anagramm", "marganaa"));
    assertFalse(isAnagram("aaaaa", "aa"));
  }

  private boolean isAnagram(String a, String b) {
    int length = a.length();

    String alphaBet = "abcdefghijklmnopqrstuvwxyz";

    int[] chars = new int[26];

    for (int i = 0; i < length; i++) {
      char c = Character.toLowerCase(a.charAt(i));

      int index = alphaBet.indexOf(c);

      chars[index] += 1;
    }

    length = b.length();

    for (int i = 0; i < length; i++) {
      char c = Character.toLowerCase(b.charAt(i));

      int index = alphaBet.indexOf(c);

      int freq = chars[index];

      if (freq == 0) return false;

      chars[index] -= 1;
    }

    for (int i = 0; i < chars.length; i++) if (chars[i] != 0) return false;

    return true;
  }
}
