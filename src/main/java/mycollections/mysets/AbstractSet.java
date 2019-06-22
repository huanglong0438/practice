package mycollections.mysets;

import mycollections.AbstractCollection;
import mycollections.Collection;

import java.util.Iterator;
import java.util.Objects;

/**
 * Set的抽象类，把能用迭代器或者其它抽象方法实现的公共逻辑都做了
 *
 * Created by donglongcheng01 on 2018/2/14.
 */
public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E> {

    protected AbstractSet() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Set)) {
            return false;
        }
        Collection<?> c = (Collection<?>) o;
        if (c.size() != size()) {
            return false;
        }
        try {
            return containsAll(c);
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }
    }

    /**
     * 就是把Set遍历一遍，每个元素的hash值累加
     *
     * @return
     */
    @Override
    public int hashCode() {
        int h = 0;
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E obj = i.next();
            if (obj != null) {
                h += obj.hashCode();
            }
        }
        return h;
    }

    /**
     * 删除集合c里的全部内容，删掉一个成功就返回true
     *
     * @param c
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;

        if (size() > c.size()) {
            // 需要被删除的集合小，就以c为基准遍历，这样可以减少遍历次数，省时间
            for (Iterator<?> i = c.iterator(); i.hasNext(); )
                // 走被删除的集合的迭代器挨个执行remove，modified状态的判断还用了一手 |=，一真全真
                modified |= remove(i.next());
        } else {
            for (Iterator<?> i = iterator(); i.hasNext(); ) {
                // 遍历本Set，如果c里有，就把这个元素删掉
                if (c.contains(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }
}
