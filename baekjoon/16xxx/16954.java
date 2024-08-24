import java.io.*;
import java.util.*;

public class Main {
    static char[][] board = new char[8][8]; // 체스판 배열
    static boolean[][] visited; // 방문 여부 확인하는 배열
    static Queue<int[]> q = new ArrayDeque<>(); // bfs에서 사용할 큐 선언

    // 상하좌우, 대각선, 제자리
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1, 0};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1, 0};

    static void bfs() {
        while(!q.isEmpty()) { // 큐가 빌 때까지
            int size = q.size();

            // 현재 큐에 있는 모든 상태를 처리
            for(int i=0; i<size; i++) {
                int[] c = q.poll();

                // 1. 목표지점에 도달하면 1을 출력하고 종료
                if(c[0]==0 && c[1]==7) {
                    System.out.println(1);
                    return;
                }

                // 2. 캐릭터를 상하좌우 또는 대각선 또는 제자리 이동 시도
                for(int j=0; j<9; j++) {
                    int nx = c[0] + dx[j];
                    int ny = c[1] + dy[j];
                    // 체스판 범위 내에 있고
                    if(nx>=0 && nx<8 && ny>=0 && ny<8) {
                        // 벽이 아니고, 방문하지 않은 경우
                        if(board[nx][ny]=='.' && !visited[nx][ny]) {
                            visited[nx][ny] = true; // 방문 처리
                            q.add(new int[]{nx, ny}); // 큐에 추가
                        }
                    }
                }
            }

            // 3. 더 이상 이동할 곳이 없으면
            if(q.isEmpty()) {
                // 0을 출력하고 종료
                System.out.println(0);
                return;
            }

            // 4. 벽을 한 칸 아래 행으로 이동
            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    if(board[i][j]=='#') {
                        board[i][j] = '.';
                        if(i+1<8) {
                            board[i+1][j] = '#';
                        }
                    }
                }
            }

            // 5. 벽이 내려와서 캐릭터가 있는 위치에 도달하면
            for(int[] pos : q) {
                if(board[pos[0]][pos[1]] == '#') {
                    // 0을 출력하고 종료
                    System.out.println(0);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<8; i++) {
            String s = br.readLine();
            for(int j=0; j<8; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        q.add(new int[]{7, 0}); // 시작 위치를 큐에 추가
        visited = new boolean[8][8]; // 방문 확인 배열 초기화
        bfs();
    }
}
