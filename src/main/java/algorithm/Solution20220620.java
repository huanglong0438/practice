package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Solution20220620 {

    private List<String> process = new ArrayList<>();

    public static void main(String[] args) {
        Solution20220620 solution20220620 = new Solution20220620();
        solution20220620.dfs(21);
    }

    private void dfs(int stone) {
        if (stone == 1) {
            System.out.println("A finally lose. " + process);
            return;
        }
        for(int i = 1; i <= 4; i++) {
            process.add("A:" + "fetch " + i + " rocks");
            process.add("B:" + "fetch " + (5-i) + " rocks");
            dfs(stone - 5);
            process.remove(process.size() - 1);
            process.remove(process.size() - 1);
        }
    }

}
