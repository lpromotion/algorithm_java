import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[] street;
    static int MAX = 10000001;

    static void findMinEnergy() {
        int[] dp = new int[N];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int i=1; i<N; i++) { // 현재 위치
            for(int j=0; j<i; j++) { // 이전 위치
                if(street[i]=='B' && street[j]=='J'
                || street[i]=='O' && street[j]=='B'
                || street[i]=='J' && street[j]=='O') {
                    int energy = (i-j)*(i-j);
                    dp[i] = Math.min(dp[i], dp[j]+energy);
                }
            }
        }

        int result = dp[N-1] == MAX ? -1 : dp[N-1];
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        street = br.readLine().toCharArray();
        findMinEnergy();
    }
}
