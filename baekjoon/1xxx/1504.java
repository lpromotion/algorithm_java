import java.io.*;
import java.util.*;

public class Main {
    static int n, e; // 정점 개수, 간선 개수
    static int v1, v2; // 반드시 거쳐야 하는 두 개의 정점
    static List<Node>[] list; // 인접리스트 형식으로 그래프 저장
    static int[] dist; // 최단 거리 저장
    static final int INF = 200000000;

    static class Node implements Comparable<Node> {
        int vertex; // 정점
        int distance; // 거리

        public Node(int vertex, int distance) { // 생성자
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            // 거리 짧은 순으로 정렬
            if(distance > o.distance) return 1;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new List[n+1]; // 리스트 초기화
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        dist = new int[n+1]; // 최단 거리 배열 초기화
        Arrays.fill(dist, INF); // 무한대 값으로 초기화

        for(int i=0; i<e; i++) { // 그래프 정보 저장
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            // 양방향으로 저장
            list[start].add(new Node(end, distance));
            list[end].add(new Node(start, distance));
        }

        // 반드시 거쳐야 하는 두 정점 저장
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 두 가지 경로를 구함
        // way1 : 1 → v1 → v2 → N
        // way2 : 1 → v2 → v1 → N
        int way1, way2;
        dijkstra(1); // 1번 정점에서 시작하는 최단 경로 구하기
        way1 = dist[v1]; way2 = dist[v2];
        Arrays.fill(dist, INF); // 무한대 값으로 초기화

        dijkstra(v1); // v1번 정점에서 시작하는 최단 경로 구하기
        way1 += dist[v2]; way2 += dist[n];
        Arrays.fill(dist, INF); // 무한대 값으로 초기화

        dijkstra(v2); // v2번 정점에서 시작하는 최단 경로 구하기
        way1 += dist[n]; way2 += dist[v1];

        if(way1 >= INF && way2 >= INF)
            System.out.println("-1");
        else System.out.println(Math.min(way1, way2));
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[start] = 0; // 시작 위치 최단 거리 0으로 설정
        q.offer(new Node(start, 0)); // 시작 위치 큐에 추가
        boolean[] visited = new boolean[n+1]; // 방문 여부 체크

        while(!q.isEmpty()) {
            Node current = q.poll();

            if(visited[current.vertex]) continue;;
            visited[current.vertex] = true;

            for(Node next : list[current.vertex]) {
                if(dist[next.vertex] > dist[current.vertex] + next.distance) {
                    // 더 짧은 경로를 찾으면 갱신 & 큐에 추가
                    dist[next.vertex] = dist[current.vertex] + next.distance;
                    q.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }
}
