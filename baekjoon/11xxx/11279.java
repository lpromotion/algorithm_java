import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 최대 힙 구현을 위해 우선순위 큐를 내림차순 정렬으로 설정
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x==0) { // x가 0이면
                // 큐가 비었을 경우 0을 출력
                if(pq.isEmpty()) sb.append("0\n");
                    // 아닌 경우 가장 큰 값 출력 & 제거
                else sb.append(pq.poll()+"\n");
            } else { // x가 자연수이면
                pq.offer(x); // x를 큐에 추가
            }
        }

        System.out.println(sb);
    }
}
