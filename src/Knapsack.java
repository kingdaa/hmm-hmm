import java.util.LinkedList;
import java.util.List;

/**
 * Created by King on 4/12/15.
 */
public class Knapsack {
    public List<Integer> knapSackTopDown(int[] values, int[] weights, int sack) {
        int n = values.length;
        int m = weights.length;
        List<Integer> res = new LinkedList<Integer>();
        if (n != m) return res;
        int[][] dp = new int[n + 1][sack + 1];
        // Initialize dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sack; j++) {
                dp[i][j] = -1;
            }
        }
        knapSackTopDownHelper(dp, values, weights, sack, n);
        reconstructSolution(res, dp, values, weights, sack);
        return res;
    }

    private int knapSackTopDownHelper(int[][] dp, int[] values, int[] weights, int sack, int n) {
        if (n == 0) return 0;
        if (dp[n][sack] != -1) return dp[n][sack];
        int result;
        if (weights[n - 1] > sack) result = knapSackTopDownHelper(dp, values, weights, sack, n - 1);
        else
            result = Math.max(knapSackTopDownHelper(dp, values, weights, sack, n - 1), values[n - 1] + knapSackTopDownHelper(dp, values, weights, sack - weights[n - 1], n - 1));
        dp[n][sack] = result;
        return result;
    }

    public List<Integer> knapSackBottomUp(int[] values, int[] weights, int sack) {
        int n = values.length;
        int m = weights.length;
        List<Integer> res = new LinkedList<Integer>();
        if (n != m) return res;
        int[][] dp = new int[n + 1][sack + 1];
        // Initialize dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sack; j++) {
                dp[i][j] = -1;
            }
        }
        // i - current item
        // j - current remaining sack
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sack; j++) {
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        reconstructSolution(res, dp, values, weights, sack);
        return res;
    }

    private void reconstructSolution(List<Integer> res, int[][] dp, int[] values, int[] weights, int sack) {
        int curI = values.length;
        int curS = sack;
        while (curI > 0 && curS > 0) {
            // Did not use current value
            if (dp[curI][curS] == dp[curI - 1][curS]) {
                curI--;
            } else {
                curS -= weights[curI - 1];
                res.add(0, values[curI - 1]);
                curI--;
            }
        }
    }

    public static void main(String[] args) {
        int[] values = {7, 9, 5, 12, 14, 6, 12};
        int[] weights = {3, 4, 2, 6, 7, 3, 5};
        int sack = 15;
        Knapsack sol = new Knapsack();
        System.out.println(sol.knapSackTopDown(values, weights, sack));
        System.out.println(sol.knapSackBottomUp(values, weights, sack));
    }
}
