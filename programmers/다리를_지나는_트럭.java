import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int answer = 0;
        int currentWeight = 0;
        int truckIndex = 0;

        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }

	// 모든 트럭이 다리에 진입할 때까지 반복
        while (truckIndex < truck_weights.length) {
            answer ++;
            
            // 다리를 완전히 건넌 트럭 제거 & 무게 계산
            currentWeight -= bridge.poll(); 

	    // 다리에 올라가는 조건
            if (currentWeight + truck_weights[truckIndex] <= weight) {
                bridge.offer(truck_weights[truckIndex]);
                currentWeight += truck_weights[truckIndex];
                truckIndex++;
            } else {
                bridge.offer(0);
            }
        }

        return answer + bridge_length;
    }
}
