import java.io.*;
import java.util.*;

public class Main {
    static int n; // 지도의 크기
    static int[][] map; // 지도 정보 저장
    static int[][] ground; // 육지 정보 저장
    static int[][] dis; // 거리 정보 저장
    static int minDis = Integer.MAX_VALUE; // 가장 짧은 다리 길이

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        ground = new int[n][n];
        dis = new int[n][n];
        // 지도 정보 입력 및 초기화
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dis[i][j] = -1; // 거리 정보를 -1로 초기화
            }
        }

        int num = 1; // 섬 번호
        // 지도를 순회하며 섬을 찾고 번호 부여
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]==1) { // 육지일 경우
                    bfs1(i, j, num++); // BFS를 통해 섬 구분
                }
            }
        }

        // 각 섬에서 다른 섬까지의 최소 다리 길이 계산
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // 육지이고 아직 방문하지 않은 경우
                if(ground[i][j]>0 && dis[i][j]==-1) {
                    bfs2(i, j, ground[i][j]); // BFS를 통해 다리 길이 계산
                }
            }
        }

        System.out.println(minDis);
    }

    static void bfs1(int x, int y, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        map[x][y] = 0; // 현재 위치 방문 처리
        ground[x][y] = num; // 현재 위치에 섬 번호 부여
        while(!q.isEmpty()) {
            int[] c = q.poll();
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 지도 범위 내에 있고, 육지인 경우
                if(nx>=0 && nx<n && ny>=0 && ny<n && map[nx][ny]==1) {
                    map[nx][ny] = 0; // 방문 처리
                    ground[nx][ny] = num; // 섬 번호 부여
                    q.add(new int[]{nx, ny}); // 다음 위치 큐에 추가
                }
            }
        }
    }

    static void bfs2(int x, int y, int num) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        dis[x][y] = 0; // 시작 위치의 거리 0으로 초기화
        while(!q.isEmpty()) {
            int[] c = q.poll();
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 다음 위치가 지도 범위 내에 있는 경우
                if(nx>=0 && nx<n && ny>=0 && ny<n) {
                    // 1. 같은 육지이면 패스
                    if(ground[nx][ny]==num) continue;
                    // 2. 바다이고, 아직 방문하지 않은 경우
                    if(ground[nx][ny]==0 && dis[nx][ny]==-1){
                        dis[nx][ny] = dis[c[0]][c[1]] + 1; // 거리 계산 & 방문 처리
                        q.add(new int[]{nx, ny}); // 다음 위치 큐에 추가
                    }
                    // 3. 바다이고, 이미 방문한 경우 (더 짧은 경로로 도달할 수 있을 경우)
                    else if(ground[nx][ny]==0 && dis[nx][ny] > dis[c[0]][c[1]]+1){
                        dis[nx][ny] = dis[c[0]][c[1]] + 1; // 거리 계산 & 방문 처리
                        q.add(new int[]{nx, ny}); // 다음 위치 큐에 추가
                    }
                    // 4. 다른 육지인 경우
                    if(ground[nx][ny]!=num && ground[nx][ny]>0) {
                        int length = dis[c[0]][c[1]]; // 현재 위치까지의 거리
                        minDis = Math.min(length, minDis); // 최소 다리 길이 계산
                    }
                }
            }
        }
    }
}
