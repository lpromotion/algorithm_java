import java.io.*;

public class Main {
    static boolean[][] land;
    static int m, n, k, x, y;
    static int count;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int i, int j) {
        for(int t=0; t<4; t++) {
            int cx = dx[t] + i;
            int cy = dy[t] + j;
            if(cx >= 0 && cx < m && cy >= 0 && cy < n && land[cx][cy]) {
                land[cx][cy] = false;
                dfs(cx, cy);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            String[] info1 = br.readLine().split(" ");
            m = Integer.parseInt(info1[0]);
            n = Integer.parseInt(info1[1]);
            k = Integer.parseInt(info1[2]);
            land = new boolean[m][n];
            for(int i=0; i<k; i++) {
                String[] info2 = br.readLine().split(" ");
                x = Integer.parseInt(info2[0]);
                y = Integer.parseInt(info2[1]);

                land[x][y] = true;
            }

            count = 0;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(land[i][j]) {
                        land[i][j] = false;
                        count++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println(count);
        }
    }
}
