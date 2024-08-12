import java.io.*;
import java.util.*;

public class Main {
    static int[] arr = new int[6]; // 선택된 조합을 저장할 배열
    static int[] nums; // 입력받은 숫자 집합
    static int k; // 입력받은 숫자 개수
    static StringBuilder sb = new StringBuilder(); // 결과 저장

    static void dfs(int s, int index) { // 시작 인덱스, 선택한 숫자 개수
        if(index == 6) { // 6개를 모두 선택했으면
            for(int i=0; i<6; i++) {
                // 선택한 숫자를 StringBuilder에 추가
                sb.append(arr[i]+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=s; i<k; i++) { // 남은 숫자 중
            arr[index] = nums[i]; // 선택한 숫자를 저장
            dfs(i+1, index+1); // 다음 숫자 선택을 위해 재귀 호출
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String[] input = br.readLine().split(" ");
            k = Integer.parseInt(input[0]);
            if(k==0) break; // 0을 입력받으면 프로그램 종료

            nums = new int[k];
            for(int i=0; i<k; i++) {
                // 입력받은 숫자 집합을 배열에 저장
                nums[i] = Integer.parseInt(input[i+1]);
            }

            sb = new StringBuilder(); // StringBuilder 초기화
            dfs(0, 0); // DFS 시작
            System.out.println(sb);
        }
    }
}
