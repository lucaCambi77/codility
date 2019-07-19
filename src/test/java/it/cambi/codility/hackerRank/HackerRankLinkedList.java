/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class HackerRankLinkedList {

	class SinglyLinkedListNode {
		int data;
		SinglyLinkedListNode next;

		/**
		 * 
		 */
		public SinglyLinkedListNode(int data) {
			this.data = data;
		}
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

			if (positionFromTail == i)
				result = data;
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

			if (head == null)
				head2 = insertAtPos(pos, head1.data, head2);

			head1 = head1.next;
		}

	}

	@Test
	public void compareLists() {
		SinglyLinkedListNode head2 = getLinkedListExample1();
		SinglyLinkedListNode head1 = getLinkedListExample2();

		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stack1 = new Stack<Integer>();

		while (head2 != null) {
			stack.add(head2.data);
			head2 = head2.next;
		}

		while (head1 != null) {
			stack1.add(head1.data);
			head1 = head1.next;
		}

		if (stack.size() != stack1.size())
			return;

		boolean areEquals = true;
		int stackSize = stack.size();
		for (int i = 0; i < stackSize; i++) {
			if (stack.pop() != stack1.pop()) {
				areEquals = false;
				break;
			}
		}

		assertEquals(false, areEquals);
	}

	@Test
	public void reversePrint() {

		SinglyLinkedListNode head = getLinkedListExample1();
		Stack<Integer> stack = new Stack<Integer>();

		while (head != null) {
			stack.add(head.data);
			head = head.next;
		}
		SinglyLinkedListNode out = null;
		int stackSize = stack.size();
		for (int i = 0; i < stackSize; i++) {
			out = insertAtPos(i, stack.pop(), out);
		}

	}

	public SinglyLinkedListNode insertAtPos(int position, int data, SinglyLinkedListNode head) {
		if (head == null) {
			return new SinglyLinkedListNode(data);
		}
		SinglyLinkedListNode nextAtInsert = new SinglyLinkedListNode(data);

		if (position == 0) {
			nextAtInsert.next = head;
			return nextAtInsert;
		}

		int pos = 0;

		SinglyLinkedListNode temp = head;

		while (pos != position - 1) {
			temp = temp.next;
			pos++;
		}

		SinglyLinkedListNode nextAfterInsert = temp.next;

		nextAtInsert.next = nextAfterInsert;

		temp.next = nextAtInsert;

		return head;
	}

	@Test
	public void deleteNodeAtPos() {

		int position = 1;
		SinglyLinkedListNode head = getLinkedListExample1();

		if (position == 0) {
			head = head.next;
			assertEquals(13, head.data);

			return;
		}

		int pos = 0;
		SinglyLinkedListNode temp = head;
		while (pos != position - 1) {
			temp = temp.next;
			pos++;
		}

		temp.next = temp.next.next;

		assertEquals(17, temp.next.data);

	}

	@Test
	public void insertNodeAtPosition() {

		int data = 10;
		int position = 1;
		SinglyLinkedListNode head = getLinkedListExample1();

		int pos = 0;

		SinglyLinkedListNode nextAtInsert = new SinglyLinkedListNode(data);

		if (position == 0) {
			nextAtInsert.next = head;
			return;
		}

		SinglyLinkedListNode temp = head;

		while (pos != position - 1) {
			temp = temp.next;
			pos++;
		}

		SinglyLinkedListNode nextAfterInsert = temp.next;

		nextAtInsert.next = nextAfterInsert;

		temp.next = nextAtInsert;

		assertEquals(13, temp.next.next.data);

	}

	@Test
	public void insertNodeAtHead() {

		SinglyLinkedListNode sll = getLinkedListExample1();

		SinglyLinkedListNode slln = new SinglyLinkedListNode(10);

		slln.next = sll;

		assertEquals(10, slln.data);
	}

	@Test
	public void insertNodeAtTail() {

		SinglyLinkedListNode sll = getLinkedListExample1();

		SinglyLinkedListNode slln = new SinglyLinkedListNode(10);

		SinglyLinkedListNode last = sll;

		while (last.next != null) {
			last = last.next;
		}

		last.next = slln;

		assertEquals(slln, last.next);

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

		SinglyLinkedListNode slln = new SinglyLinkedListNode(13);

		sll.next = slln;

		SinglyLinkedListNode sllnn = new SinglyLinkedListNode(17);

		slln.next = sllnn;

		return sll;
	}

	private SinglyLinkedListNode getLinkedListExample2() {
		SinglyLinkedListNode sll = new SinglyLinkedListNode(15);

		SinglyLinkedListNode slln = new SinglyLinkedListNode(18);

		sll.next = slln;

		SinglyLinkedListNode sllnn = new SinglyLinkedListNode(12);

		slln.next = sllnn;

		return sll;
	}

	private SinglyLinkedListNode getSortedLinkedListExample1() {
		SinglyLinkedListNode sll = new SinglyLinkedListNode(1);

		SinglyLinkedListNode slln = new SinglyLinkedListNode(2);

		sll.next = slln;

		SinglyLinkedListNode sllnn = new SinglyLinkedListNode(3);

		slln.next = sllnn;

		return sll;
	}

	private SinglyLinkedListNode getSortedLinkedListExample2() {
		SinglyLinkedListNode sll = new SinglyLinkedListNode(3);

		SinglyLinkedListNode slln = new SinglyLinkedListNode(4);

		sll.next = slln;

		return sll;
	}
}
