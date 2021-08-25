package org.example.algorithm.sort;

import java.util.ArrayList;

import static org.example.util.ArrayUtil.swap;

/**
 * @Author: suchang
 * @Description: 求数组最小的 k 个数
 * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=190&&tqId=35976&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 思路：用快排的 partition，将数组分成三个区：小于区、等于区、大于区，当第 k 小的数对应的索引落在等于区内时，k 个数就是确定的；
 * 落在小于区时，k 个数是完全不确定的，需要在小于区继续寻找；
 * 落在大于区时，小于区和等于区的数都可以确定是 k 个数范围内，但是还有一部分数没有确定，需要在大于区继续寻找
 * @Date: 18:56 2021/8/25
 */
public class SmallestKNum {

    public static void main(String[] args) {
        int[] input = new int[]{4, 5, 1, 6, 2, 7, 2, 8};
        ArrayList<Integer> res = solve(input, 2);
        System.out.println(res);
    }

    public static ArrayList<Integer> solve(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (k == 0) {
            return res;
        }
        if (k >= input.length) {
            for (int i : input) {
                res.add(i);
            }
            return res;
        }
        res.addAll(partition(input, 0, input.length - 1, k));
        return res;
    }

    private static ArrayList<Integer> partition(int[] input, int left, int right, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (k <= 0) {
            return res;
        }
        if (left > right) {
            return res;
        }
        if (left == right) {
            res.add(input[left]);
            return res;
        }
        int randIndex = left + (int) (Math.random() * (right - left + 1));
        int rand = input[randIndex];

        int small = left - 1;
        int equal = left;
        int big = right + 1;
        while (equal < big) {
            if (input[equal] < rand) {
                swap(input, equal++, ++small);
            } else if (input[equal] == rand) {
                equal++;
            } else {
                swap(input, equal, --big);
            }
        }

        int equalBegin = small + 1;
        int equalEnd = big - 1;
        int kIndex = left + k;
        // k 范围在等于区内，则可以确定最小的 k 个数
        if (equalBegin <= kIndex && kIndex <= equalEnd) {
            int i = left;
            while (k > 0) {
                res.add(input[i++]);
                k--;
            }
            return res;
        } else if (kIndex < equalBegin) {
            // k 在等于区左侧，则需要去等于区左侧寻找
            return partition(input, left, equalBegin - 1, k);
        } else {
            //  k 在等于区右侧，小于区、等于区都是 k 个数范围内，还需要去等于区右侧寻找剩下的最小数
            int i = left;
            while (i < big) {
                res.add(input[i++]);
            }
            res.addAll(partition(input, big, right, k - (big - left)));
            return res;
        }
    }
}
