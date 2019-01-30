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
}

