import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dp; // 조합의 값을 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            dp = new int[m+1][m+1]; // dp 배열 초기화

            // m개 중에서 n개를 선택하는 조합의 경우의 수를 구함
            System.out.println(combination(m, n));
        }
    }
    
    static int combination(int n, int r) {
        // 이미 계산을 한 경우, 그 값을 반환
        if(dp[n][r] > 0) return dp[n][r];
        // nCn 또는 nC1이면 값은 1로 반환
        if(n==r || r==0) return dp[n][r] = 1;

        // 재귀 호출을 통해 점화식을 만족하는 조합의 값을 계산
        dp[n][r] = combination(n-1, r-1) + combination(n-1, r);
        return dp[n][r]; // nCr 값을 반환
    }
}
