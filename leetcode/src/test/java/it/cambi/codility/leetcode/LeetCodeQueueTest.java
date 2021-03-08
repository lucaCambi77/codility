package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeetCodeQueueTest {

  class MovingAverage {
    Deque<Integer> deque;
    int maxSize;
    int sum;

    public MovingAverage(int size) {
      deque = new LinkedList<>();
      maxSize = size;
    }

    public double next(int val) {
      deque.push(val);

      sum += Optional.ofNullable(deque.peek()).orElse(0);

      if (deque.size() == maxSize + 1) sum -= deque.removeLast();

      double value = deque.size() < maxSize ? (double) sum / deque.size() : (double) sum / maxSize;
      return new BigDecimal(Double.toString(value)).setScale(5, RoundingMode.HALF_UP).doubleValue();
    }
  }

  @Test
  public void movingAverage() {
    MovingAverage movingAverage = new MovingAverage(3);
    assertEquals(1.0, movingAverage.next(1));
    assertEquals(5.5, movingAverage.next(10));
    assertEquals(4.66667, movingAverage.next(3));
    assertEquals(6.0, movingAverage.next(5));
  }
}
