import java.io.*;
import java.util.*;

public class Main {
    static class Node { // 노드를 나타내는 클래스
        char value; // 노드의 값
        Node left, right; // 왼쪽, 오른쪽 자식을 가리키는 노드 참조

        Node(char value, Node left, Node right) { // 노드 생성자
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // 트리의 루트 노드 'A'로 생성
    static Node head = new Node('A', null, null);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 이진 트리의 노드 개수

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0); // 루트 노드 값
            char left = st.nextToken().charAt(0); // 왼쪽 자식 노드 값
            char right = st.nextToken().charAt(0); // 오른쪽 자식 노드 값

            insertNode(head, root, left, right); // 트리에 노드 삽입
        }

        preOrder(head); // 전위 순회
        System.out.println();
        inOrder(head); // 중위 순회
        System.out.println();
        postOrder(head); // 후위 순회
    }

    // 트리에 노드 삽입하는 메서드
    static void insertNode(Node tmp, char root, char left, char right) {
        if(tmp.value == root) { // 현재 노드가 삽입하려는 부모 노드(root)와 일치하는 경우
            // 왼쪽 or 오른쪽 자식이 '.'이 아니면 새 노드를 생성하여 연결
            tmp.left = left == '.' ? null : new Node(left, null, null);
            tmp.right = right == '.' ? null : new Node(right, null, null);
        }
        else { // 삽입하려는 부모 노드를 찾기 위해 재귀 탐색
            // 현재 노드의 왼쪽 or 오른쪽 자식이 존재하면, 그 자식 노드에서 다시 삽입 시도 (재귀 호출)
            if(tmp.left != null) insertNode(tmp.left, root, left, right);
            if(tmp.right != null) insertNode(tmp.right, root, left, right);
        }
    }

    static void preOrder(Node node) { // 루트->왼쪽->오른쪽 순서
        if(node == null) return;
        System.out.print(node.value); // 현재 노드 출력
        preOrder(node.left); // 왼쪽 자식 노드 방문
        preOrder(node.right); // 오른쪽 자식 노드 방문
    }

    static void inOrder(Node node) { // 왼쪽->루트->오른쪽 순서
        if(node == null) return;
        inOrder(node.left); // 왼쪽 자식 노드 방문
        System.out.print(node.value); // 현재 노드 출력
        inOrder(node.right); // 오른쪽 자식 노드 방문
    }

    static void postOrder(Node node) { // 왼쪽->오른쪽->루트 순서
        if(node == null) return;
        postOrder(node.left); // 왼쪽 자식 노드 방문
        postOrder(node.right); // 오른쪽 자식 노드 방문
        System.out.print(node.value); // 현재 노드 출력
    }
}
