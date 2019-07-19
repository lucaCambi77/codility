package it.cambi.codility.company;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Ocado {

	private static String palindromeString = "aabaa";
	private static String notPalindromeString = "aaba";
	private static String canArrangePalindromeString = "baba";

	@Test
	public void isPalindrome() {
		boolean isPalindrome = true;

		int lengthOf = palindromeString.length();
		int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

		for (int i = lengthOf - 1; i >= middlePoint; i--) {
			if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1)) {
				isPalindrome = false;
				break;

			}

		}

		assertTrue(isPalindrome);
	}

	@Test
	public void canArrangeAsPalindrome() {

		boolean canArrange = true;

		int length = canArrangePalindromeString.length();

		int count[] = new int[128];
		Arrays.fill(count, 0);

		// For each character in input strings,
		// increment count in the corresponding
		// count array
		for (int i = 0; i < canArrangePalindromeString.length(); i++)
			count[(int) (canArrangePalindromeString.charAt(i))]++;

		// Count odd occurring characters
		int odd = 0;
		for (int i = 0; i < length; i++) {
			if ((count[i] & 1) == 1)
				odd++;

			if (odd > 1)
				canArrange = false;
		}

		assertTrue(canArrange);

		// Return true if odd count is 0 or 1,
		System.out.println("Can arrange palindrome" + canArrange);
	}

	@Test
	public void longestPalindrome() {

		longestPalSubstr("baabaaa");
	}

	// This function prints the longest palindrome substring
	// of str[].
	// It also returns the length of the longest palindrome
	static int longestPalSubstr(String str) {
		int n = str.length(); // get length of input string

		// table[i][j] will be false if substring str[i..j]
		// is not palindrome.
		// Else table[i][j] will be true
		boolean table[][] = new boolean[n][n];

		// All substrings of length 1 are palindromes
		int maxLength = 1;
		for (int i = 0; i < n; ++i)
			table[i][i] = true;

		// check for sub-string of length 2.
		int start = 0;
		for (int i = 0; i < n - 1; ++i) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				table[i][i + 1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// Check for lengths greater than 2. k is length
		// of substring
		for (int k = 3; k <= n; ++k) {

			// Fix the starting index
			for (int i = 0; i < n - k + 1; ++i) {
				// Get the ending index of substring from
				// starting index i and length k
				int j = i + k - 1;

				// checking for sub-string from ith index to
				// jth index iff str.charAt(i+1) to
				// str.charAt(j-1) is a palindrome
				if (table[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
					table[i][j] = true;

					if (k > maxLength) {
						start = i;
						maxLength = k;
					}
				}
			}
		}
		System.out.print("Longest palindrome substring is; ");
		System.out.println(str.substring(start, start + maxLength));

		return maxLength; // return length of LPS
	}

	public void longestPalindrome1() {
		String str = "baabaaa";
		// Java implementation of O(n^2) time and O(1) space method
		// to find the longest palindromic substring
		int maxLength = 1; // The result (length of LPS)

		int start = 0;
		int len = str.length();

		int low, high;

		// One by one consider every character as center
		// point of even and length palindromes
		for (int i = 1; i < len; ++i) {
			// Find the longest even length palindrome with
			// center points as i-1 and i.
			low = i - 1;
			high = i;
			while (low >= 0 && high < len && str.charAt(low) == str.charAt(high)) {
				if (high - low + 1 > maxLength) {
					start = low;
					maxLength = high - low + 1;
				}
				--low;
				++high;
			}

			// Find the longest odd length palindrome with
			// center point as i
			low = i - 1;
			high = i + 1;
			while (low >= 0 && high < len && str.charAt(low) == str.charAt(high)) {
				if (high - low + 1 > maxLength) {
					start = low;
					maxLength = high - low + 1;
				}
				--low;
				++high;
			}
		}

		System.out.print("Longest palindrome substring is: " + str.substring(start, start + maxLength - 1));

		// This code is contributed by Sumit Ghosh

	}

	@Test
	public void primitiveBetweenNumbers() {
		// Declare the variables
		int s1, s2, i, j, count = 0;

		s1 = 5;
		s2 = 20;

		for (i = s1; i <= s2; i++) {
			for (j = 2; j < i; j++) {
				if (i % j == 0) {
					count = 0;
					break;
				} else {
					count = 1;
				}
			}
			if (count == 1) {
				System.out.println(i);
			}
		}

	}

}
