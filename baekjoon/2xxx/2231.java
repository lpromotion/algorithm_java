import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0; // 결과값 (생성자가 없는 경우: 초기값 0)
        int num = 1; // 1부터 시작하여 모든 가능한 숫자를 검사
        
        while(num < N) { // N보다 작은 숫자를 검사
            int cal = num; // 분해합 계산을 위해
            int sum = cal; // 먼저 자기 자신으로 분해합 초기화
            while(cal > 0) { // 각 자리수를 더함
                sum += cal % 10; // 일의 자리수
                cal /= 10;
            }

            if(sum == N) { // 계산한 분해합이 N과 같으면
                result = num; // 현재 숫자를 결과값으로 저장
                break; // while문 종료
            }
            num++; // 다음 숫자
        }
        System.out.println(result);
    }
}
