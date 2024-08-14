import java.io.*;
import java.util.*;

public class Main {
    static int n; // 노드의 개수
    static ArrayList<Integer>[] list; // 노드 저장
    static boolean[] visited; // 방문 여부 체크
    static int[] parent; // 부모 노드 저장
    
    static void dfs(int s) {
        visited[s] = true; // 현재 노드 방문 처리
        for(int a : list[s]) { // 현재 노드와 연결된 노드 탐색
            if(!visited[a]) { // 해당 노드를 방문하지 않은 경우
                parent[a] = s; // 부모 노드 저장
                dfs(a); // 해당 노드로 재귀 호출
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        parent = new int[n+1];
        for(int i=0; i<=n; i++) { // list 초기화
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++) { // 연결된 정점 저장
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 인접리스트 형태로 노드 저장 (양방향)
            list[a].add(b);
            list[b].add(a);
        }
        
        for(int i=1; i<=n; i++) {
            // 방문하지 않은 노드일 경우 dfs 호출
            if(!visited[i]) dfs(i);
        }

        for(int i=2; i<=n; i++) {
            // 부모 노드 출력
            System.out.println(parent[i]);
        }
    }
}
