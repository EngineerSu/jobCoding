package org.example.util;

import org.example.pojo.ListNode;

/**
 * @Author: suchang
 * @Description:
 * @Date: 13:57 2021/8/25
 */
public class Printer {

    public static void printListNode(ListNode head) {
        if (head == null) {
            System.out.println("ListNode is null !");
            return;
        }
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty !");
            return;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
