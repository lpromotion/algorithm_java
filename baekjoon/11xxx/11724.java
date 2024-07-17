import java.io.*;

public class Main {
    static int n, k;
    static boolean[] visited;
    static boolean[][] graph;

    static void dfs(int index) {
        visited[index] = true;
        for(int i=1; i<=n; i++) {
            if(graph[index][i] && !visited[i])
                dfs(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        graph = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        for(int i=0; i<k; i++) {
            str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            graph[x][y] = graph[y][x] = true;
        }

        int count = 0;
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }
}
