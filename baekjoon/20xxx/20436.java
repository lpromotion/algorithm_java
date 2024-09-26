import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] keyboard = {{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
                                {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'},
                                {'z', 'x', 'c', 'v', 'b', 'n', 'm'}};
    static int[] leftH, rightH; // 왼손, 오른손 위치
    static int[] curPos; // 문자의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char sL = st.nextToken().charAt(0); // 처음 왼손 문자
        char sR = st.nextToken().charAt(0); // 처음 오른손 문자

        for(int i=0; i<keyboard.length; i++) {
            for(int j=0; j<keyboard[i].length; j++) {
                // 왼손, 오른손의 처음 위치 찾기
                if(sL == keyboard[i][j]) leftH = new int[]{i, j};
                if(sR == keyboard[i][j]) rightH = new int[]{i, j};
            }
        }

        int time = 0; // 걸리는 시간

        char[] input = br.readLine().toCharArray(); // 문자열 입력받기
        for(int i=0; i<input.length; i++) {
            char c = input[i];
            boolean hand = findPosAndRange(c); // 오른손 범위: true, 왼손 범위: false
            if(hand) { // 문자가 오른손 범위이면
                // 거리 계산하고, 오른손의 위치 변경
                time += Math.abs(curPos[0] - rightH[0]) + Math.abs(curPos[1] - rightH[1]) + 1;
                rightH = new int[]{curPos[0], curPos[1]};
            } else { // 문자가 왼손 범위이면
                // 거리 계산하고, 왼손의 위치 변경
                time += Math.abs(curPos[0] - leftH[0]) + Math.abs(curPos[1] - leftH[1]) + 1;
                leftH = new int[]{curPos[0], curPos[1]};
            }
        }

        System.out.println(time);
    }
    
    // 현재 문자의 위치 확인하고, 문자가 어느 손의 범위인지 확인
    static boolean findPosAndRange(char c) {
        boolean check = false;

        for(int i=0; i<keyboard.length; i++) {
            for(int j=0; j<keyboard[i].length; j++) {
                if(keyboard[i][j] == c) {
                    curPos = new int[]{i, j}; // 문자의 현재 위치 저장
                    // 문자가 어느 손의 범위인지 확인
                    // 오른손 범위: [0][5~9], [1][5~8], [2][4~6]
                    if((i==0 && j>=5 && j<=9)
                    || (i==1 && j>=5 && j<=9)
                    || (i==2 && j>=4 && j<=6))
                        check = true;
                }
            }
        }

        return check;
    }
}
