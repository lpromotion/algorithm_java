import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        while(n>0){
            st = new StringTokenizer(br.readLine(), " ");
            String op = st.nextToken();

            if(op.equals("push")){
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
            } else if(op.equals("pop")){
                if(stack.isEmpty()) sb.append("-1\n");
                else sb.append(stack.pop()).append("\n");
            } else if(op.equals("size")){
                sb.append(stack.size()).append("\n");
            } else if(op.equals("empty")){
                if(stack.isEmpty()) sb.append("1\n");
                else sb.append("0\n");
            } else {
                if(stack.isEmpty()) sb.append("-1\n");
                else sb.append(stack.peek()).append("\n");
            }
            n--;
        }
        System.out.println(sb.toString());
        br.close();
    }
  
}
