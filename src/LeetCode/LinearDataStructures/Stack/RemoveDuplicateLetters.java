//https://leetcode.com/problems/remove-duplicate-letters/description/
package LeetCode.LinearDataStructures.Stack;

import java.util.*;

public class RemoveDuplicateLetters {
    class Solution {
        public String removeDuplicateLetters(String s) {
            if (s == null) return "";
            Stack<Character> stack = new Stack<>();
            HashMap<Character, Integer> counts = new HashMap<>();
            HashSet<Character> used = new HashSet<>(counts.size());
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                counts.put(c, counts.getOrDefault(c,0)+1);
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(used.contains(c)){
                    counts.put(c, counts.get(c)-1);
                    continue;
                }
                while(!stack.empty()&&c<stack.peek()&&counts.get(stack.peek())>0){
                    used.remove(stack.pop());
                }
                stack.push(c);
                used.add(c);
                counts.put(c, counts.get(c)-1);
            }
            StringBuilder sb = new StringBuilder();
            while(!stack.empty()){
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }
    }
}
