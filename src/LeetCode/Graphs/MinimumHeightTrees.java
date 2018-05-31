package LeetCode.Graphs;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumHeightTrees {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var n = in.nextInt();
        var edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
        }
        System.out.println(new Solution().findMinHeightTrees(n, edges));
    }

    static class Solution {
        private List<Integer> findMinHeightTrees(int n, int[][] edges) {
            if (n == 1) return Collections.singletonList(0);

            List<Set<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new HashSet<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }

            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (adj.get(i).size() == 1) leaves.add(i);

            while (n > 2) {
                n -= leaves.size();
                List<Integer> newLeaves = new ArrayList<>();
                for (int i : leaves) {
                    int j = adj.get(i).iterator().next();
                    adj.get(j).remove(i);
                    if(adj.get(j).size()==1) newLeaves.add(j);
                }
                leaves = newLeaves;
            }
            return leaves;
        }
    }
}
