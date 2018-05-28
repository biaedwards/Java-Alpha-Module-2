package LeetCode.LinearDataStructures;

public class ReverseList {
    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        list.next.next.next.next.next = new ListNode(6);

        ListNode result = new Solution().reverseList(list);
        while(result!=null){
            System.out.println(result.val);
            result=result.next;
        }
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
           ListNode node = null;
           while(head!=null){
               ListNode temp = new ListNode(head.val);
               temp.next = node;
               node = temp;
               head = head.next;
           }
           return node;
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
