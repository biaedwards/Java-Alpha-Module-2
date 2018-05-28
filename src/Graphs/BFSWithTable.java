package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFSWithTable {
    ArrayList<ArrayList<Integer>> graph;

    public BFSWithTable(ArrayList<ArrayList<Integer>> graph) {
        this.graph = graph;
    }

    public ArrayList<Integer> BFS() {
        var queue = new LinkedList<Integer>();
        var path = new ArrayList<Integer>();
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                if(graph.get(i).get(j)==1){
                    queue.add(i);
                    break;
                }
            }
            if(queue.size()>0) break;
        }
        while(!queue.isEmpty()){
            int last = queue.poll();
            if(path.contains(last)) continue;
            path.add(last);
            for (int i = 0; i < graph.size(); i++) {
                if(graph.get(last).get(i)==1){
                    queue.add(i);
                }
            }
        }
        return path;
    }
}
