import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());

        Queue<int[]> q = new LinkedList<>();

        while(test-- > 0) {
            q.clear();
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                int[] a = {i, Integer.parseInt(st.nextToken())};
                q.offer(a); // {index, priority}
            }

            while(!q.isEmpty()) {
                int[] first = q.poll();
                boolean pass = true;
                for(int[] doc : q) {
                    if(first[1] < doc[1]){
                        q.offer(first);
                        pass = false; break;
                    }
                }
                if(pass) {
                    count++;
                    if(m == first[0]) break;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
