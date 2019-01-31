package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator2 {
    /*
 Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
     */
    public static void main(String[] args) {
        System.out.println(calculate("412* 200 / 10 + 9"));
        System.out.println(calculate("0-214748367"));
        System.out.println(calculate("1-1-9"));

    }

    public static int calculate(String s) {
        int num = 0, result = 0, sign = 1;
        int len = s.length();
        for (int i = 0; i < len; ) {
            char curr = s.charAt(i);
            if (Character.isWhitespace(curr)) {
                i++;
                continue;
            }
            else if (curr == '+' || curr == '-') {
                sign = curr == '+' ? 1 : -1;
                i++;
            } else if (Character.isDigit(curr)){
                Character mulOrDiv = null;
                // Case: if a number is 200 or 200*60/10
                while(i < len) {
                    if (Character.isWhitespace(s.charAt(i))) {i++; continue; }
                    else if (Character.isDigit(s.charAt(i))){
                        int currNum = s.charAt(i) - '0';
                        while (i < len - 1 && Character.isDigit(s.charAt(i+1))) {
                            currNum = currNum * 10 + s.charAt(++i) - '0';
                        }
                        if (mulOrDiv != null) {
                            num = mulOrDiv == '*' ? num * currNum : num / currNum;
                        } else
                            num = currNum;
                        i++;
                    }
                    else if (s.charAt(i) == '*') { mulOrDiv = '*'; i++; }
                    else if (s.charAt(i) == '/') { mulOrDiv = '/'; i++;}
                    else break;
                }
                result = result + sign * num;
            }
        }
        return result;
    }

}
