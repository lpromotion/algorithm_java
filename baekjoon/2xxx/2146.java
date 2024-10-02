import java.io.*;
import java.util.*;

public class Main {
    static int n; // 나라의 크기
    static int[][] map; // 주어진 맵 정보 저장
    static boolean[][] visited; // 방문 처리 배열
    static int minLen = Integer.MAX_VALUE; // 다리 최소 길이

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i=0; i<n; i++) { // 주어진 맴 정보 입력받고 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 0; // 섬 구분할 번호
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    bfs1(i, j, ++num);
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] != 0) {
                    visited = new boolean[n][n];
                    bfs2(i, j, map[i][j]);
                }
            }
        }

        System.out.println(minLen);
    }

    static void bfs1(int x, int y, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y}); // 시작위치 큐에 추가
        map[x][y] = num; // 섬 번호 부여
        visited[x][y] = true; // 방문 처리

        while(!q.isEmpty()) {
            int[] c = q.poll();

            for(int i=0; i<4; i++) { // 동서남북 탐색
                int nx = dx[i] + c[0];
                int ny = dy[i] + c[1];
                if(nx>=0 && nx<n && ny>=0 && ny<n) { // 맵 범위에 속하는 경우
                    if(map[nx][ny] == 0 || visited[nx][ny]) continue; // 바다이거나 방문한 경우 패스
                    map[nx][ny] = num; // 섬 번호 부여
                    visited[nx][ny] = true; // 방문 처리
                    q.add(new int[]{nx, ny}); // 큐에 추가
                }
            }
        }
    }

    static void bfs2(int x, int y, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y, 0}); // 시작위치, 거리
        visited[x][y] = true; // 방문 처리

        while(!q.isEmpty()) {
            int[] c = q.poll();

            // 현재 경로가 이미 최소 거리보다 길 경우 패스
            if(minLen < c[2]-1) return;

            // 바다가 아니고, 다른 섬에 도달한 경우
            if(map[c[0]][c[1]] != 0 && map[c[0]][c[1]] != num) {
                minLen = Math.min(minLen, c[2]-1); // 최솟값 갱신
            }

            for(int i=0; i<4; i++) { // 동서남북 탐색
                int nx = dx[i] + c[0];
                int ny = dy[i] + c[1];

                // 맵 범위에 속하고, 방문하지 않은 경우
                if(nx>=0 && nx<n && ny>=0 && ny<n) {
                    if(map[nx][ny] == num) continue; // 같은 섬이면 패스
                    if(!visited[nx][ny]) {
                        q.add(new int[]{nx, ny, c[2] + 1}); // 거리 1 증가시켜 큐에 추가
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
