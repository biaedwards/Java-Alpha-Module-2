package Graphs;

import java.util.ArrayList;
import java.util.Stack;

public class DFSWithList {
    private ArrayList<ArrayList<Character>> graph;

    DFSWithList(ArrayList<ArrayList<Character>> graph) {
        this.graph = graph;
    }

    ArrayList<Character> DFSIterative(){
        var path = new ArrayList<Character>();
        var stack = new Stack<Character>();
        path.add(graph.get(0).get(0));
        stack.push(graph.get(0).get(0));
        while(!stack.empty()){
            var ch = stack.peek();
            var index = -1;
            for (var i=0; i<graph.size(); i++) {
                if(graph.get(i).get(0)!=ch) continue;
                index = i;
                break;
            }
            var anythingNew = false;
            for (var i = 1; i < graph.get(index).size(); i++) {
                if(path.contains(graph.get(index).get(i))) continue;
                path.add(graph.get(index).get(i));
                stack.add(graph.get(index).get(i));
                anythingNew = true;
                break;
            }
            if(!anythingNew) stack.pop();
        }
        return path;
    }

    ArrayList<Character> DFSRecursive() {
        var path = new ArrayList<Character>();
        recursion(graph.get(0).get(0), path);
        return path;
    }

    private void recursion(Character ch, ArrayList<Character> path){
        var index = -1;
        for (var i=0; i<graph.size(); i++) {
            if(graph.get(i).get(0)!=ch) continue;
            index = i;
            break;
        }
        for (var i = 0; i < graph.get(index).size(); i++) {
            if(path.contains(graph.get(index).get(i))) continue;
            path.add(graph.get(index).get(i));
            recursion(graph.get(index).get(i), path);
        }
    }
}
