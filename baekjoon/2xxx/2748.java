import java.io.*;

public class Main {
    static int n; // n번쨰 피보나치 수 구하기
    static long[] arr1; // bottom up 방식에 사용할 배열
//    static long[] arr2; // top down 방식에 사용할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr1 = new long[n+1];
        System.out.println(bottomUp(n));

//        arr2 = new int[n+1];
//        System.out.println(topDown(n));
    }

    static long bottomUp(int n) {
        // n이 0이면, 0번째 피보나치 수를 반환
        if(n == 0) return arr1[0] = 0;

        // 초기값 설정
        arr1[0] = 0; arr1[1] = 1;

        // 2부터 n까지의 피보나치 수를 반복문을 통해 계산
        for(int i=2; i<=n; i++) {
            arr1[i] = arr1[i-1] + arr1[i-2];
        }
        
        return arr1[n]; // n번째 피보나치 수 반환
    }

//    static long topDown(int n) {
//        // n이 0 또는 1이면, 해당 값을 반환
//        if(n < 2) return arr2[n] = n;
//        // 이미 계산된 값이 있으면, 그 값을 반환
//        if(arr2[n] > 0) return arr2[n];
//
//        // 계산되지 않은 경우, 재귀 호출로 값을 계산하여 저장
//        arr2[n] = topDown(n-1) + topDown(n-2);
//        return arr2[n]; // n번째 피보나치 수 반환
//    }
}
