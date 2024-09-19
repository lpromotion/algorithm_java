import java.io.*;
import java.util.*;

public class Main {
    static int r, c; // 목표 좌표
    static int k; // 목표값
    static int[][] arr = new int[101][101]; // 배열 A
    static int rowLength = 3, colLength = 3; // 행과 열의 최대 길이
    static int time = 0; // 시간

    static class Pair implements Comparable<Pair> {
        int num; // 수
        int cnt; // 수의 등장 횟수

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            // 1. 등장 횟수가 작은 순으로
            if(this.cnt != o.cnt) return Integer.compare(this.cnt, o.cnt);

            // 2. 수가 작은 순으로
            return Integer.compare(this.num, o.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(true) {
            if(time > 100) { // 100초가 지나면 종료
                time = -1;
                break;
            }
            if(arr[r][c] == k) break; // A[r][c]에 들어있는 값이 k가 되면 종료

            if(rowLength >= colLength) { // R 연산
                for(int i=1; i<=rowLength; i++) {
                    rCalculation(i);
                }
            }
            else { // C 연산
                for(int i=1; i<=colLength; i++) {
                    cCalculation(i);
                }
            }
            
            time++; // 시간 증가
        }

        System.out.println(time);
    }

    // 각 숫자들의 개수를 구해서 HashMap에 담았다가 우선순위 큐에 옯겨담아 정렬
    static void rCalculation(int key) {
        // 우선순위 큐를 사용하여 Pair 객체를 정렬
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>(); // <수, 등장 횟수>

        for(int i=1; i<=colLength; i++) { // 현재 행의 각 숫자들의 등장 횟수를 계산
            if(arr[key][i] == 0) continue; // 0은 등장 횟수에서 무시
            // 숫자가 이미 있으면 등장 횟수를 1 증가, 없으면 1로 설정
            map.compute(arr[key][i], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        // map에 저장된 값을 우선순위 큐에 삽입 (자동으로 등장 횟수 및 숫자 순으로 정렬됨)
        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i=1;
        // 우선순위 큐에서 정렬된 값을 꺼내서 다시 배열에 숫자와 등장 횟수로 저장
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            arr[key][i++] = p.num;
            arr[key][i++] = p.cnt;
        }

        colLength = Math.max(colLength, i); // 가장 긴 행의 길이를 갱신

        // 배열의 나머지 부분을 0으로 채움
        while(i <= 100) {
            arr[key][i++] = 0;
        }
    }

    static void cCalculation(int key) {
        // 우선순위 큐를 사용하여 Pair 객체를 정렬
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>(); // <수, 등장 횟수>

        for(int i=1; i<=rowLength; i++) { // 현재 열의 각 숫자들의 등장 횟수를 계산
            if(arr[i][key] == 0) continue; // 0은 등장 횟수에서 무시
            // 숫자가 이미 있으면 등장 횟수를 1 증가, 없으면 1로 설정
            map.compute(arr[i][key], (num, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        // map에 저장된 값을 우선순위 큐에 삽입 (자동으로 등장 횟수 및 숫자 순으로 정렬됨)
        map.forEach((k, v) -> pq.add(new Pair(k, v)));

        int i=1;
        // 우선순위 큐에서 정렬된 값을 꺼내서 다시 배열에 숫자와 등장 횟수로 저장
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            arr[i++][key] = p.num;
            arr[i++][key] = p.cnt;
        }

        rowLength = Math.max(rowLength, i); // 가장 긴 열의 길이를 갱신

        // 배열의 나머지 부분을 0으로 채움
        while(i <= 100) {
            arr[i++][key] = 0;
        }
    }
}
