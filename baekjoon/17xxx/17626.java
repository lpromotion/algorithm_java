import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // DP 배열 초기화. 인덱스 i는 숫자 i를 의미하고,
        // dp[i]는 i를 표현하는데 필요한 제곱수의 최소 개수를 저장
        int[] dp = new int[n+1];
        // 0은 제곱수가 필요 없고, 1은 1^2 하나로 표현 가능
        dp[0] = 0; dp[1] = 1;

        // 2부터 n까지의 모든 숫자에 대해 최소 제곱수 개수 계산
        for(int i=2; i<=n; i++) {
            int min = Integer.MAX_VALUE; // 최소 제곱수 개수 저장
            for(int j=1; j*j<=i; j++) { // i보다 작거나 같은 모든 제곱수 검사
                int temp = i - j*j; // i에서 현재 제곱수(j*j)를 뺀 나머지
                min = Math.min(min, dp[temp]); // dp[temp]와 현재 최솟값 비교하여 갱신
            }
            
            // 최종 최솟값에 1을 더해서 저장 (현재 사용한 제곱수 하나 추가)
            dp[i] = min+1;
        }

        System.out.println(dp[n]);
    }
}
