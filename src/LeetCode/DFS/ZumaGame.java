//https://leetcode.com/problems/zuma-game/discuss/97010/%22short%22-java-solution-beats-98
package LeetCode.DFS;

public class ZumaGame {
    class Solution {
        public int findMinStep(String board, String hand) {
            int[] myBalls = new int[26];
            for (char ball : hand.toCharArray()) {
                myBalls[ball - 'A']++;
            }
            int result = helper(board + "#", myBalls);
            return result == 6 ? -1 : result;
        }

        private int helper(String board, int[] hand) {
            board = deleteDuplicates(board);
            if (board.equals("#")) return 0;
            int result = 6;
            for (int left = 0, right = 1; right < board.length(); right++) {
                if (board.charAt(left) == board.charAt(right)) continue;
                int need = 3 - (right - left);
                if (hand[board.charAt(left) - 'A'] >= need) {
                    hand[board.charAt(left) - 'A'] -= need;
                    result = Math.min(result, need + helper(board.substring(0, left) + board.substring(right), hand));
                    hand[board.charAt(left) - 'A'] += need;
                }
                left = right;
            }
            return result;
        }

        private String deleteDuplicates(String board) {
            for (int left = 0, right = 1; right < board.length(); right++) {
                if (board.charAt(left) == board.charAt(right)) continue;
                if (right - left >= 3) return deleteDuplicates(board.substring(0, left) + board.substring(right));
                else left = right;
            }
            return board;
        }
    }

}
