import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = (long)n * Arrays.stream(times).max().getAsInt();
        long mid = 0;
        while(left <= right) {
            mid = left + (right - left) / 2;
            long count = 0;
            for(int time : times) {
                count += mid / time;
            }
            if(count < n) left = mid + 1;
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}
