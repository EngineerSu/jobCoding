package org.example.util;

import org.example.pojo.ListNode;

/**
 * @Author: suchang
 * @Description:
 * @Date: 13:55 2021/8/25
 */
public class ListNodeBuilder {
    public static ListNode buildCommonListNode() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next =  new ListNode(3);
        return head;
    }
}
