import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[] trees;

    static void binarySearch() {
        long left = 0;
        long right = trees[N-1];
        long mid = 0;

        while(left <= right) {
            mid = left + (right - left) / 2;
            long sum = 0;
            for(long tree : trees) {
                if(tree > mid) sum += tree-mid;
            }
            if(sum >= (long)M) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        trees = new long[N];
        String[] treeInput = br.readLine().split(" ");
        for(int i=0; i<treeInput.length; i++) {
            trees[i] = Long.parseLong(treeInput[i]);
        }
        Arrays.sort(trees);
        binarySearch();
    }
}
