import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 카드의 개수
        int M = Integer.parseInt(st.nextToken()); // 목표값
        int[] card = new int[N]; // 카드의 숫자를 배열에 저장
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0; // 최종 결과
        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                for(int k=j+1; k<N; k++) {
                    int sum = card[i]+card[j]+card[k]; // 현재 선택한 카드 3장의 합
                    // 현재 합이 M 이하이고 result 보다 크면 result 갱신
                    if(sum<=M && sum>result) result = sum;
                }
            }
        }
        System.out.println(result);
    }
}
