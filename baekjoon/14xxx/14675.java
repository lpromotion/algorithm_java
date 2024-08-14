import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 트리의 정점 개수
        List<Integer>[] list = new ArrayList[n+1]; // 간선 정보 저장 (인접 리스트)
        for(int i=0; i<n+1; i++) { // 리스트 초기화
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for(int i=1; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 간선 정보 양방향 저장
            list[a].add(b);
            list[b].add(a);
        }

        int q = Integer.parseInt(br.readLine()); // 질의 개수
        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if(t == 1) { // 질의 t가 1인 경우 (단절점인지)
                // 해당 정점의 간선 개수가 2개 이상인 경우
                if (list[k].size() >= 2) System.out.println("yes");
                else System.out.println("no"); // 아닌 경우
            } else { // 질의 t가 2인 경우 (단절선인지)
                // 무조건 2개의 그래프로 나눠짐
                System.out.println("yes");
            }
        }
    }
}
