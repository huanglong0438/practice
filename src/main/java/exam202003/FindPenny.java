package exam202003;

/**
 * FindPenny
 *
 * @title FindPenny
 * @Description
 * @Author donglongcheng01
 * @Date 2020-04-19
 **/
public class FindPenny {

    private void findPenny(int[] penny, int start, int aim, int[] result) {
        if (start >= penny.length) {
            return;
        }
        int count = 0;
        int newAim = aim;
        while (newAim > 0) {
            findPenny(penny, start+1, newAim, result);
            newAim = aim - penny[start] * (++count);
        }
        if(newAim == 0) { // 刚好能找到
            result[0]++;
        }
    }

    public static void main(String[] args) {
        FindPenny findPenny = new FindPenny();
        int[] result = new int[1];
        findPenny.findPenny(new int[]{1, 2, 4}, 0, 3, result);
        System.out.println(result[0]);
    }

}
