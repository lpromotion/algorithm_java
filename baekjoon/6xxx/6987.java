import java.io.*;
import java.util.*;

public class Main {
    static int[] team1 = { 0,0,0,0,0,1,1,1,1,2,2,2,3,3,4 };
    static int[] team2 = { 1,2,3,4,5,2,3,4,5,3,4,5,4,5,5 };
    static int[][] gameInputResult;
    static int[][] gameResult;
    static int[] result = new int[4];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            gameInputResult = new int[6][3];
            gameResult = new int[6][3];
            for(int j=0; j<6; j++) {
                for(int k=0; k<3; k++) {
                    gameInputResult[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            backtracking(i, 0);
            sb.append(result[i]+" ");
        }

        System.out.println(sb);
    }

    static void backtracking(int i, int round) {
        if(result[i]==1) return;
        if(round == 15) {
            for(int t=0; t<6; t++) {
                for(int k=0; k<3; k++) {
                    if(gameInputResult[t][k] != gameResult[t][k])
                        return;
                }
            }
            result[i] = 1;
            return;
        }

        int t1 = team1[round]; int t2 = team2[round];

        // 승-패
        gameResult[t1][0]++; gameResult[t2][2]++;
        backtracking(i, round+1);
        gameResult[t1][0]--; gameResult[t2][2]--;

        // 무-무
        gameResult[t1][1]++; gameResult[t2][1]++;
        backtracking(i, round+1);
        gameResult[t1][1]--; gameResult[t2][1]--;

        // 패-승
        gameResult[t1][2]++; gameResult[t2][0]++;
        backtracking(i, round+1);
        gameResult[t1][2]--; gameResult[t2][0]--;
    }
}
