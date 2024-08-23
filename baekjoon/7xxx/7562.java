import java.io.*;
import java.util.*;

public class Main {
    static int l; // 체스판 한 변의 길이
    static int[][] board; // 체스판
    static int[][] dis; // 이동 거리
    static int[] goal; // 목표 위치

    // 나이트 이동 가능 위치
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y}); // 시작 위치 큐에 추가
        board[x][y] = 1; // 시작 위치의 이동 거리 1로 설정

        while(!q.isEmpty()) {
            int[] c = q.poll(); // 현재 위치 큐에서 꺼내기
            if(c[0]==goal[0] && c[1]==goal[1]) { // 목표 지점에 도달하면
                // 해당 위치의 이동 거리를 출력
                System.out.println(board[c[0]][c[1]]-1);
                return;
            }
            for(int i=0; i<8; i++) { // 나이트의 이동 가능한 칸 탐색
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 다음 위치가 체스판 범위 내에 있을 경우
                if(nx>=0 && nx<l && ny>=0 && ny<l) {
                    // 아직 방문 하지 않은 경우
                    if(board[nx][ny]==0) {
                        board[nx][ny] = board[c[0]][c[1]] + 1; // 거리 계산
                        q.add(new int[]{nx, ny}); // 큐에 추가
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            l = Integer.parseInt(br.readLine());
            board = new int[l][l]; // 체스판 초기화
            dis = new int[l][l]; // 체스판 초기화
            // 시작 위치 입력받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            // 목표 위치 입력받기
            st = new StringTokenizer(br.readLine());
            int gx = Integer.parseInt(st.nextToken());
            int gy = Integer.parseInt(st.nextToken());
            goal = new int[]{gx, gy};
            
            bfs(sx, sy); // 시작 위치에서 BFS 수행
        }
    }
}
