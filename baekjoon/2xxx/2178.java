import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 미로의 행, 열의 개수
    static int[][] miro; // 미로 정보 저장
    static int[][] move; // 각 위치까지 이동하는 최소 칸 수 저장

    // 상하좌우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b}); // 시작 위치를 큐에 추가
        miro[a][b] = 0; // 시작 위치 방문 처리

        while(!q.isEmpty()) {
            int[] cur = q.poll(); // 현재 위치 꺼내기
            for(int i=0; i<4; i++) { // 현재 위치 상하좌우 탐색
                // 새로운 x, y 좌표
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                if(nx>0 && nx<=n && ny>0 && ny<=m) { // 새로운 위치가 미로 범위에 속하고
                    if(miro[nx][ny] == 1) { // 방문하지 않은 경우
                        miro[nx][ny] = 0; // 방문 처리
                        q.add(new int[]{nx, ny}); // 큐에 추가
                        move[nx][ny] = move[cur[0]][cur[1]] + 1; // 현재 위치까지의 이동 칸 수 + 1
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
        miro = new int[n+1][m+1];
        move = new int[n+1][m+1];
        // 미로 정보 입력받기
        for(int i=1; i<=n; i++) {
            String info = br.readLine();
            for(int j=1; j<=m; j++) {
                miro[i][j] = info.charAt(j-1) - '0'; // char -> int 변환
            }
        }

        move[1][1] = 1; // 시작 위치의 이동 칸 수를 1로 설정
        bfs(1, 1); // (1, 1)에서 BFS 시작
        System.out.println(move[n][m]); // 도착 지점의 최소 이동 칸 수
    }
}
