import java.io.*;

public class Main {
    static int n, m;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    static void backtracking(int depth) {
        if(depth == m) {
            for(int num : sequence) {
                sb.append(num + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++) {
            sequence[depth] = i;
            backtracking(depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        sequence = new int[m];

        int depth = 0;
        backtracking(depth);

        System.out.println(sb);
    }
}
