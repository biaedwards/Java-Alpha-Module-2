package LeetCode.LinearDataStructures.LinkedList;

public class RemoveDuplicatesFromSortedListII {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode answerStart = new ListNode(0);
            ListNode answer = answerStart;
            ListNode go = head;
            while (go != null) {
                if (go.next == null || go.val != go.next.val) {
                    answer.next = new ListNode(go.val);
                    answer = answer.next;
                    go = go.next;
                    continue;
                }
                int rep = go.val;
                while (go != null && go.val == rep) {
                    go = go.next;
                }
            }
            return answerStart.next;
        }
    }
}
