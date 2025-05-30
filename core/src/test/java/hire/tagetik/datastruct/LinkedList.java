package hire.tagetik.datastruct;

public class LinkedList {

  static class Node {
    int val;
    Node next;
    Node prev;

    Node(int val) {
      this.val = val;
      this.next = null;
      this.prev = null;
    }

    Node(int val, Node next, Node prev) {
      this.val = val;
      this.next = next;
      this.prev = prev;
    }
  }

  Node head;
  Node last;
  int size;

  LinkedList(Node head) {
    this.head = head;
  }

  LinkedList() {}

  void add(int val) {

    Node newNode = new Node(val, null, head);

    if (head == null) {
      this.head = newNode;
    } else {
      head.prev = newNode;
    }

    this.last = newNode;
    this.size++;
  }

  void addFirst(int val) {
    Node newNode = new Node(val, head, null);
    if (head != null) {
      this.head.prev = newNode;
    }

    this.head = newNode;
    this.size++;
  }

  int size() {
    return size;
  }

  int peek() {
    return head.val;
  }

  int last() {
    return last.val;
  }
}
