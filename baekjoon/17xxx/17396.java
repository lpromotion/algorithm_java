import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int end; // 도착 분기점
        long time; // 걸리는 시간

        public Node(int end, long time) { // 생성자
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            // 시간 짧은 순으로 정렬
            if(time > o.time) return 1;
            else return -1;
        }
    }
    
    static int n, m; // 분기점의 수, 분기점을 잇는 길의 수
    static List<Node>[] list; // 인접리스트로 그래프 표현
    static long[] dist; // 최단 거리 저장하는 배열
    static int[] visible; // 시야 정보 저장하는 배열
    static final long INF = Long.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 그래프를 인접리스트로 초기화
        list = new List[n];
        for(int i=0; i<n; i++) {
            list[i] = new ArrayList<>();
        }

        // dist 배열 무한대로 초기화 (최단 거리 계산 위해)
        dist = new long[n];
        Arrays.fill(dist, INF);

        st = new StringTokenizer(br.readLine());
        visible = new int[n];
        for(int i=0; i<n; i++) { // 시야 정보 입력받기
            visible[i] = Integer.parseInt(st.nextToken());
        }
        
        // 간선 정보 입력받기
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            // 양방향으로 정보 저장
            list[start].add(new Node(end, time));
            list[end].add(new Node(start, time));
        }
        
        dijkstra(0); // 시작점 0에서 출발
        if(dist[n-1] == INF) System.out.println(-1);
        else System.out.println(dist[n-1]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] visited = new boolean[n]; // 방문 여부 체크 배열
        q.offer(new Node(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Node current = q.poll(); // 현재 최단 경로 노드 꺼내기

            // 이미 방문한 노드면 건너뛰기
            // 가장 짧은 경로를 찾은 노드는 다시 탐색하지 않도록 (성능최적화)
            if (visited[current.end]) continue;
            visited[current.end] = true; // 방문 처리

            // 현재 노드에서 인접한 노드들 탐색
            for (Node next : list[current.end]) {
                // 마지막 노드가 아니고, 적의 시야에 보이면 건너뛰기
                if (next.end != n - 1 && visible[next.end] == 1) continue;

                // 더 짧은 경로가 있을 경우 갱신
                if (dist[next.end] > dist[current.end] + next.time) {
                    dist[next.end] = dist[current.end] + next.time; // 최단 경로 갱신
                    q.offer(new Node(next.end, dist[next.end])); // 큐에 추가
                }
            }
        }
    }

}
