import java.io.*;

public class Main {
    static int m, n; // 배추밭 가로, 세로 길이
    static int k; // 배추가 심어져 있는 위치의 개수
    static boolean[][] ground; // 배추밭 정보 (배추가 있으면 true)
    static int count; // 필요한 배추흰지렁이 수

    static int[] moveX = {-1, 1, 0, 0}; // 왼쪽, 오른쪽
    static int[] moveY = {0, 0, -1, 1}; // 위, 아래

    static void dfs(int x, int y) {
        ground[x][y] = false; // 현재 위치 방문 체크

        for(int i=0; i<4; i++) { // 현재 위치의 상하좌우를 탐색
            int cx = moveX[i] + x;
            int cy = moveY[i] + y;
            if(cx>=0 && cx<m && cy>=0 && cy<n // 해당 위치가 유효하고
                && ground[cx][cy]) { // 배추가 있는 경우
                dfs(cx, cy); // 해당 위치로 재귀 호출
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            String[] inputA = br.readLine().split(" ");
            m = Integer.parseInt(inputA[0]);
            n = Integer.parseInt(inputA[1]);
            k = Integer.parseInt(inputA[2]);
            ground = new boolean[m][n];
            for(int i=0; i<k; i++) { // 배추밭 정보 입력받기
                String[] inputB = br.readLine().split(" ");
                int a = Integer.parseInt(inputB[0]);
                int b = Integer.parseInt(inputB[1]);
                ground[a][b] = true; // 배추가 있으면 true로 설정
            }

            count = 0;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(ground[i][j]) { // 현재 위치를 방문하지 않았으면
                        count++; // 배추흰지렁이 수 증가
                        dfs(i, j); // 현재 위치로 dfs 호출
                    }
                }
            }

            System.out.println(count);
        }
    }
}
