package org.example.dataStructure.tree;

import org.example.pojo.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: suchang
 * @Description: 二叉树的层次遍历
 * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=190&&tqId=35337&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 思路：用队列（先进先出的特点）存储二叉树的左右子节点，依次遍历队列即可。
 * 为了辨别当前遍历到第几层，需要引入两个 count 变量，记录当前层剩余节点数，和下一层需要遍历的节点数
 * @Date: 19:36 2021/8/25
 */
public class TreeLayerOrder {

    public ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 当前层还有多少个节点
        int countCurLayer = 1;
        // 下一层存储了多少个节点
        int countNextLayer = 0;
        ArrayList<Integer> layer = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            layer.add(cur.val);
            countCurLayer--;
            if (cur.left != null) {
                queue.offer(cur.left);
                countNextLayer++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                countNextLayer++;
            }
            if (countCurLayer == 0) {
                // 当前层已经遍历完了，需要准备遍历下一层
                countCurLayer = countNextLayer;
                countNextLayer = 0;
                res.add(layer);
                layer = new ArrayList<Integer>();
            }
        }
        return res;
    }
}
