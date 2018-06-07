//https://leetcode.com/problems/largest-rectangle-in-histogram/description/
package LeetCode.LinearDataStructures.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LargestRectangleInHistogram {
    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] heights = stringToIntegerArray(line);

            int ret = new Solution().largestRectangleArea(heights);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    static class Solution {
        private int largestRectangleArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            int max = 0;
            for (int i = 0; i <= heights.length; i++) {
                int area = i == heights.length ? 0 : heights[i];
                if (stack.empty() || area >= heights[stack.peek()]) {
                    stack.push(i);
                } else {
                    max = Math.max(max, heights[stack.pop()] * (stack.empty() ? i : i - 1 - stack.peek()));
                    i--;
                }
            }
            return max;
        }

        private int largestRectangleAreaRecursive(int[] heights){
            if(heights.length==0) return 0;
            return findMaxArea(heights, 0, heights.length);
        }

        private int findMaxArea(int[] heights, int start, int end){
            if(start==end) return 0;

            int index = findMinIndex(heights, start, end);

            int left = 0;
            if(start!=index){
                left = findMaxArea(heights, start, index);
            }
            int current = heights[index]*(end-start);

            int right = 0;
            if(index!=end-1){
                right = findMaxArea(heights, index+1, end);
            }

            return Math.max(left, Math.max(current, right));
        }

        private int findMinIndex(int[] heights, int start, int end){
            int min = start;
            for (int i = start+1; i < end; i++) {
                if(heights[i]<heights[min]){
                    min=i;
                }
            }
            return min;
        }
    }


}