package jianzhi_offer.interview56._1;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/17
 **/
class Solution {
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int xor = xorAll(nums);
        int mask = findMask(xor);
        int singleNumber1 = 0;
        int singleNumber2 = 0;
        for(int num : nums) {
            if ((mask & num) == 0) {
                singleNumber1 ^= num;
            } else {
                singleNumber2 ^= num;
            }
        }
        return new int[]{singleNumber1, singleNumber2};
    }

    private int xorAll(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        return xor;
    }


    private int findMask(int xor) {
        int mask = 1;
        while((xor & mask) == 0) {
            mask <<= 1;
        }
        return mask;
    }

}
