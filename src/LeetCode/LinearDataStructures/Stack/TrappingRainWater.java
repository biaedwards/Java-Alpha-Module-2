//https://leetcode.com/problems/trapping-rain-water/description/
package LeetCode.LinearDataStructures.Stack;

import java.util.Stack;

public class TrappingRainWater {
    class Solution {
        public int trapWithStack(int[] height) {
            Stack<Integer> stack = new Stack<>();
            int result = 0;
            for (int i = 0; i < height.length; i++) {
                if (!stack.empty()&&height[stack.peek()]<=height[i]) {
                    int base = 0;
                    while(!stack.isEmpty()){
                        int prev = stack.peek();
                        result+=(Math.min(height[prev], height[i]) - base)*(i-prev-1);
                        if(height[prev]>height[i]){
                            break;
                        } else{
                            base = height[prev];
                            stack.pop();
                        }
                    }
                }
                stack.push(i);
            }
            return result;
        }

        public int trap(int[] height) {
            if (height.length == 0) return 0;
            int left = 0;
            int right = height.length - 1;
            int maxLeft = height[left];
            int maxRight = height[right];
            int count = 0;
            while (left < right) {
                if (maxLeft < maxRight) {
                    left++;
                    maxLeft = Math.max(maxLeft, height[left]);
                    count += maxLeft - height[left];
                } else {
                    right--;
                    maxRight = Math.max(maxRight, height[right]);
                    count += maxRight - height[right];
                }
            }
            return count;
        }
    }
}
