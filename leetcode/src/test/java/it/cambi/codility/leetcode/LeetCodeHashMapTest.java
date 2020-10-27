package it.cambi.codility.leetcode;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class LeetCodeHashMapTest {

  @Test
  public void numSplits() {
    assertEquals(2, numSplits("aacaba"));
    assertEquals(1, numSplits("abcd"));
    assertEquals(4, numSplits("aaaaa"));
    assertEquals(2, numSplits("acbadbaada"));
  }

  public int numSplits(String s) {

    Map<Character, Long> freqLeft = new HashMap<>();
    Map<Character, Long> freqRight = new HashMap<>();

    for (char c : s.toCharArray()) {
      freqRight.put(c, freqRight.getOrDefault(c, 0L) + 1);
    }

    int sol = 0;
    for (char c : s.toCharArray()) {
      freqLeft.put(c, freqLeft.getOrDefault(c, 0L) + 1);
      freqRight.put(c, freqRight.getOrDefault(c, 0L) - 1);

      if (freqLeft.values().stream().filter(v -> v > 0).count()
          == freqRight.values().stream().filter(v -> v > 0).count()) sol++;
    }

    return sol;
  }

  @Test
  public void isIsomorphic() {

    assertEquals(true, isIsomorphic("egg", "add"));
    assertEquals(false, isIsomorphic("foo", "bar"));
    assertEquals(true, isIsomorphic("paper", "title"));
    assertEquals(false, isIsomorphic("ab", "aa"));
    assertEquals(true, isIsomorphic("a", "a"));
    assertEquals(true, isIsomorphic("ab", "ab"));
  }

  private boolean isIsomorphic(String s, String t) {

    Map<Character, Character> map = new HashMap<>();
    Map<Character, Character> map1 = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {

      map.putIfAbsent(s.charAt(i), t.charAt(i));
      map1.putIfAbsent(t.charAt(i), s.charAt(i));

      if (map.get(s.charAt(i)) != t.charAt(i) || map1.get(t.charAt(i)) != s.charAt(i)) return false;
    }

    return true;
  }

  @Test
  public void groupThePeople() {
    assertEquals(
        Arrays.asList(Arrays.asList(5), Arrays.asList(0, 1, 2), Arrays.asList(3, 4, 6)),
        groupThePeople(new int[] {3, 3, 3, 3, 3, 1, 3}));
  }

  public List<List<Integer>> groupThePeople(int[] groupSizes) {

    Map<Integer, List<Integer>> groupToPeople = new HashMap<>();

    for (int i = 0; i < groupSizes.length; i++) {

      List<Integer> peopleList = groupToPeople.getOrDefault(groupSizes[i], new ArrayList<>());
      peopleList.add(i);
      groupToPeople.put(groupSizes[i], peopleList);
    }

    List<List<Integer>> result = new ArrayList<>();

    for (Map.Entry<Integer, List<Integer>> integerListEntry : groupToPeople.entrySet()) {

      List<Integer> groupPeople = integerListEntry.getValue();

      List<Integer> group = new ArrayList<>();
      int count = 0;

      for (Integer groupPerson : groupPeople) {
        count++;
        group.add(groupPerson);

        if (count >= integerListEntry.getKey()) {
          result.add(group);
          group = new ArrayList<>();
          count = 0;
        }
      }
    }

    return result;
  }

  @Test
  public void findLeastNumOfUniqueInts() {

    assertEquals(1, findLeastNumOfUniqueInts(new int[] {5, 5, 4}, 1));
    assertEquals(2, findLeastNumOfUniqueInts(new int[] {4, 3, 1, 1, 3, 3, 2}, 3));
  }

  public int findLeastNumOfUniqueInts(int[] arr, int k) {

    Map<Integer, Integer> valueToFreq = new HashMap<>();

    for (int i : arr) {
      valueToFreq.put(i, valueToFreq.getOrDefault(i, 0) + 1);
    }

    LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

    valueToFreq.entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

    int i = 0;

    for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
      i++;

      k -= entry.getValue();

      if (k <= 0) break;
    }

    return k == 0 ? sortedMap.size() - i : sortedMap.size() - i + 1;
  }

  @Test
  public void destCity() {

    assertEquals(
        "Sao Paulo",
        destCity(
            Arrays.stream(
                    new String[][] {
                      {"London", "New York"}, {"New York", "Lima"}, {"Lima", "Sao Paulo"}
                    })
                .map(Arrays::asList)
                .collect(Collectors.toList())));

    assertEquals(
        "A",
        destCity(
            Arrays.stream(new String[][] {{"B", "C"}, {"D", "B"}, {"C", "A"}})
                .map(Arrays::asList)
                .collect(Collectors.toList())));

    assertEquals(
        "A",
        destCity(
            Arrays.stream(new String[][] {{"C", "A"}})
                .map(Arrays::asList)
                .collect(Collectors.toList())));
  }

  public String destCity(List<List<String>> paths) {

    Map<String, String> cityMap = new HashMap<>();

    for (List<String> path : paths) {
      cityMap.put(path.get(0), path.get(1));
    }

    String dest = paths.get(0).get(1);
    String destTtmp = null;

    while (true) {

      destTtmp = cityMap.get(dest);

      if (destTtmp == null) break;

      dest = destTtmp;
    }

    return dest;
  }

  @Test
  public void findErrorNums() {

    assertArrayEquals(new int[] {2, 3}, findErrorNums(new int[] {1, 2, 2, 4}));
    assertArrayEquals(new int[] {2, 1}, findErrorNums(new int[] {2, 2}));
    assertArrayEquals(new int[] {4, 5}, findErrorNums(new int[] {1, 2, 3, 4, 4, 6}));
    assertArrayEquals(new int[] {3, 1}, findErrorNums(new int[] {3, 2, 3, 4, 6, 5}));
    assertArrayEquals(new int[] {3, 2}, findErrorNums(new int[] {3, 3, 1}));
  }

  private int[] findErrorNums(int[] nums) {

    Set<Integer> set = new HashSet<>();
    int repeat = 0;
    int seq = 0;

    for (int num : nums) {
      if (!set.add(num)) {
        repeat = num;
      }
    }

    while (++seq < nums.length + 1) {
      if (!set.contains(seq)) return new int[] {repeat, seq};
    }

    return null;
  }

  @Test
  public void uncommonFromSentences() {
    assertArrayEquals(
        new String[] {"sweet", "sour"},
        uncommonFromSentences("this apple is sweet", "this apple is sour"));
  }

  private String[] uncommonFromSentences(String A, String B) {

    Map<String, Integer> freqMap = new HashMap<>();

    String[] split = A.split(" ");
    String[] split1 = B.split(" ");

    for (String s : split) {
      freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
    }

    for (String s : split1) {
      freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
    }

    return freqMap.entrySet().stream()
        .filter(x -> x.getValue() == 1)
        .map(x -> x.getKey())
        .toArray(String[]::new);
  }

  @Test
  public void findLucky() {
    assertEquals(2, findLucky(new int[] {2, 2, 3, 4}));
    assertEquals(3, findLucky(new int[] {1, 2, 2, 3, 3, 3}));
    assertEquals(-1, findLucky(new int[] {2, 2, 2, 3, 3}));
    assertEquals(-1, findLucky(new int[] {5}));
  }

  private int findLucky(int[] arr) {

    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();

    for (int i : arr) {
      map.put(i, map.getOrDefault(i, 0) + 1);
      set.add(i);
    }

    int sol = -1;

    for (int i : set) {
      if (i == map.get(i)) sol = Math.max(sol, i);
    }

    return sol;
  }

  @Test
  public void uniqueOccurrences() {
    assertEquals(true, uniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));
    assertEquals(false, uniqueOccurrences(new int[] {1, 2}));
    assertEquals(true, uniqueOccurrences(new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}));
  }

  public boolean uniqueOccurrences(int[] arr) {

    Map<Integer, Integer> freq = new HashMap<>();

    for (int i : arr) freq.put(i, freq.getOrDefault(i, 0) + 1);

    HashSet<Integer> seen = new HashSet<>();

    for (Integer integer : freq.values()) {
      if (!seen.add(integer)) return false;
    }

    return true;
  }

  @Test
  public void subdomainVisits() {
    List<String> result =
        Arrays.asList(new String[] {"9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"});
    List<String> sol = subdomainVisits(new String[] {"9001 discuss.leetcode.com"});

    Collections.sort(result);
    Collections.sort(sol);

    assertEquals(result, sol);

    result =
        Arrays.asList(
            new String[] {
              "901 mail.com",
              "50 yahoo.com",
              "900 google.mail.com",
              "5 wiki.org",
              "5 org",
              "1 intel.mail.com",
              "951 com"
            });
    sol =
        subdomainVisits(
            new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});

    Collections.sort(result);
    Collections.sort(sol);

    assertEquals(result, sol);

    result =
        Arrays.asList(
            new String[] {
              "408 zfz.network",
              "1789 zqy.ca",
              "622 cwn.net",
              "6629 vdu.bfo.team",
              "817 htq.co",
              "3496 ytn.com",
              "6122 nqk.srp.org",
              "4296 jre.us",
              "8176 gqs.ca",
              "7292 zbl.ksc.net",
              "4126 lir.ajl.team",
              "84 lmm.network",
              "622 wmt.cwn.net",
              "2572 qay.network",
              "7089 ske.yls.com",
              "2979 bhp.org",
              "2740 xhe.org",
              "4439 gtu.us",
              "1110 kdd.znq.us",
              "3314 hkt.net",
              "5041 cdf.hwu.us",
              "4198 okv.co",
              "6920 tlc.dfa.us",
              "654 yjt.co",
              "5016 zwv.gqi.co",
              "59671 team",
              "5705 mqa.wsv.net",
              "9103 vbo.org",
              "2406 hkb.ocf.co",
              "7292 ksc.net",
              "8928 arz.org",
              "8342 srp.team",
              "2447 ksc.com",
              "32 ulz.com",
              "3855 yjt.network",
              "9530 omu.network",
              "2329 vva.qay.network",
              "2531 ser.com",
              "1781 lbk.ksc.co",
              "7476 cze.yvr.net",
              "1873 gjf.ca",
              "3950 xuf.network",
              "1789 xcf.zqy.ca",
              "3055 jkx.com",
              "81314 network",
              "1137 qzi.co",
              "3262 mor.ixi.us",
              "7344 czb.com",
              "3090 yjt.us",
              "6890 bfo.network",
              "3875 ato.network",
              "68516 net",
              "9374 jep.ato.co",
              "9523 cgl.network",
              "7464 jpd.fff.co",
              "2715 okv.us",
              "3528 arz.us",
              "9374 ato.co",
              "7042 btv.com",
              "64390 org",
              "2862 sci.net",
              "9259 bfo.net",
              "9172 zfz.net",
              "3055 fdy.jkx.com",
              "2160 hsj.epf.org",
              "5907 zfz.us",
              "7392 uyy.net",
              "9897 lra.uyy.org",
              "58413 com",
              "2406 ocf.co",
              "6129 hkt.com",
              "3377 jkx.network",
              "12367 bfo.team",
              "59758 co",
              "36170 ca",
              "2160 epf.org",
              "4602 weq.buf.team",
              "6920 dfa.us",
              "1879 lmm.ca",
              "3314 ytd.hkt.net",
              "2862 coh.sci.net",
              "2913 ret.ych.ca",
              "817 sgp.htq.co",
              "9531 hoh.co",
              "3609 bhp.team",
              "8274 ulz.co",
              "9846 fuw.org",
              "1501 ara.ca",
              "2913 ych.ca",
              "5738 lar.bfo.team",
              "8557 lfn.net",
              "3449 tfm.us",
              "654 yls.yjt.co",
              "1781 ksc.co",
              "3215 hoh.network",
              "2777 mkw.co",
              "9897 uyy.org",
              "767 irh.epf.us",
              "2444 gxz.team",
              "4806 xss.dfa.co",
              "9372 hci.jia.network",
              "9581 gqi.team",
              "5016 gqi.co",
              "4975 okv.com",
              "4296 xtb.jre.us",
              "5705 wsv.net",
              "9372 jia.network",
              "8544 bhp.com",
              "1873 kse.gjf.ca",
              "7464 fff.co",
              "3624 epf.network",
              "9219 tfm.ca",
              "1387 hju.gbq.org",
              "4806 dfa.co",
              "3262 ixi.us",
              "7193 fzx.ca",
              "2965 gxj.org",
              "8557 uai.lfn.net",
              "51516 us",
              "8176 ujm.gqs.ca",
              "4602 buf.team",
              "4126 ajl.team",
              "1110 znq.us",
              "2444 vks.gxz.team",
              "6865 thk.net",
              "1523 suq.bhp.co",
              "3111 gdw.team",
              "4521 weh.bfo.us",
              "7476 yvr.net",
              "8167 jre.team",
              "5041 hwu.us",
              "4521 bfo.us",
              "3322 xhe.team",
              "2777 nak.mkw.co",
              "8263 cwn.org",
              "4681 lwf.ytn.network",
              "654 yaw.lmm.ca",
              "8167 ahm.jre.team",
              "3215 bll.hoh.network",
              "4681 ytn.network",
              "767 epf.us",
              "6122 srp.org",
              "3528 jyx.arz.us",
              "408 tdt.zfz.network",
              "8390 zqk.network",
              "9523 wyv.cgl.network",
              "3624 vmv.epf.network",
              "3090 ycc.yjt.us",
              "8544 xrj.bhp.com",
              "1523 bhp.co",
              "4198 xfs.okv.co",
              "7968 gjf.network",
              "3875 brk.ato.network",
              "1387 gbq.org",
              "2471 czb.us",
              "1627 bhp.ca",
              "1137 ujs.qzi.co",
              "5729 srp.com",
              "7089 yls.com",
              "7193 xth.fzx.ca",
              "2715 ubt.okv.us",
              "2531 mce.ser.com"
            });
    sol =
        subdomainVisits(
            new String[] {
              "2777 nak.mkw.co",
              "654 yaw.lmm.ca",
              "3528 jyx.arz.us",
              "3215 bll.hoh.network",
              "408 tdt.zfz.network",
              "3322 xhe.team",
              "8342 srp.team",
              "9259 bfo.net",
              "3875 brk.ato.network",
              "2531 mce.ser.com",
              "2471 czb.us",
              "4806 xss.dfa.co",
              "654 yls.yjt.co",
              "767 irh.epf.us",
              "1501 ara.ca",
              "243 qay.network",
              "9103 vbo.org",
              "6890 bfo.network",
              "4296 xtb.jre.us",
              "2329 vva.qay.network",
              "9846 fuw.org",
              "4681 lwf.ytn.network",
              "1781 lbk.ksc.co",
              "7464 jpd.fff.co",
              "2740 xhe.org",
              "4602 weq.buf.team",
              "3055 fdy.jkx.com",
              "5705 mqa.wsv.net",
              "6629 vdu.bfo.team",
              "9897 lra.uyy.org",
              "8167 ahm.jre.team",
              "9374 jep.ato.co",
              "3624 vmv.epf.network",
              "6865 thk.net",
              "6920 tlc.dfa.us",
              "9372 hci.jia.network",
              "7968 gjf.network",
              "7292 zbl.ksc.net",
              "2862 coh.sci.net",
              "3855 yjt.network",
              "1387 hju.gbq.org",
              "817 sgp.htq.co",
              "2406 hkb.ocf.co",
              "622 wmt.cwn.net",
              "9172 zfz.net",
              "1523 suq.bhp.co",
              "9581 gqi.team",
              "2160 hsj.epf.org",
              "32 ulz.com",
              "1225 lmm.ca",
              "1137 ujs.qzi.co",
              "5041 cdf.hwu.us",
              "4126 lir.ajl.team",
              "3111 gdw.team",
              "8928 arz.org",
              "9531 hoh.co",
              "7344 czb.com",
              "2715 ubt.okv.us",
              "1110 kdd.znq.us",
              "5729 srp.com",
              "6122 nqk.srp.org",
              "7193 xth.fzx.ca",
              "3496 ytn.com",
              "3950 xuf.network",
              "4521 weh.bfo.us",
              "3262 mor.ixi.us",
              "4975 okv.com",
              "7089 ske.yls.com",
              "4198 xfs.okv.co",
              "2444 vks.gxz.team",
              "1789 xcf.zqy.ca",
              "7392 uyy.net",
              "3449 tfm.us",
              "5907 zfz.us",
              "9530 omu.network",
              "3314 ytd.hkt.net",
              "9523 wyv.cgl.network",
              "4439 gtu.us",
              "8390 zqk.network",
              "1627 bhp.ca",
              "3609 bhp.team",
              "8557 uai.lfn.net",
              "2913 ret.ych.ca",
              "2447 ksc.com",
              "7476 cze.yvr.net",
              "8544 xrj.bhp.com",
              "6129 hkt.com",
              "8274 ulz.co",
              "9219 tfm.ca",
              "5016 zwv.gqi.co",
              "5738 lar.bfo.team",
              "3377 jkx.network",
              "2979 bhp.org",
              "8176 ujm.gqs.ca",
              "84 lmm.network",
              "3090 ycc.yjt.us",
              "7042 btv.com",
              "2965 gxj.org",
              "8263 cwn.org",
              "1873 kse.gjf.ca"
            });

    Collections.sort(result);
    Collections.sort(sol);

    assertEquals(result, sol);
  }

  private List<String> subdomainVisits(String[] cpdomains) {

    Map<String, Integer> domainMap = new HashMap<>();

    for (String cpdomain : cpdomains) {

      String[] split = cpdomain.split("\\s");

      String[] split1 = split[1].split("\\.");

      if (split1.length == 2) {
        domainMap.put(
            split1[0] + "." + split1[1],
            domainMap.getOrDefault(split1[0] + "." + split1[1], 0) + Integer.valueOf(split[0]));
        domainMap.put(split1[1], domainMap.getOrDefault(split1[1], 0) + Integer.valueOf(split[0]));
      } else {

        domainMap.put(
            split1[0] + "." + split1[1] + "." + split1[2],
            domainMap.getOrDefault(split1[0] + "." + split1[1] + "." + split1[2], 0)
                + Integer.valueOf(split[0]));
        domainMap.put(
            split1[1] + "." + split1[2],
            domainMap.getOrDefault(split1[1] + "." + split1[2], 0) + Integer.valueOf(split[0]));
        domainMap.put(split1[2], domainMap.getOrDefault(split1[2], 0) + Integer.valueOf(split[0]));
      }
    }

    List<String> result = new ArrayList<>();

    for (Map.Entry<String, Integer> s : domainMap.entrySet())
      result.add(s.getValue() + " " + s.getKey());

    return result;
  }
}
