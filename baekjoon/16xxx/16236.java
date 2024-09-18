import java.io.*;
import java.util.*;

public class Main {
    static int n; // 공간 크기
    static int[][] space; // 물고기가 있는 공간 (물고기 크기 저장)
    static boolean[][] visited; // 방문 확인 배열
    static int time = 0; // 물고기 잡아먹는데 걸리는 시간
    static Shark shark = null;

    // 상좌하우 순서
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static class Shark {
        int[] pos; // 현재 위치
        int size; // 현재 크기
        int eaten; // 먹은 물고기 수

        public Shark(int[] pos, int size) { // 생성자
            this.pos = pos;
            this.size = size;
            this.eaten = 0;
        }
    }

    static class Fish implements Comparable<Fish> {
        int[] pos; // 현재 위치
        int time; // 아기상어가 먹으러 가는 시간

        public Fish(int[] pos, int time) { // 생성자
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Fish o) {
            // 1. 먹으러 가는 시간이 짧은 순
            if (this.time != o.time) return Integer.compare(this.time, o.time);

            // 2. 가장 위에 있는 물고기 순 (y좌표가 작을수록 위)
            if (this.pos[0] != o.pos[0]) return Integer.compare(this.pos[0], o.pos[0]);

            // 3. 가장 왼쪽에 있는 물고기 순 (x좌표가 작을수록 왼쪽)
            return Integer.compare(this.pos[1], o.pos[1]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        space = new int[n][n]; // 공간 초기화
        visited = new boolean[n][n]; // 방문 배열 초기화

        StringTokenizer st;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int fish = Integer.parseInt(st.nextToken());
                space[i][j] = fish; // 물고기 크기 저장
                if(fish == 9) { // 상어 현재 위치 찾으면
                    // 상어 객체 생성
                    shark = new Shark(new int[]{i, j}, 2);
                    space[i][j] = 0; // 상어의 위치는 빈 칸으로 설정
                }
            }
        }

        while(true) {
            Fish target = bfs(shark.pos); // 상어의 현재 위치에서 BFS 실행
            if(target == null) break; // 더 이상 먹을 물고기가 없으면 종료

            // 물고기 먹고 상어 위치 및 시간 갱신
            shark.pos = target.pos; // 상어 위치 갱신
            time += target.time; // 이동시간 추가
            shark.eaten++; // 먹은 물고기 수 증가
            space[target.pos[0]][target.pos[1]] = 0; // 물고기를 먹었으므로 공간을 빈칸으로 설정

            // 상어가 자신의 크기만큼 물고기를 먹었을 때 크기 증가
            if(shark.eaten == shark.size) {
                shark.size++;
                shark.eaten = 0; // 먹은 물고기 수 초기화
            }
        }

        System.out.println(time); // 총 걸린 시간
    }

    static Fish bfs(int[] pos) {
        PriorityQueue<Fish> fishQ = new PriorityQueue<>(); // 먹을 수 있는 물고기만 저장하는 우선순위 큐
        Queue<Fish> q = new LinkedList<>(); // 일반 BFS 탐색을 위한 큐
        q.add(new Fish(pos, 0));
        boolean[][] visited = new boolean[n][n];
        visited[pos[0]][pos[1]] = true;

        while(!q.isEmpty()) {
            Fish cur = q.poll();
            int[] curPos = cur.pos;
            int curTime = cur.time;

            // 상하좌우로 인접한 위치 탐색
            for(int i=0; i<4; i++) {
                int nx = curPos[0] + dx[i];
                int ny = curPos[1] + dy[i];

                // 다음 위치가 범위 안에 있고 아직 방문하지 않은 경우
                if(nx>=0 && nx<n && ny>=0 && ny<n && !visited[nx][ny]) {
                    // 아기 상어가 이동 가능한 경우
                    if(space[nx][ny] <= shark.size) {
                        visited[nx][ny] = true; // 방문 처리
                        q.add(new Fish(new int[]{nx, ny}, curTime + 1)); // 이동한 위치 추가

                        // 상어가 먹을 수 있는 물고기 (상어 크기보다 작은 경우)
                        if (space[nx][ny] > 0 && space[nx][ny] < shark.size) {
                            fishQ.add(new Fish(new int[]{nx, ny}, curTime + 1)); // 먹을 수 있는 물고기를 우선순위 큐에 추가
                        }
                    }
                }
            }
        }

        // 먹을 수 있는 물고기가 있으면 우선순위 큐에서 가장 우선순위가 높은 물고기를 반환
        if (!fishQ.isEmpty()) {
            return fishQ.poll(); // 가장 우선순위가 높은 물고기 반환
        }

        return null;
    }
}
