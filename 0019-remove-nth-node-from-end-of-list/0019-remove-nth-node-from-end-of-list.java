/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return head;
        }
        //single node,first node
        int size=count(head);
        if(n==size){
            head=head.next;
            return head;
        }
        ListNode temp=head;
        int i=1;
        while(i<size-n){
            temp=temp.next;
            i++;
        }
        temp.next=temp.next.next;
        return head;
    }
    int count(ListNode head){
        int count=0;
        ListNode temp=head;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }
}