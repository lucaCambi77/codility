/** */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author luca
 */
@ExtendWith(MockitoExtension.class)
class HackerRankLinkedListTest {
  private PrintStream out;

  @BeforeEach
  public void setUpStreams() {
    out = mock(PrintStream.class);
    System.setOut(out);
  }

  class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    /** */
    public SinglyLinkedListNode(int data) {
      this.data = data;
    }
  }

  class DoublyLinkedListNode {
    int data;
    DoublyLinkedListNode next;
    DoublyLinkedListNode prev;

    public DoublyLinkedListNode(int data) {
      this.data = data;
    }
  }

  @Test
  public void sortedInsert() {

    DoublyLinkedListNode node = new DoublyLinkedListNode(1);
    DoublyLinkedListNode node1 = new DoublyLinkedListNode(3);
    DoublyLinkedListNode node2 = new DoublyLinkedListNode(4);

    node2.next = new DoublyLinkedListNode(10);
    node1.next = node2;
    node.next = node1;

    sortedInsert(node, 5);

    DoublyLinkedListNode anode = new DoublyLinkedListNode(2);
    DoublyLinkedListNode anode1 = new DoublyLinkedListNode(3);

    anode1.next = new DoublyLinkedListNode(4);
    anode.next = anode1;

    sortedInsert(anode, 1);
  }

  private DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {

    DoublyLinkedListNode pointer = head;

    while (pointer != null) {

      if (data >= pointer.data) {

        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        newNode.next = pointer.next;

        pointer.next = newNode;
        break;
      } else if (pointer.prev == null) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        newNode.next = pointer;

        head = newNode;
        break;
      }

      pointer = pointer.next;
    }

    return head;
  }

  Stack<Integer> s1 = new Stack<>();
  Stack<Integer> s2 = new Stack<>();

  @Test
  public void queueUsingTwoStacks() {
    queueUsingTwoStacks(
        10, new String[] {"1 42", "2", "1 14", "3", "1 28", "3", "1 60", "1 78", "2", "2"});
    verify(out, times(2)).println(Integer.valueOf(14));
  }

  public void queueUsingTwoStacks(int t, String[] lines) {

    int count = 0;

    while (count < t) {

      String[] split = lines[count].split(" ");
      int query = Integer.parseInt(split[0]);
      switch (query) {
        case 1:
          s1.push(Integer.valueOf(split[1]));
          break;

        case 2:
          prepOld();
          s2.pop();

          break;

        case 3:
          prepOld();
          System.out.println(s2.peek());
          break;
      }
      count++;
    }
  }

  private void prepOld() {
    if (s2.isEmpty()) while (!s1.isEmpty()) s2.push(s1.pop());
  }

  @Test
  public void findMergeNode() {

    /*
     * SinglyLinkedListNode headA = getSortedLinkedListExample1(); SinglyLinkedListNode headB = getSortedLinkedListExample2();
     *
     * SinglyLinkedListNode currentA = headA; SinglyLinkedListNode currentB = headB;
     *
     * // Do till the two nodes are the same while (currentA != currentB) { // If you reached the end of one list start at the beginning of the
     * other one // currentA if (currentA.next == null) { currentA = headB; } else { currentA = currentA.next; } // currentB if (currentB.next ==
     * null) { currentB = headA; } else { currentB = currentB.next; } }
     */

  }

  @Test
  public void linkedListHasCycle() {

    SinglyLinkedListNode head = new SinglyLinkedListNode(3);

    head.next = new SinglyLinkedListNode(2);
    head.next.next = new SinglyLinkedListNode(0);
    head.next.next.next = new SinglyLinkedListNode(-4);
    head.next.next.next.next = head.next;

    assertTrue(hasCycle(head));

    head = new SinglyLinkedListNode(3);
    head.next = new SinglyLinkedListNode(2);

    assertFalse(hasCycle(head));
  }

  private static boolean hasCycle(SinglyLinkedListNode head) {

    if (head == null) {
      return false;
    }

    SinglyLinkedListNode slow = head;
    SinglyLinkedListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

  @Test
  public void removeDuplicates() throws IOException {

    InputStream is = new FileInputStream("src/test/resources/listWithDuplicate.txt");
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));

    String line = buf.readLine();
    SinglyLinkedListNode head2 = new SinglyLinkedListNode(1);

    int pos = 0;

    while (line != null) {
      insertAtPos(pos, Integer.parseInt(line), head2);
      line = buf.readLine();
      pos++;
    }

    buf.close();

    removeDuplicates(head2);

    // check no duplicates
    Set<Integer> seen = new HashSet<>();
    boolean check;
    while (head2 != null) {
      check = seen.add(head2.data);
      assertTrue(check);
      head2 = head2.next;
    }
  }

  private void removeDuplicates(SinglyLinkedListNode node) {
    SinglyLinkedListNode tmp = node;

    while (tmp.next != null) {
      if (tmp.data == tmp.next.data) tmp.next = tmp.next.next;
      else tmp = tmp.next;
    }
  }

  @Test
  public void deleteNodeAtPos() {

    int position = 1;
    SinglyLinkedListNode head = getLinkedListExample1();

    deleteNodeAtPosition(position, head);

    SinglyLinkedListNode sol = new SinglyLinkedListNode(16);
    sol.next = new SinglyLinkedListNode(17);

    while (head != null && sol != null) {
      assertEquals(head.data, sol.data);
      head = head.next;
      sol = sol.next;
    }
  }

  /**
   * @param position
   * @param head
   * @return
   */
  private SinglyLinkedListNode deleteNodeAtPosition(int position, SinglyLinkedListNode head) {
    if (position == 0) {
      head = head.next;
      return head;
    }

    int pos = 0;
    SinglyLinkedListNode temp = head;
    while (pos != position - 1) {
      temp = temp.next;
      pos++;
    }

    temp.next = temp.next.next;

    return head;
  }

  @Test
  public void getNode() {
    SinglyLinkedListNode head = getLinkedListExample2();
    int positionFromTail = 2;

    Stack<Integer> stack = new Stack<Integer>();

    while (head != null) {
      stack.add(head.data);
      head = head.next;
    }
    int size = stack.size();
    int result = stack.get(0);
    for (int i = 0; i < size; i++) {

      int data = stack.pop();

      if (positionFromTail == i) result = data;
    }

    assertEquals(15, result);
  }

  @Test
  public void mergeLists() {
    SinglyLinkedListNode head2 = getSortedLinkedListExample1();
    SinglyLinkedListNode head1 = getSortedLinkedListExample2();

    while (head1 != null) {

      int pos = 0;

      SinglyLinkedListNode head = head2;

      while (head != null) {
        if (head1.data <= head.data) {
          head2 = insertAtPos(pos, head1.data, head2);

          break;
        }
        head = head.next;
        pos++;
      }

      if (head == null) head2 = insertAtPos(pos, head1.data, head2);

      head1 = head1.next;
    }
  }

  @Test
  public void compareLists() {
    SinglyLinkedListNode head2 = getLinkedListExample1();
    SinglyLinkedListNode head1 = getLinkedListExample2();

    boolean areEquals = true;
    while (head2 != null && head1 != null) {
      if (head2.data != head1.data) {
        areEquals = false;
        break;
      }

      head1 = head1.next;
      head2 = head2.next;
    }

    assertFalse(areEquals);
  }

  @Test
  public void reversePrint() {

    SinglyLinkedListNode head = getLinkedListExample1();

    AtomicInteger aInt = new AtomicInteger();
    SinglyLinkedListNode out = reversePrint(aInt, head, null);

    assertEquals(17, out.data);
    assertEquals(13, out.next.data);
    assertEquals(16, out.next.next.data);
  }

  private SinglyLinkedListNode reversePrint(
      AtomicInteger pos, SinglyLinkedListNode head, SinglyLinkedListNode out) {

    if (head != null) {
      out = reversePrint(pos, head.next, out);
      out = insertAtPos(pos.getAndIncrement(), head.data, out);
    }

    return out;
  }

  public SinglyLinkedListNode insertAtPos(int position, int data, SinglyLinkedListNode head) {

    SinglyLinkedListNode nextAtInsert = new SinglyLinkedListNode(data);

    if (null == head || position == 0) {
      nextAtInsert.next = head;
      return nextAtInsert;
    }

    int pos = 0;

    SinglyLinkedListNode temp = head;

    while (pos != position - 1) {
      temp = temp.next;
      pos++;
    }

    nextAtInsert.next = temp.next;

    temp.next = nextAtInsert;

    return head;
  }

  @Test
  public void insertNodeAtPosition() {

    SinglyLinkedListNode temp = insertNodeAtPosition(10, 1, getLinkedListExample1());
    assertEquals(10, temp.next.data);
  }

  @Test
  public void insertNodeAtHead() {

    SinglyLinkedListNode temp = insertNodeAtPosition(10, 0, getLinkedListExample1());
    assertEquals(10, temp.data);
  }

  private SinglyLinkedListNode insertNodeAtPosition(
      int data, int position, SinglyLinkedListNode head) {
    int pos = 0;

    SinglyLinkedListNode nextAtInsert = new SinglyLinkedListNode(data);

    if (position == 0) {
      nextAtInsert.next = head;
      return nextAtInsert;
    }

    SinglyLinkedListNode temp = head;

    while (pos != position - 1) {
      temp = temp.next;
      pos++;
    }

    nextAtInsert.next = temp.next;

    temp.next = nextAtInsert;
    return temp;
  }

  @Test
  public void insertNodeAtTail() {

    SinglyLinkedListNode last = insertNodeAtTail(getLinkedListExample1(), 100);

    while (last.next != null) {
      last = last.next;
    }

    assertEquals(100, last.data);
  }

  private SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode sll, int lastNodeValue) {
    SinglyLinkedListNode last = sll;

    while (last.next != null) {
      last = last.next;
    }

    last.next = new SinglyLinkedListNode(lastNodeValue);
    return last;
  }

  @Test
  public void printLinkedList() {
    SinglyLinkedListNode sll = getLinkedListExample1();

    while (sll != null) {
      System.out.println(sll.data);
      sll = sll.next;
    }
  }

  private SinglyLinkedListNode getLinkedListExample1() {
    SinglyLinkedListNode sll = new SinglyLinkedListNode(16);

    SinglyLinkedListNode sllNext = new SinglyLinkedListNode(13);
    sllNext.next = new SinglyLinkedListNode(17);

    sll.next = sllNext;

    return sll;
  }

  private SinglyLinkedListNode getLinkedListExample2() {
    SinglyLinkedListNode sll = new SinglyLinkedListNode(15);

    SinglyLinkedListNode slln = new SinglyLinkedListNode(18);
    slln.next = new SinglyLinkedListNode(12);

    sll.next = slln;

    return sll;
  }

  private SinglyLinkedListNode getSortedLinkedListExample1() {
    SinglyLinkedListNode sll = new SinglyLinkedListNode(1);

    SinglyLinkedListNode slln = new SinglyLinkedListNode(2);
    slln.next = new SinglyLinkedListNode(3);

    sll.next = slln;

    return sll;
  }

  private SinglyLinkedListNode getSortedLinkedListExample2() {
    SinglyLinkedListNode sll = new SinglyLinkedListNode(3);

    sll.next = new SinglyLinkedListNode(4);

    return sll;
  }
}
