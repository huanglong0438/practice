package Hulu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by donglongcheng01 on 2017/9/21.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();
        String nk = scanner.nextLine();
        int n = Integer.parseInt(nk.split(" ")[0]);
        int k = Integer.parseInt(nk.split(" ")[1]);
        List<List<Integer>> func = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            String line = scanner.nextLine();
            String[] nums = line.split(" ");
            for (String num : nums) {
                list.add(Integer.parseInt(num));
            }
            func.add(list);
        }

        List<Integer> stack = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] result = new int[1];
//        stack.add(k - 1);
        dfs(func, k, stack, result, path);
//        System.out.println(result[0]);
        if (result[0] == 0) {
            System.out.println("R");
        } else if (result[0] == -1) {
            for (int num : path) {
                System.out.print(num + " ");
            }
            System.out.println();
        } else {
            System.out.println("E");
        }
    }

    public static void dfs(List<List<Integer>> func, int start, List<Integer> stack, int[] result, List<Integer> path) {
        if (result[0] == -1 || result[0] == -2) {
            return;
        }
        if (start == 0) {
            result[0] = 0;
            return;
        } else if (start == -1) {
            result[0] = -1;
            for (int num : stack) {
                path.add(num);
            }
            return;
        } else {
            stack.add(start);
        }
        List<Integer> list = func.get(start-1);
        for (int i : list) {
            if (stack.contains(i)) {
                result[0] = -2;
                return;
            }
            List<Integer> temp = new ArrayList<>();
            for (int num : stack) {
                temp.add(num);
            }
            dfs(func, i, temp, result, path);
        }
    }
}
