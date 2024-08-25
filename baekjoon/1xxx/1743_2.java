import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 통로의 세로, 가로 길이
    static int k; // 음식물 쓰레기 개수
    static int[][] load; // 쓰레기 정보 저장하는 통로 배열
    static boolean[][] visited; // 방문 여부 확인
    static int maxTrashSize = -1; // 가장 큰 음식물의 크기

    // 상하좌우 탐색 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y}); // 시작 위치를 큐에 추가
        visited[x][y] = true; // 시작 위치 방문 처리
        load[x][y] = 0; // 시작 위치 음식물 제거 처리
        int size = 1; // 음식물 크기

        while(!q.isEmpty()) {
            int[] c = q.poll(); // 현재 위치 큐에서 꺼내기
            for(int i=0; i<4; i++) { // 인접한 상하좌우 탐색
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 통로 범위 내에 있고, 음식물이 존재하고, 아직 방문하지 않은 경우
                if(nx>0 && nx<=n && ny>0 && ny<=m
                    && load[nx][ny]==1 && !visited[nx][ny]) {
                    load[nx][ny] = 0; // 음식물 제거 처리
                    visited[nx][ny] = true; // 방문 처리
                    size++; // 음식물 크기 1 증가
                    q.add(new int[]{nx, ny}); // 다음 위치 큐에 추가
                }
            }
        }

        return size; // 음식물 크기 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        load = new int[n+1][m+1];
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            load[x][y] = 1;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(load[i][j] == 1) { // 음식물이 존재하는 경우
                    visited = new boolean[n+1][m+1]; // 방문 배열 초기화
                    int trashSize = bfs(i, j); // BFS 호출 & 음식물 크기 저장
                    // 음식물 크기 최댓값 갱신
                    maxTrashSize = Math.max(trashSize, maxTrashSize);
                }
            }
        }

        System.out.println(maxTrashSize);
    }
}
