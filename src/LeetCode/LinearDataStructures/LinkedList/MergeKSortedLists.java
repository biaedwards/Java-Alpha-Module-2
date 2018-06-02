package LeetCode.LinearDataStructures.LinkedList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
            for(ListNode ln:lists){
                if(ln!=null) pq.offer(ln);
            }
            ListNode result = new ListNode(0);
            ListNode go = result;
            while(!pq.isEmpty()){
                ListNode temp = pq.poll();
                go.next = new ListNode(temp.val);
                go = go.next;
                if(temp.next!=null){
                    temp = temp.next;
                    pq.offer(temp);
                }
            }
            return result.next;
        }
    }
}
