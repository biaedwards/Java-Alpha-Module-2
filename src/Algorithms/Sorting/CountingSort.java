package Algorithms.Sorting;

import java.util.*;
import java.util.stream.IntStream;

public class CountingSort {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        sort(nums);
        nums.forEach(x -> System.out.printf("%d ", x));
    }

    private static void sort(List<Integer> nums){
        var map = new HashMap<Integer, Integer>();
        IntStream.range(1, findMax(nums)+1)
                .forEach(x -> map.put(x,0));
        for (int num:nums){
            map.put(num, map.get(num)+1);
        }
        nums.clear();
        map.keySet().forEach(x -> fillList(nums, x, map.get(x)));
    }

    private static void fillList(List<Integer> nums, int value, int count){
        while(count>0){
            nums.add(value);
            --count;
        }
    }

    private static int findMax(List<Integer> nums){
        int max = nums.get(0);
        for(int num:nums){
            if(num>max) max = num;
        }
        return max;
    }
}
