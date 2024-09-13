import java.io.*;
import java.util.*;

public class Main {
    static int n; // 계란 개수
    static int[] dur; // 내구도
    static int[] weight; // 무게
    static int maxCnt = Integer.MIN_VALUE; // 깨진 계란 최대 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dur = new int[n];
        weight = new int[n];

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dur[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }

        backTrack(0, 0);
        System.out.println(maxCnt);
    }

    static void backTrack(int eggIdx, int cnt) {
        // 손에 드는 계란 인덱스, 깨진 계란 수를 파라미터로 받음

        // 마지막 계란을 드는 경우 종료 (깨진 계란의 최댓값 찾음)
        if(eggIdx == n) {
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        // 손에 든 계란이 이미 깨졌거나, 이미 다른 모든 계란이 깨져있으면 넘어감 (다음 계란 들기)
        if(dur[eggIdx] <= 0 || cnt == n-1) {
            backTrack(eggIdx+1, cnt);
            return;
        }

        int backCnt = cnt;
        // 다른 모든 계란과 부딪혀봄
        for(int i=0; i<n; i++) {
            // 손에 들고 있는 계란이면 패스
            if(i == eggIdx) continue;

            // 부딪히려는 계란이 이미 깨진 계란이면 패스
            if(dur[i] <= 0) continue;

            // 부딪혔을 때 계란 깨지면 cnt 증가
            dur[eggIdx] -= weight[i];
            dur[i] -= weight[eggIdx];
            if(dur[eggIdx] <= 0) cnt++;
            if(dur[i] <= 0) cnt++;

            // 다음 계란(오른쪽)으로 재귀 호출
            backTrack(eggIdx+1, cnt);

            // 계란 원래대로 복구
            dur[eggIdx] += weight[i];
            dur[i] += weight[eggIdx];
            cnt = backCnt;
        }
    }
}
