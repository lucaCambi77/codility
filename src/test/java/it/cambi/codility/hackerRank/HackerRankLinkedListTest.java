/**
 *
 */
package it.cambi.codility.hackerRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
 *
 */
@ExtendWith(MockitoExtension.class)
public class HackerRankLinkedListTest {
    private PrintStream out;

    @BeforeEach
    public void setUpStreams() {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

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
        DoublyLinkedListNode node3 = new DoublyLinkedListNode(10);

        node2.next = node3;
        node1.next = node2;
        node.next = node1;

        sortedInsert(node, 5);

        DoublyLinkedListNode anode = new DoublyLinkedListNode(2);
        DoublyLinkedListNode anode1 = new DoublyLinkedListNode(3);
        DoublyLinkedListNode anode2 = new DoublyLinkedListNode(4);

        anode1.next = anode2;
        anode.next = anode1;

        sortedInsert(anode, 1);

    }

    private DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {

        DoublyLinkedListNode pointer = head;

        while (pointer != null) {


            if (data >= pointer.data && ((null != pointer.next && data <= pointer.next.data) || (null == pointer.next))) {

                DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
                newNode.next = pointer.next;

                pointer.next = newNode;
                break;
            } else if (pointer.prev == null && data <= pointer.data) {
                DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
                newNode.next = pointer;

                head = newNode;
                break;
            }

            pointer = pointer.next;

        }

        return head;
    }

    @Test
    public void queueUsingTwoStacks() {
        queueUsingTwoStacks(10, new String[]{"1 42", "2", "1 14", "3", "1 28", "3", "1 60", "1 78", "2", "2"});
        verify(out, times(2)).println(new Integer(14));
    }

    public void queueUsingTwoStacks(int t, String[] lines) {

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        int count = 0;

        while (count < t) {

            String[] split = lines[count].split(" ");
            int query = Integer.valueOf(split[0]);
            switch (query) {
                case 1:
                    stack1.push(Integer.valueOf(split[1]));
                    stack2.add(0, Integer.valueOf(split[1]));
                    break;

                case 2:
                    stack1.remove(0);
                    stack2.pop();

                    break;

                case 3:

                    System.out.println(stack2.peek());
                    break;
            }
            count++;
        }

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
    public void hasCycle() throws IOException {

        InputStream is = new FileInputStream("src/test/resources/listWithCycle.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);

        int pos = 0;

        while (line != null) {
            insertAtPos(pos, new Integer(line), head);

            line = buf.readLine();
            pos++;
        }

        buf.close();

        boolean hasCycle = false;

        /*
         * if (head == null){ hasCycle = false; }
         */

        SinglyLinkedListNode slow = head;
        SinglyLinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
            }
        }

        assertEquals(false, hasCycle);
    }

    @Test
    public void removeDuplicates() throws IOException {

        InputStream is = new FileInputStream("src/test/resources/listWithDuplicate.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = buf.readLine();
        SinglyLinkedListNode head2 = new SinglyLinkedListNode(1);

        int pos = 0;

        while (line != null) {
            insertAtPos(pos, new Integer(line), head2);

            line = buf.readLine();
            pos++;
        }

        buf.close();

        // Solution
        Set<Integer> seen = new HashSet<Integer>();

        seen.add(head2.data);

        SinglyLinkedListNode head = head2;

        int position = 0;
        while (head.next != null) {
            if (seen.contains(head.next.data)) {
                head2 = deleteNodeAtPosition(position, head2);
                // decrease position to match the actual linked list
                position--;
            }

            if (head.next == null)
                break;

            seen.add(head.next.data);

            position++;
            head = head.next;
        }

        // check no duplicates
        seen = new HashSet<Integer>();
        boolean check = true;
        while (head2 != null) {
            check = seen.add(head2.data);
            assertEquals(check, true);
            head2 = head2.next;
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

        boolean areEquals = true;
        while (head2 != null && head1 != null) {
            if (head2.data != head1.data) {
                areEquals = false;
                break;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        assertEquals(false, areEquals);
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

    private SinglyLinkedListNode reversePrint(AtomicInteger pos, SinglyLinkedListNode head, SinglyLinkedListNode out) {

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

        SinglyLinkedListNode nextAfterInsert = temp.next;

        nextAtInsert.next = nextAfterInsert;

        temp.next = nextAtInsert;

        return head;
    }

    @Test
    public void deleteNodeAtPos() {

        int position = 1;
        SinglyLinkedListNode head = getLinkedListExample1();

        deleteNodeAtPosition(position, head);

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
