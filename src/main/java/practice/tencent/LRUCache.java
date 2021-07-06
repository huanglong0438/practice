package practice.tencent;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LRUCache
 *
 * @title LRUCache
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/1
 **/
public class LRUCache<K, V> {

    private final Map<K, Node<K, V>> map;

    private final Deque<Node<K, V>> deque;

    private final int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.deque = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            deque.remove(node);
            node.value = value;
            deque.addFirst(node);
        } else {
            Node<K, V> node = new Node<>(key, value);
            deque.addFirst(node);
            map.put(key, node);
            if (deque.size() > capacity) {
                Node<K, V> lastNode = deque.removeLast();
                map.remove(lastNode.key);
            }
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        } else {
            Node<K, V> node = map.get(key);
            put(key, node.value);
            return node.value;
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        cache.put("d", 4);
        System.out.println(cache.get("a"));
        System.out.println(cache.get("b"));
        cache.put("e", 5);
        cache.put("f", 5);
        System.out.println(cache.get("b"));
    }

}
