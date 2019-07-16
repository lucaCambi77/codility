/**
 * 
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	public void deleteNodeAtPos() {

		int position = 0;
		SinglyLinkedListNode head = getLinkedListExample();

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
		SinglyLinkedListNode head = getLinkedListExample();

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

		SinglyLinkedListNode sll = getLinkedListExample();

		SinglyLinkedListNode slln = new SinglyLinkedListNode(10);

		slln.next = sll;

		assertEquals(10, slln.data);
	}

	@Test
	public void insertNodeAtTail() {

		SinglyLinkedListNode sll = getLinkedListExample();

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
		SinglyLinkedListNode sll = getLinkedListExample();

		while (sll != null) {
			System.out.println(sll.data);
			sll = sll.next;
		}
	}

	/**
	 * @return
	 */
	private SinglyLinkedListNode getLinkedListExample() {
		SinglyLinkedListNode sll = new SinglyLinkedListNode(16);

		SinglyLinkedListNode slln = new SinglyLinkedListNode(13);

		sll.next = slln;

		SinglyLinkedListNode sllnn = new SinglyLinkedListNode(17);

		slln.next = sllnn;

		return sll;
	}
}
