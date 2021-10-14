package it.cambi.codility.qualified;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Eidosmedia {

  @Test
  public void arrayPacking() {
    assertEquals(21784, arrayPacking(Arrays.asList(24, 85, 0)));
  }

  private Integer arrayPacking(List<Integer> integers) {

    return Integer.parseInt(
        IntStream.range(0, integers.size())
            .mapToObj(
                i ->
                    String.format(
                            "%8s", Integer.toBinaryString(integers.get(integers.size() - 1 - i)))
                        .replace(' ', '0'))
            .collect(Collectors.joining()),
        2);
  }

  @Test
  public void markdownParser() {

    assertEquals("<h1>Header</h1>", markdownParser("# Header"));
    assertEquals("<h2>Header</h2>", markdownParser("## Header"));
    assertEquals("<h6>Header</h6>", markdownParser("###### Header"));
    assertEquals("wrong header", markdownParser("wrong header"));
    assertEquals("<h4>Space Jam</h4>", markdownParser("  #### Space Jam"));
    assertEquals(" ####### Space Jam", markdownParser(" ####### Space Jam"));
    assertEquals("<h6>Space Jam</h6>", markdownParser(" ###### Space Jam"));
    assertEquals("######## Space Jam", markdownParser("######## Space Jam"));
  }

  public String markdownParser(String markdown) {

    if (!markdown.matches("(\\s)*(#){1,6} (.*?)")) return markdown;

    String stringToSplit = markdown.trim();

    String[] split = stringToSplit.split("\\s", 2);

    String header = "<h" + split[0].trim().length() + ">";
    return header + split[1].trim() + header.replace("<", "</");
  }

  @Test
  public void firstNonRepeatingLetter() {
    assertEquals("t", firstNonRepeatingLetter("stress"));
    assertEquals("T", firstNonRepeatingLetter("STreSS"));
    assertEquals("c", firstNonRepeatingLetter("ciao"));
    assertEquals("c", firstNonRepeatingLetter("c"));
    assertEquals("", firstNonRepeatingLetter("cc"));
    assertEquals("", firstNonRepeatingLetter("ccdd"));
  }

  public String firstNonRepeatingLetter(String str) {
    char[] count = new char[256];

    int i;

    for (i = 0; i < str.length(); i++) count[Character.toLowerCase(str.charAt(i))]++;

    for (i = 0; i < str.length(); i++) if (count[Character.toLowerCase(str.charAt(i))] == 1) break;

    return i == str.length() ? "" : String.valueOf(str.charAt(i));
  }

  @Test
  public void possibilities() {

    assertEquals(Collections.singletonList("E"), possibilities("."));
    assertEquals(Collections.singletonList("T"), possibilities("-"));
    assertEquals(Collections.singletonList("N"), possibilities("-."));
    assertEquals(Arrays.asList("E", "T"), possibilities("?"));
    assertEquals(Arrays.asList("I", "A"), possibilities(".?"));
    assertEquals(Arrays.asList("R", "W", "G", "O"), possibilities("?-?"));
  }

  public static List<String> possibilities(String signals) {

    LinkedHashMap<String, String> morse = new LinkedHashMap<>();
    morse.put("....", "H");
    morse.put("...", "S");
    morse.put("..", "I");
    morse.put(".", "E");
    morse.put("...-", "V");
    morse.put("..-", "U");
    morse.put(".-.", "R");
    morse.put("..-.", "F");
    morse.put(".-..", "L");
    morse.put(".--", "W");
    morse.put(".--.", "P");
    morse.put(".---", "J");
    morse.put(".-", "A");
    morse.put("-...", "B");
    morse.put("-..", "D");
    morse.put("-.-.", "C");
    morse.put("-.-", "K");
    morse.put("--.", "G");
    morse.put("-.", "N");
    morse.put("--", "M");
    morse.put("---", "O");
    morse.put("-", "T");
    morse.put("--.-", "Q");
    morse.put("-..-", "X");
    morse.put("-.--", "Y");
    morse.put("--..", "X");

    if (!signals.contains("?")) return Collections.singletonList(morse.get(signals));

    if (signals.contains("?") && signals.length() == 1) return Arrays.asList("E", "T");

    String expression = signals.replaceAll("\\?", "[.-]").replaceAll("\\.", "\\\\.");

    ArrayList<String> solution = new ArrayList<>();

    for (Map.Entry<String, String> s : morse.entrySet())
      if (s.getKey().matches(expression)) solution.add(s.getValue());

    return solution;
  }
}
