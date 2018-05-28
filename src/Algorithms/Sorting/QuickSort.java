/*The key process in quickSort is partition(). Target of partitions is, given an array and an element x of array as pivot, put x at its correct position in sorted array 
and put all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. All this should be done in linear time.
 */

package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        ArrayList<Integer> nums = new ArrayList<>();
        Arrays.stream(input)
                .forEach(x -> nums.add(Integer.parseInt(x)));
        quickSort(nums, 0, nums.size());
        nums.forEach(x -> System.out.printf("%d ", x));
    }

    private static void quickSort(ArrayList<Integer> nums, int start, int end) {
        if (start >= end - 1) return;
        int position = partition(nums, end);
        quickSort(nums, start, position);
        quickSort(nums, position + 1, end);
    }

    private static int partition(ArrayList<Integer> nums, int end) {
        int pivot = nums.get(end - 1);
        int i = -1;
        for (int j = 0; j < end - 1; j++) {
            if (nums.get(j) <= pivot) {
                ++i;
                int swap = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, swap);
            }
        }
        nums.set(end - 1, nums.get(++i));
        nums.set(i, pivot);
        return i;
    }
}
