package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterviewBitLinkedListTest {

  static class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  private ListNode listNode;

  @Test
  public void kReverseLinkedList() {

    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    ListNode reversedList = kReverseLinkedList(head, 2, null);

    ListNode sol = new ListNode(2);
    sol.next = new ListNode(1);
    sol.next.next = new ListNode(4);
    sol.next.next.next = new ListNode(3);
    sol.next.next.next.next = new ListNode(6);
    sol.next.next.next.next.next = new ListNode(5);

    assertTrue(areIdentical(sol, reversedList));
  }

  public ListNode kReverseLinkedList(ListNode listNode, int k, ListNode sol) {

    if (listNode == null) return sol;

    int segm = k;

    Stack<Integer> stack = new Stack<>();

    while (listNode != null && segm-- > 0) {
      stack.add(listNode.val);
      listNode = listNode.next;
    }

    if (sol == null) sol = new ListNode(stack.pop());

    ListNode tmp1 = sol;

    while (tmp1.next != null) {
      tmp1 = tmp1.next;
    }

    while (!stack.isEmpty()) {
      tmp1.next = new ListNode(stack.pop());
      tmp1 = tmp1.next;
    }

    return kReverseLinkedList(listNode, k, sol);
  }

  @Test
  public void removeDuplicatesFromSortedList() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(2);
    head.next.next.next.next = new ListNode(3);
    head.next.next.next.next.next = new ListNode(4);
    head.next.next.next.next.next.next = new ListNode(4);

    removeDuplicatesFromSortedList(head);

    ListNode sol = new ListNode(1);
    sol.next = new ListNode(2);
    sol.next.next = new ListNode(3);
    sol.next.next.next = new ListNode(4);

    assertTrue(areIdentical(sol, head));
  }

  public void removeDuplicatesFromSortedList(ListNode node) {

    ListNode tmp = node;

    while (tmp.next != null) {
      if (tmp.val == tmp.next.val) tmp.next = tmp.next.next;
      else tmp = tmp.next;
    }
  }

  @Test
  public void reverseList() {

    ListNode headSol1 = new ListNode(1);
    headSol1.next = new ListNode(2);
    headSol1.next.next = new ListNode(3);
    headSol1.next.next.next = new ListNode(4);
    headSol1.next.next.next.next = new ListNode(5);

    reverseList(headSol1);

    ListNode sol = new ListNode(5);
    sol.next = new ListNode(4);
    sol.next.next = new ListNode(3);
    sol.next.next.next = new ListNode(2);
    sol.next.next.next.next = new ListNode(1);

    assertTrue(areIdentical(listNode, sol));
  }

  public void reverseList(ListNode A) {

    if (A.next == null) {
      listNode = A;
      return;
    }

    reverseList(A.next);

    ListNode tmp = listNode;
    while (tmp.next != null) {
      tmp = tmp.next;
    }
    tmp.next = new ListNode(A.val);
  }

  @Test
  public void kthNodeFromMiddle() {
    ListNode headSol1 = new ListNode(3);
    headSol1.next = new ListNode(4);
    headSol1.next.next = new ListNode(7);
    headSol1.next.next.next = new ListNode(5);
    headSol1.next.next.next.next = new ListNode(16);
    headSol1.next.next.next.next.next = new ListNode(15);
    headSol1.next.next.next.next.next.next = new ListNode(61);
    headSol1.next.next.next.next.next.next.next = new ListNode(16);

    assertEquals(4, kthNodeFromMiddle(headSol1, 3));

    ListNode headSol = new ListNode(1);
    headSol.next = new ListNode(14);
    headSol.next.next = new ListNode(6);
    headSol.next.next.next = new ListNode(16);
    headSol.next.next.next.next = new ListNode(4);
    headSol.next.next.next.next.next = new ListNode(10);

    assertEquals(14, kthNodeFromMiddle(headSol, 2));

    ListNode headSol2 = new ListNode(1);
    headSol2.next = new ListNode(14);
    headSol2.next.next = new ListNode(6);
    headSol2.next.next.next = new ListNode(16);
    headSol2.next.next.next.next = new ListNode(4);
    headSol2.next.next.next.next.next = new ListNode(10);

    assertEquals(-1, kthNodeFromMiddle(headSol2, 10));

    ListNode headSol3 = new ListNode(1);
    headSol3.next = new ListNode(14);
    headSol3.next.next = new ListNode(6);
    headSol3.next.next.next = new ListNode(16);
    headSol3.next.next.next.next = new ListNode(4);
    headSol3.next.next.next.next.next = new ListNode(10);

    assertEquals(1, kthNodeFromMiddle(headSol3, 3));
  }

  public int kthNodeFromMiddle(ListNode A, int B) {
    int count = 0;

    ListNode temp = A;

    while (temp != null) {
      count++;
      temp = temp.next;
    }

    int middle = (count / 2) + 1;
    int fromStart = middle - 1 - B;

    if (fromStart < 0) return -1;

    ListNode temp1 = A;

    while (fromStart-- > 0) {
      temp1 = temp1.next;
    }

    return temp1.val;
  }

  @Test
  public void sortBinaryLinkedList() {
    ListNode head = new ListNode(1);
    head.next = new ListNode(0);
    head.next.next = new ListNode(1);

    ListNode sol = sortBinaryLinkedList(head);

    ListNode headSol = new ListNode(0);
    headSol.next = new ListNode(1);
    headSol.next.next = new ListNode(1);

    assertTrue(areIdentical(headSol, sol));

    ListNode head1 = new ListNode(0);
    head1.next = new ListNode(1);
    head1.next.next = new ListNode(0);
    head1.next.next.next = new ListNode(1);
    head1.next.next.next.next = new ListNode(0);

    ListNode sol1 = sortBinaryLinkedList(head1);

    ListNode headSol1 = new ListNode(0);
    headSol1.next = new ListNode(0);
    headSol1.next.next = new ListNode(0);
    headSol1.next.next.next = new ListNode(1);
    headSol1.next.next.next.next = new ListNode(1);

    assertTrue(areIdentical(headSol1, sol1));
  }

  @Test
  public void partition() {

    ListNode node = new ListNode(1);
    node.next = new ListNode(4);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(2);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(2);

    ListNode sol = partition(node, 3);

    ListNode headSol1 = new ListNode(1);
    headSol1.next = new ListNode(2);
    headSol1.next.next = new ListNode(2);
    headSol1.next.next.next = new ListNode(4);
    headSol1.next.next.next.next = new ListNode(3);
    headSol1.next.next.next.next.next = new ListNode(5);

    assertTrue(areIdentical(headSol1, sol));

    node = new ListNode(1);
    node.next = new ListNode(10);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);

    sol = partition(node, 5);

    headSol1 = new ListNode(1);
    headSol1.next = new ListNode(3);
    headSol1.next.next = new ListNode(4);
    headSol1.next.next.next = new ListNode(10);
    headSol1.next.next.next.next = new ListNode(5);

    assertTrue(areIdentical(headSol1, sol));

    node = new ListNode(10);
    node.next = new ListNode(1);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);

    sol = partition(node, 5);

    headSol1 = new ListNode(1);
    headSol1.next = new ListNode(3);
    headSol1.next.next = new ListNode(4);
    headSol1.next.next.next = new ListNode(10);
    headSol1.next.next.next.next = new ListNode(5);

    assertTrue(areIdentical(headSol1, sol));
  }

  public ListNode partition(ListNode A, int B) {

    ListNode lessThan = A;
    ListNode greatherThan = A;
    LinkedList<Integer> list = new LinkedList<>();

    while (greatherThan != null && lessThan != null) {

      if (greatherThan.val < B) {
        lessThan.val = greatherThan.val;
        lessThan = lessThan.next;
      } else {
        list.add(greatherThan.val);
      }

      greatherThan = greatherThan.next;
    }

    Iterator<Integer> itr = list.iterator();

    while (lessThan != null && itr.hasNext()) {
      lessThan.val = itr.next();
      lessThan = lessThan.next;
    }

    return A;
  }

  public ListNode sortBinaryLinkedList(ListNode A) {

    ListNode temp = A;

    int countZero = 0;
    int countOne = 0;

    while (temp != null) {

      if (temp.val == 0) countZero++;
      else countOne++;

      temp = temp.next;
    }

    ListNode temp1 = A;

    int i = 0;

    while (temp1 != null) {

      while (temp1 != null && i < countZero) {
        temp1.val = 0;
        temp1 = temp1.next;
        i++;
      }

      i = 0;

      while (temp1 != null && i <= countOne) {
        temp1.val = 1;
        temp1 = temp1.next;
        i++;
      }
    }

    return A;
  }

  boolean areIdentical(ListNode listA, ListNode listB) {
    ListNode a = listA, b = listB;
    while (a != null && b != null) {
      if (a.val != b.val) return false;

      a = a.next;
      b = b.next;
    }

    return (a == null && b == null);
  }
}
