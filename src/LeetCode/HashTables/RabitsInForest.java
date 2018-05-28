package LeetCode.HashTables;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class RabitsInForest {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var inputStr = in.nextLine().split(", ");
        var nums = new int[inputStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }
        System.out.println(new Solution().numRabbits(nums));
    }

    static class Solution {
        public int numRabbits(int[] answers) {
            if(answers.length==0) return 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < answers.length; i++) {
                if(map.containsKey(answers[i])){
                    map.put(answers[i], map.get(answers[i])+1);
                } else {
                    map.put(answers[i], 1);
                }
            }
            Stack<Integer> rabbits = new Stack<>();
            rabbits.push(0);
            map.keySet().forEach(x -> moreRabbits(x, map, rabbits));
            return rabbits.pop();
        }

        private static void moreRabbits(int x, HashMap<Integer, Integer> map, Stack<Integer> rabbits){
            int count = map.get(x);
            int formula = (count+x)/(x+1)*(x+1);
            rabbits.push(rabbits.pop()+formula);
        }
    }
}
