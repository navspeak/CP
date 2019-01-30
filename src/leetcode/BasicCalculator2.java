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
        System.out.println(calculate("412*200"));
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
/*
class Solution {
 public int calculate(String s) {
        List<String> postFix = convertToPostFix(s);
        return evalRPN(postFix);

    }


    private static List<String> convertToPostFix(String s) {
        Stack<Character> stack = new Stack<>();
        List<String> postfix = new ArrayList<>();
        final int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if(Character.isDigit(c)) {
                int startOfNumericVal = i;
                i++;
                while(i < length && Character.isDigit(s.charAt(i))){
                    i++;
                };
                postfix.add(s.substring(startOfNumericVal, i));
                i--;
            } else {
                    if (stack.isEmpty() || precedence(c) > precedence(stack.peek()) || c == '('){
                        stack.push(c);
                    } else {
                        while(!stack.isEmpty() && precedence(c) <= precedence(stack.peek()) || c == ')' ){
                            char popped = stack.pop();
                            if (popped == '(') break;
                            postfix.add(Character.toString(popped));
                        }
                        if (c != ')') stack.push(c);
                    }
            }
        }
        while(!stack.isEmpty()) {
            postfix.add(Character.toString(stack.pop()));
        }
        return postfix;
    }


    private static int precedence(char c) {
        switch (c) {
            case '^': return 4;
            case '*':
            case '/': return 3;
            case '+':
            case '-': return 2;
            case '(': return 1;
            default : return 0;
        }
    }

    private static int evalRPN(List<String> postFix) {
        Stack<Integer> stack = new Stack<>();
        for(String s : postFix){
            if (s.matches("\\d+")){
                stack.push(Integer.parseInt(s));
            } else {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                stack.push(operate(operand2, operand1, s));
            }
        }
        int ret = stack.pop();
        if(!stack.empty()) throw new RuntimeException("In valid postfix!");
        return ret;
    }

    private static int operate(int operand2, int operand1, String c) {
        switch (c) {
            case "^": return operand2 ^ operand1;
            case "*": return operand2 * operand1;
            case "/": return operand2 / operand1;
            case "+": return operand2 + operand1;
            case "-": return operand2 - operand1;
            default : throw new RuntimeException("Invalid Operator");
        }
    }

}
 */