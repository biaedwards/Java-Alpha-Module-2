//https://leetcode.com/problems/island-perimeter/description/
package LeetCode.HashTables;

import java.util.LinkedList;
import java.util.Scanner;

public class IslandPerimeter {
    public static void main(String[] args){
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
        System.out.println(new Solution().islandPerimeter(grid));
    }

    static class Solution {
        public int islandPerimeter(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int[] dirX = {-1, 0, 1, 0};
            int[] dirY = {0, 1, 0, -1};
            boolean[][] visited = new boolean[rows][cols];
            int perimeter = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 0 || visited[i][j]) continue;
                    LinkedList<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int[] last = queue.poll();
                        perimeter+=4;
                        for (int k = 0; k < 4; k++) {
                            int x = last[0] + dirX[k];
                            int y = last[1] + dirY[k];
                            if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 ) continue;
                            if(visited[x][y]){
                                --perimeter;
                                continue;
                            }
                            queue.add(new int[]{x, y});
                            visited[x][y] = true;
                            --perimeter;
                        }
                    }
                }
            }
            return perimeter;
        }
    }
}
