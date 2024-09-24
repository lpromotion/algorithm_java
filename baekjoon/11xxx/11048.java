import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m; // 미로 크기
    static int[][] miro; // 각 방의 사탕 개수 저장
    static int[][] dp; // 최대 사탕 개수 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        miro = new int[n][m]; // 미로 배열 초기화
        dp = new int[n][m]; // dp 배열 초기화

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                // 각 방의 사탕 개수 입력받고 저장
                miro[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = miro[0][0]; // 출발 지점 초기화

        // DP를 이용해 각 칸에서 얻을 수 있는 최대 사탕 개수 계산
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(i==0 && j==0) continue;
                else if(i==0 && j>0) // 첫번째 행은 왼쪽에서만 올 수 있음
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + miro[i][j]);
                else if(i>0 && j==0) // 첫번째 열은 위쪽에서만 올 수 있음
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + miro[i][j]);
                else { // 나머지 경우는 위, 왼쪽, 대각선 왼쪽 위에서 올 수 있음
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + miro[i][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + miro[i][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + miro[i][j]);
                }
            }
        }

        System.out.println(dp[n-1][m-1]); // 도착점의 최대 사탕 개수
    }
}
