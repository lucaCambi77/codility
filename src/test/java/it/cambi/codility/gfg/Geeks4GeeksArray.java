/**
 * 
 */
package it.cambi.codility.gfg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geeks4GeeksArray {

	@Test
	public void permutationsOfAGivenString() {

		String s = "ABC";
		TreeSet<String> set = permuteString(s, 0, s.length() - 1, new TreeSet<String>());
		StringBuilder sb = new StringBuilder();

		set.stream().forEach(x -> {
			sb.append(x).append(" ");
		});

		assertEquals("ABC ACB BAC BCA CAB CBA", sb.toString().substring(0, sb.toString().length() - 1));

	}

	private static TreeSet<String> permuteString(String str, int l, int r, TreeSet<String> set) {

		if (l == r) {
			set.add(str);
		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permuteString(str, l + 1, r, set);
				str = swap(str, l, i);
			}
		}

		return set;
	}

	private static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	@Test
	public void reverseWordsInAGivenString() {

		String s = "i.like.this.program.very.much";

		String[] split = s.split("\\.");

		StringBuilder sb = new StringBuilder();

		for (int i = split.length - 1; i >= 0; i--) {
			sb.append(split[i]).append(".");
		}

		System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
	}

	@Test
	public void reverseaStringUsingStack() {

		String str = new String("GeeksQuiz");
		Stack<Character> stack = new Stack<Character>();

		for (char c : str.toCharArray()) {
			stack.push(c);
		}

		StringBuilder strBuild = new StringBuilder();

		int stackSize = stack.size();

		for (int i = 0; i < stackSize; i++) {
			strBuild.append(stack.pop());
		}

		assertTrue(strBuild.toString().equals("ziuQskeeG"));
	}

	@Test
	public void reverseFirstKelementsofQueue() {

		int k = 3;

		Queue<Integer> q = new LinkedList<Integer>();

		q.add(3);
		q.add(1);
		q.add(2);
		q.add(4);
		q.add(5);

		int size = q.size();
		int[] toReverseArray = new int[size];

		Queue<Integer> queueOut = new LinkedList<Integer>();

		for (int i = 0; i < size; i++) {

			if (i > k - 1) {
				toReverseArray[i] = q.poll();
				continue;
			}

			toReverseArray[k - i - 1] = q.poll();
		}

		for (int i = 0; i < size; i++) {
			queueOut.add(toReverseArray[i]);
		}

		System.out.println("Size of queue " + q.size());
	}

	@Test
	public void stackOperations() {

		Stack<Integer> stack = new Stack<Integer>();

		stack.push(2);
		stack.push(4);
		stack.push(3);
		stack.push(5);

		assertTrue(stack.peek() == 5);

		assertFalse(stack.contains(8));
	}

	@Test
	public void operationsonPriorityQueue() {

		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(2);
		queue.add(3);
		queue.add(1);

		queue.contains(5);

		assertTrue(queue.poll() == 1);
	}

	@Test
	public void queueOperations() {

		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(2);
		queue.add(3);
		queue.add(1);

		assertTrue(Collections.frequency(queue, 1) == 2);

	}

	@Test
	public void arrayListOperation() {

		List<Character> clist = new ArrayList<Character>();

		clist.add('g');
		clist.add('e');
		clist.add('e');
		clist.add('k');
		clist.add('s');
		clist.add('c');
		clist.add('p');
		clist.add('p');

		if (clist.contains('f'))
			System.out.println(Collections.frequency(clist, 'f'));
		else
			System.out.println("Not Present");
	}

	@Test
	public void operationsOnArrayList() {

		List<Integer> list = new LinkedList<Integer>();

		list.add(2);
		list.add(2);
		list.add(3);
		list.add(4);

		Collections.sort(list);

		int index = findFirstOccurrence(list.stream().mapToInt(i -> i).toArray(), 0, list.size() - 1, 4);

		assertTrue(index == 3);
	}

	/**
	 * We search for middle value and move to the left or right until we find the
	 * value. If there are multiple occurrence for the key we move until middle
	 * value is the end of our search
	 * 
	 * @param a
	 * @param start
	 * @param end
	 * @param key
	 * @return
	 */
	public static int findFirstOccurrence(int[] a, int start, int end, int key) {

		while (start < end) {
			int mid = start + (end - start) / 2;

			if (a[mid] >= key) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return (a[start] == key) ? start : -1;
	}

	@Test
	public void kthMissingElement() {
		int arr[] = new int[] { 2, 4, 10, 7 };
		int k = 5;

		Arrays.sort(arr);

		Set<Integer> set = new LinkedHashSet<Integer>();

		for (int i : arr) {
			set.add(i);
		}

		Iterator<Integer> iterator = set.iterator();
		Integer start = iterator.next();
		int count = 0;
		int diff = 0;
		int thElement = 0;

		while (iterator.hasNext()) {
			int next = iterator.next();

			diff = next - start;

			if (diff == 1) {
				start = next;
				continue;
			}

			count += diff - 1;

			if (count >= k) {
				thElement = start + diff - 1;
				break;
			}

			start = next;

		}

		System.out.println(thElement);

	}

}
