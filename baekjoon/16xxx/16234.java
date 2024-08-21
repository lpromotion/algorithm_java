import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] country; // 각 나라의 인구 정보 저장
    static boolean[][] visited; // 방문 여부 확인

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>(); // BFS에 사용할 큐
        q.add(new int[]{x, y}); // 시작 위치 큐에 추가
        Queue<int[]> union = new ArrayDeque<>(); // 연합인 나라의 위치를 저장할 큐
        union.add(new int[]{x, y});
        visited[x][y] = true; // 시작 위치 방문 처리

        int population = country[x][y]; // 연합의 인구수
        int count = 1; // 연합을 이루고 있는 칸의 수

        while(!q.isEmpty()){
            int[] cur = q.poll(); // 큐에서 현재 위치 꺼냄
            for(int i=0; i<4; i++) { // 상하좌우로 인접한 칸 탐색
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                // 땅의 범위 내에 있고, 아직 방문하지 않은 칸일 경우
                if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
                    // 인구 차이 계산
                    int absDif = Math.abs(country[cur[0]][cur[1]] - country[nx][ny]);
                    if(absDif>=L && absDif<=R) { // 인구 차이가 L이상 R이하일 경우
                        q.add(new int[]{nx, ny}); // 다음 탐색 위한 큐에 추가
                        visited[nx][ny] = true; // 방문 처리
                        union.add(new int[]{nx, ny}); // 연합 큐에 추가
                        population += country[nx][ny]; // 연합 총 인구수 갱신
                        count++; // 연합 이루는 칸의 수 증가
                    }
                }
            }
        }

        if(count > 1) { // 연합을 이루는 나라가 있다면
            int newPopulation = population/count; // 새로운 인구수 계산
            while(!union.isEmpty()) {
                // 연합에 속한 칸에 인구수 갱신
                int[] qq = union.poll();
                country[qq[0]][qq[1]] = newPopulation;
            }
            return true; // 인구 이동 일어남 (반환)
        }
        return false; // 인구 이동 일어나지 않음 (반환)
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        country = new int[N][N];
        for(int i=0; i<N; i++) { // 각 나라의 인구 정보 저장
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0; // 인구 이동이 발생한 날 수
        boolean check = true; // 인구 이동이 일어났는지 확인
        while(check) { // 인구 이동이 더이상 없으면 종료
            check = false; // 인구 이동 확인 초기화
            visited = new boolean[N][N]; // 방문 여부 배열 초기화
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visited[i][j]) { // 방문하지 않은 나라일 경우
                        // BFS를 수행하고, 인구 이동이 발생하면 check를 true로 설정
                        if(bfs(i, j)) check = true;
                    }
                }
            }

            if(check) day++; // 인구 이동이 발생했으면 day를 증가
        }

        System.out.println(day);
    }
}
