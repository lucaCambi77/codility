package it.cambi.codility.codebyte;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoderByteArray {

  @Test
  public void findIntersection() {
    assertEquals("1,4,13", findIntersection(new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}));
  }

  public String findIntersection(String[] strArr) {

    int[] a =
        Arrays.stream(strArr[0].split(",")).mapToInt(v -> Integer.parseInt(v.trim())).toArray();
    int[] b =
        Arrays.stream(strArr[1].split(",")).mapToInt(v -> Integer.parseInt(v.trim())).toArray();

    int ai = 0;
    int bi = 0;

    LinkedList<String> list = new LinkedList<>();

    while (bi < b.length && ai < a.length) {

      if (b[bi] == a[ai]) {
        list.add(Integer.toString(b[bi++]));
        ai++;
      } else if (b[bi] > a[ai]) ai++;
      else if (b[bi] < a[ai]) bi++;
    }

    // code goes here
    return list.stream().collect(Collectors.joining(","));
  }
}
