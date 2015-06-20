/*
N queen 变种
题目巨长输入格式是
1
2
就是说 第1行第1个是queen 第2行第2个是queen，并保证输入的数字不重复，这样可以得出一个结论：同一行 同一列不会出现2个queen。
题目要求是求出 对于每个Queen， 最大的威胁次数，威胁指只要一个queen所能移动的范围内有别的queen就算威胁 P.S.同一方向上有2个queen威胁你 只算最近的那个。
由于同一行 同一列不会出现2个queen。（由于输入限制）所以只要考虑对角线 和逆对角线。
举个例子： 棋盘是：
100           ---- 1号 queen
010           ---- 2号 queen
001           ---- 3号 queen
1号和3号queen的受威胁次数都是1， 2号是2（被1和3） 所以答案是2.
这题我是建了2个hash表 一个是正对角线一个逆对角线来表示对角线上是否有别的queen.
从row 0 开始到 最后行， 对于输入的 column位置 先判定 hash表是否有别的Queen没的话就set 1 继续下一行
有的话就threats[row]++ 然后向那个方向追踪是谁给你的威胁，把那个点的threat也++ 就好了。
 */

import java.util.HashMap;
import java.util.Map;

public class NQueenThreats {
    public int solveNQueen(int[] queens) {
        int n = queens.length;
        if (n < 2) return 0;
        // up diag use key (i+j), down diag (i-j)
        // only store value of last row index that added from input
        Map<Integer, Integer> upDiag = new HashMap<>();
        Map<Integer, Integer> downDiag = new HashMap<>();
        int[] threats = new int[n];
        for (int i = 0; i < n; i++) {
            int upIndex = i + 1 + queens[i];
            int downIndex = i + 1 - queens[i];
            if (upDiag.containsKey(upIndex)) {
                threats[upDiag.get(upIndex)]++;
                threats[i]++;
            }
            upDiag.put(upIndex, i);
            if (downDiag.containsKey(downIndex)) {
                threats[downDiag.get(downIndex)]++;
                threats[i]++;
            }
            downDiag.put(downIndex, i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) max = Math.max(max, threats[i]);
        return max;
    }

    public static void main(String[] args) {
        int[] queens = {1, 2, 3, 4, 5, 6, 7}; //2
        int[] queens2 = {6, 5, 2, 3, 4, 1}; //4
        NQueenThreats sol = new NQueenThreats();
        System.out.println(sol.solveNQueen(queens));
        System.out.println(sol.solveNQueen(queens2));
    }
}
