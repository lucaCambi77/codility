/** */
package it.cambi.codility.leetcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cambi.codility.model.Array;
import javafx.util.Pair;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/** @author luca */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeArrayTest {

  private ObjectMapper mapper = new ObjectMapper();

  long k;

  private int[] nums;

  private String[] daysOfWeek =
      new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

  @Test
  public void reorderLogFiles() {
    assertArrayEquals(
        new String[] {
          "let1 art can", "let3 art zero", "let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"
        },
        reorderLogFiles(
            new String[] {
              "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"
            }));

    assertArrayEquals(
        new String[] {
          "a2 act car", "g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"
        },
        reorderLogFiles(
            new String[] {
              "a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo", "a2 act car"
            }));

    assertArrayEquals(
        new String[] {"t kvr", "7 so", "r 3 1", "i 403", "t 54"},
        reorderLogFiles(new String[] {"t kvr", "r 3 1", "i 403", "7 so", "t 54"}));
  }

  private String[] reorderLogFiles(String[] logs) {

    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    Arrays.sort(
        logs,
        new Comparator<String>() {
          @Override
          public int compare(String o1, String o2) {
            String[] split = o1.split(" ");
            String[] split1 = o2.split(" ");

            if (!pattern.matcher(split[1]).matches() && !pattern.matcher(split1[1]).matches()) {
              String patho1 = o1.substring(split[0].length() + 1);
              String patho2 = o2.substring(split1[0].length() + 1);

              if (patho1.compareTo(patho2) == 0) return split[0].compareTo(split1[0]);

              return patho1.compareTo(patho2);
            }

            if (pattern.matcher(split[1]).matches() && pattern.matcher(split1[1]).matches())
              return 0;

            if (!pattern.matcher(split[1]).matches()) return -1;

            return 1;
          }
        });

    return logs;
  }

  @Test
  public void findDuplicates() {

    assertArrayEquals(
        new int[] {2, 3},
        findDuplicates(new int[] {4, 3, 2, 7, 8, 2, 3, 1}).stream().mapToInt(i -> i).toArray());
  }

  private List<Integer> findDuplicates(int[] nums) {

    Set<Integer> sol = new HashSet<>();
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (!sol.add(nums[i])) list.add(nums[i]);
    }

    return list;
  }

  @Test
  public void canBeEqual() {
    assertTrue(canBeEqual(new int[] {1, 2, 3, 4}, new int[] {2, 4, 1, 3}));
  }

  private boolean canBeEqual(int[] target, int[] arr) {
    Arrays.sort(target);
    Arrays.sort(arr);

    return Arrays.equals(target, arr);
  }

  @Test
  public void luckyNumbers() {

    assertEquals(
        Arrays.asList(
            new LinkedList<Integer>() {
              {
                add(15);
              }
            }),
        Arrays.asList(luckyNumbers(new int[][] {{3, 7, 8}, {9, 11, 13}, {15, 16, 17}})));

    assertEquals(
        Arrays.asList(
            new LinkedList<Integer>() {
              {
                add(12);
              }
            }),
        Arrays.asList(luckyNumbers(new int[][] {{1, 10, 4, 2}, {9, 3, 8, 7}, {15, 16, 17, 12}})));
  }

  private List<Integer> luckyNumbers(int[][] matrix) {

    int length = matrix.length;
    int rowLentgh = matrix[0].length;

    int[] maxInColumns = new int[matrix[0].length];
    int[] minInRows = new int[matrix.length];

    for (int i = 0; i < length; i++) {

      int j = 0;
      int minInRow = Integer.MAX_VALUE;

      while (j < rowLentgh) {
        minInRow = Math.min(minInRow, matrix[i][j++]);
      }

      minInRows[i] = minInRow;
    }

    for (int i = 0; i < rowLentgh; i++) {

      int j = 0;
      int maxInCol = 0;

      while (j < length) {
        maxInCol = Math.max(maxInCol, matrix[j++][i]);
      }

      maxInColumns[i] = maxInCol;
    }

    LinkedList<Integer> sol = new LinkedList<>();

    int col = 0;

    while (col < rowLentgh) {

      int row = 0;

      while (row < length) {

        if (maxInColumns[col] - minInRows[row] == 0) sol.add(maxInColumns[col]);

        row++;
      }
      col++;
    }

    return sol;
  }

  @Test
  public void shuffle() {
    assertArrayEquals(new int[] {2, 3, 5, 4, 1, 7}, shuffle(new int[] {2, 5, 1, 3, 4, 7}, 3));
    assertArrayEquals(
        new int[] {1, 4, 2, 3, 3, 2, 4, 1}, shuffle(new int[] {1, 2, 3, 4, 4, 3, 2, 1}, 4));
    assertArrayEquals(new int[] {1, 2, 1, 2}, shuffle(new int[] {1, 1, 2, 2}, 2));
  }

  @Test
  private int[] shuffle(int[] nums, int n) {

    if (nums.length == 0) return new int[] {};

    int[] clone = nums.clone();

    int start = 0;
    int middle = n;
    int pointer = 0;

    while (start < n) {

      nums[pointer++] = clone[start++];
      nums[pointer++] = clone[middle++];
    }

    return nums;
  }

  @Test
  public void numEquivDominoPairs() {
    assertEquals(1, numEquivDominoPairs(new int[][] {{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
    assertEquals(3, numEquivDominoPairs(new int[][] {{1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}}));
  }

  private int numEquivDominoPairs(int[][] dominoes) {

    Map<Integer, Map<Integer, Integer>> dominoesMap = new HashMap<Integer, Map<Integer, Integer>>();

    int count = 0;

    for (int[] dominoe : dominoes) {}

    return count;
  }

  @Test
  public void isAlienSorted() {
    assertEquals(
        true, isAlienSorted(new String[] {"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    assertEquals(
        false, isAlienSorted(new String[] {"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    assertEquals(false, isAlienSorted(new String[] {"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
  }

  private boolean isAlienSorted(String[] words, String order) {

    int i = 0;

    while (i + 1 < words.length) {
      String word1 = words[i];
      String word2 = words[i + 1];

      int length = Math.min(word1.length(), word2.length());

      int j = 0;

      while (j < length) {
        if (order.indexOf(word1.charAt(j)) > order.indexOf(word2.charAt(j))) return false;
        else if (order.indexOf(word1.charAt(j)) < order.indexOf(word2.charAt(j))) break;

        j++;
      }

      if (j == length && word1.length() > word2.length()) return false;

      i++;
    }

    return true;
  }

  @Test
  public void shortestCompletingWord() {
    assertEquals(
        "steps",
        shortestCompletingWord("1s3 PSt", new String[] {"step", "steps", "stripe", "stepple"}));
    assertEquals(
        "pest", shortestCompletingWord("1s3 456", new String[] {"looks", "pest", "stew", "show"}));
  }

  public String shortestCompletingWord(String licensePlate, String[] words) {

    String word = null;
    Map<Character, Integer> map = new HashMap<>();
    int licensPlateWordLength = 0;

    for (char s : licensePlate.toCharArray()) {
      if ((s >= 65 && s <= 90) || (s >= 97 && s <= 122)) {
        licensPlateWordLength++;
        char lower = Character.toLowerCase(s);
        map.put(lower, map.getOrDefault(lower, 0) + 1);
      }
    }

    Set<Character> seen = new HashSet<>();
    int countChars;

    for (String s : words) {
      countChars = 0;
      for (char c : s.toCharArray()) {
        if (seen.add(c)) {
          long count = s.chars().filter(ch -> ch == c).count();

          int mapCount = map.getOrDefault(c, 0);

          if (mapCount != 0 && mapCount <= count) countChars += mapCount;
        }
      }

      if (countChars >= licensPlateWordLength)
        if (word == null) {
          word = s;
        } else {
          if (s.length() < word.length()) word = s;
        }

      seen = new HashSet<>();
    }

    return word;
  }

  @Test
  public void maxNumberOfBalloons() {
    assertEquals(1, maxNumberOfBalloons("nlaebolko"));
    assertEquals(2, maxNumberOfBalloons("loonbalxballpoon"));
    assertEquals(
        98,
        maxNumberOfBalloons(
            "rrnlnfevwkvhqzathacmhyhonsvcwmmmehcgchzfjvfoxbnagtxwnwcjcbmbgexqhlefjyrijwjolebtodxtapgyrzmgqzexvugtermrktyjbaicmskxmdopmtoxayydqjjogxmmmpvfiajknjfsikgrwsejzgnbsblsjkkcbaunqthiaetvxlylylzjqkvgysxfoltcmxwrtwpkvhofoaxdugfnsuhepfxudwllyiyrszrpwkwlvpsspvgcimcrngfllsjjdppihhczogfwegibuzzacojtruvozbqwikgdjeqxyarjwaawzictwpdruufifgbqzatmkkdnjajnklnzfdxcsjpmebjqpuqqamoexnnqxccngljzhnhbroyoaphgmksefhizibzpgehvtujmjdhskvpqicxbziqifjqqkaowileochjqhrccajnthqeqgwazedgehdtkwchxjjzcyjimrkykcvkfbzqiusgkxdilnfislphmeliqspvhuichuusqxnutiyxvdtgfnnrrkjwjmkjqjradnocssmfefgfgtzpbnlzxardrrrttnnqttnsowwrqswyqxhsxtlnfxogulwqhkowniohtznlyfjqlzvowcvqxynjpiyiggnxgtmyhjcbehxxsgltojszqyfpnsiybksmozwsqfxpvgeyoyeccxqlcziikavapxyjcftxhfimabmquzkyxnnablhpikwbwwmpjdtmukmuvcfkraqcbopufsaigskwgpjdubbympilgaojuvzgapairttwzuexbsigzcgerblgdgfwkiiqvokbmbtdzkakkeahkgjuyepvtbwqgerztvgunzhgitwottmejilmwyzwczjoxckyjtcbfsryofffaugsketifypahgqgmeypsyjvftqynbzhrobiwouuykyczskcdedidptykluykypnsfuudqgpqzwextbbumiuymngczkfrowkzxxtggqlwlaohfkgfmbyyaboapwhhopewnchlmvifpdkeovpjyugautultdbbxkjxqbcamrpqhndfeybwoatrjwvroakmqapknzzumapytdsdhhlstojlnlvpqjxlpbwfkdupgxzajyjrutzeljnmyxkcdrcjsxfmbqhzblnlqgsqzqjtiaelfkuoyjcjdlpvajkkgyuabgiwlybwmaqdtireukwieifvpzptnebjtwcvmhltkotrvkxcbbsehkdkiegzzbtszfdslqnecluxilqqgqwotfqqoogzzsyfdaujymrjfeoiqpzdeispqsnvlocqdyjcthtxlerjgowzfwsqgourkdhyfaaexjwnfppvrwhdbtsxqqqlrzvkergkxiqzlwdfomyreafirkaycmvjmwnnaqexoewwymvgsrajurzfiohzsbrehhnfghanczevwavsjjslwmnljlmixepwiwalqatgwfzzqhqvrrrhxleuzffgjuihnlbeukyapalwlfjtpumiexkwnvdxiikfajsbxcaywwjjqmgtzrihaoebjduvsmyfvjcthmbjjcausloupnqwjcncosrinvnspqwzlsrivpbpmgsoevunzthofjxspcgordbpudedcfdbbqvymtwujzdtcvigwuofoowqusrsavwkoyabsvllwpqidbpelffmrzfwdhaypxzwofssgkcqobnuomcejbuolmashglsiixcogolmgcmtauauhupkcqdhwesdmpnelcpvnyipakzlsbrpjppayzsremllqnmizvqcphkyuvwrqphdmgiwousanjuazlocddvgduoqmvfovidicmjufaytymcvoonvndbuuljgdvalsnypemzbwovgclcdfwcjptsbelifrqocsuwiwsyzrcxwuweiehtiegogvawkjogwtftbwabpcufuepabxxiqngntudhhuxrvhmhunepamdtfavtuajmrfvgirquwginxtjcmxgmstmhkqncpkdicemhcofqcbceiukstmjzsavwuhgpwbsymupyldwtsbvdjdevvgtddvqqcibnbseqgiaputwgapmovminivrbzlerjwsiofgiihyooqyjchcctvjfebhpvwncqhbleluycybaqlxikorlkqxwzmtrbgpwiqqormjftiarumgxeasqeusnewjqedzhiibbfurgbuwmxfycaevtijtttcbzvgumgiimwzjzpwhuqqwotjrlayrqwcwltiivumafhucrnzjqddvvtrxjsupfxlrohxmytridseyiajntpjsdrbdhdrtnackpytfuzkeiwgukwhvtvehvrdldfugtlwksywnuygichqnjbsmsupsowajduoukduoulagskxvxskewsyhsgakjyqeconciayfwqwdwdnilmllyuwqmatjzxyuhvujrcwkdybdmszzoxwzxojimlfqldxaiqgvziifuobnhonlafoulxgcorcclsxkrwrxinsadpgfmunwjdwgsiepbclggbqpxfjdxqseqidtgrqkixxwmblwokkupjqavvniwjyfuobcbhqfymrayevkteoaturjyvpbjaawgesxnwdvshiiijylclbqwjavftdmrrdsyhqcjweptywnqpcnzucqnzyyptaosrhvfzdomojkoeyepbpbfupkrnyzftjwpdgfqmwhjnhatsedirwzcpeppusaekjsbevlrzyxqtfgcnpszwxhgaeincudnfmcqawcnfkawjrguhitkqtxcxcvojgudfsrezcpfcntnezvskggzhisksxsehgzqxrrqtdlhjhgrblgnbfulmfawtfwnsrjixrjbwwxkvppjdtydgrsnmcpwcudxoiunilylpivbicvxwijrcxhflkznmskrbnighehsyrrzxazwyyvxgffmheubmogkdqtbeyoffgpuathllbiublcvkhzhogbspoogzobbbkylptbwxrbnbhevugkufrbrsgijcvylqeoucsomarwklmmztirevahocqoewrxojvfrakuevheeioirhgttufsxwfymwgxebtazonolntbhfwwgpmoluhnnzcwfhjoxthbuihecsxlfnexaghbaiawxfcsrznockvfoaemmizfltiebkoygchtspwcmijtjvrzljaovsbprmcsgwkqpvjtucemnpxxzzhpxvydfxsoyypecppdwwusbrgysxdaccafafoslqfcohtlgqcuojqwilxbsxrogyfcsqbxdqvdvpnjdeqmrcriczwviehftrhuwrguzrhukemyzeeworelrjwosskahsiazkpwvgscbihgnqtcoicapcsbvngvqgkqgvfuyhcoabpgrdqthxbalglvvrxifdzocgnweutsgrhmiuhoitrmccscophlcmarepzlisqdzidhocliidjikgzsmbjasukprfvlpkgryoyjtmuknjcmphkzhipocykitztqihhsjmxbrwefwgbdkdillzkosondaevcfyeikdfhgnughasibklabfwxkpxjpfwbjlczyusecvbyzlnxbhodtqpacvdgzzffxegivtemuswsmgiieldivzbildwucddnizmyofqtymmikpkzwohivqnipnwrdjatiyvlnrtwkrcrifmxvofolrbbhvyigrlsldqgbivahebsttymezbixrheczduzsqidjxfqorcxqssmcpnphnprpwlkyofnamfmezxoivrtxoxqralelxegltcpsleogdotrdgwxaspyghklhklbxsvfwjkbdjikdtzyhoufeqnqcmetpvvilyakjxmsoescrvivbslvdpunwhuylsgswgvfxfptnrskmsyiezttvwjwilhqdnpvayggvwepizpmjwhsellrypgtujbmqmpnngkwkgwrasmnomqhukusjadlpgpbhpnefhjippzhcdasuhauthuqbvvmndggmlckpjuuopsyrwtwkdtkwplrgfrgunfogevjqxqsoryjwmahmqhlgbyfqfgcfanjkerbirdilapihcrquviryewnphzwhsprelcnlgsczxonivmzknffspyeodsyxtkjcnljwfldvqwqndjcpxzxhjnsjuoedkrbmcsyyfhhjbvekmnfpxwchganhcmxbtoaofbbfvfewpzqrpgindhcqutdkyrtufdnsxxhachxvvfokczlrcdvygxusslaxllcspnbrtblgjlzmkxfpgeuepetuzesccdskwfunxaehfxsqopqjplukuivautozxtlujwxoijihzueabboyxvbtaangqzokdzwhbxodlfmryhikccbdprouslrgktmdjimvwshpbzvdmbtyyggnsamppcasbmdxlbsoosftfmamcmpvaojyfbwajwkodaokljngsknpklkticblmghnihdmezdyvriohgvvonutlmcfavvqndjnsskhamflpipuvusyqyzwlfrlestfppascaszytzagpcrwgeswhzuquurnytvrzsoyuqvjpwbmyksxueizewjmxnncniuddtmkpqhrzonwahjgmjxwihyalelbnwplqqhdgyrhcrnpdslxbdjhlmbvbvkwhjivspoejwtviwujcnuqjkskfpdnefruoyziynqcivjvlvaojhpcncbspuatgsyizklwfdbhwzbrbmxcyfxiwuwsorckrtdazaorauxmecsoywytrhuwqrobxndyfvwqukccpidvmecwuhdnqfsahqoxtswfdzzaystjmikdumrznihaakatobdrmjmahgefjomhywqfuiquafulpybnrvcfptttjaohipdrgbtvdzgxsakfvvoumdlalajlpusskjwlweufcjzpzhdcssqlkkrhaonmzxqtulrpqhubbgbagwyzpizbjegvjtqbkaiqididqvnbsknxegvkcfxdlljqs"));
  }

  private int maxNumberOfBalloons(String text) {

    String alphaBet = "abcdefghijklmnopqrstuvwxyz";
    int[] chars = new int[26];

    for (char c : text.toCharArray()) {
      int index = alphaBet.indexOf(c);
      chars[index] += 1;
    }

    int b = alphaBet.indexOf('b');
    int a = alphaBet.indexOf('a');
    int l = alphaBet.indexOf('l');
    int o = alphaBet.indexOf('o');
    int n = alphaBet.indexOf('n');

    int count = 0;

    while (chars[b] >= 1 && chars[a] >= 1 && chars[l] >= 2 && chars[o] >= 2 && chars[n] >= 1) {
      count++;
      chars[b] = --chars[b];
      chars[a] = --chars[a];
      chars[l] = --chars[l];
      chars[l] = --chars[l];
      chars[o] = --chars[o];
      chars[o] = --chars[o];
      chars[n] = --chars[n];
    }

    return count;
  }

  @Test
  public void floodFill() {

    assertArrayEquals(
        new int[][] {{0, 0, 0}, {0, 1, 1}}, floodFill(new int[][] {{0, 0, 0}, {0, 1, 1}}, 1, 1, 1));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 2}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 2, 2, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 0, 0, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 1}, {1, 1, 2}, {1, 0, 1}},
        floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 2, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 2, 1}}, floodFill(new int[][] {{0, 0, 0}, {0, 0, 1}}, 1, 0, 2));

    assertArrayEquals(
        new int[][] {{2, 2, 2}, {2, 1, 2}}, floodFill(new int[][] {{0, 0, 0}, {0, 1, 0}}, 0, 0, 2));

    assertArrayEquals(
        new int[][] {{1, 1, 0}, {1, 1, 1}}, floodFill(new int[][] {{0, 1, 0}, {0, 0, 1}}, 1, 1, 1));

    assertArrayEquals(
        new int[][] {
          {8, 4, 5, 3, 2, 2, 2, 6, 9, 5},
          {3, 1, 3, 7, 4, 8, 9, 0, 1, 6},
          {0, 7, 1, 7, 5, 2, 2, 8, 6, 9},
          {9, 1, 0, 5, 8, 8, 0, 5, 5, 9},
          {3, 4, 9, 5, 4, 8, 0, 2, 7, 5},
          {5, 1, 1, 8, 1, 6, 5, 9, 7, 8},
          {9, 7, 9, 4, 5, 3, 4, 1, 8, 2},
          {5, 1, 6, 3, 5, 4, 8, 9, 0, 8},
          {9, 9, 6, 1, 0, 8, 0, 6, 9, 9},
          {4, 1, 8, 7, 3, 6, 9, 6, 6, 1}
        },
        floodFill(
            new int[][] {
              {8, 4, 5, 3, 2, 2, 2, 6, 9, 5},
              {3, 1, 3, 7, 4, 8, 9, 0, 1, 6},
              {0, 7, 1, 7, 5, 2, 2, 8, 6, 9},
              {9, 1, 0, 5, 8, 8, 0, 5, 5, 9},
              {3, 4, 9, 5, 4, 8, 0, 2, 7, 5},
              {5, 1, 1, 8, 1, 6, 5, 9, 7, 8},
              {9, 7, 9, 4, 5, 3, 4, 1, 8, 2},
              {5, 1, 6, 3, 5, 4, 8, 9, 0, 8},
              {8, 8, 6, 1, 0, 8, 0, 6, 9, 9},
              {4, 1, 8, 7, 3, 6, 9, 6, 6, 1}
            },
            8,
            0,
            9));
  }

  private int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

    if (image[sr][sc] == newColor) return image;

    int oldColor = image[sr][sc];
    image[sr][sc] = newColor;

    floodFill(image, sr, sc, newColor, oldColor);

    return image;
  }

  private void floodFill(int[][] image, int sr, int sc, int newColor, int oldColor) {

    if (sr >= 0 && sr < image.length && sc >= 0 && sc < image[sr].length) {

      // check directions
      if (sr + 1 < image.length && checkPoint(image, sc, oldColor, newColor, sr + 1))
        floodFill(image, sr + 1, sc, newColor, oldColor);

      if (sr - 1 >= 0 && checkPoint(image, sc, oldColor, newColor, sr - 1))
        floodFill(image, sr - 1, sc, newColor, oldColor);

      if (sc + 1 < image[sr].length && checkPoint(image, sc + 1, oldColor, newColor, sr))
        floodFill(image, sr, sc + 1, newColor, oldColor);

      if (sc - 1 >= 0 && checkPoint(image, sc - 1, oldColor, newColor, sr))
        floodFill(image, sr, sc - 1, newColor, oldColor);
    }
  }

  private boolean checkPoint(int[][] image, int sc, int oldColor, int newColor, int sr) {

    if (image[sr][sc] - oldColor != 0) return false;

    image[sr][sc] = newColor;

    return true;
  }

  @Test
  public void islandPerimeter() {
    assertEquals(
        16, islandPerimeter(new int[][] {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}));

    assertEquals(
        4, islandPerimeter(new int[][] {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));

    assertEquals(
        8, islandPerimeter(new int[][] {{1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
  }

  private int islandPerimeter(int[][] grid) {

    int sol = 0;

    for (int i = 0; i < grid.length; i++) {
      int[] arr = grid[i];

      for (int j = 0; j < arr.length; j++) {

        if (arr[j] == 1) {
          sol += 4;

          if (j - 1 >= 0) {
            if (arr[j - 1] == 1) sol -= 1;
          }

          if (j + 1 < arr.length) {
            if (arr[j + 1] == 1) sol -= 1;
          }

          if (i + 1 < grid.length) {
            if (grid[i + 1][j] == 1) sol -= 1;
          }

          if (i - 1 >= 0) {
            if (grid[i - 1][j] == 1) sol -= 1;
          }
        }
      }
    }

    return sol;
  }

  @Test
  public void longestWord() {
    assertEquals(
        "apple", longestWord(new String[] {"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    assertEquals("world", longestWord(new String[] {"w", "wo", "wor", "worl", "world"}));
    assertEquals(
        "breakfast",
        longestWord(
            new String[] {
              "b",
              "br",
              "bre",
              "brea",
              "break",
              "breakf",
              "breakfa",
              "breakfas",
              "breakfast",
              "l",
              "lu",
              "lun",
              "lunc",
              "lunch",
              "d",
              "di",
              "din",
              "dinn",
              "dinne",
              "dinner"
            }));
    assertEquals(
        "yodn",
        longestWord(
            new String[] {
              "yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod",
              "ewqz", "y"
            }));
    assertEquals(
        "eyj",
        longestWord(
            new String[] {
              "ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq",
              "ogznk", "og", "eyjuoi", "d"
            }));
    assertEquals(
        "otif",
        longestWord(
            new String[] {
              "rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy",
              "onpd"
            }));

    assertEquals(
        "otif",
        longestWord1(
            new String[] {
              "rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy",
              "onpd"
            }));
  }

  private String longestWord(String[] words) {

    Arrays.parallelSort(words);

    Set<String> seenSeed = new HashSet<>();

    String word = "";
    for (String s : words) {
      if (s.length() == 1) {
        seenSeed.add(s);
        word = checkWord(word, s);
        continue;
      }

      if (seenSeed.contains(s.substring(0, s.length() - 1))) {
        seenSeed.add(s);

        word = checkWord(word, s);
      }
    }

    return word;
  }

  private String checkWord(String word, String s) {
    if (s.length() - word.length() > 0) word = s;
    else if (s.length() - word.length() == 0) {
      if (s.compareTo(word) < 0) word = s;
    }
    return word;
  }

  private String longestWord1(String[] words) {
    Trie trie = new Trie();
    int index = 0;
    for (String word : words) {
      trie.insert(word, ++index); // indexed by 1
    }
    trie.words = words;
    return trie.dfs();
  }

  class Node {
    char c;
    HashMap<Character, Node> children = new HashMap();
    int end;

    public Node(char c) {
      this.c = c;
    }
  }

  class Trie {
    Node root;
    String[] words;

    public Trie() {
      root = new Node('0');
    }

    public void insert(String word, int index) {
      Node cur = root;
      for (char c : word.toCharArray()) {
        cur.children.putIfAbsent(c, new Node(c));
        cur = cur.children.get(c);
      }
      cur.end = index;
    }

    public String dfs() {
      String ans = "";
      Stack<Node> stack = new Stack();
      stack.push(root);
      while (!stack.empty()) {
        Node node = stack.pop();
        if (node.end > 0 || node == root) {
          if (node != root) {
            String word = words[node.end - 1];
            if (word.length() > ans.length()
                || word.length() == ans.length() && word.compareTo(ans) < 0) {
              ans = word;
            }
          }
          for (Node nei : node.children.values()) {
            stack.push(nei);
          }
        }
      }
      return ans;
    }
  }

  @Test
  public void repeatedNTimes() {

    assertEquals(3, repeatedNTimes(new int[] {1, 2, 3, 3}));
    assertEquals(2, repeatedNTimes(new int[] {2, 1, 2, 5, 3, 2}));
    assertEquals(5, repeatedNTimes(new int[] {5, 1, 5, 2, 5, 3, 5, 4}));
  }

  private int repeatedNTimes(int[] A) {

    int half = A.length / 2;

    int[] freq = new int[10001];

    for (int i : A) {
      int frequency = ++freq[i];
      if (frequency - half == 0) return i;
    }

    return -1;
  }

  @Test
  public void findOcurrences() {
    assertArrayEquals(
        new String[] {"girl", "student"},
        findOcurrences("alice is a good girl she is a good student", "a", "good"));
    assertArrayEquals(
        new String[] {"we", "rock"}, findOcurrences("we will we will rock you", "we", "will"));
    assertArrayEquals(
        new String[] {"kcyxdfnoa", "kcyxdfnoa", "kcyxdfnoa"},
        findOcurrences(
            "jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa",
            "kcyxdfnoa",
            "jkypmsxd"));
  }

  private String[] findOcurrences(String text, String first, String second) {

    String[] split = text.split(" ");

    LinkedList<String> list = new LinkedList<>();

    for (int position = 0; position < split.length; position++) {
      if (position - 1 >= 0
          && split[position].equals(second)
          && position + 1 < split.length
          && split[position - 1].equals(first)) list.add(split[position + 1]);
    }

    return list.stream().toArray(String[]::new);
  }

  @Test
  public void dailyTemperatures() {
    assertArrayEquals(
        new int[] {1, 1, 4, 2, 1, 1, 0, 0},
        dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}));
    assertArrayEquals(
        new int[] {8, 1, 5, 4, 3, 2, 1, 1, 0, 0},
        dailyTemperatures(new int[] {89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
  }

  private int[] dailyTemperatures(int[] T) {

    int[] positions = new int[101];

    int[] sol = new int[T.length];
    sol[T.length - 1] = 0;
    int i = T.length - 1;
    positions[T[i]] = i;

    while (--i >= 0) {

      int value = T[i];

      int position = value;

      int result = Integer.MAX_VALUE;

      while (++position < 101) {
        if (positions[position] > i) result = Math.min(result, positions[position] - i);
      }

      sol[i] = result == Integer.MAX_VALUE ? 0 : result;
      positions[value] = i;
    }

    return sol;
  }

  @Test
  public void oddCells() {
    assertEquals(6, oddCells(2, 3, new int[][] {{0, 1}, {1, 1}}));
    assertEquals(0, oddCells(2, 2, new int[][] {{1, 1}, {0, 0}}));
  }

  private int oddCells(int n, int m, int[][] indices) {

    int[][] matrix = new int[n][m];
    int sol = 0;
    for (int[] ints : indices) {

      int[] row = matrix[ints[0]];
      for (int i = 0; i < row.length; i++) {
        int prev = row[i];
        row[i] = prev + 1;

        if ((row[i] & 1) != 0) sol++;

        if (prev != 0 && (prev & 1) == 1) {
          sol--;
        }
      }

      int column = ints[1];

      for (int i = 0; i < matrix.length; i++) {
        int[] rows = matrix[i];
        int prev = rows[column];
        rows[column] = prev + 1;

        if ((rows[column] & 1) != 0) sol++;
        if (prev != 0 && (prev & 1) == 1) {
          sol--;
        }
      }
    }

    return sol;
  }

  @Test
  public void dayOfTheWeek() {
    assertEquals(daysOfWeek[6], dayOfTheWeek(31, 8, 2019));
    assertEquals(daysOfWeek[0], dayOfTheWeek(18, 7, 1999));
    assertEquals(daysOfWeek[0], dayOfTheWeek(15, 8, 1993));
  }

  private String dayOfTheWeek(int day, int month, int year) {

    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month - 1, day);

    return daysOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1];
  }

  @Test
  public void heightChecker() {

    assertEquals(3, heightChecker(new int[] {1, 1, 4, 2, 1, 3}));
    assertEquals(5, heightChecker(new int[] {5, 1, 2, 3, 4}));
  }

  private int heightChecker(int[] heights) {

    int[] clone = heights.clone();

    Arrays.parallelSort(heights);

    int count = 0;

    for (int i = 0; i < clone.length; i++) {
      if (clone[i] != heights[i]) count++;
    }

    return count;
  }

  @Test
  public void relativeSortArray() {
    assertArrayEquals(
        new int[] {2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19},
        relativeSortArray(
            new int[] {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[] {2, 1, 4, 3, 9, 6}));
    assertArrayEquals(
        new int[] {22, 28, 8, 6, 17, 44},
        relativeSortArray(new int[] {28, 6, 22, 8, 44, 17}, new int[] {22, 28, 8, 6}));
    assertArrayEquals(
        new int[] {21, 11, 26, 20, 1, 18, 34, 50},
        relativeSortArray(new int[] {26, 21, 11, 20, 50, 34, 1, 18}, new int[] {21, 11, 26, 20}));
  }

  private int[] relativeSortArray(int[] arr1, int[] arr2) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < arr2.length; i++) {
      map.put(arr2[i], i);
    }

    arr1 =
        Arrays.stream(arr1)
            .boxed()
            .sorted(
                new Comparator<Integer>() {
                  public int compare(Integer o1, Integer o2) {
                    Integer left = map.get(o1);
                    Integer right = map.get(o2);

                    if (null != left && null != right) return left.compareTo(right);

                    if (null != left && null == right) return -1;

                    if (null == left && null != right) return 1;

                    return o1.compareTo(o2);
                  }
                })
            .mapToInt(i -> i)
            .toArray();

    return arr1;
  }

  @Test
  public void countCharacters() {

    assertEquals(6, countCharacters(new String[] {"cat", "bt", "hat", "tree"}, "atach"));
    assertEquals(
        10, countCharacters(new String[] {"hello", "world", "leetcode"}, "welldonehoneyr"));
  }

  private int countCharacters(String[] words, String chars) {

    char[] letters = chars.toCharArray();
    int[] frequency = new int[128];

    for (char letter : letters) {
      frequency[letter] = ++frequency[letter];
    }

    int sol = 0;

    for (String word : words) {
      int[] clone = frequency.clone();
      boolean isOk = true;

      for (char c : word.toCharArray()) {
        if (clone[c] > 0) clone[c] = --clone[c];
        else {
          isOk = false;
          break;
        }
      }

      if (isOk) sol += word.length();
    }

    return sol;
  }

  @Test
  public void numRookCaptures() {

    assertEquals(
        3,
        numRookCaptures(
            new char[][] {
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', 'p', '.', '.', '.', '.'},
              {'.', '.', '.', 'R', '.', '.', '.', 'p'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', 'p', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'}
            }));

    assertEquals(
        0,
        numRookCaptures(
            new char[][] {
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
              {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
              {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
              {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
              {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.'}
            }));
  }

  private int numRookCaptures(char[][] board) {

    int i = 0;

    int rockAxis = 0;
    int rockOrd = 0;

    loop:
    while (i < board.length) {

      char[] line = board[i];
      int j = 0;

      while (j < line.length) {
        if (line[j] == 'R') {
          rockAxis = j;
          rockOrd = i;
          break loop;
        }

        j++;
      }

      i++;
    }

    int result = 0;

    char[] posToCheck;

    int up = rockOrd - 1;
    int down = rockOrd + 1;
    int east = rockAxis + 1;
    int west = rockAxis - 1;

    while (up >= 0 || down < board.length || east < board.length || west >= 0) {

      if (up >= 0) {

        posToCheck = board[up];
        if (posToCheck[rockAxis] == 'B') up = -1;
        else if (posToCheck[rockAxis] == 'p') {
          result++;
          up = -1;
        }
        up--;
      }

      if (down < board.length) {
        posToCheck = board[down];

        if (posToCheck[rockAxis] == 'B') down = board.length;
        else if (posToCheck[rockAxis] == 'p') {
          result++;
          down = board.length;
        }

        down++;
      }

      if (east < board.length) {
        posToCheck = board[rockOrd];

        if (posToCheck[east] == 'B') east = board.length;
        else if (posToCheck[east] == 'p') {
          result++;
          east = board.length;
        }
        east++;
      }

      if (west >= 0) {
        posToCheck = board[rockOrd];

        if (posToCheck[west] == 'B') west = -1;
        else if (posToCheck[west] == 'p') {
          result++;
          west = -1;
        }
        west--;
      }
    }

    return result;
  }

  @Test
  public void smallerNumbersThanCurrent() {
    assertArrayEquals(
        new int[] {4, 0, 1, 1, 3}, smallerNumbersThanCurrent(new int[] {8, 1, 2, 2, 3}));
    assertArrayEquals(
        new int[] {4, 4, 0, 1, 1, 3}, smallerNumbersThanCurrent(new int[] {8, 8, 1, 2, 2, 3}));
    assertArrayEquals(new int[] {0, 0, 0, 0}, smallerNumbersThanCurrent(new int[] {7, 7, 7, 7}));
    assertArrayEquals(new int[] {0, 0}, smallerNumbersThanCurrent(new int[] {7, 7}));
    assertArrayEquals(new int[] {0, 1}, smallerNumbersThanCurrent(new int[] {1, 2}));
  }

  private int[] smallerNumbersThanCurrent(int[] nums) {

    int[] clone = nums.clone();

    Arrays.parallelSort(nums);

    int count = 1;
    int currVal = nums[0];

    int i = 1;

    Map<Integer, Integer> map = new HashMap<>();

    map.put(nums[0], 0);

    while (i < nums.length) {
      if (nums[i] > currVal) {
        currVal = nums[i];
        map.put(nums[i], count);
        count = i;
        continue;
      }

      count++;
      i++;
    }

    i = 0;

    while (i < clone.length) {
      clone[i] = map.getOrDefault(clone[i], map.size());
      i++;
    }

    return clone;
  }

  @Test
  public void sumEvenAfterQueries() {
    assertArrayEquals(
        new int[] {8, 6, 2, 4},
        sumEvenAfterQueries(
            new int[] {1, 2, 3, 4}, new int[][] {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}}));
  }

  private int[] sumEvenAfterQueries(int[] A, int[][] queries) {

    int j = 0;
    int[] sol = new int[A.length];

    int sum = 0;

    for (int i : A) if (i % 2 == 0) sum += i;

    while (j < queries.length) {
      int tmp = A[queries[j][1]];

      A[queries[j][1]] = A[queries[j][1]] + queries[j][0];

      if ((tmp & 1) != 0 && A[queries[j][1]] % 2 == 0) sum += A[queries[j][1]];
      else if ((tmp & 1) == 0 && A[queries[j][1]] % 2 == 0) {
        sum -= tmp;
        sum += A[queries[j][1]];
      } else if ((tmp & 1) == 0 && A[queries[j][1]] % 2 != 0) {
        sum -= tmp;
      }

      sol[j] = sum;

      j++;
    }

    return sol;
  }

  @Test
  public void createTargetArray() {
    assertArrayEquals(
        new int[] {0, 1, 2, 3, 4},
        createTargetArray(new int[] {1, 2, 3, 4, 0}, new int[] {0, 1, 2, 3, 0}));
    assertArrayEquals(
        new int[] {0, 4, 1, 3, 2},
        createTargetArray(new int[] {0, 1, 2, 3, 4}, new int[] {0, 1, 2, 2, 1}));
  }

  public int[] createTargetArray(int[] nums, int[] index) {
    int[] sol = new int[nums.length];
    Arrays.fill(sol, -1);

    int i = 0;

    while (i < nums.length) {

      if (sol[index[i]] != -1) {

        int j = index[i];

        int tmp = 0;
        int replace = nums[i];

        while (j < i + 1) {
          tmp = sol[j];
          sol[j] = replace;
          replace = tmp;
          j++;
        }

      } else sol[index[i]] = nums[i];

      i++;
    }

    return sol;
  }

  @Test
  public void distanceBetweenBusStops() {
    assertEquals(1, distanceBetweenBusStops(new int[] {1, 2, 3, 4}, 0, 1));
    assertEquals(3, distanceBetweenBusStops(new int[] {1, 2, 3, 4}, 0, 2));
    assertEquals(4, distanceBetweenBusStops(new int[] {1, 2, 3, 4}, 0, 3));
    assertEquals(1, distanceBetweenBusStops(new int[] {1}, 0, 1));
    assertEquals(0, distanceBetweenBusStops(new int[] {0}, 0, 1));
    assertEquals(17, distanceBetweenBusStops(new int[] {7, 10, 1, 12, 11, 14, 5, 0}, 7, 2));
    assertEquals(40, distanceBetweenBusStops(new int[] {14, 13, 4, 7, 10, 17, 8, 3, 2, 13}, 2, 9));
  }

  public int distanceBetweenBusStops(int[] distance, int start, int destination) {

    if (distance.length == 1) return distance[0];

    int lowerSteps = 0;
    int upperSteps = 0;

    int clockWise = start;
    int counterClockWise = start;

    while (clockWise != destination || counterClockWise != destination) {

      if (clockWise != destination) {
        lowerSteps += distance[clockWise];
        clockWise = clockWise + 1 > distance.length - 1 ? 0 : clockWise + 1;
      }

      if (counterClockWise != destination) {
        counterClockWise = counterClockWise - 1 < 0 ? distance.length - 1 : counterClockWise - 1;
        upperSteps += distance[counterClockWise];
      }
    }

    return Math.min(upperSteps, lowerSteps);
  }

  @Test
  public void findTheDistanceValue() {
    assertEquals(2, findTheDistanceValue(new int[] {4, 5, 8}, new int[] {10, 9, 1, 8}, 2));
    assertEquals(
        2, findTheDistanceValue(new int[] {1, 4, 2, 3}, new int[] {-4, -3, 6, 10, 20, 30}, 3));
    assertEquals(
        1, findTheDistanceValue(new int[] {2, 1, 100, 3}, new int[] {-5, -2, 10, -3, 7}, 6));
    assertEquals(0, findTheDistanceValue(new int[] {4, -3, -7, 0, 10}, new int[] {10}, 69));
    assertEquals(
        2,
        findTheDistanceValue(new int[] {-3, 10, 2, 8, 0, 10}, new int[] {-9, -1, -4, -9, -8}, 9));
  }

  public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

    Arrays.parallelSort(arr2);
    int count = 0;

    for (int i : arr1) if (!getDistanceValue(d, 0, arr2.length - 1, i, arr2)) count++;

    return count;
  }

  private boolean getDistanceValue(int d, int left, int right, int target, int[] nums) {

    while (left <= right) {

      int middle = (left + right) / 2;

      int middleEl = nums[middle];

      if (Math.abs(target - middleEl) <= d) return true;
      else if (middleEl < 0) left = middle + 1;
      else right = middle - 1;
    }

    return false;
  }

  @Test
  public void duplicateZeros() {
    int[] arr = new int[] {1, 0, 2, 3, 0, 4, 5, 0};
    duplicateZeros(arr);
    assertTrue(Arrays.equals(new int[] {1, 0, 0, 2, 3, 0, 0, 4}, arr));

    arr = new int[] {1, 2, 3};
    assertTrue(Arrays.equals(new int[] {1, 2, 3}, arr));
  }

  public void duplicateZeros(int[] arr) {

    int i = 0;

    while (i < arr.length) {

      if (arr[i] == 0 && i != arr.length - 1) {
        int j = i + 1;
        int tmp = arr[j];

        while (j + 1 < arr.length) {
          int tmp2 = arr[j + 1];
          arr[j + 1] = tmp;
          tmp = tmp2;
          j++;
        }

        arr[i + 1] = 0;
        i++;
      }

      i++;
    }
  }

  @Test
  public void minTimeToVisitAllPoints() {
    assertEquals(7, minTimeToVisitAllPoints(new int[][] {{1, 1}, {3, 4}, {-1, 0}}));
    assertEquals(5, minTimeToVisitAllPoints(new int[][] {{3, 2}, {-2, 2}}));
    assertEquals(0, minTimeToVisitAllPoints(new int[][] {{-2, 2}, {-2, 2}}));
    assertEquals(2, minTimeToVisitAllPoints(new int[][] {{1, 1}, {-1, -1}}));
    assertEquals(2, minTimeToVisitAllPoints(new int[][] {{1, 1}, {1, 3}}));
    assertEquals(2, minTimeToVisitAllPoints(new int[][] {{1, 1}, {3, 1}}));
  }

  public int minTimeToVisitAllPoints(int[][] points) {

    int i = 0;
    int totalSteps = 0;

    while (i + 1 < points.length) {

      totalSteps += getSteps(points[i][0], points[i][1], points[i + 1][0], points[i + 1][1]);
      i++;
    }

    return totalSteps;
  }

  private int getSteps(int x, int y, int x1, int y1) {

    int steps = 0;
    while (x1 != x || y != y1) {

      if (x1 > x) x++;
      else if (x1 != x) x--;

      if (y1 > y) y++;
      else if (y1 != y) y--;

      steps++;
    }
    return steps;
  }

  @Test
  public void countNegatives() {
    assertEquals(
        8,
        countNegatives(
            new int[][] {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}));
    assertEquals(0, countNegatives(new int[][] {{3, 2}, {1, 0}}));
    assertEquals(3, countNegatives(new int[][] {{1, -1}, {-1, 1}}));
    assertEquals(
        16,
        countNegatives(
            new int[][] {
              {3, -1, -3, -3, -3}, {2, -2, -3, -3, -3}, {1, -2, -3, -3, -3}, {0, -3, -3, -3, -3}
            }));
  }

  public int countNegatives(int[][] grid) {

    int count = 0;

    for (int[] ints : grid)
      count += ints.length - getNegativeStartIndex(0, ints.length - 1, 0, ints);

    return count;
  }

  private int getNegativeStartIndex(int left, int right, int target, int[] nums) {

    int middle;
    while (left <= right) {

      middle = (left + right) / 2;

      int middleEl = nums[middle];

      if (middleEl < target && (middle == 0 || middle == right || nums[middle - 1] >= target))
        return middle;
      else if (middleEl >= target) left = middle + 1;
      else right = middle - 1;
    }

    return nums.length;
  }

  @Test
  public void numMagicSquaresInside() {
    assertEquals(1, numMagicSquaresInside(new int[][] {{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}}));
    assertEquals(0, numMagicSquaresInside(new int[][] {{5, 5, 5}, {5, 5, 5}, {5, 5, 5}}));
    assertEquals(1, numMagicSquaresInside(new int[][] {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}}));
    assertEquals(0, numMagicSquaresInside(new int[][] {{10, 3, 5}, {1, 6, 11}, {7, 9, 2}}));
    assertEquals(
        1,
        numMagicSquaresInside(
            new int[][] {
              {3, 2, 9, 2, 7}, {6, 1, 8, 4, 2}, {7, 5, 3, 2, 7}, {2, 9, 4, 9, 6}, {4, 3, 8, 2, 5}
            }));
  }

  public int numMagicSquaresInside(int[][] grid) {

    if (null == grid || (grid.length < 3 || grid[0].length < 3)) return 0;

    int sol = 0;

    int i = 0;
    int j;
    while (i + 2 < grid.length) {

      int[] row1 = grid[i];
      int[] row2 = grid[i + 1];
      int[] row3 = grid[i + 2];

      j = 0;
      while (j + 2 < grid[i].length) {

        HashSet<Integer> set = new HashSet<Integer>();

        int z = j;

        while (z < j + 3) {
          if (row1[z] > 0 && row1[z] < 10) set.add(row1[z]);

          if (row2[z] > 0 && row2[z] < 10) set.add(row2[z]);

          if (row3[z] > 0 && row3[z] < 10) set.add(row3[z]);

          z++;
        }

        if (set.size() == 9) {

          int rowSum1 = IntStream.range(j, j + 3).map(r -> row1[r]).sum();
          int rowSum2 = IntStream.range(j, j + 3).map(r -> row2[r]).sum();
          int rowSum3 = IntStream.range(j, j + 3).map(r -> row3[r]).sum();

          int columnSum1 = row1[j] + row2[j] + row3[j];
          int columnSum2 = row1[j + 1] + row2[j + 1] + row3[j + 1];
          int columnSum3 = row1[j + 2] + row2[j + 2] + row3[j + 2];

          int diagonal1 = row1[j] + row2[j + 1] + row3[j + 2];
          int diagonal2 = row1[j + 2] + row2[j + 1] + row3[j];

          if (rowSum1 - rowSum2 == 0
              && rowSum2 - rowSum3 == 0
              && columnSum1 - columnSum2 == 0
              && columnSum2 - columnSum3 == 0
              && diagonal1 - diagonal2 == 0
              && diagonal1 - rowSum1 == 0) sol++;
        }
        j++;
      }

      i++;
    }

    return sol;
  }

  @Test
  public void sumZero() {

    sumZero(5);
    sumZero(10);
    sumZero(50);
    sumZero(1000);
  }

  public int[] sumZero(int n) {

    if (n == 1) return new int[1];

    int[] sol = new int[n];

    sol[0] = 500;
    sol[1] = -500;

    int k = 2;
    int i = 1;
    while (k + 2 < n) k = setRandomValues(sol, k, i++);

    if ((n & 1) == 0) setRandomValues(sol, n - 2, i);

    return sol;
  }

  private int setRandomValues(int[] sol, int k, int value) {

    sol[k++] = value;
    sol[k++] = -value;
    return k;
  }

  @Test
  public void dominantIndex() {

    assertEquals(1, dominantIndex(new int[] {3, 6, 1, 0}));
  }

  public int dominantIndex(int[] nums) {

    int max = nums[0];

    int index = 0;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > max) {
        max = nums[i];
        index = i;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0 && nums[i] != max && max / nums[i] < 2) return -1;
    }

    return index;
  }

  @Test
  public void findShortestSubArray() {

    assertEquals(2, findShortestSubArray(new int[] {1, 2, 2, 3, 1}));
    assertEquals(6, findShortestSubArray(new int[] {1, 2, 2, 3, 1, 4, 2}));
  }

  public int findShortestSubArray(int[] nums) {

    Map<Integer, Integer> left = new HashMap(), right = new HashMap(), count = new HashMap();

    for (int i = 0; i < nums.length; i++) {
      int x = nums[i];
      if (left.get(x) == null) left.put(x, i);
      right.put(x, i);
      count.put(x, count.getOrDefault(x, 0) + 1);
    }

    int ans = nums.length;
    int degree = Collections.max(count.values());
    for (int x : count.keySet()) {
      if (count.get(x) == degree) {
        ans = Math.min(ans, right.get(x) - left.get(x) + 1);
      }
    }
    return ans;
  }

  @Test
  public void findSpecialInteger() {
    assertEquals(6, findSpecialInteger(new int[] {1, 2, 2, 6, 6, 6, 6, 7, 10}));
    assertEquals(3, findSpecialInteger(new int[] {1, 2, 3, 3}));
    assertEquals(
        9808,
        findSpecialInteger(
            new int[] {
              76, 236, 301, 380, 382, 760, 854, 946, 953, 1051, 1219, 1377, 1391, 1393, 1854, 1879,
              1990, 2017, 2344, 2728, 2876, 3030, 3225, 3386, 3405, 3577, 3766, 4066, 4240, 4307,
              4637, 4648, 4746, 4894, 5106, 5214, 5256, 5362, 5388, 5479, 5492, 5496, 5771, 5795,
              5851, 5865, 6061, 6134, 6546, 6553, 6567, 6627, 6633, 6788, 6876, 7018, 7043, 7144,
              7177, 7195, 7249, 7339, 7568, 7865, 7901, 8001, 8061, 8112, 8160, 8265, 8465, 8484,
              8879, 8968, 9028, 9107, 9151, 9278, 9426, 9591, 9601, 9649, 9718, 9775, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808, 9808,
              9808, 9808, 9808, 9808, 9808, 9983, 10037, 10259, 10770, 10918, 11036, 11245, 11376,
              11501, 11508, 11570, 11589, 11750, 11765, 12096, 12104, 12160, 12190, 12620, 12630,
              12682, 12781, 12793, 12821, 12925, 13813, 13820, 13877, 13999, 14240, 14275, 14377,
              14452, 14614, 14638, 14795, 14826, 14952, 15066, 15267, 15770, 15907, 15913, 16128,
              16263, 16315, 16364, 16372, 16705, 16730, 16790, 17045, 17181, 17213, 17522, 17544,
              18036, 18216, 18246, 18273, 18331, 18407, 18489, 18870, 19105, 19147, 19180, 20008,
              20129, 20377, 20450, 20514, 20557, 20578, 20698, 20705, 20746, 20845, 20864, 20897,
              21158, 21367, 21541, 21592, 21689, 21852, 21893, 22088, 22183, 22766, 23275, 23288,
              23300, 23310, 23501, 23520, 23625, 24125, 24369, 24371, 24606, 24713, 24983, 25985,
              26174, 26333, 26356, 26545, 26547, 26826, 26832, 26932, 27020, 27091, 27312, 27438,
              27653, 27886, 28103, 28269, 28468, 28716, 29105, 29194, 29696, 30220, 30419, 30588,
              30599, 30922, 31512, 31632, 31652, 31908, 31981, 32015, 32015, 32020, 32039, 32183,
              32246, 32268, 32360, 32439, 32633, 32705, 32741, 33081, 33084, 33606, 33741, 34037,
              34094, 34176, 34310, 34341, 34572, 34972, 35076, 35186, 35363, 35375, 35524, 35637,
              35695, 35861, 36003, 36042, 36316, 36321, 36515, 36629, 36660, 36665, 36696, 37067,
              37143, 37205, 37213, 37316, 37549, 37579, 37583, 37775, 37787, 37808, 37845, 37991,
              38015, 38207, 38644, 38797, 38891, 39286, 39344, 39348, 39357, 39378, 39518, 39594,
              39616, 40267, 40377, 40576, 40703, 40729, 41304, 41615, 41702, 41921, 42216, 42571,
              42693, 42745, 42761, 42764, 42981, 43006, 43024, 43635, 43655, 43710, 43780, 44002,
              44294, 44352, 44386, 44437, 44500, 44595, 44994, 45279, 45314, 45514, 45582, 46120,
              46196, 46209, 46228, 46730, 46784, 47086, 47088, 47205, 47671, 47676, 47754, 47857,
              47861, 47941, 48008, 48214, 48528, 48877, 48941, 48955, 48961, 49064, 49242, 49246,
              49269, 49432, 49646, 49723, 49765, 50193, 50473, 50774, 50785, 50941, 51076, 51467,
              51580, 51818, 51927, 51982, 52050, 52119, 52256, 52353, 52456, 52669, 52906, 53094,
              53221, 53328, 53375, 53529, 53560, 53593, 53662, 53795, 53941, 54007, 54120, 54137,
              54244, 54276, 54288, 54336, 54421, 54446, 54565, 54612, 54621, 55065, 55278, 55342,
              55352, 55584, 55787, 56005, 56046, 56097, 56428, 57024, 57090, 57259, 57337, 57343,
              57376, 57456, 57479, 57532, 57535, 57660, 58020, 58056, 58189, 58201, 58204, 58336,
              58342, 58360, 58403, 58416, 58570, 58617, 58636, 58699, 58872, 58974, 59013, 59065,
              59082, 59261, 59381, 59421, 59459, 59513, 59559, 59712, 60204, 60266, 60345, 60551,
              60754, 60783, 60857, 60963, 61007, 61049, 61068, 61133, 61241, 61305, 61371, 61773,
              61918, 62044, 62158, 62398, 62400, 62512, 62519, 62663, 62717, 63228, 63391, 63506,
              63664, 63924, 63989, 64465, 64493, 64530, 64685, 64898, 65021, 65146, 65541, 65607,
              65631, 65656, 65720, 65866, 65930, 66544, 66564, 66649, 66668, 66862, 67076, 67516,
              67756, 68127, 68155, 68162, 68430, 68530, 68671, 69002, 69023, 69076, 69257, 69579,
              69588, 69826, 70083, 70190, 70268, 70318, 70401, 70499, 71150, 71348, 71418, 72192,
              72279, 72436, 72457, 72542, 72553, 72588, 72678, 72761, 72766, 72874, 73628, 73850,
              73975, 74081, 74588, 74798, 75552, 75942, 76023, 76309, 76722, 76778, 76949, 77153,
              77287, 77307, 77924, 78030, 78178, 78237, 78452, 78470, 78484, 78814, 78875, 79019,
              79080, 79130, 79212, 79290, 79332, 79354, 79436, 79440, 79562, 79831, 79910, 80091,
              80463, 80741, 81126, 81216, 81330, 81568, 81574, 81791, 81899, 81961, 82333, 82591,
              82646, 82658, 82734, 82764, 82772, 82794, 82812, 82943, 83081, 83156, 83197, 83197,
              83302, 83455, 83577, 83599, 83603, 83730, 83796, 84083, 84359, 85031, 85404, 85590,
              85712, 85790, 85972, 86219, 86321, 86419, 86594, 86785, 86997, 87013, 87121, 87246,
              87347, 87376, 87494, 87609, 87939, 88056, 88302, 88382, 88653, 88655, 88667, 88934,
              88950, 89609, 89831, 89861, 89938, 90168, 90406, 90581, 90597, 90721, 90898, 91021,
              91024, 91026, 91188, 91192, 91407, 91622, 91774, 92162, 92411, 92795, 92977, 93042,
              93318, 93405, 93507, 93631, 93816, 93892, 93977, 94139, 94165, 94488, 94535, 94661,
              94694, 94846, 94930, 94971, 94982, 95170, 95202, 95384, 95579, 95696, 95719, 95733,
              95970, 96001, 96014, 96060, 96444, 96444, 96610, 96835, 96855, 96912, 97093, 97291,
              97423, 98061, 98192, 98467, 98643, 98657, 98697, 98784, 99019, 99042, 99064, 99326,
              99377, 99602, 99607, 99630, 99799, 99804, 99888
            }));
  }

  public int findSpecialInteger(int[] arr) {

    int sol = arr[0];

    int length = arr.length;
    int perc = length / 4;

    int count = 1;

    while (count < length) {

      if (arr[count + perc] == sol) return sol;

      sol = arr[++count];
    }

    return sol;
  }

  @Test
  public void checkStraightLine() {

    assertEquals(
        true, checkStraightLine(new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
  }

  private boolean checkStraightLine(int[][] coordinates) {
    int[] point1 = coordinates[1];
    int[] point2 = coordinates[0];
    float gSlope = slope(point1, point2);

    for (int i = 1; i < coordinates.length; i++) {
      int[] p1 = coordinates[i];
      int[] p2 = coordinates[0];
      float slop = slope(p1, p2);
      if (gSlope != slop) return false;
    }

    return true;
  }

  private float slope(int[] p1, int[] p2) {
    if ((p1[0] - p2[0]) == 0) return 0;
    return (float) (p2[1] - p1[1]) / (p2[0] - p1[0]);
  }

  public void findNumbers() {

    assertEquals(2, findNumbers(new int[] {12, 345, 2, 6, 7896}));
    assertEquals(1, findNumbers(new int[] {555, 901, 482, 1771}));
    assertEquals(0, findNumbers(new int[] {580, 317, 640, 957, 718, 764}));
    assertEquals(0, findNumbers(new int[] {0}));
  }

  public int findNumbers(int[] nums) {

    int count = 0;
    int sol = 0;
    for (int num : nums) {

      while (num > 0) {
        num = num / 10;
        count++;
      }

      if (count != 0 && (count & 1) == 0) sol++;

      count = 0;
    }

    return sol;
  }

  @Test
  public void replaceElements() {
    assertTrue(
        Arrays.equals(
            new int[] {18, 6, 6, 6, 1, -1}, replaceElements(new int[] {17, 18, 5, 4, 6, 1})));
  }

  public int[] replaceElements(int[] arr) {

    int length = arr.length;

    if (length == 1) return new int[] {-1};

    int currentValue = arr[length - 1];
    arr[length - 1] = -1;
    int maxSoFar = currentValue;

    for (int i = length - 2; i >= 0; i--) {

      currentValue = arr[i];
      arr[i] = maxSoFar;
      maxSoFar = Math.max(currentValue, maxSoFar);
    }

    return arr;
  }

  @Test
  public void largeGroupPositions() {

    assertEquals(Arrays.asList(Arrays.asList(3, 6)), largeGroupPositions("abbxxxxzyy"));
    assertEquals(Arrays.asList(Arrays.asList(0, 2)), largeGroupPositions("aaa"));
    assertEquals(
        Arrays.asList(Arrays.asList(3, 5), Arrays.asList(6, 9), Arrays.asList(12, 14)),
        largeGroupPositions("abcdddeeeeaabbbcd"));
  }

  public List<List<Integer>> largeGroupPositions(String S) {

    List<List<Integer>> list = new ArrayList<>();

    int current = S.charAt(0);
    int start = 0;

    int count = 1;

    for (int i = 1; i < S.toCharArray().length; i++) {

      if (S.charAt(i) == current) count++;
      else {

        if (count >= 3) {
          LinkedList<Integer> list1 = new LinkedList<>();
          list1.add(start);
          list1.add(i - 1);
          list.add(list1);
        }

        start = i;
        current = S.charAt(i);
        count = 1;
      }
    }

    if (count >= 3) {
      LinkedList<Integer> list1 = new LinkedList<>();
      list1.add(start);
      list1.add(S.toCharArray().length - 1);
      list.add(list1);
    }

    return list;
  }

  public List<List<Integer>> largeGroupPositions1(String S) {

    List<List<Integer>> ans = new ArrayList();
    int i = 0, N = S.length(); // i is the start of each group
    for (int j = 0; j < N; ++j) {
      if (j == N - 1 || S.charAt(j) != S.charAt(j + 1)) {
        // Here, [i, j] represents a group.
        if (j - i + 1 >= 3) ans.add(Arrays.asList(i, j));
        i = j + 1;
      }
    }

    return ans;
  }

  @Test
  public void findMaxAverage() {
    assertEquals(12.75, findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4));
  }

  public double findMaxAverage(int[] nums, int k) {

    int i = k;
    double maxAverage;
    int sum = 0;

    while (--i >= 0) sum += nums[i];

    maxAverage = (double) sum / k;

    for (int j = k; j < nums.length; j++) {

      sum += nums[j] - nums[j - k];
      maxAverage = Math.max(maxAverage, (double) sum / k);
    }

    return maxAverage;
  }

  @Test
  public void sortArrayByParity() {

    assertArrayEquals(new int[] {2, 4, 3, 1}, sortArrayByParity(new int[] {3, 1, 2, 4}));
  }

  public int[] sortArrayByParity(int[] A) {

    int[] ans = new int[A.length];
    int t = 0;

    for (int item : A) if (item % 2 == 0) ans[t++] = item;

    for (int value : A) if (value % 2 == 1) ans[t++] = value;

    return ans;
  }

  @Test
  public void lastStoneWeight() {
    assertEquals(1, lastStoneWeight(new int[] {2, 7, 4, 1, 8, 1}));
    assertEquals(0, lastStoneWeight(new int[] {2, 2}));
  }

  public int lastStoneWeight(int[] stones) {

    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
    for (int stone : stones) {
      queue.add(stone);
    }

    if (queue.size() == 1) return queue.poll();

    int x, y;

    while (queue.size() > 1) {

      x = queue.poll();

      if (queue.size() > 0) {

        y = queue.poll();

        if (x - y > 0) queue.add(x - y);
      } else break;
    }

    return queue.size() > 0 ? queue.poll() : 0;
  }

  @Test
  public void lemonadeChange() {
    assertEquals(true, lemonadeChange(new int[] {5, 5, 5, 10, 20}));
    assertEquals(false, lemonadeChange(new int[] {5, 5, 10, 10, 20}));
    assertEquals(false, lemonadeChange(new int[] {10, 10}));
  }

  public boolean lemonadeChange(int[] bills) {

    Map<Integer, Integer> map = new HashMap<>();

    int[] queue = new int[] {20, 10, 5};

    int lemonade = 5;

    for (int bill : bills) {

      int change = bill - lemonade;

      if (change == 0) {
        map.put(lemonade, map.getOrDefault(lemonade, 0) + 1);
      } else {

        int queueSize = queue.length;

        int i = 0;

        while (i < queueSize) {

          if (change >= queue[i]) {
            Integer q = map.getOrDefault(queue[i], 0);
            if (q != 0) {
              change -= queue[i];
              map.put(queue[i], map.getOrDefault(queue[i], 0) - 1);
              continue;
            }
          }

          i++;
        }

        if (change > 0) return false;

        map.put(bill, map.getOrDefault(bill, 0) + 1);
      }
    }

    return true;
  }

  @Test
  public void maxDistToClosest() {
    assertEquals(2, maxDistToClosest(new int[] {1, 0, 0, 0, 1, 0, 1}));
    assertEquals(3, maxDistToClosest(new int[] {1, 0, 0, 0}));
    assertEquals(1, maxDistToClosest(new int[] {1, 0}));
    assertEquals(1, maxDistToClosest(new int[] {0, 1}));
    assertEquals(1, maxDistToClosest(new int[] {0, 1, 0, 0, 1}));
    assertEquals(3, maxDistToClosest(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 1}));
    assertEquals(2, maxDistToClosest(new int[] {0, 0, 1}));
  }

  public int maxDistToClosest(int[] seats) {

    int solution = 0;
    int count = 0;
    boolean startWithZero = seats[0] == 0;

    for (int seat : seats) {

      if (seat == 0) count++;
      else if (seat == 1) {

        if (startWithZero) {
          solution = count;
          startWithZero = false;
        } else {
          solution = getSolution(solution, count);
        }

        count = 0;
      }
    }

    return Math.max(solution, count);
  }

  private int getSolution(int solution, int count) {
    if ((count & 1) == 1) return Math.max(solution, (count + 1) / 2);
    else return Math.max(solution, count / 2);
  }

  @Test
  public void decompressRLElist() {
    assertArrayEquals(new int[] {2, 4, 4, 4}, decompressRLElist(new int[] {1, 2, 3, 4}));
  }

  public int[] decompressRLElist(int[] nums) {

    int length = IntStream.range(0, nums.length).filter(i -> (i & 1) == 0).map(i -> nums[i]).sum();

    int[] sol = new int[length];
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums[i]; j++) {
        sol[count++] = nums[i + 1];
      }

      i++;
    }

    return sol;
  }

  @Test
  public void numSmallerByFrequency() {
    assertTrue(
        Arrays.equals(
            new int[] {1, 2},
            numSmallerByFrequency(
                new String[] {"bbb", "cc"}, new String[] {"a", "aa", "aaa", "aaaa"})));

    assertTrue(
        Arrays.equals(
            new int[] {1}, numSmallerByFrequency(new String[] {"cbd"}, new String[] {"zaaaz"})));

    assertTrue(
        Arrays.equals(
            new int[] {6, 1, 1, 2, 3, 3, 3, 1, 3, 2},
            numSmallerByFrequency(
                new String[] {
                  "bba",
                  "abaaaaaa",
                  "aaaaaa",
                  "bbabbabaab",
                  "aba",
                  "aa",
                  "baab",
                  "bbbbbb",
                  "aab",
                  "bbabbaabb"
                },
                new String[] {
                  "aaabbb",
                  "aab",
                  "babbab",
                  "babbbb",
                  "b",
                  "bbbbbbbbab",
                  "a",
                  "bbbbbbbbbb",
                  "baaabbaab",
                  "aa"
                })));
  }

  public int[] numSmallerByFrequency(String[] queries, String[] words) {

    int[] count = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      count[i] = getWordMaxCount(word);
    }

    Supplier<IntStream> countSuppl = () -> Arrays.stream(count);
    int[] solution = new int[queries.length];

    for (int i = 0; i < queries.length; i++) {
      String query = queries[i];

      int max = getWordMaxCount(query);

      solution[i] = (int) countSuppl.get().filter(c -> c > max).count();
    }

    return solution;
  }

  /**
   * @param word
   * @return
   */
  private int getWordMaxCount(String word) {

    char min = (char) word.chars().min().getAsInt();

    int count = 0;
    for (int j = 0; j < word.length(); j++) {
      char c = word.charAt(j);
      if (min == c) count++;
    }
    return count;
  }

  @Test
  public void arrayRankTransform() {
    assertTrue(
        Arrays.equals(new int[] {4, 1, 2, 3}, arrayRankTransform(new int[] {40, 10, 20, 30})));
    assertTrue(Arrays.equals(new int[] {1, 1, 1}, arrayRankTransform(new int[] {100, 100, 100})));
    assertTrue(
        Arrays.equals(
            new int[] {5, 3, 4, 2, 8, 6, 7, 1, 3},
            arrayRankTransform(new int[] {37, 12, 28, 9, 100, 56, 80, 5, 12})));
  }

  public int[] arrayRankTransform(int[] arr) {

    int length = arr.length;
    int[] solution = new int[length];
    int[] copy = arr.clone();

    arr = IntStream.of(arr).distinct().sorted().toArray();

    for (int i = 0; i < length; i++) {
      int position = Array.binarySearch(arr, 0, arr.length, copy[i]);
      solution[i] = position + 1;
    }

    return solution;
  }

  @Test
  public void validMountainArray() {
    assertEquals(false, validMountainArray(new int[] {2, 1}));
    assertEquals(false, validMountainArray(new int[] {3, 5, 5}));
    assertEquals(true, validMountainArray(new int[] {0, 3, 2, 1}));
    assertEquals(false, validMountainArray(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
    assertEquals(false, validMountainArray(new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    assertEquals(false, validMountainArray(new int[] {1, 7, 9, 5, 4, 1, 2}));
    assertEquals(false, validMountainArray(new int[] {3, 7, 20, 14, 15, 14, 10, 8, 2, 1}));
  }

  public boolean validMountainArray(int[] A) {

    if (A.length < 3) return false;

    int value = A[0];
    boolean increase = true;

    for (int i = 1; i < A.length; i++) {
      if (increase) {
        if (A[i] == value || (A[i] <= value && i < 2)) return false;
        else if (A[i] < value) increase = false;

      } else {
        if (A[i] >= value) return false;
      }

      value = A[i];
    }

    return !increase;
  }

  @Test
  public void distributeCandies() {
    assertEquals(3, distributeCandies(new int[] {1, 1, 2, 2, 3, 3}));
    assertEquals(2, distributeCandies(new int[] {1, 1, 2, 3}));
    assertEquals(1, distributeCandies(new int[] {1, 1}));
    assertEquals(0, distributeCandies(new int[] {1, 1, 2}));
  }

  public int distributeCandies(int[] candies) {

    int length = candies.length;

    if ((length & 1) == 1) return 0;

    Set<Integer> kinds = new HashSet<Integer>();

    for (int i = 0; i < candies.length; i++) kinds.add(candies[i]);

    return Math.min(length / 2, kinds.size());
  }

  @Test
  public void hasGroupsSizeX() {
    assertEquals(true, hasGroupsSizeX(new int[] {1, 2, 3, 4, 4, 3, 2, 1}));
    assertEquals(false, hasGroupsSizeX(new int[] {1, 1, 1, 2, 2, 2, 3, 3}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1, 1, 2, 2, 2, 3, 3, 3}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4}));
    assertEquals(false, hasGroupsSizeX(new int[] {1}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1, 2, 2, 2, 2, 3, 3}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1, 1, 1, 2, 2}));
    assertEquals(true, hasGroupsSizeX(new int[] {1, 1, 1, 1, 2, 2, 2, 2, 2, 2}));
    assertEquals(true, hasGroupsSizeX(new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}));
    assertEquals(
        true, hasGroupsSizeX(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}));
    assertEquals(
        true,
        hasGroupsSizeX(
            new int[] {
              0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7
            }));
    assertEquals(
        true,
        hasGroupsSizeX(
            new int[] {
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5
            }));
    assertEquals(
        true,
        hasGroupsSizeX(
            new int[] {
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5
            }));

    assertEquals(
        true,
        hasGroupsSizeX(
            new int[] {
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
              0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
              1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
              2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
              3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4,
              4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
              4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
              4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
              4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
              5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
              6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
              7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
              9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
              9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
              9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
              9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
              11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
              14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
              14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
              15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
              15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16,
              16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
              16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17,
              17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
              17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
              18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18
            }));
  }

  public boolean hasGroupsSizeX(int[] deck) {

    if (deck.length == 1) return false;

    int[] array = new int[10001];

    for (int i = 0; i < deck.length; i++) array[deck[i]] = ++array[deck[i]];

    Set<Integer> set = new HashSet<>();

    Arrays.parallelSort(array);

    boolean isCommonGroup;
    for (int value : array) {

      if (value == 0) continue;

      if (value < 2) return false;

      if (set.size() == 0) findCommonMin(value, 101, set);

      isCommonGroup = false;

      for (Integer integer : set) {
        if (value % integer == 0) {

          isCommonGroup = true;
          break;
        }
      }

      if (!isCommonGroup) return false;
    }

    return true;
  }

  /**
   * @param i
   * @param j
   * @return
   */
  private void findCommonMin(int i, int j, Set<Integer> set) {
    for (int j2 = 2; j2 < j; j2++) {
      if (i % j2 == 0) set.add(j2);
    }
  }

  @Test
  public void pivotIndex() {
    assertEquals(3, pivotIndex(new int[] {1, 7, 3, 6, 5, 6}));
    assertEquals(-1, pivotIndex(new int[] {1, 2, 3}));
    assertEquals(0, pivotIndex(new int[] {0, 0, 0}));
    assertEquals(3, pivotIndex(new int[] {-1, -1, 0, -1, -1, -1}));
  }

  public int pivotIndex(int[] nums) {

    int sum = IntStream.of(nums).sum();
    int leftsum = 0;
    int index = nums.length;

    for (int i = 0; i < nums.length; ++i) {
      if (leftsum == sum - leftsum - nums[i]) return i;
      leftsum += nums[i];
    }

    return index == nums.length ? -1 : index;
  }

  @Test
  public void findLengthOfLCIS() {
    assertEquals(3, findLengthOfLCIS(new int[] {1, 3, 5, 4, 7}));
    assertEquals(1, findLengthOfLCIS(new int[] {2, 2, 2, 2, 2}));
    assertEquals(3, findLengthOfLCIS(new int[] {1, 3, 5, 7}));
  }

  private int findLengthOfLCIS(int[] nums) {

    if (nums.length == 0) return 0;

    int count = 0;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) count++;
    }

    return count == 0 ? 1 : count;
  }

  @Test
  public void checkPossibility() {

    assertEquals(true, checkPossibility(new int[] {4, 2, 3}));
    assertEquals(false, checkPossibility(new int[] {4, 2, 1}));
    assertEquals(true, checkPossibility(new int[] {-1, 4, 2, 3}));
    assertEquals(false, checkPossibility(new int[] {3, 4, 2, 3}));
    assertEquals(true, checkPossibility(new int[] {2, 3, 3, 2, 4}));
    assertEquals(true, checkPossibility(new int[] {1, 3, 2}));
    assertEquals(false, checkPossibility(new int[] {1, 5, 4, 6, 7, 10, 8, 9}));
  }

  private boolean checkPossibility(int[] nums) {

    for (int i = 1; i < nums.length; i++) {

      if (nums[i] < nums[i - 1]) {

        if (i + 1 < nums.length && nums[i] > nums[i + 1]) return false;
        else if (i + 1 < nums.length && nums[i + 1] < nums[i - 1]) nums[i - 1] = nums[i];
        else nums[i] = nums[i - 1];

        break;
      }
    }

    for (int i = 1; i < nums.length; i++) {

      if (nums[i] < nums[i - 1]) return false;
    }

    return true;
  }

  @Test
  public void findUnsortedSubarray() {
    assertEquals(5, findUnsortedSubarray(new int[] {2, 6, 4, 8, 10, 9, 15}));
    assertEquals(2, findUnsortedSubarray(new int[] {2, 6, 4, 8, 9, 10, 15}));
    assertEquals(0, findUnsortedSubarray(new int[] {1, 2, 3, 4}));
  }

  private int findUnsortedSubarray(int[] nums) {

    int[] copy = nums.clone();
    Arrays.parallelSort(nums);

    int minIndex = nums.length;
    int maxIndex = 0;

    for (int i = 0; i < copy.length; i++) {
      if (copy[i] != nums[i]) {
        minIndex = Math.min(i, minIndex);
        maxIndex = Math.max(i, maxIndex);
      }
    }

    return (maxIndex - minIndex >= 0 ? maxIndex - minIndex + 1 : 0);
  }

  @Test
  public void canPlaceFlowers() {
    assertEquals(false, canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 2));
    assertEquals(true, canPlaceFlowers(new int[] {1, 0, 0, 0, 1}, 1));
    assertEquals(false, canPlaceFlowers(new int[] {1, 0, 0, 0, 0, 1}, 2));
    assertEquals(true, canPlaceFlowers(new int[] {1, 0, 0, 0, 1, 0, 0}, 2));
    assertEquals(true, canPlaceFlowers(new int[] {0, 0, 1, 0, 0}, 1));
  }

  private boolean canPlaceFlowers(int[] flowerbed, int n) {

    int countZero = 0;

    if (flowerbed[0] == 0) countZero++;

    for (int i = 0; i < flowerbed.length; i++) {

      if (flowerbed[i] == 1) {
        countZero--;
        n = n - (countZero / 2);
        countZero = 0;
      } else {
        countZero++;
      }
    }

    n = n - (countZero / 2);

    return n <= 0;
  }

  @Test
  public void findPairs() {
    assertEquals(2, findPairs(new int[] {3, 1, 4, 1, 5}, 2));
    assertEquals(4, findPairs(new int[] {1, 2, 3, 4, 5}, 1));
    assertEquals(1, findPairs(new int[] {1, 3, 1, 5, 4}, 0));
  }

  public int findPairs(int[] nums, int k) {
    int count = 0;

    Arrays.parallelSort(nums);

    for (int i = 0; i < nums.length - 1; i++) {

      int diff = nums[i] + k;

      if (Array.binarySearch(nums, i + 1, nums.length, diff) >= 0) count++;

      while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;
    }

    return count;
  }

  @Test
  public void findMaxConsecutiveOnes() {
    assertEquals(3, findMaxConsecutiveOnes(new int[] {1, 1, 0, 1, 1, 1}));
    assertEquals(1, findMaxConsecutiveOnes(new int[] {1}));
  }

  private int findMaxConsecutiveOnes(int[] nums) {
    int count = 0;
    int max = 0;

    for (int i = 0; i < nums.length; i++) {
      while (i < nums.length && nums[i++] == 1) count++;

      max = Math.max(max, count);

      i--;
      count = 0;
    }

    return max;
  }

  @Test
  public void nextGreaterElement() {
    assertEquals(
        true,
        Arrays.equals(
            new int[] {-1, 3, -1},
            nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2})));
    assertEquals(
        true,
        Arrays.equals(
            new int[] {3, -1}, nextGreaterElement(new int[] {2, 4}, new int[] {1, 2, 3, 4})));
  }

  private int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int length = nums2.length - 1;

    for (int i = 0; i < nums1.length; i++) {
      int value = nums1[i];

      nums1[i] = -1;
      while (length >= 0 && nums2[length] != value) {
        if (nums2[length] > value) nums1[i] = nums2[length];
        length--;
      }

      length = nums2.length - 1;
    }

    return nums1;
  }

  @Test
  public void findDisappearedNumbers() {
    assertEquals(
        true,
        Arrays.asList(new Integer[] {5, 6})
            .equals(findDisappearedNumbers(new int[] {4, 3, 2, 7, 8, 2, 3, 1})));
    assertEquals(
        true, Arrays.asList(new Integer[] {2}).equals(findDisappearedNumbers(new int[] {1, 3, 3})));
  }

  private List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> dis = new ArrayList<Integer>();

    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]) - 1;
      if (nums[index] < 0) continue;
      else nums[index] = -nums[index];
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) dis.add(i + 1);
    }

    return dis;
  }

  @Test
  public void isMonotonic() {
    assertEquals(true, isMonotonic(new int[] {1, 2, 2, 3}));
    assertEquals(true, isMonotonic(new int[] {6, 5, 4, 4}));
    assertEquals(false, isMonotonic(new int[] {1, 3, 2}));
    assertEquals(true, isMonotonic(new int[] {1, 2, 4, 5}));
    assertEquals(true, isMonotonic(new int[] {1, 1, 1}));
  }

  private boolean isMonotonic(int[] array) {
    int value = array[0];
    int length = array.length;

    int monDecr = 0;
    int monIncr = 0;

    for (int i = 1; i < length; i++) {
      if (array[i] <= value) monDecr++;

      if (array[i] >= value) monIncr++;

      if (monDecr == i || monIncr == i) value = array[i];
      else return false;
    }

    return true;
  }

  @Test
  public void arrayPairSum() {
    assertEquals(4, arrayPairSum(new int[] {1, 4, 3, 2}));
  }

  private int arrayPairSum(int[] nums) {
    Arrays.parallelSort(nums);

    return IntStream.range(0, nums.length).filter(i -> i % 2 == 0).map(i -> nums[i]).sum();
  }

  @Test
  public void findRestaurant() {
    assertEquals(
        true,
        Arrays.equals(
            new String[] {"Shogun"},
            findRestaurant(
                new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[] {
                  "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"
                })));

    assertEquals(
        true,
        Arrays.equals(
            new String[] {"Shogun"},
            findRestaurant(
                new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[] {"KFC", "Shogun", "Burger King"})));
  }

  private String[] findRestaurant(String[] list1, String[] list2) {

    Map<String, Integer> restMap = new HashMap<>();

    for (int i = 0; i < list1.length; i++) restMap.put(list1[i], i);

    int minIndex = Integer.MAX_VALUE;
    Set<String> set = new HashSet<>();

    for (int i = 0; i < list2.length; i++) {
      int pos = restMap.getOrDefault(list2[i], -1);

      if (pos != -1) {

        int sum = pos + i;

        if (sum < minIndex) {
          set = new HashSet<String>();
          set.add(list2[i]);
          minIndex = sum;

        } else if (sum == minIndex) set.add(list2[i]);
      }
    }

    String[] sol = new String[set.size()];
    set.toArray(sol);

    return sol;
  }

  @Test
  public void kthLargest() {
    KthLargest kThLargest = new KthLargest(3, new int[] {4, 5, 8, 2});
    assertEquals(4, kThLargest.add(3));
    assertEquals(5, kThLargest.add(5));
    assertEquals(5, kThLargest.add(10));
    assertEquals(8, kThLargest.add(9));
    assertEquals(8, kThLargest.add(4));
  }

  class KthLargest {
    private Queue<Integer> pq;
    private int size;

    public KthLargest(int k, int[] nums) {
      pq = new PriorityQueue<>();
      size = k;
      for (int i : nums) {
        pq.offer(i);
      }
      while (pq.size() > k) {
        pq.poll();
      }
    }

    public int add(int val) {
      if (pq.size() < size) {
        pq.offer(val);
      } else {
        pq.offer(val);
        pq.poll();
      }
      return pq.peek();
    }
  }

  @Test
  public void findKthLargest() {
    assertEquals(5, findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
    assertEquals(4, findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }

  private int findKthLargest(int[] nums, int k) {

    Arrays.parallelSort(nums);

    k--;
    int length = nums.length;

    int value = nums[--length];

    int z = --length;

    for (int i = z; i >= 0; i--) {
      int tmp = nums[i];

      value = tmp;
      k--;

      if (k == 0) return value;
    }

    return nums[nums.length - 1];
  }

  @Test
  public void thirdMax() {
    assertEquals(1, thirdMax(new int[] {3, 2, 1}));
    assertEquals(2, thirdMax(new int[] {1, 2}));
    assertEquals(1, thirdMax(new int[] {2, 2, 3, 1}));
  }

  private int thirdMax(int[] nums) {
    Arrays.parallelSort(nums);

    int k = 2;
    int length = nums.length;

    int value = nums[--length];

    int z = --length;

    for (int i = z; i >= 0; i--) {
      int tmp = nums[i];

      if (tmp == value) continue;

      value = tmp;
      k--;

      if (k == 0) return value;
    }

    return nums[nums.length - 1];
  }

  @Test
  public void findRelativeRanks() {
    assertTrue(
        Arrays.equals(
            new String[] {"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"},
            findRelativeRanks(new int[] {5, 4, 3, 2, 1})));

    assertTrue(
        Arrays.equals(
            new String[] {"Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"},
            findRelativeRanks(new int[] {10, 3, 8, 9, 4})));
  }

  private String[] findRelativeRanks(int[] nums) {
    int length = nums.length;
    int[] copy = Arrays.copyOf(nums, length);

    String[] out = new String[nums.length];

    Arrays.parallelSort(nums);
    Map<Integer, String> map = new HashMap<>();

    int k = 0;

    for (int i = length - 1; i >= 0; i--) {
      if (k < 3) {
        map.put(nums[i], k == 0 ? "Gold Medal" : k == 1 ? "Silver Medal" : "Bronze Medal");
        k++;
      } else {
        map.put(nums[i], Integer.toString(length - i));
      }
    }

    for (int i = 0; i < length; i++) out[i] = map.get(copy[i]);

    return out;
  }

  @Test
  public void sortedSquares() {

    assertTrue(
        Arrays.equals(new int[] {0, 1, 9, 16, 100}, sortedSquares(new int[] {-4, -1, 0, 3, 10})));
    assertTrue(
        Arrays.equals(new int[] {0, 1, 9, 16, 100}, sortedSquares1(new int[] {-4, -1, 0, 3, 10})));
  }

  private int[] sortedSquares(int[] A) {
    int[] solution = new int[A.length];

    for (int i = 0; i < A.length; i++) solution[i] = A[i] * A[i];

    Arrays.parallelSort(solution);
    return solution;
  }

  /**
   * Two pointers
   *
   * @param A
   * @return
   */
  public int[] sortedSquares1(int[] A) {
    int N = A.length;
    int j = 0;
    while (j < N && A[j] < 0) j++;
    int i = j - 1;

    int[] ans = new int[N];
    int t = 0;

    while (i >= 0 && j < N) {
      if (A[i] * A[i] < A[j] * A[j]) {
        ans[t++] = A[i] * A[i];
        i--;
      } else {
        ans[t++] = A[j] * A[j];
        j++;
      }
    }

    while (i >= 0) {
      ans[t++] = A[i] * A[i];
      i--;
    }
    while (j < N) {
      ans[t++] = A[j] * A[j];
      j++;
    }

    return ans;
  }

  @Test
  public void merge() {
    int[] nums1 = new int[] {1, 2, 3, 0, 0, 0};
    int[] nums2 = new int[] {2, 5, 6};

    merge(nums1, 3, nums2, 3);

    assertArrayEquals(new int[] {1, 2, 2, 3, 5, 6}, nums1);
  }

  private void merge(int[] nums1, int m, int[] nums2, int n) {

    int tmp = 0;

    for (int i = 0; i < n; i++) {
      int j = tmp;

      while (nums2[i] >= nums1[j] && j < m) j++;

      tmp = j;

      int oldValue = nums1[j];
      nums1[j] = nums2[i];

      while (j++ < nums1.length - 1) {
        int toReplace = nums1[j];
        nums1[j] = oldValue;
        oldValue = toReplace;
      }

      m++;
    }
  }

  @Test
  public void flipAndInvertImage() {
    assertEquals(
        true,
        Arrays.deepEquals(
            new int[][] {{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 0, 1, 0}},
            flipAndInvertImage(
                new int[][] {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}})));

    assertEquals(
        true,
        Arrays.deepEquals(
            new int[][] {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}},
            flipAndInvertImage(new int[][] {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}})));
  }

  private int[][] flipAndInvertImage(int[][] A) {

    for (int i = 0; i < A.length; i++) {
      int[] array = A[i];
      int length = array.length;

      int middle = length / 2;

      for (int j = length; j > middle; j--) {
        int start = array[length - j];
        int end = array[j - 1];

        array[length - j] = end == 0 ? 1 : 0;
        array[j - 1] = start == 0 ? 1 : 0;
      }
    }
    return A;
  }

  @Test
  public void commonChars() {
    assertEquals(
        Arrays.asList(new String[] {"e", "l", "l"}),
        commonChars(new String[] {"bella", "label", "roller"}));

    assertEquals(
        Arrays.asList(new String[] {"c", "o"}), commonChars(new String[] {"cool", "lock", "cook"}));
  }

  private List<String> commonChars(String[] A) {

    String first = A[0];

    ConcurrentHashMap<Character, Integer> firstWordFreqMap =
        new ConcurrentHashMap<Character, Integer>();

    for (int i = 0; i < first.length(); i++)
      firstWordFreqMap.put(first.charAt(i), firstWordFreqMap.getOrDefault(first.charAt(i), 0) + 1);

    for (int i = 1; i < A.length; i++) {
      Map<Character, Integer> count = new HashMap<Character, Integer>();
      String string = A[i];

      for (int j = 0; j < string.length(); j++)
        count.put(string.charAt(j), count.getOrDefault(string.charAt(j), 0) + 1);

      for (Entry<Character, Integer> entrySet : firstWordFreqMap.entrySet()) {
        int freq = entrySet.getValue();
        int mapFreq = count.getOrDefault(entrySet.getKey(), 0);

        if (mapFreq == 0) firstWordFreqMap.remove(entrySet.getKey());
        else firstWordFreqMap.put(entrySet.getKey(), Math.min(freq, mapFreq));
      }
    }

    List<String> result = new ArrayList<String>();

    for (Entry<Character, Integer> entrySet : firstWordFreqMap.entrySet()) {
      for (int i = 0; i < entrySet.getValue(); i++)
        result.add(Character.toString(entrySet.getKey()));
    }

    return result;
  }

  @Test
  public void intersect() {
    assertEquals(
        true, Arrays.equals(new int[] {2, 2}, intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2})));
    assertEquals(
        true,
        Arrays.equals(new int[] {4, 9}, intersect(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4})));
  }

  private int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    List<Integer> sol = new ArrayList<Integer>();

    for (int i = 0; i < nums2.length; i++) {
      int freq = map.getOrDefault(nums2[i], 0) + 1;
      map.put(nums2[i], freq);
    }

    for (int i = 0; i < nums1.length; i++) {
      int found = map.getOrDefault(nums1[i], 0);

      if (found == 0) continue;
      else {
        sol.add(nums1[i]);
        map.put(nums1[i], --found);
      }
    }

    return sol.stream().mapToInt(i -> i).toArray();
  }

  @Test
  public void stackUsingQueues() {

    MyStack stack = new MyStack();

    stack.push(1);
    stack.push(2);
    stack.push(3);

    assertEquals(3, stack.pop());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
    assertEquals(true, stack.empty());
  }

  class MyStack {
    Deque<Integer> deque;
    Deque<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
      deque = new LinkedList<Integer>();
      queue = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
      int size = queue.size();

      for (int i = 0; i < size; i++) deque.add(queue.poll());

      queue.push(x);

      size = deque.size();

      for (int i = 0; i < size; i++) queue.add(deque.poll());
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
      return queue.pop();
    }

    /** Get the top element. */
    public int top() {
      return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
      return queue.isEmpty();
    }
  }

  @Test
  public void queueUsingStack() {
    MyQueue queueWithStacks = new MyQueue();
    queueWithStacks.push(1);
    queueWithStacks.push(2);

    assertEquals(1, queueWithStacks.peek());
    assertEquals(1, queueWithStacks.pop());
    assertEquals(false, queueWithStacks.empty());
  }

  class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
      stack1 = new Stack<Integer>();
      stack2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
      int size = stack1.size();

      for (int i = 0; i < size; i++) stack2.push(stack1.pop());

      stack1.push(x);

      size = stack2.size();

      for (int i = 0; i < size; i++) stack1.push(stack2.pop());
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
      return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
      return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
      return stack1.isEmpty();
    }
  }

  @Test
  public void numArray() {
    nums = new int[] {-2, 0, 3, -5, 2, -1};

    assertEquals(1, sumRange(0, 2));
    assertEquals(-1, sumRange(2, 5));
    assertEquals(-3, sumRange(0, 5));
  }

  public int sumRange(int i, int j) {
    return IntStream.range(0, nums.length).filter(v -> v >= i && v <= j).map(v -> nums[v]).sum();
  }

  @Test
  public void containsNearbyAlmostDuplicate() {
    assertEquals(true, containsNearbyAlmostDuplicate(new int[] {1, 2, 3, 1}, 3, 0));
    assertEquals(true, containsNearbyAlmostDuplicate(new int[] {1, 0, 1, 1}, 1, 2));
    assertEquals(false, containsNearbyAlmostDuplicate(new int[] {1, 5, 9, 1, 5, 9}, 2, 3));
    assertEquals(false, containsNearbyAlmostDuplicate(new int[] {-1, 2147483647}, 1, 2147483647));
  }

  private boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];

      for (int j = 0; j < nums.length && i != j; j++) {
        int valueIn = nums[j];

        long diff = (long) value - (long) valueIn;

        if (Math.abs(diff) <= t && Math.abs(i - j) <= k) return true;
      }
    }

    return false;
  }

  @Test
  public void containsNearbyDuplicate() {
    assertEquals(true, containsNearbyDuplicate(new int[] {1, 2, 4, 1}, 3));
    assertEquals(true, containsNearbyDuplicate(new int[] {1, 0, 1, 1}, 1));
    assertEquals(false, containsNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 2));
    assertEquals(false, containsNearbyDuplicate(new int[] {}, 0));
  }

  private boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums.length == 0) return false;

    int value = nums[0];
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    map.put(value, 0);

    for (int i = 1; i < nums.length; i++) {
      value = nums[i];

      int tmp = map.getOrDefault(value, -1);

      if (-1 != tmp && i - tmp <= k) return true;

      map.put(value, i);
    }

    return false;
  }

  @Test
  public void containsDuplicate() {
    assertEquals(true, containsDuplicate(new int[] {1, 2, 3, 1}));
    assertEquals(false, containsDuplicate(new int[] {1, 2, 3, 4}));
    assertEquals(true, containsDuplicate(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
  }

  private boolean containsDuplicate(int[] nums) {
    int size = (int) IntStream.of(nums).distinct().count();

    return nums.length != size;
  }

  @Test
  public void rob() {
    assertEquals(4, rob(new int[] {1, 2, 3, 1}));
    assertEquals(12, rob(new int[] {2, 7, 9, 3, 1}));
    assertEquals(4, rob(new int[] {2, 1, 1, 2}));
    assertEquals(1, rob(new int[] {1}));
    assertEquals(14, rob2(new int[] {4, 1, 2, 7, 5, 3, 1}));
    assertEquals(39, rob3(new int[] {6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3}));
  }

  private int rob(int[] nums) {
    if (nums == null || nums.length == 0) return 0;

    if (nums.length == 1) return nums[0];

    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }

    return dp[nums.length - 1];
  }

  private int rob2(int[] nums) {

    if (nums == null || nums.length == 0) return 0;

    int even = 0;
    int odd = 0;

    for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
        even += nums[i];
        even = even > odd ? even : odd;
      } else {
        odd += nums[i];
        odd = even > odd ? even : odd;
      }
    }

    return even > odd ? even : odd;
  }

  private int rob3(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int[] mem = new int[nums.length + 1];
    Arrays.fill(mem, -1);

    mem[0] = 0;

    return helper(nums.length, mem, nums);
  }

  private int helper(int size, int[] mem, int[] nums) {
    if (size < 1) {
      return 0;
    }

    if (mem[size] != -1) {
      return mem[size];
    }

    // two cases
    int firstSelected = helper(size - 2, mem, nums) + nums[nums.length - size];
    int firstUnselected = helper(size - 1, mem, nums);

    return mem[size] = Math.max(firstSelected, firstUnselected);
  }

  @Test
  public void rotateArray() {
    int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
    rotate(array, 3);
    assertEquals(true, Arrays.equals(new int[] {5, 6, 7, 1, 2, 3, 4}, array));

    array = new int[] {1, 2, 3, 4, 5, 6, 7};
    rotate(array, 10);
    assertEquals(true, Arrays.equals(new int[] {5, 6, 7, 1, 2, 3, 4}, array));

    array = new int[] {-1, -100, 3, 99};
    rotate(array, 2);
    assertEquals(true, Arrays.equals(new int[] {3, 99, -1, -100}, array));

    array = new int[] {1, 2, 3, 4, 5, 6};
    rotate1(array, 3);
    assertEquals(true, Arrays.equals(new int[] {4, 5, 6, 1, 2, 3}, array));
  }

  private void rotate1(int[] nums, int k) {
    int length = nums.length;

    while (length < k) k = k - length;

    int[] newArray = new int[length];
    for (int i = 0; i < length; i++) {
      int value = nums[i];
      int newPosition = i + k < length ? i + k : i + k - length;
      newArray[newPosition] = value;
    }

    for (int i = 0; i < newArray.length; i++) {
      nums[i] = newArray[i];
    }
  }

  private void rotate(int[] nums, int k) {
    int length = nums.length;

    while (length < k) k = k - length;

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < length; i++) {
      if (null != map.get(i)) {

        int value = map.get(i);
        int newPosition = i + k < length ? i + k : i + k - length;

        if (map.get(newPosition) == null) map.put(newPosition, nums[newPosition]);

        nums[newPosition] = value;
        continue;
      }

      int value = nums[i];
      int newPosition = i + k < length ? i + k : i + k - length;

      map.put(newPosition, nums[newPosition]);

      nums[newPosition] = value;
    }
  }

  @Test
  public void intersection() {
    assertEquals(
        true, Arrays.equals(new int[] {2}, intersection(new int[] {1, 2, 2, 1}, new int[] {2, 2})));
    assertEquals(
        true,
        Arrays.equals(
            new int[] {4, 9}, intersection(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4})));
  }

  private int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set = IntStream.of(nums1).boxed().distinct().collect(Collectors.toSet());
    Set<Integer> set2 = IntStream.of(nums2).boxed().distinct().collect(Collectors.toSet());

    set.retainAll(set2);

    return set.stream().mapToInt(x -> x).toArray();
  }

  @Test
  public void majorityElement() {
    assertEquals(3, majorityElement(new int[] {3, 2, 3}));
    assertEquals(2, majorityElement1(new int[] {2, 2, 1, 1, 1, 2, 2}));
    assertEquals(3, majorityElementDC(new int[] {3, 3, 4}));
  }

  private int majorityElement(int[] nums) {

    Map<Integer, Integer> mapToFreq = new HashMap<Integer, Integer>();
    int max = 0;
    int maxFreq = 0;

    for (int i = 0; i < nums.length; i++) {
      int value = nums[i];
      int freq = mapToFreq.getOrDefault(value, 0) + 1;

      if (freq > maxFreq) {

        max = value;
        maxFreq = freq;
      }

      mapToFreq.put(value, freq);
    }

    return max;
  }

  private int majorityElement1(int[] nums) {

    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  private int countInRange(int[] nums, int num, int lo, int hi) {
    int count = 0;
    for (int i = lo; i <= hi; i++) {
      if (nums[i] == num) {
        count++;
      }
    }
    return count;
  }

  private int majorityElementRec(int[] nums, int lo, int hi) {
    // base case; the only element in an array of size 1 is the majority
    // element.
    if (lo == hi) {
      return nums[lo];
    }

    // recurse on left and right halves of this slice.
    int mid = (hi - lo) / 2 + lo;
    int left = majorityElementRec(nums, lo, mid);
    int right = majorityElementRec(nums, mid + 1, hi);

    // if the two halves agree on the majority element, return it.
    if (left == right) {
      return left;
    }

    // otherwise, count each element and return the "winner".
    int leftCount = countInRange(nums, left, lo, hi);
    int rightCount = countInRange(nums, right, lo, hi);

    return leftCount > rightCount ? left : right;
  }

  private int majorityElementDC(int[] nums) {
    return majorityElementRec(nums, 0, nums.length - 1);
  }

  @Test
  public void minStack() {
    MinStack stack = new MinStack();

    stack.push(-2);
    stack.push(0);
    stack.push(-3);

    assertEquals(-3, stack.getMin());

    stack.pop();

    assertEquals(0, stack.top());
    assertEquals(-2, stack.getMin());

    stack = new MinStack();

    stack.push(1);
    stack.push(2);

    assertEquals(2, stack.top());

    assertEquals(1, stack.getMin());

    stack.pop();

    assertEquals(1, stack.getMin());
    assertEquals(1, stack.top());
  }

  class MinStack {

    private Stack<Integer> stack;
    private int min = Integer.MAX_VALUE;

    public MinStack() {
      stack = new Stack<Integer>();
    }

    public void push(int x) {
      min = Math.min(min, x);
      stack.push(x);
    }

    public void pop() {
      stack.pop();
      min = Integer.MAX_VALUE;

      for (Integer x : stack) min = Math.min(min, x);
    }

    public int top() {
      return stack.peek();
    }

    public int getMin() {
      return min;
    }
  }

  @Test
  public void convertToTitle() {
    assertEquals("A", convertToTitle(1));
    assertEquals("AB", convertToTitle(28));
    assertEquals("BB", convertToTitle(54));

    assertEquals("ZY", convertToTitle(701));
    // assertEquals("ZZ", convertToTitle(702));
    assertEquals("AAA", convertToTitle(703));
    assertEquals("AAAA", convertToTitle(1406));
  }

  public String convertToTitle(int n) {

    char[] alphaBet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    String out = "";

    while (n > 0) {

      int div = n / 26;

      int position = div > 0 ? div - 1 : n - 1;

      out += Character.toString((char) alphaBet[position]).toUpperCase();

      if (div == 0) break;

      n = n - (div * 26);
    }

    return out;
  }

  @Test
  public void missingNumber() {
    assertEquals(2, missingNumber(new int[] {3, 0, 1}));
    assertEquals(8, missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}));
    assertEquals(2, missingNumber(new int[] {0, 1}));
    assertEquals(0, missingNumber(new int[] {1}));
  }

  public int missingNumber(int[] nums) {
    Arrays.sort(nums);

    int length = nums.length;

    if (nums[0] != 0) return 0;

    if (nums[length - 1] != length) return length;

    int start = 0;
    int first = nums[start];

    while (start + 1 < length) {
      int next = nums[++start];

      if (next - first != 1) return next - 1;

      first = next;
    }

    return length - 2;
  }

  @Test
  public void singleNumber() {
    assertEquals(1, singleNumber(new int[] {2, 2, 1}));

    assertEquals(4, singleNumber(new int[] {4, 1, 2, 1, 2}));

    assertEquals(4, singleNumber1(new int[] {4, 1, 2, 1, 2}));

    assertEquals(4, singleNumber2(new int[] {4, 1, 2, 1, 2}));

    assertEquals(4, singleNumber3(new int[] {4, 1, 2, 1, 2}));
  }

  public int singleNumber(int[] nums) {

    Set<Integer> set = new HashSet<Integer>();

    int length = nums.length;

    for (int value : nums) {
      if (!set.contains(value)) set.add(value);
      else set.remove(value);
    }

    return set.iterator().next();
  }

  private int singleNumber1(int[] nums) {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    int length = nums.length;

    for (int i = 0; i < length; i++) {
      int value = nums[i];

      if (null == map.get(value)) map.put(value, 1);
      else map.remove(value);
    }

    return map.entrySet().iterator().next().getKey();
  }

  private int singleNumber2(int[] nums) {

    Set<Integer> set = Arrays.stream(nums).distinct().boxed().collect(Collectors.toSet());
    int sum = Arrays.stream(nums).sum();

    return 2 * (set.stream().reduce(0, (a, b) -> a + b) - sum);
  }

  /**
   * If we take XOR of zero and some bit, it will return that bit a⊕0=a
   *
   * <p>If we take XOR of two same bits, it will return 0 a⊕a=0
   *
   * <p>a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
   *
   * @param nums
   * @return
   */
  private int singleNumber3(int[] nums) {

    int length = nums.length;

    int value = 0;

    for (int i = 0; i < length; i++) value ^= nums[i];

    return value;
  }

  @SuppressWarnings("serial")
  @Test
  public void addToArrayForm() {
    assertEquals(
        new ArrayList<Integer>() {
          {
            add(1);
            add(2);
            add(3);
            add(4);
          }
        },
        addToArrayForm(new int[] {1, 2, 0, 0}, 34));
  }

  public List<Integer> addToArrayForm(int[] digits, int K) {

    String value = Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining(""));

    BigInteger retValue = new BigInteger(value).add(new BigInteger(Integer.valueOf(K).toString()));

    return retValue
        .toString()
        .chars()
        .mapToObj(c -> Integer.valueOf(String.valueOf((char) c)))
        .collect(Collectors.toList());
  }

  @Test
  public void plusOne() {
    assertTrue(Arrays.equals(new int[] {1, 2, 4}, plusOne(new int[] {1, 2, 3})));
    assertTrue(Arrays.equals(new int[] {4, 3, 2, 2}, plusOne(new int[] {4, 3, 2, 1})));
    assertTrue(Arrays.equals(new int[] {1, 0, 0, 0}, plusOne(new int[] {9, 9, 9})));
    assertTrue(
        Arrays.equals(
            new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 1},
            plusOne(new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1, 0})));
  }

  private int[] plusOne(int[] digits) {
    String value = Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining(""));

    BigInteger retValue = new BigInteger(value).add(new BigInteger("1"));

    return retValue
        .toString()
        .chars()
        .mapToObj(c -> String.valueOf((char) c))
        .mapToInt(Integer::valueOf)
        .toArray();
  }

  @Test
  public void updateMatrix() {
    assertTrue(
        Arrays.deepEquals(
            new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
            updateMatrix1(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}})));
    assertTrue(
        Arrays.deepEquals(
            new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}},
            updateMatrix(new int[][] {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}})));

    assertTrue(
        Arrays.deepEquals(
            new int[][] {},
            updateMatrix(
                new int[][] {
                  {0, 1, 0, 1, 1},
                  {1, 1, 0, 0, 1},
                  {0, 0, 0, 1, 0},
                  {1, 0, 1, 1, 1},
                  {1, 0, 0, 0, 1}
                })));
  }

  private int[][] updateMatrix(int[][] matrix) {

    int rows = matrix.length;

    if (rows == 0) return matrix;

    int cols = matrix[0].length;

    int[][] dist = new int[][] {};

    Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();

    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
        if (matrix[i][j] == 0) {
          dist[i][j] = 0;
          q.add(new Pair<Integer, Integer>(i, j)); // Put all 0s in the queue.
        }

    int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while (!q.isEmpty()) {
      Pair<Integer, Integer> curr = q.peek();
      q.poll();
      for (int i = 0; i < 4; i++) {
        int new_r = curr.getKey() + dir[i][0], new_c = curr.getValue() + dir[i][1];
        if (new_r >= 0 && new_c >= 0 && new_r < rows && new_c < cols) {
          if (dist[new_r][new_c] > dist[curr.getKey()][curr.getValue()] + 1) {
            dist[new_r][new_c] = dist[curr.getKey()][curr.getValue()] + 1;
            q.add(new Pair<Integer, Integer>(new_r, new_c));
          }
        }
      }
    }
    return dist;
  }

  private int[][] updateMatrix1(int[][] matrix) {
    int matrixLength = matrix.length;

    for (int i = 0; i < matrixLength; i++) {
      int[] row = matrix[i];

      int rowLength = row.length;

      for (int j = 0; j < rowLength; j++) {

        if (matrix[i][j] == 0) continue;

        boolean hasFoundDistance = false;

        while (!hasFoundDistance) {

          int distance = 1;
          if (j - distance >= 0 && (matrix[i][j - distance] == 0)
              || (i - distance >= 0 && matrix[i - distance][j] == 0)
              || (j + distance < rowLength && matrix[i][j + distance] == 0)
              || (i + distance < matrixLength && matrix[i + distance][j] == 0)) {
            matrix[i][j] = distance;
            hasFoundDistance = true;
          } else if (i - distance >= 0
              && (j - distance >= 0 && matrix[i - distance][j - distance] == 0
                  || j + distance < rowLength && matrix[i - distance][j + distance] == 0)) {
            matrix[i][j] = distance + 1;
            hasFoundDistance = true;
          } else if (i + distance < matrixLength
              && (j - distance >= 0 && matrix[i + distance][j - distance] == 0
                  || j + distance < rowLength && matrix[i + distance][j + distance] == 0)) {
            matrix[i][j] = distance + 1;
            hasFoundDistance = true;
          } else distance++;
        }
      }
    }

    return matrix;
  }

  @Test
  public void maxProfitII() {
    assertEquals(7, maxProfitII(new int[] {7, 1, 5, 3, 6, 4}));
    assertEquals(22, maxProfitII(new int[] {7, 1, 8, 5, 18, 20}));
    assertEquals(7, maxProfitII(new int[] {7, 3, 5, 1, 6, 4}));
    assertEquals(4, maxProfitII(new int[] {1, 2, 3, 4, 5}));
    assertEquals(0, maxProfitII(new int[] {7, 6, 4, 3, 1}));
    assertEquals(4, maxProfitII(new int[] {1, 2, 3, 4, 5, 4, 3, 2}));
    assertEquals(11, maxProfitII(new int[] {7, 6, 4, 3, 1, 5, 3, 10}));
  }

  private int maxProfitII(int[] prices) {

    // to hold profit

    int profit = 0;
    // to hold current minimum
    int currentMin = Integer.MAX_VALUE;

    // iterate thru prices
    for (int price : prices) {
      // check if this price is smaller than current min
      if (price < currentMin) {
        // set new current min
        currentMin = price;
      } else {
        // check if we can profit by selling stock on this day
        int currentProfit = price - currentMin;
        // check if we can profit
        if (currentProfit > 0) {
          // add to profit
          profit += currentProfit;
          // set current min to current price
          currentMin = price;
        }
      }
    }

    // result
    return profit;
  }

  @Test
  public void maxProfit() {
    assertEquals(5, maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
    assertEquals(5, maxProfitLinear(new int[] {7, 3, 5, 1, 6, 4}));
  }

  private int maxProfitLinear(int[] prices) {

    if (prices.length == 0) return 0;

    int maxProf = 0;
    int minBuy = prices[0];

    for (int i = 1; i < prices.length; i++) {
      int sell = prices[i];

      if (minBuy >= sell) {

        minBuy = sell;
        continue;
      }

      maxProf = Math.max(sell - minBuy, maxProf);
    }

    return maxProf;
  }

  private int maxProfit(int[] prices) {

    int maxProf = 0;

    for (int i = 0; i < prices.length; i++) {
      int buy = prices[i];

      for (int j = i; j < prices.length; j++) {
        int sell = prices[j];

        if (buy < sell) maxProf = Math.max(maxProf, sell - buy);
      }
    }

    return maxProf;
  }

  @Test
  public void maxSubArray() {
    assertEquals(9, maxSubArrayGreedy(new int[] {-1, 2, -3, 4, 5}));
    assertEquals(9, maxSubArrayPrefSum(new int[] {-1, 2, -3, 4, 5}));
    assertEquals(9, maxSubArrayDivideConquer(new int[] {-1, 2, -3, 4, 5}));
  }

  public int maxSubArrayGreedy(int[] nums) {

    int start = 0;
    int end = start;
    int sum = Integer.MIN_VALUE;
    int currentSum = 0;
    while (start < nums.length && end < nums.length) {
      currentSum += nums[end];
      sum = Math.max(sum, currentSum);
      if (currentSum < 0) {
        start = end + 1;
        end = start;
        currentSum = 0;
      } else {
        end++;
      }
    }
    return sum;
  }

  public int maxSubArrayPrefSum(int[] nums) {
    int currentSum = 0;
    int sum = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      currentSum = Math.max(currentSum + nums[i], nums[i]);
      sum = Math.max(currentSum, sum);
    }

    return sum;
  }

  public int maxSubArrayDivideConquer(int[] nums) {
    return maxSubArrayWithBoundries(nums, 0, nums.length - 1);
  }

  private int maxSubArrayWithBoundries(int[] nums, int start, int end) {
    if (start == end) return nums[start];

    int mid = (start + end) / 2;
    int leftMax = maxSubArrayWithBoundries(nums, start, mid);
    int rightMax = maxSubArrayWithBoundries(nums, mid + 1, end);
    int crossingMidMax = crossingMidMax(nums, start, end);
    return Math.max(Math.max(leftMax, rightMax), crossingMidMax);
  }

  private int crossingMidMax(int[] nums, int start, int end) {
    int mid = (start + end) / 2;
    int leftMidMax = Integer.MIN_VALUE;
    int rightMidMax = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid; i >= start; i--) {
      sum += nums[i];
      leftMidMax = Math.max(leftMidMax, sum);
    }
    sum = 0;
    for (int i = mid + 1; i <= end; i++) {
      sum += nums[i];
      rightMidMax = Math.max(rightMidMax, sum);
    }
    return leftMidMax + rightMidMax;
  }

  @Test
  public void firstBadVersion() {
    int n = 5;
    k = 4;
    assertEquals(k, firstBadVersion(n - 1));

    n = 10;
    k = 4;
    assertEquals(k, firstBadVersion(n - 1));

    n = 10;
    k = 2;
    assertEquals(k, firstBadVersion(n - 1));

    n = 1000000;
    k = 1;
    assertEquals(k, firstBadVersion(n - 1));

    n = 1000000;
    k = 1000000;
    assertEquals(k, firstBadVersion(n));

    n = 0;
    k = 0;
    assertEquals(k, firstBadVersion(n - 1));

    n = 2126753390;
    k = 1702766719;
    assertEquals(k, firstBadVersion(n - 1));
  }

  public int firstBadVersion(int n) {

    if (n <= 0) return 0;

    long left = 0;
    long right = n;

    while (left <= right) {

      Long middle = (left + right) / 2;

      boolean isBadVersion = isBadVersion(middle.intValue());

      if (isBadVersion) {

        if (isBadVersion(middle.intValue() - 1)) right = middle - 1;
        else return middle.intValue();
      } else left = middle + 1;
    }

    return 0;
  }

  private boolean isBadVersion(int n) {
    if (n >= k) return true;

    return false;
  }

  @Test
  public void searchInsert() {
    assertEquals(0, searchInsert(new int[] {1, 3, 5, 6}, 0));
    assertEquals(2, searchInsert(new int[] {1, 3, 5, 6}, 5));
    assertEquals(1, searchInsert(new int[] {1, 3, 5, 6}, 2));
    assertEquals(4, searchInsert(new int[] {1, 3, 5, 6}, 7));
    assertEquals(1, searchInsert(new int[] {1}, 2));
    assertEquals(0, searchInsert(new int[] {}, 1));
    assertEquals(0, searchInsert(new int[] {1, 3}, 0));
    assertEquals(1, searchInsert(new int[] {1, 3}, 2));
    assertEquals(0, searchInsert(new int[] {1, 3}, 1));
    assertEquals(2, searchInsert(new int[] {1, 4, 6, 7, 8, 9}, 6));
  }

  private int searchInsert(int[] nums, int target) {
    return search(nums, 0, nums.length - 1, target);
  }

  int search(int nums[], int left, int right, int target) {
    if (nums.length == 0) return 0;

    // Binary search
    int middle;
    while (left <= right) {

      middle = (left + right) / 2;

      int middleEl = nums[middle];

      if (middleEl == target) return middle;
      else if (middleEl < target) left = middle + 1;
      else right = middle - 1;
    }

    int length = nums.length;

    // If index not exists it is first, last or iterate to find the match between
    // lower and higher
    if (target >= nums[length - 1]) return length;
    else if (target <= nums[0]) return 0;
    else {

      int tmp = 0;

      while (tmp < length) {
        if (target > nums[tmp] && target < nums[tmp + 1]) return tmp + 1;

        tmp++;
      }
    }

    return -1;
  }

  @Test
  public void moveZeroes() {
    int[] nums = new int[] {0, 1, 0, 3, 12};
    moveZeroes(nums);

    assertEquals(true, Arrays.equals(nums, new int[] {1, 3, 12, 0, 0}));
    nums = new int[] {0, 1, 0};
    moveZeroes(nums);
    assertEquals(true, Arrays.equals(nums, new int[] {1, 0, 0}));
  }

  private void moveZeroes(int[] nums) {
    for (int i = nums.length - 1; i >= 0; i--) {
      if (nums[i] == 0) {
        int j = i;
        while (j + 1 < nums.length) {
          int tmp = nums[j + 1];
          nums[j + 1] = nums[j];
          nums[j] = tmp;
          j++;
        }
      }
    }
  }

  @Test
  public void removeElement() {
    int[] array = new int[] {3, 2, 2, 3};
    int size = removeElement(array, 3);

    assertEquals(true, Arrays.equals(new int[] {2, 2}, Arrays.copyOfRange(array, 0, size)));

    array = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
    size = removeElement(array, 2);

    assertEquals(
        true, Arrays.equals(new int[] {0, 1, 3, 0, 4}, Arrays.copyOfRange(array, 0, size)));
  }

  private int removeElement(int[] nums, int val) {
    LinkedList<Integer> b =
        IntStream.of(nums)
            .filter(e -> e != val)
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new));
    int size = b.size();

    for (int i = 0; i < size; i++) {
      nums[i] = b.poll();
    }

    return size;
  }

  @Test
  public void removeDuplicates() {
    assertEquals(2, removeDuplicates(new int[] {1, 1, 2}));
    assertEquals(5, removeDuplicates(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    assertEquals(1, removeDuplicates(new int[] {1, 1}));
  }

  private int removeDuplicates(int[] nums) {
    LinkedList<Integer> b =
        IntStream.of(nums).distinct().boxed().collect(Collectors.toCollection(LinkedList::new));
    int size = b.size();

    for (int i = 0; i < size; i++) {
      nums[i] = b.poll();
    }

    return size;
  }

  @Test
  public void combinationSum() throws JsonProcessingException {
    assertEquals(
        "[[2,2,3],[7]]", mapper.writeValueAsString(combinationSum(new int[] {2, 3, 6, 7}, 7)));
    assertEquals(
        "[[2,2,2,2],[2,3,3],[3,5]]",
        mapper.writeValueAsString(combinationSum(new int[] {2, 3, 5}, 8)));
    assertEquals("[[1,1]]", mapper.writeValueAsString(combinationSum(new int[] {1}, 2)));
    assertEquals(
        "[[2,2,3],[2,5]]", mapper.writeValueAsString(combinationSum(new int[] {2, 3, 5}, 7)));
    assertEquals(
        "[[7,7,2,2],[7,2,2,2,2,3],[7,2,3,3,3],[2,2,2,2,2,2,2,2,2],[2,2,2,2,2,2,3,3],[2,2,2,3,3,3,3],[3,3,3,3,3,3]]",
        mapper.writeValueAsString(combinationSum(new int[] {7, 2, 3}, 18)));
  }

  private List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();
    helper(0, target, 0, candidates, new ArrayList<>(), ans);
    return ans;
  }

  private void helper(
      int index,
      int target,
      int sum,
      int[] candidates,
      List<Integer> list,
      List<List<Integer>> ans) {
    if (index > candidates.length - 1 || sum > target) return;

    if (sum == target && list.size() != 0) ans.add(new ArrayList<>(list));

    for (int i = index; i < candidates.length; i++) {
      list.add(candidates[i]);
      helper(i, target, sum + candidates[i], candidates, list, ans);
      list.remove(list.size() - 1);
    }
  }

  @Test
  public void twoSumSortedArrays() {
    assertArrayEquals(new int[] {1, 2}, twoSumSortedArrays(new int[] {2, 7, 11, 15}, 9));
    assertArrayEquals(new int[] {1, 2}, twoSumSortedArrays(new int[] {0, 0, 3, 4}, 0));
    assertArrayEquals(new int[] {1, 2}, twoSumSortedArrays(new int[] {-1, 0}, -1));
    assertArrayEquals(
        new int[] {4, 5}, twoSumSortedArrays(new int[] {1, 2, 3, 4, 4, 9, 56, 90}, 8));
  }

  public int[] twoSumSortedArrays(int[] nums, int target) {
    int length = nums.length - 1;

    for (int i = 0; i < length; i++) {
      int num = nums[i];

      int fit = target - num;

      int search = Array.binarySearch(nums, i + 1, length, fit);

      if (search >= 0) return new int[] {i + 1, search + 1};
    }

    return new int[2];
  }

  @Test
  public void twoSum() {
    assertArrayEquals(new int[] {0, 1}, twoSum(new int[] {2, 7, 11, 15}, 9));
  }

  private int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> keyToIndex = new HashMap<Integer, Integer>();
    int size = nums.length;

    int[] solution = new int[2];
    for (int i = 0; i < size; i++) {
      int diff = target - nums[i];

      if (null != keyToIndex.get(diff)) {
        solution[0] = keyToIndex.get(diff);
        solution[1] = i;
        break;
      }

      keyToIndex.put(nums[i], i);
    }

    return solution;
  }
}
