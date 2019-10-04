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
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class Geeks4GeeksArray {

	@Test
	public void checkTwoArraysEquals() {
		assertEquals(true, checkTwoArraysEquals(Arrays.asList(new Integer[] { 1, 2, 4, 5, 0 }),
				Arrays.asList(new Integer[] { 0, 5, 4, 2, 1 })));
	      assertEquals(false, checkTwoArraysEquals(Arrays.asList(new Integer[] { 1, 4, 5, 0 }),
	                Arrays.asList(new Integer[] { 0, 5, 4, 2, 1 })));
	}

	public boolean checkTwoArraysEquals(List<Integer> list, List<Integer> list1) {

		Collections.sort(list);
		Collections.sort(list1);

		return Arrays.equals(list.toArray(), list1.toArray());
	}

	@Test
	public void repeatChar() {
		assertEquals("b", repeatChar("bbccdefbbaa"));
		assertEquals("g", repeatChar("geeksforgeeks"));
		assertEquals("-1", repeatChar("card"));

	}

	public String repeatChar(String s) {
		Supplier<IntStream> stringChar = () -> s.chars();
		char[] chars = s.toCharArray();
		int length = chars.length;

		AtomicInteger count = new AtomicInteger(0);

		for (int i = 0; i < length; i++) {
			char c = chars[count.getAndIncrement()];
			Long charCount = stringChar.get().filter(ch -> ch == c).count();
			if (charCount > 1)
				return Character.toString(c);
		}

		return "-1";

	}

	@Test
	public void sort() {
		assertEquals("aabbbbccdef", sort("bbccdefbbaa"));
		assertEquals("eeeefggkkorss", sort("geeksforgeeks"));

	}

	public String sort(String s) {
		Supplier<IntStream> stringChar = () -> s.chars();
		char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		int length = alphaBet.length;

		AtomicInteger count = new AtomicInteger(0);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char c = alphaBet[count.getAndIncrement()];
			Long charCount = stringChar.get().filter(ch -> ch == c).count();
			builder.append(String.join("", Collections.nCopies(charCount.intValue(), Character.toString(c))));
		}

		return builder.toString();

	}

	@Test
	public void multiplyLeftRightSum() {

		assertEquals(21,
				multiplyLeftRightSum(Arrays.stream("1 2 3 4".split(" ")).mapToInt(i -> Integer.valueOf(i)).toArray()));
		assertEquals(44, multiplyLeftRightSum(new int[] { 4, 5, 6 }));

	}

	public int multiplyLeftRightSum(int[] array) {

		int lengthOf = array.length;
		int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

		int sumLeft = 0;
		int sumRight = 0;

		for (int i = lengthOf - 1, j = 0; i >= middlePoint; i--, j++) {

			if (j < middlePoint)
				sumLeft += array[j];

			sumRight += array[i];
		}

		return sumLeft * sumRight;
	}

	@Test
	public void num() {
		assertEquals(6, num(new int[] { 11, 12, 13, 14, 15 }, 5, 1));
		assertEquals(4, num(new int[] { 0, 10, 20, 30 }, 4, 0));

	}

	public long num(int a[], int n, int k) {
		Long count = 0L;
		char match = Integer.toString(k).charAt(0);

		for (int j = 0; j < n; j++) {

			String s = Integer.toString(a[j]);

			count = count + s.chars().filter(ch -> ch == match).count();
		}

		return count.intValue();
	}

	@Test
	public void sortEmployee() {
		assertEquals("geek 50 xbnnskd 100", sortEmployee("xbnnskd 100 geek 50"));
		assertEquals("ram 50 shyam 50", sortEmployee("shyam 50 ram 50"));
		assertEquals("ram 50 shyam 50 xbnnskd 100", sortEmployee("xbnnskd 100 shyam 50 ram 50"));

	}

	// TOFIX too slow
	public String sortEmployee(String s) {

		String[] split = s.split("(?<!\\G\\S+)\\s");

		Arrays.sort(split, new Comparator<String>() {
			@Override
			public int compare(final String lhs, String rhs) {

				String[] splitLeft = lhs.split(" ");
				String[] splitRight = rhs.split(" ");

				int a = splitLeft[1].compareTo(splitRight[1]);
				int b = splitLeft[0].compareTo(splitRight[0]);

				if (a == 0)
					return b < 0 ? -1 : 1;

				if (a > 0)
					return -1;

				return 1;

			}
		});

		return Arrays.stream(split).collect(Collectors.joining(" "));
	}

	@Test
	public void checkIfFreqCanBeEqual() {

		assertEquals(false, checkIfFreqCanBeEqual("xxxxyyzz"));
		assertEquals(true, checkIfFreqCanBeEqual("xyyz"));
		assertEquals(true, checkIfFreqCanBeEqual("ehuuroaidj"));
		assertEquals(true, checkIfFreqCanBeEqual("cceea"));
		assertEquals(false, checkIfFreqCanBeEqual("evjxpnqgmvfjl"));
	}

	public boolean checkIfFreqCanBeEqual(String s1) {

		LinkedList<Long> list = s1.chars().boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values().stream().sorted()
				.collect(Collectors.toCollection(LinkedList::new));

		long first = list.get(0);
		long last = list.get(list.size() - 1);

		/**
		 * Diff greater than one
		 */
		if (last - first > 1)
			return false;

		/**
		 * All values has same frequency
		 */
		if (last - first == 0)
			return true;

		int firstFrequency = Collections.frequency(list, first);
		int lastFrequency = Collections.frequency(list, last);

		if (last - first == 1)
			if ((firstFrequency == 1 && lastFrequency == list.size() - 1)
					|| (lastFrequency == 1 && firstFrequency == list.size() - 1))
				return true;

		return false;

	}

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

		Deque<Integer> q = new LinkedList<Integer>();

		q.add(3);
		q.add(1);
		q.add(2);
		q.add(4);
		q.add(5);

		int size = q.size();
		int[] toReverseArray = new int[size];

		Deque<Integer> queueOut = new LinkedList<Integer>();

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

		list.add(1);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(3);
		list.add(4);

		Collections.sort(list);

		int index = findFirstOccurrence(list.stream().mapToInt(i -> i).toArray(), 0, list.size() - 1, 2);

		assertTrue(index == 1);
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
