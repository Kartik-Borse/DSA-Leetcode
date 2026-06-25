/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode curr=node;
        ListNode temp=node.next;
        while(temp!=null){
            curr.val=temp.val;
            if(temp.next==null){
                curr.next=null;
                return;
            }
            curr=temp;
            temp=temp.next;
        }
    }
}