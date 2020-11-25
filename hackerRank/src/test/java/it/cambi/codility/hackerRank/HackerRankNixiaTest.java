package it.cambi.codility.hackerRank;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackerRankNixiaTest {

  @Test
  public void test1() {

    assertEquals(
        Collections.singletonList("item3"),
        fetchItemsToDisplay(
            Arrays.asList(
                Arrays.asList("item2", "3", "4"),
                Arrays.asList("item1", "10", "15"),
                Arrays.asList("item3", "17", "8")),
            1,
            0,
            2,
            1));

    assertEquals(
        Collections.singletonList("p1"),
        fetchItemsToDisplay(
            Arrays.asList(Arrays.asList("p1", "1", "2"), Arrays.asList("p2", "2", "1")),
            0,
            0,
            1,
            0));
  }

  public List<String> fetchItemsToDisplay(
      List<List<String>> items,
      int sortParameter,
      int sortOrder,
      int itemsPerPage,
      int pageNumber) {

    int start = pageNumber * itemsPerPage;
    int end = Math.min(start + itemsPerPage, items.size());

    if (sortParameter == 0) {

      if (sortOrder == 0) items.sort(Comparator.comparing(l -> l.get(0)));
      else items.sort((l1, l2) -> l2.get(0).compareTo(l1.get(0)));

    } else if (sortParameter == 1) {
      if (sortOrder == 0) items.sort(Comparator.comparingInt(l -> Integer.parseInt(l.get(1))));
      else items.sort((l1, l2) -> Integer.parseInt(l2.get(1)) - Integer.parseInt(l1.get(1)));

    } else {
      if (sortOrder == 0) items.sort(Comparator.comparingInt(l -> Integer.parseInt(l.get(2))));
      else items.sort((l1, l2) -> Integer.parseInt(l2.get(2)) - Integer.parseInt(l1.get(2)));
    }

    return items.subList(start, end).stream()
        .map(r -> r.get(0))
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Test
  public void test2() throws IOException {
    assertEquals(
        0, numberOfTokens(3, Arrays.asList(Arrays.asList(0, 1, 1), Arrays.asList(1, 1, 5))));

    assertEquals(
        1,
        numberOfTokens(
            3,
            Arrays.asList(Arrays.asList(0, 1, 1), Arrays.asList(1, 1, 4), Arrays.asList(1, 2, 5))));

    assertEquals(
        1,
        numberOfTokens(
            4,
            Arrays.asList(
                Arrays.asList(0, 1, 1),
                Arrays.asList(0, 2, 2),
                Arrays.asList(1, 1, 5),
                Arrays.asList(1, 2, 7))));

    try (BufferedReader in =
        new BufferedReader(new FileReader("src/test/resources/input002.txt"))) {

      String line = in.readLine();
      int expireLimit = Integer.parseInt(line);
      int n = Integer.parseInt(in.readLine());
      in.readLine();

      List<List<Integer>> list = new ArrayList<>();
      int i = 0;

      while (i < n) {
        String[] input = in.readLine().split(" ");

        list.add(
            i,
            Arrays.asList(
                Integer.parseInt(input[0]),
                Integer.parseInt(input[1]),
                Integer.parseInt(input[2])));
        i++;
      }

      assertEquals(4, numberOfTokens(expireLimit, list));
    }
  }

  public int numberOfTokens(int expiryLimit, List<List<Integer>> commands) {
    // Write your code here
    Map<Integer, Integer> map = new HashMap<>();

    for (List<Integer> command : commands) {

      if (command.get(0) == 0) {
        map.put(command.get(1), command.get(2) + expiryLimit);
      } else {
        if (map.get(command.get(1)) != null) {
          if (command.get(2) > map.get(command.get(1))) map.remove(command.get(1));
          else map.put(command.get(1), command.get(2) + expiryLimit);
        }
      }
    }

    return map.size();
  }
}
