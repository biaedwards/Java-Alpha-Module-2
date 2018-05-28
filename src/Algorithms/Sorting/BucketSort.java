package Algorithms.Sorting;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BucketSort {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var input = in.nextLine().split(" ");
        List<Double> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Double.valueOf(x)));
        bucketSort(nums, 10);
        var df = new DecimalFormat(".##");
        nums.forEach(x -> System.out.printf("%s ", df.format(x)));
    }

    private static void bucketSort(List<Double> nums, int n){
        List<List<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }
        for (var num : nums) {
            buckets.get((int) (n * num)).add(num);
        }
        nums.clear();
        for (var bucket:buckets){
            insertionSort(bucket);
            nums.addAll(bucket);
        }
    }

    private static void insertionSort(List<Double> nums){
        for (var i = 0; i < nums.size()-1; i++) {
            var start = i+1;
            while(start>0&&nums.get(i+1)<nums.get(start-1)){
                start--;
            }
            nums.add(start, nums.get(i+1));
            nums.remove(i+2);
        }
    }
}
