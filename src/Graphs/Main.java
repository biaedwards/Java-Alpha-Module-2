package Graphs;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var in = new Scanner(System.in);
//        var nodes = Integer.parseInt(in.nextLine());
//        var graphList = new ArrayList<ArrayList<Character>>();
//        for (var i = 0; i < nodes; i++) {
//            var node = in.nextLine().split(", ");
//            var edges = new ArrayList<Character>();
//            for (var j = 0; j < node.length; j++) {
//                edges.add(node[j].charAt(0));
//            }
//            graphList.add(edges);
//        }
//
//        var dfs = new DFSWithList(graphList);
//        dfs.DFSIterative().forEach(System.out::print);
        var graphTable = new ArrayList<ArrayList<Integer>>();
        var nodes = in.nextInt();
        for (int i = 0; i < nodes; i++) {
            graphTable.add(new ArrayList<>());
            for (int j = 0; j < nodes; j++) {
                graphTable.get(i).add(in.nextInt());
            }
        }
        var bfs = new BFSWithTable(graphTable);
        bfs.BFS().forEach(System.out::print);
    }
}
