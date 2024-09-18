import java.io.*;
import java.util.StringTokenizer;

public class Main 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] line = new int[n]; // 줄 배치 저장

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int cnt = Integer.parseInt(st.nextToken()); // 자신보다 키 큰 사람 수
            for(int j=0; j<n; j++) {
                // cnt가 0이고, 현재 위치가 비어있으면
                if(cnt == 0 && line[j] == 0) {
                    // 해당 자리에 배치
                    line[j] = i + 1;
                    break;
                } else if(line[j] == 0){ // 현재 위치 비어있으면
                    cnt--; // cnt 감소
                }
            }
        }

        for(int i : line) {
            System.out.print(i+" ");
        }
    }
}
