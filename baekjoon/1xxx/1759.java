import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] alpha, password;
    static boolean[] check;
    static int vo, con;
    static LinkedHashSet<String> result = new LinkedHashSet<>();

    static void backTrack(int start, int length) {
        if(length == L) {
            if(vo >= 1 && con >= 2) {
                String s = new String(password);
                result.add(s);
            }
            return;
        }

        for(int i=start; i<C; i++) {
            password[length] = alpha[i];
            if(check[i]) con++; else vo++;
            backTrack(i+1, length+1);
            if(check[i]) con--; else vo--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        alpha = new char[C];
        check = new boolean[C];
        password = new char[L];
        String[] alpha_input = br.readLine().split(" ");
        Arrays.sort(alpha_input);
        for(int i=0; i<C; i++) {
            char ch = alpha_input[i].charAt(0);
            alpha[i] = ch;
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') {
                check[i] = false; // 모음
            }
            else check[i] = true; // 자음
        }

        backTrack(0, 0);
        for(String s : result) {
            System.out.println(s);
        }
    }
}
