/**
 * 
 */
package it.cambi.codility.test.model;

/**
 * @author luca
 *
 */
//Java program to implement singly linked list 
public class SinglyLinkedList {

	class Node {

		// node variables
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	// create reference variable of Node
	Node head;

	// function to insert a node
	// at the begining of the list
	void InsertAtStart(int data) {
		// create a node
		Node new_node = new Node(data);

		new_node.next = head;
		head = new_node;
	}

	// function to insert node
	// at the end of the list
	void InsertAtLast(int data) {

		Node new_node = new Node(data);
		if (head == null) {
			head = new_node;
			return;
		}

		new_node.next = null;

		Node last = head;
		while (last.next != null) {
			last = last.next;
		}

		last.next = new_node;
	}

	// function to delete a node
	// at the beginning of the list
	void DeleteAtStart() {
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		head = head.next;
	}

	// function to delete a node at
	// a given position in the list
	void DeleteAtPos(int pos) throws Exception {
		int position = 0;
		if (pos > Count() || pos < 0) {
			throw new Exception("Incorrect position exception");
		}
		Node temp = head;
		while (position != pos - 1) {
			temp = temp.next;
			position++;
		}
		temp.next = temp.next.next;
	}

	// function to delete a node
	// from the end of the list
	void DeleteAtLast() {
		Node delete = head;
		while (delete.next != null && delete.next.next != null) {
			delete = delete.next;
		}
		delete.next = null;
	}

	// function to display all the nodes of the list
	void Display() {
		Node disp = head;
		while (disp != null) {
			System.out.print(disp.data + "->");
			disp = disp.next;
		}
	}

	// function to return the total nodes in the list
	int Count() {
		int elements = 0;
		Node count = head;
		while (count != null) {
			count = count.next;
			elements++;
		}
		return elements;
	}
}
