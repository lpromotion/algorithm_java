import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 격자 크기, 이동 명령 수
    static int map[][]; // 물의 양을 저장하는 격자
    static Queue<int[]> cloud; // 구름 칸 저장
    static boolean[][] visited; // 구름 사라진 칸 확인

    // 8가지 방향
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 이동 명령 수
        map = new int[n][n]; // 격자
        visited = new boolean[n][n]; // 구름 사라진 칸 초기화

        for(int i=0; i<n; i++) { // 물의 양 정보 입력받기
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비구름 생성
        cloud = new LinkedList<>(); // 구름 위치 저장
        cloud.add(new int[]{n-1, 0});
        cloud.add(new int[]{n-1, 1});
        cloud.add(new int[]{n-2, 0});
        cloud.add(new int[]{n-2, 1});

        // m번 이동 수행
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            moveCloud_addWater(d, s); // 구름 이동 & 구름 칸 물의 양 1 증가
            removeCloud_bugWater(); // 구름 제거 & 물복사버그 시전
            addCloud_deWater(); // 구름 생성 & 물의 양 감소시킴
        }

        System.out.println(getWater()); // 모든 칸의 물의 양의 합
    }

    // 구름 이동 & 구름 칸 물의 양 1 증가
    static void moveCloud_addWater(int d, int s) {
        for(int[] c : cloud) {
            // 방향 d로 s칸 이동 (1-based 처리)
            c[0] = (n + c[0] + dx[d] * (s % n)) % n;
            c[1] = (n + c[1] + dy[d] * (s % n)) % n;

            map[c[0]][c[1]]++; // 물의 양 증가
        }
    }

    // 구름 제거 & 물복사버그 시전
    static void removeCloud_bugWater() {
        while(!cloud.isEmpty()) {
            int[] c = cloud.poll();
            visited[c[0]][c[1]] = true; // 구른 사라진 칸 체크

            // 대각선 방향 탐색
            for(int i=1; i<=7; i+=2) {
                int nx = c[0] + dx[i];
                int ny = c[1] + dy[i];
                // 대각선 위치가 격자 범위 내에 있으면
                if(nx>=0 && nx<n && ny>=0 && ny<n) {
                    if(map[nx][ny] >= 1) // 물이 들어있으면
                        map[c[0]][c[1]]++;
                }
            }
        }
    }

    // 구름 생성 & 물의 양 감소시킴
    static void addCloud_deWater() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // 구름이 사라진 칸이 아니고 물의 양이 2 이상인 칸
                if(!visited[i][j] && map[i][j] >= 2) {
                    cloud.add(new int[]{i, j}); // 구름 저장
                    map[i][j] -= 2; // 물의 양 2 감소시킴
                }
            }
        }
        visited = new boolean[n][n]; // 구름 사라진 칸 초기화
    }

    // 모든 칸의 물의 양의 합
    static int getWater() {
        int mount = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                mount += map[i][j];
            }
        }
        return mount;
    }
}
