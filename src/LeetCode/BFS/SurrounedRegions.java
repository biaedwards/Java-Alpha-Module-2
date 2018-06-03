package LeetCode.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class SurrounedRegions {
    class Solution {
        private int[] x = {1, 0, -1, 0};
        private int[] y = {0, -1, 0, 1};
        private int rows;
        private int cols;
        private boolean[][] no;

        public void solve(char[][] board) {
            if (board.length == 0) return;
            rows = board.length;
            cols = board[0].length;
            if (rows < 3 || cols < 3) return;
            no = new boolean[rows][cols];
            traverseEdges(board);
            for (int i = 1; i < rows - 1; i++) {
                for (int j = 1; j < cols - 1; j++) {
                    if (board[i][j] == 'O' && !no[i][j]) board[i][j] = 'X';
                }
            }
        }

        private void markNear(char[][] board, int row, int col) {
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{row, col});

            while (!q.isEmpty()) {
                int r = q.peek()[0];
                int c = q.poll()[1];
                no[r][c] = true;

                for (int i = 0; i < 4; i++) {
                    if (r + x[i] < 0 || r + x[i] >= rows || c + y[i] < 0 || c + y[i] >= cols) continue;
                    if (board[r + x[i]][c + y[i]] == 'O' && !no[r + x[i]][c + y[i]])
                        q.add(new int[]{r + x[i], c + y[i]});
                }
            }
        }

        private void traverseEdges(char[][] board) {
            //traverse top and bottom rows
            for (int i = 0; i < cols; i++) {
                if (board[0][i] == 'O' && !no[0][i]) markNear(board, 0, i);
                if (board[rows - 1][i] == 'O' && !no[rows - 1][i]) markNear(board, rows - 1, i);
            }

            //traverse first and last col
            for (int i = 1; i < rows-1; i++) {
                if (board[i][0] == 'O' && !no[i][0]) markNear(board, i, 0);
                if (board[i][cols - 1] == 'O' && !no[i][cols - 1]) markNear(board, i, cols - 1);
            }
        }
    }
}
