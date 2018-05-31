//https://leetcode.com/problems/asteroid-collision/description/
package LeetCode.Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class AsteroidCollision {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var num = in.nextInt();
        var asteroids = new int[num];
        for (int i = 0; i < num; i++) {
            asteroids[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(new Solution().asteroidCollision(asteroids)));
    }
    static class Solution {
        private int[] asteroidCollision(int[] asteroids) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < asteroids.length; i++) {
                int temp = asteroids[i];
                if(list.isEmpty()){
                    list.add(temp);
                    continue;
                }
                if(temp>0||list.getLast()<0){
                    list.add(temp);
                    continue;
                }
                while(list.getLast()<-temp){
                    list.removeLast();
                    if(list.isEmpty()||list.getLast()<0){
                        list.add(temp);
                        break;
                    }
                }
                if(!list.isEmpty()&&list.getLast()==-temp) list.removeLast();
            }
            int[] answer = new int[list.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }
}
