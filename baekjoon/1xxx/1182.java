import java.io.*;

public class Main {
    static int n, s;
    static int[] numbers;
    static boolean[] visited;
    static int depth, sum, result = 0;

    static void backTrack(int start, int curDepth) {
        if(curDepth == depth) {
            if(sum == s) result++;
            return;
        }

        for(int i=start; i<n; i++) {
            if(visited[i]) continue;
            sum += numbers[i]; visited[i] = true;
            backTrack(i+1, curDepth+1);
            sum -= numbers[i]; visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        s = Integer.parseInt(input[1]);
        numbers = new int[n];
        String[] numInput = br.readLine().split( " ");
        for(int i=0; i<numInput.length; i++) {
            numbers[i] = Integer.parseInt(numInput[i]);
        }

        for(int i=1; i<=n; i++) {
            sum = 0; depth = i;
            visited = new boolean[n];
            backTrack(0, 0);
        }
        System.out.println(result);
    }
}
