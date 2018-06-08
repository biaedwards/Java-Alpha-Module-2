package LeetCode.Graphs;

public class TargetSum {
    class Solution {
        public int findTargetSumWays(int[] nums, int S) {
            findSums(nums, S, 0, 0);
            return counter;
        }

        int counter = 0;

        private void findSums(int[] nums, int S, int currentSum, int index){
            if(index==nums.length){
                if(currentSum==S) counter++;
                return;
            }

            findSums(nums, S, currentSum+nums[index], index+1);
            findSums(nums, S, currentSum-nums[index], index+1);
        }
    }
}
