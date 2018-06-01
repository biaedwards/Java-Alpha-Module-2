package LeetCode.LinearDataStructures.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseNodesInKGroup {
    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    private static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    private static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode head = stringToListNode(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            ListNode ret = new Solution().reverseKGroup(head, k);

            String out = listNodeToString(ret);

            System.out.print(out);
        }
    }
    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            return recurse(head, head, 1, k);
        }

        private ListNode recurse(ListNode head, ListNode go, int counter, int k){
            if(go==null) return head;
            if(counter==k){
                ListNode temp = go.next;
                go.next = null;
                ListNode[] tempAr = reverseList(head);
                head = tempAr[0];
                tempAr[1].next = recurse(temp, temp, 1, k);
                return head;
            }
            else return recurse(head, go.next, counter+1, k);
        }

        private ListNode[] reverseList(ListNode head) {
            ListNode node = null;
            ListNode tail = null;
            while(head!=null){
                ListNode temp = new ListNode(head.val);
                if(node==null) tail = temp;
                temp.next = node;
                node = temp;
                head = head.next;
            }
            return new ListNode[]{node, tail};
        }
    }
}
