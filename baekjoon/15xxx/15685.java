import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> directions = getDirections(d, g);
            draw(x, y, directions);
        }

        System.out.println(getCount());
    }

    static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>(); // 방향 저장
        directions.add(d); // 초기 방향 입력

        while(g-- > 0) { // g세대까지 진행
            // "마지막 방향 -> 처음 방향" 순서로 진행
            for(int i=directions.size()-1; i>=0; i--) {
                // 새로운 방향 = (기존 d + 1) % 4
                int direction = (directions.get(i) + 1) % 4;
                directions.add(direction); // 새로운 방향 추가
            }
        }

        return directions;
    }

    static void draw(int x, int y, List<Integer> directions) {
        map[x][y] = true; // 시작점 체크

        for(int direction : directions) {
            if(direction == 0) map[++x][y] = true;
            else if(direction == 1) map[x][--y] = true;
            else if(direction == 2) map[--x][y] = true;
            else if(direction == 3) map[x][++y] = true;
        }
    }

    static int getCount() {
        int count = 0;

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
                    count++;
            }
        }

        return count;
    }
}
