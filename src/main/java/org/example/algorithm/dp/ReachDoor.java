package org.example.algorithm.dp;

import java.util.Scanner;

/**
 * @Author: suchang
 * @Description: 到达第 n + 1 个门需要多少步
 * https://www.nowcoder.com/practice/58b04ed2865f4ff4921290f1bd4ee486?tpId=137&&tqId=33878&rp=1&ru=/ta/exam-bytedance&qru=/ta/exam-bytedance/question-ranking
 * 思路：有几个重要的推论
 * 1.当第一次到达 i 门时，i 门前面所有门都会是到达偶数次（反证：如果不是偶数次，那么就不会往前走）
 *   通过这个推论，设 dp[i] 表示第一次到达 i 门所需要的步骤，则 dp[i] = dp[i-1] + 第二次到达 i-1 门 + 1
 * 2.第一次到了 i-1 门后，会走一步到 p[i-1] 门（此时该门到达次数为奇数次），接着从 p[i-1] 门再次走一定步数到 i-1 门
 *   由于除了 p[i-1] 门， i-1 门前面所有门的次数仍为偶数次，所以从 p[i-1] 门再次走到 i-1 门的步数与第一次从 p[i-1] 门走到 i-1 门的步数是相等的
 *   于是 第二次到达 i-1 门步数 = dp[i-1] - dp[p[i-1]] + 1
 * 综上，得到状态转移方程：dp[i] = 2dp[i-1] - dp[p[i-1]] + 2
 *
 * 需要注意的是：
 * 1.状态转移方程的值可能为很大，所以要用 long 数组
 * 2.取模输出时，有两点调整：1.每个 dp 求值时都取模；2.最终展示时，如果 dp 是正数则正常展示，负数则加上模值
 * @Date: 8:48 2021/8/26
 */
public class ReachDoor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            long mod = 1000000007L;
            int n = in.nextInt();
            int[] p = new int[n + 1];
            for (int i = 0; i < n; i++) {
                p[i + 1] = in.nextInt();
            }
            long[] dp = new long[n + 2];
            dp[1] = 0;
            for (int i = 2; i < n+2; i++) {
                dp[i] = (2 * dp[i-1] - dp[p[i-1]] + 2)  % mod;
            }
            System.out.println(dp[n + 1] < 0 ? dp[n + 1] + mod : dp[n + 1]);
        }
    }
}
