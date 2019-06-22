package mycollections.mylists;

import mycollections.Collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

/**
 * List的接口
 *
 * Created by donglongcheng01 on 2018/2/2.
 */
public interface List<E> extends Collection<E> {

    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(Object o);

    @Override
    Iterator<E> iterator();

    @Override
    Object[] toArray();

    @Override
    <T> T[] toArray(T[] a);

    @Override
    boolean add(E e);

    @Override
    boolean remove(Object o);

    @Override
    boolean containsAll(Collection<?> c);

    @Override
    boolean addAll(Collection<? extends E> c);

    @Override
    boolean removeAll(Collection<?> c);

    @Override
    boolean retainAll(Collection<?> c);

    /**
     * 对list内的所有元素进行replace操作，todo 这个UnaryOperator又是个lambda表达式
     *
     * @param operator
     */
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    /**
     * 按照Comparator的比较规则对list进行排序
     *
     * @param c
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    default void sort(Comparator<? super E> c) {
        // 先转成数组，然后调用Arrays.sort方法进行排序，最后再把数组塞回去
        Object[] a = this.toArray();
        // 这个Arrays.sort排序就是根据数组的长度，长于一定的长度就进行归并，比较小就进行快排
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    @Override
    void clear();

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    /**
     * 随机访问方法，这个线性表和链表各有各的办法
     * 下面的都是list这个接口所特有的方法
     *
     * @param index
     * @return
     */
    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<E> listIterator();

    List<E> subList(int fromIndex, int toIndex);

    @Override
    default Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }
}
