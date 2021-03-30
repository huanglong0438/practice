package jianzhi_offer.interview30;

import java.util.Deque;
import java.util.LinkedList;

/**
 * MinStack
 *
 * @title MinStack
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/30
 **/
public class MinStack {

    private Deque<Integer> dataStack;
    private Deque<Integer> minDataStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new LinkedList<>();
        minDataStack = new LinkedList<>();
    }

    public void push(int x) {
        dataStack.push(x);
        if (minDataStack.isEmpty() || x <= minDataStack.peek()) {
            minDataStack.push(x);
        }
    }

    public void pop() {
        int data = dataStack.pop();
        if (data == minDataStack.peek()) {
            minDataStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minDataStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.min());   // --> 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());   //   --> 返回 0.
        System.out.println(minStack.min());;   // --> 返回 -2.
    }

}
