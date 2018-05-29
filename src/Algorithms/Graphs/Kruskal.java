package Algorithms.Graphs;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Kruskal {
    private int nodes;
    private PriorityQueue<int[]> edges;

    Kruskal(int nodes) {
        this.nodes = nodes;
        edges = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
    }

    Queue<int[]> kruskal() {
        var disjointSet = new int[nodes];
        for (var i = 0; i < nodes; i++) {
            disjointSet[i] = -1;
        }
        Queue<int[]> tree = new LinkedList<>();
        while (true) {
            var temp = edges.poll();
            var x = find(temp[0], disjointSet);
            var y = find(temp[1], disjointSet);
            if (x != y) {
                tree.add(temp);
                disjointSet[y] += disjointSet[x];
                if (disjointSet[y] == -1 * nodes) break;
                disjointSet[x] = y;
            }
        }
        return tree;
    }

    private int find(int node, int[] disjointSet) {
        while (disjointSet[node] > -1) node = disjointSet[node];
        return node;
    }

    void addEdge(int x, int y, int length) {
        int[] edge = {x, y, length};
        edges.offer(edge);
    }
}
