//https://leetcode.com/problems/task-scheduler/description/
package LeetCode.LinearDataStructures.Queue;

import java.util.HashMap;

public class TaskScheduler {
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            if(tasks.length<2) return tasks.length;
            HashMap<Character, Integer> count = new HashMap<>();
            for (char task : tasks) {
                count.put(task, count.getOrDefault(task, 0) + 1);
            }
            int max = 0;
            int howMany = 1;
            for(Integer current:count.values()){
                if(current==max) howMany++;
                else if(current>max){
                    max = current;
                    howMany = 1;
                }
            }
            int shortest = (max-1)*(n+1)+howMany;
            return Math.max(shortest, tasks.length);
        }
    }
}
