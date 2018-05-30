//Source removal algorithm for topological sort
package LeetCode.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CourseSceduleSRA {
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
            int[] pres = new int[numCourses];
            for (int[] prerequisite : prerequisites) {
                int from = prerequisite[1];
                int to = prerequisite[0];
                pres[to]++;
                if (map.containsKey(from)) {
                    map.get(from).add(to);
                } else {
                    HashSet<Integer> temp = new HashSet<>();
                    temp.add(to);
                    map.put(from, temp);
                }
            }
//            ArrayList<Integer> result = new ArrayList<>();
            HashSet<Integer> hs = new HashSet<>();
            for (int i = 0; i < numCourses; i++) {
                int next = findNextFree(pres, hs);
                if(next==-1) return false;
                hs.add(next);
//                result.add(next);
                if(!map.containsKey(next)) continue;
                map.get(next).forEach(x->--pres[x]);
            }
//            System.out.println(result.toString());
            return true;
        }

        private int findNextFree(int[] pres, HashSet<Integer> hs){
            for (int i = 0; i < pres.length; i++) {
                if(pres[i]<1&&!hs.contains(i)) return i;
            }
            return -1;
        }
    }
}
