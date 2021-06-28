package jianzhi_offer.interview59._2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * MaxQueue
 *
 * @title MaxQueue
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/28
 **/
public class MaxQueue {

    private final Deque<Integer> dataDeque;

    private final Deque<Integer> maxDeque;

    public MaxQueue() {
        dataDeque = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public int max_value() {
        if (maxDeque.isEmpty()) {
            return -1;
        }
        return maxDeque.getFirst();
    }

    public void push_back(int value) {
        while(!maxDeque.isEmpty() && maxDeque.getLast() < value) {
            maxDeque.removeLast();
        }
        maxDeque.addLast(value);
        dataDeque.addLast(value);
    }

    public int pop_front() {
        if (dataDeque.isEmpty()) {
            return -1;
        }
        if (!maxDeque.isEmpty() && maxDeque.getFirst().equals(dataDeque.getFirst())) {
            maxDeque.removeFirst();
        }
        return dataDeque.removeFirst();
    }
}
