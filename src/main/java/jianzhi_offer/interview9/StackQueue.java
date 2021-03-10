package jianzhi_offer.interview9;

import javax.annotation.Nullable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 两个栈实现一个队列
 *
 * @title StackQueue
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/2
 **/
public class StackQueue<T> {

    private Deque<T> appendStack;

    private Deque<T> popStack;

    public StackQueue() {
        appendStack = new LinkedList<>();
        popStack = new LinkedList<>();
    }

    /**
     * maybe OOM
     **/
    public void appendTail(@Nullable T elem) {
        appendStack.push(elem);
    }

    public T deleteHead() {
        if (popStack.isEmpty()) {
            while(!appendStack.isEmpty()) {
                T elem = appendStack.pop();
                popStack.push(elem);
            }
        }
        if (popStack.isEmpty()) {
            throw new IllegalStateException("queue is empty.");
        }
        return popStack.pop();
    }

    public static void main(String[] args) {
        StackQueue<Integer> queue = new StackQueue<>();
        queue.appendTail(1);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead()); // exception
    }

}
