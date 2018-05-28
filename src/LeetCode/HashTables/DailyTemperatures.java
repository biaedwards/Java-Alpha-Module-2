package LeetCode.HashTables;

import java.util.Arrays;
import java.util.Scanner;

public class DailyTemperatures {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var inputStr = in.nextLine().split(", ");
        var nums = new int[inputStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(inputStr[i]);
        }
        System.out.println(Arrays.toString(new Solution().dailyTemperatures(nums)));
    }

    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] days = new int[temperatures.length];
            days[days.length-1]=0;
            for (int i = temperatures.length-2; i >=0; i--) {
                if(temperatures[i]<temperatures[i+1]){
                    days[i] = 1;
                } else {
                    days[i] = 1+days[i+1];
                    int day = i+1+days[i+1];
                    while(temperatures[day]<=temperatures[i]){
                        days[i]+=days[day];
                        if(days[day]==0){
                            days[i] = 0;
                            break;
                        }
                        day = day+days[day];
                    }
                }
            }
            return days;
        }
    }
}
