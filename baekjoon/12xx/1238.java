import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m; // 마을 수, 간선 수
    static int x; // 파티가 열리는 마을
    static List<Node>[] list, reverseList; // 정방향 및 역방향 인접 리스트
    static long[] distToX, distFromX; // X로 가는 거리와 X에서 오는 거리
    static final long INF = Long.MAX_VALUE; // 무한대 값

    static class Node implements Comparable<Node> {
        int vertex; // 도착 마을
        long time; // 걸리는 시간

        public Node(int vertex, long time) { // 생성자
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            // 시간 짧은 순으로 정렬
            if(time > o.time) return 1;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        // 정방향 및 역방향 인접리스트 초기화
        list = new List[n+1];
        reverseList = new List[n+1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
            reverseList[i] = new ArrayList<>();
        }

        distToX = new long[n+1];
        distFromX = new long[n+1];
        Arrays.fill(distToX, INF);
        Arrays.fill(distFromX, INF);

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, time)); // 정방향
            reverseList[end].add(new Node(start, time)); // 역방향
        }

        // 1. X에서 각 마을로 가는 최단 거리 구하기 (정방향 리스트 사용)
        dijkstra(list, distFromX, x);

        // 2. 각 마을에서 X로 가는 최단 거리 구하기 (역방향 리스트 사용)
        dijkstra(reverseList, distToX, x);

        // 각 마을에서 왕복 시간 계산 후 최댓값 구하기
        long max = 0L;
        for(int i=1; i<=n; i++) {
            long sum = distToX[i] + distFromX[i]; // 왕복 시간
            if(sum > max) max = sum; // 최댓값 갱신
        }
        System.out.println(max);
    }

    // 다익스트라 알고리즘
    public static void dijkstra(List<Node>[] graph, long[] dist, int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        dist[start] = 0; // 출발지의 최단 거리는 0으로 설정
        q.offer(new Node(start, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (dist[current.vertex] < current.time) continue; // 이미 처리된 노드이면 무시

            // 현재 노드와 연결된 다른 인접 노드 확인
            for (Node next : graph[current.vertex]) {
                if (dist[next.vertex] > dist[current.vertex] + next.time) {
                    dist[next.vertex] = dist[current.vertex] + next.time;
                    q.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }
}
