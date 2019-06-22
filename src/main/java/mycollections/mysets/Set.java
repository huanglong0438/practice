package mycollections.mysets;

import mycollections.Collection;

import java.util.Iterator;
import java.util.Spliterator;

/**
 * Set接口
 *
 * Created by donglongcheng01 on 2018/2/13.
 */
public interface Set<E> extends Collection<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    /**
     * 这个方法转成数组还支持扩展，也就是说子类实现的时候可以转成别的类型的数组
     *
     * @param a
     * @param <T>
     * @return
     */
    <T> T[] toArray(T[] a);

    boolean add(E e);

    @Override
    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    @Override
    boolean retainAll(Collection<?> c);

    @Override
    boolean removeAll(Collection<?> c);

    @Override
    void clear();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    /**
     * undo
     *
     * @return
     */
    @Override
    default Spliterator<E> spliterator() {
        return null;
    }
}
