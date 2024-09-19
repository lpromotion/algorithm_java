import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static boolean[][] map; // 경계선 및 5구역을 표시하는 배열
    static int[][] population; // 각 지역의 인구 수를 저장하는 배열
    static int minDiff = Integer.MAX_VALUE; // 인구 차이의 최솟값을 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 재현시의 크기
        population = new int[n+1][n+1]; // 인구 저장 배열

        for(int i=1; i<=n; i++) { // 인구 데이터를 입력 받음
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가능한 모든 x, y, d1, d2의 조합을 탐색
        for(int x=1; x<=n; x++) { // 1 <= x <= N
            for(int y=1; y<=n; y++) { // 1 <= y <= N
                for(int d1=1; d1<=n; d1++) { // 1 <= d1 <= N
                    for(int d2=1; d2<=n; d2++) { // 1 <= d2 <= N
                        // 경계가 범위를 벗어나지 않도록
                        if (x + d1 + d2 <= n && 1 <= y - d1 && y + d2 <= n)
                            brute(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(minDiff);
    }

    // 경계선을 설정하고 각 구역의 인구 차이를 계산
    static void brute(int x, int y, int d1, int d2) {
        map = new boolean[n+1][n+1]; // 경계선 저장하는 배열
        int[] popSum = new int[5]; // 구역별 인구수 저장하는 배열
        
        // 1. 경계선 설정 (5번 구역 설정)
        for(int i=0; i<=d1; i++) {
            map[x+i][y-i] = true; // 경계선 조건 1
            map[x+d2+i][y+d2-i] = true; // 경계선 조건 4
        }
        for(int i=0; i<=d2; i++) {
            map[x+i][y+i] = true; // 경계선 조건 2
            map[x+d1+i][y-d1+i] = true; // 경계선 조건 3
        }
        // 5구역 내부 채우기
        for(int i=x+1; i<x+d1+d2; i++) {
            boolean inside = false;
            for(int j=1; j<=n; j++) {
                // 경계선을 만나면 내부 구역 시작/종료
                if(map[i][j]) inside = !inside;
                if(inside) map[i][j] = true;
            }
        }

        // 1번 구역
        for(int r=1; r<x+d1; r++) {
            for(int c=1; c<=y; c++) {
                if(!map[r][c]) popSum[0] += population[r][c];
            }
        }

        // 2번 구역
        for(int r=1; r<=x+d2; r++) {
            for(int c=y+1; c<=n; c++) {
                if(!map[r][c]) popSum[1] += population[r][c];
            }
        }

        // 3번 구역
        for(int r=x+d1; r<=n; r++) {
            for(int c=1; c<y-d1+d2; c++) {
                if(!map[r][c]) popSum[2] += population[r][c];
            }
        }

        // 4번 구역
        for(int r=x+d2+1; r<=n; r++) {
            for(int c=y-d1+d2; c<=n; c++) {
                if(!map[r][c]) popSum[3] += population[r][c];
            }
        }

        // 5번 구역
        for(int r=1; r<=n; r++) {
            for(int c=1; c<=n; c++) {
                if(map[r][c]) popSum[4] += population[r][c];
            }
        }

        Arrays.sort(popSum); // 오름차순 정렬
        minDiff = Math.min(minDiff, popSum[4]-popSum[0]); // 인구 차이의 최솟값 갱신
    }
}
