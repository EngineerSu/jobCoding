package org.example.dataStructure.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: suchang
 * @Description: https://www.nowcoder.com/practice/e3769a5f49894d49b871c09cadd13a61?tpId=188&tags=&title=&difficulty=0&judgeStatus=0&rp=1
 * 设计一个 LRU：用双向链表将 Node 链接起来，按要求在 put/get 时对链表头尾进行操作，LRU 对象中使用 map 可以优化时间复杂度
 * @Date: 15:46 2021/8/25
 */
public class LRUCache {

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        // write code here
        LRU lru = new LRU(k);
        int size = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 2) {
                size++;
            }
        }
        int[] res = new int[size];
        int index = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                lru.put(operators[i][1], operators[i][2]);
            } else if (operators[i][0] == 2) {
                int value = lru.get(operators[i][1]);
                res[index++] = value;
            }
        }
        return res;
    }

    public static class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode pre;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class LRU {
        /**
         * 用 map 可以将遍历的判断复杂度由 O(N) 将为 O(logN)
         */
        Map<Integer, ListNode> map;
        ListNode tail;
        int size;
        int capacity;

        public LRU(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
        }

        private void moveListNodeToTail(ListNode cur) {
            if (this.tail.key == cur.key) {
                // 不需要移动
                return;
            }
            // 找到了指定值，需要将 cur 作为新的 tail
            ListNode pre = cur.pre;
            ListNode next = cur.next;
            if (pre != null) {
                pre.next = next;
            }
            if (next != null) {
                next.pre = pre;
            }
            this.tail.next = cur;
            cur.pre = this.tail;
            cur.next = null;
            this.tail = cur;
        }

        public void put(int key, int value) {
            ListNode listnode = new ListNode(key, value);
            if (size == 0) {
                // 第一次插入数据
                this.tail = listnode;
                size++;
                return;
            }

            // 非第一次插入
            // 首先判断原缓存中是否有值
            ListNode cur = this.tail;
            while (cur != null) {
                if (cur.key == key) {
                    cur.value = value;
                    moveListNodeToTail(cur);
                    return;
                }
                cur = cur.pre;
            }
            // 需要将最新插入的数据放置尾部
            this.tail.next = listnode;
            listnode.pre = this.tail;
            this.tail = listnode;
            // 判断是否超过容量，若是则移除头部
            if (size + 1 > capacity) {
                cur = this.tail;
                ListNode next = cur.next;
                while (cur != null) {
                    next = cur.next;
                    cur = cur.pre;
                }
                next.pre = null;
            }
            size++;
        }

        public int get(int key) {
            if (size == 0) {
                return -1;
            }

            ListNode cur = this.tail;
            while (cur != null) {
                if (cur.key == key) {
                    // 找到了指定值，需要将 cur 作为新的 tail
                    moveListNodeToTail(cur);
                    return cur.value;
                }
                cur = cur.pre;
            }
            return -1;
        }
    }
}
