import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] house;
    static boolean[][] visited;
    static int count = 0;
    static int house_count = 0;

    static void dfs(int i, int j) {
        int[] dx = {-1, 1, 0, 0}; // 상하좌우 행 이동
        int[] dy = {0, 0, -1, 1}; // 상하좌우 열 이동

        for(int k=0; k<4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            if(x >= 0 && x < n && y >= 0 && y < n && house[x][y] == 1 && !visited[x][y]) {
                visited[x][y] = true;
                house_count++;
                dfs(x, y);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                house[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[n][n];
        ArrayList<Integer> house_counts = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(house[i][j] == 1 && !visited[i][j]) {
                    count++;
                    house_count = 1;
                    visited[i][j] = true;
                    dfs(i, j); // 상하좌우 검사
                    house_counts.add(house_count);
                }
            }
        }

        // 단지내 집의 수 오름차순 정렬
        Collections.sort(house_counts);
        System.out.println(count);
        for(int house_count : house_counts) {
            System.out.println(house_count);
        }
    }
}
