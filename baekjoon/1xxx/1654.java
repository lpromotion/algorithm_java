import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static long[] lines;

    static void binarySearch() {
        long left = 0;
        long right = lines[K-1];

        while(left <= right) {
            long mid = left + (right - left) / 2;
            if(mid == 0) break;
            long count = 0;
            for(long line : lines) {
                if(line >= mid)
                    count += line / mid;
            }
            if(count >= N) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        K = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        lines = new long[K];
        for(int i=0; i<K; i++) {
            lines[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(lines);

        binarySearch();
    }
}
