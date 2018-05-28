package LeetCode.HashTables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var secret = in.nextLine();
        var guess = in.nextLine();
        System.out.println(new Solution().getHint(secret, guess));
    }
    static class Solution {
        public String getHint(String secret, String guess) {
            HashMap<Character, Integer> secretMap = new HashMap<>();
            int cows = 0;
            HashSet<Integer> bulls = new HashSet<>();
            for (int i = 0; i < secret.length(); i++) {
                char temp = secret.charAt(i);
                if(temp==guess.charAt(i)){
                    bulls.add(i);
                    continue;
                }
                if(secretMap.containsKey(temp)){
                    secretMap.put(temp, secretMap.get(temp)+1);
                } else{
                    secretMap.put(temp, 1);
                }
            }

            for (int i = 0; i < guess.length(); i++) {
                if(bulls.contains(i)){
                    continue;
                }
                char temp = guess.charAt(i);
                if(secretMap.containsKey(temp)&&secretMap.get(temp)>0){
                    secretMap.put(temp, secretMap.get(temp)-1);
                    cows++;
                }
            }
            return String.valueOf(bulls.size()+"A"+cows+"B");
        }
    }
}
