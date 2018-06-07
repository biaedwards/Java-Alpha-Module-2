package LeetCode.Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class FriendCircles {
    class Solution {
        public int findCircleNum(int[][] M) {
            if(M.length==0) return 0;

            boolean[] visited = new boolean[M.length];

            int groups = 0;

            for (int i = 0; i < M.length; i++) {
                if(visited[i]) continue;

                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                while(!q.isEmpty()){
                    int current = q.poll();
                    if(visited[current]) continue;
                    visited[current] = true;
                    for (int j = 0; j < M.length; j++) {
                        if(M[current][j]==1) q.add(j);
                    }
                }
                ++groups;
            }

            return groups;
        }
    }
}
