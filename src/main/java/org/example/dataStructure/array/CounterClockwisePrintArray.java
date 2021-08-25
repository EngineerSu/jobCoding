package org.example.dataStructure.array;

/**
 * @Author: suchang
 * @Description: 给一个矩阵(二维数组)，要求逆时针从外到内转圈打印这个矩阵
 * @Date: 9:21 2021/8/25
 */
public class CounterClockwisePrintArray {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        solve(matrix);
    }

    /**
     * 整体思路：
     * 将矩阵看作由一个个 口字型 边框组成，对于边框，只要只要知道左上和右下的坐标，就可以逆时针将其打印出来
     * 每打印完一个边框，将其左上的坐标右下移，右下的坐标左上移，继续打印下一个边框
     * 直到左上坐标与右下坐标“错过”
     */
    private static void solve(int[][] matrix) {
        int row = matrix.length;
        int clo = matrix[0].length;
        print(matrix, 0, 0, --row, --clo);
    }

    /**
     * 打印完当前边框，则递归打印下一个边框
     */
    private static void print(int[][] matrix, int leftUpx, int leftUpY, int rightDownX, int rightDownY) {
        // 递归停止
        if (leftUpx > rightDownX || leftUpY > rightDownY) {
            return;
        }

        // 特殊情况：一横
        if (leftUpY == rightDownY) {
            while (leftUpx <= rightDownX) {
                System.out.println(matrix[leftUpx++][leftUpY] + " ");
            }
            return;
        }

        // 特殊情况：一竖
        if (leftUpx == rightDownX) {
            while (leftUpY <= rightDownY) {
                System.out.println(matrix[leftUpx][leftUpY++] + " ");
            }
            return;
        }

        // 一般情况，打印顺序：左竖、下横、右竖、上横
        int x = leftUpx;
        int y = leftUpY;
        while (x < rightDownX) {
            System.out.println(matrix[x++][y] + " ");
        }
        while (y < rightDownY) {
            System.out.println(matrix[x][y++] + " ");
        }
        while (x > 0) {
            System.out.println(matrix[x--][y] + " ");
        }
        while (y > 0) {
            System.out.println(matrix[x][y--] + " ");
        }

        // 递归打印下一个边框
        print(matrix, ++leftUpx, ++leftUpY, --rightDownX, --rightDownY);
    }
}
