import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] sequence, numbers;
    static boolean[] visited;
    static LinkedHashSet<String> result = new LinkedHashSet<>();

    static void backTracking(int depth) {
        if(depth == m) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<m; i++) {
                sb.append(sequence[i] + " ");
            }
            result.add(sb.toString());
            return;
        }

        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            sequence[depth] = numbers[i];
            backTracking(depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        visited = new boolean[n];
        sequence = new int[n];
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num;
        }
        Arrays.sort(numbers);

        int depth = 0;
        backTracking(depth);
        for(String i : result) {
            System.out.println(i);
        }
    }
}
