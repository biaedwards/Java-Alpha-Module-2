package LeetCode.LinearDataStructures;

public class RemoveDuplicates {
    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(1);
        list.next.next = new ListNode(2);
        list.next.next.next = new ListNode(3);
        list.next.next.next.next = new ListNode(3);
        list.next.next.next.next.next = new ListNode(3);

        ListNode result = new Solution().deleteDuplicates(list);
        while(result!=null){
            System.out.println(result.val);
            result=result.next;
        }
    }

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode node = head;
            while(node!=null&&node.next!=null){
                if(node.val==node.next.val){
                    node.next = node.next.next;
                }
                else{
                    node = node.next;
                }
            }
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
