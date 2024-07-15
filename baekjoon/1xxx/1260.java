import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[][] arr;

    static int N,  M, V;

    static Queue<Integer> q = new LinkedList<>();

    static void DFS(int v) {
        visited[v] = true;
        sb.append(v).append(" ");
        for(int i=0; i<arr.length; i++) {
            if(arr[v][i] == 1 && !visited[i]) {
                DFS(i);
            }
        }
    }

    static void BFS(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        while(!q.isEmpty()) {
            int current = q.poll();
            sb.append(current).append(" ");
            for(int i=0; i<arr.length; i++) {
                if(arr[current][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1; arr[y][x] = 1;
        }

        visited = new boolean[N+1];
        DFS(V); sb.append("\n");

        visited = new boolean[N+1];
        BFS(V);

        System.out.println(sb);
        br.close();;
    }
}
