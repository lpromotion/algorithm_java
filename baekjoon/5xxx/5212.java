import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        for(int i=0; i<r; i++) {
            String input = br.readLine();
            for(int j=0; j<c; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                int cnt = 0; // 인접한 바다 개수

                if(map[i][j] == 'X') { // 땅인 경우
                    // 인접한 상하좌우 탐색
                    for(int k=0; k<4; k++) {
                        int nx = dx[k] + i;
                        int ny = dy[k] + j;

                        // 현재 땅이 지도의 맨위 또는 맨아래에 있으면
                        if (nx == -1 || nx == r) cnt++;
                        // 현재 땅이 지도의 맨왼쪽 또는 맨오른쪽에 있으면
                        if (ny == -1 || ny == c) cnt++;
                        // 인접한 위치가 지도 범위 내에 있고 바다이면
                        if(nx > -1 && nx < r && ny > -1 && ny < c) {
                            if(map[nx][ny] == '.') cnt++;
                        }
                    }
                }

                // 인접한 바다 개수가 3 이상이면 해당 위치 저장
                if(cnt >= 3) q.offer(new int[]{i, j});
            }
        }

        // 저장한 위치 바다로 변경
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            map[pos[0]][pos[1]] = '.';
        }

        // 출력 시 필요한 범위 설정
        int minX = r, minY = c, maxX = 0, maxY = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(map[i][j] == 'X') {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        // 설정한 범위로 지도 출력
        for(int i=minX; i<=maxX; i++) {
            for(int j=minY; j<=maxY; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
