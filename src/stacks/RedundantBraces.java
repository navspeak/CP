package stacks;
/*
Write a program to validate if the input string has redundant braces?
Return 0/1

0 -> NO
1 -> YES
Input will be always a valid expression

and operators allowed are only + , * , - , /

Example:

((a + b)) has redundant braces so answer will be 1
(a + (a + b)) doesn't have have any redundant braces so answer will be 0

 */

import java.util.Stack;

public class RedundantBraces {

    static boolean hasRedundantBraces(String exp){
        Stack<Character> stack = new Stack<>();
        for (char c: exp.toCharArray()) {
            if (c == ')') {
                int n = 0;
                while (!stack.isEmpty() && stack.peek() != '(' ){
                    stack.pop();
                    n++;
                }
                if (stack.peek() == '(') stack.pop();
                if (n == 0) return true;

            } else {
                if (isOperator(c) || c == '(')
                    stack.push(c);
            }
        }
        return false;
    }

    private static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '/' || c == '*')
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        String exp1 = "((a + b))";
        String exp2 = "(a+(a+ b))";
        String exp3 = "(a+(b)/c)";
        System.out.println(hasRedundantBraces(exp1));
        System.out.println(hasRedundantBraces(exp2));
        System.out.println(hasRedundantBraces(exp3));
    }
}
