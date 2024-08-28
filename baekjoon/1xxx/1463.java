import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1000001];
        dp[2] = 1; dp[3] = 1; // 초기값 설정
        
        for(int i=4; i<=n; i++) {
            dp[i] = dp[i-1] + 1; // 1을 빼는 연산

            if(i%2 == 0) { // 2의 배수인 경우
                // 2로 나눈 경우와 비교하여 최솟값 선택
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
            }

            if(i%3 == 0) { // 3의 배수인 경우
                // 3으로 나눈 경우와 비교하여 최솟값 선택
                dp[i] = Math.min(dp[i], dp[i/3] + 1);
            }

        }

        System.out.println(dp[n]);
    }
}
