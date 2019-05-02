package leetcode;

import java.util.Stack;

public class BasicCalculator1 {
    public static void main(String[] args) {
        String str1 = "((1+6-2+(5-3 - (2-1))-6+9))";
        int result = calculate(str1);
        System.out.println(result);
    }

    private static int calculate(String str) {
        int len = str.length();
        int num = 0, sign = 1, result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len ; i++) {
            char curr = str.charAt(i);
            if (Character.isWhitespace(curr)) continue;
            else if (Character.isDigit(curr)) {
                num = curr - '0';
                while(i < len -1 && Character.isDigit(str.charAt(i+1))) {
                    num = num * 10 + str.charAt(++i) - '0';
                }
                result = result + sign * num;
            } else if (curr == '+' || curr == '-') {
                sign = curr == '+' ? 1 : -1;
            } else if (curr == '(') {
                stack.push(result); result = 0; num = 0;
                stack.push(sign); sign = 1;
            } else if (curr == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        //check - stack should be already empty by now
       while(!stack.isEmpty()){
           result = result * stack.pop() + stack.pop();
       }
       return result;
    }

    public static class MaximumSubArray {
        //−1 2 4 −3 5 2 −5 2

        /*
        A straightforward way to solve the problem is to go through all possible subarrays,
        calculate the sum of values in each subarray and maintain the maximum sum.
         */
        public int solve_ncube(int[] array) {
            int best = 0;
            final int length = array.length;
            for (int i = 0; i < length; i++) { // first pointer
                for (int j = i; j < length; j++) { // second pointer
                    int sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum += array[k]; // sum in the window between first & second pointer
                    }
                    best = Math.max(sum, best);
                }

            }
            return best;
        }

        // Improve it by computing sum at the same time as we move second pointer
        // O(n^2)
        public int solve_nsquare(int[] array) {
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
        // −1 2 4 −3 5 2 −5 2
        public int solve_lineartime(int[] array) {
            //int best = 0; int sum = 0;
            int best = array[0]; // Best so far
            int sum = array[0];  // Max Sum ending at this index
            // Consider input [-1]
            int endIndexOfSubArray = 0; // needed if we want to print all elements
            final int length = array.length;
            for (int i = 1; i < length; i++) {
                sum = Math.max(array[i], sum + array[i]);
                if (sum > best) {
                    endIndexOfSubArray = i;
                }
                best = Math.max(sum, best);
            }
            int rem = best;
            for (int i = endIndexOfSubArray; i > 0; i--) {
                if (rem <= 0) break;
                System.out.print(array[i] + " ");
                rem -= array[i];

            }
            System.out.println("\n=====");
            return best;
        }


        public static void main(String[] args) {
    //        leetcode.BasicCalculator1.MaximumSubArray maximumSubArray = new leetcode.BasicCalculator1.MaximumSubArray();
    //        int[] array = {-1, 2, 4, -3, 5, 2, -5, 2};
    //        int ans1 = maximumSubArray.solve_lineartime(array);
    //        int ans2 = maximumSubArray.solve_nsquare(array);
    //        int ans3 = maximumSubArray.solve_ncube(array);
    //        System.out.printf("%d %d %d\n", ans1, ans2, ans3);
    //        int[] arr2 = {7,1,5,3,6,4};
    //        int[] arr3 = {1,2,3,4,5};
    //        int[] arr4 = {7,6,4,3,1};
    //        System.out.println(maxProfit2(arr2));
    //        System.out.println(maxProfit2(arr3));
    //        System.out.println(maxProfit2(arr4));

    //        int[] arr2 = {3,3,5,0,0,3,1,4};
    //        int[] arr3 = {1,2,3,4,5};
    //        int[] arr4 = {7,6,4,3,1};
    //        System.out.println(maxProfit3(arr2));
    //        System.out.println(maxProfit3(arr3));
    //        System.out.println(maxProfit3(arr4));
        }


    }
}

