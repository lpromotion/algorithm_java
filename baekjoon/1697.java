import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] position = new int[100001];

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int x = q.poll();
            int[] cx = {x-1, x+1, x*2};

            for(int i=0; i<3; i++) {
                if(cx[i] >= 0 && cx[i] <= 100000 && position[cx[i]] == 0) {
                    q.offer(cx[i]);
                    position[cx[i]] = position[x] + 1;
                }
                if(cx[i] == k) return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[] str = new BufferedReader(new InputStreamReader(System.in)).readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);

        position[n] = 1;
        bfs(n);

        int time = position[k]-1;
        System.out.println(time);
    }
}
