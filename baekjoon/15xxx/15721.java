import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine()); // 사람 수
        int T = Integer.parseInt(br.readLine()); // 구하고자 하는 "뻔"과 "데기"의 순서
        int C = Integer.parseInt(br.readLine()); // 0이면 "뻔", 1이면 "데기"

        int person = 0; // 현재 순서의 사람 (0부터)
        int count = 0; // 현재까지 발생한 "뻔" 또는 "데기"의 횟수
        int round = 1; // 현재 라운드 (1부터)

        while(true) {
            // "뻔 – 데기 – 뻔 – 데기" 패턴
            for(int i=0; i<4; i++) {
                // 현재 순서가 "뻔"인지 "데기"인지 확인
                // 원하는 구호이고 T번째 발생했을 때
                if(i%2 == C && ++count == T) {
                    System.out.println(person % A); // T번째 발생 시 현재 사람 번호 출력
                    return;
                }
                person++; // 다음 사람
            }

            // "뻔" round+1 번 반복
            for(int i=0; i<round+1; i++) {
                if(C == 0 && ++count == T) { // "뻔"을 찾는 경우이고 T번째 발생했을 때
                    System.out.println(person % A);
                    return;
                }
                person++;
            }

            // "데기" round+1 번 반복
            for(int i=0; i<round+1; i++) {
                if(C == 1 && ++count == T) { // "데기"을 찾는 경우이고 T번째 발생했을 때
                    System.out.println(person % A);
                    return;
                }
                person++;
            }

            round++; // 다음 라운드 (반복 횟수 증가)
        }
    }
}
