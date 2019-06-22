package mycollections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Collection的抽象类，这种类一般都实现了Collection的基本方法，然后一些关键的个性化的方法实现例如iterator(),size(),add()都留着扩展
 *
 * 而通用的如contains(),toArray(),remove(),addAll(),retainAll(),clear(),toString()
 * 这样的方法大抵都可以通过迭代器或调用个性化方法实现一样的逻辑，于是在抽象类中实现
 *
 * Created by donglongcheng01 on 2018/2/1.
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    protected AbstractCollection() {

    }

    public abstract Iterator<E> iterator();

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            // 如果拷贝到数组的第i个就没有了，那数组r就重新定义长度i，然后结束(细细细！！！)
            if (!it.hasNext()) {
                return Arrays.copyOf(r, i);
            }
            r[i] = it.next();
        }
        // 考虑完迭代器先结束的情况了，那如果迭代器还有，数组不够用了呢？细细细！！！
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 我tm尽力了，这个我是真的看！不！懂！
     *
     * @param a
     * @param <T>
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        int size = size();
        T[] r = a.length >= size ? a : (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) {
                // 迭代器到头了，数组r可能还没有填完
                if (a == r) {
                    // 如果到头了，r用的a（也就是说参数传的数组a太大了，那多出的部分都塞null）
                    r[i] = null;
                } else if (a.length < i) {
                    // i走出a的范围了，说明上面的三元表达式走的是后面的，【说明r的长度比a大，把r扩张成长度i】
                    return Arrays.copyOf(r, i);
                } else {
                    // 把r的前i位拷贝到a
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        // 如果a比i长了，把a的第i位赋值null
                        a[i] = null;
                    }
                }
                return a;
            }
            // 这么霸道？强制类型转换？
            r[i] = (T) it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 数组能分配到的最大的大小.
     * 一些虚拟机在数组的中保留了一些头部信息.
     * 如果试着申请比这更大的数组可能会导致
     * OutOfMemoryError: 请求的数组大小超过了虚拟机的上限
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 这种就是如果迭代器it还没倒完，数组r却已经满了
     * 你是真细
     *
     * @param r 满了的数组，但是打算继续往里灌
     * @param it 还没迭代完的迭代器
     * @param <T> 数组的类型
     * @return 填满了的数组
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                // i == cap说明数组容量到达上限，要扩容！
                // 花里胡哨的还用个位运算，就是 1.5*cap + 1,扩充了1.5倍然后再加个1
                int newCap = cap + (cap >> 1) + 1;
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    // 如果超过数组的最大上限MAX_ARRAY_SIZE，则执行hugeCapacity方法判断是否OOM
                    newCap = hugeCapacity(cap + 1);
                }
                // 求到空间了，就把数组内容copy到新的大数组里面去
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T)it.next();
        }
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    /**
     * 如果溢出-抛异常，如果超过预设最大-返回int最大值，如果没超过预设最大-返回预设最大
     * 总之基本就是要返回线上的大小了
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        // 这里minCapacity小于0不是指真的传了个0，而是说cap + 1以后超过int的范围，溢出了
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                    ("Required array size too large");
        // 如果要求的空间已经超过了预留的空间，就不多bb了，直接给你最大的int范围
        // 如果还没超过预留空间，就返回给预留的最大
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * 时刻不忘防一手NPE，你怎么说嘛
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // 遍历c，如果c的每一个都包含就返回真，正常逻辑没毛病
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 搞了一手boolean变量，用来表示改动一次就置位的逻辑，也是编写代码时的常用逻辑
     *
     * @param c
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 正常逻辑，也是上面的逻辑，这种xxAll的操作要返回是否成功都需要用一个boolean来记录
     * 然后可以学一下NPE抛出点控制
     *
     * @param c
     * @return
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        // 如果c为null，则把空指针异常的抛出点置到这里
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 保留下来c的，移除掉所有c以外的
     *
     * @param c
     * @return
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                // c以外的，移除
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 小伙子很喜欢用迭代器，有空可以了解下，为什么用迭代器，为什么不用for循环和增强型for循环
     * 学一手
     * 迭代器可以更加定制化，对于线性表和链表，有着不同的遍历方式，一个是i累加读，一个是通过next指针，迭代器可以统一
     * 另外，增强型for循环的本质是调用的迭代器，是一样的，是java的一个语法糖
     *
     */
    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            // 又是一手迭代器
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
