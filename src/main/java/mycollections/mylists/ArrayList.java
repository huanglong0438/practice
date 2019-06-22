package mycollections.mylists;

import mycollections.Collection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * todo 搞定后整个把ArrayList这一路过一遍，整理个文档（扩容从刚new出来到10，11，到100，过程走一遍，再从addAll()方法的角度考虑下）
 * 整个ArrayList就是数组操作的一个封装
 *
 * Created by donglongcheng01 on 2018/2/4.
 */
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {

    private static final long serialVersionUID = 3886646594114574290L;

    /**
     * ArrayList底层封装的数组的初始大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * static的，所有ArrayList实例共享的空数据的实例
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 也是一个空的数组，和EMPTY_ELEMENTDATA不同的是，这个是准备要初始化的
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * ArrayList底层的数组，刚new的时候是DEFAULTCAPACITY_EMPTY_ELEMENTDATA，
     * 如果执行了add，就new一个大小为DEFAULT_CAPACITY的数组给它
     */
    transient Object[] elementData;

    /**
     * ArrayList的大小，有可能不是elementData.length
     */
    private int size;

    /**
     * 构造函数，如果有规定初始化容量，就按规定的来
     * 如果规定是0，那就是说要建一个空ArrayList，就给它共享的空数组EMPTY_ELEMENTDATA
     * （节省空间，学一手，以后任何表示空的东西都可以用一个static的全局常量表示）
     * 如果初始化容量小于0，就抛异常（细细细细细）
     *
     * @param initialCapacity
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(Collection<? extends E> c) {
        // 卧槽，Collection直接转数组，这么简单粗暴吗,直接toArray方法转数组,然后给到底层数组elementData
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // 还有可能不是Object[]？不会吧？...卧槽,这是JDK的一个bug,有可能c.toArray().getClass()不是Object[].class
            // https://www.zhihu.com/question/26603565/answer/33394672
            // https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6260652
            if (elementData.getClass() != Object[].class) {
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            // 也是要创建一个空ArrayList
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * 在size比底层数组长度要小的情况时（我猜一般是ArrayList执行了逻辑删除），整理下ArrayList的size，
     * 如果新的size空了就直接底层数组赋值EMPTY_ELEMENTDATA，不空就执行数组复制把原数组压缩
     */
    public void trimToSize() {
        // 注意，这里会调节数组长度，所以防止并行修改异常，modCount++了
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * 根据elementData判断是否为初始化的数组，得到当前可扩展的容量，然后根据可扩展的容量进行比较决定是否扩展
     *
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity) {
        // 可扩展的容量
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // 如果不是刚初始化的DEFAULTCAPACITY_EMPTY_ELEMENTDATA，那随便扩展无上限，所以0
                ? 0
                // 如果是刚初始化的DEFAULTCAPACITY_EMPTY_ELEMENTDATA，那上线是规定的DEFAULT_CAPACITY=10
                : DEFAULT_CAPACITY;
        if (minCapacity > minExpand) {
            // 如果可扩展的能力比需求的扩展量小，那就要扩容
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 如果elementData是刚初始化的，执行扩张时就取DEFAULT_CAPACITY和minCapacity最大的
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 前面的那个是个封装，封装了DEFAULTCAPACITY_EMPTY_ELEMENTDATA的判断，
     * 这个才是真正判断是否需要扩展的，因为这里直接比较了底层数组的大小容量和要求的容量，
     * 如果比较得到结果需要扩展，就执行grow方法
     *
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        // 扩容的时候注意并发修改异常
        modCount++;

        // 自主扩展的代码
        // 如果当前底层数组的长度比要求的扩展性小，就执行自动扩容grow
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    // 8是头部信息header words的长度
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 扩展的时候先把原数组长度扩张1.5倍，如果ok就ok，不行就直接扩到要求的长度，如果扩完的长度超上限有OOM风险，就走hugeCapacity方法
     * 最后调用Arrays.copyOf执行数组的扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        // oldCapacity就是底层数组的长度，扩容按照1.5倍扩展
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            // 如果扩容完了还小，那干脆就一步扩到要求的minCapacity得了
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            // 如果扩容完了超过规定上限MAX_ARRAY_SIZE，执行hugeCapacity方法处理超大数组
            newCapacity = hugeCapacity(minCapacity);
        }
        // minCapacity is usually close to size, so this is a win:
        // 最后调用Arrays.copyOf执行数组的扩容
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 超过预定上限有OOM风险的时候执行的方法，如果minCapacity溢出了（变成负数）就抛OOM
     * 否则判断是否超预定上限，超了就直接给最大整数（这是理论上的数组的最大长度），没超预定就给预定的上限
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        // 同样没有歧视null同学
        if (o == null) {
            // 底层是数组嘛，所以就是遍历数组，这我也会
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            // 记住，lastIndexOf不必从前到后然后记录最大的index，直接反向走
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * 要想clone方法不报错，必须实现cloneable接口
     *
     * @return
     */
    public Object clone() {
        try {
            // 执行clone
            ArrayList<?> v = (ArrayList<?>) super.clone();
            // clone体的elementData还要自己赋值的？不是直接clone的吗？
            // answer: clone执行了以后对于数组只会复制数组的指针，这并没有什么卵用，于是需要执行Arrays.copyOf进行深复制
            v.elementData = Arrays.copyOf(elementData, size);
            // 重置clone体的modCount
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            // 这个异常应该进不去，因为ArrayList实现cloneable接口了
            throw new InternalError(e);
        }
    }

    /**
     * 就是把底层的elementData复制一份提供出去，
     * 啥，为什么要复制？你傻啊，不给复制给原件外面随便改动会影响ArrayList不懂吗
     *
     * @return
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 其实一般人都是调的下面这个，没有信息的Object[]空壳子谁爱用啊
     * 如果传的参数a的长度更短，则直接copy出一个新的数组
     * 如果传的参数a的长度更长，则把elementData的内容都拷贝到a，然后把多余部分的第一个置为null（{1,2,3,4,5,null}）
     *
     * @param a
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /**
     * 简单粗暴的方法，取数组
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 取数，调用了封装好的校验index范围的方法，（学一手，要封装）
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * 基本操作，没啥好说的
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 调用了ensureCapacityInternal判断扩容，初始和10比，10之前的add都不会改变elementData
     * 11之后add会导致扩容1.5倍，变成15，16之后又扩容成15*1.5
     *
     * 其它都是基本操作
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * 同上，除了校验index其它都是基本操作
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        // 因为外面传过来的index，所以确定
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        // 把index以后的部分 挪到 index+1往后的位置，把index的位置给空出来
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 从数组中删除掉中间的数，后面往前归拢一下的方法实现，也就是一般编程题的水平吧
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        // 注意到也可能不需要挪动，如果删除的那一位是最后一位的话
        if (numMoved > 0)
            // 把index+1之后的位置 挪到 index的位置上，把index位置的内容给挤掉
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        // size减一，并且挪完后最后一位会冗余，置为null，让GC把它回收掉
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 老逻辑了，到处都是
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /**
     * 提出来的逻辑，为了代码的美观性，可读性
     *
     * @param index
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * 清除ArrayList的逻辑，就是把底层数组每一位都置null，让GC回收
     * 所以我们看透了JVM的本质，在JVM中【清除】=【GC回收】，其它改引用什么的都是扯淡
     *
     */
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    /**
     * 所以这个ensureCapacityInternal方法就厉害了，各处都可以无脑调用
     *
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * 基本操作，不暴露出去，留给子类可能想删除范围的时候用的
     * 所以是protect，所以没有进行fromIndex和toIndex的校验
     *
     * @param fromIndex
     * @param toIndex
     */
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    /**
     * 不校验index<0的情况吗，哦哦，index负数直接在elementData方法上取数组的时候抛异常了。。emmm，你开心就好。。。
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 这你就知道校验index<0了？
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * 把c里的元素都删掉
     *
     * @param c
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, false);
    }

    /**
     * 这个方法最精髓的地方在于,由于是批量删除,所以一个一个删除再把后面全部往前归拢会很蠢,
     * 所以先统一把要保留的都往前归拢,最后把后面那些(多余的)统一GC掉
     *
     * @param c
     * @param complement false就是把c里的都删掉，true就是把c以外的都删掉 #搞了一手complement机智地扩展了方法
     * @return 修改成功与否，有删除过就算成功
     */
    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    // 如果complement==false，就是对于数组里的r号元素如果不在c里，说明要保留，所以就把它往前归拢到前面
                    // 走进这个if条件里的都是要保留的
                    elementData[w++] = elementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                // 正常情况是不会走到这里的，走到这里一般就是c.contains()抛异常了
                // 这种异常的情况就把r以及后面的都挪到前面对接上w后的位置，r是先锋走到的位置，w是处理完的元素堆起来的位置
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // w != size 说明有删除过的痕迹，就把w后面的元素都释放
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * 把elementData的元素挨个写进ObjectOutputStream里
     *
     * @param s
     * @throws java.io.IOException
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 从ObjectInputStream里读元素放到elementData
     * （是提供给内部自己调用的，找到调用方看看，这个size是怎么分配的，直接从0写到数组，说明数组原来必须是空的吧）
     * 回答，是serializable接口规定自定义序列化时使用的方法，size是反序列化得到的，原来数组肯定是空，不空也会覆盖
     *
     * @param s
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i=0; i<size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    private class Itr implements Iterator<E> {

        /**
         * 这仨都是老朋友了，主要看为什么非要重写一个Itr
         */
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return this.cursor != size;
        }

        /**
         * 基本上就是封装了遍历数组的那一套，只不过有两个指针，一前一后同时往前推
         * 中间加上并行修改校验，数组越界校验
         *
         * @return
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                // 就是没判断hasNext的时候出界了，正常操作
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                // 上面判断了没出界，走到这出界了，说明存在并行修改
                throw new ConcurrentModificationException();
            // cursor往前走
            cursor = i + 1;
            // lastRet往前走的同时，把elementData的元素取出来（连打带跑）
            return (E) elementData[lastRet = i];
        }

        /**
         * 删掉lastRet位置的元素，把后面的往前归拢一下
         * cursor往前推，lastRet置-1
         * 同步下expectedModCount
         */
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {
            // 把空指针抛异常的位置提到这里
            Objects.requireNonNull(consumer);
            final int size = ArrayList.this.size;
            // 从迭代器当前遍历位置开始，调用consumer的方法的
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                // i没有大于size，但是却大于了elementData.length，一般表示有其他人在捣乱（remove）
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount) {
                // 遍历执行consumer.accept
                consumer.accept((E) elementData[i++]);
            }
            // update once at end of iteration to reduce heap write traffic
            // 在遍历完了，循环结束的时候统一更新cursor和lastRet，而不是在循环里不断改，是为了减少堆的写操作
            // （卧槽，至于吗？优化狂魔啊，一般我们写业务代码不需要这么小心翼翼，不过中间件啥的应该要注意吧，学一手）
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        /**
         * 小老板判断出界总是喜欢用 !=
         *
         * @return
         */
        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            // lastRet有可能和cursor对齐了，所以用cursor靠谱点
            return cursor - 1;
        }

        /**
         * ArrayList的previous方法会让cursor和lastRet对齐，
         * 导致的结果是。。没结果。。因为人家ArrayList里控制前后就不是根据cursor和lastRet的。。。
         *
         * @return
         */
        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            // ArrayList.this 就是内部类持有的对外部类的引用
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        /**
         * 注意设置的是lastRet的位置
         *
         * @param e
         */
        @Override
        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                // add完是-1, 所以不能add完就set
                lastRet = -1;
                // 同步下expectedModCount和modCount
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public List<E> subList(int fromIndex, int toIndex) {
        // 这一步的检查，过了之后，后面SubList子类的一切活动就不再多余校验fromIndex和toIndex了
        subListRangeCheck(fromIndex, toIndex, size);
        // 这个offset是啥，是在ArrayList的offset基础上再切？
        return new SubList(this, 0, fromIndex, toIndex);
    }


    /**
     * 各种查范围，from，to，from和to
     *
     * @param fromIndex
     * @param toIndex
     * @param size
     */
    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    /**
     * 和Abstract版的实现类似，也是一个没有实体的子列表
     */
    private class SubList extends AbstractList<E> implements RandomAccess {

        /**
         * parent是subList的源数据，祖传命根子
         */
        private final AbstractList<E> parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(AbstractList<E> parent,
                int offset, int fromIndex, int toIndex) {
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            // 继承了父List的modCount
            this.modCount = ArrayList.this.modCount;
        }

        @Override
        public E set(int index, E e) {
            rangeCheck(index);
            checkForComodification();
            // 都是日常操作，index是外界传来的所以要小心校验，然后校验并发修改
            // 注意是subList所以取elementData的时候要加个offset偏移量
            E oldValue = ArrayList.this.elementData(offset + index);
            ArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        /**
         * 基本操作，不谈
         *
         * @param index
         * @return
         */
        @Override
        public E get(int index) {
            rangeCheck(index);
            checkForComodification();
            return ArrayList.this.elementData(offset + index);
        }

        @Override
        public int size() {
            // 获得subList的长度前要确定有没有人捣乱，因为这会影响size的准确性
            // 同时注意到，subList也搞了个自己的modCount，和Iterator一样，也要校验并发修改
            checkForComodification();
            return this.size;
        }

        public void add(int index, E e) {
            rangeCheckForAdd(index);
            checkForComodification();
            // 所有修改都是在parent上的，AbstractList的实现是获得了parent的ListIterator，这里直接调了parent，为啥。。
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }

        public E remove(int index) {
            rangeCheck(index);
            checkForComodification();
            // 都是调用的parent的方法
            E result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            parent.removeRange(parentOffset + fromIndex,
                    parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        public boolean addAll(Collection<? extends E> c) {
            return addAll(this.size, c);
        }

        public boolean addAll(int index, Collection<? extends E> c) {
            // 外来参数，校验
            rangeCheckForAdd(index);
            // c也是外来参数，校验
            int cSize = c.size();
            if (cSize==0)
                return false;

            // 修改会导致list变多，所以这里要校验下，同时下面加完了之后要同步下modCount
            // （规律：只要不是在ArrayList自己的方法里进行的修改，都要校验并发修改的问题）
            // (发现ArrayList没有synchronized这些,如果不是在迭代器或者SubList,直接在本方法上并发修改了呢?)
            // 回答：ArrayList就不是支持并发修改的容器，会通过一手modCount和expectModCount来判断，
            // 你敢并发修改我就抛异常，不跟你搞这个东西
            checkForComodification();
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        /**
         * SubList也要再来个自定义迭代器? 666...
         * @return
         */
        public Iterator<E> iterator() {
            return listIterator();
        }


        /**
         * 没啥好分析的,基本上就是照着ArrayList版的迭代器再来了一遍,加上了offset偏移量的控制
         *
         * @param index
         * @return
         */
        public ListIterator<E> listIterator(final int index) {
            checkForComodification();
            rangeCheckForAdd(index);
            final int offset = this.offset;

            return new ListIterator<E>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = ArrayList.this.modCount;

                public boolean hasNext() {
                    return cursor != SubList.this.size;
                }

                @SuppressWarnings("unchecked")
                public E next() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= SubList.this.size)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i + 1;
                    return (E) elementData[offset + (lastRet = i)];
                }

                public boolean hasPrevious() {
                    return cursor != 0;
                }

                @SuppressWarnings("unchecked")
                public E previous() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0)
                        throw new NoSuchElementException();
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length)
                        throw new ConcurrentModificationException();
                    cursor = i;
                    return (E) elementData[offset + (lastRet = i)];
                }

                @SuppressWarnings("unchecked")
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = SubList.this.size;
                    int i = cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    while (i != size && modCount == expectedModCount) {
                        consumer.accept((E) elementData[offset + (i++)]);
                    }
                    // update once at end of iteration to reduce heap write traffic
                    lastRet = cursor = i;
                    checkForComodification();
                }

                public int nextIndex() {
                    return cursor;
                }

                public int previousIndex() {
                    return cursor - 1;
                }

                public void remove() {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                public void set(E e) {
                    if (lastRet < 0)
                        throw new IllegalStateException();
                    checkForComodification();

                    try {
                        ArrayList.this.set(offset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                public void add(E e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (expectedModCount != ArrayList.this.modCount)
                        throw new ConcurrentModificationException();
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex) {
            // 这个不是走的ArrayList.subList方法的,所以要加上subListRangeCheck对范围进行判断
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
        }

        private void checkForComodification() {
            if (ArrayList.this.modCount != this.modCount)
                throw new ConcurrentModificationException();
        }

        /**
         * undo 这个不搞了,to be continued...
         *
         * @return
         */
        public Spliterator<E> spliterator() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        for (int i = 0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount) {
            // 如果是因为这个原因出来的就抛异常
            throw new ConcurrentModificationException();
        }
    }

    /**
     * undo to be continued...
     *
     * @return
     */
    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * undo to be continued...
     *
     * @param <E>
     */
    static final class ArrayListSpliterator<E> implements Spliterator<E> {

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            return false;
        }

        @Override
        public Spliterator<E> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        int removeCount = 0;
        /**
         * BitSet底层是long[]数组,根据给定的size右移6位(因为long是64=2^6)+1,决定数组需要初始化几个long
         * 从上面可以看出,如果你给定的size不足64位,就直接是1个long,数据是有冗余的,
         * 如果刚好64位,那就刚好多出来一个long(想想64个bit位的结构,其实是超过了2^64-1的上限了的)
         */
        final BitSet removeSet = new BitSet(size);
        final int expectModCount = modCount;
        final int size = this.size;

        Object[] elementData = this.elementData;
        for (int i = 0; modCount == expectModCount && i < size; i++) {
            @SuppressWarnings("unchecked")
            final E element = (E) elementData[i];
            if (filter.test(element)) {
                // 把BitSet的第i位置为true,表示要删除这一位,机智啊,老哥
                // 如果像我之前的实现方式,每次都remove那每次都要归拢一遍,大大影响性能,
                // JDK源码的方式是记录下来,然后统一往左边归拢,最后把剩下的部分置null,GC,这性能差距!!!可以学一手!!!
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectModCount) {
            throw new ConcurrentModificationException();
        }

        final boolean anyToRemove = removeCount > 0;
        if (anyToRemove) {
            final int newSize = size - removeCount;
            for (int i = 0, j = 0; (i < size) && (j < newSize); i++, j++) {
                // 找到i之后下一个不需要删除的位置
                i = removeSet.nextClearBit(i);
                // 把这个不需要删除的位置的数往前推到最前面(j,因为j是自增的位置)
                elementData[j] = elementData[i];
            }
            for (int k = newSize; k < size; k++) {
                elementData[k] = null; // 从newSize往后的都是归拢完剩下的残渣,GC掉它们
            }
            this.size = newSize;
            // 此句判断和上面的expectModCount = modCount,共同形成了一个锁!
            if (modCount != expectModCount) {
                // 丫的ArrayList就不支持多线程操作的,只支持单线程,但是不支持不代表就不管了
                // 通过一手modCount != expectModCount可以判断出是不是在被并发操作,发现了并发操作直接报错(简单粗暴!)
                throw new ConcurrentModificationException();
            }
            modCount++;
        }
        return anyToRemove;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = this.size;
        // 和上面一样,日常操作,遍历数组,对数组的每一个元素执行operator.apply操作
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementData[i] = operator.apply((E) elementData[i]);
        }
        // 一样一样
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        // 表示进行了一次修改,如果ArrayList同时也处在expectModCount = modCount和if (modCount != expectModCount)组成的锁中,
        // 则表示动了锁,立刻报错
        modCount++;
    }

    /**
     * ArrayList调用的sort底层还是调的Arrays.sort, 这点和它的接口List的default实现原理差不多,
     * 只不过它多了个加锁,List则是多了个toArray,毕竟ArrayList已经有elementData不需要toArray
     *
     * @param c
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        // 加锁操作
        final int expectedModCount = modCount;
        // 所以ArrayList调用的sort底层还是调的Arrays.sort
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

}
