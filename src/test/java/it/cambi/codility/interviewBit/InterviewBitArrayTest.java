package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitArrayTest {

    @Test
    public void setZeroes() {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 0, 1), Arrays.asList(1, 1, 1), Arrays.asList(1, 1, 1));
        setZeroes(a);
        assertEquals(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 1), Arrays.asList(1, 0, 1)), a);

        List<List<Integer>> b = Arrays.asList(Arrays.asList(1, 0, 1), Arrays.asList(1, 1, 1), Arrays.asList(1, 0, 1));
        setZeroes(b);
        assertEquals(Arrays.asList(Arrays.asList(0, 0, 0), Arrays.asList(1, 0, 1), Arrays.asList(0, 0, 0)), b);
    }

    private void setZeroes(List<List<Integer>> a) {
        int[] rows = new int[a.size()];
        int[] columns = new int[a.get(0).size()];

        for (int r = 0; r < a.size(); r++) {
            for (int c = 0; c < a.get(r).size(); c++) {
                if (a.get(r).get(c) == 0) {
                    rows[r] = -1;
                    columns[c] = -1;
                }
            }
        }

        for (int r = 0; r < a.size(); r++) {
            for (int c = 0; c < a.get(r).size(); c++) {
                if (rows[r] == -1 || columns[c] == -1)
                    a.get(r).set(c, 0);
            }
        }
    }

    @Test
    public void nobleInteger() {
        assertEquals(1, nobleInteger(new int[]{3, 2, 1, 3}));
        assertEquals(-1, nobleInteger(new int[]{1, 1, 3, 3}));
        assertEquals(1, nobleInteger(new int[]{-4, -2, 0, -1, -6}));
        assertEquals(1, nobleInteger(new int[]{0}));
        assertEquals(-1, nobleInteger(new int[]{-4, 7, 5, 3, 5, -4, 2, -1, -9, -8, -3, 0, 9, -7, -4, -10, -4, 2, 6, 1, -2, -3, -1, -8, 0, -8, -7, -3, 5, -1, -8, -8, 8, -1, -3, 3, 6, 1, -8, -1, 3, -9, 9, -6, 7, 8, -6, 5, 0, 3, -4, 1, -10, 6, 3, -8, 0, 6, -9, -5, -5, -6, -3, 6, -5, -4, -1, 3, 7, -6, 5, -8, -5, 4, -3, 4, -6, -7, 0, -3, -2, 6, 8, -2, -6, -7, 1, 4, 9, 2, -10, 6, -2, 9, 2, -4, -4, 4, 9, 5, 0, 4, 8, -3, -9, 7, -8, 7, 2, 2, 6, -9, -10, -4, -9, -5, -1, -6, 9, -10, -1, 1, 7, 7, 1, -9, 5, -1, -3, -3, 6, 7, 3, -4, -5, -4, -7, 9, -6, -2, 1, 2, -1, -7, 9, 0, -2, -2, 5, -10, -1, 6, -7, 8, -5, -4, 1, -9, 5, 9, -2, -6, -2, -9, 0, 3, -10, 4, -6, -6, 4, -3, 6, -7, 1, -3, -5, 9, 6, 2, 1, 7, -2, 5}));
    }

    private int nobleInteger(int[] array) {

        Arrays.sort(array);

        if (array[array.length - 1] == 0)
            return 1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == array.length - (i + 1) && (i + 1 < array.length && array[i + 1] != array[i]))
                return 1;
        }
        return -1;
    }


    @Test
    public void diffPossible() {
        assertEquals(0, diffPossible(new int[]{1, 3, 2}, 0));
    }

    private int diffPossible(final int[] A, int B) {

        Set<Integer> set = new HashSet<>();

        for (int i : A) {
            if (set.contains(i + B) || set.contains(i - B))
                return 1;

            set.add(i);
        }

        return 0;
    }

    public int[] solve(int[] A) {
        // 2 pointers
        // LHS pointer on last negatuve number
        // RHS pointer on fotst positive number

        int p1 = -1;
        int p2 = -1;
        int n = A.length;

        for (int i = 0; i <= A.length - 1; i++) {
            int num = A[i];
            if (num >= 0) {
                if (p2 == -1) {
                    p2 = i;
                    p1 = i - 1;
                    break;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        if (p2 == -1) {
            p1 = n - 1;
            p2 = n;
        }
        // if(p1 == -1 && p2 == -1)
        // {
        //     return ans;
        // }

        while (p1 != -1 && p2 != n) {
            int a = A[p1] * A[p1];
            int b = A[p2] * A[p2];

            if (a < b) {
                ans.add(a);
                p1--;
            } else {
                ans.add(b);
                p2++;
            }
        }
        while (p1 != -1) {
            int a = A[p1] * A[p1];
            ans.add(a);
            p1--;
        }
        while (p2 != n) {
            int b = A[p2] * A[p2];
            ans.add(b);
            p2++;
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void perfectPeakArray() {

        assertEquals(1, perfectPeakArray(new int[]{5, 1, 4, 3, 6, 8, 10, 7, 9}));
        assertEquals(0, perfectPeakArray(new int[]{5, 1, 4, 4}));
        assertEquals(0, perfectPeakArray(new int[]{1}));
        assertEquals(0, perfectPeakArray(new int[]{10549, 15882, 24856, 301, 16642, 14414, 19856}));
        assertEquals(
                1,
                perfectPeakArray(
                        new int[]{
                                9895, 30334, 17674, 23812, 20038, 25668, 6869, 1870, 4665, 27645, 7712, 17036, 31323,
                                8724, 28254, 28704, 26300, 25548, 15142, 12860, 19913, 32663, 32758
                        }));
        assertEquals(
                0,
                perfectPeakArray(
                        new int[]{
                                5706, 26963, 24465, 29359, 16828, 26501, 28146, 18468, 9962, 2996, 492, 11479, 23282,
                                19170, 15725, 6335
                        }));
    }

    private int perfectPeakArray(int[] A) {

        int left[] = new int[A.length];
        int right[] = new int[A.length];
        left[0] = A[0];
        right[A.length - 1] = A[(A.length - 1)];
        for (int i = 1; i < A.length; i++) left[i] = Math.max(left[i - 1], A[i]);
        for (int i = A.length - 2; i >= 0; i--) right[i] = Math.min(right[i + 1], A[i]);
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] - left[i - 1] > 0 && A[i] - right[i + 1] < 0) return 1;
        }
        return 0;
    }

    @Test
    public void leaderInArray() {

        assertArrayEquals(new int[]{2, 5, 17}, leaderInArray(new int[]{16, 17, 4, 3, 5, 2}));
        assertArrayEquals(new int[]{2}, leaderInArray(new int[]{1, 2}));
    }

    private int[] leaderInArray(int[] A) {

        int max = 0;
        List<Integer> list = new ArrayList<>();

        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] > max) {
                list.add(A[i]);
                max = A[i];
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
