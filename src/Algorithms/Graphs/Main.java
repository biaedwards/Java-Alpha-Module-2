package Algorithms.Graphs;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var graphSize = in.nextInt();
//        var dijkstra = new Dijkstra(graphSize);
//        var edges = in.nextInt();
//        for (var i = 0; i < edges; i++) {
//            dijkstra.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
//        }
//        var answer = dijkstra.dijkstra(0);
//        for(var i:answer){
//            System.out.printf("%d ", i);
//        }


//        var floyd = new FloydWarshall(graphSize);
//        var edges = in.nextInt();
//        for (var i = 0; i < edges; i++) {
//            floyd.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
//        }
//        var answer = floyd.floydWarshall();
//        for (var anAnswer : answer) {
//            for (var j = 0; j < answer.length; j++) {
//                if (anAnswer[j] == Integer.MAX_VALUE) System.out.print("INF ");
//                else System.out.printf("%d ", anAnswer[j]);
//            }
//            System.out.println();
//        }


//        var kruskal = new Kruskal(graphSize);
//        var edges = in.nextInt();
//        for (var i = 0; i < edges; i++) {
//            kruskal.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
//        }
//        var tree = kruskal.kruskal();
//        while(!tree.isEmpty()){
//            System.out.printf("%s\n", Arrays.toString(tree.poll()));
//        }


        var prim = new Prim(graphSize);
        var edges = in.nextInt();
        for (var i = 0; i < edges; i++) {
            prim.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        System.out.println(prim.prim());
    }
}
