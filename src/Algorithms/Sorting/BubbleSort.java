//Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.

//Worst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.
//Best Case Time Complexity: O(n). Best case occurs when array is already sorted.
//Auxiliary Space: O(1)
//Boundary Cases: Bubble sort takes minimum time (Order of n) when elements are already sorted.
//Sorting In Place: Yes
//Stable: Yes

//Due to its simplicity, bubble sort is often used to introduce the concept of a sorting algorithm.
//In computer graphics it is popular for its capability to detect a very small error (like swap of just two elements) in almost-sorted arrays and fix it with just linear complexity (2n).
// For example, it is used in a polygon filling algorithm, where bounding lines are sorted by their x coordinate at a specific scan line
// (a line parallel to x axis) and with incrementing y their order changes (two elements are swapped) only at intersections of two lines.


package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BubbleSort {
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
        for (int i = nums.size()-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(nums.get(j)<nums.get(j+1)) continue;
                int swap = nums.get(j);
                nums.set(j, nums.get(j+1));
                nums.set(j+1, swap);
            }
        }
    }
}
