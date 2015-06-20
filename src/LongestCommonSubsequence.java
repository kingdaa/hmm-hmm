/**
 * Created by King on 4/12/15.
 */
public class LongestCommonSubsequence {
    public String lcsDPTopDown(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        //Initialize array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = -1;
            }
        }
        lcsTopDownHelper(s1, s2, dp, n, m);
        return reconstructString(s1, dp, n, m);
    }

    private int lcsTopDownHelper(String s1, String s2, int[][] dp, int n, int m) {
        if (n == 0 || m == 0) return 0;
        if (dp[n][m] != -1) return dp[n][m];
        int result;
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) result = 1 + lcsTopDownHelper(s1, s2, dp, n - 1, m - 1);
        else result = Math.max(lcsTopDownHelper(s1, s2, dp, n - 1, m), lcsTopDownHelper(s1, s2, dp, n, m - 1));
        dp[n][m] = result;
        return result;
    }

    public String lcsDPBottomUp(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1))
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                else
                    dp[i][j] = dp[i - 1][j - 1] + 1;
            }

        }
        return reconstructString(s1, dp, n, m);
    }

    private String reconstructString(String s1, int[][] dp, int n, int m) {
        StringBuilder sb = new StringBuilder();
        int curI = n, curJ = m;
        while (curI > 0 && curJ > 0) {
            if (dp[curI][curJ] == dp[curI - 1][curJ]) {
                curI -= 1;
                continue;
            }
            if (dp[curI][curJ] == dp[curI][curJ - 1]) {
                curJ -= 1;
                continue;
            }
            if (dp[curI][curJ] == dp[curI - 1][curJ - 1] + 1) {
                sb.append(s1.charAt(curI - 1));
                curI -= 1;
                curJ -= 1;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        LongestCommonSubsequence sol = new LongestCommonSubsequence();
        String s1 = "BACBAD";
        String s2 = "ABAZDC";
        System.out.println(sol.lcsDPTopDown(s1, s2));
        System.out.println(sol.lcsDPBottomUp(s1, s2));
    }

}
