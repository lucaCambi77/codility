package it.cambi.codility.codility;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.MethodName.class)
class CodilityTest {

  @Test
  public void perMissElement() {
    assertEquals(1, perMissElement(new int[] {}));
    assertEquals(2, perMissElement(new int[] {1}));
    assertEquals(4, perMissElement(new int[] {2, 3, 1, 5}));
    assertEquals(1, perMissElement(new int[] {2}));
    assertEquals(5, perMissElement(new int[] {2, 3, 1, 4}));
  }

  public int perMissElement(int[] A) {
    if (A.length == 0) return 1;

    Arrays.sort(A);
    int pointer = 1;

    for (int i = 0; i < A.length; i++) {
      if (A[i] == pointer) pointer++;
      else return pointer;
    }

    return pointer;
  }

  /**
   * This is a demo task.
   *
   * <p>Write a function:
   *
   * <p>class Solution { public int solution(int[] A); }
   *
   * <p>that, given an array A of N integers, returns the smallest positive integer (greater than 0)
   * that does not occur in A.
   *
   * <p>For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
   *
   * <p>Given A = [1, 2, 3], the function should return 4.
   *
   * <p>Given A = [−1, −3], the function should return 1.
   *
   * <p>Write an efficient algorithm for the following assumptions:
   *
   * <p>N is an integer within the range [1..100,000]; each element of array A is an integer within
   * the range [−1,000,000..1,000,000].
   */
  @Test
  public void smallestPositiveInteger() {

    assertEquals(5, smallestPositiveInteger(new int[] {1, 3, 6, 4, 1, 2}));
    assertEquals(1, smallestPositiveInteger(new int[] {-1, -3}));
    assertEquals(3, smallestPositiveInteger(new int[] {1, 2}));
    assertEquals(2, smallestPositiveInteger(new int[] {-1, 1}));
    assertEquals(1, smallestPositiveInteger(new int[] {-1, 0}));
  }

  public int smallestPositiveInteger(int[] array) {

    Arrays.sort(array);

    int j = 1;
    int i = 1;
    while (j < array.length) {

      if (array[j] < 1) j++;
      else if (array[j] - array[j - 1] == 1) {
        i++;
        j++;
      } else if (array[j] - array[j - 1] > 1) return ++i;
      else j++;
    }

    return array[array.length - 1] <= 0 ? 1 : ++i;
  }

  @Test
  public void cyclicRotation() {

    int K = 7;

    int[] A = {1, 5, 7, 4};

    if (A.length == 0) return;

    int lentghtA = A.length;

    while (K > lentghtA) {
      K = K % lentghtA;
    }

    int[] finalArray = new int[lentghtA];

    for (int i = 0; i < lentghtA; i++) {
      int finalPosition = i + K;

      if (finalPosition >= lentghtA) finalPosition = finalPosition - lentghtA;

      finalArray[finalPosition] = A[i];
    }
  }

  /**
   * Write a function:
   *
   * <p>class Solution { public int solution(int N); }
   *
   * <p>that, given a positive integer N, returns the length of its longest binary gap. The function
   * should return 0 if N doesn't contain a binary gap.
   */
  @Test
  public void binaryGap() {
    assertEquals(5, binaryGap(1041));
    assertEquals(2, binaryGap(9));
    assertEquals(4, binaryGap(529));
    assertEquals(2, binaryGap(328));
  }

  private int binaryGap(int input) {

    String binary = Integer.toBinaryString(input);

    int countTmp = 0;
    int maxCount = 0;

    for (int i = 0; i < binary.toCharArray().length; i++) {

      if (binary.toCharArray()[i] == '0') countTmp++;
      else {
        maxCount = Math.max(maxCount, countTmp);
        countTmp = 0;
      }
    }
    return maxCount;
  }

  /**
   * the elements at indexes 0 and 2 have value 9, the elements at indexes 1 and 3 have value 3, the
   * elements at indexes 4 and 6 have value 9, the element at index 5 has value 7 and is unpaired.
   *
   * <p>Write a function:
   *
   * <p>int solution(int A[], int N);
   *
   * <p>that, given an array A consisting of N integers fulfilling the above conditions, returns the
   * value of the unpaired element.
   */
  @Test
  public void oddOccurrences() {
    assertEquals(7, oddOccurrences(new int[] {9, 3, 9, 3, 9, 7, 9, 1, 1}));
    assertEquals(9, oddOccurrences(new int[] {9, 3, 9, 3, 9, 7, 7, 1, 1}));
    assertEquals(7, oddOccurrences(new int[] {9, 3, 9, 3, 9, 7, 7, 7, 1, 1}));
    assertEquals(1, oddOccurrences(new int[] {9, 3, 9, 3, 9, 7, 7, 7, 7, 1}));
  }

  private int oddOccurrences(int[] A) {

    Arrays.sort(A);

    for (int i = 1; i < A.length; i++) {
      if (A[i] != A[i - 1]) return A[i - 1];
      i++;
    }

    return A[A.length - 1];
  }

  /**
   * Write a function:
   *
   * <p>class Solution { public int[] solution(int[] A, int K); }
   *
   * <p>that, given an array A consisting of N integers and an integer K, returns the array A
   * rotated K times.
   */
  @Test
  public void rotateArray() {

    assertArrayEquals(new int[] {3, 4, 1, 2}, rotateArray(new int[] {1, 2, 3, 4}));
  }

  private int[] rotateArray(int[] array) {
    int k = 2;

    int arrayLength = array.length;

    int[] sol = new int[array.length];

    for (int i = 0; i < array.length; i++) {
      int position = (i + k) % arrayLength;

      sol[position] = array[i];
    }
    return sol;
  }

  /**
   * class Solution { public int solution(int X, int Y, int D); }
   *
   * <p>that, given three integers X, Y and D, returns the minimal number of jumps from position X
   * to a position equal to or greater than Y.
   */
  @Test
  public void frogJump() {

    int X = 10;
    int Y = 76;

    int D = 20;

    int diff = Y - X;

    Math.ceil((double) diff / D);
  }

  /**
   * Write a function:
   *
   * <p>class Solution { public int solution(int[] A); }
   *
   * <p>that, given an array A of N integers, returns the smallest positive integer (greater than 0)
   * that does not occur in A.
   */
  @Test
  public void missingElement() {

    assertEquals(3, missingElement(new int[] {1, 2, 0}));
    assertEquals(2, missingElement(new int[] {3, 4, -1, 1}));
    assertEquals(1, missingElement(new int[] {-8, -7, -6}));
  }

  private int missingElement(int[] A) {

    Arrays.parallelSort(A);

    int start = 1;

    int i = 0;

    while (i < A.length && A[i] < start) i++;

    for (; i < A.length; i++) {
      if (A[i] == start) start++;
      else return start;
    }

    return A[A.length - 1] < 0 ? 1 : start;
  }
  /**
   * Write a function:
   *
   * <p>int solution(int A[], int N);
   *
   * <p>that, given a non-empty array A of N integers, returns the minimal difference (absolute
   * difference between the sum of the first part and the sum of the second part) that can be
   * achieved.
   */
  @Test
  public void tapeEquilibrium() {
    assertEquals(2000, tapeEquilibrium(new int[] {-1000, 1000}));
    assertEquals(1, tapeEquilibrium(new int[] {3, 1, 2, 4, 3}));
  }

  private int tapeEquilibrium(int[] A) {

    int sum = 0;

    long leftSum = 0;
    long rightSum;
    for (int value : A) {
      sum += value;
    }

    long minDifference = Integer.MAX_VALUE;
    for (int i = 1; i < A.length; i++) {
      leftSum += A[i - 1];
      rightSum = sum - leftSum;

      long difference = Math.abs(leftSum - rightSum);
      minDifference = Math.min(minDifference, difference);
    }

    return (int) minDifference;
  }
  /**
   * A non-empty array A consisting of N integers is given.
   *
   * <p>The goal is to check whether array A is a permutation.
   */
  @Test
  public void permCheck() {

    assertEquals(1, permCheck(new int[] {4, 3, 1, 2}));
    assertEquals(0, permCheck(new int[] {4, 3, 1}));
  }

  private int permCheck(int[] A) {

    Set<Integer> set = new HashSet<>();

    for (int value : A) set.add(value);

    for (int i = 1; i <= A.length; i++) if (!set.contains(i)) return 0;

    return 1;
  }

  @Test
  public void frogRiverOne() {

    assertEquals(4, frogRiverOne(new int[] {1, 3, 1, 3, 2, 1, 3}, 3));
    assertEquals(6, frogRiverOne(new int[] {1, 3, 1, 4, 2, 3, 5, 4}, 5));
    assertEquals(-1, frogRiverOne(new int[] {1, 3, 1, 4, 3, 5, 4}, 5));
    assertEquals(7, frogRiverOne(new int[] {1, 3, 1, 4, 3, 5, 1, 2}, 5));
  }

  private int frogRiverOne(int[] A, int X) {

    Set<Integer> leaves = new HashSet<>();
    int count = 0;

    for (int i = 0; i < A.length; i++) {
      if (leaves.add(A[i])) {
        count += A[i];
        if ((X * (X + 1)) / 2 == count) return i;
      }
    }

    return -1;
  }

  @Test
  public void passingCars() {

    int[] A = {0, 1, 0, 1, 1};

    int num_east = 0; // initial
    int num_pass = 0; // initial

    for (int value : A) {
      if (value == 0) { // to east
        num_east++;
      }
      if (value == 1) { // to west
        num_pass = num_pass + num_east;
      }
      // note: just look back "num_east"
      // that will be the number of cars can be paired
      // (with the current car)
    }

    // note 1: can use "_" for a big value
    // note 2: "num_pass < 0" is for the "overflow" cases
    // when "overflow" occurs, the value will "< 0" (important)
    if (num_pass > 1_000_000_000 || num_pass < 0) return; // -1;
    else return; // num_pass;
  }

  @Test
  public void minAvgTwoSlice() {

    assertEquals(2, minAvgTwoSlice(new int[] {-3, -5, -8, -4, -10}));
    assertEquals(1, minAvgTwoSlice(new int[] {4, 2, 2, 5, 1, 5, 8}));
    // {-8, -16, -20, -30}
  }

  public int minAvgTwoSlice(int[] A) {
    int min_idx = 0;
    double min_value = 10001;

    for (int idx = 0; idx < A.length - 1; idx++) {
      if ((A[idx] + A[idx + 1]) / 2.0 < min_value) {
        min_idx = idx;
        min_value = (A[idx] + A[idx + 1]) / 2.0;
      }

      if (idx < A.length - 2 && (A[idx] + A[idx + 1] + A[idx + 2]) / 3.0 < min_value) {
        min_idx = idx;
        min_value = (A[idx] + A[idx + 1] + A[idx + 2]) / 3.0;
      }
    }

    return min_idx;
  }

  @Test
  public void countDiv() {

    int A = 11;
    int B = 345;

    if (B - A < 0) return; // - 1;
    int count = 0;

    for (int i = A; i <= B; i++) {
      if (i % 17 == 0) count++;
    }
  }

  @Test
  public void maxProductOfThree() {
    int[] A = {-5, 5, -5, 4};
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
        Math.max(
            A[0] * A[1] * A[arrLength - 1],
            A[arrLength - 1] * A[arrLength - 2] * A[arrLength - 3]));
  }

  /**
   * Write a function:
   *
   * <p>struct Results solution(int N, int A[], int M);
   *
   * <p>that, given an integer N and a non-empty array A consisting of M integers, returns a
   * sequence of integers representing the values of the counters.
   */
  @Test
  public void maxCounters() {

    int[] operations = {3, 4, 4, 6, 1, 4, 4};

    int N = 5;
    int max = 0;
    int lastMax = 0;
    int[] array = new int[N];

    for (int i = 0; i < operations.length; i++) {
      if (operations[i] >= 1 && operations[i] <= N) {

        if (array[operations[i] - 1] < lastMax) array[operations[i] - 1] = lastMax + 1;
        else array[operations[i] - 1]++;

        max = Math.max(max, array[operations[i] - 1]);

      } else {
        lastMax = max;
      }
    }

    /** Riporto i valori che sono sotto il massimo corrente */
    for (int i = 0; i < array.length; i++) {
      if (array[i] < lastMax) array[i] = lastMax;
    }

    System.out.println(Arrays.toString(array));
  }

  /**
   * Write a function
   *
   * <p>class Solution { public int solution(int[] A); }
   *
   * <p>that, given an array A consisting of N integers, returns the number of distinct values in
   * array A.
   */
  @Test
  public void sorting() {
    assertEquals(4, sorting(new int[] {3, 4, 4, 6, 1, 4, 4}));
  }

  public int sorting(int[] array) {

    Set<Integer> set = new HashSet<>();

    for (int value : array) set.add(value);

    return set.size();
  }

  /**
   * Write a function:
   *
   * <p>class Solution { public int solution(int[] A, int[] B); }
   *
   * <p>that, given two non-empty arrays A and B consisting of N integers, returns the number of
   * fish that will stay alive.
   */
  @Test
  public void fish() {

    int[] A = {4, 3, 2, 1, 5};
    int[] B = {0, 1, 0, 0, 0};

    if (A.length != B.length) return;

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

          if (downFishes.peek() < A[i]) downFishes.pop();
          else break;
        }
      }
    }

    assertEquals(2, result);
  }

  /**
   * Write a function:
   *
   * <p>class Solution { public int[] solution(String S, int[] P, int[] Q); }
   *
   * <p>that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q
   * consisting of M integers, returns an array consisting of M integers specifying the consecutive
   * answers to all queries.
   */
  @Test
  public void genomicRangeQuery() {

    int[] p = {2, 5, 0};
    int[] q = {4, 5, 6};

    if (p.length != q.length || p.length > 50000) return;

    Map<String, Integer> impactMap = new HashMap<>();
    impactMap.put("A", 1);
    impactMap.put("C", 2);
    impactMap.put("G", 3);
    impactMap.put("T", 4);

    String S = "CAGCCTA";

    if (S.length() > 100000) return;

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

    String S = "CAACCTA";

    if (S.length() > 100000) return;

    int[] P = {2, 5, 1};
    int[] Q = {4, 5, 2};

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
      int toIndex = Q[i] + 1;
      /**
       * Controllo in ordine crescente, dal minore impatto al maggiore. Se c'è uno (o più) scalini
       * di differenza vuol dire che c'è una di quelle lettere
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

    assertFalse(brackets("{[()]"));
    assertFalse(brackets("}[])]"));
    assertTrue(brackets("{[()]}"));
    assertTrue(brackets(""));
  }

  public boolean brackets(String s) {

    Stack<Character> st = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '{' || c == '[' || c == '(' || st.isEmpty()) st.push(c);
      else if ((st.peek() == '(' && c == ')'
          || st.peek() == '[' && c == ']'
          || st.peek() == '{' && c == '}')) {
        st.pop();
      } else {
        break;
      }
    }

    return st.isEmpty();
  }

  @Test
  public void peaks() {

    int[] A = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};

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

    int[] A = {1, 2, 1};
    int arrLe = A.length;

    if (arrLe == 0) return;

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
      if (A[i] == candidate) count++;

      if (count > midLength) leader = candidate;
    }

    if (leader == -1) return;

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
    int[] A = {4, 3, 4, 4};
    int[] B = Arrays.copyOf(A, A.length);

    int arrLe = A.length;

    if (arrLe == 0) return;

    int midLength = arrLe / 2;

    Arrays.sort(A);

    int candidate = A[midLength];
    int count = 0;
    int leader = -1;

    for (int i = 0; i < A.length; i++) {
      if (A[i] == candidate) count++;

      if (count > midLength) leader = candidate;
    }

    int equiLeaderNum = getEquiLeaderNum(B, leader, count);
  }

  @Test
  public void dominator1() {

    int[] A = {6, 8, 4, 6, 8, 6, 6};

    Stack<Integer> st = new Stack<Integer>();

    st.push(A[0]);
    int previous = A[0];

    for (int i = 1; i < A.length; i++) {
      st.push(A[i]);

      if (st.size() == 1) continue;

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

    int[] A = {5, -7, 3, 5, -2, 4, -1};

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

    int[] A = {23171, 21011, 21123, 21366, 21013, 21367};

    if (A.length == 1) return;

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
    int[] A = {1, 3, -5, 3, 7, 14, 29};
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

  /** Merge sort */
  @Test
  public void sort() {
    int[] inputArr = {5, 1, 6, 2, 3, 4};
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
    assertEquals(8, countFactors(N));
  }

  public int countFactors(int N) {

    int numFactor = 0;

    // main idea:
    // check from 1 to "sqrt_of_N"
    // then, taking its pair into consideration
    // ---> numFactor = numFactor * 2;

    int sqrtN = (int) Math.sqrt(N);
    numFactor = 0; // number of factors

    // check if i is a factor or not (by using N % i ==0)
    for (int i = 1; i <= sqrtN; i++) {
      if (N % i == 0) numFactor++;
    }

    numFactor = numFactor * 2; // add its pair

    // be careful: check if "sqrtN * sqrtN == N"
    if (sqrtN * sqrtN == N) numFactor = numFactor - 1; // minus one: avoid double counting

    return numFactor;
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
