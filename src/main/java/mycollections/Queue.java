package mycollections;

import mycollections.Collection;

/**
 * Created by donglongcheng01 on 2018/2/12.
 */
public interface Queue<E> extends Collection<E> {

    /**
     * 队列尾部添加，如果满了会抛异常
     *
     * @param e
     * @return
     */
    @Override
    boolean add(E e);

    /**
     * 队列尾部添加，满了返回false
     *
     * @param e
     * @return
     */
    boolean offer(E e);

    /**
     * 删除并返回队列头部元素，如果空队列抛异常
     *
     * @return
     */
    E remove();

    /**
     * 删除并返回队列头部元素，没有就给个null
     *
     * @return
     */
    E poll();

    /**
     * 查看队列头部元素，如果没有就抛异常
     *
     * @return
     */
    E element();

    /**
     * 查看队列头部元素，如果没有就返回null
     *
     * @return
     */
    E peek();
}
