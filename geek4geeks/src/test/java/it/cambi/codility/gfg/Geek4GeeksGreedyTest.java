/** */
package it.cambi.codility.gfg;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
class Geek4GeeksGreedyTest {

  @Test
  public void remainingString() {

    assertEquals("folder3/", ordinalIndexOf("/folder1/folder2/folder3/", "/", 3));
    assertEquals("Empty string", ordinalIndexOf("/folder1/folder2/folder3/", "/", 4));
    assertEquals("ksforgeeks", ordinalIndexOf("geeksforgeeks", "e", 2));
    assertEquals("ng", ordinalIndexOf("Thisisdemostring", "i", 3));
    assertEquals("Empty string", ordinalIndexOf("Helloeveryone", "y", 2));
  }

  private String ordinalIndexOf(String str, String substr, int n) {

    if (n == 0) return str;

    int pos = str.indexOf(substr);
    while (--n > 0 && pos != -1) pos = str.indexOf(substr, pos + 1);

    /**
     * If i have found requested frequency and it is not the last occurrence and there is at least
     * one more possible character to match
     */
    if (n == 0 && pos != -1 && pos != str.length() - 1) return str.substring(pos + 1, str.length());

    return "Empty string";
  }

  @Test
  public void firstElementToOccourKTime() {
    assertEquals(7, firstElementToOccourKTime(new String("1 7 4 3 4 8 7"), 2));
    assertEquals(-1, firstElementToOccourKTime(new String("1 7 4 3 4 8 7"), 3));
  }

  private int firstElementToOccourKTime(String input, int freqToCheck) {

    Integer[] array =
        Arrays.asList(input.split(" ")).stream().map(Integer::valueOf).toArray(Integer[]::new);

    Map<Integer, Integer> valueToFreq = new HashMap<>();
    /** Map of key to Value - Position to find in case the first occurrence in array */
    Map<Integer, Map<Integer, Integer>> freqTovalue = new HashMap<>();

    for (int i = 0; i < array.length; i++) {

      int freq = valueToFreq.getOrDefault(array[i], 0);
      valueToFreq.put(array[i], freq + 1);

      Map<Integer, Integer> set = freqTovalue.getOrDefault(freq, new HashMap<Integer, Integer>());
      Integer position = set.get(array[i]) == null ? i : set.get(array[i]);
      set.remove(array[i]);

      freqTovalue.put(freq, set);

      Map<Integer, Integer> set1 =
          freqTovalue.getOrDefault(freq + 1, new HashMap<Integer, Integer>());
      set1.put(array[i], position);

      freqTovalue.put(freq + 1, set1);
    }

    try {

      return freqTovalue.entrySet().stream()
          .filter(m -> m.getKey() == freqToCheck)
          .findFirst()
          .orElse(null)
          .getValue()
          .entrySet()
          .stream()
          .sorted(Comparator.comparingInt(Map.Entry::getValue))
          .findFirst()
          .map(Map.Entry::getKey)
          .get();
    } catch (Exception e) {
      /** If we don't have that frequency or no more elements with that frequency */
      return -1;
    }
  }

  @Test
  public void uncommonCharacters() {
    assertEquals("bclpr", uncommonCharacters("characters", "alphabets"));
  }

  private String uncommonCharacters(String s1, String s2) {

    Set<Integer> set = s1.chars().distinct().boxed().collect(Collectors.toSet());

    Set<Integer> set1 = s2.chars().distinct().boxed().collect(Collectors.toSet());

    for (Integer element : set) {
      // .add() returns false if element already exists
      if (!set1.add(element)) {
        set1.remove(element);
      }
    }

    return set1.stream()
        .sorted()
        .map(e -> Character.toString((char) e.intValue()))
        .reduce("", String::concat);
  }

  @Test
  public void extractMaximum() {
    assertEquals(564, extractMaximum("100klh564abc365bg"));
    assertEquals(9, extractMaximum("abvhd9sdnkjdfs"));
    assertEquals(0, extractMaximum("abchsd0sdhs"));
  }

  private int extractMaximum(String s) {

    String[] s1 = s.replaceAll("[a-z]", " ").split(" ");

    return Arrays.asList(s1).stream()
        .filter(c -> !c.isEmpty())
        .mapToInt(Integer::valueOf)
        .max()
        .getAsInt();
  }

  @Test
  public void sumOfNumberInString() {
    assertEquals(24, sumOfNumberInString("1abc23"));
    assertEquals(4, sumOfNumberInString("geeks4geeks"));
    assertEquals(100, sumOfNumberInString("1abc2x30yz67"));
    assertEquals(123, sumOfNumberInString("123abc"));
  }

  private int sumOfNumberInString(String s) {

    String[] s1 = s.replaceAll("[a-z]", " ").split(" ");

    return Arrays.asList(s1).stream().filter(c -> !c.isEmpty()).mapToInt(Integer::valueOf).sum();
  }

  @Test
  public void shopInCandyStore() {
    int n = 4;
    int k = 2;
    int[] candies = new int[] {1, 2, 4, 3};

    Arrays.sort(candies);
    int minCost = candies[0];

    for (int i = n - k - 1; i > 0; i--) {
      minCost += candies[i];
    }

    System.out.println(minCost);
    int maxCost = candies[n - 1];

    for (int i = k; i < n - 1; i++) {
      maxCost += candies[i];
    }

    System.out.println(maxCost);
  }

  @Test
  public void minimumOperations() {
    int value = 9;

    minimumOperations(value, 0);
  }

  private void minimumOperations(int value, int count) {
    if (value == 1) {
      System.out.println(++count);
      return;
    }

    if (value == 2) {
      ++count;
      System.out.println(++count);
      return;
    }

    if (value == 3) {
      ++count;
      ++count;
      System.out.println(++count);
      return;
    }

    if (((value & 1) == 1)) {
      minimumOperations(--value, ++count);
    } else {
      minimumOperations(value / 2, ++count);
    }
  }
}
