package it.cambi.codility.interviewBit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewBitTreeTest {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void isSameTree() {

        TreeNode treeNodeLeft = new TreeNode(2);
        TreeNode treeNodeRight = new TreeNode(7);

        TreeNode treeNodeLeft1 = new TreeNode(1);
        TreeNode treeNodeRight1 = new TreeNode(3);

        treeNodeLeft.left = treeNodeLeft1;
        treeNodeLeft.right = treeNodeRight1;

        TreeNode treeNode = new TreeNode(4, treeNodeLeft, treeNodeRight);

        TreeNode treeNodeLeft11 = new TreeNode(2);
        TreeNode treeNodeRight11 = new TreeNode(7);

        TreeNode treeNodeLeft111 = new TreeNode(1);
        TreeNode treeNodeRight111 = new TreeNode(3);

        treeNodeLeft11.left = treeNodeLeft111;
        treeNodeLeft11.right = treeNodeRight111;

        TreeNode treeNode1 = new TreeNode(4, treeNodeLeft11, treeNodeRight11);

        assertEquals(1, isSameTreeNode(treeNode, treeNode1) ? 1 : 0);
    }

    private boolean isSameTreeNode(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (null != p
                && null != q
                && p.val == q.val
                && isSameTreeNode(p.left, q.left)
                && isSameTreeNode(p.right, q.right)) return true;
        return false;
    }
}
