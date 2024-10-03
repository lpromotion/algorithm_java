import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isComposite = new boolean[N+1]; // false: 소수, true: 합성수
        isComposite[0] = isComposite[1] = true; // 0, 1는 소수가 아님

        // 에라토스테네스의 체 알고리즘 사용
        for(int i=2; i*i<=N; i++) {
            if(!isComposite[i]) { // i가 소수인 경우
                for(int j=i*i; j<=N; j+=i) { // i의 배수들을 합성수로 표시
                    isComposite[j] = true;
                }
            }
        }

        for(int i=M; i<=N; i++) {
            if(!isComposite[i]) // 소수인 수를 출력
                System.out.println(i);
        }
    }
}
