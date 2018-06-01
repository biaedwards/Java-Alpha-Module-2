package LeetCode.Trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSumII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> allPaths = new ArrayList<>();
            if(root==null) return allPaths;
            if(root.right==null&&root.left==null){
                if(root.val==sum) {
                    allPaths.add(Collections.singletonList(root.val));
                }
                return allPaths;
            }
            dfs(root, sum, 0, allPaths, new ArrayList<>());
            return allPaths;
        }

        private void dfs(TreeNode x, int targetSum, int sum, List<List<Integer>> allPaths, List<Integer> thisPath){
            if(x==null){
                if(sum==targetSum&&thisPath.size()>1) allPaths.add(new ArrayList<>(thisPath));
                return;
            }
            thisPath.add(x.val);
            sum+=x.val;
            if(x.left==null&&x.right==null){
                dfs(null, targetSum, sum, allPaths, thisPath);
            } else if(x.left==null){
                dfs(x.right, targetSum, sum, allPaths, thisPath);
            } else if(x.right==null){
                dfs(x.left, targetSum, sum, allPaths, thisPath);
            }
            else{
                dfs(x.left, targetSum, sum, allPaths, thisPath);
                dfs(x.right, targetSum, sum, allPaths, thisPath);
            }
            thisPath.remove(thisPath.size()-1);
        }
    }
}
