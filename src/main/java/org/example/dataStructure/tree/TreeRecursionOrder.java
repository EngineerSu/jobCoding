package org.example.dataStructure.tree;

import org.example.pojo.TreeNode;

/**
 * @Author: suchang
 * @Description: 二叉树的各种遍历
 * https://www.nowcoder.com/practice/a9fec6c46a684ad5a3abd4e365a9d362?tpId=190&&tqId=35221&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 递归方法非常简单，需要注意的是传入的 index 和传出 index 的区别
 * 前序、中序、后序是由递归中，当前节点的打印顺序决定：当前节点优先打印就是前序，第二个打印就是中序，最后打印就是后续
 * @Date: 17:12 2021/8/25
 */
public class TreeRecursionOrder {
    public int[][] threeOrders (TreeNode root) {
        // 二叉树节点的个数
        int size = getSize(root);
        int[][] res = new int[3][size];
        res[0] = new int[size];
        res[1] = new int[size];
        res[2] = new int[size];
        preOrder(root, res[0], 0);
        midOrder(root, res[1], 0);
        lastOrder(root, res[2], 0);
        return res;
    }

    private int lastOrder(TreeNode root, int[] res, int index) {
        if (root == null) {
            return index;
        }
        index = lastOrder(root.left, res, index);
        index = lastOrder(root.right, res, index);
        res[index++] = root.val;
        return index;
    }

    private int midOrder(TreeNode root, int[] res, int index) {
        if (root == null) {
            return index;
        }
        index = midOrder(root.left, res, index);
        res[index++] = root.val;
        return midOrder(root.right, res, index);
    }

    private int preOrder(TreeNode root, int[] res, int index) {
        if (root == null) {
            return index;
        }
        res[index++] = root.val;
        index = preOrder(root.left, res, index);
        return preOrder(root.right, res, index);
    }

    private int getSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getSize(root.left) + getSize(root.right);
    }
}
