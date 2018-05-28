package LeetCode.HashTables;

import java.util.HashMap;
import java.util.Scanner;

public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(new Solution().firstUniqChar(input));
    }

    static class Solution {
        public int firstUniqChar(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                Character ch = s.charAt(i);
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1) return i;
            }
            return -1;
        }
    }
}
