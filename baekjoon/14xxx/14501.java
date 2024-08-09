import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] T;
    static int[] P;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        T = new int[n+1];
        P = new int[n+1];
        for(int i=1; i<=n; i++) {
            // 각 날짜별 상담 기간과 금액을 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        backTrack(1, 0); // 1일, 금액 0으로 시작
        System.out.println(result);
    }

    static void backTrack(int s, int money) {
        if(s >= n+1) { // 날짜가 퇴사일 이후이면
            result = Math.max(result, money); // 받을 수 있는 최대 이익 저장
            return;
        }

        if(s+T[s] <= n+1){ // 현재 날짜의 상담이 가능하면
            backTrack(s+T[s], money+P[s]); // 다음 상담일로 재귀 호출
        }

        backTrack(s+1, money); // 현재 날짜의 상담을 하지 않으면, 다음 날짜로 재귀 호출
    }
}
