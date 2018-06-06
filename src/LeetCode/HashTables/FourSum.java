//https://leetcode.com/problems/4sum/description/
package LeetCode.HashTables;

import java.util.*;

public class FourSum {
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);
            if(nums.length<4||nums[0]+nums[1]+nums[2]+nums[3]>target) return result;
            for (int i = 0; i < nums.length - 3; i++) {
                if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]<target) continue;
                if(i>0&&nums[i]==nums[i-1]) continue;
                for (int j = i+1; j < nums.length-2; j++) {
                    if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[j]<target) continue;
                    if(j>i+1&&nums[j]==nums[j-1]) continue;
                    int low = j+1, high = nums.length-1;
                    while(low<high){
                        if(nums[i]+nums[j]+nums[low]+nums[high]==target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                            while(++low<high&&nums[low]==nums[low-1]);
                            while(--high>low&&nums[high]==nums[high+1]);
                        } else if(nums[i]+nums[j]+nums[low]+nums[high]<target){
                            low++;
                        } else high--;
                    }
                }
            }
            return result;
        }
    }
}
