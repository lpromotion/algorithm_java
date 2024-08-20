import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 친구 수, 친구 관계 수
    static List<Integer>[] friend; // 친구 관계 저장하는 인접리스트
    static boolean[] visited; // 방문 여부 확인하는 배열
    static boolean possible; // 5명의 친구 관계가 가능한지 확인

    static void dfs(int s, int depth) {
        if(depth == 5) { // 친구 관계 5명이 나오면
            possible = true;
            return; // 종료
        }

        for(int next : friend[s]) { // 인접한 노드 탐색
            if(!visited[next]) { // 아직 방문하지 않은 노드라면
                visited[next] = true; // 해당 노드 방문 처리
                dfs(next, depth+1); // 해당 노드를 다음 깊이로 재귀 호출
                visited[next] = false; // 방문 처리 취소 (백트래킹)
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        friend = new ArrayList[n];
        for(int i=0; i<n; i++) { // 인접 리스트 초기화
            friend[i] = new ArrayList<>();
        }
        visited = new boolean[n];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 양방향으로 친구 관계 저장
            friend[a].add(b);
            friend[b].add(a);
        }

        for(int i=0; i<n; i++) { // 모든 노트를 탐색
            visited[i] = true; // 시작 노드 방문 처리
            dfs(i, 1); // 깊이 1로 탐색 시작
            visited[i] = false; // 시작 노드 방문 처리 취소

            if(possible) break; // 5명의 친구 관게를 찾을 경우 종료
        }

        System.out.println(possible ? 1 : 0);
    }
}
