import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m; // 성의 가로, 세로 길이
    static int t; // 제한 시간
    static int[][] map; // 성의 공간 정보 저장
    static int resultTime; // 최종 걸린 시간

    // 방향 배열 (상하좌우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y;
        boolean findGram; // 그람 찾았는지 여부
        int time; // 걸린 시간

        public Node(int x, int y, boolean findGram, int time) {
            this.x = x;
            this.y = y;
            this.findGram = findGram;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        resultTime = t+1;
        bfs(0, 0);

        if(resultTime == 0 || resultTime > t) System.out.println("Fail");
        else System.out.println(resultTime);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y,  false, 0));
        boolean[][][] visited = new boolean[n][m][2]; // 그람을 찾지 못한 경우, 찾은 경우로 나눔
        visited[x][y][0] = true;
        
        while(!q.isEmpty()) {
            Node c = q.poll(); // 현재 위치 꺼내기

            if(c.x == n-1 &&  c.y == m-1) { // 도착점에 도달하면
                // 걸린 시간 최소값 갱신
                System.out.println(c.time);
                resultTime = Math.min(resultTime, c.time);
                continue;
            }
            
            for(int i=0; i<4; i++) { // 상하좌우 탐색
                int nx = dx[i] + c.x;
                int ny = dy[i] + c.y;
                
                // 다음 위치가 성의 범위 내에 있는 경우
                if(nx>=0 && nx<n && ny>=0 && ny<m) {
                    if(!c.findGram) { // 이전에 그람을 찾지 못한 경우
                        if(!visited[nx][ny][0] && map[nx][ny] == 0) {
                            // 이전에 방문한 적 없고, 빈 공간일 경우 이동
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, c.findGram, c.time+1));
                        } else if(!visited[nx][ny][0] && map[nx][ny] == 2) {
                            // 이전에 방문한 적 없고, 그람이 있을 경우 그람 여부 체크하고 이동
                            visited[nx][ny][0] = true;
                            q.add(new Node(nx, ny, true, c.time+1));
                        }
                    } else { // 이전에 그람을 찾은 경우
                        if(!visited[nx][ny][1]) {
                            // 이전에 방문한 적 없는 경우 이동
                            visited[nx][ny][1] = true;
                            q.add(new Node(nx, ny, c.findGram, c.time+1));
                        }
                    }
                }
            }
        }
    }
}
