import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 직사각형의 가로 길이

        // 문제에서 n의 최대값이 1000이므로 1001 크기의 배열 생성
        int[] dp = new int[1001];
        dp[1] = 1; dp[2] = 2; // 초기값 설정

        for(int i=3; i<=n; i++) {
            // 점화식을 통해 dp 배열 채우기
            dp[i] = (dp[i-1] + dp[i-2]) % 10_007;
        }

        System.out.println(dp[n]);
    }
}
