package LeetCode.HashTables;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaxPointsOnALine {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        int n = in.nextInt();
        var points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(in.nextInt(), in.nextInt());
        }
        System.out.println(new Solution().maxPoints(points));
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    static class Solution {
        private int maxPoints(Point[] points) {
            if(points==null) return 0;
            if(points.length<2) return points.length;
            int result = 0;
            HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                map.clear();
                int max = 0, same = 0;
                for (int j = i+1; j < points.length; j++) {
                    int x = points[j].x - points[i].x;
                    int y = points[j].y - points[i].y;
                    if(x==0&&y==0){
                        ++same;
                        continue;
                    }
                    int gcd = GCD(x,y);
                    if(gcd!=0){
                        x/=gcd;
                        y/=gcd;
                    }
                    if(!map.containsKey(x)) map.put(x, new HashMap<Integer, Integer>());
                    map.get(x).put(y, map.get(x).getOrDefault(y, 0)+1);
                    max = Math.max(max, map.get(x).get(y));
                }
                max += same + 1;
                result = Math.max(result, max);
            }
            return result;
        }

        //Greatest Common Denominator
        private int GCD(int x, int y){
            if(y==0) return x;
            else return GCD(y, x%y);
        }
    }
}
