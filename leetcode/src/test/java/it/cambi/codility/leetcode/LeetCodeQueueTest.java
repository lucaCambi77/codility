package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class LeetCodeQueueTest {

  static class MovingAverage {
    Deque<Integer> deque;
    int maxSize;
    int sum;

    public MovingAverage(int size) {
      deque = new LinkedList<>();
      maxSize = size;
    }

    public double next(int val) {
      deque.push(val);

      if (deque.size() == maxSize + 1) sum -= deque.removeLast();

      sum += val;

      int n = Math.min(deque.size(), maxSize);

      return Math.round((double) sum / n * 100000.0) / 100000.0;
    }
  }

  @Test
  void movingAverage() {
    MovingAverage movingAverage = new MovingAverage(3);
    assertEquals(1.0, movingAverage.next(1));
    assertEquals(5.5, movingAverage.next(10));
    assertEquals(4.66667, movingAverage.next(3));
    assertEquals(6.0, movingAverage.next(5));
  }

  @Test
  void countStudents() {
    assertEquals(0, countStudents(new int[] {1, 1, 0, 0}, new int[] {0, 1, 0, 1}));
    assertEquals(3, countStudents(new int[] {1, 1, 1, 0, 0, 1}, new int[] {1, 0, 0, 0, 1, 1}));
  }

  private int countStudents(int[] students, int[] sandwiches) {

    Deque<Integer> dequeStud =
        Arrays.stream(students).boxed().collect(Collectors.toCollection(LinkedList::new));

    int sz = students.length;
    int ans = sz;
    int i = 0;
    int cycle = 0;

    while (i < sz && cycle < sz) {

      int stud = dequeStud.pop();

      if (stud != sandwiches[i]) {
        dequeStud.addLast(stud);
        cycle++;
      } else {
        cycle = 0;
        i++;
        ans--;
      }
    }

    return ans;
  }

  class MyCircularQueue {

    int[] queue;
    int pointStart = 0;
    int pointEnd = -1;
    int size;
    int elements = 0;

    public MyCircularQueue(int k) {
      queue = new int[k];
      this.size = k;
    }

    public boolean enQueue(int value) {
      if (isFull()) return false;

      pointEnd = (pointEnd + 1) % size;

      queue[pointEnd] = value;
      elements++;
      return true;
    }

    public boolean deQueue() {
      if (isEmpty()) return false;

      pointStart = (pointStart + 1) % size;

      elements--;
      return true;
    }

    public int Front() {
      if (isEmpty()) return -1;
      return queue[pointStart];
    }

    public int Rear() {
      if (isEmpty()) return -1;
      return queue[pointEnd];
    }

    public boolean isEmpty() {
      return elements == 0;
    }

    public boolean isFull() {
      return size == elements;
    }
  }

  /**
   * Your MyCircularQueue object will be instantiated and called as such: MyCircularQueue obj = new
   * MyCircularQueue(k); boolean param_1 = obj.enQueue(value); boolean param_2 = obj.deQueue(); int
   * param_3 = obj.Front(); int param_4 = obj.Rear(); boolean param_5 = obj.isEmpty(); boolean
   * param_6 = obj.isFull();
   */
  @Test
  void circularQueue() {

    MyCircularQueue myCircularQueue;

    myCircularQueue = new MyCircularQueue(3);
    assertTrue(myCircularQueue.enQueue(1));
    assertTrue(myCircularQueue.enQueue(2));
    assertTrue(myCircularQueue.enQueue(3));
    assertFalse(myCircularQueue.enQueue(4));
    assertEquals(3, myCircularQueue.Rear());
    assertTrue(myCircularQueue.isFull());
    assertTrue(myCircularQueue.deQueue());
    assertTrue(myCircularQueue.enQueue(4));
    assertEquals(4, myCircularQueue.Rear());

    myCircularQueue = new MyCircularQueue(6);
    assertTrue(myCircularQueue.enQueue(6));
    assertEquals(6, myCircularQueue.Rear());
    assertEquals(6, myCircularQueue.Rear());
    assertTrue(myCircularQueue.deQueue());
    assertTrue(myCircularQueue.enQueue(5));
    assertEquals(5, myCircularQueue.Rear());
    assertTrue(myCircularQueue.deQueue());
    assertEquals(-1, myCircularQueue.Front());

    myCircularQueue = new MyCircularQueue(2);
    assertTrue(myCircularQueue.enQueue(8));
    assertTrue(myCircularQueue.enQueue(8));
    assertEquals(8, myCircularQueue.Front());
    assertFalse(myCircularQueue.enQueue(4));
    assertTrue(myCircularQueue.deQueue());
    assertTrue(myCircularQueue.enQueue(1));
    assertFalse(myCircularQueue.enQueue(1));
    assertEquals(1, myCircularQueue.Rear());
    assertFalse(myCircularQueue.isEmpty());
    assertEquals(8, myCircularQueue.Front());
    assertTrue(myCircularQueue.deQueue());
  }
}
