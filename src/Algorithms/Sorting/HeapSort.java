package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HeapSort {
    public static void main(String[] args){
        var in = new Scanner(System.in);
        var input = in.nextLine().split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        sort(nums);
        nums.forEach(x -> System.out.printf("%d ", x));
    }

    private static void sort(ArrayList<Integer> nums){
        for (var i = (nums.size()-1)/2; i >=0 ; i--) {
            heapify(nums, i);
        }
        var sorted = new ArrayList<Integer>();
        while(nums.size()>0){
            sorted.add(nums.get(0));
            nums.set(0, nums.get(nums.size()-1));
            nums.remove(nums.size()-1);
            heapify(nums, 0);
        }
        nums.addAll(sorted);
    }

    private static void heapify(ArrayList<Integer> nums, int parent){
        var smallest = parent;
        var leftChild = 2*parent+1;
        var rightChild = 2*parent+2;
        if(leftChild<nums.size()&&nums.get(leftChild)<nums.get(smallest)){
            smallest = leftChild;
        }
        if(rightChild<nums.size()&&nums.get(rightChild)<nums.get(smallest)){
            smallest = rightChild;
        }
        if(smallest!=parent){
            var swap = nums.get(parent);
            nums.set(parent, nums.get(smallest));
            nums.set(smallest, swap);
            heapify(nums, smallest);
        }
    }
}
