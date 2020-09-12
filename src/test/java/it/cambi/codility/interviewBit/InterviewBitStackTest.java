package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitStackTest {

    class Pair {
        int position;
        int value;

        public Pair(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }

    @Test
    public void maxSpecialProduct() {
        assertEquals(0, maxSpecialProduct(new int[]{10, 7}));
        assertEquals(0, maxSpecialProduct(new int[]{10, 7, 100}));
        assertEquals(3, maxSpecialProduct(new int[]{1, 4, 3, 4}));
        assertEquals(8, maxSpecialProduct(new int[]{1, 4, 4, 3, 4}));
        assertEquals(3, maxSpecialProduct(new int[]{5, 4, 3, 6}));
        assertEquals(0, maxSpecialProduct(new int[]{1, 2, 3, 4}));
    }

    public int maxSpecialProduct(int[] A) {

        int n = A.length;

        //Nearest Greater To Left
        int[] nll = new int[n];

        //Nearest Greater To Right
        int[] nlr = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && A[st.peek()] < A[i])
                nll[st.pop()] = i;

            st.push(i);
        }

        while (st.isEmpty())
            nll[st.pop()] = -1;


        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && A[st.peek()] < A[i])
                nlr[st.pop()] = i;

            st.push(i);
        }

        while (st.isEmpty())
            nlr[st.pop()] = n;

        long ans = -1;
        int mod = (int) (1e9 + 7);

        for (int i = 0; i < n; i++)
            ans = Math.max(ans, (long) nll[i] * nlr[i]);

        return (int) (ans % mod);
    }

    @Test
    public void balancedParenthesis() {
        assertEquals(1, balancedParenthesis("(()())"));
        assertEquals(0, balancedParenthesis("(()"));
    }

    private int balancedParenthesis(String A) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < A.length(); i++) {

            if (!stack.isEmpty() && stack.peek() == '(' && A.charAt(i) == ')') {
                stack.pop();
                continue;
            }

            stack.push(A.charAt(i));
        }

        return stack.isEmpty() ? 1 : 0;
    }

    @Test
    public void firstNonRepeatingChar() {
        assertEquals("aabbdd", firstNonRepeatingChar("abadbc"));
        assertEquals("a#", firstNonRepeatingChar("aa"));
        assertEquals("a#b", firstNonRepeatingChar("aab"));
        assertEquals("aaabc#", firstNonRepeatingChar("abcabc"));
        assertEquals("ppt", firstNonRepeatingChar("ptp"));
        assertEquals("x#iiiiiiiiiiiiiiiiwwwwwwwwwwwwwwwwwwwwww", firstNonRepeatingChar("xxikrwmjvsvckfrqxnibkcasompsuyuogauacjrr"));
        assertEquals("jjjjjjjjjjjjjjjjjjjjjyyyyyyyyyyyyyyyyyyyyyyyyyyyqqqqq", firstNonRepeatingChar("jyhrcwuengcbnuchctluxjgtxqtfvrebveewgasluuwooupcyxwgl"));
    }

    private String firstNonRepeatingChar(String A) {

        Queue<Character> queue = new LinkedList<>();

        String alphaBet = "abcdefghijklmnopqrstuvwxyz";
        int[] chars = new int[26];

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < A.length(); i++) {
            ++chars[alphaBet.indexOf(A.charAt(i))];
            queue.add(A.charAt(i));

            while (!queue.isEmpty() && chars[alphaBet.indexOf(queue.peek())] > 1)
                queue.poll();

            s.append(queue.isEmpty() ? '#' : queue.peek());
        }

        return s.toString();
    }

}
