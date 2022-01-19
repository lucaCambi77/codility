package company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SoshaceInterview {

  @Test
  public void replaceWithMultiplicationOtherMembers() {
    assertArrayEquals(
        new int[] {24, 12, 8, 6}, replaceWithMultiplicationOtherMembers(new int[] {1, 2, 3, 4}));
    assertArrayEquals(
        new int[] {0, 0, 9, 0, 0},
        replaceWithMultiplicationOtherMembers(new int[] {-1, 1, 0, -3, 3}));
    assertArrayEquals(
        new int[] {0, 0, 0}, replaceWithMultiplicationOtherMembers(new int[] {0, 0, 0}));
  }

  public int[] replaceWithMultiplicationOtherMembers(int[] arr) {

    int[] sol = new int[arr.length];
    int totMult = 1;
    int countZero = 0;
    boolean hasZero = false;

    for (int j : arr) {
      if (j == 0) {
        hasZero = true;
        countZero++;
      } else {
        totMult = totMult * j;
      }
    }

    if (countZero == arr.length) return arr;

    for (int i = 0; i < arr.length; i++) {
      sol[i] = 0;
      if (!hasZero) sol[i] = totMult / arr[i];
      if (hasZero && arr[i] == 0) sol[i] = totMult;
    }

    return sol;
  }

  @Test
  public void commonMinChars() {
    assertEquals(
        Arrays.asList('e', 'l', 'l'), commonMinChars(new String[] {"bella", "label", "roller"}, 3));
    assertEquals(Arrays.asList('c', 'o'), commonMinChars(new String[] {"cool", "lock", "cook"}, 3));
  }

  static int MAX_CHAR = 26;

  public List<Character> commonMinChars(String[] str, int n) {

    Boolean[] prim = new Boolean[MAX_CHAR];
    Arrays.fill(prim, Boolean.TRUE);
    int[] alphabetFreqCount = new int[MAX_CHAR];
    Arrays.fill(alphabetFreqCount, Integer.MAX_VALUE);

    for (int i = 0; i < n; i++) {

      Boolean[] sec = new Boolean[MAX_CHAR];
      Arrays.fill(sec, Boolean.FALSE);

      int[] wordAlphabetCount = new int[MAX_CHAR];

      for (int j = 0; j < str[i].length(); j++) {

        // if character is present in all strings before, mark it.
        if (prim[str[i].charAt(j) - 'a']) {

          sec[str[i].charAt(j) - 'a'] = true;
          wordAlphabetCount[str[i].charAt(j) - 'a'] = wordAlphabetCount[str[i].charAt(j) - 'a'] + 1;
        }
      }

      for (int j = 0; j < wordAlphabetCount.length; j++) {
        alphabetFreqCount[j] = Math.min(alphabetFreqCount[j], wordAlphabetCount[j]);
      }

      // copy whole secondary array into primary
      System.arraycopy(sec, 0, prim, 0, MAX_CHAR);
    }

    List<Character> sol = new ArrayList<Character>();

    for (int i = 0; i < 26; i++)
      if (prim[i]) {

        int j = 0;

        while (j < alphabetFreqCount[i]) {
          sol.add((char) (i + 'a'));
          j++;
        }
      }

    return sol;
  }
}
