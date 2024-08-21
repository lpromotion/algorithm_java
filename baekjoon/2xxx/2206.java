import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 행, 열 개수
    static int[][] map; // 맵 정보 저장
    static int[][][] visited; // 최단 거리 저장
    static int minDis = Integer.MAX_VALUE; // 최단 거리 저장

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 1, 0}); // (1, 1)에서 벽을 부수지 않은 상태(0)로 시작
        visited[1][1][0] = 1; // (1, 1) 방문 처리 및 거리 1로 초기화

        while (!q.isEmpty()) {
            int[] c = q.poll(); // 현재 위치와 벽 부순 상태를 가져옴
            int x = c[0], y = c[1], z = c[2];

            if(x==n && y==m) { // 목표지점에 도달한 경우
                minDis = visited[x][y][z]; // 목표지점까지의 최단 거리 저장
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                if(nx>0 && nx<=n && ny>0 && ny<=m) { // 맵의 범위 내에 있을 경우
                    // 다음 위치가 벽이 아니고, 아직 방문하지 않은 경우
                    if(map[nx][ny]==0 && visited[nx][ny][z]==0) {
                        // 벽을 부수지 않은 상태(z=0) 또는 부순 상태(z=1)에서 
                        // 방문하지 않은 곳이라면 해당 위치까지의 거리를 갱신하고 큐에 추가
                        visited[nx][ny][z] = visited[x][y][z] + 1; // 거리 저장
                        q.add(new int[]{nx, ny, z});
                    }
                    // 다음 위치가 벽이고, 아직 벽을 부순 적이 없는 경우
                    if(map[nx][ny]==1 && z==0 && visited[nx][ny][1]==0) {
                        // 현재까지 벽을 부수지 않은 상태(z=0)에서
                        // 벽을 부수고 그 위치로 이동하는 경우를 처리
                        // 벽은 부순 상태(z=1)로 해당 위치까지의 거리를 갱신하고 큐에 추가
                        visited[nx][ny][1] = visited[x][y][z] + 1;
                        q.add(new int[]{nx, ny, 1});
                    }
                }

            }
        }

        minDis = -1;
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        map = new int[n+1][m+1];
        visited = new int[n+1][m+1][2];
        for(int i=1; i<=n; i++) {
            String input2 = br.readLine();
            for(int j=1; j<=m; j++) {
                map[i][j] = input2.charAt(j-1) - '0';
            }
        }

        bfs();
        System.out.println(minDis);
    }
}
