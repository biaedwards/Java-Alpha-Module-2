package LeetCode.HashTables;

import java.util.HashSet;
import java.util.Scanner;

public class JewelsAndStones {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var jewels = in.nextLine();
        var stones = in.nextLine();
        System.out.println(new Solution().numJewelsInStones(jewels, stones));
    }

    static class Solution {
        public int numJewelsInStones(String J, String S) {
            HashSet<Character> jewels = new HashSet<>();
            for (int i = 0; i < J.length(); i++) {
                jewels.add(J.charAt(i));
            }
            int counter = 0;
            for (int i = 0; i < S.length(); i++) {
                if(jewels.contains(S.charAt(i))) counter++;
            }
            return counter;
        }
    }
}
