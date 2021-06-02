package jianzhi_offer.interview50;

/**
 * Solution_2
 *
 * @title Solution_2
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/2
 **/
public class Solution_2 {

    public char firstUniqChar(String s) {
        CharStatistics statistics = new CharStatistics();
        for (int i = 0; i < s.length(); i++) {
            statistics.insert(s.charAt(i));
        }
        return statistics.firstAppearingOnce();
    }

}
