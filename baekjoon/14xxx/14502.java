import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 연구실의 세로, 카로 크기
    static int[][] lab; // 연구실 정보
    static boolean[][] virus; // 바이러스 체크
    static int maxSafeZone = 0; // 안전 영역 크기의 최댓값

    static int[] dx = {-1, 1, 0, 0}; // 좌우
    static int[] dy = {0, 0, -1, 1}; // 상하

    static void dfs(int count) { // 벽의 개수를 매개변수로
        if(count == 3) { // 벽이 3개인 경우
            bfs(); // bfs 호출
            return;
        }

        // 연구소의 모든 칸 탐색
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(lab[i][j] == 0) { // 빈 칸이면
                    lab[i][j] = 1; // 벽 세움
                    dfs(count+1); // 벽 1 증가시켜 재귀 호출
                    lab[i][j] = 0; // 벽 취소 (백트래킹)
                }
            }
        }
    }

    static void bfs() { // 바이러스 퍼트리고, 안전 구역 계산
        Queue<int[]> q = new ArrayDeque<>();
        virus = new boolean[n+1][m+1];

        // 현재 연구소 상태를 임시 배열에 복사
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(lab[i][j] == 2) {
                    q.add(new int[]{i, j});
                    virus[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 큐에서 현재 위치 꺼냄
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                // 다음 위치가 이동 범위에 속하고, 빈 칸일 경우
                if(nx>0 && nx<=n && ny>0 && ny<=m &&
                        lab[nx][ny]==0 && !virus[nx][ny]) {
                    virus[nx][ny] = true; // 바이러스 확산
                    q.add(new int[]{nx, ny}); // 큐에 다음 위치 추가
                }
            }
        }

        int count = 0; // 안전 구역 개수 저장
        // 안전 구역 계산
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(lab[i][j] == 0 && !virus[i][j]) // 연구소에서 빈 칸이면
                    count++; // 안전 구역 증가
            }
        }
        maxSafeZone = Math.max(maxSafeZone, count); // 최댓값 갱신
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n+1][m+1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(maxSafeZone);
    }
}
