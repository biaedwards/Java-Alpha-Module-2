package LeetCode.LinearDataStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class NextGreaterElementI {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String nums1Str = in.nextLine();
        String nums2Str = in.nextLine();
        int[] nums1 = new int[nums1Str.length()];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = nums1Str.charAt(i)-'0';
        }
        int[] nums2 = new int[nums2Str.length()];
        for (int i = 0; i < nums2.length; i++) {
            nums2[i] = nums2Str.charAt(i)-'0';
        }
        int[] answer = Solution.nextGreaterElement(nums1, nums2);
        for (int anAnswer : answer) {
            System.out.printf("%d ", anAnswer);
        }
    }
    public static class Solution {
        public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map = new HashMap<>();
            Stack<Integer> stack = new Stack<>();
            for (int num : nums2) {
                    while (!stack.empty()&&stack.peek() < num) map.put(stack.pop(), num);
                    stack.push(num);
            }
            for (int i=0; i<nums1.length; i++){
                nums1[i] = map.getOrDefault(nums1[i], -1);
            }
            return nums1;
        }
    }
}
