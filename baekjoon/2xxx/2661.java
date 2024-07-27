import java.io.*;

public class Main {
    static int N;

    static void backTrack(String seq) {
        if(seq.length() == N) {
            System.out.println(seq);
            System.exit(0);
        }

        for(int i=1; i<=3; i++) {
            if(isGoodSeq(seq+i))
                backTrack(seq+i);
        }
    }

    static boolean isGoodSeq(String seq) {
        int end = seq.length() / 2;
        for(int i=1; i<=end; i++) {
            String s1 = seq.substring(seq.length()-i*2, seq.length()-i);
            String s2 = seq.substring(seq.length()-i, seq.length());
            if(s1.equals(s2)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        backTrack("");
    }
}
