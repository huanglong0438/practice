package jianzhi_offer.interview60;

/**
 * Solution1
 *
 * @title Solution1
 * @Description
 * @Author donglongcheng01
 * @Date 2021/6/29
 **/
public class Solution1 {

    private static final int MAX_PIPS = 6;

    public double[] dicesProbability(int n) {
        if (n < 1) {
            return null;
        }
        int[] occurrences = new int[MAX_PIPS * n - n + 1];
        probability(n, 0, 0, occurrences);
        double[] probabilities = new double[MAX_PIPS*n - n + 1];
        double allProbabilities = Math.pow(MAX_PIPS, n);
        for(int i = 0; i < occurrences.length; i++) {
            probabilities[i] = occurrences[i] / allProbabilities;
        }
        return probabilities;
    }


    private void probability(int n, int cur, int sum, int[] occurrences) {
        if (cur == n) {
            incrementProbabilities(occurrences, n, sum);
            return;
        }
        for (int i = 1; i <= MAX_PIPS; i++) {
            probability(n, cur+1, sum+i, occurrences);
        }
    }


    private void incrementProbabilities(int[] occurrences, int n, int sum) {
        occurrences[sum - n]++;
    }
}
