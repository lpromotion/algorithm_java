import java.io.*;

public class Main {
    static int n, e;
    static int[][] adjMatrix; // 인접 행렬
    static boolean[] visited; // 방문 여부 체크
    static int count = 0; // 감염된 컴퓨터 수 (결과값)

    static void dfs(int s) {
        visited[s] = true; // 현재 노드 방문 처리

        for(int i=1; i<=n; i++) {
            // 현재 컴퓨터와 연결되어 있고, 아직 방문하지 않은 경우
            if(adjMatrix[s][i] == 1 && !visited[i]){
                count++; // 결과값 증가
                dfs(i); // 해당 컴퓨터로 재귀 호출
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());
        adjMatrix = new int[n+1][n+1];
        visited = new boolean[n+1];
        for(int i=0; i<e; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            // 무방향 그래프 -> 양쪽 모두 1로 설정
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }

        dfs(1); // 1부터 DFS 시작
        System.out.println(count);
    }
}
