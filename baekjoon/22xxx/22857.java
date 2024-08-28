import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] s = new int[n+1];
        int[][] dp = new int[n+1][k+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        int maxLen = 0; // 최대 길이 저장

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (s[i] % 2 == 0) { // 짝수일 경우
                    // 짝수를 포함하여 길이를 증가
                    dp[i][j] = dp[i-1][j] + 1;
                } else if (j > 0) { // 홀수일 경우, 제거 가능할 때
                    dp[i][j] = dp[i-1][j-1];
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        System.out.println(maxLen);
    }
}
