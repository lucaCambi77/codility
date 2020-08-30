package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitArrayTest {

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

    public int perfectPeakArray(int[] A) {

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

    public int[] leaderInArray(int[] A) {

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

    @Test
    public void fizzBuzz() {
        assertArrayEquals(new String[]{"1", "2", "Fizz", "4", "Buzz"}, fizzBuzz(5));
    }

    @Test
    public String[] fizzBuzz(int A) {

        int i = 1;
        String[] sol = new String[A];
        while (i <= A) {

            if (i % 15 == 0)
                sol[i - 1] = "FizzBuzz";
            else if (i % 5 == 0)
                sol[i - 1] = "Buzz";
            else if (i % 3 == 0)
                sol[i - 1] = "Fizz";
            else
                sol[i - 1] = Integer.toString(i);

            i++;
        }
        return sol;
    }
}
