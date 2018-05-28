package Algorithms.Searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InterpoltionSearch {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var input = in.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        var target = in.nextInt();
        var search = interpolationSearch(nums, target);
        if(search==-1) System.out.println("Number not found.");
        else System.out.println("Number found at position "+search);
    }

    private static int interpolationSearch(List<Integer> nums, int target){
        var start = 0;
        var end = nums.size()-1;
        while(start<end){
            var pos = start + ((target-nums.get(start))/(nums.get(end)-nums.get(start)));
            if(nums.get(pos)==target) return pos;
            if(target<nums.get(pos)) end = pos-1;
            else start = pos+1;
        }
        return -1;
    }
}
