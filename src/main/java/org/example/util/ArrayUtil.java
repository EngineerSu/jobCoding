package org.example.util;

/**
 * @Author: suchang
 * @Description:
 * @Date: 14:30 2021/8/25
 */
public class ArrayUtil {
    public static void swap(int[] arr, int p1, int p2) {
        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }
}
