import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 돌의 개수
        int[] dp = new int[1001];
        dp[1] = 1; // 1 -> 홀 -> 상근 승리
        dp[2] = 2; // 1+1 => 짝 -> 창영 승리
        dp[3] = 1; // 3 => 홀 -> 상근 승리

        for(int i=4; i<=n; i++) {
            // 돌 1개 또는 3개를 가져갔을 때
            // 남은 돌의 상태에서 최적의 전략을 선택
            dp[i] = Math.min(dp[i-1], dp[i-3]) + 1;
        }

        if(dp[n]%2 == 0) {
            // dp[n]이 짜수이면 창영이의 승리
            System.out.println("CY");
        } // dp[n]이 홀수이면 상근이의 승리
        else System.out.println("SK");
    }
}
