/*
Like QuickSort, Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves.
 The merge() function is used for merging two halves. The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] are sorted and merges the two sorted sub-arrays into one. See following C implementation for details.

MergeSort(arr[], l,  r)
If r > l
     1. Find the middle point to divide the array into two halves:
             middle m = (l+r)/2
     2. Call mergeSort for first half:
             Call mergeSort(arr, l, m)
     3. Call mergeSort for second half:
             Call mergeSort(arr, m+1, r)
     4. Merge the two halves sorted in step 2 and 3:
             Call merge(arr, l, m, r)

Time Complexity: Sorting arrays on different machines. Merge Sort is a recursive algorithm and time complexity can be expressed as following recurrence relation.
T(n) = 2T(n/2) + \Theta(n)
The above recurrence can be solved either using Recurrence Tree method or Master method. It falls in case II of Master Method and solution of the recurrence is \Theta(nLogn).
Time complexity of Merge Sort is \Theta(nLogn) in all 3 cases (worst, average and best) as merge sort always divides the array in two halves and take linear time to merge two halves.

Auxiliary Space: O(n)

Algorithmic Paradigm: Divide and Conquer

Sorting In Place: No in a typical implementation

Stable: Yes
*/

package Algorithms.Sorting;

import java.util.*;

public class MergeSort {
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
        List<Integer> temp = new ArrayList<>(Collections.nCopies(nums.size(), 0));
        mergeSort(nums, 0, nums.size(), temp);
    }

    private static void mergeSort(List<Integer> nums, int startIndex, int endIndex, List<Integer> temp){
        int middleIndex = (startIndex+endIndex)/2;
        if(startIndex<middleIndex){
            mergeSort(nums, startIndex, middleIndex, temp);
            mergeSort(nums, middleIndex, endIndex, temp);
            merge(nums, startIndex, middleIndex, endIndex,temp);
        }
//        merge(nums, startIndex, middleIndex, endIndex,temp);
    }

    private static void merge(List<Integer> nums, int startIndex, int middleIndex, int endIndex, List<Integer> temp){
        int first = startIndex;
        int second = middleIndex;
        int place = startIndex;
        while(first<middleIndex&&second<endIndex){
            if(nums.get(first)<nums.get(second)){
                temp.set(place, nums.get(first));
                ++first;
            }
            else{
                temp.set(place, nums.get(second));
                ++second;
            }
            ++place;
        }
        while(first<middleIndex){
            temp.set(place, nums.get(first));
            ++first;
            ++place;
        }
        while(second<endIndex){
            temp.set(place, nums.get(second));
            ++second;
            ++place;
        }
        for (int i = startIndex; i < endIndex; i++) {
            nums.set(i, temp.get(i));
        }
    }
}
