//https://leetcode.com/problems/max-area-of-island/description/
package LeetCode.Graphs;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var rows = Integer.parseInt(in.nextLine());
        var cols = Integer.parseInt(in.nextLine());
        var grid = new int[rows][cols];
        for (var i = 0; i < rows; i++) {
            var temp = in.nextLine().split(",");
            for (var j = 0; j < cols; j++) {
                grid[i][j] = Integer.parseInt(temp[j]);
            }
        }
        System.out.println(new Solution().maxAreaOfIsland(grid));
    }

    static class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int[] dirX = {-1, 0, 1, 0};
            int[] dirY = {0, 1, 0, -1};
            boolean[][] visited = new boolean[rows][cols];
            int max = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 || visited[i][j]) continue;
                    LinkedList<int[]> queue = new LinkedList<>();
                    int current = 0;
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] last = queue.poll();
                        ++current;
                        for (int k = 0; k < 4; k++) {
                            int x = last[0] + dirX[k];
                            int y = last[1] + dirY[k];
                            if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || visited[x][y]) continue;
                            queue.add(new int[]{x, y});
                            visited[x][y] = true;
                        }
                    }
                    max = Math.max(current, max);
                }
            }
            return max;
        }
    }
}
