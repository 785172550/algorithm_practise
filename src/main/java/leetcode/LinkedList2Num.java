package leetcode;

public class LinkedList2Num {


  /*
  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  Output: 7 -> 0 -> 8
  Explanation: 342 + 465 = 807.
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    ListNode cursor = new ListNode(0);
    ListNode head = cursor;
    int val = 0;
    int offset = 0;
    while (l1 != null || l2 != null) {
      if (l1 != null && l2 != null) {
        val = l1.val + l2.val + offset;
        cursor.next = new ListNode(val % 10);
        offset = val / 10;
        l1 = l1.next;
        l2 = l2.next;
      } else if (l1 != null) {
        val = l1.val + offset;
        cursor.next = new ListNode(val % 10);
        offset = val / 10;
        l1 = l1.next;
      } else {
        val = l2.val + offset;
        cursor.next = new ListNode(val % 10);
        offset = val / 10;
        l2 = l2.next;
      }
      cursor = cursor.next;
    }
    if (offset == 1) {
      cursor.next = new ListNode(1);
    }
    return head.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next = new ListNode(7);

    LinkedList2Num main = new LinkedList2Num();
    main.addTwoNumbers(l1, l2);
  }
}

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

