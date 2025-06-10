package Stack;

import java.util.Stack;

public class StackMin {
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public StackMin() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        mainStack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (mainStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        mainStack.pop();
    }

    public int top() {
        if (mainStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return mainStack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return minStack.peek();
    }

    public boolean isEmpty() {
        return mainStack.isEmpty();
    }
}
