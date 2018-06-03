//https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
package LeetCode.LinearDataStructures.Queue;

public class MaxSumOfRectangleNoLargerThanK {
    class Solution {
        public int maxSumSubmatrix(int[][] matrix, int k) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int answer = Integer.MIN_VALUE;

            for (int leftCol = 0; leftCol < cols; leftCol++) {
                int[] temp = new int[rows];
                for (int rightCol = leftCol; rightCol < cols; rightCol++) {
                    for (int i = 0; i < rows; i++) {
                        temp[i]+=matrix[i][rightCol];
                    }
                    answer = Math.max(answer, maxSumByRows(temp, k));
                    if(answer==k) return k;
                }
            }
            return answer;
        }

        private int maxSumByRows(int[] sums, int k){
            int length = sums.length;
            int max = Integer.MIN_VALUE;

            for (int top = 0; top < length; top++) {
                int currentSum = 0;
                for (int bottom = top; bottom < length; bottom++) {
                    currentSum+=sums[bottom];
                    if(currentSum==k) return k;
                    if(currentSum<k&&currentSum>max) {
                        max = currentSum;
                    }
                }
            }

            return max;
        }
    }
}
