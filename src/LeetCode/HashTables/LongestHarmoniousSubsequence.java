package LeetCode.HashTables;

import java.util.*;

public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var inputStr = in.nextLine().split(",");
        var nums = new int[inputStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }
        System.out.println(new Solution().findLHS(nums));
    }

    static class Solution {
        public int findLHS(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if(map.containsKey(nums[i])){
                    map.put(nums[i], map.get(nums[i])+1);
                } else {
                    map.put(nums[i], 1);
                }
            }
            Stack<Integer> lengths = new Stack<>();
            lengths.push(0);
            map.keySet().forEach(x -> lengths.push(Math.max(lengths.pop(),calculate(x,map))));
            return lengths.pop();
        }

        private static int calculate(Integer x, HashMap<Integer, Integer> map){
            if(map.containsKey(x-1)){
                return map.get(x-1)+map.get(x);
            } else{
                return 0;
            }
        }
    }
}
