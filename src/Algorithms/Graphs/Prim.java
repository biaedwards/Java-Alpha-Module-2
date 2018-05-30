package Algorithms.Graphs;

import java.util.*;

public class Prim {
    private int nodes;
    private HashMap<Integer, HashSet<int[]>> edges;

    Prim(int nodes) {
        this.nodes = nodes;
        edges = new HashMap<>();
    }

    int prim() {
        var known = new boolean[nodes];
        var cost = new int[nodes];
        var path = new int[nodes];
        for (var i = 0; i < cost.length; i++) {
            cost[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        cost[0] = 0;
        for (var i = 0; i < nodes; i++) {
            var index = findMin(known, cost);
            known[index] = true;
            updateCostPath(known, cost, path, index);
        }
        var minCostPath = 0;
        for (var i = 0; i < nodes; i++) {
            minCostPath+=cost[i];
        }
        return minCostPath;
    }

    private void updateCostPath(boolean[] known, int[] cost, int[] path, int index) {
        for (var edge : edges.get(index)) {
            if (known[edge[1]]) continue;
            if(cost[edge[1]]>edge[2]){
                cost[edge[1]] = edge[2];
                path[edge[1]] = index;
            }
        }
    }

    private int findMin(boolean[] known, int[] cost) {
        var minCost = Integer.MAX_VALUE;
        var index = -1;
        for (var i = 0; i < nodes; i++) {
            if (known[i]) continue;
            if (minCost > cost[i]) {
                minCost = cost[i];
                index = i;
            }
        }
        return index;
    }

    void addEdge(int x, int y, int length) {
        int[] tempX = {x, y, length};
        if (edges.containsKey(x)) {
            edges.get(x).add(tempX);
        } else {
            var hs = new HashSet<int[]>();
            hs.add(tempX);
            edges.put(x, hs);
        }

        int[] tempY = {y, x, length};
        if (edges.containsKey(y)) {
            edges.get(y).add(tempY);
        } else {
            var hs = new HashSet<int[]>();
            hs.add(tempY);
            edges.put(y, hs);
        }
    }
}
