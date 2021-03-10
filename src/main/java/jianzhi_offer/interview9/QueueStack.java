package jianzhi_offer.interview9;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 *
 * @title QueueStack
 * @Description
 * @Author donglongcheng01
 * @Date 2021/3/2
 **/
public class QueueStack<T> {

    private Queue<T> queue1;

    private Queue<T> queue2;

    public QueueStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(T elem) {
        Queue<T> curQueue = queue2.isEmpty() ? queue1 : queue2;
        curQueue.offer(elem);
    }

    public T pop() {
        Queue<T> curQueue = queue2.isEmpty() ? queue1 : queue2;
        Queue<T> anotherQueue = curQueue == queue1 ? queue2 : queue1;
        if (curQueue.isEmpty()) {
            throw new IllegalStateException("stack is empty.");
        }
        while(curQueue.size() > 1) {
            T elem = curQueue.poll();
            anotherQueue.offer(elem);
        }
        return curQueue.poll();
    }

    public static void main(String[] args) {
        QueueStack<Integer> stack = new QueueStack<>();
        stack.push(1);
        System.out.println(stack.pop());
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
