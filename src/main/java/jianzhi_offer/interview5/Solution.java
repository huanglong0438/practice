package jianzhi_offer.interview5;

/**
 * Solution
 *
 * @title Solution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/22
 **/
public class Solution {

    public void replaceBlank(char[] str, int length) {
        if (str == null || str.length == 0) {
            return;
        }
        int spaces = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                spaces++;
            }
        }
        int pOld = length - 1;
        int pNew = length - 1 + (spaces * 2);
        while(pOld >= 0) {
            if (str[pOld] != ' ') {
                str[pNew--] = str[pOld--];
            } else {
                str[pNew--] = '0';
                str[pNew--] = '2';
                str[pNew--] = '%';
                pOld--;
            }
        }
    }

    public static void main(String[] args) {
        char[] str = new char[]{ 'w', 'e', ' ', 'a', 'r', 'e', ' ', 'h', 'a', 'p', 'p', 'y', '.', '\0',
                '\0', '\0', '\0', '\0', '\0' };
        new Solution().replaceBlank(str, 13);
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]);
        }
        System.out.println();
    }

}
