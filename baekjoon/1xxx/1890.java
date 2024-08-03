import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] gameBoard = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                gameBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1; // start
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i==N-1 && j==N-1) break;

                if(gameBoard[i][j]+i < N)
                    dp[gameBoard[i][j]+i][j] += dp[i][j];
                if(gameBoard[i][j]+j < N)
                    dp[i][gameBoard[i][j]+j] += dp[i][j];
            }
        }

        System.out.println(dp[N-1][N-1]);
    }
}
