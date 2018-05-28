package LeetCode.Graphs;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var N = in.nextInt();
        var K = in.nextInt();
        var times = new int[in.nextInt()][3];
        for (int i = 0; i < times.length; i++) {
            times[i][0] = in.nextInt();
            times[i][1] = in.nextInt();
            times[i][2] = in.nextInt();
        }
        System.out.println(new Solution().networkDelayTime(times, N, K));
    }
    static class Solution {
        public int networkDelayTime(int[][] times, int N, int K) {
            if(times==null||times.length==0) return -1;
            HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
            for (int[] time : times) {
                if (map.containsKey(time[0])) {
                    map.get(time[0]).put(time[1], time[2]);
                } else {
                    HashMap<Integer, Integer> temp = new HashMap<>();
                    temp.put(time[1], time[2]);
                    map.put(time[0], temp);
                }
            }
            HashMap<Integer, Integer> distances = new HashMap<>();
            distances.put(K,0);
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(i -> i[1]));
            queue.offer(new int[]{K,0});
            while(!queue.isEmpty()){
                int[] current = queue.poll();
                if(distances.containsKey(current[0])&&distances.get(current[0]) < current[1]){
                    continue;
                }
                if(map.get(current[0])==null){
                    continue;
                }
                for(Map.Entry<Integer, Integer> path:map.get(current[0]).entrySet()){
                    int node = path.getKey();
                    int distance = current[1] + path.getValue();
                    if(distances.containsKey(node)&&distances.get(node) <= distance){
                        continue;
                    }
                    distances.put(node, distance);
                    queue.offer(new int[]{node, distance});
                }
            }
            int max = -1;
            for(Map.Entry<Integer, Integer> current:distances.entrySet()){
                max = Math.max(max, current.getValue());
            }
            return max;
        }
    }
}
