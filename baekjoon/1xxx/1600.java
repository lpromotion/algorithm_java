package lsj.study.algorithm_note.DFS_BFS;

import java.io.*;
import java.util.*;

public class BJ_1600 {
    static int k; // 말의 이동 방법 최대 개수
    static int w, h; // 격자판의 가로, 세로 길이
    static int[][] board; // 격자판 배열
    static boolean[][][] visited; // 방문 여부 확인 (가로, 세로, 말 이동방법 쓴 횟수)

    // 원숭이의 상하좌우 이동 방법
    static int[] dx1 = {-1, 1, 0, 0};
    static int[] dy1 = {0, 0, -1, 1};
    // 말의 이동 방법 (8가지)
    static int[] dx2 = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy2 = {-1, 1, -2, 2, -2, 2, -1, 1};


    static int bfs(int a, int b) {
        // 큐 생성 및 시작 위치 추가, 방문 처리
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b, 0, 0}); // x좌표, y좌표, 말 이동 횟수, 이동 횟수
        visited[a][b][0] = true;
        
        while (!q.isEmpty()) { // 큐가 빌 때까지
            int[] c = q.poll();
            int cx = c[0], cy = c[1];
            int horseMove = c[2], move = c[3];
            
            // 도착점에 도달한 경우 이동 횟수를 반환
            if(cx == h-1 && cy == w-1) {
                return move;
            }

            // 원숭이의 이동방법 사용할 경우 (상하좌우)
            for(int i=0; i<4; i++) {
                int nx1 = c[0] + dx1[i];
                int ny1 = c[1] + dy1[i];
                // 범위 내에 있고
                if(nx1>-1 && nx1<h && ny1>-1 && ny1<w) {
                    // 장애물이 아니고, 방문하지 않은 경우
                    if(board[nx1][ny1]==0 && !visited[nx1][ny1][horseMove]) {
                        visited[nx1][ny1][horseMove] = true;
                        q.add(new int[]{nx1, ny1, horseMove, move+1});
                    }
                }
            }

            // 말의 이동방법 사용할 경우 (최대 k번 가능)
            if(horseMove < k) {
                for(int i=0; i<8; i++) {
                    int nx2 = c[0] + dx2[i];
                    int ny2 = c[1] + dy2[i];
                    // 범위 내에 있고
                    if(nx2>-1 && nx2<h && ny2>-1 && ny2<w) {
                        // 장애물이 아니고, 해당 말 이동 횟수로 방문하지 않은 경우
                        if(board[nx2][ny2]==0 && !visited[nx2][ny2][horseMove+1]) {
                            visited[nx2][ny2][horseMove+1] = true;
                            q.add(new int[]{nx2, ny2, horseMove+1, move+1});
                        }
                    }
                }
            }
        }
        
        return -1; // 도달할 수 없는 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][w]; // 격자판 배열 초기화
        visited = new boolean[h][w][k+1]; // 말의 최대 이동 횟수가 k이므로 k+1 크기로 설정
        for(int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++) {
                // 격자판 정보 저장
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // (0, 0)에서 BFS 탐색 시작
        System.out.println(bfs(0, 0));

    }
}
