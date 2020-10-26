/**
 *
 */
package it.cambi.codility.gfg;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luca
 *
 */
public class Geeks4GeeksLinkedListTest {

    class Node {

        // node variables
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    @Test
    public void countNodes() {

        Node head = new Node(5);
        head.next = new Node(4);

        assertEquals(2, getCount(head));
    }

    private int getCount(Node head) {

        int count = 0;

        if (null == head)
            return count;

        while (head.next != null) {
            head = head.next;
            count++;
        }
        return count + 1;
    }

    @Test
    public void getNth() {

        Node head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(6);

        assertEquals(6, getNth(head, 3));
    }

    private static int getNth(Node node, int ind) {
        int count = 0;

        while (null != node.next) {
            if (++count == ind)
                return node.data;

            node = node.next;
        }

        return node.data;
    }

    public void getMiddle() {

        Node head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(6);

        assertEquals(4, getMiddle(head));
    }

    private int getMiddle(Node head) {
        LinkedList<Node> list = new LinkedList<Node>();

        while (null != head) {
            list.add(head);
            head = head.next;

        }

        int size = list.size();

        int middle = size / 2;

        return list.get(middle).data;
    }

    @Test
    public void reverseList() {
        Node head = new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(6);

        reverseList(head);
    }

    private Node reverseList(Node node) {
        Node prev = null;
        Node current = node;
        Node next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    @Test
    public void reverseQueue() {

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        Queue<Integer> queue1 = new LinkedList<Integer>();
        queue1.add(3);
        queue1.add(2);
        queue1.add(1);

        assertEquals(queue1, reverseQueue(queue));
    }

    private Queue<Integer> reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.add(queue.peek());
            queue.remove();
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        return queue;
    }

    private Stack<Integer> s1 = new Stack<Integer>();
    private Stack<Integer> s2 = new Stack<Integer>();

    @Test
    public void twoStackAsQueue() {

        insert(2);
        insert(3);
        int r1 = remove();
        insert(4);
        int r2 = remove();

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(2);
        stack.push(3);

        assertEquals(Arrays.toString(new int[]{2, 3}), Arrays.toString(new int[]{r1, r2}));
    }

    public void insert(int B) {
        s1.push(B);
    }

    public int remove() {
        prepOld();
        return s2.pop();
    }

    public int peek() {
        prepOld();
        return s2.peek();
    }

    private void prepOld() {
        if (s2.isEmpty())
            while (!s1.isEmpty())
                s2.push(s1.pop());
    }
}
