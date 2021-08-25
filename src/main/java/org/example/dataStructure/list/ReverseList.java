package org.example.dataStructure.list;

import org.example.pojo.ListNode;
import org.example.util.ListNodeBuilder;
import org.example.util.Printer;

/**
 * @Author: suchang
 * @Description: 输入一个链表，反转链表后，输出新链表的表头。
 * @Date: 10:10 2021/8/25
 */
public class ReverseList {

    public static void main(String[] args) {
        Printer.printListNode(ListNodeBuilder.buildCommonListNode());
        Printer.printListNode(solve(ListNodeBuilder.buildCommonListNode()));
        Printer.printListNode(solveByRecursion(ListNodeBuilder.buildCommonListNode()));
    }

    /**
     * 递归解法：先返回当前节点的下一个节点，然后再处理反转后的子链表与当前节点的关系
     */
    public static ListNode solveByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 将 next 所代表的链表拿去反转，得到的是 next 所代表的链表的反转结果
        ListNode newHead = solveByRecursion(head.next);

        // 处理 next 所代表的链表与当前节点的关系
        // 1.next 节点作为反转子链表的最后一个节点，它的 next 应该指向当前节点
        head.next.next = head;
        // 2.当前节点的 next 应该指向 null（因为当前节点是作为反转链表的最后一个节点）
        head.next = null;

        // 处理不影响反转链表的头节点
        return newHead;
    }

    /**
     * 思路：遍历链表，记录 pre 与 next，对于每一个元素，它需要将 next 设置为 pre，设置完后即可更新
     */
    public static ListNode solve(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    
}
