package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitStringTest {

  @Test
  public void intToRoman() {
    assertEquals("IV", intToRoman(4));
    assertEquals("XIV", intToRoman(14));
    assertEquals("MCC", intToRoman(1200));
    assertEquals("CLI", intToRoman(151));
    assertEquals("CM", intToRoman(900));
    assertEquals("XC", intToRoman(90));
  }

  private String intToRoman(int n) {
    StringBuilder s = new StringBuilder();

    LinkedList<Integer> list = new LinkedList<>();

    int i = 0;
    while (n > 0) {
      int x = n % 10;
      int y = (int) Math.pow(10, i);
      list.add(x * y);
      n = n / 10;
      i++;
    }

    int x = list.size();

    for (i = x - 1; i >= 0; i--) {
      int y = list.get(i);
      if (y >= 1000) {
        if (y == 1000) {
          s.append("M");
        } else if (y == 2000) {
          s.append("MM");
        } else if (y == 3000) {
          s.append("MMM");
        }

      } else if (y >= 100 && y < 1000) {
        if (y == 900) {
          s.append("CM");
        } else if (y == 800) {
          s.append("DCCC");
        } else if (y == 700) {
          s.append("DCC");
        } else if (y == 600) {
          s.append("DC");
        } else if (y == 500) {
          s.append("D");
        } else if (y == 400) {
          s.append("CD");
        } else if (y == 300) {
          s.append("CCC");
        } else if (y == 200) {
          s.append("CC");
        } else if (y == 100) {
          s.append("C");
        }
      } else if (y >= 10 && y < 100) {
        if (y == 90) {
          s.append("XC");
        } else if (y == 80) {
          s.append("LXXX");
        } else if (y == 70) {
          s.append("LXX");
        } else if (y == 60) {
          s.append("LX");
        } else if (y == 50) {
          s.append("L");
        } else if (y == 40) {
          s.append("XL");
        } else if (y == 30) {
          s.append("XXX");
        } else if (y == 20) {
          s.append("XX");
        } else if (y == 10) {
          s.append("X");
        }
      } else if (y >= 1 && y < 10) {
        if (y == 9) {
          s.append("IX");
        } else if (y == 8) {
          s.append("VIII");
        } else if (y == 7) {
          s.append("VII");
        } else if (y == 6) {
          s.append("VI");
        } else if (y == 5) {
          s.append("V");
        } else if (y == 4) {
          s.append("IV");
        } else if (y == 3) {
          s.append("III");
        } else if (y == 2) {
          s.append("II");
        } else if (y == 1) {
          s.append("I");
        }
      }
    }
    return s.toString();
  }

  @Test
  public void removeConsecutiveChars() {
    assertEquals("bcd", removeConsecutiveChars("aabcd", 2));
    assertEquals("d", removeConsecutiveChars("aabbccd", 2));
  }

  public String removeConsecutiveChars(String A, int B) {

    char c = A.charAt(0);

    StringBuilder sb = new StringBuilder();
    sb.append(c);
    StringBuilder sol = new StringBuilder();
    for (int i = 1; i < A.length(); i++) {

      if (A.charAt(i) != c) {
        c = A.charAt(i);
        if (sb.length() != B) sol.append(sb.toString());

        sb = new StringBuilder();
      }
      sb.append(A.charAt(i));
    }

    if (sb.length() > 0 && sb.length() != B) sol.append(sb.toString());

    return sol.toString();
  }

  @Test
  public void compareVersion() {
    assertEquals(0, compareVersion("0.1", "0.1"));
    assertEquals(1, compareVersion("0.2", "0.1"));
    assertEquals(1, compareVersion("0.1.1", "0.1"));
    assertEquals(-1, compareVersion("0.1.1", "1.1"));
    assertEquals(-1, compareVersion("1.13", "1.13.4"));
    assertEquals(1, compareVersion("4444371174137455", "5.168"));
    assertEquals(-1, compareVersion("444444444444444444444444", "4444444444444444444444444"));
    assertEquals(0, compareVersion("1.0", "1"));
    assertEquals(0, compareVersion("1.0.0", "1"));
  }

  public int compareVersion(String A, String B) {
    String[] version1 = A.split("\\.");
    String[] version2 = B.split("\\.");

    int i = 0;
    int j = 0;
    while (i < version1.length || j < version2.length) {

      if (new BigInteger(i < version1.length ? version1[i] : "0")
              .compareTo(new BigInteger(j < version2.length ? version2[j] : "0"))
          < 0) return -1;
      else if (new BigInteger(i < version1.length ? version1[i] : "0")
              .compareTo(new BigInteger(j < version2.length ? version2[j] : "0"))
          > 0) return 1;

      if (i < version1.length) i++;

      if (j < version2.length) j++;
    }

    return 0;
  }

  @Test
  public void minimumParenthesis() {
    assertEquals(1, minimumParenthesis("())"));
    assertEquals(3, minimumParenthesis("((("));
    assertEquals(2, minimumParenthesis(")("));
  }

  public int minimumParenthesis(String A) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < A.length(); i++) {

      if (!stack.isEmpty() && stack.peek() == '(' && A.charAt(i) == ')') {
        stack.pop();
        continue;
      }

      stack.push(A.charAt(i));
    }

    return stack.size();
  }

  @Test
  public void fullJustify() {
    assertArrayEquals(
        new String[] {"This    is    an", "example  of text", "justification.  "},
        fullJustify(
            new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16));

    assertArrayEquals(new String[] {}, fullJustify(new String[] {}, 10));
    assertArrayEquals(
        new String[] {"What must be", "shall be.   "},
        fullJustify(new String[] {"What", "must", "be", "shall", "be."}, 12));
    assertArrayEquals(
        new String[] {
          "glu  muskzjyen  ahxkp  t  djmgzzyh  jzudvh  raji  vmipiz  sg rv mekoexzfmq fsrihvdnt yvnppem gidia fxjlzekp uvdaj ua pzagn bjffryz nkdd osrownxj",
          "fvluvpdj  kkrpr  khp  eef aogrl gqfwfnaen qhujt vabjsmj ji f opihimudj awi jyjlyfavbg tqxupaaknt dvqxay ny ezxsvmqk ncsckq nzlce cxzdirg dnmaxql",
          "bhrgyuyc qtqt yka wkjriv xyfoxfcqzb fttsfs m                                                                                                    "
        },
        fullJustify(
            new String[] {
              "glu",
              "muskzjyen",
              "ahxkp",
              "t",
              "djmgzzyh",
              "jzudvh",
              "raji",
              "vmipiz",
              "sg",
              "rv",
              "mekoexzfmq",
              "fsrihvdnt",
              "yvnppem",
              "gidia",
              "fxjlzekp",
              "uvdaj",
              "ua",
              "pzagn",
              "bjffryz",
              "nkdd",
              "osrownxj",
              "fvluvpdj",
              "kkrpr",
              "khp",
              "eef",
              "aogrl",
              "gqfwfnaen",
              "qhujt",
              "vabjsmj",
              "ji",
              "f",
              "opihimudj",
              "awi",
              "jyjlyfavbg",
              "tqxupaaknt",
              "dvqxay",
              "ny",
              "ezxsvmqk",
              "ncsckq",
              "nzlce",
              "cxzdirg",
              "dnmaxql",
              "bhrgyuyc",
              "qtqt",
              "yka",
              "wkjriv",
              "xyfoxfcqzb",
              "fttsfs",
              "m"
            },
            144));
  }

  public String[] fullJustify(String[] A, int B) {
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0, w; i < A.length; i = w) {
      int l = -1;
      for (w = i; w < A.length && A[w].length() + 1 + l <= B; w++) l += A[w].length() + 1;
      StringBuilder sb = new StringBuilder(A[i]);
      int spaces = 1, extra = 0;
      if (w != i + 1 && w != A.length) {
        spaces = (B - l) / (w - i - 1) + 1;
        extra = (B - l) % (w - i - 1);
      }
      for (int j = i + 1; j < w; j++) {
        sb.append(" ".repeat(Math.max(0, spaces)));
        if (extra-- > 0) sb.append(' ');
        sb.append(A[j]);
      }
      int remaining = B - sb.length();
      while (remaining-- > 0) sb.append(' ');
      list.add(sb.toString());
    }
    return list.toArray(String[]::new);
  }
}
