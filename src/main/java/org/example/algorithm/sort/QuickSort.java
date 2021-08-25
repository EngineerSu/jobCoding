package org.example.algorithm.sort;

import org.example.util.ArrayBuilder;
import org.example.util.ArrayUtil;
import org.example.util.Printer;

/**
 * @Author: suchang
 * @Description: 快排
 * 先在数组中找一个随机位置，然后根据这个位置的数，将数据分成三部分（small、equal、big）
 * small 位置的数一定都小于该数，equal 等于，big 大于
 * 分完后，equal 这部分就相当于排序确定了，再递归地去排 small 和 big 这两部分即可
 * @Date: 14:26 2021/8/25
 */
public class QuickSort {
    public static void main(String[] args) {
        Printer.printArray(ArrayBuilder.buildDisorderArray());
        Printer.printArray(solve(ArrayBuilder.buildDisorderArray()));
    }

    public static int[] solve(int[] arr) {
        // write code here
        if (arr == null || arr.length < 2) {
            return arr;
        }

        return quickSort(arr, 0, arr.length - 1);
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return arr;
        }
        // 寻找一个随机位置
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        int randomVal = arr[randomIndex];
        // partition 过程：将小于随机数和大于随机数分成两组
        // small 和 big 两个初始位置一定要在当前区间外
        int small = left - 1;
        int equal = left;
        int big = right + 1;
        while (equal < big) {
            if (arr[equal] < randomVal) {
                ArrayUtil.swap(arr, equal++, ++small);
            } else if (arr[equal] == randomVal) {
                equal++;
            } else {
                ArrayUtil.swap(arr, equal, --big);
            }
        }
        quickSort(arr, left, small);
        quickSort(arr, big, right);
        return arr;
    }


}
