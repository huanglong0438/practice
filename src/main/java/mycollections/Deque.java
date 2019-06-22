package mycollections;

import java.util.Iterator;

/**
 * 看了下这个接口提供的方法,厉害了我的哥,这里的Deque不仅是一个双向队列,还是一个队列+栈,也就是说这也是个栈
 *
 * Stack接口的实现是Vector,Vector太慢了,没人用,一般用栈就用这个就行
 *
 * Created by donglongcheng01 on 2018/2/12.
 */
public interface Deque<E> extends Queue<E> {

    /**
     * 下面这些都是Queue改装成双向队列后的方法，相当于队列方法*2
     *
     * @param e
     */
    void addFirst(E e);

    void addLast(E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    E removeFirst();

    E removeLast();

    E pollFirst();

    E pollLast();

    E getFirst();

    E getLast();

    E peekFirst();

    E peekLast();

    boolean removeFirstOccurrence(Object o);

    boolean removeLastOccurrence(Object o);

    // *** Queue Methods ***

    /**
     * 都是队列的方法
     *
     * @param e
     * @return
     */
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();

    // *** Stack methods ***

    /**
     * 都是栈的方法，厉害了，还支持栈，其实也就俩，push和pop
     *
     * @param e
     */
    void push(E e);

    E pop();

    boolean remove(Object o);

    boolean contains(Object o);

    public int size();

    /**
     * 正序迭代器
     *
     * @return
     */
    Iterator<E> iterator();

    /**
     * 逆序迭代器
     *
     * @return
     */
    Iterator<E> descendingIterator();

}
