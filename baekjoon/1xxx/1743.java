import java.io.*;

public class Main {
    static boolean[][] food;
    static int n, m, k;
    static int maxFoodSize = 0;
    static int foodSize = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int i, int j) {
        food[i][j] = false;
        for(int t=0; t<4; t++) {
            int x = i + dx[t];
            int y = j + dy[t];
            if(x > 0 && x <= n && y > 0 && y <= m && food[x][y]) {
                foodSize++;
                backTracking(x, y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);
        food = new boolean[n+1][m+1];
        for(int i=0; i<k; i++) {
            String[] info = br.readLine().split(" ");
            int x = Integer.parseInt(info[0]);
            int y = Integer.parseInt(info[1]);
            food[x][y] = true;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(food[i][j]) {
                    foodSize = 1;
                    dfs(i, j);
                }
                if(maxFoodSize < foodSize) maxFoodSize = foodSize;
            }
        }

        System.out.println(maxFoodSize);
    }
}
