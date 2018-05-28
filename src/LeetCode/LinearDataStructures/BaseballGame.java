package LeetCode.LinearDataStructures;

import java.util.Scanner;
import java.util.Stack;

public class BaseballGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        System.out.println(Solution.calPoints(input));
    }

    static class Solution {
        public static int calPoints(String[] ops) {
            Stack<Integer> points = new Stack<>();
            for (String op : ops) {
                if (op.equals("+")) {
                    int last = points.pop();
                    int current = points.peek() + last;
                    points.push(last);
                    points.push(current);
                } else if (op.equals("D")) {
                    points.push(2 * points.peek());
                } else if (op.equals("C")) {
                    points.pop();
                } else {
                    points.push(Integer.parseInt(op));
                }
            }
            int count = 0;
            while(!points.empty()){
                count+=points.pop();
            }
            return count;
        }
    }
}
