package Algorithms.Graphs;

public class FloydWarshall {
    private long[][] graph;

    FloydWarshall(int nodes){
        graph = new long[nodes][nodes];
        initGraph();
    }

    long[][] floydWarshall(){
        for (var i = 0; i < graph.length; i++) {
            for (var j = 0; j < graph.length; j++) {
                if(j==i) continue;
                for (var k = 0; k < graph.length; k++) {
                    if(k==j||k==i) continue;
                    if(graph[j][k]>graph[j][i]+graph[i][k]){
                        graph[j][k]=graph[j][i]+graph[i][k];
                    }
                }
            }
        }
        return graph;
    }

    private void initGraph() {
        long infinity = Integer.MAX_VALUE;
        for (var i = 0; i < graph.length; i++) {
            for (var j = 0; j < graph.length; j++) {
                graph[i][j] = infinity;
            }
        }
    }

    void addEdge(int x, int y, int length){
        graph[x][y] = length;
    }
}
