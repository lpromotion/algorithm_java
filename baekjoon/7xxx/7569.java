import java.io.*;
import java.util.*;

public class Main {
    static int m, n, h; // 상자의 가로&세로 칸 수, 상자 개수
    static int[][][] box; // 토마토 정보를 저장
    static int[][][] day; // 일자 저장
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dh = {-1, 1, 0, 0, 0, 0}; // 앞, 뒤
    static int[] dx = {0, 0, -1, 1, 0, 0}; // 위, 아래
    static int[] dy = {0, 0, 0, 0, -1, 1}; // 왼쪽, 오른쪽

    static void bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<6; i++) { // 인접한 여섯 방향 탐색
                int nh = cur[0] + dh[i];
                int nx = cur[1] + dx[i];
                int ny = cur[2] + dy[i];
                // 상자 범위 내에 있는 경우
                if(nh>-1 && nh<h && nx>0 && nx<=n && ny>0 && ny<=m) {
                    // 토마토가 아직 익지 않고, 방문하지 않은 경우
                    if(box[nh][nx][ny] == 0 && day[nh][nx][ny] == -1) {
                        day[nh][nx][ny] = day[cur[0]][cur[1]][cur[2]] + 1; // 일자 계산
                        box[nh][nx][ny] = 1; // 익음 처리
                        q.add(new int[]{nh, nx, ny}); // 큐에 다음 위치 추기
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        box = new int[h][n+1][m+1];
        day = new int[h][n+1][m+1];
        for(int i=0; i<h; i++) {
            for(int j=1; j<=n; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=1; k<=m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    day[i][j][k] = -1; // 일자 -1로 초기화
                    if(box[i][j][k] == 1) { // 토마토가 익은 경우
                        q.add(new int[]{i, j, k}); // 해당 위치를 큐에 추가
                        day[i][j][k] = 0; // 해당 위치의 일자를 0으로 설정
                    }
                }
            }
        }

        bfs();

        boolean check = true; // 토마토가 모두 익지 못할 경우를 저장
        int minDay = -1; // 최소 일자
        h: for(int i=0; i<h; i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=m; k++) {
                    if(box[i][j][k] == 0) { // 상자에 안 익은 토마토가 남아있는 경우
                        check = false;
                        break h; // 최소 일자 구하는 for문을 모두 종료
                    }
                    if(day[i][j][k] > minDay) {
                        // day 배열 중 가장 큰 일자를 저장
                        minDay = day[i][j][k];
                    }
                }
            }
        }

        if(!check) System.out.println(-1);
        else System.out.println(minDay);
    }
}
