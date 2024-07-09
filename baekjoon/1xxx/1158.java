import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] r = br.readLine().split(" ");
        int N = Integer.parseInt(r[0]); int K = Integer.parseInt(r[1]);
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++){
            q.offer(i);
        }
        sb.append("<");

        while(N-- > 0) {
            for(int i=0; i<K-1; i++) q.offer(q.poll());
            sb.append(q.poll());

            if(N!=0) sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb);
    }
}
