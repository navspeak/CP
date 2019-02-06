import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MaximumSubArray {
    //−1 2 4 −3 5 2 −5 2

    /*
    A straightforward way to solve the problem is to go through all possible subarrays,
    calculate the sum of values in each subarray and maintain the maximum sum.
     */
    public int solve_ncube(int[] array){
        int best = 0;
        final int length = array.length;
        for (int i = 0; i < length; i++) { // first pointer
            for (int j = i; j < length; j++) { // second pointer
                int sum = 0;
                for (int k = i; k <=j ; k++) {
                    sum+=array[k]; // sum in the window between first & second pointer
                }
                best = Math.max(sum, best);
            }

        }
        return best;
    }

    // Improve it by computing sum at the same time as we move second pointer
    // O(n^2)
    public int solve_nsquare(int[] array){
        int best = 0;
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += array[j]; // sum of subarray starting at i and ending at j < length
                best = Math.max(best, sum); // update best when sum is better than best
            }


        }
        return best;
    }
    // Kadane's algo
    // Subarray ending at k will have maximum sum = array[k]
    // or sum of maximum for subarray ending at k-1 + array[k]
    public int solve_lineartime(int[] array){
        //int best = 0; int sum = 0;
        int best = array[0]; int sum = array[0];
        // Consider input [-1]
        int endIndexOfSubArray = 0; // needed if we want to print all elements
        final int length = array.length;
        for (int i = 1; i < length; i++) {
            sum = Math.max(array[i], sum+array[i]);
            if (sum > best) {
                endIndexOfSubArray = i;
            }
            best = Math.max(sum, best);
        }
        int rem = best;
        for (int i = endIndexOfSubArray; i > 0 ; i--) {
            if (rem <= 0) break;
            System.out.print(array[i] + " ");
            rem-=array[i];

        }
        System.out.println("\n=====");
        return best;
    }


    public static void main(String[] args) {
        MaximumSubArray maximumSubArray = new MaximumSubArray();
        int[] array = {-1, 2, 4, -3, 5, 2, -5, 2};
        int ans1 = maximumSubArray.solve_lineartime(array);
        int ans2 = maximumSubArray.solve_nsquare(array);
        int ans3 = maximumSubArray.solve_ncube(array);
        System.out.printf("%d %d %d\n", ans1, ans2, ans3);
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public int maxProfit(int[] prices) {
        int minBuyingPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - minBuyingPrice);
            minBuyingPrice = Math.min(minBuyingPrice, prices[i]);
        }
        return profit;
    }

}
/*
Surprisingly, it is possible to solve the problem in O(n) time, which means that just one loop is enough.
The idea is to calculate, for each array position, the maximum sum of a subarray that ends at that position.
After this, the answer for the problem is the maximum of those sums.
Consider the subproblem of finding the maximum-sum subarray that ends at position k.
There are two possibilities:
1. The subarray only contains the element at position k.
2. The subarray consists of a subarray that ends at position k − 1, followed by
the element at position k.
In the latter case, since we want to find a subarray with maximum sum,
the subarray that ends at position k − 1 should also have the maximum sum.
Thus, we can solve the problem efficiently by calculating the maximum subarray sum for each ending position from
left to right.
The following code implements the algorithm:
The algorithm only contains one loop that goes through the input, so the time complexity is O(n).
This is also the best possible time complexity, because any algorithm for the problem has to examine
all array elements at least once.

 */
