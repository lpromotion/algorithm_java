import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        for(int x=-999; x<=999; x++) { // x에 -999부터 999까지 대입
            for(int y=-999; y<=999; y++) { // y에 -999부터 999까지 대입
                if(a*x + b*y == c && d*x + e*y == f) { // 두 개의 연립방정식에 만족하면
                    System.out.println(x+" "+y); // x와 y를 출력
                    return; // 프로그램 종료
                }
            }
        }
    }
}
