package leetcode;

public class BestTimeToBuySellStock {

    //http://hanslen.me/2017/10/15/Best-Time-to-Buy-and-Sell-Stock-series-with-Dynamic-Programming-in-Java/

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    //ßhttps://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process

    /*
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
    public int maxProfit(int[] prices) {
        int minBuyingPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - minBuyingPrice);
            minBuyingPrice = Math.min(minBuyingPrice, prices[i]);
        }
        return profit;
    }

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
//
/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
     */

    public static int maxProfit2(int[] prices) {
        int bp, sp, profit=0, i = 0;
        int len = prices.length;
        while(i < len - 1){
            bp = prices[i];
            while (i < len - 1 && prices[i+1] < prices[i]){
                bp = prices[++i];
            }
            sp = bp;

            while (i < len - 1 && prices[i+1] > prices[i]){
                sp = prices[++i];
            }
            profit+=sp-bp;
            i++;
        }
        return profit;
    }

    //    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    public static int maxProfit3(int[] prices) {
        int bp = prices[0];
        int sp = prices[0];
        int len = prices.length;

        int i = 1;
        while(i < len){

        }
        return 1;
    }
}
