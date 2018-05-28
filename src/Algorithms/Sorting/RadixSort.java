package Algorithms.Sorting;

import java.util.*;
import java.util.stream.IntStream;

public class RadixSort {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var input = in.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        sort(nums);
        nums.forEach(x -> System.out.printf("%d ", x));
    }

    private static void sort(List<Integer> nums){
        var max = findMax(nums);
        var level = 1;
        while(max>0){
            countingSort(nums, level);
            level*=10;
            max/=10;
        }
    }

    private static void countingSort(List<Integer> nums, int level){
        var map = new HashMap<Integer, List<Integer>>();
        IntStream.range(0, 10)
                .forEach(x -> map.put(x,new ArrayList<>()));
        for (int num:nums){
            var place = (num/level)%10;
            map.get(place).add(num);
        }
        nums.clear();
        map.keySet().forEach(x -> nums.addAll(map.get(x)));
    }

    private static int findMax(List<Integer> nums){
        int max = nums.get(0);
        for(int num:nums){
            if(num>max) max = num;
        }
        return max;
    }
}
