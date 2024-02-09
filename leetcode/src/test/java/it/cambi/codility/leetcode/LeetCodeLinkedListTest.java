/**
 *
 */
package it.cambi.codility.leetcode;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author luca
 */
class LeetCodeLinkedListTest {

    static class ListNode {

        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    @Test
    public void deleteNodes() {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(11);
        head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(12);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(13);

        ListNode sol = new ListNode(1);
        sol.next = new ListNode(2);
        sol.next.next = new ListNode(6);
        sol.next.next.next = new ListNode(7);
        sol.next.next.next.next = new ListNode(11);
        sol.next.next.next.next.next = new ListNode(12);

        assertTrue(areIdentical(sol, deleteNodes(head, 2, 3)));

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next.next = new ListNode(8);
        head1.next.next.next.next.next.next.next.next = new ListNode(9);
        head1.next.next.next.next.next.next.next.next.next = new ListNode(10);
        head1.next.next.next.next.next.next.next.next.next.next = new ListNode(11);

        ListNode sol1 = new ListNode(1);
        sol1.next = new ListNode(5);
        sol1.next.next = new ListNode(9);

        assertTrue(areIdentical(sol1, deleteNodes(head1, 1, 3)));

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next.next = new ListNode(7);
        head2.next.next.next.next.next.next.next = new ListNode(8);
        head2.next.next.next.next.next.next.next.next = new ListNode(9);
        head2.next.next.next.next.next.next.next.next.next = new ListNode(10);
        head2.next.next.next.next.next.next.next.next.next.next = new ListNode(11);

        ListNode sol2 = new ListNode(1);
        sol2.next = new ListNode(2);
        sol2.next.next = new ListNode(3);
        sol2.next.next.next = new ListNode(5);
        sol2.next.next.next.next = new ListNode(6);
        sol2.next.next.next.next.next = new ListNode(7);
        sol2.next.next.next.next.next.next = new ListNode(9);
        sol2.next.next.next.next.next.next.next = new ListNode(10);
        sol2.next.next.next.next.next.next.next.next = new ListNode(11);

        assertTrue(areIdentical(sol2, deleteNodes(head2, 3, 1)));

        ListNode head3 = new ListNode(9);
        head3.next = new ListNode(3);
        head3.next.next = new ListNode(7);
        head3.next.next.next = new ListNode(7);
        head3.next.next.next.next = new ListNode(9);
        head3.next.next.next.next.next = new ListNode(10);
        head3.next.next.next.next.next.next = new ListNode(8);
        head3.next.next.next.next.next.next.next = new ListNode(2);

        ListNode sol3 = new ListNode(9);
        sol3.next = new ListNode(7);
        sol3.next.next = new ListNode(8);

        assertTrue(areIdentical(sol3, deleteNodes(head3, 1, 2)));
    }

    private ListNode deleteNodes(ListNode head, int m, int n) {
        int i = 0;
        int k = 0;

        ListNode list = head;

        while (list != null) {

            while (i++ < m - 1 && list != null)
                list = list.next;

            if (list == null)
                break;

            ListNode list1 = list;

            while (k++ < n && list1.next != null)
                list1 = list1.next;

            list.next = list1.next;

            list = list.next;

            i = 0;
            k = 0;
        }

        return head;
    }

    @Test
    public void getDecimalValue() {

        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);

        assertEquals(5, getDecimalValue(head));

        head = new ListNode(1);

        int[] arr = new int[] { 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 };

        ListNode tmp = head;
        for (int i : arr) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        assertEquals(18880, getDecimalValueBitWise(head));
    }

    public int getDecimalValue(ListNode head) {

        StringBuilder builder = new StringBuilder();

        while (head != null) {
            builder.append(head.val);
            head = head.next;
        }

        return Integer.parseInt(builder.toString(), 2);
    }

    // Shift and adds next bit. So multiply by 2 and adds previous sum when one
    public int getDecimalValueBitWise(ListNode head) {
        int num = head.val;
        while ((head = head.next) != null)
            num = (num << 1) + head.val;
        return num;
    }

    @Test
    public void middleNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        assertEquals(3, middleNode(head).val);

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        assertEquals(4, middleNode(head).val);

        head = new ListNode(1);
        assertEquals(1, middleNode(head).val);
    }

    public ListNode middleNode(ListNode head) {
        int i = 0;

        ListNode copy = head;

        while (copy != null) {
            i++;
            copy = copy.next;
        }

        copy = head;

        int tmp = 0;
        while (tmp++ < i / 2) {
            copy = copy.next;
        }

        return copy;
    }

    @Test
    public void swapPairs() {
        ListNode head = new ListNode(4);
        head.next = new ListNode(1);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listCompare = new ListNode(1);
        listCompare.next = new ListNode(4);
        listCompare.next.next = new ListNode(4);
        listCompare.next.next.next = new ListNode(8);
        listCompare.next.next.next.next = new ListNode(5);

        assertTrue(areIdentical(listCompare, swapPairs(head)));
    }

    private ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode tmp = head;
        ListNode node = head.next;
        tmp.next = head.next.next;
        node.next = tmp;

        node.next.next = swapPairs(tmp.next);

        return node;
    }

    @Test
    public void getIntersectionNode() {

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = new ListNode(8);
        headB.next.next.next.next = new ListNode(4);
        headB.next.next.next.next.next = new ListNode(5);

        ListNode node = new ListNode(8);
        node.next = new ListNode(4);
        node.next.next = new ListNode(5);

        assertTrue(areIdentical(node, getIntersectionNode(headA, headB)));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return null;
    }

    @Test
    public void deleteDuplicates() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(1);
        l.next.next = new ListNode(2);

        assertTrue(areIdentical(new ListNode(2), deleteDuplicates(l)));

        l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(3);
        l.next.next.next.next = new ListNode(4);
        l.next.next.next.next.next = new ListNode(4);
        l.next.next.next.next.next.next = new ListNode(5);

        ListNode sol = new ListNode(1);
        sol.next = new ListNode(2);
        sol.next.next = new ListNode(5);

        assertTrue(areIdentical(sol, deleteDuplicates(l)));

    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {

                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }

                if (prev != null) { 
                    prev.next = curr.next; // change the reference of the original head node
                    curr = curr.next;
                } else { 
                    curr = curr.next;
                    head = curr; // create a new head if the begininning of original head has duplicate
                }

            } else { 
                prev = curr; // keep reference to head node
                curr = curr.next;
            }
        }
        return head;
    }

    @Test
    public void mergeTwoLists() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode result = new ListNode(1);
        result.next = new ListNode(1);
        result.next.next = new ListNode(2);
        result.next.next.next = new ListNode(3);
        result.next.next.next.next = new ListNode(4);
        result.next.next.next.next.next = new ListNode(4);

        assertEquals(true, areIdentical(mergeTwoLists(l1, l2), result));
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        List<Integer> list = new ArrayList<Integer>();

        while (null != l1) {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (null != l2) {
            list.add(l2.val);
            l2 = l2.next;
        }

        if (list.size() == 0)
            return null;

        Collections.sort(list);

        ListNode node = new ListNode(list.get(0));
        ListNode head = node;

        for (int i = 1; i < list.size(); i++) {
            head.next = new ListNode(list.get(i));
            head = head.next;
        }

        return node;
    }

    @Test
    public void isPalindrome() {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(2);

        assertTrue(isPalindrome(l1));

        ListNode l2 = new ListNode(-129);
        l2.next = new ListNode(-129);

        assertTrue(isPalindrome(l2));
    }

    public boolean isPalindrome(ListNode head) {
        Deque<Integer> queue = new LinkedList<Integer>();

        while (head != null) {
            queue.push(head.val);
            head = head.next;
        }

        int size = queue.size() / 2;

        for (int i = 0; i < size; i++) {
            int first = queue.poll();
            int last = queue.pollLast();

            if (first != last)
                return false;
        }

        return true;
    }

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(7);
        l3.next = new ListNode(0);
        l3.next.next = new ListNode(8);

        assertTrue(areIdentical(l3, addTwoNumbers(l1, l2)));

        ListNode l4 = new ListNode(9);

        int[] array = new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9 };

        ListNode l5 = new ListNode(Integer.valueOf(array[0]));
        ListNode tmp = l5;
        for (int i = 1; i < array.length; i++) {
            tmp.next = new ListNode(array[i]);
            tmp = tmp.next;
        }

        array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };

        ListNode l6 = new ListNode(Integer.valueOf(array[0]));
        tmp = l6;
        for (int i = 1; i < array.length; i++) {
            tmp.next = new ListNode(array[i]);
            tmp = tmp.next;
        }

        assertTrue(areIdentical(l6, addTwoNumbers(l4, l5)));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder builder = new StringBuilder();

        while (l1 != null) {
            builder.append(l1.val);
            l1 = l1.next;
        }

        String s1 = builder.reverse().toString();

        builder.setLength(0);

        while (l2 != null) {
            builder.append(l2.val);
            l2 = l2.next;
        }

        String s2 = builder.reverse().toString();

        BigInteger sum = new BigInteger(s1).add(new BigInteger(s2));
        String sumToString = sum.toString();

        int length = sumToString.length() - 1;
        ListNode head = new ListNode(Integer.parseInt(Character.toString(sumToString.charAt(length))));
        ListNode tmp = head;

        for (int i = length - 1; i >= 0; i--) {
            tmp.next = new ListNode(Integer.parseInt(Character.toString(sumToString.charAt(i))));
            tmp = tmp.next;
        }

        return head;
    }

    boolean areIdentical(ListNode lista, ListNode listb) {
        if (lista == null && listb == null)
            return true;

        ListNode a = lista, b = listb;
        while (a != null && b != null) {
            if (a.val != b.val)
                return false;

            a = a.next;
            b = b.next;
        }

        return (a == null && b == null);
    }

    @Test
    public void removeNthFromEnd() {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        l2.next.next.next = new ListNode(5);

        assertTrue(areIdentical(l2, removeNthFromEnd(l1, 2)));

        l1 = new ListNode(1);

        assertTrue(areIdentical(null, removeNthFromEnd(l1, 1)));

        l1 = new ListNode(1);
        l1.next = new ListNode(2);

        assertTrue(areIdentical(new ListNode(1), removeNthFromEnd(l1, 1)));

        l1 = new ListNode(1);
        l1.next = new ListNode(2);

        assertTrue(areIdentical(new ListNode(2), removeNthFromEnd(l1, 2)));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode copy = head;

        int count = 0;

        while (copy != null) {
            copy = copy.next;
            count++;
        }

        int tmp = count - n - 1;

        if (tmp < 0)
            return head.next;

        copy = head;

        while (tmp-- > 0) {
            copy = copy.next;
        }

        if (copy.next == null)
            return null;

        copy.next = copy.next.next;

        return head;
    }

}
