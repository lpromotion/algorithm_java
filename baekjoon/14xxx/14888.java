import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n; // 수의 개수
    static int[] nums; // 수의 배열
    static int[] oper; // 연산자 개수 저장하는 배열
    static int max = Integer.MIN_VALUE; // 최댓값
    static int min = Integer.MAX_VALUE; // 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        oper = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            // n개의 수를 입력받음
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            // 각 연산자 개수를 입력받음
            oper[i] = Integer.parseInt(st.nextToken());
        }

        // 1번째 수부터 계산 시작, 초기 결과값은 0번째 수
        backTrack(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    // 계산할 인덱스와 현재까지 계산된 결과를 인자로 받음
    static void backTrack(int numIdx, int result) {
        // 모든 수를 계산했을 경우, 최댓값과 최솟값 갱신
        if(numIdx == n) {
            max = Math.max(max, result);
            min = Math.min(min, result);
        }

        for(int j=0; j<4; j++) {
            // 연산자가 남아있으면
            if(oper[j] > 0) {
                int nextResult; // 연산 결과 저장
                if(j==0) { // 덧셈
                    nextResult = result + nums[numIdx];
                } else if(j==1) { // 뺄셈
                    nextResult = result - nums[numIdx];
                } else if(j==2) { // 곱셈
                    nextResult = result * nums[numIdx];
                } else {
                    if(result < 0) { // 음수 나눗셈 처리
                        nextResult = Math.abs(result) / nums[numIdx] * (-1);
                    }
                    else nextResult = result / nums[numIdx];
                }
                oper[j]--; // 연산자 소모
                backTrack(numIdx+1, nextResult); // 다음 수로 재귀호출
                oper[j]++; // 연산자 복구
            }
        }
    }
}
