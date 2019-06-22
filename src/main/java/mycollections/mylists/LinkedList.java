package mycollections.mylists;

import mycollections.Deque;

import java.io.Serializable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class LinkedList<E> extends AbstractList<E> implements List<E>, Deque<E>, Cloneable, Serializable {

    transient int size;

    transient Node<E> first;

    transient Node<E> last;

    public LinkedList() {
    }

    public LinkedList(Collection<E> c) {
        this();
        addAll(c);
    }

    /**
     * 链表添加表头的基本操作
     *
     * @param e
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            // 去掉引用,触发GC
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            // 去掉引用,触发GC
            x.next = null;
        }

        // 去掉引用,触发GC
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 在index的位置上加c的元素进去
     *
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        // 老规矩，外部传参先校验
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        // pred是前节点,succ是后节点
        Node<E> pred, succ;
        if (index == size) {
            // 如果是尾部添加succ就是null,pred就是最后一个
            succ = null;
            pred = last;
        } else {
            // 如果是中间添加,就要先通过index定位succ,然后succ前一位是pred
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            // 用pred来添加
            if (pred == null)
                // pred是null表示链表为空,就赋值first
                first = newNode;
            else
                // 把pred后面接上新节点
                pred.next = newNode;
            // pred往前推动
            pred = newNode;
        }

        if (succ == null) {
            // succ是null,说明是添加到末尾的,把添加完的最后一个pred定为last
            last = pred;
        } else {
            // 说明是中间添加的,把pred和succ的关系连好
            pred.next = succ;
            succ.prev = pred;
        }
        // 别忘了增加size
        size += numNew;
        // LinkedList也是不支持并发修改,只有vector支持并发修改,但是不好用,没人用了
        modCount++;
        return true;
    }

    @Override
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {
            // 遍历链表
            Node<E> next = x.next;
            // 拆掉三个部分,触发GC(如果是generational GC,保证就算有一个可达的迭代器也会回收)
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        // first last 拆掉
        first = last = null;
        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        // index外部传入的,先校验
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 执行node方法找到index位置的值,然后把Node的值替换下
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public void add(int index, E element) {
        // 校验index
        checkPositionIndex(index);

        if (index == size)
            // 如果是size,说明加在末尾,那和add(E element)是一样的
            linkLast(element);
        else
            // 否则加在index位置前
            linkBefore(element, node(index));
    }

    @Override
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 根据index找到节点Node
     *
     * @param index
     * @return
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);
        // 根据index和size的大小关系,如果index在前半部分就正向遍历,否则反向遍历
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * LinkedList特有的数据结构，就是双向链表的节点
     *
     * @param <E>
     */
    private static class Node<E> {
        E item;
        Node<E> next;

        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }


    }

    /**
     * 基本和AbstractList还有ArrayList的思路一样,就是遍历,然后计数
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * 一手反向遍历
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override
    public void addLast(E e) {
        linkLast(e);
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    @Override
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    @Override
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    @Override
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }


    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    @Override
    public E element() {
        return getFirst();
    }


    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    public E remove() {
        return removeFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    /**
     * 老操作的了,对于null和普通Object一视同仁
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    /**
     * ArrayList的迭代器是通过一手cursor和lastRet来标记遍历的位置,再辅以modCount校验并发修改
     * todo 看下LinkedList的listIterator迭代器有什么特色
     */
    private class ListItr implements ListIterator<E> {

        // 记录上一个,next的前一个
        private Node<E> lastReturned;
        // 下面这俩是用来遍历的
        private Node<E> next;
        private int nextIndex;
        // 用来保证只有一个迭代器修改的
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            // 根据index定位next和nexIndex
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        /**
         * 这里怎么就知道用小于了?
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }


        @Override
        public E previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            // 遍历到末尾了吗,到了就返回last,否则就返回next的前一个,lastReturned和next对齐
            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }


        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        /**
         * 把lastReturned删掉,lastReturned置null,next如果是走到头了(next == lastReturned),就让next=lastReturned
         * 所以remove()完之后不能set,这时的lastReturned是null
         *
         */
        @Override
        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<E> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        @Override
        public void set(E e) {
            // 刚remove完不能set
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        /**
         * 还是基本的链表连接方法
         *
         * @param e
         */
        @Override
        public void add(E e) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                // 遍历,每个元素item都执行action.accept()方法
                action.accept(next.item);
                // 往前推
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }
    }

    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }

    /**
     * small trick, hum? ListItr反过来
     */
    private class DescendingIterator implements Iterator<E> {
        private final ListItr itr = new ListItr(size());
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public E next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 就是执行了下Object.clone(),主要是想封装调这个异常
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Object clone() {
        // 执行父类(Object)的clone方法,克隆出的first,last都是引用,所以只克隆了引用,如果对克隆体修改是会影响原LinkedList内容的
        LinkedList<E> clone = superClone();

        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initialize clone with our elements
        // 对克隆体执行LinkedList.add()方法逐个添加元素,都是新内存区域
        for (Node<E> x = first; x != null; x = x.next)
            clone.add(x.item);

        return clone;
    }

    /**
     * 哼哼,这里你就不像ArrayList那么便利了吧,需要遍历添加到数组
     *
     * @return
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    /**
     * 参数a传入个new T[0]或者 new T[size]都行
     * 传入new T[0],相当于只要类型,
     * 传入new T[size]相当于引用也保留,这个引用后面可以用
     *
     * 如果传入的a太长了,会在最后一位的后面搞一个null,大概是表示截断吧
     *
     * @param a
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // 传入的a的长度比较短,原来传的就废掉了,直接反射一个新的数组
        if (a.length < size)
            // 这句就是用反射完成的 a = new T[size]
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        // 然后在这里赋值
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        // 如果传入的a太长了,就把第一个多余的位置置为null(表示截断?)
        if (a.length > size)
            a[size] = null;

        return a;
    }
}
