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

    for (int i = 1; i < input.length; i++) {
      //
      int l = 0;
      StringBuilder stringBuilder = new StringBuilder();

      while (l < input[i].length()
          && l < input[i - 1].length()
          && input[i].charAt(l) == input[i - 1].charAt(l)) {
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
