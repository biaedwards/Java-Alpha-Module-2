//https://leetcode.com/problems/keys-and-rooms/description/
package LeetCode.Graphs;

import java.util.*;

public class KeysAndRooms {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var rooms = Integer.parseInt(in.nextLine());
        List<List<Integer>> keys = new ArrayList<>();
        for (int i = 0; i < rooms; i++) {
            var temp = in.nextLine().split(",");
            keys.add(new ArrayList<>());
            if(temp[0].equals("")){
                keys.get(i).add(i);
                continue;
            }
            for (String aTemp : temp) {
                keys.get(i).add(Integer.parseInt(aTemp));
            }
        }
        System.out.println(new Solution().canVisitAllRooms(keys) ? "true" : "false");
    }

    static class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            HashSet<Integer> canVisit = new HashSet<>();
            LinkedList<Integer> toVisit = new LinkedList<>();
            toVisit.add(0);
            canVisit.add(0);
            while(!toVisit.isEmpty()){
                int currentRoom = toVisit.poll();
                for(Integer key:rooms.get(currentRoom)){
                    if(canVisit.contains(key)) continue;
                    canVisit.add(key);
                    toVisit.add(key);
                }
            }
            return canVisit.size()==rooms.size();
        }
    }
}
