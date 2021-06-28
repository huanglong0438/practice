package jianzhi_offer.interview56._2;

import java.util.ArrayList;
import java.util.List;

/**
 * MySolution
 *
 * @title MySolution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/17
 **/
class MySolution {

    public int singleNumber(int[] nums) {
        List<Integer> bitCounts = new ArrayList<>();
        for(int num : nums) {
            addBit(num, bitCounts);
        }
        int singleNumber = 0;
        int mask = 1;
        for (int bitCount : bitCounts) {
            if (bitCount % 3 != 0) {
                singleNumber += mask;
            }
            mask <<= 1;
        }
        return singleNumber;
    }

    private void addBit(int num, List<Integer> bitCounts) {
        int i = 0;
        while(num != 0) {
            int bit = num & 1;
            if (i == bitCounts.size()) {
                bitCounts.add(bit);
            } else {
                bitCounts.set(i, bitCounts.get(i) + bit);
            }
            i++;
            num >>= 1;
        }
    }
}
