package leetcode;

import java.util.*;

public class BasicCalculatorGeneric {
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
        System.out.println(calculate("(4+4)/2 - 3"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate("3+5 / 2"));
        System.out.println(calculate("42"));
        System.out.println(calculate("2 * 3 ^ 4 / 5 - 6 * 7 ^ ( 8 - 9)"));


    }

    public static int calculate(String s) {
        List<String> postFix = convertToPostFix(s);
        return evalRPN(postFix);

    }


    private static List<String> convertToPostFix(String s) {
        // f = a * c ^ k / p - q * g ^ ( h - b)  => fack^*p/qghb-
        int len = s.length();
        List<String> postfix = new ArrayList<>();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len ; i++) {
            char curr = s.charAt(i);
            if (Character.isWhitespace(curr)) continue;
            else if (Character.isDigit(curr)) {
                int num = curr - '0';
                while(i < len - 1 && Character.isDigit(s.charAt(i+1))){
                    num = num * 10 + s.charAt(++i) - '0';
                }
                postfix.add(Integer.toString(num));
            } else {// this must be an operand
                if (stack.isEmpty() || curr == '(' || precedence(curr) > precedence(stack.peek()))
                    stack.push(curr);
                else {
                    while (!stack.isEmpty() && precedence(curr) <= precedence(stack.peek())
                    || curr == ')'){
                        char popped = stack.pop();
                        if (popped == '(') break;
                        postfix.add(Character.toString(popped));
                    }
                    if (curr != ')') stack.push(curr);
                }
            }
        }
        while (!stack.isEmpty()) {
            postfix.add(Character.toString(stack.pop()));
        }
        return postfix;
    }


    private static int precedence(char c) {
        switch (c) {
            case '^': return 5;
            case '*':
            case '/': return 4;
            case '+':
            case '-': return 3;
            case '(': return 2;
            //case '=': return 1; needed for LHS = RHS types
            default : return 0;
        }
    }

    private static int evalRPN(List<String> postFix) {
        Deque<Integer> stack = new LinkedList<>();
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
        if(!stack.isEmpty()) throw new RuntimeException("In valid postfix!");
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
