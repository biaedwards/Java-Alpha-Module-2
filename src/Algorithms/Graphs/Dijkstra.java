package Algorithms.Graphs;

public class Dijkstra {
    private static int[][] graph;

    Dijkstra(int graphSize){
        initGraph(graphSize);
    }

    public int[] dijkstra(int x) {
        var result = new int[graph.length];
        var done = new boolean[graph.length];

        for (var i = 0; i < result.length; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        result[x] = 0;

        var ready = 0;
        for (var n = 0; n < graph.length-1; n++) {
            var minValue = Integer.MAX_VALUE;
            var node = -1;
            for (var i = 0; i<result.length; i++) {
                if (done[i]) continue;
                if (result[i] < minValue) {
                    minValue = result[i];
                    node = i;
                }
            }
            done[node] = true;

            for (var i = 0; i < graph.length; i++) {
                if (done[i] || graph[node][i] == -1) continue;
                result[i] = Math.min(result[i], minValue + graph[node][i]);
            }
        }
        for (var i = 0; i < result.length; i++) {
            if(result[i]==Integer.MAX_VALUE) result[i] = -1;
        }
        return result;
    }

    private void initGraph(int graphSize) {
        graph = new int[graphSize][graphSize];
        for (var i = 0; i < graphSize; i++) {
            for (var j = 0; j < graphSize; j++) {
                graph[i][j] = -1;
            }
        }
    }

    void addEdge(int x, int y, int length){
        graph[x][y] = length;
    }
}
