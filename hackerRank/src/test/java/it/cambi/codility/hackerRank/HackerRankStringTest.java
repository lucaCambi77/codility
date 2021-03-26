package it.cambi.codility.hackerRank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HackerRankStringTest {

  @Test
  public void palindromeIndex() {
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
