package org.example;


import org.example.dataStructure.list.LRUCache;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LinkedList<Integer> linkedList = new LinkedList<>();
        PriorityQueue<Integer> smallHeap  = new PriorityQueue<Integer>((o1, o2) -> o2 -o1);
        smallHeap.offer(1);
        smallHeap.offer(2);
        smallHeap.offer(3);
        System.out.println(smallHeap.poll());
        System.out.println();
    }
}
