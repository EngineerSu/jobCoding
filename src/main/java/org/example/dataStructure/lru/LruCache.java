package org.example.dataStructure.lru;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author: suchang
 * @Description:
 * @Date: 20:27 2021/8/26
 */
public class LruCache {
    private int len;
    private HashMap<Integer, Integer> cache = new HashMap<>((int) (len / 0.75));
    /**
     * first 存放最近使用过的数据，清理数据从 last 开始
     * 链表仅存 key，用于实现 lru，实际存取数还是由 cache 负责
     */
    private LinkedList<Integer> space = new LinkedList<>();

    public LruCache(int len) {
        this.len = len;
    }

    public Integer get(int k) {
        if (cache.containsKey(k)) {
            move2First(k);
        }

        return cache.getOrDefault(k, -1);
    }

    public void put(int k, int v) {
        if (cache.size() >= len && !cache.containsKey(k)) {
            // 仅当已存满且是加入新 key 时，才需要清楚元素
            Integer e = space.pollLast();
            cache.remove(e);
        }

        cache.put(k, v);
        move2First(k);
    }

    private void move2First(Integer k) {
        Iterator<Integer> it = space.iterator();
        while (it.hasNext()) {
            if (it.next().equals(k)) {
                it.remove();
                break;
            }
        }

        space.addFirst(k);
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(2);

        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        cache.get(2);

    }
}
