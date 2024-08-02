import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int S = Integer.parseInt(input[1]);
        int M = Integer.parseInt(input[2]);
        String[] volList = br.readLine().split(" ");

        int[] dp = new int[M+1];
        Arrays.fill(dp, -1);
        dp[S] = 0; // 0번째 연주에서 가능한 볼륨은 S
        for(int i=1; i<=N; i++) {
            int V = Integer.parseInt(volList[i-1]);
            List<Integer> possibleVol = new ArrayList<>();

            for(int j=0; j<=M; j++) {
                if(dp[j] == i-1) {
                    int case1 = j - V;
                    int case2 = j + V;
                    if(case1 >= 0 && case1 <= M) possibleVol.add(case1);
                    if(case2 >= 0 && case2 <= M) possibleVol.add(case2);
                }
            }

            for(int vol : possibleVol) {
                dp[vol] = i;
            }
        }

        int result = -1;
        for(int i=0; i<M+1; i++) {
            if(dp[i] == N) result = Math.max(result, i);
        }

        System.out.println(result);
    }
}
