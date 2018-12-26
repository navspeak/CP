package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum {
    /*
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

     */

    public static int[] twoSum(int[] nums, int target) {
        final int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
       for(int i=0; i < len; i++){
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }
       return null;
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2,7,11,15}, 9);
        if (result != null)
            System.out.println(result[0] + ", " + result[1]);
        else
            System.out.println("No solutions");
    }
}
