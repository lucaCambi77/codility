package it.cambi.codility.hackerRank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HackerRankStringTest {

  @Test
  void timeConversion() {
    // Write your code here
    assertEquals("19:05:45", timeConversion("07:05:45PM"));
    assertEquals("12:00:01", timeConversion("12:00:01PM"));
    assertEquals("00:00:01", timeConversion("12:00:01AM"));
    assertEquals("13:00:01", timeConversion("01:00:01PM"));
  }

  String timeConversion(String s) {
    boolean am = s.contains("AM");

    String[] split = s.split("AM|PM");

    String[] time = split[0].split(":");

    if (am) {
      return time[0].equals("12")
          ? "00" + ":" + time[1] + ":" + time[2]
          : time[0] + ":" + time[1] + ":" + time[2];
    } else {
      return time[0].equals("12")
          ? time[0] + ":" + time[1] + ":" + time[2]
          : (Integer.parseInt(time[0]) + 12) + ":" + time[1] + ":" + time[2];
    }
  }

  @Test
  void palindromeIndex() {
    assertEquals(3, palindromeIndex("aaab"));
    assertEquals(0, palindromeIndex("baa"));
    assertEquals(-1, palindromeIndex("aaa"));
    assertEquals(3, palindromeIndex("bcbc"));
    assertEquals(1, palindromeIndex("quyjjdcgsvvsgcdjjyq"));
    assertEquals(
        33,
        palindromeIndex("fgnfnidynhxebxxxfmxixhsruldhsaobhlcggchboashdlurshxixmfxxxbexhnydinfngf"));
    assertEquals(
        23,
        palindromeIndex(
            "bsyhvwfuesumsehmytqioswvpcbxyolapfywdxeacyuruybhbwxjmrrmjxwbhbyuruycaexdwyfpaloyxbcpwsoiqtymhesmuseufwvhysb"));
    assertEquals(
        25,
        palindromeIndex(
            "fvyqxqxynewuebtcuqdwyetyqqisappmunmnldmkttkmdlnmnumppasiqyteywdquctbeuwenyxqxqyvf"));
    assertEquals(
        44,
        palindromeIndex(
            "mmbiefhflbeckaecprwfgmqlydfroxrblulpasumubqhhbvlqpixvvxipqlvbhqbumusaplulbrxorfdylqmgfwrpceakceblfhfeibmm"));
    assertEquals(
        20, palindromeIndex("tpqknkmbgasitnwqrqasvolmevkasccsakvemlosaqrqwntisagbmknkqpt"));
    assertEquals(-1, palindromeIndex("lhrxvssvxrhl"));
    assertEquals(
        14,
        palindromeIndex(
            "prcoitfiptvcxrvoalqmfpnqyhrubxspplrftomfehbbhefmotfrlppsxburhyqnpfmqlaorxcvtpiftiocrp"));
    assertEquals(-1, palindromeIndex("kjowoemiduaaxasnqghxbxkiccikxbxhgqnsaxaaudimeowojk"));
    assertEquals(44, palindromeIndex("hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh"));
    assertEquals(8, palindromeIndex("hgygsvlfwcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcflvsgygh"));
  }

  private int palindromeIndex(String s) {

    int middle = (s.length() & 1) == 0 ? (s.length() / 2) - 1 : s.length() / 2;

    for (int i = s.length() - 1; i > middle; i--) {

      char c = s.charAt(s.length() - 1 - i);

      if (s.charAt(i) != c) {
        char c1 = s.charAt(s.length() - i);

        if (s.charAt(i - 1) == c && s.charAt(i - 2) == c1) return i;

        if (c1 == s.charAt(i)) return s.length() - 1 - i;
      }
    }

    return -1;
  }
}
