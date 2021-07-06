package jianzhi_offer.interview62;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Solution1
 * timeout
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/7/6
 **/
class Solution1 {
    public int lastRemaining(int n, int m) {
        if(n <= 0 || m <= 0) {
            return -1;
        }
        LinkedList<Integer> link = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            link.add(i);
        }
        Iterator<Integer> iter = link.iterator();
        while(link.size() > 1) {
            for (int i = 0; i < m; i++) {
                if(!iter.hasNext()) {
                    iter = link.iterator();
                }
                iter.next();
            }
            iter.remove();
        }
        return link.get(0);
    }
}
