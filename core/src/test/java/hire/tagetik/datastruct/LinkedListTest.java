package hire.tagetik.datastruct;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTest {

  @Test
  void testAdd() {
    LinkedList linkedList = new LinkedList();
    linkedList.add(1);
    linkedList.add(2);

    assertEquals(2, linkedList.size());
    assertEquals(1, linkedList.peek());
    assertEquals(2, linkedList.last());
  }

  @Test
  void testAddFirst() {
    LinkedList linkedList = new LinkedList();
    linkedList.add(1);
    linkedList.addFirst(2);

    assertEquals(2, linkedList.size());
    assertEquals(2, linkedList.peek());
    assertEquals(1, linkedList.last());
  }
}
