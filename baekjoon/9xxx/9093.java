import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine() + "\n";
            for(char c : str.toCharArray()) {
                if(c == ' ' || c == '\n') {
                    while(!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(" ");
                } else {
                    stack.push(c);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
