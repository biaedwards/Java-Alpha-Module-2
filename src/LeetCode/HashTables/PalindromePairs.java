//https://leetcode.com/problems/palindrome-pairs/description/

package LeetCode.HashTables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PalindromePairs {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var words = in.nextLine().split(", ");
        System.out.println(new Solution().palindromePairs(words));
    }
    static class Solution {
        private List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> pairs = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                map.put(words[i], i);
            }
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j <= words[i].length(); j++) {
                    String left = words[i].substring(0,j);
                    String right = words[i].substring(j);
                    if(isPalindrome(left)){
                        String rev = new StringBuilder(right).reverse().toString();
                        if(map.containsKey(rev)&&map.get(rev)!=i){
                            List<Integer> pair = new ArrayList<>();
                            pair.add(map.get(rev));
                            pair.add(i);
                            pairs.add(pair);
                        }
                    }
                    if(isPalindrome(right)){
                        String rev = new StringBuilder(left).reverse().toString();
                        if(map.containsKey(rev)&&map.get(rev)!=i&&right.length()!=0){
                            List<Integer> pair = new ArrayList<>();
                            pair.add(i);
                            pair.add(map.get(rev));
                            pairs.add(pair);
                        }
                    }
                }
            }
            return pairs;
        }
        private boolean isPalindrome(String s){
            int start = 0;
            int end = s.length()-1;
            while(start<end){
                if(s.charAt(start++)!=s.charAt(end--)) return false;
            }
            return true;
        }
    }
}
