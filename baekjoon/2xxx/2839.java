import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 설탕의 무게

        int[] dp = new int[5001]; // 최소 봉지 개수 저장
        dp[1] = 0; dp[2] = 0; dp[4] = 0; // 1,2,4는 0으로 초기화
        dp[3] = 1; dp[5] = 1; // 3,5는 1로 초기화

        // 6부터 n까지 dp값 계산
        for(int i=6; i<=n; i++) {
            // 3kg 봉지로 만들 수 없고 5kg 봉지로 만들 수 있는 경우
            if(dp[i-3]==0 && dp[i-5]!=0)
                dp[i] = dp[i-5] + 1;
            // 3kg 봉지로 만들 수 있고 5kg 봉지로 만들 수 없는 경우
            else if(dp[i-3]!=0 && dp[i-5]==0)
                dp[i] = dp[i-3] + 1;
            // 3kg과 5kg 봉지 모두 사용 가능한 경우
            else if(dp[i-3]!=0 && dp[i-5]!=0)
                // 최소 개수를 선택
                dp[i] = Math.min(dp[i-3], dp[i-5]) + 1;
            // 어떤 경우에도 만들 수 없는 경우
            else dp[i] = 0;
        }

        // n에 해당하는 dp 값이 0이라면 -1 출력 (불가능)
        if(dp[n] == 0) System.out.println(-1);
        else System.out.println(dp[n]);
    }
}
