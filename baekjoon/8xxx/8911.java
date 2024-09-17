import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 방향 배열: 북(0), 동(1), 남(2), 서(3)
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while(n-- > 0) {
            int curDir = 0; // 시작 시 북쪽 방향

            // 최소/최대 x, y 좌표
            int minX = 0, maxX = 0, minY = 0, maxY = 0;
            int x = 0, y = 0; // 계산할 x, y 좌표

            String move = br.readLine();
            for(int i=0; i<move.length(); i++) {
                char turtle = move.charAt(i); // 명령 입력받기

                if(turtle == 'F') { // 한 눈금 앞으로
                    x += dx[curDir];
                    y += dy[curDir];
                } else if(turtle == 'B') { // 한 눈금 뒤로
                    x -= dx[curDir];
                    y -= dy[curDir];
                } else if(turtle == 'L') { // 왼쪽으로 90도 회전
                    curDir = (curDir + 3) % 4;
                } else { // 오른쪽으로 90도 회전
                    curDir = (curDir + 1) % 4;
                }
                
                // x, y 좌표 최소/최대값 갱신
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }

            int lenX = maxX - minX;
            int lenY = maxY - minY;
            sb.append(lenX * lenY+"\n");
        }

        System.out.println(sb);
    }
}
