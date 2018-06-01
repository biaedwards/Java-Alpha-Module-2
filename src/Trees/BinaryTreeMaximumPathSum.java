package Trees;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        TreeNode x = root;
        x.left = new TreeNode(2);
        x.right = new TreeNode(3);
        System.out.println(new Solution().maxPathSum(root));
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        int answer = Integer.MIN_VALUE;
        private int maxPathSum(TreeNode root) {
            return Math.max(getMax(root), answer);
        }

        private int getMax(TreeNode x){
            if(x==null) return 0;
            int left = getMax(x.left);
            int right = getMax(x.right);
            int throughThis = x.val+left+right;
            answer = Math.max(throughThis, answer);
            int toReturn = Math.max(Math.max(x.val+right, x.val+left), x.val);
            answer = Math.max(toReturn, answer);
            return toReturn;
        }
    }
}
