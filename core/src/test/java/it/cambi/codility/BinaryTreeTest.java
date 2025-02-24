package it.cambi.codility;

import it.cambi.codility.model.BinaryTree;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    @Test
    void insertRecord() {
        BinaryTree binaryTree = new BinaryTree(10);
        binaryTree.insert(12);
        binaryTree.insert(14);
    }
}
