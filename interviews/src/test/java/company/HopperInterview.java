package company;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HopperInterview {

  @Test
  public void shortestUniquePrefix() {
    assertTrue(
        List.of("dog", "dov", "du", "z")
            .containsAll(
                shortestUniquePrefix(new String[] {"dog", "dog1", "duck", "zebra", "dove"})));
  }

  public Set<String> shortestUniquePrefix(String[] input) {

    Arrays.sort(input);
    Set<String> set = new HashSet<>();
    set.add(input[0]);

    int l;
    int minLength;

    for (int i = 1; i < input.length; i++) {
      //
      l = 0;
      StringBuilder stringBuilder = new StringBuilder();

      minLength = Math.min(input[i].length(), input[i - 1].length());

      while (l < minLength && input[i].charAt(l) == input[i - 1].charAt(l)) {
        stringBuilder.append(input[i].charAt(l));
        l++;
      }

      if (!set.contains(stringBuilder.toString())) {
        String s = input[i].substring(0, l + 1);
        set.add(s);
      }
    }

    return set;
  }
}
