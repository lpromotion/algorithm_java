import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 지도의 세로, 가로 크기
    static int[][] map; // 지도 정보 저장
    static int[][] answer; // 방문 여부 확인 & 거리 저장
    static int x, y; // 목표지점 위치

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b}); // 시작 위치 큐에 추가

        while (!q.isEmpty()) {
            int[] c = q.poll(); // 현재 위치 꺼내기
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 지도 범위 내에 있는지 확인
                if(nx>0 && nx<=n && ny>0 && ny<=m) {
                    // 갈 수 있는 방이고, 아직 방문하지 않은 경우
                    if (map[nx][ny]==1 && answer[nx][ny]==-1) {
                        q.add(new int[]{nx, ny}); // 다음 위치 큐에 추가
                        answer[nx][ny] = answer[c[0]][c[1]] + 1; // 거리 계산
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        answer = new int[n+1][m+1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    x = i; y = j; // 목표 지점 저장
                }
                if(map[i][j] == 1) { // 갈 수 있는 땅일 경우
                    answer[i][j] = -1; // -1로 초기화
                }
            }
        }

        bfs(x, y); // 목표지점에서 모든 위치를 탐색

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                sb.append(answer[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
