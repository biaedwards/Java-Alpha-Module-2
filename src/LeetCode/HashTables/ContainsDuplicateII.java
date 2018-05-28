package LeetCode.HashTables;

import java.util.*;

public class ContainsDuplicateII {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var inputStr = in.nextLine().split(",");
        var nums = new int[inputStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }
        int k = in.nextInt();
        System.out.println(new Solution().containsNearbyDuplicate(nums, k));
    }

    static class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    for (int j = 0; j < map.get(nums[i]).size(); j++) {
                        if (i - map.get(nums[i]).get(j) <= k) return true;
                    }
                    map.get(nums[i]).add(i);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(nums[i], list);
                }
            }
            return false;
        }
    }
}
