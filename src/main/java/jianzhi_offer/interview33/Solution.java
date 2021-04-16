package jianzhi_offer.interview33;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/4/4
 **/
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if(postorder == null || postorder.length == 0) {
            return true;
        }
        return verifyPostorderCore(postorder, 0, postorder.length);
    }

    private boolean verifyPostorderCore(int[] postorder, int start, int end) {
        if (start >= end || end - start == 1) {
            return true;
        }
        int firstOfRight = findFirstOfRight(postorder, start, end);
        return allBiggerThanRoot(postorder, firstOfRight, end)
                && verifyPostorderCore(postorder, start, firstOfRight)
                && verifyPostorderCore(postorder, firstOfRight, end - 1);
    }

    private int findFirstOfRight(int[] postorder, int start, int end) {
        int root = postorder[end-1];
        for(int i = start; i < end; i++) {
            if(postorder[i] > root) {
                return i;
            }
        }
        return end-1;
    }

    private boolean allBiggerThanRoot(int[] postorder, int start, int end) {
        int root = postorder[end-1];
        for(int i = start; i < end; i++) {
            if(postorder[i] < root) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.verifyPostorder(new int[] {4, 8, 6, 12, 16, 14, 10}));
    }

}
