package leetcode;


public class MergeLinkedList {

     //* Definition for singly-linked list.
     static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

/*
You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example:
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode ptr1 = l1, ptr2 = l2, ptrTmp = null, ptrFinal = null;
         int carry = 0;
         while(ptr1!=null || ptr2 !=null){
             int val1 = ptr1 == null? 0 : ptr1.val;
             int val2 = ptr2 == null? 0 : ptr2.val;
             int addedVal = val1 + val2 + carry;
             carry = addedVal / 10;
             addedVal = addedVal % 10;
             if (ptrFinal == null) {
                 ptrFinal = new ListNode(addedVal);
                 ptrTmp = ptrFinal;
             }
             else{
                 ptrTmp.next = new ListNode(addedVal);
                 ptrTmp = ptrTmp.next;
             }
             ptr1 = ptr1 == null? null: ptr1.next;
             ptr2 = ptr2 == null? null: ptr2.next;
         }
         if (carry > 0) {
             ptrTmp.next = new ListNode(carry);
         }
        return ptrFinal;
    }


    public static ListNode reverse(ListNode l1){
        if (l1.next == null) return l1;
        ListNode reversed = reverse(l1.next);
        l1.next.next = l1;
        l1.next = null;
        return reversed;
    }

    public static ListNode reverseItr(ListNode l1){
        if (l1 == null || l1.next == null) return l1;
        ListNode ptr = null, ptrNxt = l1;
        while(ptrNxt!=null){
            ListNode tmp = ptrNxt.next;
            ptrNxt.next = ptr;
            ptr = ptrNxt;
            ptrNxt = tmp;
        }
        return ptr;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode( 3);
        l1.next.next.next = new ListNode( 5);
        print(l1);
        print(reverseItr(l1));

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode( 8);

        //print(addTwoNumbers(l1,l2));
        // print(mergeTwoListsRec(l1,l2));

        //print(l1);
        //print(l2);

    }

    private static void print(ListNode ptr) {
         while (ptr != null) {
             System.out.print(ptr.val + " > ");
             ptr = ptr.next;
         }
        System.out.println("NULL");
    }

    public static ListNode mergeTwoListsRec(ListNode l1, ListNode l2){
         if (l1 == null) return l2;
         if (l2 == null) return l1;
         if (l1.val <= l2.val) {
             l1.next = mergeTwoListsRec(l1.next, l2);
             return l1;
         } else {
             l2.next = mergeTwoListsRec(l1, l2.next);
             return l2;
         }

    }


    public static ListNode mergeTwoLists_NonMutative(ListNode l1, ListNode l2) {
         if (l1 == null) return l2;
         if (l2 == null) return l1;
         ListNode merged, ptr1 = l1, ptr2 = l2;

        if (ptr1.val < ptr2.val) {
            merged = new ListNode(ptr1.val);
            ptr1 = ptr1.next;
        } else {
            merged = new ListNode(ptr2.val);
            ptr2 = ptr2.next;
        }
        ListNode ptrMerged = merged;

        while(ptr1 != null && ptr2 != null){
            if (ptr1.val < ptr2.val) {
                ptrMerged.next = new ListNode(ptr1.val);
                ptr1 = ptr1.next;
            } else {
                ptrMerged.next = new ListNode(ptr2.val);
                ptr2 = ptr2.next;
            }
            ptrMerged = ptrMerged.next;
        }

        ListNode ptrRem = ptr1 == null? ptr2 : ptr1;
        while (ptrRem != null){
            ptrMerged.next = new ListNode(ptrRem.val);
            ptrRem = ptrRem.next;
            ptrMerged = ptrMerged.next;
        }
        return merged;
    }


    public static ListNode mergeTwoLists_Mutative(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode merged, ptr1 = l1, ptr2 = l2;

        if (l1.val < l2.val) {
            merged = l1;
            l1 = l1.next;
        } else {
            merged = l2;
            l2 = l2.next;
        }
        ListNode ptrMerged = merged;

        while(l1 != null && l2 != null){
            if (l1.val < l2.val) {
                ptrMerged.next = l1;
                l1 = l1.next;
            } else {
                ptrMerged.next = l2;
                l2 = l2.next;
            }
            ptrMerged = ptrMerged.next;
        }

        ListNode ptrRem = l1 == null? l2 : l1;
        while (ptrRem != null){
            ptrMerged.next = ptrRem;
            ptrRem = ptrRem.next;
            ptrMerged = ptrMerged.next;
        }
        return merged;
    }

}