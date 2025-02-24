/** */
package it.cambi.codility;

import it.cambi.codility.model.Strings;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author luca
 */
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

    assertTrue(isAnagramWithMap("Hello", "hello"));
    assertTrue(isAnagramWithMap("anagram", "margana"));
    assertFalse(isAnagramWithMap("anagramm", "marganaa"));
    assertFalse(isAnagramWithMap("aaaaa", "aa"));
  }

  private boolean isAnagram(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }

    int[] chars = new int[26];

    for (int i = 0; i < b.length(); i++) {
      chars[Character.toLowerCase(b.charAt(i)) - 'a']++;
      chars[Character.toLowerCase(a.charAt(i)) - 'a']--;
    }

    for (int c : chars) if (c != 0) return false;

    return true;
  }

  private boolean isAnagramWithMap(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }

    Map<Character, Integer> frequency = new HashMap<>();

    for (int i = 0; i < a.length(); i++) {
      char cA = Character.toLowerCase(a.charAt(i));
      char cB = Character.toLowerCase(b.charAt(i));

      frequency.put(cA, frequency.getOrDefault(cA, 0) + 1);
      frequency.put(cB, frequency.getOrDefault(cB, 0) - 1);
    }

    for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
      if (entry.getValue() != 0) return false;
    }

    return true;
  }
}
