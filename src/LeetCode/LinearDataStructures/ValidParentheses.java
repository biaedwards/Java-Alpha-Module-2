package LeetCode.LinearDataStructures;

import java.util.Scanner;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        System.out.println(Solution.isValid(expression));
    }

    static class Solution {
        public static boolean isValid(String s) {
            Stack<Character> opened = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char el = s.charAt(i);
                if(el=='('||el=='{'||el=='['){
                    opened.push(el);
                }
                else if(opened.empty()) return false;
                else{
                    switch(el){
                        case ')': if(opened.pop()!='(') return false; break;
                        case '}': if(opened.pop()!='{') return false; break;
                        case ']': if(opened.pop()!='[') return false; break;
                    }
                }
            }
            return opened.empty();
        }
    }
}

