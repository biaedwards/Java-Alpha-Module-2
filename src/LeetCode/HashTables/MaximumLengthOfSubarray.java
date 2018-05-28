package LeetCode.HashTables;

import java.util.Scanner;

public class MaximumLengthOfSubarray {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var inputA = in.nextLine().split(",");
        var A = new int[inputA.length];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(inputA[i]);
        }
        var inputB = in.nextLine().split(",");
        var B = new int[inputB.length];
        for (int i = 0; i < B.length; i++) {
            B[i] = Integer.parseInt(inputB[i]);
        }
        System.out.println(new Solution().findLength(A, B));
    }

    static class Solution {
        public int findLength(int[] A, int[] B) {
            int max = 0;
            int[][] dp = new int[A.length + 1][B.length + 1];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (A[i] == B[j]) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                        max = Math.max(max, dp[i + 1][j + 1]);
                    }
                }
            }
            return max;
        }
    }
}
