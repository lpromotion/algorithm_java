import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // dp 배열 초기화: dp[i]는 i를 1, 2, 3의 합으로 나타낼 수 있는 경우의 수를 의미
        int[] dp = new int[12]; // 문제에서 n이 최대 11
        dp[1] = 1; // 1을 만드는 방법은 1가지 (1)
        dp[2] = 2; // 2를 만드는 방법은 2가지 (1+1, 2)
        dp[3] = 4; // 3을 만드는 방법은 4가지 (1+1+1, 1+2, 2+1, 3)

        // DP 배열을 채움: 4부터 11까지의 값을 점화식을 통해 계산
        for(int i=4; i<=11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }
}
