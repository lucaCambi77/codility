/**
 * 
 */
package it.cambi.codility.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import it.cambi.codility.coreJava.StringTest;

/**
 * @author luca
 *
 */
@TestMethodOrder(Alphanumeric.class)
public class LeetCodeStringTest {

	/**
	 * Letter to primitive map
	 */
	@SuppressWarnings("serial")
	private static Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>() {
		{

			put('a', 2);
			put('b', 3);
			put('c', 5);
			put('d', 7);
			put('e', 11);
			put('f', 13);
			put('g', 17);
			put('h', 19);
			put('i', 23);
			put('j', 29);
			put('k', 31);
			put('l', 37);
			put('m', 41);
			put('n', 43);
			put('o', 47);
			put('p', 53);
			put('q', 59);
			put('r', 61);
			put('s', 67);
			put('t', 71);
			put('u', 73);
			put('v', 79);
			put('w', 83);
			put('x', 89);
			put('y', 97);
			put('z', 101);

		}
	};

	@Test
	public void lengthOfLongestSubstring() {
		assertEquals(3, lengthOfLongestSubstring("abc", new LinkedList<Character>(), 0));
		assertEquals(3, lengthOfLongestSubstring("abcabcbb", new LinkedList<Character>(), 0));
		assertEquals(1, lengthOfLongestSubstring("bbbbb", new LinkedList<Character>(), 0));
		assertEquals(3, lengthOfLongestSubstring("pwwkew", new LinkedList<Character>(), 0));
		assertEquals(6, lengthOfLongestSubstring("rphqbgbse".toCharArray()));
		assertEquals(3, lengthOfLongestSubstring("abcabcbb"));

	}

	// Too slow
	private int lengthOfLongestSubstring(String chars, LinkedList<Character> list, int position) {

		int maxLength = list.size();

		for (int i = position; i < chars.length(); i++) {

			char ch = chars.charAt(position);

			if (list.contains(ch))
				return maxLength;

			list.add(ch);
			int length1 = lengthOfLongestSubstring(chars, list, position + 1);
			list.pop();
			int length2 = lengthOfLongestSubstring(chars, list, position + 1);

			maxLength = Math.max(maxLength, Math.max(length2, length1));
		}

		return maxLength;
	}

	// Sliding window
	private int lengthOfLongestSubstring(char[] s) {

		Set<Character> seen = new HashSet<>();
		int longest = 0;
		int l = 0;
		
		for (int r = 0; r < s.length; r++) {
			while (seen.contains(s[r])) {
				seen.remove(s[l]);
				l++;
			}
			seen.add(s[r]);
			longest = Math.max(longest, r - l + 1);
		}
		return longest;
	}

    private int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
    
	@Test
	public void groupAnagrams() {
		groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
		groupAnagrams1(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
		groupAnagrams2(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });

	}

	private List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0)
			return new ArrayList<List<String>>();

		Map<String, List<String>> ans = new HashMap<String, List<String>>();
		int[] count = new int[26];

		for (String s : strs) {
			Arrays.fill(count, 0);
			for (char c : s.toCharArray())
				count[c - 'a']++;

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}

			String key = sb.toString();
			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<String>());
			ans.get(key).add(s);
		}

		return new ArrayList<List<String>>(ans.values());
	}

	private List<List<String>> groupAnagrams1(String[] strs) {

		HashMap<OrderedChars, List<String>> map = new HashMap<OrderedChars, List<String>>();

		for (String str : strs) {
			char[] chars = str.toCharArray();

			Arrays.parallelSort(chars);
			OrderedChars chars1 = new OrderedChars(chars);

			List<String> anagrams = map.getOrDefault(chars1, new ArrayList<String>());
			anagrams.add(str);

			map.put(chars1, anagrams);
		}

		return new ArrayList<List<String>>(map.values());
	}

	private List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0)
			return new ArrayList<List<String>>();

		Map<String, List<String>> ans = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String key = String.valueOf(ca);

			if (!ans.containsKey(key))
				ans.put(key, new ArrayList<>());

			ans.get(key).add(s);
		}
		return new ArrayList<List<String>>(ans.values());
	}

	class OrderedChars {
		private char[] chars;

		/**
		 * @param chars
		 */
		public OrderedChars(char[] chars) {
			super();
			this.chars = chars;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(chars);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			OrderedChars other = (OrderedChars) obj;

			if (!Arrays.equals(chars, other.chars))
				return false;
			return true;
		}

	}

	@Test
	public void isAnagram() {
		assertEquals(true, isAnagram("hello", "llohe"));
		assertEquals(true, isAnagram("anagram", "nagaram"));
		assertEquals(true, isAnagram1("anagram", "nagaram"));
		assertEquals(false, isAnagram("rat", "car"));

	}

	public boolean isAnagram(String s, String t) {
		AtomicReference<BigInteger> valueHolder = new AtomicReference<>();
		valueHolder.set(new BigInteger("1"));

		s.chars().forEach(c -> {
			valueHolder.getAndAccumulate(BigInteger.valueOf(alphabetMap.get((char) c)),
					(previous, x) -> previous.multiply(x));
		});

		AtomicReference<BigInteger> valueHolder1 = new AtomicReference<>();
		valueHolder1.set(new BigInteger("1"));

		t.chars().forEach(c -> {
			valueHolder1.getAndAccumulate(BigInteger.valueOf(alphabetMap.get((char) c)),
					(previous, x) -> previous.multiply(x));
		});

		return valueHolder.get().compareTo(valueHolder1.get()) == 0;
	}

	public boolean isAnagram1(String s, String t) {
		char[] c = s.toCharArray();
		char[] c1 = t.toCharArray();

		Arrays.parallelSort(c);
		Arrays.parallelSort(c1);

		return Arrays.equals(c, c1);

	}

	@Test
	public void findWords() {
		assertEquals(true, Arrays.equals(new String[] { "Alaska", "Dad" },
				findWords(new String[] { "Hello", "Alaska", "Dad", "Peace" })));
	}

	private String[] findWords(String[] words) {
		String[] patterns = new String[] { "[asdfghjkl]", "[qwertyuiop]", "[zxcvbnm]" };
		ArrayList<String> out = new ArrayList<String>();

		for (int i = 0; i < words.length; i++) {

			for (int j = 0; j < patterns.length; j++) {

				Pattern pattern = Pattern.compile(patterns[j], Pattern.CASE_INSENSITIVE);

				int count = 0;

				Matcher m = pattern.matcher(words[i]);

				while (m.find())
					count++;

				if (count == 0)
					continue;
				else if (count == words[i].length()) {

					out.add(words[i]);
					break;
				} else
					break;
			}
		}

		return out.stream().sorted().toArray(String[]::new);
	}

	@Test
	public void fizzBuzz() {
		assertEquals(Arrays.asList(new String[] { "1", "2", "Fizz", "4", "Buzz" }), fizzBuzz(5));
	}

	public List<String> fizzBuzz(int n) {
		List<String> ans = new LinkedList<String>();

		@SuppressWarnings("serial")
		HashMap<Integer, String> fizzBizzDict = new HashMap<Integer, String>() {
			{
				put(3, "Fizz");
				put(5, "Buzz");
			}
		};

		for (int num = 1; num <= n; num++) {

			String numAnsStr = "";

			for (Integer key : fizzBizzDict.keySet()) {

				if (num % key == 0)
					numAnsStr += fizzBizzDict.get(key);
			}

			if (numAnsStr.isEmpty())
				numAnsStr += Integer.toString(num);

			ans.add(numAnsStr);
		}

		return ans;
	}

	@Test
	public void isSubsequence() {
		assertEquals(true, isSubsequence("abc", "ahbgdc"));
		assertEquals(false, isSubsequence("axc", "ahbgdc"));
		assertEquals(true, isSubsequence("", "ahbgdc"));

	}

	public boolean isSubsequence(String s, String t) {
		int sLength = s.length();
		int tLength = t.length();

		if (sLength == 0)
			return true;

		int k = 0;
		char c = s.charAt(k);

		for (int i = 0; i < tLength; i++) {
			if (t.charAt(i) == c) {
				if (++k == sLength)
					return true;

				c = s.charAt(k);
			}

		}

		return false;
	}

	@Test
	public void checkRecord() {
		assertEquals(true, checkRecord("PPALLPL"));
		assertEquals(false, checkRecord("PPALLL"));
		assertEquals(true, checkRecord("A"));
		assertEquals(false, checkRecord("LLL"));
		assertEquals(false, checkRecord("ALLAPPL"));

	}

	private boolean checkRecord(String str) {
		Pattern pattern = Pattern.compile("(.*A.*){2,}|(L){3}+");
		Matcher m = pattern.matcher(str);

		if (m.find())
			return false;

		return true;
	}

	@Test
	public void findTheDifference() {
		assertEquals('e', findTheDifference("abcd", "abcde"));
	}

	private char findTheDifference(String s, String t) {

		int sum = s.chars().sum();
		int sum1 = t.chars().sum();

		return (char) (sum1 - sum);
	}

	@Test
	public void canConstruct() {
		assertEquals(false, canConstruct("a", "b"));
		assertEquals(false, canConstruct("aa", "ab"));
		assertEquals(true, canConstruct("aa", "aab"));

	}

	private boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < magazine.length(); i++) {
			map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);

		}

		for (int i = 0; i < ransomNote.length(); i++) {
			int freq = map.getOrDefault(ransomNote.charAt(i), 0);

			if (freq == 0)
				return false;
			else
				map.put(ransomNote.charAt(i), --freq);
		}

		return true;
	}

	@Test
	public void detectCapitalUse() {
		assertEquals(true, detectCapitalUse("USA"));
		assertEquals(true, detectCapitalUse("leetcode"));
		assertEquals(true, detectCapitalUse("Google"));
		assertEquals(true, detectCapitalUse("Google"));
		assertEquals(false, detectCapitalUse("FlaG"));

	}

	private boolean detectCapitalUse(String word) {

		boolean areAllCapitals = true;
		boolean areAllLowerLetters = true;
		boolean isFirstCapital = Character.isUpperCase(word.charAt(0)) ? true : false;

		for (int i = 1; i < word.length(); i++) {
			char c = word.charAt(i);

			if (Character.isUpperCase(c))
				areAllLowerLetters = false;
			else
				areAllCapitals = false;

			if (areAllCapitals || areAllLowerLetters)
				continue;
			else
				break;
		}

		return (areAllCapitals && isFirstCapital) || (areAllLowerLetters && isFirstCapital)
				|| (areAllLowerLetters && !isFirstCapital);

	}

	@Test
	public void uniqueMorseRepresentations() {
		assertEquals(2, uniqueMorseRepresentations(new String[] { "gin", "zen", "gig", "msg" }));

		assertEquals(1, uniqueMorseRepresentations(new String[] { "rwjje", "aittjje", "auyyn", "lqtktn", "lmjwn" }));
	}

	private int uniqueMorseRepresentations(String[] words) {

		String[] alphabetMorse = new String[] { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
				"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
				"-.--", "--.." };

		Set<String> seen = new HashSet<String>();

		for (String word : words) {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < word.length(); i++)
				sb.append(alphabetMorse[word.charAt(i) - 'a']);

			String morsetransform = sb.toString();

			seen.add(morsetransform);

		}

		return seen.size();
	}

	@Test
	public void reverseWords() {
		assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
	}

	private String reverseWords(String s) {

		StringBuilder solution = new StringBuilder();

		String[] split = s.split("\\s");

		for (String string : split) {
			char[] array = string.toCharArray();

			int length = string.length() - 1;

			for (int i = length; i > length / 2; i--) {
				char c1 = array[i];
				char c2 = array[length - i];

				array[i] = c2;
				array[length - i] = c1;
			}

			solution.append(array).append(" ");
		}

		return solution.toString().trim();
	}

	@Test
	public void reverseStr() {
		assertEquals("bacde", reverseStr("abcde", 2));
		assertEquals("bacdfeg", reverseStr("abcdefg", 2));
		assertEquals("bacdfegh", reverseStr("abcdefgh", 2));
		assertEquals("cbad", reverseStr("abcd", 3));
		assertEquals("a", reverseStr("a", 2));
		assertEquals("ba", reverseStr("ab", 2));
		assertEquals("abcdefg", reverseStr("abcdefg", 1));
	}

	private String reverseStr(String s, int k) {

		Queue<Character> queue = s.chars().boxed().map(i -> (char) i.intValue())
				.collect(Collectors.toCollection(LinkedList::new));
		Stack<Character> reverse = new Stack<Character>();

		StringBuilder sb = new StringBuilder();
		StringBuilder solution = new StringBuilder();

		int step = 2 * k;

		int i = 0;
		int queueSize = queue.size();

		while (!queue.isEmpty()) {

			if (i < k) {
				reverse.push(queue.poll());
				i++;
				continue;
			} else if (i < step) {
				i++;
				sb.append(queue.poll());
				continue;
			} else {
				i = 0;

				int stackSize = reverse.size();

				for (int ii = 0; ii < stackSize; ii++)
					solution.append(reverse.pop());

				solution.append(sb.toString());

				sb = new StringBuilder();
				reverse.clear();

				int remaining = queueSize - queue.size();

				if (remaining < k) {

					while (!queue.isEmpty())
						reverse.push(queue.poll());

					stackSize = reverse.size();

					for (int ii = 0; ii < stackSize; ii++)
						solution.append(reverse.pop());

					break;

				} else if (remaining < 2 * k && remaining >= k) {

					int tmp = -1;

					while (++tmp < k)
						reverse.push(queue.poll());

					stackSize = reverse.size();

					for (int ii = 0; ii < stackSize; ii++)
						solution.append(reverse.pop());

					while (!queue.isEmpty())
						sb.append(queue.poll());

					solution.append(sb.toString());

					sb = new StringBuilder();

					break;

				}
			}
		}

		if (!reverse.isEmpty())
			while (!reverse.isEmpty())
				solution.append(reverse.pop());

		if (sb.length() > 0)
			solution.append(sb.toString());

		return solution.toString();
	}

	@Test
	public void removeDuplicates() {
		assertEquals("ca", removeDuplicates("abbaca"));

	}

	private String removeDuplicates(String str) {

		Stack<Character> stack = new Stack<Character>();
		stack.push(str.charAt(0));

		for (int i = 1; i < str.length(); i++) {
			if (!stack.isEmpty() && str.charAt(i) == stack.peek())
				stack.pop();
			else
				stack.push(str.charAt(i));
		}

		return stack.stream().map(i -> Character.toString(i)).collect(Collectors.joining(""));
	}

	@Test
	public void firstUniqChar() {
		assertEquals(0, firstUniqChar("leetcode"));
		assertEquals(2, firstUniqChar("loveleetcode"));
		assertEquals(-1, firstUniqChar("lllcc"));
		assertEquals(-1, firstUniqChar(
				"sdnvlbkrmtbollujsdjfjfppksravjkwwsimlmdtcmiilpjibjhcppluisqbqfwrjjlrapsmcwrsrnfrmtjrffpuuqwonqfjfqxellpvmcfmhxccljqlvboioelpfcawrxlwsajfaiehutvogduhobwgpogvatpbvoaognbepqnkhkjsvqmfaghavopppcjbjunuaeotpkbfsmeqikjflakgjexnqqgxnsdjolbjbvhreighxhkihwphexwqufasjakmrdrpwciefaiqsaifmcfdeidhmjekoorvcuxtejlrfscrjekfkcnsdhhsxenhkuanafdpnsjnepdmvvrhbxwuhmrkenrcwbadsiulpvcklhlburudrbbskokwnwqktwjxstsvebvpqcugxjebcivudojorntphtscxhoxlhteuqunhvrsndtabfpdqdcsuqmdbeiiexdkaqtgkncfatlawrudbausifpsicibgrwastcxkjasmrmtrchbwlkxnbsxeaurtfdwmjqkgikcctgccsisgwlkkwflvahasltfusxogtbejrderxbqshacgsrvtqpgdcwmuwuejcmqanirwthsaxihlowgwjeegdltsujtrfuhjnqjjefqjwubiktuglirmgcdqfolvmrilkrvtlrtntujffbrtfiwdtvtfrkfnwklfulecxtkvqpgcrqvgjxbebjlbiopbnhqbdhhxsmgpdojhupxnfkmqsjlmcserhsiwhvelcufuvqrvpwhckexvutuwgfbgnqsmpmfuqkrvwggskovlqtqnqxvpghqoomplnnkmuhkbwpaxuhrkphbgwopxikisuegmxhbjocwhumauhiegojccvibwfdrdnahnwduvnuxjcuncrcgwwrjhxmdemnuxcjspjisgduuoojpxluvehoqpctncxniatxqckhmggtwgjihfnxdhbjwjhuqwuoveibqppqrmxrmaddukgcxpubmvgidcvwenbisercjiwvogdsgaqgsmahrlhgguanubvdptiofskqehtugwjdtgmcfdbpcovmvkntwsmtlcvrpmjclmsasnjtbfolgggthxjxplmkoafjodoevblwxbhctberkobjdqbidfsktudhcnsrexmetkowvxmrxvixbnfgrpeeuwpmskmbjvfdvjnippnedtbupouibddxahrhwdofdlvsnmwxakbsbxfrxlagdqpexnakowwnlkvcmnxmiogtwldgiepsigitkbnklunqqivlrkbwhuofmgfccjkrxffdamqbuglhcovmmnewgfptkabfwlqgrfhnburpbtajjmnfwmamehrdjkaalalxvwwaglidkkthgtuxwjehjegvxcclkrrudbknoqvgqfobarlsetjpgdflhjxfrxdodudljkxadlhghxcfbjigrmfcoxwxsabfgqilathijexhuwjjevobvgpvgfrrvotilgdvskfoeqvlgnaofwgdxdtborgsqxrqkusaphbldgdsntgtumhsbcbjwfgsvfsmmuonpclpxjxwskficxigsdadxpjslhhvqehdddsrxgdfljlowxkilobxqroctficwgedsspktwvpdibnrevillfwrsxjmxvuhqxmvhkjtrgvhxxecpjnkdjisrcqgnwtkmwxscdhihvpjgueboghiuieeqqwbpkohrklmukmbjueqnkfgsdigxrdporegntvppdjqdghwfrmnuhncubwteturdvcuppsawqraabqaffjwtmhmcibhepauhthunhcnwlcdebbgxqqwkbreoqsdvvpxcutnhcqtpcfbnxpavcmcbvulobiorfqkmrphgssmjgblfsnxfslmrsxaukmqhhtgfxtsivihexpituqkrjntigltpvgsgcrcwbopgmlejngagtlriidpishuhavdqqcpfwrnbxtmghhlaqufcffjpwuueectmxntdnmepwxouwkcaddmxicipbrsnaukcalqrpvvsjasesgmwerrxtdewnhpuwebcktovuxxrxvretussdojcmhowaocqmkxepgtbuisubpcwrtdmxbgqnpuwdntclvvavejthrgspvfjeupmjlprekrgofcatvkofuafubevbidetgwkkuenjbkojnitulgghpowubdmxqlrxcbwxcouoejougudoxritmngxqmxaenpqhhblqmxkcvogxxbiaumbeumfpwgomcggxbbfmxmumpgqbxddhodqgekgnjrpkqgrpfpuxvnfjnwsdwdpexdrlexjlnredcxajnmjiaqvscwuhmxufawusutabdemknrffbguxaxmwdhokxjkvikrmeprwklbvlmgsxxvolirwcpxgbpqcprqjnjntcxrmdqdbroldtnfsghitjlavokiigcupuwnifngkgsiwhfjjxijqamavumcsqkvtpknjsugmxnciqiktgqkonsrdjjgorkswdjrojlsxdgkjvoljsjdfanswoiufdqjjjnjideducfxiwhtdtkemkfvxbrmwwwduqmmwonrlvcdntljijselbcavvmgcakxwaidruqmdkvucvcgrftetxenrhbbblgpqsaoietlnuweudmkghuummtjljqoswbkbfefexiqiqfjoornofnmnxxhscbvvkkpkruinugbvisuwerxjbgdhirgpcnoeglhswigcdfxelfmeqfhqkxlrhpvbksqmkdkkvabdnfcmjptbxdhdrikuvkojrbvixkhbfupkrnjfdoxvlfvrebbijtxoqltguiqihurmxawaimrxdrstnsqulqajfwtwjnlhrboupthvahjaprnimuormdlcsevjwrrngbaashntmbpxxtqbanvwdlpqhtaxeestdnnahuillicvebrobwlmihtdkfwhchwasgwfgsqnfgurgsjwhabkujamvjvriivnkslxakealslelwqkdcpjniwthpoxpqgbdlmvukwueuionfgbmlniwmfqbwrxwqsnwxridlmkaulwlhldcfkiefujkagrfwjjrqjptgxkxkdfwmosfhpjjdujkmugfmtupnntgbglcwskveiegaekewworsnnvahwvovltolgqawwixrjnsbuwoqqatxhfexjnlbiduecsiiapnjcimgovekjgflugprswurcnldaxgqbdgipbodjdgwgerjlhrombjuajcgihcwqdtvpxixmivcsihanqkgvvkekqdnjknjopssslkdascasmarrjsbvsxxcbfklrnctivnkduhmldgracfitjgvaqgnmieitubwvchurhtrlsvvchsknpxfqviojmsniqgqwlpdnjppifewefrwimsejhpdbcnnblbamohnotsfewfvowrmtaittermlafnnlspgnfcmgfuxmgkgiunglarhfhkaxbaucqfexslxqqtxxamgvajrnmhmlbvoxtmwxejweoqatldgwdmfmwxnxsmsmxmhjjrmsgdferqonwmldxrmngijxtjksrittuqwlwebxtebibnxvtesaqufhpwtmoiaxubccmuafdtbddobebehflkkngcfkxnqlrlsxhcuwcopvfirqhsemasptavjtuwdjdxfcixucamcahtxcgopnivmaeawbvmxqrmdthcqjvwdtrouaghsqalktudmlshoxmlvceebtrebwskptoofvttqwwodbsohqeeshxnwbtnujugimqshnqowacpmtxjdkboldpuajmceeftarseqgotubcjojxsjnljomwjnvnskqgbwlnlasodtbnckelorrbpvekfxttwntxxstjiqxqncqsdwssadgmvqjmqfnbfgoxovqnthpmdswslemogqumovfdjiroatbinobtdvickctnsmdfqpurxgwjbprbpgfqfniridebecitsnvaxretddoeikmdjpiivduimhcdbcttbwmptmnsakevnscuwemiwxnaeurgitfxpcwslsivgfquqotuxxhqxjdrlpltcehnvgnjillfwdjrkwhkuqdxbrgkxshjwvpwsmhdbnxkivjedkoaevpguqonqevsvxpspegjpsnutchuclqmaadfpvdqxgnepbgqmujtbkucqkmlaafhaqgecbnmehxqlltsiwijqddohvohukvjtwctpsurohmgcgagmrtdmxugouoiphicrjosmkagfwsgtimfhgkiefolaomqjjpbtbkarbcflncapmhxkensiobftdbgbiihjwxwhgimoletnhrundehcjntiutjptvekrdmnbemoajiglkthlxqposxgatcvkmixrxcmcevevpprhkvqfhusnihnxcbifxdixdamrkgxbaqxebscisvjphuxkhwleeftravmhfbiudtiahkkubbmtpgsarquiffljmkfmmmxxxpdfmflcvjucbfwbkrdrjmoxixgwbidjgnjlblsrmfrclauwrpbfikvjneqxlvvspnjbulkjqnkfgrtgwtwgcxpbcbdgstucchuhoortxjatdonjamblhsnxwgwegkbcpdjfiehotassiapxrmknxfgrdkbqxbujwjreivfctdrepotjictccwtbagfknbmipiesajdapcubosjwfvbniodwvcccqsrocjrchpagxnehpcjhrgxdjnxbsohjquutxmqbeavxtebqjpcdsndowsxbhjnwogfpwbtbjmrilwclekecjrnnfpsgunlrqkldixprfdnthnxrqiiuwmgixsqibaktiopolipkanjhhpsjnmwccmwafetiolslutjqicidsrmvlnqdlmnttkeefnbndocbobxkigfedjwtdgehkkwptgbmrvsiiqdomqutreufldqdauuokuahiiubadphgdurqpgdflwpticfafqhllekupokbrxetnnkjhotdvkbjitpucrlfgbqonghovdhrpicwqdscaqmwsudvlhibxasoqnqgilowrlibkgqufkokxvjribjferoljtwrkoledombvogamnbgavpkbqtdglxxaxqxknwbesowbukgfeniduhpfdirnwsjmshaspnhnpxafphwpfqcqljaftgrburjptseifkfoeopsicququtjklebxqrscdhptrdnomtxruuaxveswrcuqpnrqltplrefruvjlubaiqnxthcsxfbtgmcnorgjnjgjoeldubhjpstxnitmkkxulddeikciifotralvqtfbocgxujvhipmqicqaououffucdigvqsljwjhqbbrnaviovujxrbufmhjpufnedhrjogmctbaeldlijopkratcuflclooscrpbhbpvtxifjrfuukqqgsmmghedrdlhmrwrjlwrrftarpgdxhfdhvndjdcknvdkknmqdllnelpmdggfkfxjudxpothxqtffmhcxobfsuxwlanfcenedwmcaexkjjvtorhbgrferstptaeejowgrnpofeftrbeuwbthvnaaawgknkebepatlaemiohrxihivwfoucrapqrpjhmvbamnjhtcxrewdfghsvvvlpbgawdvxxnjbbbscefktvnrvrjrpjjtjepcjduggbfeerpexvjkemrcmdrgcxdspklklfechngfbrxkacxeblajjunwuccjnjnxwuehlmpgkapmnmphmvswetgjfapqlqobkcdgagbgvhskwtcllaakvbsaejjxqqfixmspwsdudnfmswaphujlegtbdxiwgqlfuvhcxjfkmlkclfhficnkqbbfqpeftjwgckmarcixiqamhbrmfkvqdssvedgeidqnbojhfoktwfpcxsneitattkixpdnlffrqrxmrteogpsifsqevxgonbbdvjhsdjphjkvxetvjamoctscmhlkiuewrojodsodmwtjskfjvktcgvvqpluivcokmbjisrcohrgdtsaxjcmfkmudnhkpfgvveahwxjwlbrbkedwavjrqawxlvmuirpsgxsmiiacqbonhepflcqicifghhtddihfvphxvhiwfhqrjuvgbxvkeipbxlkqhcfgtthkomqjtmhpblvqbejxwcgxnsdinirvqectsnpecolvwbpfecnphnjjxdegmqlrdiausuxxrxemjxllkaqcqcapnwpjfmdmvsjtvapsahsusdblkorhaqtbiudqxubenpnquchfrnnvqpjvefcxihcphdhmtxojurnvxrpqrjopedlrceklpavqugvvanifshpbnvxsdltrvwihblbiobuwmkmlhoihwjmlhoxefunmuaggukhrmuhpbrewqlhwiinumnxwhlviarhhbsefcjgmlsvvsdtlklqmvbqasarfkkbkicitiprmsghsoowwjshdjsqsomltccohkltufgbbmdcltfwpqorarbitswkrxjmghunicweruuwjshfveofmevjupwvrjxfogvwpwidvqoedsussasvathbgufgjsniaaopiktxvjlcdufueckisovcwsjuspdmneagffhwbwsbhirvushwsubkxrnputhlstcveqfkjhdsivqecsqxsgsdiiwxoetmsmucwlukegpdgecjnhwditjeihoursohuixxtfeoljforeamhlqkomfuipuooormpokkconfoqnsqojvowwusrwniiqnticleqvvnaibnmagnjwmdaarurvgqblcahmnttbxkqurauemoovkoppcwejphtmdpteamrnhsrsngsmlueafwocnglkihvmrgwevgsjhfbbnbtndpuhgvlrlowqjwarvhgdvdwnwkajbxbrmwgjwrpvnumetvenfidjorlsedhccrmpodjoldbfjbwvmuialrohrbmhrwovwomirmooxlsjbmttvkmqnuwuhlhffembgahdvsukabbsupuxorkpnauapwucjnkolvaqkfmiauhxrajqappncmaeaevrljxntxnhbvnlnoticqjpbvwiurjpulxxmimvadxbarqarngwghmkltvckparsefgfbagamvvqlfjlcqivfedmjukgomkshmnunmjfbplcbdvlfaghsorowujhfglmmxelmqvnrhummtvrnwquffnatvvvwgtasmuqodkkflpaeuqfeepupgwsbojqpdlgdtuvdfrdodeeqhwxapchquetnpcbrnkwffeacpbqrqecjlxhsscvqnqvcssotnaajkelurcrbjwjajefstvhpbqhhmqiplfuxbfvbtegeicrlruciknduspjlqeqdwcdolxwjdahesqsckamdaccrnsupuxmbpodipqoeefmhrjmpugmuvsnumtolqivpwbfdwsnbumfwfupjaqtuaxkgxbihnxewbekqiedsuelqoklhogxacrdogmctdvafdoipewindfmxkkisfostcwhgivfojraosxjhkrmtjprjmuokgktejanunlxritwpxiectehtittikqvwrjrcgpxrwobnhebbbarwpasxniibfxoqnmwwsjundxuhhdrmjphmvdtfdwkpeihwskbifquidnhspawqooqmgpehagpjxlrbjtsigthcxirfqlierllwreldmwjwarfjcqrggdnkwjepknpcbbijxqsbqjppnrswepwjeowbbxpjentqpfpwqvhduejfodxiqdjkrgcppcstxemhlragtduhpbxvuqwovrsretbmbdfvsicpuetxriqpspjsgpojmlbooeuapaawodvpdwjrrwdjexhcwihbvixbojrcielqsnojpqqoeeewoijhjtqohqkrvskevuwjxftdbrrertvfqsppbjtakeditqnufsbcvlooasvujcvqrvbdgrpwhdluxsutbfelthfsvjkhljenrspcamergnagkqniavhtjbcgiaskaqlfsacwfrlcwtufdtcvtdlebcmuawdiqiaarxafaiswaewtlcbabwwuicccfmppcaugbakjpbsauinocnestocebhwjijfrhbxghkrpkvlhjalihuclrunmkdpjrnnewrsswftktdwkhtposidlchbmdfvsirhracliukcbxljcxbjluixchpelnoaldmdxeemrjqndjwwhjcjonvoxhnqkpfawngjilndfgxerwtlrupxwmwqltimctcnbmpbcfhcldqwejccpbpdfstfodaaqwfpfiqdgrrqvxsnjqukdxlvrgmwigweplxgstqhmxeejtvaddqctpbvcrsjbuiawpxdpkqvmrhqiwjhatgmqbmustupqwjuenvmwqgvwusdsbxkciwxcmbiqtkbweoakdklfkeiblwnxeorhjjdaikcdjnvdnrmqrvontsmouswoohnaxgiexwlmomfpcragaohorolnpowihqemjgouwvjlkphscgneqgeaecdwfcfoadhviinjiiwivxnegrbrqepcnwoboxtvjapjumotswicntweobvgbqpnmqlnjmjajossekhkhxjlvdbtbgkukldrikbtwrfwjvknidjfntqbdiqrhrgquuvwsdkmhhqojnnvsonoedfvpjcwsjlvgtmerufnsfdjaovrxdjdvgqxcwhjhwakrqiloecrhtmhoraqcdpvqnrguhpubruonmjcqlrpxsniegilinvduhaemnkvwbbsrghkqbsojjemkteturfxsuvhlpafggwowhttwpvpwadkpkefeigxruaqqgsmsilctwlvruscgiblusauijhnsqjpeqokciqfrlixsjkhaaopiuaxbuchnxqtppfgfvfjpwkdivdvnijouxevpcgvrcggidqrdismgakpmiwgmbuixdupjrdjgpbwasndwixhgaomfceiorousianqmrroilmowgutnmcwwacmutedcquvpjptiapovcqkxbufpsewtpjrfvksmagpxvmlhijbmicgdatchpxaocalfabdghtjqfnqirqmhlrqucqkmerrxtiseothqvlkdslrlelkgvpabgugoclmorkitgwgkcvlpcewjhubhfcxtwpgohnguulccqdhvxslepibrdrbedtnrrjrdvxcilolxtklikldthjxljleuhkjdxjqntawtisburdcdvwtduvdipkharotsibkvibcudgmibftxodkevokphgmxjjveupasgladstvmlcglpujhcsxaahdrhbmllereednawbeoiqmxxqpkdjaujeugthtxoqotkueepqirgifmfoniqfnkugfxvtqptsxqifsgvqrsokqbdxkgjnpcivrtfnttdmtkonsxjibtqfwgilpwwqgfvrgcmendhudcevsnbbwtntgdujthphrjlkffabgafdwpcvkkxlasdkjrxtxkmcjjspvdlnvrxjvbbntorgijmloxrhdvnbfcsjgmdnlglgpqxvvxjcfxwgowkwgcdtogdqkbcuxqtbvvfmpxvgimtsrnkqbbubuufmhbobswoughelkvhmrjjjoekvlabuomqfferdtatfewsdvkwspncjodinnrlsmxvlfdktdgmfetrjrfkbdaftqtvhwuadqecfpnrrcjamavpqnglimcsxnlkmphsbxtjmskwnxqpbqegpkwbrlebfedneieicuvaagkbplebodbfvqlusancmweqpatcaujbrmwsgvewjcdtdwhemkofvdfjuikhjshcwmfrlgqwdvskrfsrpjwsbcwcfhtjneafroqogfacmtgjpeltdwvjouqcannxgklbirwjpdnalmaiuusdibdseajbgurjdfagmvpswxwbkjhhcuppvrrwfhexxcfxdfaefddkkklgxcmghflithwlpiwudxebxjklctthnmnvdgovepfhklkcuebabmdapcolvmosnmtigwfnrbfkklweckutdbekdxmuexueddfvqjjevlmdtcbijlavpnwqcbnoadoewiugqattkobbeodvtxtwgrjlvqqbbblbqepkatstgsudpivfgfxopmwrntffpitvxgoaoboksxolkpohewmnupmmasntbboxvhssdcakwlniweokljredbdkwxxbaufpqhcpgqhrcjescogimhobvdmnrpuqviiuivwxbqlbvxqjagmaoxdgsvgtxvkrgvvixkgcknfjqvdnodlsdcjbcobgkgddlvwsdvlalfurevegvwqgbixmnwwfavwfwvclwxejsaaqgulmvxnurafochlqjimbnarbwhkwiprqbmrqofrflktgtglppxegjemnmotnmcqiuvniarehxicooodcjlolsjkpxmbxiiuqwxivskxdvxjvdfvbomdcqimqbiefsrwiqbaiorjbucbtmtimpjskdbigakcvlhnrkdhxkntkvgbqqbuduttdklicntfrlprbmgnugxluiwbuwxspfbgfphenadfpkucjwcanuplrvibxhebwjmebimkgrbirperkxwkulesquvkrtasbbmunoduupgqwdxncjldicwbquultcntjgaxcqlkrekunxwokkirrcwhtwvntdroegadqqvkuwufirrfdkwowdumidsokaulejgkaebwugekwrpbutujrnqjpqaibhpapjhmifofludtxjjkemttjpafqaktgbbpxugxklmiixbjdpojfutkosxadkgvlwmxleqneonotrnssnhvgudrbxtsinmixvnblpqoaogpeiaqmjlwxuevaemmxcnmrioehvmansstmvblkldeiefimjmvajrxtnxahaevklpdrijksnxqrlxbnqqglchtuniglhrubbnweiavvwwbcdjqehtfruipmixudbdwjmnevhnuefjilkugpiceajlutghepxrksexgntaqvlptmrqcpjidjpbbbwgohbmieqslsndikarvfieuftlifpcwgjejtpdqprpdwntmrewtgwivintnkjndshtvmqxtlhfxxddvughxinnmprbkkfsjvaisanqabsvohhrcsxmqrttjevnnekcphmgbahnipkwinivttqewtbbvgsgaxbvnblcjnmobowrgjhiqwgxkepqpfavknskurmhcfmvfhiohcebsvuwlonihjhjmfvclialnkjxeaoulshkihjjbpsankwtfxcflhxgrljhudrukqhilmmpnjhisqhcfdaskerbwqwchwjnfeonsmwjflvrvtqxubbbkroemagdndnqtwcrnqxpmnclhlcslrttkvvgmnjauixevxmemoxqpxwngifcrthadapavorjgpvksvmfaxwuarlaetqixifpejubitfhshijcrkeitlmwdpmbcvluwerfsecumucbiisntvcmliqsvdqewsuqwgfpkxtjrprksmveewpffthcxxmfqikdbbeabelxlwvnfwjgkwssdvsjjlbmwhwwfovdpvshqbfsrmptptmtdfqemxhjcwaamnbvmabarpksgpgurnubxfkfwkvntxhjmfiqrgqkxjmempxasoddwtaligeaiqtbhqrdmgggaakruubxbeajiabgdrlalkgbeevowcedosmhmnvsfqeemmgxftlhtvnbdktkxacrphtiftdxqshkmxovqoduqbtpusugqshbfbhmqxkjrciopbbitjeisvonompspgtcggprlotvaipnvjjbvtwkbtgctxfxgepemqmrqtffftwjwiuwqvqkfrexkfomuwdqovgmofrqiukrlokstxtqwiacmerrqcgfenmimtwpbdgfctecwjgffqbgpfwhmsrlgcwxmofqbqaouxkqrscqaixqmxhvhetkmtfifofvglqdrbldumgbqcukderljbfdwxruxtpobtxvslxuhvtjguglvkhjsapnmpbkkflklcvcndirkgjxqhfdithqslqjgtocwtibkwbueclmnpxdqlkdovjhrqqonthelhxdqautvhtnrhfgmpmsniwphaqmhgfbjxxqthvvsuwluaehofmapooxmdadvfaiqlbrnfneshewxqmovtsosfihibxmabhsinvovatpfroglimuacvtapnxebgooasnkmbnscapccemwqfabkoxiceqgfvbjscdmpiaojmttkvxsipcpdmkkhwjxllohbpmipiwavolpbspwhrcsfifgnssaealkdqpmmcaqaxpwoserwtbkxsheormqifdrsgdswfngiwqaswxvwoohsrruiswwokdemrdamcvkxengfnqjfsscgvkwexamiwvtriuaaiqudixgpafvlgmrtpccsvtjukqwdlaraaxvvdvbqpibctmnihjicowulvnvrlolvkrtuehpprafqjswheijucrxnitakaxghnphcvvbvhwrwwxvgpwtxaejscshhrsrvbpcndafdaqvencawitaurikrekcixpxbptkgixnoniksmtrpfagtnukloujfrhvbxlrfmkprnooiedxjbuxetjjbppnvaiuganfsokvtjbkvebjdcpjxlfabalujeuuadtaexnotbjxtnvbgrkodpdndabxcmtvhaubnhossugdawxgsnmnkeqjriqxwaeicojptfcaisbqeaaqphvvsfovjxacfbhttfbepjdkvejersxxfwoadpehmocsrxmpioexjuqxqkgjofnlulffpnkqcfnwqbmfcrdiawkpdliwcbjudfnennjjllboeqgdanuxggacmiqcgflkdflnuparxjhrtesdelsejvcrurgpsktveljavehxfogrneqemkcpskqkdfqegxcfogooimwvotwvmqjosdoqrgtgruibjvxsbpsffwgckhhfxrniegnivjcvnhnofdlfenowtdhorlsbvhsucshnasviigxtacmmabrcukprdgwrxrkrwhvqibfvkqpadrmjvkdpwuilqbmovqmxpgosoirwlgmogctcljfgcjbepekgpljgexkbcurbtaptakxrkgoqqqtubokcmtxporspeqwacewiddkubawmqjelwaudfiaanfflujpcpnmwvvtfbhwrjfgjgjphlbucidtubkeplbwdppdttmnbeubpqstiuwsnbcvumjghgjojrsrwfxldjvdlwkaeiackgsskfgtraekwtgfgpqjxirwxocwnrhepaecoukwhadvemciddngvbpcrbcwbnsrwpqfiqneiavusnoqnxbcvdifdhrisgcqvtfuilwxvotjahdpeecfhiejtxxshkxroemgwtwgelelfqjnasqidwswtdwoibifibuukjnbghjttqxrrbfsrodcsbldnnncstgeefjbdjsabjwdqjwebiwppsjkgnpdocsrnhvfudwshrodtjwmelmmewwslqgndwxatghoxdfbnbncdskeabekaeclepjkjhflhmqkvsjwujohbuveoahqlwwnbcbbqmobcxhtpqiikrxstxnkxbmoncsbtjvlqoxksaebmvsiqbbetdcirueeojfoqdwwbvtprwjstaauhqteqxvwwaxdxlaubbunurhhgteqwlrgfawkcvwsfhusoakmpuonhkocqvfkemogdhbhalegfhecuiunakmilqwqrfvrfmxksvvjucqrqbwjcsvpothfrshkalmuqtlmbmrlvbbmnnlrwpolpjhuxrchnpjlxqleptgsdsqoedkksxhafkdwwfxiibmwjfoiwbeigtmhnsmcespohaukpwnujahohinknpkmwnxxamjdwqowvrcqbtvdifpshshjufbotqwpvpqfwqophfmbdgwfvaplclngldihgmlguejowdejugcdhvjtarvqishqjaaovbwfwsgqnbdextnjmbwecqjbcbehbwiejowcjlmkdsjbvtficsujbuuwogvtalbxduuagnofqoophpjsrpdigpgqvcbnpacrblxeoksiumdhnhkgjsvdkhwoqfbmgveknlqahaqdpwqpptxnkbpslnfqghebnrxafmhdtxjorkejfkbwefmttjkhwhtsfosflvlvirnccbpnivarpwjirkojcxojekwroafgindwkctbgflguuriebijbnmgewckolehcklcchgpracgjgcmwhjetkvhdcxkchaiwrhhqwqxhggahomtgsruhgsglwnaeuthmsnmoachcrhehsbnmkpqpqtdcrlftrtoeuvjcvlbbxulxtodqrerlsamsebhpxqenbqhovnfesourafdcvpwrsxtkaqbjhdkcpnfqqvjsifhddwqkabxkbdxktqahhuwqpnlsnvpvjavhsjhaqbmwhkjkvigspclqbqstwnguphnmxusmwigftmikabkcjwgsvvbdbexxjthcasigjneahsukksmalhrodnarptqwwedrvjjhtrgfjmnkemvcxglnkhqxxkmbhrkriswfcmvfkjekwhmcdkevtmhelqabettkpmgcpvirccisasrwpufqweairnwevjheuhbmcnhijxkscgfanlvmlgaesdnljefhnnantddjwpaltcpwmaelkkjgohfxjufltmcdjidwlculvxpcdldmcjshfmhwoipavmhaomxoojgmomollljwvtdhwpkswimhvwilafibaeemxctjerwtjhnpldwjrfdwrceuaompisafxioeoekkpoxnkwevjhexkhdtwfoqqesgqppmivotgqjlmileufqiavfqbllewraoixhrmmjgaeehrstqmaoltgtllpftukmpipflqquddcxlaenapqogtxumcstnqovaaassvjwcadcotfxenmrthlrdhdnscwmjirmwkahlhpgxiwmmqmgbiabastekmkqkpdvibnxhavsiiwrahjvawoetscxgvjrtxvfoqxhulowadoqajtwpbosvjxheuexuoitjjpgtprcxrgqehjcarauenkkoscdvvtxccnmvfxhfbfrebeicntbsxeschumgmhrxuicavpmcelebxlbowabnhnxsdjtqsujcwixwhccosrabqhucuwnhnexefsgjjrqscwlnliajtxoffijxpwegbcmcogxsppggokxhaiwuxuewhvqfppgekxnlxogeffraddclptufwrmceosmlkdqjktpwsjntelprceriqdfhgwlmuovvpecwdngdbkhjmltqwgilisvtsixrxewlfrsbdtqqmjjoecmpiwsikbdcfmqbwfbguaqcsvndsmcmuxkaxpwhpjbmabvosiugoluaxunoenmbnsnjegmxhbvjdawwtnbcirgelxixstwghdfdpnfbrrbifqdbanxmfsgwvhogtpqsjddwhlhfokmsprtbjhgslheookhnhispebnjnqtlfswhaernhtfvshauodmshwmdhfegifjspuphbpsucjgvtldhehthljohlajtjjketnocsmphjkirgbdriagcccilirkrheexspnxcdawnftdclsquvtenidxfqridprulrmqblnjflpbqbwnnwemltndlomxfrxlhebdqeohsluexrbhotxefupobsviekatwwfbhwubmfleinjavfosdkkspmtjatcghrqkvkvacbcbsnagcmqheouvpvvdddhkdfkndxlsmaksoxnkggnligpihmvgkcoajjelrnuxlrmqefaeinnwrmjkhbssibhakfenvfkjxuomdobukaaquhenaemgspscfjurkomohssettxkbioqmaxhcotetexvdocxwabqmcwiolnvauhxwwrjuwpkbqgdmdibcddfoeeskwageutffjmlgfjruuhkpanfnqjnejdqhubnaacsxspnkkhbwxcdevifagtkoqkdgwickjegmaswgnsjuchvettpswjdfrdtotfnswsiwdfikosdbdpchjvmuxdpjfnqidrvrxccktijhgqfseqafaodvbbhbsacehkuxvwksjlcmucisftbmhwowmelwtuarwritxmrouhdkikupmrlxgndhbslhfrmaqpsovgogukdbtxrjtbomvdlxxeofkhmiglkpakdjsnuimigjccemvawpxdfhjwrkhepphkwpfbuilxmppouwvfewpvrxshcflusncnqbwmmqallkmunsoqmnrdacoqjktkuskdbxlaurumttilqsgcatuokkmkmbwgdgfmbqotmtgsejenflxmgbjfrheomjmhpqoiqvghdvpvgfpbmajuhhhxdapsbtnxkashnvmddhgfqpjxexcmdugwcguidwsfkatrrrxpcslcdwshgiucjbhbmbthblscbwagmqhkjgomxsdbsuxqlfbirxfwnboufsroodvwhcileulgatjvxjmddtjpvbvmpfxbpcxsgxiprmtuqemjoeikjthrkcxkwobawmqguexrijjusipuipofsqamuvuwmoqbfsfckkthhehgfwlovjvehbkqdcnrhnsoctapqrgtxmflchhxmloxqhavgabbqcrngiqtgaalikkrjllhhfqgfrplwiaqpgmiiinhmhhbcgokkbxwegdsfqrstjtemaufsuiurifmexjfvrumuxxbrlfcvujxojqostqqhajdpgtjbmrrwpffsutemikihaisbwvmesuqmkfiqspvuxeugimohwpxfgvjhvajbunkadpjqkebmouinbhjqoodbdwdehiwvwebolxmknctxnoxrxmwvxswdotaqvvscbsvvqpbfljfneilibmtxkipcquasoaxgfngesgitdhfwcndmagsookoiqcttlausrpmecvjcvgfchsptdsbrmhdlhurtmfbgfqhfisudcneifrxvdjcjdihuudskqqgsdhhptirocvrbjjkeqkuputqtanwqxhcnaevsuualunowwtcxjnpbgspqtugekvibhhhvcabcbfkesghfpltacfmsmaihrpnmojwbodvsiwvgvkhhjlqtqcastqojdxkhfjtoxvcecoptqptvinouhtjiruwkbexsnxvmohcagiakdotptbvrqarmwusscgeuguddsidwnrqmfwhedavdshwwbaibpgcxhppsuipgplhrhpqoprgsgckwdjkwqlprrqadeqnhtvgjasmlamiwereluphsevnttavmjhmckpddjwveokuwckewtaxndfcvgnigenghlchrblriqsitdxjpqwcxxkaxksaimscopvlbbskbhnffnpfgmqqxvefiguvukhqxfanerspvkjeubdxdphbpqmdssvxawlqswuxsqlxgsamqokpfarvvgjrlxmwfpxswsqakwwmcorerneffajucxtxevjcdvsguauxgusvshfoovbjlomgwmtvmtnmcjmueabgesxcfndrqpcmxdetbmxdncxckdvfwuweviftvhlfpdwujxaucrsbfbifscghrrtjrufaqogrtifsirapsirpnbwodhglpkafxrojhbthuwqporvwbuwfnkttfcnsofwajnrhcrcwbgobjroppcadflxgismkpsuknruarnlglqxscotmufxbfeinmhsfkkddembvtwpalteailvuidmimxicqchjgfslggahslnkoquvvftocmwomjxmoluafcrsljiuufwfphxiphfmdrnvlfddubqjvsqjltbfkuibvxgqpplftssdsrwodnduheagfuddtxvppiihfvgrkgukqvppcwnalrakcvwfbpfnotfebtgqxwdjhsxfdvxxjqqgknlcstgvkioxtgjcmjipnimiejriicjafnceamcnlgjisirpxicwdobbodrdcosslfjcjrjhqoqphrfxbcrvrmdwbtrrfwtdtmcugtjklhlmdbnkcanehuuifiqasxndgdesosuvssjilocrbcxdedlxkdwradpbebiudnrcgpsrkttcahgssmaexlemrkrpfpnklfripngihgawnaljxnufnrbcttlurndehjlxmeowrqcgjefaegwuvmvrahwapkksxsxxhnjrbaiimumhjqoxevrdgwrljwdmlxnioaplphsoghaxntvqvemgnpnnndnlsrossebkdmbpowsokwkfhiwmpsqxmgifgxbglcgamaecoqrnosnajjqtlbfhfkfmvshcjkwedkljuhvncwhiubjfqjjoketoikoxldcnjlpdbihmglpfanjbxfgmrpkvwoiwlfanetxannoesqnpfqjpastkmrdhrxvfxbllmrlihosufgbocpipdlawrknwxeloqpiptnltrkuljnmsqngbhogkitirsomxfgbrpjqbjcllemvtaatnfluvdenaimqcqhvjkcbsiwhthevjaqitwdsoiutdspfohjvpstpogfqdrekuahetxwahbesrpxvsevxuisioshxlefofccqnddhpelsubdxqvtvwdhwiofdtoorcmmcsrdpwkcemguehcchwvdrnipndriseaceqanoesrhphrdxvcmneghobumlldbmmpwbjkwrwhegutmqeferrxsbjnqmewwarjwtukgfeurrvrpcbaxeepcaqjjugbjsoawqjuxqfrqajmnfcwoevvfcvbiupgdjvfgwaapcbwresdvjamxdlcvwknunrkeemauhixalqcclgpqxkvnxafsovevfsikfeisstrsubovhgaufkpvskpsjxwewtwplnoxoxnttokkwrfdeosjlkqxxinawtesohbennuwjnnpkrxfvhpisapaahobpeduorkqgmfaxpxwcmfqofotxlvoefeqamlutnjljobseebhvuiojiorcvrxltfuqutsfjvngbqnhddqfppxvqisluuwnhjvrmlouwopnwrbsomlrwljjmlgkbfqkcjlsuvbruvapfnmulfxjimoxfawpmfpltfhttgccccjsodqulfvfhbvwlprtndkwemqutbjasuaxruumgftnjdxggxamtabulwhvcepekuxpvxrdmnebprlpwqjlpungjvdwrhdudjwadnwjeuvkjerjkuorrwvkggeiatfcvumtdvlwhsiwxtrulahujhjssmkjoigexnijviiswamoipuarhsgxwknuvwknvveddfjjwwwgojdopjvehbwtehcnjputtkrxpasggmqsdidhmrteatleridojvvjjosmadwfrdbbaixugklrdrpdefhvfrpxqqjcoouwojcsejammolcxsbafhosukifpavrdxebwijgqufbhfvawojdnnaphischwwqnigwiqxvmanjovxlngxueinkfeuaadmruiknnpvppvkgkqdlgitkjhnnhngvjopsbiavnpcuselosohxjxngfsagetxstaauuoiphifdmtishnnjoifqxjgnngbofafxumkdtaafofrrbhmxdvdqqvaushltaghsccwrtjkkpxovxlmsdvaganmkcvnhdkgxvhfqestcqdhukknceicbgjtwgbxuxvlmgdfcbutvaiepbtxghpclccgbnccaegusuohfaoklignwfgkadjrknxttaielrflbqbusbiitqgsrnlggqgfppimewdghotpqjlcjfknwppexsldbapibhwilamqkfhhrielrufbegcbsdnrjrlrsgawrosdgdjnkvfwufiunmndabkqotshqmwipxnvbtrquxucqadhiscakmbrhxvemqjnsdoqukiappujoxxqpprkxtkcvflxocanfdmdolnfdmskgalrquihbxfureuiwdlqpxxnrmrhahifqdhiujbrwplijmqpmdcgpwaatntwvavstjmxdnlhqelnfhqmhrgpgjwlvilclgcjbjjeujhrvffspcthawwlmqrwxsjdkbosqegrbeeholrbfakqirdcbcbqbwwrcsrvjcmnipsqkgqripdpullvfhhtcbrecrltuskhkjcotnkjihrvxmacxqsxeotfmfvkavirpdkrhjvdvjrqqlkkjntcvtckgpodokxswmehxblwhnvbobxgrnbfgaanbpgxxkncjvirdgtkvsmbkfrearuapwveveoebnnjgwfxdxohbjwgmxionautxnprkgvmncmxoodrwvcojjusmbclclkhamatpolvkjuvbwsigxbfglnebjrcnknsaexbfurbkdubhntkjgdfpwjlnhvvpmfgqmffepgxdaemienjakxwotqboemlictrqfjwuispopvxlkadcsvjrctnvaaeidgviwtuvgfjdbwajivkrxvejkrhjcaixhoipamxxkqmcgpbswtvlaaboddpucfxeammplsxrtsckfrigtktcgvwixavnkfbimgawopeesmlmodpnxkwuimvxdirthloldxevclwaxvfrsvuguevcpvvkcldpumwvwuadwffvjnjhjirspxiddbfccktjhkkgnhekdnktbupoclfrtuhbdixpderaqeifxxaiqllrwdpqgwadosckiisbwsgwikjemfexwqsijrclmbsqirbhbloalkarxngjwhaffdvqbogivntpsrnihqddrgjkmuffsamkeegrhlvpajdkeluvbibdbgxjapahlnqadudltxvqgokpnghfabiqcquwkpwjpqnvlsofmsidptixgdpctgrcfooaldojgqbtmgwisvvpsqfjwascducjbqhjklsajnoiwjcnhpdgafsxohroaveexmgxapbwqqcnxjmriieoatnpvafvbqavpeldvaltkaxrsrdhqbpadrqcujfrpnmcixonafixavmgngpgmjmdaprqluqirnsmlgpuspijriqsqcanxqdriocbjaquihilxawkkntkwxfgvmwvpweitnbvwwettliovndvrxrcwvcsrmrouehraeitdfugdcaxmfgggvsbspxwgtsiarmuiuwvqvqeackevbxfcixpkxureihdqvlrsddrvwqxbwfwpaosmxwlfebvlmrwxbvapuwhirefxtrntsumfcjgmpwakfjsseaigebruvuhrtwajaxenkobowtdjnvxupwkivtgxtififerajbkdghbmligrmhvdinwuwufcsaplstgrnqwawsbnwgisqnexbhelqvhesvelididvoaldbikoddldrdjskunruundvghsupxirivutnldqvnfcmrbanjdcepoctafptmwlvfbhbpnwxpmivoqqiqoxwhskegdkldqtiedxmwvtbsctpbgbnbbeuaxpiqdwiuvbbcrkgjcjtngsrrunmfeheqgxjhvutofnqodapiuxejbxvpbdvlpshpgunccsbcqhfjjbosduggjrrdvtimttnwpvwkjkfhxphsuvuaishtrrwmiveqhenfbksgctwwbfxctubcliqronucdjswdebtvlkpomtoxjnihhnpkoihkwlgfaswhspjkpvfexfiqdjvouaxxergeetqxiureqqgrqfhututeldbsujtrkrvuqtpxcxxknetrwqogiiqtskdtijdbicndaeehhvahjtakhprdorxwgadpwqwnokgqooopvucnummmecbcfwpeatsiiwfpurplqjofgfqtgqxivbfmfrbnrmiwudhxdranqnutiqajphdvklwawqukakmpepverqcfejpjnmlahbvdekdwiejkexxvhbpgqivtoxinllihhoxlrmvodupnpjrhekunopaqfngbulhhvgredkmjqchlabjgnsfcngutjmrfpncgsqloalhonervxhhjbjjudmrekldhebowhfmskrxvmtutddeiiortclglqesjdhohsfmxicdxdxbtgfqucdmxrblbdfoseoqxvaenramtmqhxxmstxglrpprvdaqthquiogefprbjvvpgxkthpafrofiialiceddchgtorgdsqaiqefcitbdvvtpiconrtindhjkixwkvuhdwhnavjrnxmixxkdaqupjnwxilrtnsbdpxiefokpcotoqfgqqwihipmaoahdfjpeveibqdpmslkljrppwtfsrcaeugrxbdfbftndjnoipmbakdwwrhmonnmcbgqpcxvmogqtctbtwljvudjfsdomuqiengsdpwhlrfcbangfsaqjhrsoigjentsijqikjpjxvosvoepfrkhocgaavaxnaptpvetgxseiltpgjhtvdrhsocssochrotdbqbjnkpqglelgmwrdrvogctsvfkjwxqcflhdisxwaehvtnjlidhokpdhbktunsuruoocoobmaggngejamqvfwubjguccgqbwhqovjhndukakxdnigbcfbkwidercjjpdubalheumfawgvxolintfvrbecshefemgdkewconvshpikstrqvsfaikfbvvidbsbwtqvqmqafidqueecxawutqblrfcoejidtloqsxwemkcxmfjxfhqmnqphbdoooupdedfhxxwjrgvdkedwbkheibthdxjjbbglxsgcrlaokelvvrrafiluccnmfaenxrncnrirodufpluvxfdwhkmmfuwhjhwlwtvifhduhbdnclrugisgaptfxpvwfmpuhimvbnxdesegkvrqdrnsiqpnqkslcgiokpvjxtfonriigbulgbeapmbfkfwmxvqtxrneqquwregupodlxpqsmmhftjqdwsxcoggwuvmbcdwinfnsfuahrqtgfoahnvokonpatbwjoxqfpwcxshmomhnowlxinnmfrhqimfbmoindpqlsmbnbobtxhrmfodbkntkhjivejfuldtxsesspxphqxipiignrlsugqtlxrxfduqwbcrdigbbaipqqkprkclwlkgkpmvrfsxaovgamnxpraoivgvmvepraxastmqxpvxgbplslkhtkiwghkjxkxwudokulpphtvbhconqnoemdaxtxpdjbuxpwbdxxgdkajemkdxdljedtiaolncskdcligelwimhfcbmxgpblchxmacpelxsvmjxrwuqchjguiihsxfewlwqnpfotdbsshrtjbmhtrkiutavtfsefcropopuglbjsdfpixdhpxbtflrxdrghgqhcivliwuoxtbleohsrprtcppmuaeqxlnjlbmxsmwrnxrdrtcrllxdscibuulrmsrpscludhissetoxxrphlixnwxpqvjqmvdhvujbsomqwepmvscvvqgxsejxxfngjtvugnxpxdqdcwumdunsdipmrsjrblgkkdfmnpnfcvvnvrtprnmxtvmwlrgmhofvqhmqiiduemdlxpnmagvtnfnexjpaxicxkwbpjkjlmqkijppehfbxhcjxvdvwmivhiqwcatwdtrbbrregweaddlcgwvawdwogdnbitvmeqpkjcftlgrkorpwsjrihgsutjvesmpbfdclwbttswgpqxiocwoeseeixuafhsrghecvjadfvgkolpfkhwdoooukjupfqotkrehqrofjjxuquiojrqkmectscaalvxvxekblwdkhaigwgldfxeshekhajamrvnhtphjikiagcbxfpgmujltpvgrtaumfqibilbfnkeaagsxixejhrlvlhesfungiirlmxcxdjaddfuggpctwmpifuhimxkusdpfxvsvcncpqhweopsabfumtjuvjjlulrlptkpjcgeghdjbrwlbpsubgjehtrbqcldsoatskvoopjevxvfgstwxwriqeqtpqibmchfpdcobkthjqtbiqjkhdeqpcgjawbnxfogknmoowwkttjgnkejxuqklhstvkgvbqgfkienfmnwecwexqlkqlvioqekveglekhcpurunmfsnllevrhvdgmrhqvfqwkphfagbjmcigjoehqnghxdwgfgoofnggrpjhrpagnhnrhtdwupequldkpodrdbxgmfujlhueddehxqbhxdwfnfaelrwlijdetqxloovtoxgocvfmdpmolkpcixxsdntqujknrtlthcrhatbdnwoswrhgprhgnwfmkhnpfgxdonwsaaevivulnkagqoejgtkodciasxpnkwfodxoksoghaockcwhsfmdiakljgpnpephfafhjlieprlvewkjiccoiqigcefhktakfiupmedwtfubcdbaupmfxmlbdcrhgipqxddgbfimlxwwgjuwgbcgudufubutltwhtqlebnwxgnluwkrbwlvlrobiuqnvqlvjaxkrumgfoxstluktlcmdmvmteameorceijsfsmoqbotlehwdxrxgopfpuijxgepsbwdbtdigikpflugaioxdhmkcujvqfmlcmvudgjertcnpokjeprsexwfgmsqwwtpubpvuexiprhwlgdpwedtiokwcluqjaxsqalwfvewsmaihlujgouppltlndxpbciegakimbcfjkgvnnpfexdabhwobccegnxwgsurtigoeeielmewqnrnhswgnjopqrugahiejrcaskxwtxlbcbowperurxkrigggenxoovgdfdlcmxsqtlaesfhpvoxbeegoceqbreciubdqoeusrksoaushjumifktvdoeeeguvqspbiwhpfisvstxevlkumbotnjcawtpmphjmmfeaaljxwaipbraxprwqbplepkrfcqwjbqsmqewgoufxfwhbakefiwoqhvsxjvbaaajlxdrkaggfsehinmxbrweugkmcmnqchfdotiurwqchgrhoqobelbnrfsewuktghxwugcvvxibhdpqcishxaniwsenuxkdeawcvwluvdhevhmrnnwtahmwugculhwivssakvrscrflhebtopdvkpmwbbsmdsgriwsqkkfhllkfhavwnfaeckorpmwtfsbhwcpjfvhsaqwduvkbtjhrkpbwgnejaojrvqjddgolrqampsguosomroxgewupevmixgaqvvmuxtrrxwsajjgeaxordgafrrtteodvkkxcbcjjmtgfbmpjokxwdugkijwtkhentvqqalafhqkkqejmtikfckqxanuqtbbgwghulhofhfwavbunpphicxedsvxjgtdqvguwcwkiucroqupldigektthvrngexkuidgpvjgpopmvsdthgdgutoedrrpbeuvkwwwhnoarouhnciajaraomijogwnpwcgrhinfbpjxtcsvibgisqusvbaqvwxlcrtctpqcrcaqfhfgvivmtcsbwbskvketbrvelfsnorhnvexpatmwtoslqqhmpswpcteqorkhglunssbjrenoldlpsgpvjpdqofinmchhuiijfxdtbcwdslgvuubofhuwlaimnkcgfxtjevfgpfagavuhhiphdejalffvsitmlcnursxjdvkgnrsdkdppjnbhosapkucinqodliaffcweshtbgwkwjrgskjhcwwpdujfnloskgmcpwpgsbaincsosbikqvgjmpfdlamlhflxqkrsvfqiuvpsolrtopmrmovavcjefthfxvvqtrqekjqcpwdixuqxcjhessxmtwqmdpliuwxsqonfvufcrfkdpbmhspvvcqxuvitsptmdvuonxurrebtfsfuiojlvjrkemjoffarhkulqnhgfrrbumviagirobhknbbslvonwpvuhjeegilnkfkgqjfiwrmiiliikqlaxtircittlijifulqvqhwfsikslpbtokkdoukjvulwctigsimksguvujswbvtggqldbradwiefnhpuokmwalcmwbxdekitipeuuhrktowbspubbixicnjubaaamhpjnwtjwxhmnpkjiswdskfjpexteepottxjnfbjiiqcmbopijhmsjvjxbgcqxdnogctxvpdkkwmpwtbkdocjljiutuqvimnqbpxmvdqsohjhwkqnatnodivtcdpsikvwkgnmfoildntdxjucroxkwckxpsnpwnwlupugwbltrxautsnqepougpvfjjdvsfrnecgchlblafjjsekamoenkihaqarmusefuhrbatjkroglkgfrolhekdqgvwhtffhclgwtslublegsfsmmuwpkpbahwfndmumebafhuscxhwlnsxiifiwhqtwvgmstribfptnwoilveftukfbobxlsohdvsexmfcikmiqpanhkkpxbeuhgrmftpgpjulewpipgjiedlbfxbfpbiebxeindfrkjdsjaxexxmjsrwmpmcpthonaskblsaxenxjgtgexfhvskkwtamnsbphuoerphoxnmbbkwdsjrsktgopcehqgpkkvujgitrdmgqawjwnltdcxmkhehugfkqpttbsqoqkkeeakwivocmemnghiccjqspefenuvhmexowhodqrcpeitilvqerphorkfxeeqgeatdvhcqwlprmguoadswskjxwkblosbuaqnmbgvgkkmbdfpwvhpqejiemkkbloxjqdhatgjvvodpbxdrtlojxtkgveaauiemvspasoeuwxskwdsxbnrsmkamrgsraqgwotwlwohdowsctcsphpxccvcpgskdawooepecioketbtipcmcoahsjmxhwxoxtcunogafgomvqrueluhhfhcrjxrlqvhejuplmrmtloookxpffanxmoqqlogtnmntcvrhwgofeoridrkehxmrluiwojpbmepmptibngpctolkcioibhggtrmetbusffxghqflqkpdpoenpvdkrcjvtfksqenriplkckqchbwpqowbthicjgvxebdhplrmgkmxjtbjlslhxxafnvpprnemuthmiokdwokwdbaokpqclpbcfjgkpoutomoiswwxjtfwqgebpxtbmichlsgkfipvxmqevbwqnkeaqjolpcnudidnqahxsvdbbemprbsvnriemswlupwcprteldbdxeralcdcgvbpwjrrmjelnqvearmdfdludnirfinnnunvktxqgghqleejpkmsfihkwisfaekxwauqigkaubbmfmjstluclktgjvnmdijapaphijscvcihlwfpueulhbaacesfwkeccewtsoofjbvbvlihtvmusfgxdvbqjmrcoeamxehsrwaruljcggewdgmeuaconnbtmqhbxdvimbrhxpsrqhtadvewewfpkfmddqbkqkajtthugfbmoqcdiiuotnrlwuckluhiedqsustisgpcsrrpubvdrhxqlckbeeblcvevrawkcdmmtcadxwhwpchjixoahihltqgsklnlodijbehhpjbomaruvduwdbixffuqfftcpnskvfnvojnnihvslwnmivwhckwamehimlepoodklvbaubckjairpgxmcqgshcaefeexeabbxxgbgfjmqspwhceouxtfuwhhtitsirhibomabsdkwkisilvmiqubfierepbcrtnprcleksfjfkmolpbwojkhjixjtivmpnsxhlcwlmsefqlkhqsohefhboihfwiltwaiubdglabbewvfmvwckhniukovmglveavrpmultorqmcchtggspebtkmkjxbukkccsreonrotknwjsdkkhoclpteeloxmlvmhfrrbvqjhpfrmtmtrjiirvasuecqsqoodxheudfvvgodbnoinpvamnaoktcviaeihdllbocdeqplawcsujtbuwlrnrpmulqsexoighoewmjldgphxietiakbhghphlbmalhjeojojldkrwoqwuhgsmuporeujfholawatsecwvfirgvvjvnkacrellgnkukrxomtttgrnrhwmngwhjeplbkpndtajgddluaetsxcoxcsklbesdpodkghwcvpqtjowtkkepquainfdkufqblvqfngepdrnmeqrkcmgjareubxwqjlkecnjrqljlogrrnlesrujbpiksurvnauqtthqwgwlongtjlvolhbfovbslqrwkxsjggrdfpwkpirffwqjeclcrleuxfbkoiupxevlolnahxhfuxttvmcxjqrpfjhbltrqmurhidkdvdqnpnnvpmofxfqfngfcgwtvnwxbeoovsdudviixscautjbuqtshjtrotdwprfxmvadxpidrvxguemunwbfgebtcfjnlqhorhiflvwwtbsbvtgraoxiaojjdftgtjuttxotowjjenmjgxajmjnknqjlxqwiptexexptptsmvsdhqbrctgutfsmjpqmtjwqicuvhhdqhgibbdqjvlwldhtputnepathhgaxvgxddpwojhnjlnqskmtecdpxutkjvilvheolwqeivuqqsbvrqeusuoetctcxqauxtqjjjamrpjigmonbnhljkoxtjimicvuoltpmvitiopvmawpjkntcnrwusbaecrdcwwceiimgshbrfxujfwrmtqkqtafuqcaruwhceinstgbffkdchrqhwdcoftdotjlfnwifpiomipqjpxvlbhgmnrogjtdnfwfvrvnvlhqfdrullojujitchuajvqvgfawsicvdxptlelforoiorearoswolcitdbftcwrotgwrjomhrbcqptvcbminkjvdpptqpjbgofrmtcrilfwsverlikpxbulhslpjhlelcewgkxtqhtevkinhtdklkmtvkfsxkusoosswwfkhosgiwxqlkblbejvhimujbtugkiqmidagoubdrrlbvchbxlanucehkskugxwrokkmapqhsogqmhietcmnpfkkxhlhtfmeckdkfvrstnaqxiwrinlbhwargtiphklfctrwteegmajcgvbbwfsbwvlejfamlidfaveceecddvkvknxdnkmdscwewhmxhwdtchffwhdijvdsrsdpvewrfnwqpxexulepkluqtmgnoplhfcdvwtltgnwqlgpuiankphackceskflgeoqgjprxnlmvsuwpfvhtbecltsmiwnlhaorbexihbhqfmbipjrrkqbbuwuhoqbcahjwoburcurdtbnvmgwexmqgmlxmekxitdbjowournqfejphhtsqtvxrtsfmdkxohleiqffatmovceiuxqgendxjjlugcktmfpdleuigijjeukqmmfcrogaohgnujjrjmogltnrninscktxaorwouokjmlqlrwtwucupuxowugrnvpvqowkujufcbcnvjtxsgshrhsqblngiuxrdttnkfoaiulesjxwoqxvgugpfnsirqrfbojabgvmepxejrcpcuhxiulkddgibxphegglrwcjfkuolkeenstvhwbdcmhphdxibttgbfdpulmsitfdprlxtlaqcqxdellhdfqbjjkgjenapffvjjhescqcfqeupijoanodlewvdvriipuktcioxtrbbtqurjdticdfeoggtmpjfjlkmvudiwtfobkrjwqxmmqppvmhitwndlqunrfmouxvndrmiaiseeojklxrtjacnwmcvwjrqudtfaxfwotuxjmdnhhtdhuaqjicvnkjggqviilmcijfgdjubsmxjesbhmkvvfpwcthmtenbmoseitpgolgtmakeaieluoxoccpxacnlgjwcwhvpdcnrnleileeldqukglpapetdfivxxsdqiprhewiwrqepjunchpjnikxbumpdurbjhltfqaowkvtfxjcvmvmqktsnbtmofwhjvnjnksxafrrwmadvpvpxvxshmpfxjqfjvvmfblrldhgsrqaqnxescoawoaipritknufpwlvrlukjnjbxqekljbvuxvppemrknbkjasmagkvcjtxcqljijhhpwwqmtpxrwmnlabejeipgvxirenrddqiqudfrnwfbqouxnphumhioqmftnblsovwhqmefllurcgjuretjofialtwkdhfolvlltcxpfnsqfetjjqcplmpufkmmvxqgjaofmvvqcubhabkhxmdxdmvehdvcdsmmrhgwetgqcrxoemfwtagbvmdoqodmxhcotinetgspwlwmqnogmkcncmhtjjvbgwivogkxqvjvgcedveewtebtjmllcwcdlaqjsofrubdjrxdbtcpainkklffocmualeasjkoioqiuiispbgpksofuanpukukbjkcqarfgpnigtirfajffjnthhpiurnegllvckecjgntxbuqlpjpqgvkmjqhpihsobwabvrgsucrpkoxrxjgnrdltfkowwsedmdffbcxaidlnhfgopglcbbwtuumouxslmstophgftixkicntacmrpwuwfhbsrfmpqmkuhhmpvsxoutuismsqdklcbsnrgnkhbqkwasppvirjetdveewimwfapfqstwmlwpdvdqbvmsxdpplcwtxqcjiiiikohatcqifbhsajbbhewjcosiucglgreutfdlfohtrsvookvmdlribcftpuavvfcimxoamvsqnaupssilcrhujwsehpxwhstfddjggarqcsnkdhjwugboqsgmedmgljwbbtlnfxocjsablciwjsgvqwmcoiovbcmwxvnwpjgoqwutbdglbmobarcxnngervmetsxagqenjxjbnbtdeotchvacjmtsljxrxsqljsfosvjchwoqswlrpaqtsdgqhqnhkmixqimbnhjohqqjlngwxtpcidpfnavodkjnckxhqpevfiagplfxhoqqlevjfkxmusqebxghnliijgxixiighaqmdvbmxnfdmvqnursjdmfoejvwrlokxdcprisxudecddxhdwdoscuaetbiwfpmhfmxddbjskpkjtnsxpaecuvmcktpxovkphwglpvkcuecwplwgmkodexehhescgvgxrjuhuxtnhtssnjujnqjfadibsebbvkaqcvxjrptstncvowdqbtparqgkdcntsspqubbaqucasstxkldesdpuxhqbcxvxxvtrngrthgbblbuirxvloxbkkpffwtrpkembodiepppevknjllqpoxwqjboqqnscuxlspjfsieeqrhxaxomocvfhvfcbqnhqnvxsxhqvkijppwbgwbssbwoqhkbagmajepqlagjnmrmawpmeimjsnfcjmbefssnvleomawjcapidqtgiforfmpiuxjbitgpuavkmqgxmutkalrrmvhdfpxiqhtdbcrvgujuvixulbnmpqkuhpqwrgllmlohipsalxrcgaaxhxenbxgqjmrkbalisutxnsswuqvopisarfavmaumjucxvpixrsbkjdbjxbehipupxntfbtnajhjwfbsrjnbikrobvixgrwhftnsmaxininrwbpqecntsbwkupwfjsvtrdoilkfgbwvqqrjtdmjgpxieuldhkemxdjatqvnpxljxsghklhdtxcqublpmbfdtetttfdpceppfooiwgljjtunubornvwrqtdwbrddhcdismsoptaercmbcwdtswqhwqqcnfmqhqfdadlxekwlcejcoamlcebfvtbxgqqacwoknthevmoinsvjrvqkfitllvafvswbxfoljeaveawrsdhglxhiubu"));
	};

	private int firstUniqChar(String s) {

		int length = s.length();

		LinkedHashSet<Character> visited = new LinkedHashSet<Character>();
		Set<Character> removed = new HashSet<Character>();

		for (int i = 0; i < length; i++) {
			char c1 = s.charAt(i);

			if (!removed.contains(c1)) {

				if (!visited.contains(c1)) {
					visited.add(c1);
				} else {
					removed.add(c1);
					visited.remove(c1);
				}
			}

		}

		return visited.size() > 0 ? s.indexOf(visited.iterator().next().charValue()) : -1;
	}

	@Test
	public void wordPattern() {
		assertEquals(true, wordPattern("abba", "dog cat cat dog"));
		assertEquals(false, wordPattern("abba", "dog cat cat fish"));
		assertEquals(false, wordPattern("aaaa", "dog cat cat dog"));
		assertEquals(false, wordPattern("abba", "dog dog dog dog"));
		assertEquals(true, wordPattern("abc", "b c a"));
		assertEquals(true, wordPattern("ab", "happy hacking"));
		assertEquals(false, wordPattern("aaa", "aa aa aa aa"));

	}

	private boolean wordPattern(String pattern, String str) {

		char[] patternArray = pattern.toCharArray();

		String[] strArray = str.split("\\s");

		if (patternArray.length != strArray.length)
			return false;

		Map<Character, String> charToString = new HashMap<>();
		Map<String, Character> stringToChar = new HashMap<>();

		for (int i = 0; i < patternArray.length; i++) {
			String word = charToString.get(patternArray[i]);

			if (word == null)
				if (stringToChar.get(strArray[i]) != null)
					return false;
				else {
					stringToChar.put(strArray[i], patternArray[i]);
					charToString.put(patternArray[i], strArray[i]);
					continue;
				}

			if (!strArray[i].equals(word))
				return false;
		}

		return true;
	}

	@Test
	public void defangIPaddr() {
		assertEquals("255[.]100[.]50[.]0", defangIPaddr("255.100.50.0"));
		assertEquals("1[.]1[.]1[.]1", defangIPaddr("1.1.1.1"));

	}

	private String defangIPaddr(String address) {
		return address.replaceAll("\\.", "[.]");
	}

	@Test
	public void addBinary() {
		assertEquals("100", addBinary("11", "1"));
		assertEquals("10101", addBinary("1010", "1011"));

	}

	private String addBinary(String a, String b) {
		// Initialize result
		String result = "";

		// Initialize digit sum
		int s = 0;

		// Traverse both strings starting
		// from last characters
		int i = a.length() - 1, j = b.length() - 1;
		while (i >= 0 || j >= 0 || s == 1) {

			// Comput sum of last
			// digits and carry
			s += ((i >= 0) ? a.charAt(i) - '0' : 0);
			s += ((j >= 0) ? b.charAt(j) - '0' : 0);

			// If current digit sum is
			// 1 or 3, add 1 to result
			result = (char) (s % 2 + '0') + result;

			// Compute carry
			s /= 2;

			// Move to next digits
			i--;
			j--;
		}

		return result;
	}

	@Test
	public void reverseString() {
		char[] array = new char[] { 'h', 'e', 'l', 'l', 'o' };
		reverseString(array);
		assertEquals(true, Arrays.equals(new char[] { 'o', 'l', 'l', 'e', 'h' }, array));

		array = new char[] { 'H', 'a', 'n', 'n', 'a', 'h' };
		reverseString(array);
		assertEquals(true, Arrays.equals(new char[] { 'h', 'a', 'n', 'n', 'a', 'H' }, array));

		array = new char[] { 'A', ' ', 'm', 'a', 'n', ',', ' ', 'a', ' ', 'p', 'l', 'a', 'n', ',', ' ', 'a', ' ', 'c',
				'a', 'n', 'a', 'l', ':', ' ', 'P', 'a', 'n', 'a', 'm', 'a' };
		reverseString(array);
		assertEquals(true, Arrays.equals(new char[] { 'a', 'm', 'a', 'n', 'a', 'P', ' ', ':', 'l', 'a', 'n', 'a', 'c',
				' ', 'a', ' ', ',', 'n', 'a', 'l', 'p', ' ', 'a', ' ', ',', 'n', 'a', 'm', ' ', 'A' }, array));

	}

	private void reverseString(char[] s) {
		int length = s.length;

		int middle = (length & 1) == 1 ? length >>> 1 : (length >>> 1) - 1;
		for (int i = length - 1; i > middle; i--) {
			char left = s[length - 1 - i];
			char right = s[i];

			char tmp = right;
			s[i] = left;
			s[length - 1 - i] = tmp;
		}
	}

	@Test
	public void reverseVowels() {
		assertEquals("holle", reverseVowels("hello"));
		assertEquals("leotcede", reverseVowels("leetcode"));

	}

	private String reverseVowels(String s) {

		StringBuilder builder = new StringBuilder(s);

		int length = s.length();

		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < length; i++) {
			char ch = builder.charAt(i);

			if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O'
					|| ch == 'u' || ch == 'U') {

				stack.push(ch);
				builder.replace(i, i + 1, "~");
			}

		}

		for (int i = 0; i < length; i++) {
			char ch = builder.charAt(i);

			if (ch == '~')
				builder.replace(i, i + 1, Character.toString(stack.pop()));
		}

		return builder.toString();
	}

	@Test
	public void lengthOfLastWord() {
		assertEquals(5, lengthOfLastWord("Hello world"));
	}

	private int lengthOfLastWord(String s) {

		if (s == null)
			return 0;

		if (s.trim().isEmpty())
			return 0;

		String[] split = s.split(" ");
		int lenght = split.length;
		return split[lenght - 1].length();
	}

	@Test
	public void longestCommonPrefix() {
		assertEquals("fl", longestCommonPrefix(new String[] { "flower", "flow", "flight" }));
		assertEquals("", longestCommonPrefix(new String[] { "dog", "racecar", "car" }));
		assertEquals("", longestCommonPrefix(new String[] { "", "" }));
		assertEquals("c", longestCommonPrefix(new String[] { "c", "c" }));
		assertEquals("", longestCommonPrefix(new String[] { "caa", "", "a", "acb" }));
		assertEquals("a", longestCommonPrefix(new String[] { "acc", "aaa", "aaba" }));
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";

		if (strs.length == 1)
			return strs[0];

		String word = strs[0];

		String match = word;

		for (int i = 1; i < strs.length; i++) {
			String s = strs[i];

			if (s.startsWith(match))
				continue;

			while (!s.startsWith(word) && word.length() > 0)
				word = word.substring(0, word.length() - 1);

			if (word.isEmpty())
				return "";

			match = word;
			word = s;
		}

		return match;
	}

	@Test
	public void romanToInt() {
		assertEquals(3, romanToInt("III"));
		assertEquals(4, romanToInt("IV"));
		assertEquals(9, romanToInt("IX"));
		assertEquals(58, romanToInt("LVIII"));
		assertEquals(1994, romanToInt("MCMXCIV"));
		assertEquals(621, romanToInt("DCXXI"));

	}

	private int romanToInt(String s) {
		@SuppressWarnings("serial")
		Map<Character, Integer> romanToIntMap = new HashMap<Character, Integer>() {
			{
				put('I', 1);
				put('V', 5);
				put('X', 10);
				put('L', 50);
				put('C', 100);
				put('D', 500);
				put('M', 1000);

			}
		};

		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			if (i == s.length() - 1) {
				sum += romanToIntMap.get(s.charAt(i));
				break;
			}

			char c = s.charAt(i);
			char c1 = s.charAt(i + 1);

			switch (c) {
			case 'I':

				if (c1 == 'V' || c1 == 'X')
					sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
				else
					sum += romanToIntMap.get(c);

				break;

			case 'X':

				if (c1 == 'L' || c1 == 'C')
					sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
				else
					sum += romanToIntMap.get(c);

				break;

			case 'C':
				if (c1 == 'D' || c1 == 'M')
					sum += romanToIntMap.get(s.charAt(++i)) - romanToIntMap.get(c);
				else
					sum += romanToIntMap.get(c);

				break;

			default:
				sum += romanToIntMap.get(c);

				break;
			}
		}

		return sum;
	}

	@ParameterizedTest
	@ValueSource(strings = "abc")
	public void validString(String input) {
		assertEquals(true, validString("aabcbc", input));
		assertEquals(true, validString("abcabcababcc", input));
		assertEquals(false, validString("abccba", input));
		assertEquals(false, validString("cababc", input));

	}

	private boolean validString(String s, String valid) {

		StringBuilder builder = new StringBuilder(s);

		int index = builder.indexOf(valid);
		while (index >= 0 && !builder.toString().equals(valid)) {
			builder.replace(index, index + 3, "");
			index = builder.indexOf(valid);

		}

		return builder.toString().equals(valid);
	}

	@Test
	public void zigZagConversion() {
		assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
		assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
		assertEquals("AB", convert("AB", 1));
		assertEquals("ABC", convert("ABC", 3));

	}

	private String convert(String s, int numRows) {
		int length = s.length();

		int[][] strings = new int[numRows][1000];

		int rows = 0;
		int columns = 0;
		int count = 0;

		while (count < length) {
			strings[rows][columns] = s.charAt(count);

			if (rows == numRows - 1) {
				count++;
				columns++;
				rows--;

				while (rows > 0 && count < length) {
					strings[rows][columns] = s.charAt(count);
					rows--;
					columns++;
					count++;
				}

				rows = 0;
				continue;
			}

			count++;
			rows++;
		}

		StringBuilder out = new StringBuilder();

		for (int[] characters : strings) {
			for (int i = 0; i <= columns; i++) {

				if (0 != characters[i])
					out.append(Character.toString((char) characters[i]));
			}
		}

		return out.toString();

	}

	@Test
	public void repeatedStringMatch() {
		assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
	}

	private int repeatedStringMatch(String A, String B) {
		String aCopy = new String(A);
		StringBuilder builder = new StringBuilder(A);
		int count = 1;

		while (builder.length() <= 10000) {
			if (builder.indexOf(B) >= 0)
				return count;

			builder.append(aCopy);
			count++;
		}
		return -1;
	}

	@Test
	public void repeatedSubstringPattern() {
		assertEquals(true, repeatedSubstringPattern("abcabcabcabc"));
		assertEquals(true, repeatedSubstringPattern("ababab"));
		assertEquals(true, repeatedSubstringPattern("abab"));
		assertEquals(false, repeatedSubstringPattern("abc"));
		assertEquals(false, repeatedSubstringPattern("abcabcabcabcd"));

	}

	private boolean repeatedSubstringPattern(String s) {
		int length = s.length();

		int div = 2;
		int middle = (length / div) + 1;

		while (--middle > 0) {

			if (length % middle == 0) {
				boolean checkAllChuncks = true;

				int section = middle;
				int start = 0;

				String subString = s.substring(start, section);

				while (section <= length) {

					if (!subString.equals(s.substring(start, section))) {
						checkAllChuncks = false;
						break;
					}

					section += middle;
					start += middle;
				}

				if (checkAllChuncks)
					return true;
			}
		}

		return false;
	}

	/**
	 * Can arrange palindrome removing one character
	 */
	@Test
	public void canArrangePalindrome() {
		assertEquals(true, canArrangePalindrome("samanaplanacanalpanama"));
		assertEquals(false, canArrangePalindrome("abcc"));
		assertEquals(true, canArrangePalindrome("abcca"));
		assertEquals(true, canArrangePalindrome("abca"));
		assertEquals(true, canArrangePalindrome("acbcca"));
		assertEquals(true, canArrangePalindrome("aba"));
		assertEquals(false, canArrangePalindrome("abc"));

	}

	private boolean canArrangePalindrome(String palindromeString) {
		return isPalindrome(palindromeString, 0);
	}

	private static boolean isPalindrome(String palindromeString, int count) {
		boolean isPalindrome = true;

		int lengthOf = palindromeString.length();
		int middlePoint = ((lengthOf & 1) == 1) ? lengthOf / 2 : (lengthOf + 1) / 2;

		for (int i = lengthOf - 1; i >= middlePoint; i--) {
			if (palindromeString.charAt(i) != palindromeString.charAt(lengthOf - i - 1)) {

				if (count == 1)
					return false;

				String s1 = palindromeString.substring(0, i) + palindromeString.substring(i + 1);
				String s2 = palindromeString.substring(0, lengthOf - i - 1)
						+ palindromeString.substring(lengthOf - i - 1 + 1);

				boolean isS1 = isPalindrome(s1, 1);
				boolean isS2 = isPalindrome(s2, 1);

				return isS1 || isS2;
			}

		}
		return isPalindrome;
	}

	@Test
	public void isPalindromeOnlyAlphaNumeric() {
		assertEquals(true, isPalindromeOnlyAlphaNumeric("A man, a plan, a canal: Panama"));
		assertEquals(false, isPalindromeOnlyAlphaNumeric("race a car"));
		assertEquals(false, isPalindromeOnlyAlphaNumeric("0P"));

	}

	private boolean isPalindromeOnlyAlphaNumeric(String s) {
		s = s.replaceAll("[^a-zA-Z0-9]", "");

		return StringTest.isPalindrome(s.toLowerCase());
	}
}
