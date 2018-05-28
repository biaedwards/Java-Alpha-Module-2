//Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
//Time Complexity: O(n*n)
//Auxiliary Space: O(1)
//Boundary Cases: Insertion sort takes maximum time to sort if elements are sorted in reverse order. And it takes minimum time (Order of n) when elements are already sorted.
//Algorithmic Paradigm: Incremental Approach
//Sorting In Place: Yes
//Stable: Yes
//Online: Yes
//Uses: Insertion sort is used when number of elements is small. It can also be useful when input array is almost sorted, only few elements are misplaced in complete big array.

package Algorithms.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InsertionSort {
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
        for (int i = 0; i < nums.size()-1; i++) {
            int start = i+1;
            while(start>0&&nums.get(i+1)<nums.get(start-1)){
                start--;
            }
            nums.add(start, nums.get(i+1));
            nums.remove(i+2);
        }
    }
}
