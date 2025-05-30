package it.cambi.codility.model;

/** @author luca */
// Java program to implement singly linked list
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
  public void insertAtStart(int data) {
    // create a node
    Node newNode = new Node(data);

    newNode.next = head;
    head = newNode;
  }

  // function to insert node
  // at the end of the list
  public void insertAtLast(int data) {

    Node newNode = new Node(data);
    if (head == null) {
      head = newNode;
      return;
    }

    newNode.next = null;

    Node last = head;
    while (last.next != null) {
      last = last.next;
    }

    last.next = newNode;
  }

  // function to delete a node
  // at the beginning of the list
  public void deleteAtStart() {
    if (head == null) {
      System.out.println("List is empty");
      return;
    }
    head = head.next;
  }

  // function to delete a node at
  // a given position in the list
  public void deleteAtPos(int pos) throws Exception {
    if (pos > count() || pos < 0) throw new Exception("Incorrect position exception");

    Node temp = head;

    if (pos == 0) {
      head = head.next;
      return;
    }

    int position = 0;

    while (position != pos - 1) {
      temp = temp.next;
      position++;
    }

    temp.next = temp.next.next;
  }

  public void insertAtPos(int position, int data) throws Exception {
    int pos = 0;

    if (position > count() || position < 0) {
      throw new Exception("Incorrect position exception");
    }

    Node nextAtInsert = new Node(data);
    Node temp = head;

    if (position == 0) {
      nextAtInsert.next = head;
      return;
    }

    while (pos != position - 1) {
      temp = temp.next;
      pos++;
    }

      nextAtInsert.next = temp.next;

    temp.next = nextAtInsert;
  }

  // function to delete a node
  // from the end of the list
  void deleteAtLast() {
    Node delete = head;
    while (delete.next != null && delete.next.next != null) {
      delete = delete.next;
    }
    delete.next = null;
  }

  // function to display all the nodes of the list
  void display() {
    Node disp = head;
    while (disp != null) {
      System.out.print(disp.data + "->");
      disp = disp.next;
    }
  }

  // function to return the total nodes in the list
  int count() {
    int elements = 0;
    Node count = head;
    while (count != null) {
      count = count.next;
      elements++;
    }
    return elements;
  }

  boolean areIdentical(SinglyLinkedList listb) {
    Node a = this.head, b = listb.head;
    while (a != null && b != null) {
      if (a.data != b.data) return false;

      a = a.next;
      b = b.next;
    }

    return (a == null && b == null);
  }
}
