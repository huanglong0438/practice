package mycollections.mylists;

import mycollections.AbstractCollection;
import mycollections.Collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * List的抽象实现类，把能通过迭代器或者调用add等抽象方法实现的逻辑都做了，然后具体的add等方法到底怎么做交给子类，
 * 这才是面向对象，逻辑清晰感人
 *
 * Created by donglongcheng01 on 2018/2/2.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {

    /**
     * todo 思考，为什么构造函数都要是protected的
     */
    protected AbstractList() {
    }

    /**
     * 这个方法就是永远返回true
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * 随机访问的实现交给子类，线性表、链表各有办法
     *
     * @param index
     * @return
     */
    @Override
    abstract public E get(int index);

    /**
     * 默认的List不支持set方法，也就是说不要求子类必须实现这个方法，可以直接用这个
     * 为什么别的方法都留给子类一定要实现，这个方法却直接自己消化跑异常呢？
     * 我猜大概是考虑商量过的吧，子类实现set接口不是必要的
     *
     * 像Arrays.asList()方法得到的就是一个AbstractList类的简单实现，
     * 所以只有基本的访问遍历功能，其它方法没必要，在简易实现里面只需要一个数组，其他功能有点多余
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * 用【迭代器】可以实现的方法就都可以在抽象类中实现了
     *
     * 注意，不论是AbstractCollection还是AbstractList，各种方法都支持null，都没有歧视null同学，这很平权
     *
     * @param o
     * @return 元素o第一次出现的位置，或者没有出现返回-1
     */
    @Override
    public int indexOf(Object o) {
        ListIterator<E> it = listIterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return it.previousIndex();
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    return it.previousIndex();
                }
            }
        }
        return -1;
    }

    /**
     * ListIterator的强大初露端倪，可以反向遍历，和我刚开始预想的用一个index来记录更新最大索引位置的思路不一样
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        ListIterator<E> it = listIterator();
        if (o == null) {
            while (it.hasPrevious()) {
                if (it.previous() == null) {
                    return it.nextIndex();
                }
            }
        } else {
            while (it.hasPrevious()) {
                if (o.equals(it.previous())) {
                    return it.nextIndex();
                }
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        removeRange(0, size());
    }

    /**
     * 从index的位置往后把c填进去，index是外面传的，要保证不越界，所以调用方法rangeCheckForAdd
     *
     * @param index
     * @param c 用了extends的泛型，表示了一种包容性，包容不只是E，E的子类都可以
     * @return
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    /**
     * 注意到这里new了一个内部类ListItr，传了参数index，
     * 敲黑板！java的内部类不允许对外部的参数进行修改，所以外部的参数要定义为final类型
     *
     * 敲黑板！因为index是外面传来的，不确定是否合法，所以先来一手越界判断，
     * 不但要判断而且还抽象出来了一个方法rangeCheckForAdd，细细细细细细细细！！！
     *
     * @param index
     * @return
     */
    public ListIterator<E> listIterator(final int index) {
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

    /**
     * list的基础迭代器，这个是所有Collection都有的
     *
     * 总结：
     * 因为每个迭代器有自己的expectedModCount，如果只在本迭代器进行修改操作是不会出现expectedModCount != modCount的，
     * 会导致expectedModCount != modCount的情况
     * 要么是你自己直接在list进行了remove等修改，
     * 要么是通过别的迭代器调用list.remove等进行了操作
     *
     * 因此，
     * 在遍历的时候【一定要通过迭代器来进行修改】，不要自己对list进行操作，
     *   因为Iterator有自己的一个成员变量计数器，对比自己造成的修改和list整个的修改
     * 不要在多个迭代器并行遍历的时候执行修改，这样可能导致ConcurrentModificationException快速失败
     *
     */
    private class Itr implements Iterator<E> {

        /**
         * 调next的时候准备返回给它的位置
         */
        int cursor = 0;

        /**
         * 最近一次遍历到的位置，cursor的前置位，这个实际上才是当前遍历到的位置，
         * 如果调了remove方法把当前删了，那lastRet就是置到-1
         */
        int lastRet = -1;

        /**
         * 迭代器预估List遍历还剩多少个元素，如果这个期望值非法，迭代器就会检测到【并发修改】，
         * 这里已经考虑到并发的问题了，注意，学一手
         *
         * expectedModCount是迭代器专属的，modCount是list的，一开始是相等的，
         * 一旦list执行了add,set,remove等修改操作就会modCount++，而如果用了迭代器的话说明正在遍历，会快速失败
         * 所以要在读的时候进行修改就要用迭代器操作
         */
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            // 就很自信，直接用!=判断，相信自己的Itr类里的cursor肯定不会出界，因为是private没有暴露给外面瞎用
            // 暴露出的public方法都很自信

            // 自信个毛啊- -|| 遍历的时候remove会导致size=0,cursor=1的情况，
            // 但是这种情况会被checkForComodification发现并行修改，然后抛异常
            return cursor != size();
        }

        /**
         * important 这个方法是遍历的核心
         *
         * @return 遍历到的下一个元素
         */
        @Override
        public E next() {
            checkForComodification();
            try {
                // 看来AbstractList用的是随机访问的方式，根据下标i来遍历的，偏向【线性表】的方法，我猜链表会对这个重新定义
                int i = cursor;
                E next = get(i);
                // 迭代标准套路，cursor和lastRet都往前推一位
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                // 调用方的代码写的不严谨没有判断hasNext()的时候就会走到这里
                // 这里横竖都是抛异常，如果查到modCount != expectedModCount就是并发修改异常，否则就是NoSuchElementException
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        /**
         * 就是把lastRet位置的元素删掉，然后cursor--，lastRet置为-1
         *
         * @exception ConcurrentModificationException
         * 注意：
         * 1. lastRet默认就是-1，所以上来没有next就直接remove会抛异常
         * 2. remove删除完了后lastRet会置-1，所以连着两个remove会抛异常
         */
        @Override
        public void remove() {
            // lastRet默认就是-1，所以如果不执行next就直接remove会抛异常
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            // 和next方法一样先看是否有并发修改
            checkForComodification();
            try {
                // 本质上是调了外面的remove
                AbstractList.this.remove(lastRet);
                if (lastRet < cursor) {
                    cursor--;
                }
                // 注意，这里删完了之后lastRet又置为-1，所以不能连续remove两次
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            // 这里的抽象方法没写，list子类实现的时候add,set,remove等方法会让modCount++，
            // 如果此时正在用迭代器遍历，就会抛异常，
            // 所以遍历的时候修改要在这里做，因为迭代器的这些方法都会执行expectedModCount = modCount
            // 原来设计的时候也不是为了list.remove，而是防止并发搞了多个迭代器，然后同时在迭代器里修改，防的是这个
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    /**
     * list的ListIterator迭代器，这个是List这种接口单独扩展的一种迭代器，
     * 针对List的结构特点，比传统Iterator更好用，支持反向迭代
     */
    private class ListItr extends Itr implements ListIterator<E> {

        public ListItr(int index) {
            // 直接复用了父类Itr的cursor变量
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * 往前遍历的时候和iterator不同的是取的不是cursor，而是cursor - 1
         *
         * @return
         */
        @Override
        public E previous() {
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                // 往前遍历的时候取完previous，lastRet和cursor对齐了
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * 把元素e设置到lastRet的位置，因为用的lastRet的位置，同样的：
         *
         * @exception IllegalStateException,ConcurrentModificationException
         * 注意：
         * 1. lastRet默认就是-1，所以上来没有next就直接set会抛异常
         * 2. remove删除完了后lastRet会置-1，所以remove后直接set会抛异常
         *
         * @param e
         */
        @Override
        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                // 这里IndexOutOfBoundsException是因为有可能list执行了remove，导致set的时候lastRet出界了
                throw new ConcurrentModificationException();
            }

        }

        /**
         * 在cursor的位置插入e，注意这次不是lastRet了，是执行next才会遍历到的cursor位
         *
         * @param e
         */
        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                // 这个add是插入到i的置位（无损原来的），还是有损的，
                // 是插入了挪动后面的（线性表），还是修改指针指向（链表），都取决于子类add的实现，
                // 这样一手扩展很舒服
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                // 同上，这里IndexOutOfBoundsException是因为有可能list执行了remove，导致add的时候lastRet出界了
                throw new ConcurrentModificationException();
            }

        }
    }


    /**
     * 如果是RandomAccess随机访问类型的list就返回RandomAccessSubList，问题是这里的RandomAccessSubList也没干啥。。
     * 难道是子类扩展定义的？随机访问的可能会有单独的扩展实现？
     *
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public List<E> subList(int fromIndex, int toIndex) {
        return this instanceof RandomAccess ?
                new RandomAccessSubList<>(this, fromIndex, toIndex) :
                new SubList<>(this, fromIndex, toIndex);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }

        // 对于list，官方还是很青睐于用ListIterator的，可能各种扩展都会对List比较适用吧
        ListIterator<E> e1 = listIterator();
        ListIterator<?> e2 = ((List) obj).listIterator();

        while (e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();

            // 非常的简（难）洁（懂）
            // 每次都会注意到null的情况，
            // 考虑到正常情况o1如果是null那o2就要是null，o1如果不是null那o1.equals(o2)，
            // 然后一手取反，不是上述情况的话就不相等，就退出
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }

        // e1,e2任何一个没走完 这件事是true，就说明不相等（false），所以取反，代码是简洁了，但是真他妈难懂
        return !(e1.hasNext() || e2.hasNext());
    }

    /**
     * 一个公式，list的每个元素遍历，公式： hash = 31 * hash + e.hashCode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        // 为啥这里就不用迭代器了
        for (E e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }

    /**
     * 从fromIndex到toIndex，使用ListIterator进行遍历，遍历一个删一个
     * 注意前面这些用迭代器的方法遍历时是通过it.hasNext()方法判断越界的，
     * 而listIterator(fromIndex)方法的fromIndex是从外面传进来的，所以需要判断越界（严谨！细！学着点！）
     *
     * @param fromIndex
     * @param toIndex
     */
    protected void removeRange(int fromIndex, int toIndex) {
        ListIterator<E> it = listIterator(fromIndex);
        for (int i=0, n=toIndex-fromIndex; i<n; i++) {
            it.next();
            it.remove();
        }
    }

    /**
     * modCount，一个不接受序列化的成员变量，
     * 因为它是用来在遍历的时候判断并发修改的，是动态的调用的时候用到的，【功能性的变量】，序列化成静态内容没什么用
     * 我们需要序列化的是【结果性的变量】
     */
    protected transient int modCount = 0;

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }
}

/**
 * SubList为啥非要重新定义一个AbstractList的子类
 *
 * SubList好像是依附于AbstractList的子类，之所以没有单独新建一个AbstractList貌似是为了节省空间？
 * 直接复用了AbstractList的内存空间数据，在原AbstractList的数据之上进行各种操作，
 * 所以对subList的修改会影响原List
 *
 * @param <E>
 */
class SubList<E> extends AbstractList<E> {

    /**
     * 这两个之所以订成final，是因为真的不会改变，这个就是SubList的元数据
     */
    private final AbstractList<E> l;

    private final int offset;

    private int size;

    SubList(AbstractList<E> list, int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > list.size()) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        }
        l = list;
        offset = fromIndex;
        size = toIndex - fromIndex;
        this.modCount = l.modCount;
    }

    /**
     * SubList好像是依附于AbstractList的子类，之所以没有单独新建一个AbstractList貌似是为了节省空间？
     * 直接复用了AbstractList的内存空间数据，在原AbstractList的数据之上进行各种操作，
     * 所以对subList的修改会影响原List
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        // 外部传来的index，所以要校验范围
        rangeCheck(index);
        // 日常判断并行修改
        checkForComodification();
        // 对原List l进行修改，在offset的偏移量上进行index位的修改
        return l.set(index+offset, element);
    }

    /**
     * 在原List数据上进行操作，所以get方法也是同理
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangeCheck(index);
        checkForComodification();
        return l.get(index+offset);
    }

    @Override
    public int size() {
        checkForComodification();
        return size;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        checkForComodification();
        l.add(index+offset, element);
        this.modCount = l.modCount;
        // size这里要自己手动加
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        checkForComodification();
        E result = l.remove(index+offset);
        this.modCount = l.modCount;
        // 同上，自己手动减一
        size--;
        return result;
    }

    protected void removeRange(int fromIndex, int toIndex) {
        checkForComodification();
        l.removeRange(fromIndex+offset, toIndex+offset);
        this.modCount = l.modCount;
        size -= (toIndex-fromIndex);
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return false;

        checkForComodification();
        l.addAll(offset+index, c);
        this.modCount = l.modCount;
        size += cSize;
        return true;
    }

    /**
     * 这个直接放弃Iterator了。。。直接用listIterator了。。。
     *
     * @return
     */
    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator(final int index) {
        checkForComodification();
        rangeCheckForAdd(index);

        return new ListIterator<E>() {

            // 从父List获得listIterator
            private final ListIterator<E> i = l.listIterator(index + offset);

            @Override
            public boolean hasNext() {
                return nextIndex() < size;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    return i.next();
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return previousIndex() > 0;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    return i.previous();
                } else {
                    throw new NoSuchElementException();
                }
            }

            /**
             * 对外来说是从0开始的，所以要假装从0开始，减掉offset
             *
             * @return
             */
            @Override
            public int nextIndex() {
                return i.nextIndex() - offset;
            }

            @Override
            public int previousIndex() {
                return i.previousIndex() - offset;
            }

            @Override
            public void remove() {
                i.remove();
                SubList.this.modCount = l.modCount;
                size--;
            }

            @Override
            public void set(E e) {
                i.set(e);
            }

            @Override
            public void add(E e) {
                i.add(e);
                // 会影响到整体的size的操作就要进行modCount确认是否有并发修改
                SubList.this.modCount = l.modCount;
                size++;
            }

        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList<>(this, fromIndex, toIndex);
    }


    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkForComodification() {
        if (this.modCount != l.modCount)
            throw new ConcurrentModificationException();
    }
}

/**
 * SubList的一种扩展
 *
 * @param <E>
 */
class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {
    RandomAccessSubList(AbstractList<E> list, int fromIndex, int toIndex) {
        super(list, fromIndex, toIndex);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new RandomAccessSubList<>(this, fromIndex, toIndex);
    }
}
