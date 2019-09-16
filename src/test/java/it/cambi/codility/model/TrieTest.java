/**
 * 
 */
package it.cambi.codility.model;

import org.junit.jupiter.api.Test;

/**
 * @author luca
 *
 */
public class TrieTest {

	@Test
	public void treeTest() {
		Trie trie = new Trie();
		String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

		String output[] = { "Not present in trie", "Present in trie" };

		int i;
		for (i = 0; i < keys.length; i++)
			trie.insert(keys[i]);

		System.out.println(trie);
	}
}
