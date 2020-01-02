/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class LeetCodeLinkedListTest
{

    class ListNode
    {

        int val;
        ListNode next;

        public ListNode(int val)
        {
            this.val = val;
            this.next = null;
        }
    }

    @Test
    public void swapPairs()
    {
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

    private ListNode swapPairs(ListNode head)
    {
        if (head == null || (head != null && head.next == null))
            return head;

        ListNode tmp = head;
        ListNode node = head.next;
        tmp.next = head.next.next;
        node.next = tmp;

        node.next.next = swapPairs(tmp.next);

        return node;
    }

    @Test
    public void getIntersectionNode()
    {

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

        assertEquals(true, areIdentical(node, getIntersectionNode(headA, headB)));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        return null;
    }

    @Test
    public void deleteDuplicates()
    {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);

        assertEquals(true, areIdentical(l2,
                deleteDuplicates(l1)));
    }

    public ListNode deleteDuplicates(ListNode head)
    {
        if (head == null)
            return head;

        Set<Integer> values = new HashSet<Integer>();

        ListNode copy = head;
        ListNode prev = head;

        while (copy != null)
        {

            if (!values.add(copy.val))
            {
                ListNode tmp = copy;
                copy = prev;
                copy.next = tmp.next;
                tmp = null;
                prev = copy;
                copy = copy.next;
                continue;
            }

            prev = copy;
            copy = copy.next;
        }

        return head;
    }

    @Test
    public void mergeTwoLists()
    {
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

    private ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {

        List<Integer> list = new ArrayList<Integer>();

        while (null != l1)
        {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (null != l2)
        {
            list.add(l2.val);
            l2 = l2.next;
        }

        if (list.size() == 0)
            return null;

        Collections.sort(list);

        ListNode node = new ListNode(list.get(0));
        ListNode head = node;

        for (int i = 1; i < list.size(); i++)
        {
            head.next = new ListNode(list.get(i));
            head = head.next;
        }

        return node;
    }

    @Test
    public void isPalindrome()
    {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(2);

        assertEquals(true, isPalindrome(l1));

        ListNode l2 = new ListNode(-129);
        l2.next = new ListNode(-129);

        assertEquals(true, isPalindrome(l2));
    }

    public boolean isPalindrome(ListNode head)
    {
        Deque<Integer> queue = new LinkedList<Integer>();

        while (head != null)
        {
            queue.push(head.val);
            head = head.next;
        }

        int size = queue.size() / 2;

        for (int i = 0; i < size; i++)
        {
            int first = queue.poll();
            int last = queue.pollLast();

            if (first != last)
                return false;
        }

        return true;

    }

    @Test
    public void addTwoNumbers()
    {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(7);
        l3.next = new ListNode(0);
        l3.next.next = new ListNode(8);

        assertEquals(true, areIdentical(l3, addTwoNumbers(l1, l2)));

        ListNode l4 = new ListNode(9);

        int[] array = new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9 };

        ListNode l5 = new ListNode(new Integer(array[0]));
        ListNode tmp = l5;
        for (int i = 1; i < array.length; i++)
        {
            tmp.next = new ListNode(array[i]);
            tmp = tmp.next;
        }

        array = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };

        ListNode l6 = new ListNode(new Integer(array[0]));
        tmp = l6;
        for (int i = 1; i < array.length; i++)
        {
            tmp.next = new ListNode(array[i]);
            tmp = tmp.next;
        }

        assertEquals(true, areIdentical(l6, addTwoNumbers(l4, l5)));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        StringBuilder builder = new StringBuilder();

        while (l1 != null)
        {
            builder.append(l1.val);
            l1 = l1.next;
        }

        String s1 = builder.reverse().toString();

        builder.setLength(0);

        while (l2 != null)
        {
            builder.append(l2.val);
            l2 = l2.next;
        }

        String s2 = builder.reverse().toString();

        BigInteger sum = new BigInteger(s1).add(new BigInteger(s2));
        String sumToString = sum.toString();

        int length = sumToString.length() - 1;
        ListNode head = new ListNode(new Integer(Character.toString(sumToString.charAt(length))));
        ListNode tmp = head;

        for (int i = length - 1; i >= 0; i--)
        {
            tmp.next = new ListNode(new Integer(Character.toString(sumToString.charAt(i))));
            tmp = tmp.next;
        }

        return head;

    }

    boolean areIdentical(ListNode lista, ListNode listb)
    {
        ListNode a = lista, b = listb;
        while (a != null && b != null)
        {
            if (a.val != b.val)
                return false;

            a = a.next;
            b = b.next;
        }

        return (a == null && b == null);
    }
}
