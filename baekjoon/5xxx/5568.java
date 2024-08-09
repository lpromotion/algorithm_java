import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static String[] arr;
    static boolean[] visited;
    static StringBuilder st = new StringBuilder();
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new String[n]; // 카드 배열 초기화
        visited = new boolean[n]; // 방문 체크 배열 초기화
        for(int i=0; i<n; i++) {
            arr[i] = br.readLine(); // 각 카드 수를 문자열로 저장
        }

        backTrack(0, ""); // 백트래킹 시작
        System.out.println(set.size()); // set의 size가 중복 제거된 조합의 수
    }

    static void backTrack(int count, String next) {
        if(count == k) { // 현재 선택한 카드 수가 k개이면
            set.add(next); // 현재 조합을 HastSet에 추가
            return;
        }

        for(int i=0; i<n; i++) { // 모든 카드를 탐색
            if(!visited[i]) { // 아직 선택하지 않은 카드이면
                visited[i] = true; // 해당 카드 방문 처리
                // {선택 카드 수 + 1}, {현재 카드 추가한 조합} 으로 재귀 호출
                backTrack(count+1, next+arr[i]);
                visited[i] = false; // 해당 카드 방문 처리 취소
            }
        }
    }
}
