import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static int[][] time;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 1});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<4; i++) {
                int x = dx[i] + cur[0];
                int y = dy[i] + cur[1];
                if(x > 0 && x<=N && y > 0 && y <= M && graph[x][y] == 1) {
                    q.offer(new int[]{x, y});
                    graph[x][y] = 0;
                    time[x][y] = time[cur[0]][cur[1]] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        graph = new int[N+1][M+1];
        time = new int[N+1][M+1];
        time[1][1] = 1;
        for(int i=1; i<=N; i++) {
            String info = br.readLine();
            for(int j=1; j<=M; j++) {
                graph[i][j] = Character.getNumericValue(info.charAt(j-1));
            }
        }

        bfs();
        System.out.println(time[N][M]);
    }
}
