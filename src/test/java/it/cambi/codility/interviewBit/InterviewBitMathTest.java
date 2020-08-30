package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitMathTest {

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
