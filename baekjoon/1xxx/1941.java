import java.io.*;
import java.util.*;

public class Main {
    static char[][] students;
    static int[] selected = new int[7]; // 선택된 학생들의 좌표 저장 (0~24로 저장)
    static boolean[] visited; // 방문 처리 배열
    static int count = 0; // 가능한 경우의 수

    // // 상하좌우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        students = new char[5][5];
        // 학생 자리 배치 정보 입력받기
        for(int i=0; i<5; i++) {
            students[i] = br.readLine().toCharArray();
        }

        // 백트래킹으로 7명의 학생 선택
        backTrack(0, 0, 0);

        System.out.println(count);
    }

    // 시작 위치, 'Y' 학생 수, 선택한 학생 수를 인자로 받음
    static void backTrack(int start, int yCnt, int depth) {
        // 'Y' 학생이 4명 이상이 되면 종료
        if(yCnt >= 4) return;

        // 7명을 선택하면 인접해있는지 검사
        if(depth == 7) {
            visited = new boolean[7];
            bfs();
            return;
        }

        // 0~24 중에서 7명을 선택 (5x5 배열을 1차원으로 변환하여 탐색)
        // 5로 나눈 몫을 행, 나머지를 열로 설정하면 5*5 행렬 표현이 가능함
        for(int i=start; i<25; i++) {
            selected[depth] = i; // i번쨰 좌표 선택
            if(students[i/5][i%5] == 'Y') { // 'Y' 학생일 경우
                backTrack(i+1, yCnt+1, depth+1);
            } else { // 'S' 학생일 경우
                backTrack(i+1, yCnt, depth+1);
            }
        }
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        // 첫 번째 선택된 학생의 위치 넣기
        q.offer(new int[]{selected[0]/5, selected[0]%5});
        visited[0] = true; // 방문 처리
        int cnt = 1; // 인접한 학생 수

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            // 상하좌우로 인접한 위치 탐색
            for(int i=0; i<4; i++) {
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                int ni = nx*5 + ny; // 새로운 좌표를 0~24 범위로 변환

                // 자리 범위 내에 있을 경우
                if(nx > -1 && nx < 5 && ny > -1 && ny < 5) {
                    // 선택된 7개의 인덱스가 서로 연결되어있는지 검사
                    for(int j=0; j<7; j++) {
                        // 아직 방문하지 않은 학생이면
                        if(!visited[j] && selected[j] == ni) {
                            visited[j] = true; // 방문 처리
                            q.offer(new int[]{nx, ny}); // 큐에 추가
                            cnt++; // 인접한 학생 수 증가
                        }
                    }
                }
            }
        }
        
        // 7명의 학생이 인접해있으면 count 증가
        if(cnt == 7) count++;
    }
}
