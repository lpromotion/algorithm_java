import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001]; // dp 배열의 크기를 1001로 설정
        // 초기값 설정
        dp[1] = 1; // 2x1 직사각형을 채우는 방법의 수
        dp[2] = 3; // 2x2 직사각형을 채우는 방법의 수

        for(int i=3; i<=n; i++) {
            // 점화식을 이용해 배열 채우기
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
        }

        System.out.println(dp[n]);
    }
}
