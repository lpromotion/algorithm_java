import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder result; // 문자열 저장할 StringBuilder

    static void cantor(int s, int len) {
        if(len == 1) return; // 길이가 1이면 종료
        int div = len/3; // 현재 문자열 길이 3등분
        for(int i=s+div; i<s+div*2; i++) {
            // 3등분 중 2번째 부분을 공백으로 변경
            result.setCharAt(i, ' ');
        }
        cantor(s, div); // 3등분 중 1번째 부분을 재귀 호출
        cantor(s+div*2, div); // 3등분 중 3번째 부분을 재귀 호출
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine()) != null) { // 파일의 끝에서 입력을 멈춤
            int n = Integer.parseInt(str);
            result = new StringBuilder(); // 문자열 초기화
            for(int i=0; i<(int)Math.pow(3, n); i++) {
                result.append("-"); // 3^n 만큼 "-"으로 초기화
            }
            cantor(0, result.length()); // 시작인덱스, 문자열 길이로 메서드 호출
            System.out.println(result);
        }
        br.close();
    }
}
