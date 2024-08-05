import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int count; // recursion 함수의 호출 횟수

    // 문자열이 팰린드롬인지 재귀적으로 확인하는 함수
    static int recursion(String str, int l, int r) {
        if(l >= r) return 1; // 왼쪽 인덱스가 오른쪽 인덱스보다 크거나 같으면 팰린드롬 O
        else if(str.charAt(l) != str.charAt(r)) return 0; // 양 끝 문자가 다르면 팰린드롬 X
        else {
            count++; // 호출 횟수 증가
            return recursion(str, l+1, r-1); // 다음 검사할 인덱스로 재귀 호출
        }
    }


    static int isPalindrome(String str) {
        return recursion(str, 0, str.length()-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            String str = br.readLine();
            count = 1; // 각 테스트마다 count 초기화 (초기 호출 1포함)
            sb.append(isPalindrome(str)+" "+count+"\n"); // 결과 StringBuilder에 추가
        }
        System.out.println(sb);
    }
}
