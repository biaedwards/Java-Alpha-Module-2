package LeetCode.LinearDataStructures;

public class RotateList {
    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        list.next.next.next.next.next = new ListNode(6);

        ListNode result = new Solution().rotateRight(list, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            ListNode temp = head;
            int count = 0;
            while (temp!=null && temp.next != null) {
                count++;
                temp = temp.next;
            }
            count++;
            k %= count;
            if(temp==null||k==0) return head;
            count -= k;
            ListNode rotation = new ListNode(0);
            ListNode add = rotation;
            for (int i = 0; i < count; i++) {
                rotation.next = new ListNode(head.val);
                if(i==0) add = rotation.next;
                head = head.next;
                rotation = rotation.next;
            }
            temp.next = add;
            return head;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
