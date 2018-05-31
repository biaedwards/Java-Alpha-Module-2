package LeetCode.HashTables;

import java.util.*;

public class NumberOfAtoms {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var formula = in.nextLine();
        System.out.println(new Solution().countOfAtoms(formula));
    }

    static class Solution {
        private String countOfAtoms(String formula) {
            Stack<HashMap<String, Integer>> stack = new Stack<>();
            HashMap<String, Integer> map = new HashMap<>();
            int n = formula.length();
            int i = 0;
            while (i < n) {
                char c = formula.charAt(i);
                ++i;
                if (c == '(') {
                    stack.push(map);
                    map = new HashMap<>();
                } else if (c == ')') {
                    int start = i;
                    int count = 1;
                    while (i<n&&Character.isDigit(formula.charAt(i))) {
                        ++i;
                    }
                    if (start != i) count = Integer.parseInt(formula.substring(start, i));
                    Map<String, Integer> tempMap = map;
                    map = stack.pop();
                    for(String key:tempMap.keySet()){
                        map.put(key, map.getOrDefault(key, 0)+tempMap.get(key)*count);
                    }
                } else {
                    int start = i - 1;
                    while (i<n&&Character.isLowerCase(formula.charAt(i))) {
                        ++i;
                    }
                    String atom = formula.substring(start, i);
                    start = i;
                    int count = 1;
                    while (i<n&&Character.isDigit(formula.charAt(i))) {
                        ++i;
                    }
                    if (start != i) count = Integer.parseInt(formula.substring(start, i));
                    map.put(atom, map.getOrDefault(atom, 0)+count);
                }
            }
            StringBuilder sb= new StringBuilder();
            List<String> list= new ArrayList<>(map.keySet());
            Collections.sort(list);
            for(String key: list){
                sb.append(key);
                if(map.get(key)>1) sb.append(map.get(key));
            }
            return sb.toString();
        }
    }
}
