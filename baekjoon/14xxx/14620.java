import java.io.*;
import java.util.*;

public class Main {
    static int n; // 정원의 크기
    static int[][] garden; // 정원의 비용 정보 저장
    static boolean[][] visited; // 방문 여부 확인
    static int minCost = Integer.MAX_VALUE; // 최소 비용 저장

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static void dfs(int flower, int cost) {
        if(flower == 3) { // 꽃을 3개 심은 경우
            if(cost < minCost) // 최소 비용 갱신
                minCost = cost;
            return;
        }

        // (2, 2)부터 (n-1, n-1)까지 탐색
        for(int i=2; i<n; i++) {
            for(int j=2; j<n; j++) {
                boolean check1 = true;
                for(int a=0; a<4; a++) {
                    int nx = i + dx[a];
                    int ny = j + dy[a];
                    // 현재 위치와 상하좌우 위치가 방문 가능한지 확인
                    if (visited[nx][ny] || visited[i][j])
                        check1 = false;
                }

                if(check1) { // 방문 가능하면
                    boolean check = false;
                    visited[i][j] = true; // 현재 위치 방문 처리
                    int c = garden[i][j]; // 현재 위치 비용

                    // 상하좌우 위치의 방문 처리 및 비용 계산
                    for(int a=0; a<4; a++) {
                        int nx = i + dx[a];
                        int ny = j + dy[a];
                        if(!visited[nx][ny]) {
                            check = true;
                            visited[nx][ny] = true;
                            c += garden[nx][ny];
                        }
                    }

                    if(check) { // 씨앗을 심을 수 있는 위치이면
                        dfs(flower+1, cost+c); // 다음 꽃 심기
                    }

                    // 방문 처리 원상 복구 (백트래킹)
                    visited[i][j] = false;
                    for(int a=0; a<4; a++) {
                        int nx = i + dx[a];
                        int ny = j + dy[a];
                        visited[nx][ny] = false;
                    }
                }

            }
        }
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        garden = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                // 비용 정보 저장
                garden[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(minCost);
    }
}
