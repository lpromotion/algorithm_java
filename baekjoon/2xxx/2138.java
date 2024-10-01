import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 전구와 스위치 개수
        int[] curA = new int[n]; // 현재 전구 상태 저장
        int[] curB = new int[n]; // 현재 전구 상태 저장
        int[] result = new int[n]; // 만들고자 하는 전구 상태 저장

        String input = br.readLine();
        for(int i=0; i<n; i++) {
            curA[i] = curB[i] = input.charAt(i) - '0';
        }
        input = br.readLine();
        for(int i=0; i<n; i++) {
            result[i] = input.charAt(i) - '0';
        }

        // 첫번째 스위치를 누르는 경우와 누르지 않는 경우를 저장
        // curA의 첫번째 스위치를 누름
        curA[0] ^= 1;
        curA[1] ^= 1;
        int countA = 1, countB = 0;
        
        for(int i=1; i<n; i++) {
            // i-1 번째 전구가 목표 상태와 다르면 i번째 스위치를 누름
            if(curA[i-1] != result[i-1]) {
                curA[i-1] ^= 1; // 비트 연산으로 전구 상태 변경
                curA[i] ^= 1;
                if(i < n-1) curA[i+1] ^= 1;
                countA++;
            }
            if(curB[i-1] != result[i-1]) {
                curB[i-1] ^= 1;
                curB[i] ^= 1;
                if(i < n-1) curB[i+1] ^= 1;
                countB++;
            }
        }

        boolean possibleA = curA[n-1] == result[n-1];
        boolean possibleB = curB[n-1] == result[n-1];

        // 두 가지 경우 중 목표 상태와 같고 누른 횟수가 작은 것을 선택
        if(possibleA && possibleB) System.out.println(Math.min(countA, countB));
        else if(possibleA) System.out.println(countA);
        else if(possibleB) System.out.println(countB);
        else System.out.println(-1); // 둘다 불가능하면 -1
    }
}
