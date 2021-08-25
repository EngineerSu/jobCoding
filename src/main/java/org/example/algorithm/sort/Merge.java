package org.example.algorithm.sort;

import org.example.util.ArrayBuilder;
import org.example.util.Printer;

/**
 * @Author: suchang
 * @Description: 归并排序算法
 * 首先将数组分成两半，两半分别递归排序好后，再将排序好的两部分合并
 * @Date: 13:51 2021/8/25
 */
public class Merge {
    public static void main(String[] args) {
        Printer.printArray(ArrayBuilder.buildDisorderArray());
        Printer.printArray(solve(ArrayBuilder.buildDisorderArray()));
    }

    public static int[] solve(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        return merge(arr, 0, arr.length - 1);
    }

    public static int[] merge(int[] arr, int left, int right) {
        if (left >= right) {
            return arr;
        }

        int mid = (left +  right) / 2;
        merge(arr, left, mid);
        merge(arr, mid + 1, right);
        return merge(arr, left, mid, right);
    }

    public static int[] merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            temp[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        index = 0;
        while (index < temp.length) {
            arr[left++] = temp[index++];
        }
        return arr;
    }
}
