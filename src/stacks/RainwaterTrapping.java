package stacks;
/*
InterviewBit (not really stack. But anyways):

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.
Example :

Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */

public class RainwaterTrapping {

    /*
                 __
                |  |
     ---        |  |
    |   |    ---|  |
    |   |___|   |  |      ---
    |   |   |   |  |__ __|   |
    [ 3   1   2   5  0  0  1 ]

    Find left[i] which shows Max height left of i including i 3335555
    Find right[i] which shows Max height right of i including i 5555111

    water += min(left[i], right[i]) - arr[i]

    0+2+1+1+1

    */
    int solve(int[] arr){
        final int left[] = new int[arr.length];
        final int right[] = new int[arr.length];
        int result = 0;
        left[0] = arr[0];
        right[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length ; i++) {
            left[i] = Math.max(left[i-1], arr[i]);
        }

        for (int i = arr.length - 2; i >= 0 ; i--) {
            right[i] = Math.max(right[i+1], arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            result +=  Math.min(left[i],right[i]) - arr[i];
        }
        return result;
    }
 /*
                 __
                |  |
     ---        |  |
    |   |    ---|  |
    |   |___|   |  |      ---
    |   |   |   |  |__ __|   |
    [ 3   1   2   5  0  0  1 ]

    Keep two pointers at left and right end. Do below till i < j
    See height at which end is lesser
    Update leftMax or rightMax while traversing the array if ht at i or j is greater than leftMax or rightMax
    else water  += leftMax or rightMax - arr[i]

    i=0, j=6

    leftMax =  0, 0
    RightMax = 0, 1

    ht[i=0] > ht[j=6] (3>1) check right
       ht[6] > rightMax = 0 update rightMax = 1 and j = 5

    ht[i=0] > ht[j=5] (3>0) check right
       ht[5] < rightMax = 1 water = rightMax - ht[5] = 1 - 0 = 1 & j = 4

    ht[i=0] > ht[j=4] (3>0) check right
       ht[5] < rightMax = 1 water = 1 + (rightMax - ht[4]) = 1+ 1 - 0 = 2 & j = 3

    ht[i=0] < ht[j=3] (3<5) check left
       ht[0] > leftMax = 0 update leftMax = 3 and i = 1

     ht[i=1] < ht[j=3] (1<5) check left
       ht[1] < leftMax = 3 water = 2 + (leftMax - ht[1]) = 2 + 3 - 1 = 4 & i = 2

     ht[2] < ht[3] (2<5) check left
       ht[2] < leftmax water = 4 + (3 -2) = 5 and i = 3

     since i == j break






*/

    int solve_improved (int[] arr){
       int i = 0;
       int j = arr.length - 1;
       int leftMax = 0, rightMax = 0;
       int result = 0;

       while (i < j) {
           if (arr[i] < arr[j]) {
               if (arr[i] > leftMax)
                   leftMax = arr[i];
               else
                   result+=leftMax - arr[i];
               i++;
           } else {
               if (arr[j] > rightMax)
                   rightMax = arr[j];
               else
                   result+=rightMax - arr[j];
               j--;
           }
       }
       return result;
    }
}

class RainWaterTrappingTest {
    public static void main(String[] args) {
        RainwaterTrapping rainWaterTrapping = new RainwaterTrapping();
        System.out.println(rainWaterTrapping.solve_improved(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}) == 6?
                "Test 1 Passed" : "Test 1 failed");

        System.out.println(rainWaterTrapping.solve(new int[]{ 3, 1, 2,5,0, 0, 1}) == 5 ?
                "Test 2 Passed" : "Test 2 failed");
    }
}