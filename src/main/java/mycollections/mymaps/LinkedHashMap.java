package mycollections.mymaps;

/**
 * 空壳子
 *
 * Created by donglongcheng01 on 2018/2/22.
 */
public class LinkedHashMap {

    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, HashMap.Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

}
