package jianzhi_offer.interview41;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * MedianFinder
 *
 * @title MedianFinder
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/29
 **/
class MedianFinder {

    private Queue<Integer> smaller;
    private Queue<Integer> larger;

    /** initialize your data structure here. */
    public MedianFinder() {
        smaller = new PriorityQueue<>((v1, v2) -> (v2 - v1));
        larger = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (smaller.size() == larger.size()) {
            if (larger.size() == 0 || num <= larger.peek()) {
                smaller.offer(num);
            } else {
                int smalliestOfLarger = larger.poll();
                larger.offer(num);
                smaller.offer(smalliestOfLarger);
            }
        } else {
            if (smaller.size() == 0 || num >= smaller.peek()) {
                larger.offer(num);
            } else {
                int largestOfSmaller = smaller.poll();
                smaller.offer(num);
                larger.offer(largestOfSmaller);
            }
        }
    }

    public double findMedian() {
        if (smaller.size() == 0 && larger.size() == 0) {
            return -1;
        }
        if (smaller.size() == larger.size()) {
            return (smaller.peek() + larger.peek()) / 2.0;
        } else {
            return smaller.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
