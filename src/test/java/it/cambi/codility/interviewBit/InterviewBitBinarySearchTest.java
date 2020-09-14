package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitBinarySearchTest {

    @Test
    public void validBSTfromPreorder() {
        assertEquals(0, validBSTfromPreorder(new int[]{7, 7, 10, 10, 9, 5, 2, 8}));
    }

    public int validBSTfromPreorder(int[] A) {
        Stack<Integer> s = new Stack<>();
        int root = Integer.MIN_VALUE;

        for (Integer a : A) {

            if (a < root)
                return 0;

            while (!s.isEmpty() && s.peek() < a) {
                root = s.peek();
                s.pop();
            }

            s.push(a);

        }
        return 1;
    }

    @Test
    public void searchBitonicArray() {
        assertEquals(3, searchBitonicArray(new int[]{3, 9, 10, 20, 17, 5, 1}, 20));
        assertEquals(4, searchBitonicArray(new int[]{3, 9, 10, 20, 17, 5, 1}, 17));
        assertEquals(4, searchBitonicArray(new int[]{3, 9, 18, 20, 17, 5, 1}, 17));
        assertEquals(1, searchBitonicArray(new int[]{3, 9, 17, 20, 18, 5, 1}, 9));
        assertEquals(5, searchBitonicArray(new int[]{3, 9, 17, 20, 18, 5, 1}, 5));
        assertEquals(-1, searchBitonicArray(new int[]{1, 20, 50, 40, 10}, 5));
        assertEquals(0, searchBitonicArray(new int[]{5, 20, 50, 40, 10}, 5));
    }

    private int searchBitonicArray(int[] a, int k) {

        int bitonicInd = 0;

        if (a[0] == k)
            return 0;

        for (int i = 1; i < a.length; i++) {
            if (a[i] == k)
                return i;

            if (a[i - 1] < a[i] && a[i + 1] > a[i])
                bitonicInd = i;
        }

        return binarySearch(a, bitonicInd, a.length, k);

    }

    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // (low + high) / 2;
            int midVal = a[mid];

            if (midVal < key) low = mid + 1;
            else if (midVal > key) high = mid - 1;
            else return mid; // key found
        }

        return -1; // key not found.
    }
}
