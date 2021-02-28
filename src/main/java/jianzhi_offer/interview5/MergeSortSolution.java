package jianzhi_offer.interview5;

import com.alibaba.fastjson.JSON;

/**
 * MergeSortSolution
 *
 * @title MergeSortSolution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/2/24
 **/
public class MergeSortSolution {

    public void mergeSort(int[] a1, int[] a2, int len1, int len2) {
        if(a1 == null || a2 == null) {
            return;
        }
        int p = len1 + len2 - 1;
        int p1 = len1 - 1;
        int p2 = len2 - 1;
        while(p1 >= 0 && p2 >= 0) {
            if(a1[p1] > a2[p2]) {
                a1[p--] = a1[p1--];
            } else {
                a1[p--] = a2[p2--];
            }
        }
        while(p1 >= 0) {
            a1[p--] = a1[p1--];
        }
        while(p2 >= 0) {
            a1[p--] = a2[p2--];
        }
    }

    public static void main(String[] args) {
        MergeSortSolution solution = new MergeSortSolution();
        {
            int a1[] = new int[]{1, 2, 4, 0, 0};
            int a2[] = new int[]{3, 5};
            int len1 = 3;
            int len2 = 2;
            solution.mergeSort(a1, a2, len1, len2);
            System.out.println(JSON.toJSONString(a1));
        }
        {
            int a1[] = new int[]{1, 2, 4, 0, 0};
            solution.mergeSort(a1, null, 3, 0);
            System.out.println(JSON.toJSONString(a1));
        }
        {
            int a1[] = new int[0];
            int a2[] = new int[0];
            solution.mergeSort(a1, a2, 0, 0);
            System.out.println(JSON.toJSONString(a1));
        }
    }

}
