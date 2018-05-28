//The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order) from unsorted part and putting it at the beginning.
//Time Complexity: O(n2) as there are two nested loops.

//Auxiliary Space: O(1)
//The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a costly operation.

package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SelectionSort {
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
        for (int i = 0; i < nums.size()-1; i++) {
            int min = i;
            for (int j = i+1; j < nums.size(); j++) {
                if(nums.get(min)<nums.get(j)) continue;
                min = j;
            }
            int swap = nums.get(i);
            nums.set(i, nums.get(min));
            nums.set(min, swap);
        }
    }
}
