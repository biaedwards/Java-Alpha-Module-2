//https://leetcode.com/problems/flood-fill/description/
package LeetCode.Graphs;

import java.util.Stack;

public class FloodFill {
    public static void main(String[] args) {
        var image = new int[2][3];
        image[0][0] = 0;
        image[0][1] = 0;
        image[0][2] = 1;
        image[1][0] = 1;
        image[1][1] = 0;
        image[1][2] = 0;
        var newImage = new Solution().floodFill(image, 1, 0, 2);
        System.out.println("Hello");
    }

    static class Solution {
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            int m = image.length;
            int n = image[0].length;
            int[] dir = {1, -1, n, -1*n};
            int color = image[sr][sc];
            image[sr][sc] = newColor;
            Stack<Integer> stack = new Stack<>();
            stack.push(code(sr, sc, n));

            boolean[][] visited = new boolean[m][n];
            visited[sr][sc] = true;
            while (!stack.empty()) {
                int last = stack.peek();
                for (int i = 0; i < 4; i++) {
                    int newX = decodeX(last + dir[i], n);
                    int newY = decodeY(last + dir[i], n);
                    if (newX >= m || newX < 0 || newY >= n || newY < 0 || image[newX][newY] != color || visited[newX][newY]||(newX!=decodeX(last,n)&&newY!=decodeY(last,n)))
                        continue;
                    visited[newX][newY] = true;
                    image[newX][newY] = newColor;
                    stack.push(code(newX, newY, n));
                }
                if (stack.peek() == last) stack.pop();
            }
            return image;
        }

        int code(int x, int y, int length) {
            return x * length + y;
        }

        int decodeX(int code, int length) {
            return code / length;
        }

        int decodeY(int code, int length) {
            return code % length;
        }
    }
}
