package DynamicProgramming;

/**
 *
 * 一手状态转移方程，皮的嘛，就不谈了
 * 状态转移方程： sum[i]=max(sum[i-1]+a[i],a[i])
 * sum[i]要么是sum[i-1]+a[i]，要么是从a[i]开始重新算
 *
 * Created by donglongcheng01 on 2017/9/13.
 */
public class MaxSeriesSum {
    public static void main(String[] args) {
        int sum = 0, max = 0;
        int data[] = {1,-2,3,-1,7};
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        System.out.println(max);
    }
}
