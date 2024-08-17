import java.io.*;
import java.util.*;

public class Main {
    static int n, k; // 수빈과 동생의 위치
    static int[] time = new int[100001]; // 위치에 대한 시간을 저장
    static int minTime = Integer.MAX_VALUE; // 최소 시간
    static int count = 0; // 방법의 수

    static void bfs(int s) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s); // 시작 위치 큐에 추가

        while (!q.isEmpty()) {
            int cur = q.poll(); // 현재 위치를 꺼냄

            if(cur == k) { // 현재 위치가 동생의 위치일 경우
                if(time[cur] < minTime) { // 최소 시간인지 확인
                    minTime = time[cur]; // 최소 시간 갱신
                    count = 1; // count를 1로 초기화
                } else if(time[cur] == minTime) { // 동일한 최소 시간이 나오면
                    count++; // couht 증가
                }
            }

            int next[] = {cur-1, cur+1, cur*2}; // 이동할 수 있는 위치
            for(int i=0; i<3; i++) {
                // 다음 위치가 범위 내에 있는 경우
                if(next[i] >= 0 && next[i] <= 100000) {
                    // 방문하지 않은 위치이거나 동일한 시간에 도달할 수 있는 경우
                    if(time[next[i]] == 0 || time[next[i]] == time[cur] + 1) {
                        q.add(next[i]); // 다움 위치 큐에 추가
                        time[next[i]] = time[cur] + 1; // 다음 위치에 {현재까지의 시간 + 1}을 저장
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n); // 수빈의 시작 위치로 bfs 호출
        System.out.println(minTime+"\n"+count);
    }
}
