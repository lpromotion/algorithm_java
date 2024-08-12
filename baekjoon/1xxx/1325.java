import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list; // 인접 리스트
    static boolean[] visited; // 방문 여부 체크
    static int[] trust; // 각 컴퓨터가 해킹할 수 있는 컴퓨터 수 저장
    static int N, M;

    static void bfs(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        visited[n] = true;

        while(!q.isEmpty()) {
            int num = q.poll();
            for(int i : list[num]) {
                if(visited[i]) continue;
                // 시작노드(n)가 현재노드(i)를 해킹할 수 있으면 신뢰도 증가시킴
                trust[n]++;
                visited[i] = true;
                q.add(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        trust = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>(); // 인접리스트 초기화
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[b].add(a); // 그래프 방향을 뒤집어서 저장
        }

        for(int i=1; i<=N; i++) { // 각 컴퓨터에 대해 BFS 수행
            Arrays.fill(visited, false); // visited 배열 초기화
            bfs(i);
        }

        int max = 0; // 최대 해킹 가능 컴퓨터 수 
        for(int i=1; i<=N; i++) {
            if(trust[i] > max) max = trust[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            // 최대 해킹 가능한 컴퓨터 번호 출력
            if(trust[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
