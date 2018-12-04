package stacks;

import java.util.Deque;
import java.util.LinkedList;

/*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result
and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

 */
public class EvaluateRPN {

    public int evalRPN(String[] tokens) {

        Deque<String> stack = new LinkedList<>();
        for (String token : tokens){
            if (isOperand(token)) {
                stack.push(token);

            } else if (isOperator(token)) {
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String result = operate(operand2, operand1, token);
                stack.push(result);

            } else {
                throw new RuntimeException("Invalid tokens");
            }
        }

    }

    private String operate(String operand2, String operand1, String token) {
    }

    private boolean isOperator(String token) {
        return false;
    }

    private boolean isOperand(String token) {
        return false;
    }

    public static void main(String[] args) {
        EvaluateRPN evaluateRPN = new EvaluateRPN();
        int a = evaluateRPN.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"});
        int b = evaluateRPN.evalRPN(new String[]{"4", "13", "5", "/", "+"});
        int c = evaluateRPN.evalRPN(new String[]{"2", "1", "+", "3", "*"});
        System.out.println(a == 22 ? "Test 1 passed" : "Test 1 failed");
        System.out.println(b == 6 ? "Test 1 passed" : "Test 1 failed");
        System.out.println(c == 9 ? "Test 1 passed" : "Test 1 failed");
    }
}
