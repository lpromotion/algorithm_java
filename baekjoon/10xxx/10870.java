import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_10870 {
    static int n; 
    static int count = 1; // 순서 1부터 시작
    static int result = 0; // 피보나치 수 저장

    static void fibonacci(int a, int b) {
        if(count == n) { // 현재 순서가 n번째 이면
            System.out.println(result); // 현재 피보나치 수 출력
            return; // 종료
        }
        result = a + b; // 두 매개변수 값을 더하고 저장
        count++; // 현재 순서 갱신
        fibonacci(b, result); // 메서드 재귀 호출
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if(n==0) System.out.println(0); // n이 0이면 0을 출력
        else if(n==1) System.out.println(1); // n이 1이면 1을 출력
        else fibonacci(0, 1); // n이 2이상이면 fibonacci 메서드 호출
    }
}
