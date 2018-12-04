package stacks;/*
Leetcode:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

 */

    import java.util.Deque;
    import java.util.LinkedList;

    public class MinStack {
        private Deque<Value> stack;
        private class Value {int val; int min;}

        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList<>();
        }

        public void push(int x) {
            Value val = new Value();
            val.val = x;
            if (stack.isEmpty()){
                val.min = x;
            } else if (x > stack.peek().min) {
                val.min = stack.peek().min;
            } else
                val.min = x;
            stack.push(val);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            if (stack.isEmpty()) throw new RuntimeException("Stack is Empty");
            return stack.peek().val;
        }

        public int getMin() {
            if (stack.isEmpty()) throw new RuntimeException("Stack is Empty");
            return stack.peek().min;
        }
    }

    class TestMinStack {
        public static void main(String[] args) {
            MinStack minStack = new MinStack();
            minStack.push(-2);
            minStack.push(0);
            minStack.push(-3);
            System.out.println(minStack.getMin() == -3 ? "Test 1 Passed" : "Test 1 Failed");
            minStack.pop();
            System.out.println(minStack.top() == 0 ? "Test 2 Passed" : "Test 2 Failed");
            System.out.println(minStack.getMin() == -2 ? "Test 3 Passed" : "Test 3 Failed");
        }
    }