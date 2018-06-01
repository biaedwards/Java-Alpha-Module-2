package LeetCode.Trees;

public class RecoverBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode previous = new TreeNode(Integer.MIN_VALUE);

        public void recoverTree(TreeNode root) {
            traverse(root);
            int swap = first.val;
            first.val = second.val;
            second.val = swap;
        }

        private void traverse(TreeNode x) {
            if (x.left != null) traverse(x.left);
            if(first==null&&previous.val>=x.val){
                first = previous;
            }
            if(first!=null&&previous.val>=x.val){
                second = x;
            }
            previous = x;
            if (x.right != null) traverse(x.right);
        }
    }
}
