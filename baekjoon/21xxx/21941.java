import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine(); // 문자열 S
        int m = Integer.parseInt(br.readLine()); // 지울 수 있는 문자열 개수

        Map<String, Integer> substrings = new HashMap<>(); // 지울 수 없는 문자열을 저장
        StringTokenizer st;
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            String substring = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            substrings.put(substring, score);
        }

        int[] dp = new int[s.length()+1];

        for(int i=1; i<=s.length(); i++) {
            dp[i] = dp[i-1] + 1; // 한 글자 제거하는 경우

            for(String substr : substrings.keySet()) { // 해당 위치에서 끝나는 부분 문자열을 제거하는 경우
                if(i >= substr.length() && s.substring(i - substr.length(), i).equals(substr)) {
                    dp[i] = Math.max(dp[i], dp[i - substr.length()] + substrings.get(substr));
                }
            }
        }

        System.out.println(dp[s.length()]);
    }
}
