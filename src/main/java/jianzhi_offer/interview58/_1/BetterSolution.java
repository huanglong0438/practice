package jianzhi_offer.interview58._1;

/**
 * BetterSolution
 *
 * @title BetterSolution
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/24
 **/
class BetterSolution {
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int i = s.length()-1, j = s.length()-1;
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--;
            sb.append(s.substring(i+1, j+1)).append(' ');
            while(i >= 0 && s.charAt(i) == ' ') i--;
            j = i;
        }
        return sb.toString().trim();
    }
}
