package it.cambi.codility.model;

public class Strings {

  /** @return */
  public static boolean isPalindrome(String palindromeString) {
    boolean isPalindrome = true;

    int lengthOf = palindromeString.length();
    int middlePoint = (lengthOf & 1) == 1 ? lengthOf / 2 : (lengthOf + 1) / 2;

    for (int i = lengthOf - 1; i >= middlePoint; i--) {
      if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1)) {
        isPalindrome = false;
        break;
      }
    }
    return isPalindrome;
  }
}
