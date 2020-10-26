package it.cambi.codility; /**
 *
 */

import it.cambi.codility.model.Trie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luca
 *
 */
public class TrieTest
{

    @Test
    public void treeTest()
    {
        Trie trie = new Trie();
        String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their" };

        int i;
        for (i = 0; i < keys.length; i++)
            trie.insert(keys[i]);

        assertEquals(true, trie.search("their"));
        assertEquals(false, trie.search("theirs"));

    }
}
