import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int count = 0; // 결과값 카운트
        for(int h=0; h<=N; h++) { // "시"를 0부터 N까지
            for(int m=0; m<60; m++) { // "분"을 0부터 59까지
                for(int s=0; s<60; s++) { // "초"를 1부터 59까지
                    // 시, 분, 초 중에 각 자리수에 K가 포함되어 있는지 확인
                    if(h/10 == K || m/10 == K || s/10 == K
                    || h%10 == K || m%10 == K || s%10 == K) {
                        count++; // K가 포함되어 있으면 count 증가
                    }
                }
            }
        }

        System.out.println(count);
    }
}
