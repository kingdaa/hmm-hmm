/*
 Problem Statement

 Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.

 Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

 Input

 The first line contains the number of test cases T. T test cases follow:

 The first line of each test case contains a number N. The next line contains N integers, denoting the predicted price of WOT shares for the next N days.

 Output

 Output T lines, containing the maximum profit which can be obtained for the corresponding test case.

 Constraints

 1 <= T <= 10
 1 <= N <= 50000

 All share prices are between 1 and 100000

 Sample Input

 3
 3
 5 3 2
 3
 1 2 100
 4
 1 3 1 2
 Sample Output

 0
 197
 3
 Explanation

 For the first case, you cannot obtain any profit because the share price never rises.
 For the second case, you can buy one share on the first two days, and sell both of them on the third day.
 For the third case, you can buy one share on day 1, sell one on day 2, buy one share on day 3, and sell one share on day 4.
 */

import java.util.Arrays;

public class StockMaximize {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;
        boolean[] sellOn = new boolean[n]; // an array to keep track on days on which we can sell
        Arrays.fill(sellOn, false); // fill with false
        long localMax = Long.MIN_VALUE; // initialize the local max with minimum value possible

        // traverse the array from back and if there mark selling days for local maximum
        for (int i = n - 1; i >= 0; i--) {
            if (localMax < prices[i]) { // found better local maximum
                localMax = prices[i]; // update local maximum
                sellOn[i] = true; // sell all existing shares on this day
                // If the price rises to highest peek first, and drop to lowest, then rises to second peek (lower than first)
                // Note that this will mark sellOn[i] = true for i between first peek to the lowest after where price > second peek
                // But this is fine, because we will sell all at first peek, and since sellOn is true for these days, we won't buy any stock in these days
                // This makes sure that we do not have any loss from buying in this range and take loss (because in second peak price is not that high any more)
            }
        }

        int currentCost = 0, currentStocks = 0, profit = 0;
        for (int i = 0; i < n; i++) {
            if (sellOn[i] == true) {
                profit += (currentStocks * prices[i] - currentCost);
                currentStocks = 0;
                currentCost = 0;
            } else {
                currentCost += prices[i];
                currentStocks++;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        StockMaximize sol = new StockMaximize();
        int[] prices1 = {5, 3, 2};
        int[] prices2 = {1, 2, 100};
        int[] prices3 = {1, 3, 1, 2};
        int[] prices4 = {3, 2, 2, 100}; // 293
        System.out.println(sol.maxProfit(prices1));
        System.out.println(sol.maxProfit(prices2));
        System.out.println(sol.maxProfit(prices3));
        System.out.println(sol.maxProfit(prices4));
    }
}
