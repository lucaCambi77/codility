package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterviewBitStringTest {

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
        String s = "";

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
                    s += "M";
                } else if (y == 2000) {
                    s += "MM";
                } else if (y == 3000) {
                    s += "MMM";
                }

            } else if (y >= 100 && y < 1000) {
                if (y == 900) {
                    s += "CM";
                } else if (y == 800) {
                    s += "DCCC";
                } else if (y == 700) {
                    s += "DCC";
                } else if (y == 600) {
                    s += "DC";
                } else if (y == 500) {
                    s += "D";
                } else if (y == 400) {
                    s += "CD";
                } else if (y == 300) {
                    s += "CCC";
                } else if (y == 200) {
                    s += "CC";
                } else if (y == 100) {
                    s += "C";
                }
            } else if (y >= 10 && y < 100) {
                if (y == 90) {
                    s += "XC";
                } else if (y == 80) {
                    s += "LXXX";
                } else if (y == 70) {
                    s += "LXX";
                } else if (y == 60) {
                    s += "LX";
                } else if (y == 50) {
                    s += "L";
                } else if (y == 40) {
                    s += "XL";
                } else if (y == 30) {
                    s += "XXX";
                } else if (y == 20) {
                    s += "XX";
                } else if (y == 10) {
                    s += "X";
                }
            } else if (y >= 1 && y < 10) {
                if (y == 9) {
                    s += "IX";
                } else if (y == 8) {
                    s += "VIII";
                } else if (y == 7) {
                    s += "VII";
                } else if (y == 6) {
                    s += "VI";
                } else if (y == 5) {
                    s += "V";
                } else if (y == 4) {
                    s += "IV";
                } else if (y == 3) {
                    s += "III";
                } else if (y == 2) {
                    s += "II";
                } else if (y == 1) {
                    s += "I";
                }
            }
        }
        return s;
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

            if (new BigInteger(i < version1.length ? version1[i] : "0").compareTo(new BigInteger(j < version2.length ? version2[j] : "0")) == -1)
                return -1;
            else if (new BigInteger(i < version1.length ? version1[i] : "0").compareTo(new BigInteger(j < version2.length ? version2[j] : "0")) == 1)
                return 1;

            if (i < version1.length)
                i++;

            if (j < version2.length)
                j++;
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
        assertArrayEquals(new String[]{"This    is    an",
                "example  of text",
                "justification.  "}, fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));

        assertArrayEquals(new String[]{}, fullJustify(new String[]{}, 10));
        assertArrayEquals(new String[]{"What must be", "shall be.   "}, fullJustify(new String[]{"What", "must", "be", "shall", "be."}, 12));
        assertArrayEquals(new String[]{"glu  muskzjyen  ahxkp  t  djmgzzyh  jzudvh  raji  vmipiz  sg rv mekoexzfmq fsrihvdnt yvnppem gidia fxjlzekp uvdaj ua pzagn bjffryz nkdd osrownxj"
                        , "fvluvpdj  kkrpr  khp  eef aogrl gqfwfnaen qhujt vabjsmj ji f opihimudj awi jyjlyfavbg tqxupaaknt dvqxay ny ezxsvmqk ncsckq nzlce cxzdirg dnmaxql", "bhrgyuyc qtqt yka wkjriv xyfoxfcqzb fttsfs m                                                                                                    "},
                fullJustify(new String[]{"glu", "muskzjyen", "ahxkp", "t", "djmgzzyh", "jzudvh", "raji", "vmipiz", "sg", "rv", "mekoexzfmq", "fsrihvdnt", "yvnppem", "gidia", "fxjlzekp", "uvdaj", "ua", "pzagn", "bjffryz", "nkdd", "osrownxj", "fvluvpdj", "kkrpr", "khp", "eef", "aogrl", "gqfwfnaen", "qhujt", "vabjsmj", "ji", "f", "opihimudj", "awi", "jyjlyfavbg", "tqxupaaknt", "dvqxay", "ny", "ezxsvmqk", "ncsckq", "nzlce", "cxzdirg", "dnmaxql", "bhrgyuyc", "qtqt", "yka", "wkjriv", "xyfoxfcqzb", "fttsfs", "m"}, 144));

    }

    public String[] fullJustify(String[] A, int B) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0, w; i < A.length; i = w) {
            int l = -1;
            for (w = i; w < A.length && A[w].length() + 1 + l <= B; w++)
                l += A[w].length() + 1;
            StringBuilder sb = new StringBuilder(A[i]);
            int spaces = 1, extra = 0;
            if (w != i + 1 && w != A.length) {
                spaces = (B - l) / (w - i - 1) + 1;
                extra = (B - l) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = 0; s < spaces; s++)
                    sb.append(' ');
                if (extra-- > 0)
                    sb.append(' ');
                sb.append(A[j]);
            }
            int remaining = B - sb.length();
            while (remaining-- > 0) sb.append(' ');
            list.add(sb.toString());
        }
        return list.stream().toArray(String[]::new);
    }
}

