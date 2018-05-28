package LeetCode.Graphs;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var classes = in.nextInt();
        var pre = in.nextInt();
        var prerequisites = new int[pre][2];
        for (int i = 0; i < pre; i++) {
            prerequisites[i][0] = in.nextInt();
            prerequisites[i][1] = in.nextInt();
        }
        System.out.println((new Solution().canFinish(classes, prerequisites))?"yes":"no");
    }
    static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                int from = prerequisite[1];
                int to = prerequisite[0];
                if (map.containsKey(from)) {
                    map.get(from).add(to);
                } else {
                    HashSet<Integer> temp = new HashSet<>();
                    temp.add(to);
                    map.put(from, temp);
                }
            }

            boolean[] allCourses = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                if(allCourses[i]) continue;
                allCourses[i] = true;
                if(!map.containsKey(i)) continue;
                if(!rec(new HashSet<>(), map, i)) return false;
            }
            return true;
        }

        private boolean rec(HashSet<Integer> visited, HashMap<Integer, HashSet<Integer>> map, int current){
            if(!map.containsKey(current)) return true;
            if(visited.contains(current)) return false;
            visited.add(current);
            for (Integer node:map.get(current)) {
                if(!rec(visited, map, node)) return false;
            }
            visited.remove(current);
            return true;
        }
    }
}
