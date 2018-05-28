package LeetCode.HashTables;

import java.util.HashMap;
import java.util.Scanner;

public class FractoringToRecurringDecimal {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        System.out.println(new Solution().fractionToDecimal(in.nextInt(), in.nextInt()));
    }

    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder answer = new StringBuilder();
            answer.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
            long num = Math.abs((long)numerator);
            long den = Math.abs((long)denominator);
            if(num==0) return "0";
            answer.append(num/den);
            num = num%den;
            if(num==0) return answer.toString();
            answer.append(".");
            HashMap<Long, Integer> map = new HashMap<>();
            while(num!=0){
                map.put(num, answer.length());
                answer.append((num*10)/den);
                num = (num*10)%den;
                if(map.containsKey(num)){
                    answer.insert(map.get(num), "(");
                    answer.append(")");
                    break;
                }
            }
            return answer.toString();
        }
    }
}
