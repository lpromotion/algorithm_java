import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 활성화 되어 있는 앱의 개수
        int M = Integer.parseInt(st.nextToken()); // 필요한 메모리 바이트

        int[] m = new int[N]; // 각 앱이 사용중인 메모리
        int[] c = new int[N]; // 각 앱이 비활성화할 때 드는 추가 비용
        int totalCost = 0; // 최대 비용, dp 배열의 크기

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            m[i] = Integer.parseInt(input[i]);
        }
        input = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            c[i] = Integer.parseInt(input[i]);
            totalCost += c[i];
        }

        int[] dp = new int[totalCost+1]; // 각 비용에 대한 최대 메모리 저장

        for(int i=0; i<N; i++) {
            for(int j=totalCost; j>=c[i]; j--) { // DP 배열 역순 탐색
                // 현재 앱을 비활성화하는 경우와 그렇지 않은 경우 중 최대 메모리 선택
                dp[j] = Math.max(dp[j], dp[j-c[i]] + m[i]);
            }
        }

        // 필요한 메모리 M을 확보할 수 있는 최소 비용 찾음
        for(int i=0; i<=totalCost; i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
