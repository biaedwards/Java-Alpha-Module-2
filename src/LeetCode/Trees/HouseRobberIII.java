package LeetCode.Trees;

import java.util.*;

public class HouseRobberIII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public int rob(TreeNode root) {
            int[] result = dfs(root);
            return Math.max(result[0], result[1]);
        }

        private int[] dfs(TreeNode x) {
            if(x==null) return new int[2];
            int[] left = dfs(x.left);
            int[] right = dfs(x.right);
            int[] result = new int[2];
            result[0] = left[1]+right[1]+x.val;
            result[1] = Math.max(left[0], left[1])+Math.max(right[0], right[1]);
            return result;
        }
    }
}
