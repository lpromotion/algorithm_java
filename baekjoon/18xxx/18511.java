import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] num;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        num = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            // 입력받은 원소를 배열에 저장
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num); // 원소를 오름차순 정렬
        find(0); // 재귀 함수로 탐색
        System.out.println(result);
    }

    static void find(int cur) {
        // cur: 현재 만들어진 수
        if(cur > n) return; // cur이 n보다 크면 탐색 종료
        if(result < cur) result = cur; // cur이 결과값보다 크면 결과값 갱신

        for(int i=k-1; i>=0; i--) { // 큰 수 부터 탐색하도록 배열 역순으로 탐색
            find(cur*10 + num[i]); // 현재 수에 새로운 숫자를 붙여 재귀 호출
        }
    }
}
