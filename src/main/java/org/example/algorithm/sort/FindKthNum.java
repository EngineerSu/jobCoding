package org.example.algorithm.sort;

import static org.example.util.ArrayUtil.swap;

/**
 * @Author: suchang
 * @Description: 寻找第 k 大的数
 * https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=190&&tqId=35209&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 思路：仍然是用快排的思想，partition 将数组分成小于区、等于区和大于区，仅当第 k 大的数索引落在等于区时，才是正确结果；其他情况都要继续递归寻找
 * @Date: 22:16 2021/8/25
 */
public class FindKthNum {

    public int findKth(int[] a, int n, int K) {
        // write code here
        return solve(a, 0, n-1, K);
    }

    public int solve(int[] a, int left, int right, int k) {
        if (left >= right) {
            return a[right];
        }

        int randIndex = left + (int)(Math.random() * (right - left + 1));
        int rand = a[randIndex];
        int small = left  - 1;
        int equal = left;
        int big = right + 1;
        while (equal < big) {
            if (a[equal] < rand) {
                swap(a, equal++, ++small);
            } else if (a[equal] == rand) {
                equal++;
            } else {
                swap(a, equal, --big);
            }
        }

        int equalBegin = small + 1;
        int equalEnd = big - 1;
        int kthIndex = right - k + 1;
        if (equalBegin <= kthIndex && kthIndex <= equalEnd) {
            // 找到了第 k 大的数
            return rand;
        } else if (kthIndex > equalEnd) {
            // 缩小范围
            return solve(a, equalEnd + 1, right, k);
        } else {
            // 结果在小于区，那么等于区和大于区的内容都可以排除
            return solve(a, left, equalBegin - 1, k - (right - equalBegin + 1));
        }
    }

}
