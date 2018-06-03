package LeetCode.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcatenatedWords {
    class Solution {
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                map.put(words[i], i);
            }
            List<String> list = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if(isConcat(map, words[i], i)) list.add(words[i]);
            }
            return list;
        }

        private boolean isConcat(Map<String, Integer> map, String word, int index){
            if(map.containsKey(word)&&map.get(word)!=index) return true;
            for (int i = 1; i < word.length(); i++) {
                if(map.containsKey(word.substring(0,i))&&isConcat(map, word.substring(i), index)) return true;
            }
            return false;
        }
    }
}
