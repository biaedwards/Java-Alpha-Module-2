package Algorithms.Searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var input = in.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        var target = in.nextInt();
//        var search = binarySearchRecursive(nums, target, 0, nums.size());
        var search = binarySearchIterative(nums, target);
        if(search==-1) System.out.println("Number not found.");
        else System.out.println("Number found at position "+search);
    }

    private static int binarySearchRecursive(List<Integer> nums, int target, int start, int end) {
        var middle = (start + end) / 2;
        while (start < end) {
            if (nums.get(middle) > target) return binarySearchRecursive(nums, target, start, middle);
            if (nums.get(middle) < target) return binarySearchRecursive(nums, target, middle + 1, end);
            if (nums.get(middle) == target) return middle;
        }
        return -1;
    }

    private static int binarySearchIterative(List<Integer> nums, int target){
        var start = 0;
        var end = nums.size();
        while(start<end){
            var middle = (start+end)/2;
            if(nums.get(middle)>target) end = middle;
            else if(nums.get(middle)<target) start = middle+1;
            else return middle;
        }
        return -1;
    }
}
