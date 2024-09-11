import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 내림차순 정렬의 우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        // N-1번 큐의 맨 앞의 값을 제거
        for(int i=0; i<n-1; i++) {
            pq.poll();
        }

        // N번째의 값을 출력
        System.out.println(pq.poll());
    }
}
