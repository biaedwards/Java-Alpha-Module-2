//https://leetcode.com/problems/is-graph-bipartite/description/
package LeetCode.Graphs;

import java.util.*;
import java.util.stream.IntStream;

public class IsGraphBipartite {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var graph = new int[in.nextInt()][];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new int[in.nextInt()];
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = in.nextInt();
            }
        }
        System.out.println(new Solution().isBipartite(graph) ? "true" : "false");
    }

    static class Solution {
        public boolean isBipartite(int[][] graph) {
            int[] visited = new int[graph.length];
            for (int i = 0; i < graph.length; i++) {
                if(graph[i].length==0||visited[i]!=0) continue;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = 1;
                while(!queue.isEmpty()){
                    int current = queue.poll();
                    for (int c:graph[current]) {
                        if(visited[c]==0){
                            visited[c] = visited[current]+1;
                            queue.add(c);
                        } else{
                            if((visited[c]+visited[current]+1)%2==1) return false;
                        }
                    }
                }
            }
            return true;
        }
    }
}
