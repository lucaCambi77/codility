package company;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SabreInterview {

  @Test
  public void sumOfFirstEvenMoreThan5ValueFromEachList() {
    assertEquals(
        16,
        sumOfFirstEvenMoreThan5ValueFromEachList(
            List.of(List.of(4, 8, 6), List.of(7), List.of(7, 8, 6))));
  }

  private Integer sumOfFirstEvenMoreThan5ValueFromEachList(List<List<Integer>> input) {

    return input.stream()
        .map(
            l ->
                l.stream()
                    .filter(integer -> (integer & 1) == 0 && integer > 5)
                    .findFirst()
                    .orElse(0))
        .reduce(0, Integer::sum);
    /*
    int sol = 0;

    for (List<Integer> list : input) {
      for (Integer integer : list) {
        if ((integer & 1) == 0 && integer > 5) {
          sol += integer;
          break;
        }
      }
    }

    return sol;*/
  }
}
