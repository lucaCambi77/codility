package it.cambi.codility.codility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Codility {
	private static final Logger log = LoggerFactory.getLogger(Codility.class);

	@Test
	public void perMissElement() {
		// int[] A = new int[] { 2, 3, 1, 5 };
		// int[] A = new int[] { 2 };
		int[] A = new int[] { 1 };
		// int[] A = new int[] { 2, 3, 1, 4 };
		if (A.length == 0)
			return;

		Arrays.sort(A);
		int realLength = A.length < 2 ? 2 : A.length + 1;

		for (int i = A.length - 1; i >= 0; i--) {
			if (A[i] == realLength) {
				if (i == 0)
					return; // realLength - 1

				--realLength;
				continue;
			}

			return; // realLength

		}
	}

	/**
	 * 
	 * 
	 * This is a demo task.
	 * 
	 * Write a function:
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given an array A of N integers, returns the smallest positive integer
	 * (greater than 0) that does not occur in A.
	 * 
	 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
	 * 
	 * Given A = [1, 2, 3], the function should return 4.
	 * 
	 * Given A = [−1, −3], the function should return 1.
	 * 
	 * Write an efficient algorithm for the following assumptions:
	 * 
	 * N is an integer within the range [1..100,000]; each element of array A is an
	 * integer within the range [−1,000,000..1,000,000].
	 * 
	 * 
	 */
	@Test
	public void smallestPositiveInteger() {

		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] array = { 1, 3, 6, 4, 1, 2 };

		Set<Integer> mySet = Arrays.stream(array).boxed().collect(Collectors.toSet());

		int i = 1;

		while (mySet.contains(i)) {
			i++;
		}

		System.out.println(i);
	}

	@Test
	public void cyclicRotation() {

		int K = 7;

		int[] A = { 1, 5, 7, 4 };

		if (A.length == 0)
			return;

		int lentghtA = A.length;

		while (K > lentghtA) {
			K = K % lentghtA;

		}

		int[] finalArray = new int[lentghtA];

		for (int i = 0; i < lentghtA; i++) {
			int finalPosition = i + K;

			if (finalPosition >= lentghtA)
				finalPosition = finalPosition - lentghtA;

			finalArray[finalPosition] = A[i];

		}

	}

	/**
	 * Write a function:
	 * 
	 * class Solution { public int solution(int N); }
	 * 
	 * that, given a positive integer N, returns the length of its longest binary
	 * gap. The function should return 0 if N doesn't contain a binary gap.
	 */
	@Test
	public void BinaryGap() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		String binary = Integer.toBinaryString(32);

		Integer countTmp = 0;
		Integer maxCount = 0;

		boolean isZeroCount = false;
		boolean isOne = false;

		for (int i = 0; i < binary.toCharArray().length; i++) {
			char charAt = binary.toCharArray()[i];

			if (charAt == "1".charAt(0) && isZeroCount) {
				if (countTmp > maxCount)
					maxCount = countTmp;

				countTmp = 0;
			} else if (isOne && charAt == "0".charAt(0) && isZeroCount) {
				countTmp++;

				continue;
			} else if (charAt == "1".charAt(0) && !isZeroCount) {
				isZeroCount = true;
				isOne = true;
			}
		}

		log.info(maxCount.toString());
	}

	/**
	 * 
	 * the elements at indexes 0 and 2 have value 9, the elements at indexes 1 and 3
	 * have value 3, the elements at indexes 4 and 6 have value 9, the element at
	 * index 5 has value 7 and is unpaired.
	 * 
	 * Write a function:
	 * 
	 * int solution(int A[], int N);
	 * 
	 * that, given an array A consisting of N integers fulfilling the above
	 * conditions, returns the value of the unpaired element.
	 */
	@Test
	public void oddOccurrences() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] A = { 9, 3, 9, 3, 9, 7, 9, 1, 1 };

		int unpaired;
		unpaired = A[0]; // initial

		for (int i = 1; i < A.length; i++) {
			unpaired = unpaired ^ A[i]; // xor

			System.out.println(unpaired);

		}

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < A.length; i++) {
			if (map.get(A[i]) == null) {

				map.put(A[i], 1);
				continue;
			}

			map.remove(A[i]);

		}

		System.out.println(map.entrySet().iterator().next().getKey());
	}

	/**
	 * Write a function:
	 * 
	 * class Solution { public int[] solution(int[] A, int K); }
	 * 
	 * that, given an array A consisting of N integers and an integer K, returns the
	 * array A rotated K times.
	 */
	@Test
	public void rotateArray() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int k = 2;

		int[] array = { 1, 2, 3, 4 };

		int arrayLength = array.length;

		int[] sol = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			int position = (i + k) % arrayLength;

			sol[position] = array[i];
		}

		System.out.println(Arrays.toString(sol));

	}

	/**
	 * class Solution { public int solution(int X, int Y, int D); }
	 * 
	 * that, given three integers X, Y and D, returns the minimal number of jumps
	 * from position X to a position equal to or greater than Y.
	 */
	@Test
	public void frogJump() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int X = 10;
		int Y = 76;

		int D = 20;

		int diff = Y - X;

		Math.ceil((double) diff / D);
	}

	/**
	 * Write a function:
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given an array A of N integers, returns the smallest positive integer
	 * (greater than 0) that does not occur in A.
	 */
	@Test
	public void missingElement() {

		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] A = { -1, -3, -4 };

		// Using the concept of "Sum = (ceiling + floor) * height /2"
		// So---> Sum = (1 + N+1) * N /2
		// the missing element can be found by minus other elements

		// note: need to use "long" to avoid potential bugs (large numbers)
		long ceiling = A.length + 1;
		long floor = 1;
		long height = A.length + 1; // note: need to plus extra "1"
									// because there is one element "missing"!
									// be careful about this (important)
		long sum = (ceiling + floor) * height / 2; // main idea
		/*
		 * int high = A.length +1; int low = 1; int height = A.length + 1; int sum =
		 * (high +low) * height /2; // main idea
		 */
		long missing_number = sum; // initial setting (sum)

		for (int i = 0; i < A.length; i++) {
			missing_number = missing_number - A[i]; // minus other elements
		}

	}

	/**
	 * Write a function:
	 * 
	 * int solution(int A[], int N);
	 * 
	 * that, given a non-empty array A of N integers, returns the minimal difference
	 * that can be achieved.
	 */
	@Test
	public void tapeEquilibrium() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] A = { -1000, 1000 };

		int sum = 0;

		long leftSum = 0;
		long rightSum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}

		long minDifference = Integer.MAX_VALUE;
		for (int i = 1; i < A.length; i++) {
			leftSum += A[i - 1];
			rightSum = sum - leftSum;

			final long difference = Math.abs(leftSum - rightSum);
			minDifference = Math.min(minDifference, difference);
		}

		System.out.println((int) minDifference);

	}

	/**
	 * A non-empty array A consisting of N integers is given.
	 * 
	 * The goal is to check whether array A is a permutation.
	 */
	@Test
	public void permCheck() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] A = { 4, 3, 1, 2 };
		// int[] A = { 1, 1 };

		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < A.length; i++) {
			set.add(A[i]);
		}

		// check if "all" the elements from "1 to A.length" appeared
		for (int i = 1; i <= A.length; i++) {
			if (!set.contains(i))
				return;// 0; // not a permutation (A[i] is missing)
		}

		// if it contains all the elements (from "1 to A.length")
		// then, "yes"
		return; // 1;

	}

	@Test
	public void frogRiverOne() {

		int X = 3; // leaves

		int[] A = { 1, 3, 1, 3, 2, 1, 3 };

		Set<Integer> leaves = new HashSet<>();

		for (int i = 1; i <= X; i++) {
			leaves.add(i);
		}

		for (int i = 0; i < A.length; i++) {
			if (leaves.contains(A[i]))
				leaves.remove(A[i]);

			if (leaves.isEmpty())
				return; // i;

		}

		return; // -1;
	}

	@Test
	public void passingCars() {

		int[] A = { 0, 1, 0, 1, 1 };

		int num_east = 0; // initial
		int num_pass = 0; // initial

		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) { // to east
				num_east++;
			}
			if (A[i] == 1) { // to west
				num_pass = num_pass + num_east;
			}
			// note: just look back "num_east"
			// that will be the number of cars can be paired
			// (with the current car)
		}

		// note 1: can use "_" for a big value
		// note 2: "num_pass < 0" is for the "overflow" cases
		// when "overflow" occurs, the value will "< 0" (important)
		if (num_pass > 1_000_000_000 || num_pass < 0)
			return; // -1;
		else
			return; // num_pass;
	}

	@Test
	public void minAvgTwoSlice() {
		int[] A = { -3, -5, -8, -4, -10 };
		int minDiff = Integer.MAX_VALUE;
		int result = -1;

		for (int i = 1; i < A.length; i++) {
			int diff = (A[i] + A[i - 1]) / 2;

			if (diff < minDiff) {
				minDiff = diff;
				result = i - 1;
			}
		}

	}

	@Test
	public void countDiv() {

		int A = 11;
		int B = 345;

		if (B - A < 0)
			return; // - 1;
		int count = 0;

		for (int i = A; i <= B; i++) {
			if (i % 17 == 0)
				count++;
		}
	}

	@Test
	public void MaxProductOfThree() {
		int[] A = { -5, 5, -5, 4 };
		int arrLength = A.length;
		int minimal = 0;

		for (int i = 0; i < arrLength; i++) {
			minimal = i;

			for (int j = i + 1; j < arrLength; j++) {

				if (A[j] < A[minimal]) {
					minimal = j - 1;

					int swap = A[i];

					A[i] = A[j];
					A[j] = swap;

				}
			}
		}

		System.out.println(
				Math.max(A[0] * A[1] * A[arrLength - 1], A[arrLength - 1] * A[arrLength - 2] * A[arrLength - 3]));
	}

	/**
	 * Write a function:
	 * 
	 * struct Results solution(int N, int A[], int M);
	 * 
	 * that, given an integer N and a non-empty array A consisting of M integers,
	 * returns a sequence of integers representing the values of the counters.
	 */
	@Test
	public void maxCounters() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] operations = { 3, 4, 4, 6, 1, 4, 4 };

		int N = 5;
		int max = 0;
		int lastMax = 0;
		int[] array = new int[N];

		for (int i = 0; i < operations.length; i++) {
			if (operations[i] >= 1 && operations[i] <= N) {

				if (array[operations[i] - 1] < lastMax)
					array[operations[i] - 1] = lastMax + 1;
				else
					array[operations[i] - 1]++;

				max = Math.max(max, array[operations[i] - 1]);

			} else {
				lastMax = max;

			}

		}

		/**
		 * Riporto i valori che sono sotto il massimo corrente
		 */
		for (int i = 0; i < array.length; i++) {
			if (array[i] < lastMax)
				array[i] = lastMax;
		}

		System.out.println(Arrays.toString(array));

	}

	/**
	 * Write a function
	 * 
	 * class Solution { public int solution(int[] A); }
	 * 
	 * that, given an array A consisting of N integers, returns the number of
	 * distinct values in array A.
	 */
	@Test
	public void sorting() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		Set<Integer> set = new HashSet<>();

		int[] array = { 3, 4, 4, 6, 1, 4, 4 };

		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);

		}

	}

	/**
	 * Write a function:
	 * 
	 * class Solution { public int solution(int[] A, int[] B); }
	 * 
	 * that, given two non-empty arrays A and B consisting of N integers, returns
	 * the number of fish that will stay alive.
	 */
	@Test
	public void fish() {

		int[] A = { 4, 3, 2, 1, 5 };
		int[] B = { 0, 1, 0, 0, 0 };

		if (A.length != B.length)
			return;

		int result = 0;

		Stack<Integer> downFishes = new Stack<Integer>();

		// O(N)
		for (int i = 0; i < A.length; i++) {
			result++;

			if (B[i] == 1) {
				downFishes.push(A[i]);
			} else if (!downFishes.empty()) {
				while (result != 0 && !downFishes.empty()) {
					result--;

					if (downFishes.peek() < A[i])
						downFishes.pop();
					else
						break;
				}
			}
		}

		assertEquals(2, result);

	}

	/**
	 * Write a function:
	 * 
	 * class Solution { public int[] solution(String S, int[] P, int[] Q); }
	 * 
	 * that, given a non-empty string S consisting of N characters and two non-empty
	 * arrays P and Q consisting of M integers, returns an array consisting of M
	 * integers specifying the consecutive answers to all queries.
	 */
	@Test
	public void genomicRangeQuery() {

		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] p = { 2, 5, 0 };
		int[] q = { 4, 5, 6 };

		if (p.length != q.length || p.length > 50000)
			return;

		Map<String, Integer> impactMap = new HashMap<>();
		impactMap.put("A", 1);
		impactMap.put("C", 2);
		impactMap.put("G", 3);
		impactMap.put("T", 4);

		String S = "CAGCCTA";

		if (S.length() > 100000)
			return;

		List<String> split = Arrays.asList(S.split("(?!^)"));

		int[] result = new int[p.length];

		for (int i = 0; i < p.length; i++) {
			int min = Integer.MAX_VALUE;
			int charAtP = p[i];
			int charAtQ = q[i];

			List<String> section = split.subList(charAtP, charAtQ + 1);

			for (String string : section) {

				min = Math.min(min, impactMap.get(string));

			}

			result[i] = min;
		}

		System.out.println(Arrays.toString(result));

	}

	@Test
	public void solveGenomicRange() {
		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		String S = "CAACCTA";

		if (S.length() > 100000)
			return;

		int[] P = { 2, 5, 1 };
		int[] Q = { 4, 5, 2 };

		// used jagged array to hold the prefix sums of each A, C and G genoms
		// we don't need to get prefix sums of T, you will see why.
		int[][] genoms = new int[3][S.length() + 1];
		// if the char is found in the index i, then we set it to be 1 else they are 0
		// 3 short values are needed for this reason
		short a, c, g;
		for (int i = 0; i < S.length(); i++) {
			a = 0;
			c = 0;
			g = 0;
			if ('A' == (S.charAt(i))) {
				a++;
			}
			if ('C' == (S.charAt(i))) {
				c++;
			}
			if ('G' == (S.charAt(i))) {
				g++;
			}
			// here we calculate prefix sums. To learn what's prefix sums look at here
			// https://codility.com/media/train/3-PrefixSums.pdf
			genoms[0][i + 1] = genoms[0][i] + a;
			genoms[1][i + 1] = genoms[1][i] + c;
			genoms[2][i + 1] = genoms[2][i] + g;
		}

		int[] result = new int[P.length];
		// here we go through the provided P[] and Q[] arrays as intervals
		for (int i = 0; i < P.length; i++) {
			int fromIndex = P[i];
			// we need to add 1 to Q[i],
			// because our genoms[0][0], genoms[1][0] and genoms[2][0]
			// have 0 values by default, look above genoms[0][i+1] = genoms[0][i] + a;
			int toIndex = Q[i] + 1;/**
									 * Controllo in ordine crescente, dal minore impatto al maggiore. Se c'è uno (o
									 * più) scalini di differenza vuol dire che c'è una di quelle lettere
									 */
			if (genoms[0][toIndex] - genoms[0][fromIndex] > 0) {
				result[i] = 1;
			} else if (genoms[1][toIndex] - genoms[1][fromIndex] > 0) {
				result[i] = 2;
			} else if (genoms[2][toIndex] - genoms[2][fromIndex] > 0) {
				result[i] = 3;
			} else {
				result[i] = 4;
			}
		}

		// return result;
	}

	@Test
	public void brackets() {

		assertFalse(brackets("{[()]").isEmpty());
		assertFalse(brackets("}[])]").isEmpty());
		assertTrue(brackets("{[()]}").isEmpty());

	}

	@Test
	public Stack<Character> brackets(String S) {

		Stack<Character> st = new Stack<Character>();

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);

			if (c == '{' || c == '[' || c == '(' || st.isEmpty()) {
				st.push(c);
				continue;
			}

			if (((char) st.peek() == '(' && c == ')' || (char) st.peek() == '[' && c == ']'
					|| (char) st.peek() == '{' && c == '}')) {
				st.pop();
			} else {
				break;
			}
		}

		return st;
	}

	@Test
	public void peaks() {

		log.info("********************************************************");
		log.info("Sono in -------------------> " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] A = { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };

		// main idea:
		// 1) find all the peaks, and put them into a List
		// 2) try to divide the array into a number of groups,
		// and check if all the groups have at least one peak
		// --> if yes, return the "number of groups"

		// use "List" to store all the peaks
		List<Integer> peaksIndexList = new ArrayList<>();

		// 1) find the peaks (and store them)
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i - 1] < A[i] && A[i] > A[i + 1]) { // A[i] > A[i-1], A[i] > A[i+1]
				peaksIndexList.add(i);
			}
		}

		// 2) check the number of Blocks
		int N = A.length;

		// from the "biggest possible number" to smaller number
		for (int numBlocks = N; numBlocks >= 1; numBlocks--) {

			if (N % numBlocks == 0) { // it is divisible

				int blockSize = N / numBlocks;
				int ithOkBlock = 0; // the ith block has peak(s)

				// test all the peaks
				// if a peak is found in the ith block
				// then, go to the (i+1)th block
				for (int peaksIndex : peaksIndexList) {
					if (peaksIndex / blockSize == ithOkBlock) { // peak in the ith block
						ithOkBlock++; // go to check (i+1)th block
					}
				}

				// ithOkBlock: the number of blocks having peak(s)
				// if all the blocks have peak(s)
				// then, return the number of blocks
				// note: we test from the biggest possible number
				// so, once we find it, we can just return it
				// (no need to check the smaller possible numbers)
				if (ithOkBlock == numBlocks) {
					System.out.println(numBlocks);
					// return numBlocks;
				}
			}
		}

		// return 0;
		System.out.println(0);

	}

	@Test
	public void dominator() {

		int[] A = { 1, 2, 1 };
		int arrLe = A.length;

		if (arrLe == 0)
			return;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++) {
			map.put(A[i], i);

		}

		int midLength = arrLe / 2;

		Arrays.sort(A);

		int candidate = A[midLength];
		int count = 0;
		int leader = -1;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate)
				count++;

			if (count > midLength)
				leader = candidate;

		}

		if (leader == -1)
			return;

		System.out.println(map.get(leader));
	}

	private int getEquiLeaderNum(int[] A, int leader_Value, int leader_Count) {

		int num_Equi_leaders = 0; // number of equi leaders
		int left_Leader_Count = 0; // number of leaders in left side

		// scan the array
		for (int i = 0; i < A.length; i++) {

			// find a leader (in left side)
			if (A[i] == leader_Value) {
				left_Leader_Count++;
			}

			// if the leader is "a leader in left side" (more than half)
			if (left_Leader_Count > (0.5) * (i + 1)) {
				// then, check right side
				int right_Leader_Count = leader_Count - left_Leader_Count;
				// if the leader is "a leader in right side" (more than half)
				if (right_Leader_Count > (0.5) * (A.length - i - 1)) {
					num_Equi_leaders++; // leader in both sides (then, equi leaders++)
				}
			}
		}
		return num_Equi_leaders;
	}

	@Test
	public void equiLeader() {
		// int[] A = { 4, 4, 4, 4, 4 };
		int[] A = { 4, 3, 4, 4 };
		int[] B = Arrays.copyOf(A, A.length);

		int arrLe = A.length;

		if (arrLe == 0)
			return;

		int midLength = arrLe / 2;

		Arrays.sort(A);

		int candidate = A[midLength];
		int count = 0;
		int leader = -1;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate)
				count++;

			if (count > midLength)
				leader = candidate;

		}

		int equiLeaderNum = getEquiLeaderNum(B, leader, count);

	}

	@Test
	public void dominator1() {

		int[] A = { 6, 8, 4, 6, 8, 6, 6 };

		Stack<Integer> st = new Stack<Integer>();

		st.push(A[0]);
		int previous = A[0];

		for (int i = 1; i < A.length; i++) {
			st.push(A[i]);

			if (st.size() == 1)
				continue;

			if (previous != A[i]) {
				st.pop();
				st.pop();

				continue;
			}

			previous = A[i];
		}

		System.out.println(st.peek());
	}

	@Test
	public void maxSliceSum() {

		int[] A = { 5, -7, 3, 5, -2, 4, -1 };

		int result = 0;

		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				int sum = 0;
				for (int j2 = i; j2 < j + 1; j2++) {

					sum += A[j2];
					result = Math.max(result, sum);
				}

			}
		}

		System.out.println(result);

		result = 0;

		for (int i = 0; i < A.length; i++) {
			int sum = 0;
			for (int j = i; j < A.length; j++) {
				sum += A[j];
				result = Math.max(result, sum);

			}
		}

		System.out.println(result);

		int maxEndingHere = A[0];
		int maxSoFar = A[0];

		for (int i = 1; i < A.length; i++) {
			maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}

		System.out.println(maxSoFar);

	}

	@Test
	public void maxProfit() {

		int[] A = { 23171, 21011, 21123, 21366, 21013, 21367 };

		if (A.length == 1)
			return;

		int solution = 0;

		for (int i = 1; i < A.length; i++) {

			int diff = A[i] - A[0];

			if (diff < solution) {
				if (A[i] < A[0]) {

					int tmp = A[0];
					A[0] = A[i];
					A[i] = tmp;
				}

			} else {
				solution = Math.max(diff, solution);

			}

		}

		System.out.println(solution);
	}

	@Test
	public void prefixSum() {

		// int[] A = { -2, 1 };
		// int[] A = { 3, 2, -6, 4, 0 };
		// int[] A = { 1, 1, 1 };
		// int[] A = { 1, -2, 3 };
		int[] A = { 1, 3, -5, 3, 7, 14, 29 };
		// int sum = 0;
		// int[] arrOut = new int[A.length];
		//
		// for (int i = 0; i < A.length; i++)
		// {
		// sum += A[i];
		// arrOut[i] = sum;
		// }

		int solution = A[0];
		int sum = A[0];

		for (int i = 1; i < A.length; i++) {

			int diff = A[i] + A[i - 1];
			sum += A[i];

			if (diff > solution) {
				solution = Math.max(diff, solution);

			}

			solution = Math.max(A[i], solution);
			solution = Math.max(sum, solution);
		}

		System.out.println(Arrays.toString(A));
	}

	/**
	 * Merge sort
	 */
	@Test
	public void sort() {
		int[] inputArr = { 5, 1, 6, 2, 3, 4 };
		mergeSort(inputArr, inputArr.length);
	}

	public static void mergeSort(int[] a, int n) {
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i = mid; i < n; i++) {
			r[i - mid] = a[i];
		}
		mergeSort(l, mid);
		mergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);
	}

	public static void merge(int[] a, int[] l, int[] r, int left, int right) {

		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
	}

	@Test
	public void countFactors() {
		int N = 24;
		int numFactor = 0;

		for (int i = 1; i <= N; i++) {
			if (N % i == 0)
				numFactor += 1;
		}

		// main idea:
		// check from 1 to "sqrt_of_N"
		// then, taking its pair into consideration
		// ---> numFactor = numFactor * 2;

		int sqrtN = (int) Math.sqrt(N);
		numFactor = 0; // number of factors

		// check if i is a factor or not (by using N % i ==0)
		for (int i = 1; i <= sqrtN; i++) {
			if (N % i == 0) {
				numFactor++;
			}
		}

		numFactor = numFactor * 2; // add its pair

		// be careful: check if "sqrtN * sqrtN == N"
		if (sqrtN * sqrtN == N) {
			numFactor = numFactor - 1; // minus one: avoid double counting
		}
	}

	@Test
	public void minPeremeterRectangle() {

		int N = 30;
		int perimeter = Integer.MAX_VALUE;
		int sqrtN = (int) Math.sqrt(N);

		for (int i = sqrtN; i > 0; i--) {
			if (N % i == 0) {
				perimeter = Math.min(perimeter, 2 * (i + N / i));
			}

		}

		System.out.println(perimeter);
	}

}
