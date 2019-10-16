/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Deque;
import java.util.LinkedList;

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
