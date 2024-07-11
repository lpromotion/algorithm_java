import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            for(char c : str.toCharArray()) {
                if(c == '(') stack.push(c);
                else {
                    if(stack.isEmpty()) {
                        stack.push(c); break;
                    }
                    else stack.pop();
                }
            }
            if(stack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
