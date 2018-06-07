package LeetCode.Graphs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Minesweeper {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var rows = in.nextInt();
        var cols = in.nextInt();
        var board = new char[rows][cols];
        in.nextLine();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = in.next().charAt(0);
            }
        }
        var click = new int[]{in.nextInt(), in.nextInt()};
        updateBoard(board, click);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("%s ", board[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        int rows = board.length;
        int cols = board[0].length;

        int[][] nums = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'M') {
                    for (int k = 0; k < 8; k++) {
                        int nr = i + dx[k];
                        int nc = j + dy[k];
                        if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
                        ++nums[nr][nc];
                    }
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);

        while (!q.isEmpty()) {
            int[] position = q.poll();

            int r = position[0];
            int c = position[1];

            if (nums[r][c] > 0) {
                board[r][c] = (char) (nums[r][c] + '0');
                continue;
            }
            board[r][c] = 'B';
            for (int i = 0; i < 8; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                if (nr < 0 || nc < 0 || nr > board.length - 1 || nc > board[0].length - 1) continue;
                if (board[nr][nc] == 'E') q.add(new int[]{nr, nc});
            }
        }
        return board;
    }
}
