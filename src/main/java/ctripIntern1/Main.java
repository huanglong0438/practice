package ctripIntern1;

import java.util.Scanner;

public class Main {
	
	public static int[] memo = new int[50];
	static{
		memo[1] = 1;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println(dp(n,memo));
		scanner.close();
	}
	
	public static int dp(int n, int memo[]){
		int res = 0;
		if(memo[n] != 0)	
			return memo[n];
		for(int i = 1; i < n; i++){
			int fac = max(n - i, dp(n - i, memo));
			res = max(res, fac*i);
		}
		memo[n] = res;
		return res;
	}
	
	public static int max(int a, int b){
		return a > b ? a : b;
	}
}

