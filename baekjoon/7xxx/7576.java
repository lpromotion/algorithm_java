import java.io.*;
import java.util.*;

public class Main {
    static int m, n; // 상자의 가로, 세로 칸의 수
    static int[][] box; // 상자
    static Queue<int[]> q = new ArrayDeque<>(); // 익은 토마토 저장할 큐

    static int[] dx = {-1, 1, 0, 0}; // 왼쪽, 오른쪽
    static int[] dy = {0, 0, -1, 1}; // 앞, 뒤

    static int bfs() {
        while(!q.isEmpty()) { // 큐가 빌 때까지
            int[] pos = q.poll(); // 큐에서 값 꺼냄
            int x = pos[0];
            int y = pos[1];
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx>=0 && nx<n && ny>=0 && ny<m) { // 이동 가능한 위치일 때
                    if(box[nx][ny] == 0) { //  다음 위치의 토마토가 익히지 않은 토마토일 때
                        box[nx][ny] = box[x][y] + 1; // 날짜 세기 위해 -> 현재 위치 값 + 1
                        q.add(new int[]{nx, ny}); // 큐에 다음 위치 추가
                    }
                }
            }
        }

        int day = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(box[i][j] == 0) { // 안 익은 토마토가 있는지 확인
                    return -1; // 결과값 1로 설정
                }
                day = Math.max(day, box[i][j]);
            }
        }

        if (day == 1) { // 처음부투 토마토가 모두 익어있으면
            return 0; // 결과값 0으로 설정
        } else { // 두 가지 조건에 모두 충족하지 않으면
            return day - 1; // 최종 날짜 설정
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) // 익은 토마토일 경우
                    q.add(new int[] {i, j}); // 해당 위치를 큐에 추가
            }
        }

        System.out.println(bfs());
    }
}
