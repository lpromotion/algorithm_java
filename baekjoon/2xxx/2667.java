import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int count = 1; // 단지 내 집의 수 계산

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int a, int b) {
        map[a][b] = 0; // 현재 위치 방문 처리
        count++; // 집의 수 증가

        // 현재 위치의 상하좌우 탐색
        for(int i=0; i<4; i++) {
            int nx = a + dx[i];
            int ny = b + dy[i];
            // 다음 위치가 이동 가능한 범위이고, 집이 있는 경우
            if(nx>0 && nx<=n && ny>0 && ny<=n && map[nx][ny]==1) {
                dfs(nx, ny); // 해당 위치로 재귀 호출
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            String input = br.readLine();
            for(int j=1; j<=n; j++) {
                // 지도의 집 정보를 입력받아 저장
                map[i][j] = input.charAt(j-1) - '0';
            }
        }

        List<Integer> list = new ArrayList<>(); // 단지 내 집의 수 저장
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j] == 1) { // 집이 있는 경우
                    count = 0; // count 초기화
                    dfs(i, j); // dfs 호출
                    list.add(count); // 구한 단지의 집의 수 저장
                }
            }
        }

        list.sort(Comparator.naturalOrder()); // 리스트 오름차순 정렬
        System.out.println(list.size()); // 단지 수
        for(int l : list) {
            System.out.println(l); // 단지 내 집의 수
        }
    }
}

/** BFS 풀이 **/
/*
import java.io.*;
import java.util.*;

public class BJ_2667_bfs {
    static int n;
    static int[][] map;
    static int count = 1; // 단지 내 집의 수 계산

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int a, int b) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{a, b});
        map[a][b] = 0; // 시작 위치

        while(!q.isEmpty()) {
            int[] cur = q.poll(); // 현재 위치 꺼내기

            for(int i=0; i<4; i++) { // 현재 위치의 상하좌우 탐색
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                // 다음 위치가 이동 가능한 범위이고, 집이 있는 경우
                if(nx>0 && nx<=n && ny>0 && ny<=n && map[nx][ny]==1) {
                    q.add(new int[]{nx, ny}); // 큐에 다음 위치 저장
                    map[nx][ny] = 0; // 방문 처리
                    count++; // 집의 수 증가
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            String input = br.readLine();
            for(int j=1; j<=n; j++) {
                // 지도의 집 정보를 입력받아 저장
                map[i][j] = input.charAt(j-1) - '0';
            }
        }

        List<Integer> list = new ArrayList<>(); // 단지 내 집의 수 저장
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j] == 1) { // 집이 있는 경우
                    count = 1; // count 초기화
                    bfs(i, j); // bfs 호출
                    list.add(count); // 구한 단지의 집의 수 저장
                }
            }
        }

        list.sort(Comparator.naturalOrder()); // 리스트 오름차순 정렬
        System.out.println(list.size()); // 단지 수
        for(int l : list) {
            System.out.println(l); // 단지 내 집의 수
        }
    }
}*/
