package hashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by donglongcheng01 on 2017/7/31.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Map大家族
         */
        Hashtable<String, Object> hashtable = new Hashtable<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        hashtable.put("name", "donglongcheng");
        hashtable.get("name");

        hashMap.put("name", "donglongcheng");
        hashMap.get("name");
        hashMap.put(null, "I'm null");
        System.out.println(hashMap.get(null));
        System.out.println(null == null);
        System.out.println(hashMap.get("girlFriend"));

        concurrentHashMap.put("name", "donglongcheng");
        concurrentHashMap.get("name");

        /**
         * List大家族
         */
        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        Vector<String> vector = new Vector<>();

        arrayList.add("donglongcheng");
        arrayList.get(0);

        linkedList.add("donglongcheng");
        linkedList.get(0);

        vector.add("donglongcheng");
        vector.get(0);

        // LinkedList也是Queue的一种实现
        Queue<String> queue = new LinkedList<>();

        linkedList.addFirst("donglongcheng");
        linkedList.getFirst();

    }
}
