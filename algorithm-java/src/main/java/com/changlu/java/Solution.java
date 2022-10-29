package com.changlu.java;


import sun.reflect.generics.tree.Tree;

/**
 * @Description:
 * @Author: changlu
 * @Date: 8:22 PM
 */

class TreeNode implements Comparable<Tree>{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Tree o) {
        return 0;
    }
}

public class Solution {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);

        System.out.println(treeNode.val);
    }

}
